package c09

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.PowerIterationClustering

/**
 * 快速迭代聚类
 * 数据源要求 RDD[(Long),(Long),(Double)]
 * 其中第一个和第二参数是第一个点和第二个数据点的编号，即其之间ID。第三个参数为相似度计算值。
 */
object PIC {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("PIC") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D09\\pic.txt") //读取数据
    val parsedData = data.map { line => //开始对数据集处理
      val parts = line.split(' ') //根据逗号进行分区
      (parts(0).toLong, parts(1).toLong, parts(2).toDouble)
    }
      .cache() //转化数据格式

    val pic = new PowerIterationClustering() //创建专用类
      .setK(3) //设定聚类数
      .setMaxIterations(20) //设置迭代次数

    val model = pic.run(parsedData) //创建模型

  }

}
