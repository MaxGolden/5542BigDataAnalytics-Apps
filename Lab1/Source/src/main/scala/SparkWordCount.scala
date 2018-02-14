

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

    System.setProperty("hadoop.home.dir", "C:\\bin\\winutils.exe")

    val sparkConf = new SparkConf().setAppName("LAB1").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val input = sc.textFile("u.data")  // Input the 100000 movie rating data

    val rep = input.map(line =>line.replace('\t', ',')) // Replace all the TABs in the data to comma

    val newdata1 = rep.map(line=>(line.split(",")(0),1))  //Select the first row we need (for User ID) and make every line label number

    val output = newdata1.reduceByKey(_ + _).filter(x=> x._2.toInt > 25) // Use filter to show the users who rated over 25 items

    output.saveAsTextFile("output")  // Output the file
  }
}

