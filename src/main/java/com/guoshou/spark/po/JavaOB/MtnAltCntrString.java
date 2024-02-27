package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_mtn_alt_cntr")
public class MtnAltCntrString {
    private String mtnId;
    private String mtnBizNo;
    private String ipsnNo;
    private String mtnItemCode;
    private String procStat;
    private String procDate;
}
