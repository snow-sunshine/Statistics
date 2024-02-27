package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_cntr4_if_of_alt")
public class IfOfAltString {
    private String cntrId;
    private String ipsnNo;
    private String outForceDate;
    private String reInForceDate;
}
