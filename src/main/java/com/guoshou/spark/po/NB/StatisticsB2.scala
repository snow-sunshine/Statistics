package com.guoshou.spark.po.NB

case class StatisticsB2(
                         years: Double,
                         times: Double,
                         polFrom: String,
                         polCode: String,
                         polName: String,
                         dateFrom: String,
                         inPsCnt: Double,
                         inPsAmt: Double,
                         payRCnt: Double,
                         payRAmt: Double,
                         payCrDrMrCnt: Double,
                         payCrDrMrAmt: Double,
                         payE9Cnt: Double,
                         payE9Amt: Double,
                         payEpCnt: Double,
                         payEpAmt: Double,
                         payRpRsCnt: Double,
                         payRpRsAmt: Double,
                         mrType: String,
                         procDate: String
                       )
