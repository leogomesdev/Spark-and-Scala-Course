package com.sundogsoftware.spark

import com.sundogsoftware.spark.FriendsByAgeFromCSV.Person
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{round, sum}
import org.apache.spark.sql.types.{FloatType, IntegerType, StructType}

object TotalAmountSpentByEachCostumerDataset {

  case class Order(customer_id: Int, item_id: Int, amount_spent: Double)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("TotalAmountSpentByEachCostumerDataset")
      .master("local[*]")
      .getOrCreate()

    val ordersSchema = new StructType()
      .add("customer_id", IntegerType,nullable = true)
      .add("item_id", IntegerType,nullable = true)
      .add("amount_spent", FloatType,nullable = true)

    import spark.implicits._
    val ordersDS = spark.read
      .schema(ordersSchema)
      .csv("data/customer-orders.csv")
      .as[Order]

    val totalByEachCostumer = ordersDS
      .groupBy("customer_id")
      .agg(round(sum("amount_spent"), 2)
        .alias("total_amount"))
      .sort("total_amount")

    totalByEachCostumer.show(totalByEachCostumer.count.toInt)
  }
}
