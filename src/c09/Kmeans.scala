package c09

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Kmeans算法
 * 聚类算法
 * Kmeans中关于距离的计算方式使用的是欧氏距离计算方法
 */
object Kmeans {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("Kmeans ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D09\\Kmeans.txt") //输入数据集
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))
      .cache() //数据处理

    val numClusters = 2 //最大分类数
    val numIterations = 20 //最大迭代次数
    val model = KMeans.train(parsedData, numClusters, numIterations) //训练模型
    model.clusterCenters.foreach(println) //打印中心点坐标

  }
}
