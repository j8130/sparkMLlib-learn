package c03

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 将传入的数据依据 作为参数 传入的计算方法进行分组
 */
object groupBy {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("groupBy ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    var arr = sc.parallelize(Array(1, 2, 3, 4, 5)) //创建数据集

    // 第一个参数是传入的方法名，第二个参数是分组的标签值
    arr.groupBy(myFilter(_), 1) //设置第一个分组
    arr.groupBy(myFilter2(_), 2) //设置第二个分组

    arr.groupBy(myFilter2(_), 2).foreach(println)
  }

  def myFilter(num: Int): Unit = { //自定义方法
    num >= 3 //条件
  }

  def myFilter2(num: Int): Unit = { //自定义方法
    num < 3 //条件
  }
}
