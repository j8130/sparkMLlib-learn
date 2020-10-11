package c07

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

object test07Bayes {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("Bayes ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = MLUtils.loadLabeledPoints(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D07\\bayes.txt") //读取数据集
    val model = NaiveBayes.train(data, 1.0) //训练贝叶斯模型
    model.labels.foreach(println) //打印label值  labels是标签类别
    model.pi.foreach(println) //打印先验概率  pi存储各个label的先验概率

    println("=============")
    // theta 是各个类别中的条件概率
    model.theta.foreach(f=>{
      println("----")
      f.foreach(println)
    })
  }
}
