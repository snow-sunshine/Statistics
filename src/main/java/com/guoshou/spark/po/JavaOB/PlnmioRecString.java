package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_plnmio_rec")
public class PlnmioRecString {
    private String plnmioRecId;
    private String cntrType;
    private String sgNo;
    private String cgNo;
    private String archiveBranch;
    private String signYear;
    private String polCode;
    private String cntrNo;
    private String mtnId;
    private String mtnItemCode;
    private String ipsnNo;
    private String mioCustNo;
    private String mioCustName;
    private String mioClass;
    private String plnmioDate;
    private String premDeadline;
    private String mioItemCode;
    private String mioTypeCode;
    private String mgrBranchNo;
    private String salesChannel;
    private String salesBranchNo;
    private String salesNo;
    private String amnt;
    private String lockFlag;
    private String bankCode;
    private String bankAccNo;
    private String holdFlag;
    private String voucherNo;
    private String finPlnmioDate;
    private String clearingMioTxNo;
    private String mioProcFlag;
    private String accId;
    private String remark;
    private String plnmioCreateTime;
    private String mtnNo;
    private String sysNoOriginal;
    private String systemId;
    private String topayLogId;
}
