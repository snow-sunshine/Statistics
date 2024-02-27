package com.guoshou.spark.mapper.NB;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.BizParameterString;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BizParameterStringMapper extends BaseMapper<BizParameterString> {
    @Select("select bizParamVal  from biz_proc4_biz_parameter where bizParamCode ='OBPSORNBPS_FLAG'")
    List<BizParameterString> SelBizParameterObOrNbFlag();
}
