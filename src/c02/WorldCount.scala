package C02

import org.apache.spark.{SparkContext, SparkConf}
/**
 * @Author: jsy
 * @Date: 2020/10/7 15:33
 */
object wordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("wordCount")	//创建环境变量
    val sc = new SparkContext(conf)								//创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D02\\wc.txt")								//读取文件
    // flatMap参数返回一个collect，_ 代表传入的数据
    // map((_, 1)) 对每字符开始计数，不涉及合并和计算
    // reduceByKey(_+_) 传入数据按照key值相加
    // collect() spark的行动算子，是对程序的启动
    data.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)	//word计数
  }
}

