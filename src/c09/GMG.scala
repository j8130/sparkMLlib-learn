package c09

import org.apache.spark.mllib.clustering.GaussianMixture
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 高斯混合模型
 */
object GMG {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("GMG ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D09\\gmg.txt") //输入数据
    val parsedData = data.map(s => Vectors.dense(s.trim.split(' ') //转化数据格式
      .map(_.toDouble))).cache()
    // setK(2) 设置了模型训练的分类数，可以在打印结果里看到模型被分为了2个聚类结果
    val model = new GaussianMixture().setK(2).run(parsedData) //训练模型

    for (i <- 0 until model.k) {
      println("weight=%f\nmu=%s\nsigma=\n%s\n" format //逐个打印单个模型
        (model.weights(i), model.gaussians(i).mu, model.gaussians(i).sigma)) //打印结果
    }
  }
}
