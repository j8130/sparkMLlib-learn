package c04

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 斯皮尔曼相关系数
 */
object test10Correct2 {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testCorrect2 ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val rddX = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\testCorrectX.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val rddY = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\testCorrectY.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val correlation: Double = Statistics.corr(rddX, rddY, "spearman") //使用斯皮尔曼计算不同数据之间的相关系数
    println(correlation) //打印结果
  }
}
