package c04

import org.apache.spark.mllib.linalg.{Matrix, Matrices}

/**
 * 本地矩阵
 */
object test04Matrix {
  def main(args: Array[String]) {
    val mx = Matrices.dense(2, 3, Array(1, 2, 3, 4, 5, 6)) //创建一个分布式矩阵   2行 3列
    println(mx) //打印结果
  }
}
