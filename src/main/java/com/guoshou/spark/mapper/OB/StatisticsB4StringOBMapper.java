package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.Dto.JiTuanDto;
import com.guoshou.spark.po.JavaOB.StatisticsB4String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface StatisticsB4StringOBMapper extends BaseMapper<StatisticsB4String> {

    //select count(*) from cl_biz5_statistics_b4 where polCode='' and years ='' and time =''
    //select count(*) from cl_biz5_statistics_b4 WHERE  polCode='Z5A' and yearS='2023' and times ='2'
    @DS("OB")
    @Select("SELECT count(*) FROM cl_biz5_statistics_b4 " +
            "where polCode=#{polCode} " +
            "AND  years = #{years} " +
            "AND  times =#{times}")
    long selectCountNowRecNewCount(@Param("polCode") String polCode, @Param("years") String years, @Param("times") String times);
//-------------------------------------------------------------------------------------------------------------
    @DS("OB")
    @Select("SELECT count(*) FROM cl_biz5_statistics_b4 " +
            "where polCode=#{polCode} and applBranchNo =#{branchNo}" +
            "AND  years = #{years} " +
            "AND  times =#{times}")
    long selectCountNowRecNewCountJiTuan(@Param("polCode") String polCode, @Param("years") String years, @Param("times") String times,@Param("branchNo") String branchNo);

}
