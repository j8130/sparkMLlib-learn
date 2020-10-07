package c03

import org.apache.spark.{SparkConf, SparkContext}

/**
 * reduce主要是对传入的数据进行合并处理，两个下划线分别代表不同的内容，
 * 第一个下划线代表数据集的第一个数据，第二个下划线在第一次合并处理时代表空集
 *
 * 即以以下方式进行
 * null + one -> one + two -> onetwo + three -> onetwothree + four -> onetwothreefour + five
 */
object testReduce {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testReduce ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    var str = sc.parallelize(Array("one", "two", "three", "four", "five")) //创建数据集
    val result = str.reduce(_ + _) //进行数据拟合
    result.foreach(print) //打印数据结果
  }
}
