package com.guoshou.spark.po.JavaNB;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_proc4_biz_parameter")
public class BizParameterString implements Serializable {
    private String bizParamCode;
    private String bizParamVal;
    private String bizParamChn;
}
