package c04

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * 向量标签
 * 向量标签用于对机器学习算法的不同值做标记，例如分类问题中，可以将不同的数据集分成若干份，以整型数 0,1,2。。。进行标记
 * 即程序的编写者可以根据自己的需要对数据进行标记
 */
object test02LabeledPoint {
  def main(args: Array[String]) {
    val vd: Vector = Vectors.dense(2, 0, 6) //建立密集向量
    val pos = LabeledPoint(1, vd) //对密集向量建立标记点
    println(pos.features) //打印标记点内容数据
    println(pos.label) //打印既定标记

    val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(9, 5, 2, 7)) //建立稀疏向量
    val neg = LabeledPoint(2, vs) //对密集向量建立标记点
    println(neg.features) //打印标记点内容数据
    println(neg.label) //打印既定标记
  }
}
