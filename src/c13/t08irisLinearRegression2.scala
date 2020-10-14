package c13

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 均方误差（MSE）验证
 *
 * 对上面的回归方程进行验证，通过返回计算相关变量，判断其拟合程度
 */
object t08irisLinearRegression2 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisLinearRegression2") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例

    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\spral.txt") //读取数据
    val parsedData = data.map { line => //处理数据
      val parts = line.split("\\s+") //按空格分割
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).toDouble)) //固定格式
    }.cache() //加载数据

    val model = LinearRegressionWithSGD.train(parsedData, 10, 0.1) //创建模型

    //创建均方误差训练数据
    val valuesAndPreds = parsedData.map { point => {
      val prediction = model.predict(point.features) //创建数据

      //创建预测数据
      (point.label, prediction)
    }
    }
    val MSE = valuesAndPreds.map { case (v, p) => math.pow((v - p), 2) }.mean() //计算均方误差

    println("均方误差结果为:" + MSE) //打印结果
  }

}
