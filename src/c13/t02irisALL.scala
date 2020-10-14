package c13

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 所有数据一起的 Length的均值和方差
 */
object t02irisALL {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisAll ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\Sepal.Length.txt") //创建RDD文件路径
      .map(_.split(' ')
      .map(_.toDouble)
    ) //转成Double类型
    .map(line => Vectors.dense(line)) //转成Vector格式
    val summary = Statistics.colStats(data) //计算统计量
    println("全部Sepal.Length的均值为：" + summary.mean) //打印均值
    println("全部Sepal.Length的方差为：" + summary.variance) //打印方差
  }
}
