package c03

import org.apache.spark.{SparkConf, SparkContext}

object testRDDMethod2 {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testRDDMethod2 ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6)
      , 2
    ) //输入数组数据集   第二个参数表示将数据值分布在多少个数据节点中存放   Array(1, 2, 3, 4, 5, 6) -> Array(1, 2, 3) + Array(4, 5, 6)

    // 有两个数据集传入math.max 方法，分别找出两个数据集的最大值，分别是 3 ， 6  aggregate进行相加
    val result = arr.aggregate(0)(math.max(_, _), _ + _) //使用aggregate方法
    println(result) //打印结果   9
  }
}
