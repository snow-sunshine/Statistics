package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz4_statistics_b2")
public class StatisticsB2String {
    private String years;
    private String times;
    private String polFrom;
    private String polCode;
    private String polName;
    private String dateFrom;
    private String inPsCnt;
    private String inPsAmt;
    private String payRCnt;
    private String payRAmt;
    private String payCrDrMrCnt;
    private String payCrDrMrAmt;
    private String payE9Cnt;
    private String payE9Amt;
    private String payEpCnt;
    private String payEpAmt;
    private String payRpRsCnt;
    private String payRpRsAmt;
    private String mrType;
    private String procDate;
}
