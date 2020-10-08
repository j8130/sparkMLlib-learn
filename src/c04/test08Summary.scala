package c04

import org.apache.spark._
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.stat.Statistics

/**
 * 求数据的均值和标准差
 *
 * Statistics.colStats(rdd) 以列为基础计算统计量的基本数据
 */
object test08Summary {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testSummary") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val rdd = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\testSummary.txt") //创建RDD文件路径
      .map(_.split(' ') //按“ ”分割
        .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转成Vector格式
    val summary = Statistics.colStats(rdd) //获取Statistics实例
    println(summary.mean) //计算均值
    println(summary.variance) //计算标准差
    println(summary.count) // 计算行内数据个数
    println(summary.max) // 计算最大值
    println(summary.normL1) // 计算曼哈顿距离     标明两个点在标准坐标系上的绝对轴距总和，相当于 1+2+3+4+5
    println(summary.normL2) // 计算欧几里得距离   欧式距离，就是勾股定理那个
    println(summary.numNonzeros) // 计算不含0值的个数

  }
}
