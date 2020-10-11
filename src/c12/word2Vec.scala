package c12

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.feature.Word2Vec
import org.apache.spark.{SparkConf, SparkContext}

object word2Vec {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("word2Vec ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D12\\word.txt").map(_.split(" ").toSeq) //读取数据文件

    val word2vec = new Word2Vec() //创建词向量实例
    val model = word2vec.fit(data) //训练模型
    println(model.getVectors) //打印向量模型

    // 两个参数分别为查找目标和查找数量
    val synonyms = model.findSynonyms("spar", 1) //寻找spar的相似词
    for (synonym <- synonyms) { //打印找到的内容
      println(synonym)
    }
  }
}


