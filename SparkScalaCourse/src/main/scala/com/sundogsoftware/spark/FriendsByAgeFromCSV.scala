package com.sundogsoftware.spark

import com.sundogsoftware.spark.DataFramesDataset.Person
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, round}

object FriendsByAgeFromCSV {

  case class Person(id: Int, name: String, age: Int, friends: Int)

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._
    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person]

    println("Schema:")
    people
      .printSchema()

    println("Average of # friends by age:")
    people
      .groupBy("age")
      .agg(round(avg("friends"), 1)
        .alias("friends_avg"))
      .sort("age").show()
  }
}
