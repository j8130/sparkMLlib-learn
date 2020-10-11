package c10

import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.{SparkConf, SparkContext}

/**
 * FP树示例
 * 读入数据没处理，是我处理的，不知道对不对
 */
object FPTree {

  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("FPTree ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D10\\fp.txt") //读取数据

    val parsedData = data.map { line => //开始对数据集处理
      val parts = line.split('、') //根据逗号进行分区
      parts
    }

    val fpg = new FPGrowth().setMinSupport(0.3) //创建FP数实例并设置最小支持度

    val model = fpg.run(parsedData) //创建模型

  }
}
