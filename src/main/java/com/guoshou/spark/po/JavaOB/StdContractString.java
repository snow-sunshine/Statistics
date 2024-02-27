package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_cntr5_std_contract")
public class StdContractString {
    private String cntrId;
    private String cntrFrom;
    private String applNo;
    private String cntrType;
    private String cntrNo;
    private String loseRegNum;
    private String originalCntrNo;
    private String polCode;
    private String mrType;
    private String masterCntrId;
    private String cntrStat;
    private String cntrTermCause;
    private String cntrTermDate;
    private String cgNo;
    private String sgNo;
    private String ipsnNum;
    private String termIpsnNum;
    private String applDate;
    private String signDate;
    private String inForceDate;
    private String autoInForceDate;
    private String renewFlag;
    private String renewTimes;
    private String mgrBranchNo;
    private String salesChannel;
    private String oSalesBranchNo;
    private String oSalesCode;
    private String nSalesBranchNo;
    private String nSalesCode;
    private String cntrExpiryDate;
    private String moneyinItrvl;
    private String moneyinItrvlMon;
    private String bddType;
    private String paidupFlag;
    private String cvForPremFlag;
    private String moneyinType;
    private String bankCode;
    private String bankAccNo;
    private String accCustName;
    private String saleCode;
    private String schemeCode;
    private String giftType;
    private String otherInsurFlag;
    private String currencyCode;
}
