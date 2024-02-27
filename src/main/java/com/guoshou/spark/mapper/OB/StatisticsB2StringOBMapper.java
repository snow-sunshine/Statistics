package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.StatisticsB2String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsB2StringOBMapper extends BaseMapper<StatisticsB2String> {
    @DS("OB")
    @Select("select * from cl_biz5_statistics_b2 where years=#{years} and times=#{times}")
    List<StatisticsB2String> selAllStatisticsB2OB(@Param("years") String years, @Param("times") String times);

    //总计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, " +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','0','总计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt)," +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2  " +
            "where years=#{years} AND times#{times} group by years, times, procDate;")
    Integer insertPolode0(@Param("years") String years, @Param("times") String times);

    //总颁险种合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','1','总颁险种合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where polFrom='总颁险种' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode1(@Param("years") String years, @Param("times") String times);

    //地方险种合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, " +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate) " +
            "SELECT years,times,'汇总','2','地方险种合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt), " +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where polFrom='地方险种' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode2(@Param("years") String years, @Param("times") String times);

    //主险合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','4','主险合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where mrType='M' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode3(@Param("years") String years, @Param("times") String times);

    //附加险合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','5','附加险合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where mrType!='M' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode4(@Param("years") String years, @Param("times") String times);

    //PICC1合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','6','PICC1合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where dateFrom!='PICC1' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode5(@Param("years") String years, @Param("times") String times);

    //PICC3合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','7','PICC3合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where dateFrom!='PICC3' and years=#{years} AND times#{times} group by years, times, procDate;")
    Integer insertPolode6(@Param("years") String years, @Param("times") String times);

    //自有系统合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','8','自有系统合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where dateFrom!='自有' and years=#{years} AND times#{times} group by years, times, procDate")
    Integer insertPolode7(@Param("years") String years, @Param("times") String times);

    //手工管理合计
    @DS("OB")
    @Select("INSERT INTO cl_biz5_statistics_b2(years,times,polFrom,polCode,polName,dateFrom,inPsCnt,inPsAmt,payRCnt,payRAmt,payCrDrMrCnt, \n" +
            "payCrDrMrAmt,payE9Cnt,payE9Amt,payEpCnt,payEpAmt,payRpRsCnt,payRpRsAmt,procDate)\n" +
            "SELECT years,times,'汇总','9','手工管理合计','汇总',sum(inPsCnt),sum(inPsAmt),sum(payRCnt),sum(payRAmt),sum(payCrDrMrCnt),\n" +
            "sum(payCrDrMrAmt),sum(payE9Cnt),sum(payE9Amt),sum(payEpCnt),sum(payEpAmt),sum(payRpRsCnt),sum(payRpRsAmt),procDate from cl_biz5_statistics_b2 where dateFrom!='手工' and years=#{years} AND times#{times} group by years, times, procDate;")
    Integer insertPolode8(@Param("years") String years, @Param("times") String times);

}
