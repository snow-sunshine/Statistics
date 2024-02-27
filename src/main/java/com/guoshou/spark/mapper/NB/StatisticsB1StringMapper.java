package com.guoshou.spark.mapper.NB;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsB1StringMapper extends BaseMapper<StatisticsB1String> {

    @Select("select * from  cl_biz4_statistics_b1 where years=#{years} and times =#{times} order by polFrom desc,polCode asc \n")
    List<StatisticsB1String> SelStatisticsB1ByYearAndTimes(@Param("years") int years, @Param("times") int times);


    //总计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate) \n" +
            "SELECT years,times,'汇总','0','总计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE years = #{years} AND times = #{times} GROUP BY years,times,procDate;\n")
    Integer insertPolode0(@Param("years") String years, @Param("times") String times);

    //总颁险种合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)" +
            "SELECT years,times,'汇总','1','总颁险种合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE polFrom='总颁险种' and years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode1(@Param("years") String years, @Param("times") String times);

    //地方险种合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)   \n" +
            "SELECT years,times,'汇总','2','地方险种合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE polFrom='地方险种' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode2(@Param("years") String years, @Param("times") String times);

    //主险合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate) \n" +
            "SELECT years,times,'汇总','4','主险合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE mrType='M' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode4(@Param("years") String years, @Param("times") String times);

    //附加险合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)  \n" +
            "SELECT years,times,'汇总','5','附加险合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE mrType<>'M' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode5(@Param("years") String years, @Param("times") String times);

    //
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)\n" +
            " SELECT years,times,'汇总','6','PICC1合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE dateFrom='PICC1' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode6(@Param("years") String years, @Param("times") String times);

    //PICC3合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate) \n" +
            "SELECT years,times,'汇总','7','PICC3合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE dateFrom='PICC3' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;\n")
    Integer insertPolode7(@Param("years") String years, @Param("times") String times);

    //自有系统合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)\n" +
            "SELECT years,times,'汇总','8','自有系统合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE dateFrom='自有' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode8(@Param("years") String years, @Param("times") String times);

    //手工管理合计
    @Select("INSERT INTO tj.cl_biz4_statistics_b1 (years,times,polFrom,polCode,polName,dateFrom,begCount,begNorCount,begExpCount,begLoseCount,begLoseStopCount,nowRecNewCount,nowReinCount,\n" +
            "nowLoseCount,nowExpCount,nowExpStopCount,nowSevStopCount,nowLoseStopCount,nowOtherStopCount,endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount,procDate)\n" +
            "SELECT years,times,'汇总','9','手工管理合计','汇总',SUM(begCount),SUM(begNorCount),SUM(begExpCount),SUM(begLoseCount),SUM(begLoseStopCount),SUM(nowRecNewCount),\n" +
            "SUM(nowReinCount),SUM(nowLoseCount),SUM(nowExpCount),SUM(nowExpStopCount),SUM(nowSevStopCount),SUM(nowLoseStopCount),SUM(nowOtherStopCount),SUM(endCount),SUM(endNorCount),\n" +
            "SUM(endExpCount),SUM(endLoseCount),SUM(endLoseStopCount),procDate FROM tj.cl_biz4_statistics_b1 WHERE dateFrom='手工' and  years = #{years} AND times = #{times} GROUP BY years,times,procDate;")
    Integer insertPolode9(@Param("years") String years, @Param("times") String times);


}
