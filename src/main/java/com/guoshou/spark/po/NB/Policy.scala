package com.guoshou.spark.po.NB

import java.sql.Date
;case class Policy (
        branchNo: String,
        polCode: String,
        chiefPolCode: String,
        polNameEng: String,
        polNameChn: String,
        polEffDate: Date,
        polExpDate: Date,
        businessType: String,
        insurDurType: String,
        yieldType: String,
        premiumType: String,
        botAgeLmt: String,
        topAgeLmt: String,
        sexSaleTo: String,
        moneyinItrvl: String,
        moneyinType: String,
        mrType: String,
        uwRiskFact: String,
        siRiskFact: String,
        premAlgo: BigDecimal,
        ageAlgo: BigDecimal,
        cashAlgo: BigDecimal,
        subpolOptionFlag: String,
        coverOptionFlag: String,
        faceAmntType: String,
        singleSalesFlag: String
        )
