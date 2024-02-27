package com.guoshou.spark.mapper.NB;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.CntrSubStateString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CntrSubStateStringMapper extends BaseMapper<CntrSubStateString> {

    @Select("select sum(a.stdPremium) from cl_cntr4_cntr_sub_state a,cl_cntr4_std_contract b where b.polCode=#{polCode} and a.cntrId = b.cntrId and b.cntrStat ='K'")
    Long surXAmt1(@Param("polCode") String polCode);
}
