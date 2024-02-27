package com.guoshou.spark.Process.OB;

//import com.baomidou.dynamic.datasource.annotation.DS;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.mapper.OB.*;
import com.guoshou.spark.po.Dto.CountAndSumResult;
import com.guoshou.spark.po.JavaOB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OBTjProcessFuntions {

    @Autowired
    private StatisticsB1StringOBMapper statisticsB1StringMapper;
    @Autowired
    private StatisticsB2StringOBMapper statisticsB2StringOBMapper;
    @Autowired
    private StatisticsB3StringOBMapper statisticsB3StringOBMapper;
    @Autowired
    private BizParameterStringOBMapper bizParameterStringMapper;
    @Autowired
    private CntrSubStateStringOBMapper cntrSubStateStringOBMapper;
    @Autowired
    private PolicyStringOBMapper policyStringMapper;

    @Autowired
    private StdContractStringOBMapper stdContractStringMapper;

    @Autowired
    private SvrGroupStringOBMapper svrGroupStringMapper;

    @Autowired
    private StatisticsB4StringOBMapper statisticsB4StringMapper;

    @Autowired
    private IfOfAltStringOBMapper ifOfAltStringMapper;

    @Autowired
    private MioLogStringOBMapper mioLogStringOBMapper;

    @DS("OB")
    public Integer InsertStatisticsB1(StatisticsB1String statisticsB1String) {
        return statisticsB1StringMapper.insert(statisticsB1String);
    }

//    @DS("NB")

    /**
     * DelStatisticsB1DateFrom  删除 STATISTICS_B1 表中符合条件的记录
     * dateFrom 等于 'PICC1'或'手工' 或 '自有'
     */
    public long DelStatisticsB1DateFrom(List<String> dateFrom) {
        Long delete = statisticsB1StringMapper.DelStatisticsB1DateFrom(dateFrom.get(1));
        if (delete == null) {
            delete = 0l;
        }
        System.out.println(delete + "------------------OB   DelStatisticsB1DateFrom 方法");
        return delete;
    }

    /**
     * 通过 year 和 times 查询 STATISTICS_B1 表中符合条件的记录数量，
     */
    public long cfdSelStatisticsB1ByYearAndTimes(int years, int times) {
        Long aLong = statisticsB1StringMapper.SelStatisticsB1ByYearAndTimes(years, times);
        System.out.println(aLong + "------------------OB   cfdSelStatisticsB1ByYearAndTimes 方法");
        return aLong;
    }

    /**
     * 通过 year 和 times 删除 STATISTICS_B1 表中符合条件的记录数量，
     *
     * @param years
     * @param times
     * @return
     */
    public long cfdDelStatisticsB1ByYearAndTimes(int years, int times) {
        Long delete = statisticsB1StringMapper.DelStatisticsB1ByYearAndTimes(years, times);
        if (delete ==null){
            delete = 0l;
        }
        return delete;
    }

    /**
     * CfdSelBizParameterObOrNbFlag  查询 BizParameter表中 bizParamCode字段等于 OBPSORNBPS_FLAG 的条数
     *
     * @return
     */
    public List<BizParameterString> SelBizParameterObOrNbFlag() {
        List<BizParameterString> bizParameterStrings = bizParameterStringMapper.SelBizParameterObOrNbFlag();

        System.out.println(bizParameterStrings.size() + "------------------OB   CfdSelBizParameterObOrNbFlag 方法");
        return bizParameterStrings;
    }

    /**
     * cfdSelPolicy  查询NB Policy 表对日期字段进行处理 日期值修改为 年-月-日
     * 返回Policy中所有数据
     *
     * @return
     */
    public List<PolicyString> cfdSelPolicy() {
        List<PolicyString> policyStrings = policyStringMapper.SelPolicy();
        return policyStrings;
    }

    /**
     * 根据 years 和times 以及 polCode  查询满足的 statisticsB1String
     *
     * @param statisticsB1String
     * @return
     */
    public List<StatisticsB1String> cfdSelStatisticsB1ByPolCode(StatisticsB1String statisticsB1String) {
        String polCode = statisticsB1String.getPolCode();
        String times = statisticsB1String.getTimes();
        String years = "";
//        if (statisticsB1String.getTimes() == "1") {
        if ("1".equals(statisticsB1String.getTimes())) {
            years = statisticsB1String.getYears();
        } else {
            years = Integer.parseInt(statisticsB1String.getYears()) - 1 + "";
        }
        List<StatisticsB1String> statisticsB1StringList = statisticsB1StringMapper.SelStatisticsB1ByPolCode(years, times, polCode);
        System.out.println(statisticsB1StringList.size());
        for (int i = 0; i < statisticsB1StringList.size(); i++) {
            System.out.println(statisticsB1StringList);
        }
        return statisticsB1StringList;
    }

    //取本次报表的期末数据
    @DS("OB")
    public void cfdSelEndOfTermDate(StatisticsB1String statisticsB1String, String startTime, String endTime) {
        long start = System.currentTimeMillis();
        String polCode = statisticsB1String.getPolCode().trim();
        long countaaa = 0;
        long countbbb = 0;
        long countccc = 0;
        long end = System.currentTimeMillis();
        //期末保有
//        Long aLong = stdContractStringMapper.selEndOfTermRetention(polCode);
//        statisticsB1String.setEndCount(aLong + "");
//        System.out.println("selEndOfTermRetention 花费时间" + (end - start) + "ms");

        //期末有效
        start = System.currentTimeMillis();
        countaaa = stdContractStringMapper.selEndNorCountA(polCode, endTime);
        end = System.currentTimeMillis();
        System.out.println("selEndNorCountA 花费时间" + (end - start) + "ms");

        start = System.currentTimeMillis();
        countbbb = svrGroupStringMapper.selEndNorCountB(polCode, endTime);
        end = System.currentTimeMillis();
        System.out.println("selEndNorCountB 花费时间" + (end - start) + "ms");
        statisticsB1String.setEndNorCount(countaaa + countbbb + "");

        //期末满期
        start = System.currentTimeMillis();
        countaaa = stdContractStringMapper.selectCountA(polCode);
        end = System.currentTimeMillis();
        System.out.println("selectCountA 花费时间" + (end - start) + "ms");

        start = System.currentTimeMillis();
        countbbb = stdContractStringMapper.selectCountB(polCode);
        end = System.currentTimeMillis();
        System.out.println("selectCountB 花费时间" + (end - start) + "ms");

        start = System.currentTimeMillis();
        countccc = stdContractStringMapper.selectCountC(polCode);
        end = System.currentTimeMillis();
        System.out.println("selectCountC 花费时间" + (end - start) + "ms");
        statisticsB1String.setEndExpCount(countaaa + countbbb + countccc + "");

        //期末失效
        start = System.currentTimeMillis();
        long l = stdContractStringMapper.selectCountEndLoseCount(polCode);
        end = System.currentTimeMillis();
        System.out.println("selectCountEndLoseCount 花费时间" + (end - start) + "ms");
        statisticsB1String.setEndLoseCount(l + "");

        start = System.currentTimeMillis();
        countaaa = stdContractStringMapper.selectCountEndLoseStopCount(polCode, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectCountEndLoseStopCount 花费时间" + (end - start) + "ms");

        start = System.currentTimeMillis();
        countbbb = stdContractStringMapper.selectCountEndLoseStopCountB(polCode, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectCountEndLoseStopCountB 花费时间" + (end - start) + "ms");

        statisticsB1String.setEndLoseStopCount(countaaa - countbbb + "");
        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount())+
                Long.parseLong(statisticsB1String.getEndExpCount())+Long.parseLong(statisticsB1String.getEndLoseCount())+Long.parseLong(statisticsB1String.getEndLoseStopCount())+"");
    }

    //取本次报表的本期相关数据
    public void cfdSelThisPeriodDate(StatisticsB1String statisticsB1String, String startTime, String endTime) {
        String polCode = statisticsB1String.getPolCode().trim();
        String years = statisticsB1String.getYears().trim();
        String times = statisticsB1String.getTimes().trim();
        //本期补录新增
        long start = System.currentTimeMillis();
        System.out.println(polCode);
        System.out.println(years);
        System.out.println(times);
        long nowRecNewCount = statisticsB4StringMapper.selectCountNowRecNewCount(polCode, years, times);
        long end = System.currentTimeMillis();
        statisticsB1String.setNowRecNewCount(nowRecNewCount + "");
        System.out.println("selectCountNowRecNewCount 花费时间" + (end - start) + "ms" + "    数量为"+nowRecNewCount);

//        //本期复效
        start = System.currentTimeMillis();
        long countNowReinCount = ifOfAltStringMapper.selectCountNowReinCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        statisticsB1String.setNowReinCount(countNowReinCount + "");
        System.out.println("selectCountNowReinCount  花费时间" + (end - start) + "ms" +"    数量为"+ countNowReinCount);
//
//        //本期失效  selectCountNowLoseCount未执行
        start = System.currentTimeMillis();
        long loseCount = ifOfAltStringMapper.selectCountNowLoseCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        statisticsB1String.setNowLoseCount(loseCount + "");
        System.out.println("selectCountNowLoseCount  花费时间" + (end - start) + "ms");

        //本期满期
        start = System.currentTimeMillis();
        long nowExpCount1 = stdContractStringMapper.selectNowExpCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        start = System.currentTimeMillis();
        long nowExpCount2 = stdContractStringMapper.selectNowExpCount1(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        statisticsB1String.setNowExpCount(nowExpCount1 + nowExpCount2 + "");


        //本期满期终止
        start = System.currentTimeMillis();
        long nowExpStopCount = stdContractStringMapper.selectNowExpStopCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowExpStopCount  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowExpStopCount(nowExpStopCount + "");
        long newCount = Long.parseLong(statisticsB1String.getNowExpStopCount()) - Long.parseLong(statisticsB1String.getNowExpCount());
        statisticsB1String.setNowExpStopCount(newCount + "");

        //本期解约终止
        start = System.currentTimeMillis();
        long nowSevStopCountA = stdContractStringMapper.selectNowSevStopCountA(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowSevStopCountA  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowSevStopCount(nowSevStopCountA + "");

        //本期失效终止
        start = System.currentTimeMillis();
        long nowSevStopCount = stdContractStringMapper.selectNowSevStopCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowSevStopCount  花费时间" + (end - start) + "ms");
        start = System.currentTimeMillis();
        long nowSevStopCount1 = stdContractStringMapper.selectNowSevStopCount1(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowSevStopCount1  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowLoseStopCount(nowSevStopCount - nowSevStopCount1 + "");

        //本期其他终止
        start = System.currentTimeMillis();
        long nowOtherStopCount = stdContractStringMapper.selectNowOtherStopCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowOtherStopCount  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowOtherStopCount(nowOtherStopCount + "");
    }


    //----------------------------------------b2表处理----------------------------------------------------------------

    /**
     * select count(*) from cl_biz4_statistics_b2 where years ='2023' and times ='2'
     *
     * @param years
     * @param times
     * @return
     */
    @DS("OB")
    public long cfdSelStatisticsB2ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB2String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        Long aLong = statisticsB2StringOBMapper.selectCount(queryWrapper);
        return aLong;
    }

    /**
     * delete from cl_biz4_statistics_b2 where years ='2023' and times ='2'
     *
     * @param years
     * @param times
     * @return
     */
    @DS("OB")
    public int cfdDelStatisticsB2ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB2String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        int delete = statisticsB2StringOBMapper.delete(queryWrapper);
        return delete;
    }

    @DS("OB")
    public void selMioLogByMioItemCodeAndPolCode(StatisticsB2String statisticsB2String, String startTime, String endTime) {
        String polCode = statisticsB2String.getPolCode().trim();
        CountAndSumResult countAndSum = mioLogStringOBMapper.inPsCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setInPsCnt(countAndSum.getCounts() + "");
        statisticsB2String.setInPsAmt(countAndSum.getAmntSums());
        String mrType = statisticsB2String.getMrType();
        if (!("M".equals(mrType))) {
            CountAndSumResult payRCntAndAmt = mioLogStringOBMapper.payRCntAndAmt(polCode, startTime, endTime);
            statisticsB2String.setPayRCnt(payRCntAndAmt.getCounts() + "");
            statisticsB2String.setPayRAmt(payRCntAndAmt.getAmntSums());
            statisticsB2String.setPayCrDrMrCnt("0");
            statisticsB2String.setPayCrDrMrAmt("0");
        } else {
            CountAndSumResult payCrDrMrCntAndAmt = mioLogStringOBMapper.payCrDrMrCntAndAmt(polCode, startTime, endTime);
            statisticsB2String.setPayCrDrMrCnt(payCrDrMrCntAndAmt.getCounts() + "");
            statisticsB2String.setPayCrDrMrAmt(payCrDrMrCntAndAmt.getAmntSums());
            statisticsB2String.setPayRCnt("0");
            statisticsB2String.setPayRAmt("0");
        }
        CountAndSumResult payE9CntAndAmt = mioLogStringOBMapper.payE9CntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayE9Cnt(payE9CntAndAmt.getCounts() + "");
        statisticsB2String.setPayE9Amt(payE9CntAndAmt.getAmntSums());
        CountAndSumResult payEPCntAndAmt = mioLogStringOBMapper.payEPCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayEpCnt(payEPCntAndAmt.getCounts() + "");
        statisticsB2String.setPayEpAmt(payEPCntAndAmt.getAmntSums());

        CountAndSumResult payRpRsCntAndAmt = mioLogStringOBMapper.payRpRsCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayRpRsAmt(payRpRsCntAndAmt.getAmntSums());
        statisticsB2String.setPayRpRsCnt(payRpRsCntAndAmt.getCounts() + "");
        System.out.println(statisticsB2String);
        int insert = statisticsB2StringOBMapper.insert(statisticsB2String);
        System.out.println("OB插入了"+insert+"条数据");
    }


    //----------------------------------------b3表处理----------------------------------------------------------------
    @DS("OB")
    public long cfdSelStatisticsB3ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB3String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        Long aLong = statisticsB3StringOBMapper.selectCount(queryWrapper);
        return aLong;
    }

    @DS("OB")
    public int cfdDelStatisticsB3ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB3String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        int delete = statisticsB3StringOBMapper.delete(queryWrapper);
        return delete;
    }

    @DS("OB")
    public void selMioLogByMioItemCodeAndPolCodeIII(StatisticsB3String statisticsB3String, String startTime, String endTime) {
        String polCode = statisticsB3String.getPolCode().trim();
        CountAndSumResult payRCntAndAmtB3 = mioLogStringOBMapper.payRCntAndAmtB3(polCode, startTime, endTime);
        statisticsB3String.setPayRCnt(payRCntAndAmtB3.getCounts()+"");
        statisticsB3String.setPayRAmt(payRCntAndAmtB3.getAmntSums());
        CountAndSumResult payXCntAndAmtB3 = mioLogStringOBMapper.payXCntAndAmtB3(polCode, startTime, endTime);
        statisticsB3String.setPayXCnt(payXCntAndAmtB3.getCounts()+"");
        statisticsB3String.setPayXAmt(payXCntAndAmtB3.getAmntSums());
        Long surXAmt1 = cntrSubStateStringOBMapper.surXAmt1(polCode);
        Long surXAmt = mioLogStringOBMapper.surXAmt(polCode, endTime);
        statisticsB3String.setSurXAmt(surXAmt1-surXAmt +"");
        System.out.println(statisticsB3String);
        int insert = statisticsB3StringOBMapper.insert(statisticsB3String);
        System.out.println("b3插入了"+insert+"条数据");
    }
}
