package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_cntap5_branch")
public class BranchString {
    private String branchNo;
    private String cityCode;
    private String branchName;
    private String prioBranchNo;
    private String branchPostCode;
    private String branchTel;
    private String branchAddress;
    private String branchFax;
    private String branchEmailNo;
    private String branchPolDefflag;
    private String branchSearchName;
    private String processingCenter;
    private String branchClassNo;
    private String printInfoBranch;
    private String archiveBranch;
    private String printBranchName;
    private String branchGrp;
    private String branchPosition;
    private String businessLic;
    private String taxpayerId;
    private String serviceTel;
    private String webAddr;
}
