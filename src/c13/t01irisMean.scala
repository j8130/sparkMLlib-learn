package c13

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 微观分析——均值与方差的对比分析
 *
 *  只算第一种❀
 * 对鸢尾花数据集中第一个数据萼片长（sepal length）做出分析，这里由于所有的数据都在一个统计表中，可以将其取出做成独立
 * 的数据集，这点可以由读者自行完成。
 */
object t01irisMean {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisMean ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)


    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\Sepal.Length_setosa.txt") //创建RDD文件路径
      .map(_.split(' ')
        .map(_.toDouble)
      ) //转成Double类型
      .map(line => Vectors.dense(line)) //转成Vector格式

    val summary = Statistics.colStats(data) //计算统计量
    println("setosa中Sepal.Length的均值为：" + summary.mean) //打印均值
    println("setosa中Sepal.Length的方差为：" + summary.variance) //打印方差
  }
}
