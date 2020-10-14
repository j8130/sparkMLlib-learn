package c13

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 使用Kmeans算法进行聚类分析
 *
 * 聚类分析是无监督学习的一种，它通过机器处理，自行研究算法去
 * 发现数据集的潜在关系，并将关系最相近的部分结合在一起，从而实现
 * 对数据的聚类处理。聚类分析的最大特点就是没有必然性，可能每次聚
 * 类处理的结果都不尽相同。
 */
object t10irisKmeans {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisKmeans ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\all.txt") //输入数据集
    val parsedData = data.map(s => Vectors.dense(s.split("\\s+").map(_.toDouble)))
      .cache() //数据处理

    val numClusters = 3 //最大分类数
    val numIterations = 20 //迭代次数
    val model = KMeans.train(parsedData, numClusters, numIterations) //训练模型
    model.clusterCenters.foreach(println) //打印中心点坐标

  }
}
