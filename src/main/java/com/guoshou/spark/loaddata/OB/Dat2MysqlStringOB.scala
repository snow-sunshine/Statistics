package com.guoshou.spark.loaddata.OB

import com.guoshou.spark.config.OBTableName
import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}
import com.guoshou.spark.po.OB.{AmendGenInfoString, AmendItemString, BizParameterString, BranchString, CntrSubStateString, EndowAccString, IfOfAltString, InsurApplString, MioLogString, MtnAltCntrString, MtnOsmbShiftString, PlnmioRecString, PolicyString, SgApplString, StatisticsB1String, StatisticsB2String, StatisticsB3String, StatisticsB4String, StdContractString, SvrGroupLstString, SvrGroupString}
object Dat2MysqlStringOB {

  def main(args: Array[String]): Unit = {
    /**
     * 配置spark环境
     */
    val spark = SparkSession.builder()
      .appName("LoadDataToMySQL")
      .master("local[*]")
      .getOrCreate()

//    spark.sparkContext.setLogLevel("ERROR")

    /**
     * 指定 MySQL 的连接参数
     */
    val url = "jdbc:mysql://localhost:3306/tjob?characterEncoding=utf8"
    val connectionProperties = new java.util.Properties()
    connectionProperties.setProperty("user", "root")
    connectionProperties.setProperty("password", "123456")

    import spark.implicits._
//    TODO 时间处理

    //TODO 读取文件 STATISTICS_B1
//    val statisticsB1DF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\STATISTICS_B1.0.dat")
//    //TODO 数据处理 生成  case class类   STATISTICS_B1
//    val statisticsB1Map = statisticsB1DF.map(row => {
//      StatisticsB1String(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25)
//      )
//    })
//    //TODO   STATISTICS_B1 写入到 MySQL 数据库
//    statisticsB1Map.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_statistics_b1.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件 biz_proc5_biz_parameter
//    val bizParameterDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\BIZ_PROC5\\TABLE\\BIZ_PARAMETER.0.dat")
//    //TODO 数据处理 生成  case class类  BizParameter
//    val bizParameterMap = bizParameterDF.map(row => {
//      BizParameterString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2)
//      )
//    })
//    //TODO   BizParameter 写入到 MySQL 数据库
//    bizParameterMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.biz_proc5_biz_parameter.toString, connectionProperties)
//
//
//    //-------------------------------------------------------ok 需处理
//    //TODO 读取文件 cl_biz5_endow_acc
//    val endowAccDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\ENDOW_ACC.0.dat")
//    //TODO 数据处理 生成  case class类   EndowAcc
//    val endowAccMap = endowAccDF.map(row => {
//      EndowAccString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26)
//      )
//    })
//    //TODO   EndowAcc 写入到 MySQL 数据库
//    endowAccMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_endow_acc.toString, connectionProperties)

    //-------------------------------------------------------ok 需处理
    //TODO 读取文件 cl_biz4_mio_log
    val mioLogDF: DataFrame = spark.read
      .option("header", "false")
      .option("delimiter", "|")
      .option("charset", "GB2312") // 设置编码格式为GB2312
      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\MIO_LOG.0.dat")
    //TODO 数据处理 生成  case class类   MioLog
    val minLogMap = mioLogDF.map(row => {
     MioLogString(
        row.getString(0),
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
        row.getString(20),
        row.getString(21),
        row.getString(22),
        row.getString(23),
        row.getString(24),
        row.getString(25),
        row.getString(26),
        row.getString(27),
        row.getString(28),
        row.getString(29),
        row.getString(30),
        row.getString(31),
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
        row.getString(43),
        row.getString(44),
        row.getString(45),
        row.getString(46)
      )
    })
    //TODO   MioLog 写入到 MySQL 数据库
    minLogMap.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, OBTableName.cl_biz5_mio_log.toString, connectionProperties)

    //-------------------------------------------------------ok 需处理

    //-------------------------------------------------------ok
    //TODO 读取文件 cl_biz5_MTN_ALT_CNTR  MtnAltCntrString
//    val mtnAltCntrDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\MTN_ALT_CNTR.0.dat")
//    //TODO 数据处理 生成  case class类 cl_biz5_MTN_ALT_CNTR
//    val mtnAltCntrMap = mtnAltCntrDF.map(row => {
//      MtnAltCntrString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5)
//      )
//    })
//    //TODO   MtnOsmbShift 写入到 MySQL 数据库
//    mtnAltCntrMap.write
//          .mode(SaveMode.Overwrite)
//          .jdbc(url, OBTableName.cl_biz5_mtn_alt_cntr.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_biz5_plnmio_rec   PLNMIO_REC.0.dat
//    val plnmioRecDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\PLNMIO_REC.0.dat")
//    //TODO 数据处理 生成  case class类 PlnmioRec
//    val plnmioRecMap = plnmioRecDF.map(row => {
//      PlnmioRecString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33),
//        row.getString(34),
//        row.getString(35),
//        row.getString(36),
//        row.getString(37)
//      )
//    })
//    //TODO   PlnmioRec 写入到 MySQL 数据库
//    plnmioRecMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_plnmio_rec.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件 cl_biz5_statistics_b2 STATISTICS_B2
//    val statisticsB2DF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\STATISTICS_B2.0.dat")
//    //TODO 数据处理 生成  case class类   STATISTICS_B2
//    val statisticsB2Map: Dataset[StatisticsB2String] = statisticsB2DF.map(row => {
//      StatisticsB2String(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19)
//      )
//    })
//    //TODO   STATISTICS_B2写入到 MySQL 数据库
//    statisticsB2Map.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_statistics_b2.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件 cl_biz5_statistics_b3 STATISTICS_B3
////    val statisticsB3DF: DataFrame = spark.read
////      .option("header", "false")
////      .option("delimiter", "|")
////      .option("charset", "GB2312") // 设置编码格式为GB2312
////      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\STATISTICS_B3.0.dat")
////    //TODO 数据处理 生成  case class类   StatisticsB3
////    val statisticsB3Map = statisticsB3DF.map(row => {
////      StatisticsB3String(
////        row.getString(0),
////        row.getString(1),
////        row.getString(2),
////        row.getString(3),
////        row.getString(4),
////        row.getString(5),
////        row.getString(6),
////        row.getString(7),
////        row.getString(8),
////        row.getString(9),
////        row.getString(10),
////        row.getString(11),
////        row.getString(12)
////      )
////    })
////    //TODO   StatisticsB3 写入到 MySQL 数据库
////    statisticsB3Map.write
////      .mode(SaveMode.Overwrite)
////      .jdbc(url, OBTableName.cl_biz5_statistics_b3.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_biz5_svr_group  SVR_GROUP
//    val svrGroupDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\SVR_GROUP.0.dat")
//    //TODO 数据处理 生成  case class类   SvrGroup
//    val svrGroupMap = svrGroupDF.map(row => {
//      SvrGroupString(row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33))
//    })
//    //TODO   SvrGroup 写入到 MySQL 数据库
//    svrGroupMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_svr_group.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_biz5_svr_group_lst  SVR_GROUP_LST
//    val svrGroupLstDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\SVR_GROUP_LST.0.dat")
//    //TODO 数据处理 生成  case class类   SvrGroupLst
//    val svrGroupLstMap = svrGroupLstDF.map(row => {
//      SvrGroupLstString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3)
//      )
//    })
//    //TODO   SvrGroupLst 写入到 MySQL 数据库
//    svrGroupLstMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_svr_group_lst.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_cntap5_branch  BRANCH
//    val branchDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTAP5\\TABLE\\BRANCH.0.dat")
//    //TODO 数据处理 生成  case class类   Branch
//    val branchMap = branchDF.map(
//      row => {
//        BranchString(row.getString(0),
//          row.getString(1),
//          row.getString(2),
//          row.getString(3),
//          row.getString(4),
//          row.getString(5),
//          row.getString(6),
//          row.getString(7),
//          row.getString(8),
//          row.getString(9),
//          row.getString(10),
//          row.getString(11),
//          row.getString(12),
//          row.getString(13),
//          row.getString(14),
//          row.getString(15),
//          row.getString(16),
//          row.getString(17),
//          row.getString(18),
//          row.getString(19),
//          row.getString(20),
//          row.getString(21)
//        )
//      }
//    )
//    //TODO   Branch 写入到 MySQL 数据库
//    branchMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_cntap5_branch.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_cntr5_cntr_sub_state  CNTR_SUB_STATE
//    val cntrSubStateDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR5\\TABLE\\CNTR_SUB_STATE.0.dat")
//    //TODO 数据处理 生成  case class类   CNTRSubState
//    val cntrSubStateMap = cntrSubStateDF.map(row => {
//      CntrSubStateString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14)
//      )
//    })
//    //TODO   SubState 写入到 MySQL 数据库
//    cntrSubStateMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_cntr5_cntr_sub_state.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_cntr4_if_of_alt   IF_OF_ALT
//    val ifOfAltDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR5\\TABLE\\IF_OF_ALT.0.dat")
//    //TODO 数据处理 生成  case class类   IfOfAlt
//    val ifOfAltMap = ifOfAltDF.map(row => {
//      IfOfAltString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3)
//      )
//    })
//    //TODO   IfOfAlt 写入到 MySQL 数据库
//    ifOfAltMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_cntr5_if_of_alt.toString, connectionProperties)
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_cntr4_std_contract   STD_CONTRACT
//    val stdContractDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_CNTR5\\TABLE\\STD_CONTRACT.0.dat")
//    //TODO 数据处理 生成  case class类 StdContract
//    val stdContractMap = stdContractDF.map(row => {
//      StdContractString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33),
//        row.getString(34),
//        row.getString(35),
//        row.getString(36),
//        row.getString(37),
//        row.getString(38),
//        row.getString(39),
//        row.getString(40),
//        row.getString(41),
//        row.getString(42),
//        row.getString(43)
//      )
//    })
//    //TODO   IfOfAlt 写入到 MySQL 数据库
//    stdContractMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_cntr5_std_contract.toString, connectionProperties)
//
////    -------------------------------------------------------ok
////    TODO 读取文件  product4_policy  POLICY
//    val policyDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\PRODUCT5\\TABLE\\POLICY.0.dat")
//    //TODO 数据处理 生成  case class类   POLICY
//    val policyMap = policyDF.map(row => {
//      PolicyString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25)
//      )
//    })
//    //TODO   policy 写入到 MySQL 数据库
//    policyMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.product5_policy.toString, connectionProperties)
//
//
//    //-------------------------------------------------------ok
//    //TODO 读取文件  cl_biz5_amend_gen_info  AMEND_GEN_INFO
//    val amendGenInfoDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\AMEND_GEN_INFO.0.dat")
//    //TODO 数据处理 生成  case class类   AMEND_GEN_INFO
//    val amendGenInfoMap = amendGenInfoDF.map(row => {
//      AmendGenInfoString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33),
//        row.getString(34),
//        row.getString(35)
//      )
//    })
//    //TODO   AMEND_GEN_INFO 写入到 MySQL 数据库
//    amendGenInfoMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_amend_gen_info.toString, connectionProperties)
//
//    //TODO 读取文件  cl_biz5_amend_item  AmendItem
//    val AmendItemDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\AMEND_ITEM.0.dat")
//    //TODO 数据处理 生成  case class类   AmendItem
//    val AmendItemMap = AmendItemDF.map(row => {
//      AmendItemString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5)
//      )
//    })
//    //TODO   AmendItem 写入到 MySQL 数据库
//    AmendItemMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_amend_item.toString, connectionProperties)
//
//    //TODO 读取文件  product4_policy  INSUR_APPL
//    val insurApplDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\INSUR_APPL.0.dat")
//    //TODO 数据处理 生成  case class类   INSUR_APPL
//    val insurApplMap = insurApplDF.map(row => {
//      InsurApplString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33),
//        row.getString(34),
//        row.getString(35),
//        row.getString(36),
//        row.getString(37),
//        row.getString(38),
//        row.getString(39),
//        row.getString(40),
//        row.getString(41),
//        row.getString(42),
//        row.getString(43),
//        row.getString(44),
//        row.getString(45),
//        row.getString(46),
//        row.getString(47),
//        row.getString(48),
//        row.getString(49),
//        row.getString(50),
//        row.getString(51),
//        row.getString(52),
//        row.getString(53),
//        row.getString(54),
//        row.getString(55),
//        row.getString(56),
//        row.getString(57),
//        row.getString(58),
//        row.getString(59),
//        row.getString(60),
//        row.getString(61),
//        row.getString(62),
//        row.getString(63),
//        row.getString(64),
//        row.getString(65)
//      )
//    })
//    //TODO   INSUR_APPL 写入到 MySQL 数据库
//    insurApplMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_insur_appl.toString, connectionProperties)
//
//    //TODO 读取文件  product4_policy  SG_APPL
//    val sgApplDF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\SG_APPL.0.dat")
//    //TODO 数据处理 生成  case class类   SG_APPL
//    val sgApplMap = sgApplDF.map(row => {
//      SgApplString(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12),
//        row.getString(13),
//        row.getString(14),
//        row.getString(15),
//        row.getString(16),
//        row.getString(17),
//        row.getString(18),
//        row.getString(19),
//        row.getString(20),
//        row.getString(21),
//        row.getString(22),
//        row.getString(23),
//        row.getString(24),
//        row.getString(25),
//        row.getString(26),
//        row.getString(27),
//        row.getString(28),
//        row.getString(29),
//        row.getString(30),
//        row.getString(31),
//        row.getString(32),
//        row.getString(33),
//        row.getString(34),
//        row.getString(35),
//        row.getString(36),
//        row.getString(37),
//        row.getString(38),
//        row.getString(39),
//        row.getString(40),
//        row.getString(41),
//        row.getString(42),
//        row.getString(43),
//        row.getString(44),
//        row.getString(45)
//      )
//    })
//    //TODO   SG_APPL 写入到 MySQL 数据库
//    sgApplMap.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_sg_appl.toString, connectionProperties)
//
//    //TODO 读取文件  product4_policy  STATISTICS_B4
//    val statisticsB4DF: DataFrame = spark.read
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("charset", "GB2312") // 设置编码格式为GB2312
//      .csv("C:\\Users\\Administrator\\Desktop\\data\\CL_BIZ5\\TABLE\\STATISTICS_B4.0.dat")
//    //TODO 数据处理 生成  case class类   STATISTICS_B4
//    val statisticsB4Map = statisticsB4DF.map(row => {
//      StatisticsB4String(
//        row.getString(0),
//        row.getString(1),
//        row.getString(2),
//        row.getString(3),
//        row.getString(4),
//        row.getString(5),
//        row.getString(6),
//        row.getString(7),
//        row.getString(8),
//        row.getString(9),
//        row.getString(10),
//        row.getString(11),
//        row.getString(12)
//      )
//    })
//    //TODO   STATISTICS_B4 写入到 MySQL 数据库
//    statisticsB4Map.write
//      .mode(SaveMode.Overwrite)
//      .jdbc(url, OBTableName.cl_biz5_statistics_b4.toString, connectionProperties)

  }
}
