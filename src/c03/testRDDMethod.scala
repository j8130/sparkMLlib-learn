package c03

import org.apache.spark.{SparkConf, SparkContext}

object testRDDMethod {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testRDDMethod ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6)) //输入数组数据集  parallelize是将内存数据读入Spark中，作为一个整体数据集
    // math.max(_, _) 比较数据集中数据的大小，_ + _ 对第一个比较方法结果进行处理
    val result = arr.aggregate(0)(math.max(_, _), _ + _) //使用aggregate方法
    println(result) //打印结果
  }
}
