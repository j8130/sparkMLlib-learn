package c12

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.feature.ChiSqSelector
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 卡方检验
 * 卡方检验主要是对已有向量进行数据归类处理
 */
object FeatureSelection {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("FeatureSelection ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = MLUtils.loadLibSVMFile(sc, "E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D12\\FeatureSelection.txt") //读取数据文件

    val discretizedData = data.map { lp => //创建数据处理空间
      LabeledPoint(lp.label, Vectors.dense(lp.features.toArray.map { x => x / 2 }))
    }

    val selector = new ChiSqSelector(2) //创建选择2个特性的卡方检验实例
    val transformer = selector.fit(discretizedData) //创建训练模型
    val filteredData = discretizedData.map { lp => //过滤前2个特性
      LabeledPoint(lp.label, transformer.transform(lp.features))
    }

    filteredData.foreach(println) //打印结果
  }
}


