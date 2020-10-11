package c08

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils

/**
 * 决策树
 * 1 1:1 2:0 3:0 4:1
 * 0 1:1 2:0 3:1 4:1
 * 0 1:0 2:1 3:0 4:0
 * 1 1:1 2:1 3:0 4:0
 * 1 1:1 2:0 3:0 4:0
 * 1 1:1 2:1 3:0 4:0
 *
 * 第一列数据表示是否出去玩，后面若干个 键值对 分别表示其对应的值，k表示属性的序号，v是序号对应具体的值
 */
object test01DT {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("DT") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D08\\DTree.txt") //输入数据集

    val numClasses = 2 //设定分类数量
    val categoricalFeaturesInfo = Map[Int, Int]() //设定输入格式
    val impurity = "entropy" //设定信息增益计算方式
    val maxDepth = 5 //设定树高度
    val maxBins = 3 //设定分裂数据集

    //  maxDepth : scala.Int, maxBins : scala.Int) : org.apache.spark.mllib.tree.model.DecisionTreeModel = { /* compiled code */ }


    /**
     * 参数说明
     * RDD[LabeledPoint]    输入的数据集
     * numClasses : Int     分类的数量，本例中只有出去和不出去，所以分2类
     * categoricalFeaturesInfo : Map[Int, Int]    属性对的格式，这里是单纯的键值对
     * impurity : String    计算信息增益的形式
     * maxDepth : Int       树的高度
     * maxBins : Int        能够分裂的数据集和数量
     */
    val model = DecisionTree.trainClassifier(data, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins) //建立模型
    println(model.topNode) //打印决策树信息

  }
}
