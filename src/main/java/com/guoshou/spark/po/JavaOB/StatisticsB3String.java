package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_statistics_b3")
public class StatisticsB3String {
    private String years;
    private String times;
    private String polFrom;
    private String polCode;
    private String polName;
    private String dateFrom;
    private String payRCnt;
    private String payRAmt;
    private String payXCnt;
    private String payXAmt;
    private String surXAmt;
    private String mrType;
    private String procDate;
}
