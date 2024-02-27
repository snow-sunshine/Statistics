package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_sg_appl")
public class SgApplString {
    private String applId;
    private String applNo;
    private String unevCustId;
    private String contactSeq;
    private String applType;
    private String inForceStFlag;
    private String prtInvoiceType;
    private String sgNo;
    private String sgType;
    private String sgCntrNum;
    private String moneyinItrvl;
    private String polCode;
    private String moneyinDate;
    private String moneyinType;
    private String bankCode;
    private String bankaccNo;
    private String bankaccName;
    private String sumPrem;
    private String applDate;
    private String evDate;
    private String mgrBranchNo;
    private String acceptBranchNo;
    private String acceptDeptType;
    private String acceptDeptNo;
    private String bizAcceptDate;
    private String sysAcceptDate;
    private String salesChannel;
    private String salesBranchNo;
    private String salesCode;
    private String oclkClerkNo;
    private String oclkBranchNo;
    private String prtDate;
    private String ernstPrem;
    private String applEntBranchNo;
    private String applEntClerkNo;
    private String listEntBranchNo;
    private String listEntClerkNo;
    private String applChkBranchNo;
    private String applChkClerkNo;
    private String listChkBranchNo;
    private String listChkClerkNo;
    private String recvMode;
    private String accesschannel;
    private String amendArcNo;
    private String oldCntrNo;
    private String nextMoneyinDate;
}
