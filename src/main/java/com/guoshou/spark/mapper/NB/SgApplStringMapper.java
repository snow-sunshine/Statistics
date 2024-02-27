package com.guoshou.spark.mapper.NB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.SgApplString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SgApplStringMapper extends BaseMapper<SgApplString> {

    @DS("OB")
    @Select("select sgNo from cl_biz4_sg_appl where amendArcNo =#{amendArcNo}")
    String IntoCntrNo(@Param("amendArcNo") String amendArcNo);
}
