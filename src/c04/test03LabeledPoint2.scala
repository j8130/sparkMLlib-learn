package c04

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark._
import org.apache.spark.mllib.util.MLUtils

object test03LabeledPoint2 {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("SparkPi") //建立本地环境变量
    val sc = new SparkContext(conf) //建立Spark处理

    // 数据集标记index是从1开始的，从0开始报错
    val mu = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\loadLibSVMFile.txt") //从C路径盘读取文件
    mu.foreach(println) //打印内容
  }
}
