package c03

import org.apache.spark.{SparkConf, SparkContext}

/**
 * cache 将数据内容计算并保存在计算节点的内存中，这个方法使用的是针对Spark的 Lazy 数据处理模式
 * 在 Lazy 模式中，数据在编译和未使用时是不进行计算的，仅仅保存其存储地址，只有在Action方法到来是才进行计算
 * 好处：减少存储空间，提高利用率
 */
object CacheTest {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("CacheTest") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val arr = sc.parallelize(Array("abc", "b", "c", "d", "e", "f")) //设定数据集
    println(arr) //打印结果
    println("----------------") //分隔符
    println(arr.cache()) //打印结果
  }
}

