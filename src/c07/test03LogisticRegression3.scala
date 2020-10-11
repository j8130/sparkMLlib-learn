package c07

import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.evaluation.MulticlassMetrics

/**
 * 逻辑回归的验证
 *
 * MLlib中的 MulticlassMetrics 类是对数据进行分类的类。可以通过调用其中的precision方法对验证数据进行验证。
 */
object test03LogisticRegression3 {
  val conf = new SparkConf() //创建环境变量
    .setMaster("local") //设置本地化处理
    .setAppName("LogisticRegression3") //设定名称
  val sc = new SparkContext(conf) //创建环境变量实例

  def main(args: Array[String]) {
    val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D07\\sample_libsvm_data.txt") //读取数据集

    //对数据集切分 为60%的parsedData和40%的parseTest，分别作为训练数据集，和测试数据集。
    // 通过使用训练后的模型对测试集进行实际测试
    val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
    val parsedData = splits(0) //分割训练数据
    val parseTest = splits(1) //分割测试数据
    val model = LogisticRegressionWithSGD.train(parsedData, 50) //训练模型
    println(model.weights) //打印θ值
    val predictionAndLabels = parseTest.map { //计算测试值
      case LabeledPoint(label, features) => //计算测试值
        val prediction = model.predict(features) //计算测试值
        (prediction, label) //存储测试和预测值
    }
    val metrics = new MulticlassMetrics(predictionAndLabels) //创建验证类
    val precision = metrics.precision //计算验证值
    println("Precision = " + precision) //打印验证值
  }
}
