package com.guoshou.spark.po.JavaOB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_cntr5_if_of_alt")
public class IfOfAltString {
    private String cntrId;
    private String ipsnNo;
    private String outForceDate;
    private String reInForceDate;
}
