package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.StatisticsB1String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.spark.sql.execution.columnar.LONG;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsB1StringOBMapper extends BaseMapper<StatisticsB1String> {

    @DS("OB")
    @Select("delete from cl_biz5_statistics_b1 where dateFrom = #{dateFrom} or dateFrom='手工' or dateFrom = '自动'")
//    @Select("select count(*)  from cl_biz5_statistics_b1 where dateFrom = #{dateFrom} or dateFrom='手工' or dateFrom = '自动'")
    Long DelStatisticsB1DateFrom(@Param("dateFrom") String dateFrom);

    @DS("OB")
    @Select("select count(*)  from cl_biz5_statistics_b1 where years = #{years} and times=#{times}")
    long SelStatisticsB1ByYearAndTimes(@Param("years") int years, @Param("times") int times);

    @DS("OB")
    @Select("delete from cl_biz5_statistics_b1 where years = #{years} and times=#{times}")
    Long DelStatisticsB1ByYearAndTimes (@Param("years") int years, @Param("times") int times);

    @DS("OB")
    @Select("select * from cl_biz5_statistics_b1 where years = #{years} and times=#{times} and polCode=#{polCode}")
    List<StatisticsB1String> SelStatisticsB1ByPolCode(@Param("years") String years, @Param("times") String times,@Param("polCode") String polCode );

    @DS("OB")
    @Select("select * from cl_biz5_statistics_b1 where years=#{years} and times=#{times}")
    List<StatisticsB1String> selAllStatisticsB1OB(@Param("years") String years, @Param("times") String times);
}
