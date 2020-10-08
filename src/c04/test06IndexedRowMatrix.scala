package c04

import org.apache.spark._
import org.apache.spark.mllib.linalg.distributed.{IndexedRow, RowMatrix, IndexedRowMatrix}
import org.apache.spark.mllib.linalg.{Vector, Vectors}

/**
 * 带有行索引的行矩阵
 *
 * 单纯行矩阵 需要通过调用方法显示内部数据内容，带索引的行矩阵更方便在调试过程中观察内容
 */
object test06IndexedRowMatrix {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("testIndexedRowMatrix") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val rdd = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D04\\RowMatrix.txt") //创建RDD文件路径
      .map(_.split(' ') //按" "分割
        .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转化成向量存储
      .map((vd) => new IndexedRow(vd.size, vd)) //转化格式
    val irm = new IndexedRowMatrix(rdd) //建立索引行矩阵实例
    println(irm.getClass) //打印类型
    println(irm.rows.foreach(println)) //打印内容数据

    // IndexedRowMatrix 可以直接转换成其他矩阵
    irm.toRowMatrix() // 转成行矩阵
    irm.toCoordinateMatrix() // 转成坐标矩阵
    irm.toBlockMatrix() // 转成块矩阵
  }
}
