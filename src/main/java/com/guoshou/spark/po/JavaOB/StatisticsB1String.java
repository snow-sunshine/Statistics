package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_statistics_b1")
public class StatisticsB1String implements Serializable {
    private String years;
    private String times;
    private String polFrom;
    private String polCode;
    private String polName;
    private String dateFrom;
    private String begCount;
    private String begNorCount;
    private String begExpCount;
    private String begLoseCount;
    private String begLoseStopCount;
    private String nowRecNewCount;
    private String nowReinCount;
    private String nowLoseCount;
    private String nowExpCount;
    private String nowExpStopCount;
    private String nowSevStopCount;
    private String nowLoseStopCount;
    private String nowOtherStopCount;
    private String endCount;
    private String endNorCount;
    private String endExpCount;
    private String endLoseCount;
    private String endLoseStopCount;
    private String mrType;
    private String procDate;
}
