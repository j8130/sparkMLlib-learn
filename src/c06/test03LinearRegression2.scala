package c06

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 线性回归实战：商品价格与消费者收入之间的关系
 * 没跑通
 * 数据格式   50|8,30     | 分割了y与x，不同的x通过,分割      函数 y=a1x+a2x
 */
object test03LinearRegression2 {
  val conf = new SparkConf() //创建环境变量
    .setMaster("local") //设置本地化处理
    .setAppName("LinearRegression2") //设定名称
  val sc = new SparkContext(conf) //创建环境变量实例

  Logger.getRootLogger.setLevel(Level.ERROR)

  def main(args: Array[String]) {
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D06\\lr.txt") //获取数据集路径
    val parsedData = data.map { line => //开始对数据集处理
      val parts = line.split('|') //根据逗号进行分区
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(',').map(_.toDouble)))
    }.cache() //转化数据格式
    //建立模型
    val model = LinearRegressionWithSGD.train(parsedData, 200, 0.1)
    val prediction = model.predict(parsedData.map((_.features)))
    //检验测试集数据
    prediction.foreach(obj => println(obj)) //打印原测试集数据使用模型后得出的结果
    println(model.predict(Vectors.dense(0, 1))) //提供新的待测数据
  }
}


