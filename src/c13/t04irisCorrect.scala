package c13

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 去除重复项，相关系数的确定
 * 数据列之间进行两两相关性测试，把2个读取数据换成读取不同的Excel列
 *
 * 在对一些数据问题的分析中，其数据的产生是带有一定的相关性，例如某个地区供水量和用水量呈现出一个拟合度较好的线性关系（损耗忽略不计）。
 * 对它进行分析的时候，往往只需要分析一个变量即可。
 *
 * 本数据集也是如此，数据集中的萼片长、萼片宽、花瓣长、花瓣宽这些数据项在分析中是否有重复性需要去除，
 * 可以通过计算这些数据项相互之间的相关系数做出分析。如果相关系数超过阈值，则可以认定这些数据项具有一定的相关性，从而可以在数据分析中作为额外项去除。
 */
object t04irisCorrect {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisCorrect ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val dataX = sc.textFile("c://x.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val dataY = sc.textFile("c://y.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val correlation: Double = Statistics.corr(dataX, dataY) //计算不同数据之间的相关系数
    println(correlation) //打印结果
  }
}
