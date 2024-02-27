package com.guoshou.spark.loaddata.NB

import com.guoshou.spark.config.NBTableName
import com.guoshou.spark.po.NB._
import com.guoshou.spark.po._
import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}

import java.sql.Date
import java.text.SimpleDateFormat

object Dat2Mysql {

  def main(args: Array[String]): Unit = {
    /**
     * 配置spark环境
     */
    val spark = SparkSession.builder()
      .appName("LoadDataToMySQL")
      .master("local[*]")
      .getOrCreate()

    /**
     * 指定 MySQL 的连接参数
     */
    val url = "jdbc:mysql://localhost:3306/tj?characterEncoding=utf8"
    val connectionProperties = new java.util.Properties()
    connectionProperties.setProperty("user", "root")
    connectionProperties.setProperty("password", "123456")
    import spark.implicits._
    //TODO 时间处理
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm")


    //    //TODO 读取文件 STATISTICS_B1
    val statisticsB1DF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\STATISTICS_B1.0.dat")
    //TODO 数据处理 生成  case class类   STATISTICS_B1
    val statisticsB1Map: Dataset[StatisticsB1] = statisticsB1DF.map(row => {
      StatisticsB1(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6).toDouble,
        row.getString(7).toDouble,
        row.getString(8).toDouble,
        row.getString(9).toDouble,
        row.getString(10).toDouble,
        row.getString(11).toDouble,
        row.getString(12).toDouble,
        row.getString(13).toDouble,
        row.getString(14).toDouble,
        row.getString(15).toDouble,
        row.getString(16).toDouble,
        row.getString(17).toDouble,
        row.getString(18).toDouble,
        row.getString(19).toDouble,
        row.getString(20).toDouble,
        row.getString(21).toDouble,
        row.getString(22).toDouble,
        row.getString(23).toDouble,
        row.getString(24),
        row.getString(25)
      )
    })
    //TODO   STATISTICS_B1 写入到 MySQL 数据库
    statisticsB1Map.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_statistics_b1.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件 biz_proc4_biz_parameter
    val bizParameterDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\BIZ_PROC4\\TABLE\\BIZ_PARAMETER.0.dat")
    //TODO 数据处理 生成  case class类  BizParameter
    val bizParameterMap = bizParameterDF.map(row => {
      BizParameter(
        row.getString(0),
        row.getString(1),
        row.getString(2)
      )
    })
    //TODO   BizParameter 写入到 MySQL 数据库
    bizParameterMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.biz_proc4_biz_parameter.toString, connectionProperties)


    //-------------------------------------------------------ok 需处理
    //TODO 读取文件 cl_biz4_endow_acc
    val endowAccDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\ENDOW_ACC.0.dat")
    //TODO 数据处理 生成  case class类   EndowAcc
    val endowAccMap = endowAccDF.map(row => {
      EndowAcc(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2),
        row.getString(3).toDouble,
        row.getString(4),
        new Date(format.parse(row.getString(5)).getTime),
        new Date(format.parse(row.getString(6)).getTime),
        row.getString(7),
        row.getString(8),
        row.getString(9),
        row.getString(10),
        row.getString(11).toDouble,
        new Date(format.parse(row.getString(12)).getTime),
        row.getString(13).toDouble,
        row.getString(14).toDouble,
        row.getString(15).toDouble,
        row.getString(16).toDouble,
        new Date(format.parse(row.getString(17)).getTime),
        row.getString(18).toDouble,
        row.getString(19),
        row.getString(20),
        row.getString(21),
        row.getString(22),
        row.getString(23),
        row.getString(24),
        row.getString(25),
        row.getString(26)
      )
    })
    //TODO   EndowAcc 写入到 MySQL 数据库
    endowAccMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_endow_acc.toString, connectionProperties)

    //-------------------------------------------------------ok 需处理
    //TODO 读取文件 cl_biz4_mio_log
    val mioLogDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\MIO_LOG.0.dat")
    //TODO 数据处理 生成  case class类   MioLog
    val minLogMap = mioLogDF.map(row => {
      val str = row.getString(25)
      var doubles: Double = 0
      if ("" != str || str != null) {
        doubles = str.toDouble
      }
      MioLog(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6),
        row.getString(7).toDouble,
        row.getString(8),
        row.getString(9).toDouble,
        row.getString(10),
        row.getString(11),
        new Date(format.parse(row.getString(12)).getTime),
        new Date(format.parse(row.getString(13)).getTime),
        new Date(format.parse(row.getString(14)).getTime),
        new Date(format.parse(row.getString(15)).getTime),
        row.getString(16),
        row.getString(17),
        row.getString(18),
        row.getString(19),
        row.getString(20),
        row.getString(21),
        row.getString(22),
        row.getString(23),
        row.getString(24).toDouble,
        doubles,
        row.getString(26),
        row.getString(27),
        row.getString(28).toDouble,
        row.getString(29).toDouble,
        new Date(format.parse(row.getString(30)).getTime),
        row.getString(31).toDouble,
        row.getString(32),
        row.getString(33),
        row.getString(34),
        new Date(format.parse(row.getString(35)).getTime),
        row.getString(36),
        row.getString(37).toDouble,
        row.getString(38).toDouble,
        row.getString(39).toDouble,
        row.getString(40),
        row.getString(41),
        row.getString(42),
        row.getString(43),
        row.getString(44),
        row.getString(45),
        row.getString(46)
      )
    })
    //TODO   MioLog 写入到 MySQL 数据库
    minLogMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_mio_log.toString, connectionProperties)

    //-------------------------------------------------------ok 需处理
    //TODO 读取文件 cl_biz4_mtn_alt_cntr
    val mtnAltCntrDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\MTN_ALT_CNTR.0.dat")
    //TODO 数据处理 生成  case class类   MtnAltCntr
    val mtnAltCntrMap = mtnAltCntrDF.map(row => {
      val str = row.getString(5)
      var dates: Date = new Date(System.currentTimeMillis())
      if ("" != str || str != null) {
        dates = new Date(format.parse(row.getString(5)).getTime)
      }


      MtnAltCntr(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2).toDouble,
        row.getString(3),
        row.getString(4),
        dates
      )
    })
    //TODO   MtnAltCntr 写入到 MySQL 数据库
    mtnAltCntrMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_mtn_alt_cntr.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件 cl_biz4_mtn_osmb_shift
    val mtnOsmbShiftDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\MTN_OSMB_SHIFT.0.dat")
    //TODO 数据处理 生成  case class类 MtnOsmbShift
    val mtnOsmbShiftMap = mtnOsmbShiftDF.map(row => {
      MtnOsmbShift(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6),
        row.getString(7),
        row.getString(8),
        row.getString(9),
        row.getString(10),
        row.getString(11),
        row.getString(12),
        row.getString(13),
        row.getString(14),
        row.getString(15),
        row.getString(16),
        row.getString(17),
        row.getString(18),
        row.getString(19),
        row.getString(20).toDouble,
        row.getString(21).toDouble,
        row.getString(22),
        row.getString(23),
        row.getString(24),
        row.getString(25),
        row.getString(26),
        row.getString(27),
        row.getString(28),
        row.getString(29).toDouble,
        row.getString(30),
        row.getString(31),
        row.getString(32),
        row.getString(33),
        row.getString(34),
        row.getString(35)
      )
    })
    //TODO   MtnOsmbShift 写入到 MySQL 数据库
    mtnOsmbShiftMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_mtn_osmb_shift.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_biz4_plnmio_rec   PLNMIO_REC.0.dat
    val plnmioRecDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\PLNMIO_REC.0.dat")
    //TODO 数据处理 生成  case class类 PlnmioRec
    val plnmioRecMap = plnmioRecDF.map(row => {
      PlnmioRec(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5).toDouble,
        row.getString(6),
        row.getString(7),
        row.getString(8).toDouble,
        row.getString(9),
        row.getString(10).toDouble,
        row.getString(11),
        row.getString(12),
        row.getString(13).toDouble,
        new Date(format.parse(row.getString(14)).getTime),
        new Date(format.parse(row.getString(15)).getTime),
        row.getString(16),
        row.getString(17),
        row.getString(18),
        row.getString(19),
        row.getString(20),
        row.getString(21),
        row.getString(22).toDouble,
        row.getString(23),
        row.getString(24),
        row.getString(25),
        row.getString(26),
        row.getString(27),
        new Date(format.parse(row.getString(28)).getTime),
        row.getString(29),
        row.getString(30),
        row.getString(31).toDouble,
        row.getString(32),
        new Date(format.parse(row.getString(33)).getTime),
        row.getString(34),
        row.getString(35),
        row.getString(36),
        row.getString(37)
      )
    })
    //TODO   PlnmioRec 写入到 MySQL 数据库
    plnmioRecMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_plnmio_rec.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件 cl_biz4_statistics_b2 STATISTICS_B2
    val statisticsB2DF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\STATISTICS_B2.0.dat")
    //TODO 数据处理 生成  case class类   STATISTICS_B2
    val statisticsB2Map: Dataset[StatisticsB2] = statisticsB2DF.map(row => {
      StatisticsB2(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6).toDouble,
        row.getString(7).toDouble,
        row.getString(8).toDouble,
        row.getString(9).toDouble,
        row.getString(10).toDouble,
        row.getString(11).toDouble,
        row.getString(12).toDouble,
        row.getString(13).toDouble,
        row.getString(14).toDouble,
        row.getString(15).toDouble,
        row.getString(16).toDouble,
        row.getString(17).toDouble,
        row.getString(18),
        row.getString(19)
      )
    })
    //TODO   STATISTICS_B2写入到 MySQL 数据库
    statisticsB2Map.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_statistics_b2.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件 cl_biz4_statistics_b3 STATISTICS_B3
    val statisticsB3DF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\STATISTICS_B3.0.dat")
    //TODO 数据处理 生成  case class类   StatisticsB3
    val statisticsB3Map = statisticsB3DF.map(row => {
      StatisticsB3(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6).toDouble,
        row.getString(7).toDouble,
        row.getString(8).toDouble,
        row.getString(9).toDouble,
        row.getString(10).toDouble,
        row.getString(11),
        row.getString(12)
      )
    })
    //TODO   StatisticsB3 写入到 MySQL 数据库
    statisticsB3Map.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_statistics_b3.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_biz4_svr_group  SVR_GROUP
    val svrGroupDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\SVR_GROUP.0.dat")
    //TODO 数据处理 生成  case class类   SvrGroup
    val svrGroupMap = svrGroupDF.map(row => {
      SvrGroup(row.getString(0).toDouble,
        row.getString(1),
        row.getString(2).toDouble,
        row.getString(3),
        row.getString(4),
        row.getString(5),
        row.getString(6),
        row.getString(7).toDouble,
        row.getString(8),
        row.getString(9),
        row.getString(10),
        row.getString(11),
        new Date(format.parse(row.getString(12)).getTime),
        row.getString(13),
        row.getString(14).toDouble,
        new Date(format.parse(row.getString(15)).getTime),
        new Date(format.parse(row.getString(16)).getTime),
        new Date(format.parse(row.getString(17)).getTime),
        new Date(format.parse(row.getString(18)).getTime),
        new Date(format.parse(row.getString(19)).getTime),
        new Date(format.parse(row.getString(20)).getTime),
        row.getString(21),
        row.getString(22),
        row.getString(23),
        new Date(format.parse(row.getString(24)).getTime),
        row.getString(25).toDouble,
        row.getString(26),
        new Date(format.parse(row.getString(27)).getTime),
        row.getString(28),
        row.getString(29).toDouble,
        row.getString(30),
        row.getString(31),
        row.getString(32).toDouble,
        row.getString(33))
    })
    //TODO   SvrGroup 写入到 MySQL 数据库
    svrGroupMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_svr_group.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_biz4_svr_group_lst  SVR_GROUP_LST
    val svrGroupLstDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ4\\TABLE\\SVR_GROUP_LST.0.dat")
    //TODO 数据处理 生成  case class类   SvrGroupLst
    val svrGroupLstMap = svrGroupLstDF.map(row => {
      SvrGroupLst(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2).toDouble,
        row.getString(3).toDouble
      )
    })
    //TODO   SvrGroupLst 写入到 MySQL 数据库
    svrGroupLstMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_biz4_svr_group_lst.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_cntap4_branch  BRANCH
    val branchDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTAP4\\TABLE\\BRANCH.0.dat")
    //TODO 数据处理 生成  case class类   Branch
    val branchMap = branchDF.map(
      row => {
        Branch(row.getString(0),
          row.getString(1),
          row.getString(2),
          row.getString(3),
          row.getString(4),
          row.getString(5),
          row.getString(6),
          row.getString(7),
          row.getString(8),
          row.getString(9).toDouble,
          row.getString(10),
          row.getString(11),
          row.getString(12),
          row.getString(13),
          row.getString(14),
          row.getString(15),
          row.getString(16),
          row.getString(17),
          row.getString(18),
          row.getString(19),
          row.getString(20),
          row.getString(21)
        )
      }
    )
    //TODO   Branch 写入到 MySQL 数据库
    branchMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_cntap4_branch.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_cntr4_cntr_sub_state  CNTR_SUB_STATE
    val cntrSubStateDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR4\\TABLE\\CNTR_SUB_STATE.0.dat")
    //TODO 数据处理 生成  case class类   CNTRSubState
    val cntrSubStateMap = cntrSubStateDF.map(row => {
      CntrSubState(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        row.getString(2),
        new Date(format.parse(row.getString(3)).getTime),
        new Date(format.parse(row.getString(4)).getTime),
        new Date(format.parse(row.getString(5)).getTime),
        row.getString(6).toDouble,
        row.getString(7).toDouble,
        row.getString(8).toDouble,
        row.getString(9),
        row.getString(10),
        row.getString(11).toDouble,
        row.getString(12),
        row.getString(13).toDouble,
        row.getString(14).toDouble,
      )
    })
    //TODO   SubState 写入到 MySQL 数据库
    cntrSubStateMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_cntr4_cntr_sub_state.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_cntr4_if_of_alt   IF_OF_ALT
    val ifOfAltDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR4\\TABLE\\IF_OF_ALT.0.dat")
    //TODO 数据处理 生成  case class类   IfOfAlt
    val ifOfAltMap = ifOfAltDF.map(row => {
      IfOfAlt(
        row.getString(0).toDouble,
        row.getString(1).toDouble,
        new Date(format.parse(row.getString(2)).getTime),
        new Date(format.parse(row.getString(3)).getTime)
      )
    })
    //TODO   IfOfAlt 写入到 MySQL 数据库
    ifOfAltMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_cntr4_if_of_alt.toString, connectionProperties)

    //-------------------------------------------------------ok
    //TODO 读取文件  cl_cntr4_std_contract   STD_CONTRACT
    val stdContractDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR4\\TABLE\\STD_CONTRACT.0.dat")
    //TODO 数据处理 生成  case class类 StdContract
    val stdContractMap = stdContractDF.map(row => {
      StdContract(
        row.getString(0).toDouble,
        row.getString(1),
        row.getString(2),
        row.getString(3),
        row.getString(4),
        row.getString(5).toDouble,
        row.getString(6),
        row.getString(7),
        row.getString(8),
        row.getString(9).toDouble,
        row.getString(10),
        row.getString(11),
        new Date(format.parse(row.getString(12)).getTime),
        row.getString(13),
        row.getString(14),
        row.getString(15).toDouble,
        row.getString(16).toDouble,
        new Date(format.parse(row.getString(17)).getTime),
        new Date(format.parse(row.getString(18)).getTime),
        new Date(format.parse(row.getString(19)).getTime),
        new Date(format.parse(row.getString(20)).getTime),
        row.getString(21),
        row.getString(22).toDouble,
        row.getString(23),
        row.getString(24),
        row.getString(25),
        row.getString(26),
        row.getString(27),
        row.getString(28),
        new Date(format.parse(row.getString(29)).getTime),
        row.getString(30),
        row.getString(31).toDouble,
        row.getString(32),
        row.getString(33),
        row.getString(34),
        row.getString(35),
        row.getString(36),
        row.getString(37),
        row.getString(38),
        row.getString(39),
        row.getString(40),
        row.getString(41),
        row.getString(42),
        row.getString(43)
      )
    })
    //TODO   IfOfAlt 写入到 MySQL 数据库
    stdContractMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.cl_cntr4_std_contract.toString, connectionProperties)


    //-------------------------------------------------------ok
    //TODO 读取文件  product4_policy  POLICY
    val policyDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\PRODUCT4\\TABLE\\POLICY.0.dat")
    //TODO 数据处理 生成  case class类   POLICY
    val policyMap = policyDF.map(row => {
      Policy(
        row.getString(0),
        row.getString(1),
        row.getString(2),
        row.getString(3),
        row.getString(4),
        new Date(format.parse(row.getString(5)).getTime),
        new Date(format.parse(row.getString(6)).getTime),
        row.getString(7),
        row.getString(8),
        row.getString(9),
        row.getString(10),
        row.getString(11),
        row.getString(12),
        row.getString(13),
        row.getString(14),
        row.getString(15),
        row.getString(16),
        row.getString(17),
        row.getString(18),
        BigDecimal(row.getString(19)),
        BigDecimal(row.getString(20)),
        BigDecimal(row.getString(21)),
        row.getString(22),
        row.getString(23),
        row.getString(24),
        row.getString(25)
      )
    })
    //TODO   policy 写入到 MySQL 数据库
    policyMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, NBTableName.product4_policy.toString, connectionProperties)

  }
}
