package c04

import org.apache.spark.mllib.linalg.{Vector, Vectors}

/**
 * 本地向量集 local vector
 * MLlib使用的本地化存储类型是向量。向量主要由两类构成
 *
 * 稀疏型数据集 sparse  MLlib专用的集合格式，与array类似
 *    向量数据 (9,5,2,7) 按密集型数据格式可以被设定成 (9,5,2,7) 进行存储，数据集被作为一个集合的形式整体存储
 *
 * 密集型数据集 dense
 *    向量数据 (9,5,2,7) 可以按向量的大小存储为 (4, Array(0, 1, 2, 3), Array(9, 5, 2, 7))
 *
 *    dense将给定数据Array(9,5,2,7) 分解成4个部分进行处理，其对应值分别属于程序中vs的向量对应值
 *    第一个参数4，代表输入数据大小，一般要求大于等于输入的数据值；第三个参数 Array(9,5,2,7) 是输入的数据值
 *    第二个参数 Array(0, 1, 2, 3) 是数据vs下标的数值。这里严格要求按序增加的方法增加数据
 *
 */
object test01Vector {
  def main(args: Array[String]) {
    val vd: Vector = Vectors.dense(2, 0, 6) //建立密集向量
    println(vd(2)) //打印稀疏向量第3个值
    val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(9, 5, 2, 7)) //建立稀疏向量
    println(vs(2)) //打印稀疏向量第3个值
  }
}
