package com.guoshou.spark.mapper.NB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.StatisticsB4String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface StatisticsB4StringMapper extends BaseMapper<StatisticsB4String> {

    @Select("SELECT count(*) \n" +
            "FROM cl_biz4_statistics_b4\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  yearS = #{years}\n" +
            "AND  times =#{times};")
    //select count(*) from cl_biz4_statistics_b4 WHERE  polCode='' and yearS='2023' and times ='2'
    long selectCountNowRecNewCount(@Param("polCode") String polCode, @Param("years") String years, @Param("times") String times);

//-------------------------------------------------------------------------------------------------------------
    @Select("SELECT count(*) FROM cl_biz4_statistics_b4 " +
            "where polCode=#{polCode} and applBranchNo =#{branchNo}" +
            "AND  years = #{years} " +
            "AND  times =#{times}")
    long selectCountNowRecNewCountJiTuan(@Param("polCode") String polCode, @Param("years") String years, @Param("times") String times,@Param("branchNo") String branchNo);
}
