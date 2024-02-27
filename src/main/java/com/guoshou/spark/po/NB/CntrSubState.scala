package com.guoshou.spark.po.NB

import java.sql.Date
;case class CntrSubState(cntrId: Double,
        ipsnNo: Double,
        polCode: String,
        inForceDate: Date,
        stopMoneyinDate: Date,
        termDate: Date,
        stdPremium: Double,
        premium: Double,
        faceAmnt: Double,
        hldrExPremFlag: String,
        ipsnExPremFlag: String,
        paidupFaceAmnt: Double,
        moneyinItrvl: String,
        moneyinDur: Double,
        annAmnt: Double)
