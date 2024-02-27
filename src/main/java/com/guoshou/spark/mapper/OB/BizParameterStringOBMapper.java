package com.guoshou.spark.mapper.OB;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.BizParameterString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BizParameterStringOBMapper extends BaseMapper<BizParameterString> {

    @DS("OB")
    @Select("select bizParamVal  from biz_proc5_biz_parameter where bizParamCode ='OBPSORNBPS_FLAG'")
    List<BizParameterString> SelBizParameterObOrNbFlag();
}
