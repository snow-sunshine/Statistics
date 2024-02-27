package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_statistics_b4")
public class StatisticsB4String {
    private String years;
    private String times;
    private String busSys;
    private String proBranchNo;
    private String regBranchNo;
    private String applBranchNo;
    private String cntrNo;
    private String polCode;
    private String inForceDate;
    private String amendDate;
    private String faceAmnt;
    private String amendType;
    private String remark;
}
