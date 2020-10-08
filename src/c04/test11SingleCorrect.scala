package c04

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 单个数据集相关系数的计算
 * 单个数据集相关系数计算首先要将数据转化成本地向量，之后再进行计算
 */
object test11SingleCorrect {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testSingleCorrect ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val rdd = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\testSingleCorrect.txt") //读取数据文件
      .map(_.split(' ') //切割数据
        .map(_.toDouble)) //转化为Double类型
      .map(line => Vectors.dense(line)) //转为向量
    println(Statistics.corr(rdd, "spearman")) //使用斯皮尔曼计算相关系数
  }
}
