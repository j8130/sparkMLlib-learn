package c05

import org.apache.spark._
import org.apache.spark.mllib.recommendation.{ALS, Rating}

/**
 * 基于ALS算法的协同过滤推荐
 * MLlib中ALS算法有固定的数据格式    Rating:元组类型的[Int,Int,Double]
 */
object CollaborativeFilter {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("CollaborativeFilter ") //设置环境变量
    val sc = new SparkContext(conf) //实例化环境
    val data = sc.textFile("E:\\GitHub\\sparkMLlib-learn\\resources\\DATA\\D05\\u1.txt") //设置数据集
    val ratings = data.map(_.split(' ') match { //处理数据
      case Array(user, item, rate) => //将数据集转化
        Rating(user.toInt, item.toInt, rate.toDouble) //将数据集转化为专用Rating
    })
    val rank = 2 //设置隐藏因子
    val numIterations = 2 //设置迭代次数
    val model = ALS.train(ratings, rank, numIterations, 0.01) //进行模型训练

    // 根据已有数据集建立协同过滤模型后用recommendProducts为第二个用户推荐一个商品
    var rs = model.recommendProducts(2, 1) //为用户2推荐一个商品
    rs.foreach(println) //打印结果

    /**
     * 为第二个用户推荐了编号为15的商品，同时将预测评分3.94进行输出，与实际值4相差较小
     */
    //Rating(2,15,3.941361373749174)
  }
}
