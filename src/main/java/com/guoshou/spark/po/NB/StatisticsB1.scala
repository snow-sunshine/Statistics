package com.guoshou.spark.po.NB

case class StatisticsB1(
                         years: Double,
                         times: Double,
                         polFrom: String,
                         polCode: String,
                         polName: String,
                         dateFrom: String,
                         begCount: Double,
                         begNorCount: Double,
                         begExpCount: Double,
                         begLoseCount: Double,
                         begLoseStopCount: Double,
                         nowRecNewCount: Double,
                         nowReinCount: Double,
                         nowLoseCount: Double,
                         nowExpCount: Double,
                         nowExpStopCount: Double,
                         nowSevStopCount: Double,
                         nowLoseStopCount: Double,
                         nowOtherStopCount: Double,
                         endCount: Double,
                         endNorCount: Double,
                         endExpCount: Double,
                         endLoseCount: Double,
                         endLoseStopCount: Double,
                         mrType: String,
                         procDate: String
                       )
