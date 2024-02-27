package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.guoshou.spark.po.JavaOB.StatisticsB3String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsB3StringOBMapper extends BaseMapper<StatisticsB3String> {

    @DS("OB")
    @Select("select * from cl_biz5_statistics_b3 where years=#{years} and times=#{times}")
    List<StatisticsB3String> selAllStatisticsB3OB(@Param("years") String years, @Param("times") String times);

}
