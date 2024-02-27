package com.guoshou.spark.mapper.OB;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.CntrSubStateString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CntrSubStateStringOBMapper extends BaseMapper<CntrSubStateString> {

    @DS("OB")
    @Select("select sum(a.stdPremium) from cl_cntr5_cntr_sub_state a,cl_cntr5_std_contract b where b.polCode=#{polCode} and a.cntrId = b.cntrId and b.cntrStat ='K'")
    Long surXAmt1(@Param("polCode") String polCode);

}
