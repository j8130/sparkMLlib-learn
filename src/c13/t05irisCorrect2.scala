package c13

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 对不同类别植物的相同特性进行分析
 *
 * 可以看到，不同种类的同种特性之间，只有很低的相关性（小于
 * 0.1），因此可以认定不同种类的同种特性不具有相关性。
 */
object t05irisCorrect2 {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisCorrect2") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val dataX = sc.textFile("c://x.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val dataY = sc.textFile("c://y.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val correlation: Double = Statistics.corr(dataX, dataY) //计算不同数据之间的相关系数
    println("setosa和versicolor中Sepal.Length的相关系数为：" + correlation) //打印相关系数                                      
  }
}
