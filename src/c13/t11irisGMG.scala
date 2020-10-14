package c13

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.GaussianMixture
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 使用高斯聚类器对数据进行聚类
 *
 * 高斯分类器一样将数据集分成了3个部分，同时还
 * 打印出每个分类后的数据集所占得百分比。如第一个集所占的比重为
 * 36％，第二个为63％
 *
 */
object t11irisGMG {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisGMG") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\all.txt") //输入数个
    val parsedData = data.map(
      s => Vectors.dense(s.trim.split("\\s+") //转化数据格式
      .map(_.toDouble))).cache()
    val model = new GaussianMixture().setK(2).run(parsedData) //训练模型

    for (i <- 0 until model.k) {
      println("weight=%f\nmu=%s\nsigma=\n%s\n" format //逐个打印单个模型
        (model.weights(i), model.gaussians(i).mu, model.gaussians(i).sigma)) //打印结果
    }
  }
}
