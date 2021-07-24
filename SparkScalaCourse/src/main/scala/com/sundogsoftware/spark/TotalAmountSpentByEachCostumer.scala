package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TotalAmountSpentByEachCostumer {
  /** A function that splits a line of input into (customer, orderPrice) tuples. */
  def parseLine(line: String): (Int, Float) = {
    // Split by commas
    val fields = line.split(",")
    // Extract the customer and orderPrice fields, and convert to int and float
    val customer = fields(0).toInt
    val orderPrice = fields(2).toFloat
    // Create a tuple that is our result.
    (customer, orderPrice)
  }

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalAmountSpentByEachCostumer")

    // Load each line of the source data into an RDD
    val lines = sc.textFile("data/customer-orders.csv")

    // Use our parseLines function to convert to (customer, orderPrice) tuples
    val mappedInput = lines.map(parseLine)

    val totalByCustomer = mappedInput.reduceByKey( (x,y) => (x + y))

    val flipped = totalByCustomer.map( x => (x._2, x._1) )

    val totalByCustomerSorted = flipped.sortByKey()

    val results = totalByCustomerSorted.collect()

    results.foreach(println)
  }
}
