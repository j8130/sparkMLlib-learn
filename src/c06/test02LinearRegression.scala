package c06

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 线性回归
 * Y=a+bX
 */
object test02LinearRegression {
  val conf = new SparkConf() //创建环境变量
    .setMaster("local") //设置本地化处理
    .setAppName("LinearRegression ") //设定名称
  val sc = new SparkContext(conf) //创建环境变量实例

  Logger.getRootLogger.setLevel(Level.ERROR)

  def main(args: Array[String]) {
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D06\\lpsa.data") //获取数据集路径
    val parsedData = data.map { line => //开始对数据集处理
      val parts = line.split(',') //根据逗号进行分区
      // 这里part(0)和part(1)分别代表y和x
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }.cache() //转化数据格式

    val numlterations = 100 // 整体模型的迭代次数，理论上迭代次数越多模型的拟合程度越高，迭代需要的时间越长
    val stepSize = 0.1 // 随机梯度下降算法中的步进系数，代表每次迭代过程中模型的整体修正程度
    val model = LinearRegressionWithSGD.train(parsedData, numlterations, stepSize)
    //建立模型
    val prediction = model.predict(parsedData.map((_.features)))
    //检验测试集数据
    prediction.foreach(obj => println(obj)) //打印原测试集数据使用模型后得出的结果

    println("=============")
    // 提供新的待测数据, Vectors.dense(0, 1) 人为创建一个MLlib数据向量输入到已构成的数据模型中
    println(model.predict(Vectors.dense(0, 1)))
  }

}





