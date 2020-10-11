package c08

import org.apache.spark.mllib.regression.IsotonicRegression
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 保序回归
 * 数据格式是以[value,label]的格式存储的一个数据集。
 * 例如[1,1]指的是第一个数据的值是1，而其标签是1，
 * [6,3]指的是其值是6的那个数据，标签为3
 */
object test04IS {
  def main(args: Array[String]) {

    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("IS") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    //val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D08\\is.txt") //输入数据集
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D08\\is.txt") //输入数据集

    val parsedData = data.map { line => //处理数据格式
      val parts = line.split(',').map(_.toDouble) //切分数据
      (parts(0), parts(1), 1.0) //分配数据格式
    }

    val model = new IsotonicRegression().setIsotonic(true).run(parsedData) //建立模型

    model.predictions.foreach(println) //打印保序回归模型

    println("==============")
    val res = model.predict(5) //创建预测值
    println(res) //打印预测结果

  }
}
