package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz4_amend_gen_info")
public class AmendGenInfoString {
    private String mtnId;
    private String cntrNo;
    private String amendBranchNo;
    private String amendDate;
    private String amendSeq;
    private String oldCntrNo;
    private String polCode;
    private String polNameChn;
    private String applType;
    private String applDate;
    private String applBranchNo;
    private String applClerkCode;
    private String applEnterBranch;
    private String applEnterClerk;
    private String oclkBranchNo;
    private String oclkClerkCode;
    private String vclkBranchNo;
    private String vclkClerkCode;
    private String commitDate;
    private String enterDate;
    private String verifyDate;
    private String applStat;
    private String approvePsn;
    private String contactOTel;
    private String mobileNo;
    private String ipsnEmail;
    private String contactAddress;
    private String postCode;
    private String amendArcNo;
    private String outForceDate;
    private String reInForceDate;
    private String cntrStat;
    private String cntrTermCause;
    private String reverseReason;
    private String verifyOpn;
    private String checkOpn;
}
