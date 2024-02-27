package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_mio_log")
public class MioLogString {
    private String mioLogId;
    private String plnmioRecId;
    private String polCode;
    private String cntrType;
    private String cgNo;
    private String sgNo;
    private String cntrNo;
    private String mtnId;
    private String mtnItemCode;
    private String ipsnNo;
    private String mioCustNo;
    private String mioCustName;
    private String plnmioDate;
    private String mioDate;
    private String mioLogUpdTime;
    private String premDeadline;
    private String mioItemCode;
    private String mioTypeCode;
    private String mgrBranchNo;
    private String oclkBranchNo;
    private String oclkClerkNo;
    private String salesBranchNo;
    private String salesChannel;
    private String salesNo;
    private String mioClass;
    private String amnt;
    private String bankCode;
    private String bankAccNo;
    private String mioTxClass;
    private String mioTxNo;
    private String corrMioDate;
    private String corrMioTxNo;
    private String receiptNo;
    private String voucherNo;
    private String clearingMioTxNo;
    private String finPlnmioDate;
    private String remark;
    private String netIncome;
    private String vat;
    private String vatRate;
    private String vatId;
    private String branchTypeFlag;
    private String finAccNo;
    private String finChannel;
    private String chargeNo;
    private String systemId;
    private String topayLogId;
}