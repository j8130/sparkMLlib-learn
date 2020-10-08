package c04

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 分层抽样
 * 分层抽样是一种数据提取算法，先将总体的单位按某种特征分为若干次级总体（层），然后按一定的比例，
 * 在从每一层内进行进行单纯随机抽样，组成一个样本的统计学计算方法，这种方法常用于数据量较大的情况
 *
 * 在 MLlib 中使用Map作为分层抽样的数据标记。一般情况下，Map的构成是[key, value]格式，key作为数据组，value作为数据标签进行处理
 *
 * 本例：将字符串长度为2划分为层1和层2，对层1和层2按不同的概率进行抽样
 */
object test12StratifiedSampling2 {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testSingleCorrect2 ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\testStratifiedSampling.txt") //读取数据
      .map(row => { //开始处理
        if (row.length == 3) //判断字符数
          (row, 1) //建立对应map
        else (row, 2) //建立对应map
      })
      .map(each => (each._2, each._1))

    data.foreach(println)
    println("sampleByKey:")
    val fractions: Map[Int, Double] = (List((1, 0.2), (2, 0.8))).toMap //设定抽样格式
    val approxSample = data.sampleByKey(withReplacement = false, fractions, 0) //计算抽样样本
    approxSample.foreach(println)

    println("Second:")
    //http://homepage.cs.latrobe.edu.au/zhe/ZhenHeSparkRDDAPIExamples.html#sampleByKey
    val randRDD = sc.parallelize(List((7, "cat"), (6, "mouse"), (7, "cup"), (6, "book"), (7, "tv"), (6, "screen"), (7, "heater")))
    val sampleMap = List((7, 0.4), (6, 0.8)).toMap
    val sample2 = randRDD.sampleByKey(false, sampleMap, 42).collect
    sample2.foreach(println)

    println("Third:")
    //http://bbs.csdn.net/topics/390953396
    val a = sc.parallelize(1 to 20, 3)
    val b = a.sample(true, 0.8, 0)
    val c = a.sample(false, 0.8, 0)
    println("RDD a : " + a.collect().mkString(" , "))
    println("RDD b : " + b.collect().mkString(" , "))
    println("RDD c : " + c.collect().mkString(" , "))
    sc.stop

    //val fractions: Map[String, Double] = Map("aa" -> 2) //设定抽样格式
    //val approxSample = data.sampleByKey(withReplacement = false, fractions, 0) //计算抽样样本
    //approxSample.foreach(println) //打印结果
  }
}
