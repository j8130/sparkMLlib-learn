package c03

import org.apache.spark.{SparkConf, SparkContext}

object testRDDMethod3 { // aggregate用于字符串
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testRDDMethod3 ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val arr = sc.parallelize(Array("abc", "b", "c", "d", "e", "f")) //创建数据集
    val result = arr.aggregate("")((value, word) => value + word, _ + _) //调用计算方法
    println(result) //打印结果
  }
}

