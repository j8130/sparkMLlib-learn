package c04

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.random.RandomRDDs._

object test14RandomRDD {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("testRandomRDD")
    val sc = new SparkContext(conf)
    val randomNum = normalRDD(sc, 100) // 创建100个随机数
    randomNum.foreach(println)
  }
}
