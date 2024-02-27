package com.guoshou.spark.mapper.NB;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.StatisticsB2String;
import com.guoshou.spark.po.JavaNB.StatisticsB3String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StatisticsB3StringMapper extends BaseMapper<StatisticsB3String> {

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','0','总计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode0(@Param("years") String years, @Param("times") String times);

    //总颁险种合计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','1','总颁险种合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where polFrom='总颁险种' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode1(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','2','地方险种合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where polFrom='地方险种' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode2(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','4','主险合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where mrType='M' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode3(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','5','附加险合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where mrType!='M' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode4(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','6','PICC1合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where dateFrom='PICC1' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode5(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','7','PICC3合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where dateFrom='PICC3' and  years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode6(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','8','自有系统合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where  dateFrom='自有' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode7(@Param("years") String years, @Param("times") String times);

    //总计
    @Select(" INSERT INTO cl_biz4_statistics_b3 (years, times, polFrom, polCode, polName, dateFrom, payRCnt, payRAmt, payXCnt, payXAmt, surXAmt, procDate) " +
            "SELECT years, times," +
            "'汇总','9','手工管理合计','汇总',sum(payRCnt),sum(payRAmt),sum(payXCnt),sum(payXAmt),sum(surXAmt),procDate from cl_biz4_statistics_b3 where  dateFrom='手工' and years=#{years} AND times=#{times} " +
            "group by years, times, procDate;")
    Integer insertPolode8(@Param("years") String years, @Param("times") String times);

    @Select("select * from  cl_biz4_statistics_b3 where years=#{years} and times =#{times} order by polFrom desc,polCode asc")
    List<StatisticsB3String> SelStatisticsB3ByYearAndTimes(@Param("years") int years, @Param("times") int times);

}

