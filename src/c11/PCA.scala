package c11

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkConf, SparkContext}

/**
 * PCA
 */
object PCA {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("PCA ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例

    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D11\\mx.txt") //创建RDD文件路径
      .map(_.split(' ') //按“ ”分割
        .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转成Vector格式
    val rm = new RowMatrix(data) //读入行矩阵

    val pc = rm.computePrincipalComponents(3) //提取主成分，设置主成分个数,即对每个向量最终提取3个主成分作为向量的表示。
    // ultiply方法是对主成分的组合，重新构建成一个使用主成分构建的矩阵，用于表示原始矩阵
    val mx = rm.multiply(pc) //创建主成分矩阵
    mx.rows.foreach(println) //打印结果
  }
}
