

import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.io.File

/**
  * Created by Hongkun Jin on 29-Jan-18.
  */
object SparkWordCount {

  def main(args: Array[String]) {

//    System.setProperty("hadoop.home.dir","/home/max/Downloads/")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input")

    val fm1=input.flatMap(line=>line.split(" ")) //transformation

    val fm2=fm1.map(word=>(word, word.take(1)))  //transformation and action

    val fm3=fm2.map(f => (f._2, f._1))           //transformation

    val fm4=fm3.reduceByKey(_+","+_)             //action

    fm4.foreach(println(_))                      //action
    fm4.saveAsTextFile("output")                 //action
    }

}

