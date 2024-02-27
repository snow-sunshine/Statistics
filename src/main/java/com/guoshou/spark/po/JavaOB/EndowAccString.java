package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_endow_acc")
public class EndowAccString {
    private String accId;
    private String mgrBranchNo;
    private String accNo;
    private String accNoSeq;
    private String custNo;
    private String accOpenDate;
    private String maturity;
    private String accCloseDate;
    private String accTypeCode;
    private String cashType;
    private String accStat;
    private String settledIntRate;
    private String lastUpdDate;
    private String balance;
    private String frozenBalance;
    private String intCalBase;
    private String unsettledInt;
    private String lastSettleDate;
    private String lastSettleBalance;
    private String intCalType;
    private String settleCycle;
    private String settleDay;
    private String settleType;
    private String accExtractDate;
    private String unsettledDate;
    private String polCode;
    private String oriAccTypeCode;
}
