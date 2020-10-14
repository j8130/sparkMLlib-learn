package c13

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * 使用贝叶斯分类分析对数据集进行分类处理
 *
 * 除了贝叶斯分类器，svm也是一种分类器
 *
 * 聚类回归有助于发现新的未经证实和发现的东西，而对于已经有所
 * 归类的数据集，其处理可能不会按固定的模式去做。因此对其进行分析
 * 就需要使用另外一种数据的分类方法，即数据的分类
 */
object t12irisBayes {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisBayes") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\irisBayes.txt") //读取数据集

    val parsedData = data.map { line => //处理数据
      val parts = line.split(",") //分割数据
      LabeledPoint(parts(0).toDouble, //标签数据转换
        Vectors.dense(parts(1).split(" ").map(_.toDouble))) //向量数据转换
    }

    val model = NaiveBayes.train(parsedData, lambda = 1.0) //训练贝叶斯模型

    val test = Vectors.dense(7.3, 2.9, 6.3, 1.8) //创建待测定数据
    val result = model.predict(test) //打印结果

    println("测试数据归属类别：" + result)
  }
}
