package c06

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

//object test02LinearRegression {
//  val conf = new SparkConf() //创建环境变量
//    .setMaster("local") //设置本地化处理
//    .setAppName("LinearRegression ") //设定名称
//  val sc = new SparkContext(conf) //创建环境变量实例
//
//  def main(args: Array[String]) {
//    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D06\\lpsa.data") //获取数据集路径
//    val parsedData = data.map { line => //开始对数据集处理
//      val parts = line.split(',') //根据逗号进行分区
//      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
//    }.cache() //转化数据格式
//
//    val model = LinearRegressionWithSGD.train(parsedData, 100, 0.1)
//    //建立模型
//    val prediction = model.predict(parsedData.map((_.features)))
//    //检验测试集数据
//    prediction.foreach(obj => println(obj)) //打印原测试集数据使用模型后得出的结果
//    println(model.predict(Vectors.dense(0, 1))) //提供新的待测数据
//  }
//
//}





