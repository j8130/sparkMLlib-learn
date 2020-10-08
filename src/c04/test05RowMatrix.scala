package c04

import org.apache.spark._
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.linalg.distributed.RowMatrix

/**
 * 分布式矩阵
 * 采用分布式矩阵进行存储的情况都是数据量非常大的，其处理速度和效率与其存储格式息息相关
 * MLlib 提供了4种分布式矩阵存储形式。均由支持长整型的行列数和双精度浮点数内容构成，分别为
 * 行矩阵，带有行索引的行矩阵，坐标矩阵 和 块矩阵。  其中前三种较常用 后面的可以向前面的转换
 */

/**
 * 行矩阵
 * 行矩阵是以行作为基本方向的矩阵存储格式，列的作用相对较小。可以将其理解为行矩阵是一个巨大的特征向量的集合。
 * 每一行就是一个具有相同格式的向量数据，且每一行的向量内容都可以单独取出来进行操作
 */
object test05RowMatrix {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testRowMatrix") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val rdd = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\RowMatrix.txt") //创建RDD文件路径
      .map(_.split(' ') //按" "分割
        .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转成Vector格式
    val rm = new RowMatrix(rdd) //读入行矩阵  RowMatrix 是一个转换算子
    println(rm.numRows()) //打印列数
    println(rm.numCols()) //打印行数
  }
}
