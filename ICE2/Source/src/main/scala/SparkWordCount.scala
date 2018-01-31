

import breeze.linalg.DenseMatrix
import com.sun.xml.fastinfoset.util.CharArray
import org.apache.spark.status.api.v1.RDDPartitionInfo
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.io.File

/**
  * Created by Hongkun Jin on 29-Jan-18.
  */
object SparkWordCount {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("LAB1").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val input = sc.textFile("u.data")  // Input the 100000 movie rating data

    val rep = input.map(line =>line.replace('\t', ',')) // Replace all the TABs in the data to comma

    val newdata = rep.map(line=>(line.split(",")(0),line.split(",")(1))).cache()  //Select the first two rows we need (for User ID, Item ID)

    val output = newdata.filter(x=> x._2.toInt > 25) // Use filter to show the ItemID which more than 25

    output.saveAsTextFile("output")  // Output the file

  }
}

