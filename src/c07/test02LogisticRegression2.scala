package c07

import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 多元逻辑回归
 *
 * 实际影响胃癌的因素很多，并不能单纯地使用一元逻辑回归
 *
 * libSVM数据格式
 * Label 1:value 2: value ...
 * Label是类别的标识，比如0或者1，可根据需要自己随意定，这里由于是做的回归分析，那么其定义为0或者1
 * Value是要训练的数据，从分类的角度来说就是特征值，数据之间需要使用空格隔开，而每个：用于标注向量的序号和向量值，如
 *
 * 1 1:12 3:7 4:1
 * 指的是表示为1的那组数据，第1个数据值为12，第3个数据值为7，第4个数据值为1，第2个数据缺失。特征冒号前面的序号可以不连续。
 * 可以减少内存的使用，并提高计算矩阵内积时的运算速度
 */
object test02LogisticRegression2 {
  val conf = new SparkConf() //创建环境变量
    .setMaster("local") //设置本地化处理
    .setAppName("LogisticRegression2 ") //设定名称
  val sc = new SparkContext(conf) //创建环境变量实例

  def main(args: Array[String]) {
    val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D07\\sample_libsvm_data.txt") //读取数据文件
    val model = LogisticRegressionWithSGD.train(data, 50) //训练数据模型
    println(model.weights.size) //打印θ值
    println(model.weights) //打印θ值个数
    println(model.weights.toArray.filter(_ != 0).size) //打印θ中不为0的数
  }
}
