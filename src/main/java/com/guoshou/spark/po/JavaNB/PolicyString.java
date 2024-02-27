package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("product4_policy")
public class PolicyString implements Serializable {
    private String branchNo;
    private String polCode;
    private String chiefPolCode;
    private String polNameEng;
    private String polNameChn;
    private String polEffDate;
    private String polExpDate;
    private String businessType;
    private String insurDurType;
    private String yieldType;
    private String premiumType;
    private String botAgeLmt;
    private String topAgeLmt;
    private String sexSaleTo;
    private String moneyinItrvl;
    private String moneyinType;
    private String mrType;
    private String uwRiskFact;
    private String siRiskFact;
    private String premAlgo;
    private String ageAlgo;
    private String cashAlgo;
    private String subpolOptionFlag;
    private String coverOptionFlag;
    private String faceAmntType;
    private String singleSalesFlag;
}
