package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

/** Find the superheroes with minimum co-appearance. */
object LessPopularSuperheroesDataset {

  case class SuperHeroNames(id: Int, name: String)
  case class SuperHero(value: String)
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("LessPopularSuperheroesDataset")
      .master("local[*]")
      .getOrCreate()

    // Create schema when reading Marvel-names.txt
    val superHeroNamesSchema = new StructType()
      .add("id", IntegerType, nullable = true)
      .add("name", StringType, nullable = true)

    // Build up a hero ID -> name Dataset
    import spark.implicits._
    val namesDS = spark.read
      .schema(superHeroNamesSchema)
      .option("sep", " ")
      .csv("data/Marvel-names.txt")
      .as[SuperHeroNames]

    val lines = spark.read
      .text("data/Marvel-graph.txt")
      .as[SuperHero]

    val connections = lines
      .withColumn("id", split(col("value"), " ")(0))
      .withColumn("connections", size(split(col("value"), " ")) - 1)
      .groupBy("id").agg(sum("connections").alias("connections"))

    // TO NOT assume that 1 is the minimum times that one hero appears, we get the "1" from the dataset
    val minimumConnectionCount = connections.agg(min("connections")).first().getLong(0)

    val lessPopularHeroes = connections
        .filter($"connections" === minimumConnectionCount)

    val namesListOfLessPopularHeroes = lessPopularHeroes
      .join(namesDS, usingColumn = "id")

    println(s"Those characters have only $minimumConnectionCount connection(s):")
    namesListOfLessPopularHeroes.select("name").show(namesListOfLessPopularHeroes.count.toInt)
    println(s"Total: ${namesListOfLessPopularHeroes.count()}")

    spark.stop()
  }
}
