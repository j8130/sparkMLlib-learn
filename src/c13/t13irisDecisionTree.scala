package c13

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils

/**
 * 决定数据集的归类——决策树
 *
 * 决策树可以对输入的数据进行判定，并且打印其所
 * 属的归类，这点相比较其他方法来说是一个重大进步。它使得决策程序
 * 在完全没有人工干扰的情况下自主地对数据进行分类，这点极大地方便
 * 了大数据的决策与分类的自动化处理。
 */
object t13irisDecisionTree {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisDecisionTree ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例

    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D13\\irisDTree.txt") //输入数据集
    val numClasses = 3 //设定分类数量
    val categoricalFeaturesInfo = Map[Int, Int]() //设定输入格式
    val impurity = "entropy" //设定信息增益计算方式
    val maxDepth = 5 //设定树高度
    val maxBins = 3 //设定分裂数据集


    val model = DecisionTree.trainClassifier(data, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins) //建立模型
    val test = Vectors.dense(Array(7.2, 3.6, 6.1, 2.5))
    println("预测结果是:" + model.predict(test))
  }
}
