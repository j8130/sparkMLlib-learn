package c07

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils

/**
 * 朴素贝叶斯僵尸粉鉴定(朴素贝叶斯需要非负特征值)
 * 正常用户标记为１，虚假用户标记为０
 * V(v1,v2,v3)
 * v1 = 已发微博/注册天数
 * v2 = 好友数量/注册天数
 * v3 = 是否有手机
 * 已发微博/注册天数　< 0.05, V1 = 0
 * 0.05 <= 已发微博/注册天数　< 0.75, V1 = 1
 * 0.75 <= 已发微博/注册天数, V1 = 2
 * Created by eric on 16-7-19.
 */
object test08BayesTest {

  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("BayesTest ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D07\\data.txt") //读取数据集

    val parsedData = data.map { line => //处理数据
      val parts = line.split(',') //分割数据
      LabeledPoint(parts(0).toDouble, //标签数据转换
        Vectors.dense(parts(1).split(' ').map(_.toDouble))) //向量数据转换
    }

    val splits = parsedData.randomSplit(Array(0.7, 0.3), seed = 11L) //对数据进行分配
    val trainingData = splits(0) //设置训练数据
    val testData = splits(1) //设置测试数据
    val model = NaiveBayes.train(trainingData, lambda = 1.0) //训练贝叶斯模型
    val predictionAndLabel = testData.map(p => (model.predict(p.features), p.label)) //验证模型
    val accuracy = 1.0 * predictionAndLabel.filter( //计算准确度
      label => label._1 == label._2).count() //比较结果
    println(accuracy) //打印准确度

    val test = Vectors.dense(0, 0, 10)
    val result = model.predict(test) //预测一个特征　
    println(result) //2
  }
}

