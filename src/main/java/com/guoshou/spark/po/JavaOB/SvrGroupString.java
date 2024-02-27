package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_svr_group")
public class SvrGroupString {
    private String sgId;
    private String custNo;
    private String contactSeq;
    private String applNo;
    private String sgNo;
    private String sgType;
    private String mgrBranchNo;
    private String sgCntrNum;
    private String salesChannel;
    private String salesBranchNo;
    private String salesCode;
    private String moneyinItrvl;
    private String moneyinDate;
    private String moneyinType;
    private String sumPrem;
    private String applDate;
    private String evDate;
    private String billLastPrtDate;
    private String inForceDate;
    private String autoInForceDate;
    private String signDate;
    private String bankCode;
    private String bankAccNo;
    private String accCustName;
    private String cntrExpiryDate;
    private String loseRegNum;
    private String cntrTermCause;
    private String cntrTermDate;
    private String cntrStat;
    private String masterSgId;
    private String mrType;
    private String polCode;
    private String renewTimes;
    private String originalCntrNo;
}
