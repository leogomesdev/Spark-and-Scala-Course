# Spark and Scala Course
 
The initial content of this project was provided by Frank Kane in the Udemy Course [Apache Spark with Scala - Hands On with Big Data!](https://www.udemy.com/course/apache-spark-with-scala-hands-on-with-big-data/)

More information is available on [this page of Sundog Education by Frank Kane](https://sundog-education.com/sparkscala)

## Running

To run this project, I recommend you to use:

- [SDKMAN!](https://sdkman.io) to easly install locally:
  - [Java](https://openjdk.java.net)
  - [Scala](https://www.scala-lang.org)
  - Spark
  - SBT
- [IntelliJ IDEA: The Capable & Ergonomic Java IDE by JetBrains](https://www.jetbrains.com/idea/download/#section=mac)
- [Scala - plugin for IntelliJ IDEA and Android Studio | JetBrains](https://plugins.jetbrains.com/plugin/1347-scala)

## Using Spark

### Setup the artifact

Once your IDE is installed and ready to use, import the folder *SparkScalaCourse*

Go to File Menu -> Project Structure -> Artifacts
New (+) -> JAR -> Empty
Extend *Spark Scala Course* to add *'Spark Scala Course' compile output* 

Hit "Include in project build"

spark-submit --class com.sundogsoftware.spark.HelloWorld out/artifacts/SparkCourse/SparkCourse.jar

### Packing one script with SBT and running locally with Spark


Note: Edit *sbt/build.sbt* if required, to change versions os scala and spark

Sample to build the file *SparkScalaCourse/sbt/src/main/scala/MinTemperaturesDataset.scala*:

- Open the terminal, inside sbt folder, run:

```bash
  sbt assembly
  cp -v target/scala-2.12/MinTemperaturesDataset-assembly-1.0.jar ../
  cd ..
  spark-submit MinTemperaturesDataset-assembly-1.0.jar
```

