package c13

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 线性回归分析萼片长与宽之间的关系
 */
object t07irisLinearRegression {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisLinearRegression ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\spral.txt") //读取数据
    val parsedData = data.map { line => //处理数据
      val parts = line.split('	') //按空格分割
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).toDouble)) //固定格式
    }.cache() //加载数据
    val model = LinearRegressionWithSGD.train(parsedData, 10, 0.1) //创建模型
    println("回归公式为: y = " + model.weights + " * x + " + model.intercept) //打印回归公式
  }

}


