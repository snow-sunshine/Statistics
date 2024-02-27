package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_cntr4_cntr_sub_state")
public class CntrSubStateString {
    private String cntrId;
    private String ipsnNo;
    private String polCode;
    private String inForceDate;
    private String stopMoneyinDate;
    private String termDate;
    private String stdPremium;
    private String premium;
    private String faceAmnt;
    private String hldrExPremFlag;
    private String ipsnExPremFlag;
    private String paidupFaceAmnt;
    private String moneyinItrvl;
    private String moneyinDur;
    private String annAmnt;
}
