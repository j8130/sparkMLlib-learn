package c07

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 一元逻辑回归
 * 只有一个影响因素
 */
object test01LogisticRegression {
  val conf = new SparkConf() //创建环境变量
    .setMaster("local") //设置本地化处理
    .setAppName("LogisticRegression") //设定名称
  val sc = new SparkContext(conf) //创建环境变量实例

  Logger.getRootLogger.setLevel(Level.ERROR)

  def main(args: Array[String]) {
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D07\\u.txt") //获取数据集路径

    // parsedData是一个LabeledPoint[RDD] 形式的数据集
    val parsedData = data.map { line => //开始对数据集处理
      val parts = line.split('|') //根据逗号进行分区
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(" ").map(_.toDouble)))
    }.cache() //转化数据格式

    // 通过随机梯队下降算法迭代形成的逻辑回归模型
    val model = LogisticRegressionWithSGD.train(parsedData, 50) //建立模型
    // 待测试数据
    val target = Vectors.dense(-1) //创建测试值

    //根据模型计算结果
    val resulet = model.predict(target)
    println(resulet) //打印结果
  }
}

