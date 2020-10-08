package c04

import org.apache.spark.mllib.linalg.{Matrices, Vectors}
import org.apache.spark.mllib.stat.Statistics

/**
 * 假设检验 - 卡方检验
 * 对于数据结果的好坏，需要一个能够反映和检验结果正确与否的方法。
 * 卡方检验是一种常用的假设检验方法，能够较好地对数据集之间的拟合度、相关性和独立性进行验证。
 * MLlib中规定常用的卡方检验使用的数据集一般为向量和矩阵
 *
 * 卡方校验使用皮尔逊算法对数据集进行计算，得到最终结果P值，一般情况，P<0.05 指数据集不存在显著性差异
 */
object test13ChiSq{
  def main(args: Array[String]) {
    val vd = Vectors.dense(1,2,3,4,5)                                 //
    val vdResult = Statistics.chiSqTest(vd)
    println(vdResult)
    println("-------------------------------")
    val mtx = Matrices.dense(3, 2, Array(1, 3, 5, 2, 4, 6))
    val mtxResult = Statistics.chiSqTest(mtx)
    println(mtxResult)
  }
}

/**
 * 输出
 * Chi squared test summary:
 * method: pearson
 * degrees of freedom = 4
 * statistic = 3.333333333333333
 * pValue = 0.5036682742334986
 * No presumption against null hypothesis: observed follows the same distribution as expected..
 * -------------------------------
 * Chi squared test summary:
 * method: pearson                  // 卡方检验使用的方法
 * degrees of freedom = 2           // 自由度      总体参数估计量中变量值独立自由变化的数目
 * statistic = 0.14141414141414144  // 统计量      不同方法下的统计量
 * pValue = 0.931734784568187       // p值         显著性差异的指标
 * No presumption against null hypothesis: the occurrence of the outcomes is statistically independent..
 */


