package com.guoshou.spark.po.JavaOB;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cl_biz5_svr_group_lst")
public class SvrGroupLstString {
    private String sgId;
    private String cntrNo;
    private String ipsnCategory;
    private String ipsnNo;
}
