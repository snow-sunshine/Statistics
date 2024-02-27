package com.guoshou.spark.Process;

//import com.baomidou.dynamic.datasource.annotation.DS;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.mapper.NB.*;
import com.guoshou.spark.po.Dto.CountAndSumResult;
import com.guoshou.spark.po.JavaNB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NBTjProcessFuntions {

    @Autowired
    private StatisticsB1StringMapper statisticsB1StringMapper;
    @Autowired
    private StatisticsB2StringMapper statisticsB2StringMapper;
    @Autowired
    private StatisticsB3StringMapper statisticsB3StringMapper;
    @Autowired
    private BizParameterStringMapper bizParameterStringMapper;
    @Autowired
    private CntrSubStateStringMapper cntrSubStateStringMapper;
    @Autowired
    private PolicyStringMapper policyStringMapper;

    @Autowired
    private StdContractStringMapper stdContractStringMapper;

    @Autowired
    private SvrGroupStringMapper svrGroupStringMapper;

    @Autowired
    private StatisticsB4StringMapper statisticsB4StringMapper;

    @Autowired
    private IfOfAltStringMapper ifOfAltStringMapper;

    @Autowired
    private MioLogStringMapper mioLogStringMapper;


    //----------------------------------------b1表处理----------------------------------------------------------------
    public Integer InsertStatisticsB1(StatisticsB1String statisticsB1String) {
        return statisticsB1StringMapper.insert(statisticsB1String);
    }


    /**
     * DelStatisticsB1DateFrom  删除 STATISTICS_B1 表中符合条件的记录
     * dateFrom 等于 'PICC1'或'手工' 或 '自有'
     * delete from cl_biz4_statistics_b1 where dateFrom='' or dateFrom='手工' or dateFrom='自有'
     */
    public int DelStatisticsB1DateFrom(List<String> dateFrom) {
        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("dateFrom", dateFrom.get(0))// 等于条件
                .or().eq("dateFrom", "手工")
                .or().eq("dateFrom", "自有");

//        List<StatisticsB1String> statisticsB1Strings = statisticsB1StringMapper.selectList(queryWrapper);
//        for (StatisticsB1String statisticsB1String : statisticsB1Strings) {
//            System.out.println(statisticsB1String);
//        }
        int delete = statisticsB1StringMapper.delete(queryWrapper);
        return delete;
    }

    /**
     * 通过 year 和 times 查询 STATISTICS_B1 表中符合条件的记录数量，
     * select count(*) from cl_biz4_statistics_b1 where years ='' and times =''
     */
    public long cfdSelStatisticsB1ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        Long aLong = statisticsB1StringMapper.selectCount(queryWrapper);
        return aLong;
    }

    /**
     * 通过 year 和 times 删除 STATISTICS_B1 表中符合条件的记录数量，
     *
     * @param years
     * @param times
     * @return
     */
    public int cfdDelStatisticsB1ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        int delete = statisticsB1StringMapper.delete(queryWrapper);
        System.out.println("cfdDelStatisticsB1ByYearAndTimes  方法删除了" + delete + "条数据");
        return delete;
    }

    /**
     * CfdSelBizParameterObOrNbFlag  查询 BizParameter表中 bizParamCode字段等于 OBPSORNBPS_FLAG 的条数
     *select * from biz_proc4_biz_parameter where bizParamCode = 'OBPSORNBPS_FLAG'
     * @return
     */
    public List<BizParameterString> CfdSelBizParameterObOrNbFlag() {
        QueryWrapper<BizParameterString> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bizParamCode", "OBPSORNBPS_FLAG");
        List<BizParameterString> bizParameterStrings = bizParameterStringMapper.selectList(queryWrapper);
        return bizParameterStrings;
    }

    /**
     * cfdSelPolicy  查询NB Policy 表对日期字段进行处理 日期值修改为 年-月-日
     * 返回Policy中所有数据
     *
     * @return
     */
    public List<PolicyString> cfdSelPolicy() {
        QueryWrapper<PolicyString> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("branchNo", "trim(polCode) as polCode", "chiefPolCode", "polNameEng", "polNameChn",
                "SUBSTRING(polEffDate, 1, 10) as polEffDate", "SUBSTRING(polExpDate, 1, 10) as polExpDate",
                "businessType", "insurDurType", "yieldType", "premiumType", "botAgeLmt", "topAgeLmt",
                "sexSaleTo", "moneyinItrvl", "moneyinType", "mrType", "uwRiskFact", "siRiskFact", "premAlgo",
                "ageAlgo", "cashAlgo", "subpolOptionFlag", "coverOptionFlag", "faceAmntType,singleSalesFlag");
        List<PolicyString> policyStrings = policyStringMapper.selectList(queryWrapper);
//        for (PolicyString policyString : policyStrings) {
//            System.out.println(policyString);
//        }
        return policyStrings;
    }

    /**
     * 根据 years 和times 以及 polCode  查询满足的 statisticsB1String
     *
     * @param statisticsB1String
     * @return select * from 表 where years ='' and polCode='' and times ='2'
     */
    public List<StatisticsB1String> cfdSelStatisticsB1ByPolCode(StatisticsB1String statisticsB1String) {
        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("polCode", statisticsB1String.getPolCode())
//                    .eq("years", statisticsB1String.getYears())
                .eq("times", statisticsB1String.getTimes());
        System.out.println("-----select * from 表 where years ='' and polCode='' and times ='2'------");
        if ("1".equals(statisticsB1String.getTimes())) {
//            if (statisticsB1String.getTimes() =="1") {
//            "select endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount"
            System.out.println("2023");
            queryWrapper.eq("years", statisticsB1String.getYears());
        } else {
            System.out.println("2022");
            queryWrapper.eq("years", Integer.parseInt(statisticsB1String.getYears()) - 1);
        }
        List<StatisticsB1String> statisticsB1StringList = statisticsB1StringMapper.selectList(queryWrapper);
        return statisticsB1StringList;
    }

    //取本次报表的期末数据
    public void cfdSelEndOfTermDate(StatisticsB1String statisticsB1String, String startTime, String endTime) {
        long start = System.currentTimeMillis();
        String polCode = statisticsB1String.getPolCode().trim();
        long countaaa = 0;
        long countbbb = 0;
        long countccc = 0;
        //期末保有
//        QueryWrapper<StdContractString> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("polCode",polCode);
//        Long aLong = stdContractStringMapper.selectCount(queryWrapper);
//        statisticsB1String.setEndCount(aLong+"");
//        System.out.println(statisticsB1String);
        //期末有效
        //select count(*) from cl_cntr4_std_contract where polCode ='F01' and cntrStat ='K' and STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')>'2023-12-31'
        QueryWrapper<StdContractString> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("polCode", polCode)
                .eq("cntrStat", "K")
                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')", endTime);
        countaaa = stdContractStringMapper.selectCount(queryWrapper1);

//        QueryWrapper<SvrGroupString> queryWrapper2 = new QueryWrapper<>();   老写法
//        queryWrapper2
//                .eq("polCode", polCode)
//                .eq("cntrStat", "K")
//                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')",endTime)
//                .notExists("select * from cl_biz4_svr_group_lst where sgId = cl_biz4_svr_group.sgId");
//        countbbb = svrGroupStringMapper.selectCount(queryWrapper2);
        //优化 SELECT COUNT(*) FROM cl_biz4_svr_group a LEFT JOIN cl_biz4_svr_group_lst b ON a.sgId = b.sgId
        //WHERE a.polCode = "y06" AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= '2023-12-31' AND b.sgId IS NULL;
        //SELECT COUNT(*) FROM cl_biz4_svr_group a LEFT JOIN cl_biz4_svr_group_lst b ON a.sgId = b.sgId
        //WHERE a.polCode ="" AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= "" AND b.sgId IS NULL
        countbbb = svrGroupStringMapper.selEndNorCountB(polCode, endTime);
        statisticsB1String.setEndNorCount(countaaa + countbbb + "");

        //期末满期
        countaaa = stdContractStringMapper.selectCountA(polCode);
        countbbb = stdContractStringMapper.selectCountB(polCode);
        countccc = stdContractStringMapper.selectCountC(polCode);
        statisticsB1String.setEndExpCount(countaaa + countbbb + countccc + "");

        //期末失效
        long l = stdContractStringMapper.selectCountEndLoseCount(polCode);
        statisticsB1String.setEndLoseCount(l + "");

        countaaa = stdContractStringMapper.selectCountEndLoseStopCount(polCode, endTime);
        countbbb = stdContractStringMapper.selectCountEndLoseStopCountB(polCode, endTime);
        statisticsB1String.setEndLoseStopCount(countaaa - countbbb + "");
        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount()) +
                Long.parseLong(statisticsB1String.getEndExpCount()) + Long.parseLong(statisticsB1String.getEndLoseCount()) + Long.parseLong(statisticsB1String.getEndLoseStopCount()) + "");
        long end = System.currentTimeMillis();
        System.out.println("cfdSelEndOfTermDate 耗时" + (end - start) + "ms");
    }

    //取本次报表的本期相关数据
    public void cfdSelThisPeriodDate(StatisticsB1String statisticsB1String, String startTime, String endTime) {
        String polCode = statisticsB1String.getPolCode().trim();
        String years = statisticsB1String.getYears().trim();
        String times = statisticsB1String.getTimes().trim();
        long countaaa = 0;
        long countbbb = 0;
        long countccc = 0;
        //本期补录新增
        long start = System.currentTimeMillis();
        long nowRecNewCount = statisticsB4StringMapper.selectCountNowRecNewCount(polCode, years, times);
        long end = System.currentTimeMillis();
        statisticsB1String.setNowRecNewCount(nowRecNewCount + "");
        System.out.println("selectCountNowRecNewCount花费时间" + (end - start) + "ms");
//
//        //本期复效  需优化
        start = System.currentTimeMillis();
        long countNowReinCount = ifOfAltStringMapper.selectCountNowReinCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        statisticsB1String.setNowReinCount(countNowReinCount + "");
        System.out.println("selectCountNowReinCount  花费时间" + (end - start) + "ms");
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
        System.out.println("selectNowExpCount  花费时间" + (end - start) + "ms");
        start = System.currentTimeMillis();
        long nowExpCount2 = stdContractStringMapper.selectNowExpCount1(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowExpCount1  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowExpCount(nowExpCount1 + nowExpCount2 + "");


        //本期满期终止
        start = System.currentTimeMillis();
        long nowExpStopCount = stdContractStringMapper.selectNowExpStopCount(polCode, startTime, endTime);
        end = System.currentTimeMillis();
        System.out.println("selectNowExpStopCount  花费时间" + (end - start) + "ms");
        statisticsB1String.setNowExpStopCount(nowExpStopCount + "");
        long newCount = Long.parseLong(statisticsB1String.getNowExpStopCount()) -
                Long.parseLong(statisticsB1String.getNowExpCount());
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


    /**
     * 通过 year 和 times 查询 STATISTICS_B1 表中符合条件的记录数量，
     * select * from  cl_biz4_statistics_b1 where years='2023' and times ='2' order by polFrom desc,polCode asc
     */
    public List<StatisticsB1String> SelStatisticsB1ByYearAndTimes(int years, int times) {
        List<StatisticsB1String> statisticsB1Strings = statisticsB1StringMapper.SelStatisticsB1ByYearAndTimes(years, times);
        return statisticsB1Strings;
    }

    //----------------------------------------b2表处理----------------------------------------------------------------

    /**
     * select count(*) from cl_biz4_statistics_b2 where years ='2023' and times ='2'
     *
     * @param years
     * @param times
     * @return
     */
    public long cfdSelStatisticsB2ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB2String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        Long aLong = statisticsB2StringMapper.selectCount(queryWrapper);
        return aLong;
    }

    /**
     * delete from cl_biz4_statistics_b2 where years ='2023' and times ='2'
     *
     * @param years
     * @param times
     * @return
     */
    public int cfdDelStatisticsB2ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB2String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        int delete = statisticsB2StringMapper.delete(queryWrapper);
        return delete;
    }

    public void selMioLogByMioItemCodeAndPolCode(StatisticsB2String statisticsB2String, String startTime, String endTime) {
        String polCode = statisticsB2String.getPolCode().trim();
        CountAndSumResult countAndSum = mioLogStringMapper.inPsCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setInPsCnt(countAndSum.getCounts() + "");
        statisticsB2String.setInPsAmt(countAndSum.getAmntSums());
        String mrType = statisticsB2String.getMrType();
        if (!("M".equals(mrType))) {
            CountAndSumResult payRCntAndAmt = mioLogStringMapper.payRCntAndAmt(polCode, startTime, endTime);
            System.out.println(polCode+"------"+payRCntAndAmt+"------"+mrType+"------payRCntAndAmt------");
            statisticsB2String.setPayRCnt(payRCntAndAmt.getCounts() + "");
            statisticsB2String.setPayRAmt(payRCntAndAmt.getAmntSums());
            statisticsB2String.setPayCrDrMrCnt("0");
            statisticsB2String.setPayCrDrMrAmt("0");
        } else {
            CountAndSumResult payCrDrMrCntAndAmt = mioLogStringMapper.payCrDrMrCntAndAmt(polCode, startTime, endTime);
            System.out.println(polCode+"------"+payCrDrMrCntAndAmt+"------"+mrType+"------payCrDrMrCntAndAmt------");
            statisticsB2String.setPayCrDrMrCnt(payCrDrMrCntAndAmt.getCounts() + "");
            statisticsB2String.setPayCrDrMrAmt(payCrDrMrCntAndAmt.getAmntSums());
            statisticsB2String.setPayRCnt("0");
            statisticsB2String.setPayRAmt("0");
        }
        CountAndSumResult payE9CntAndAmt = mioLogStringMapper.payE9CntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayE9Cnt(payE9CntAndAmt.getCounts() + "");
        statisticsB2String.setPayE9Amt(payE9CntAndAmt.getAmntSums());
        CountAndSumResult payEPCntAndAmt = mioLogStringMapper.payEPCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayEpCnt(payEPCntAndAmt.getCounts() + "");
        statisticsB2String.setPayEpAmt(payEPCntAndAmt.getAmntSums());

        CountAndSumResult payRpRsCntAndAmt = mioLogStringMapper.payRpRsCntAndAmt(polCode, startTime, endTime);
        statisticsB2String.setPayRpRsAmt(payRpRsCntAndAmt.getAmntSums());
        statisticsB2String.setPayRpRsCnt(payRpRsCntAndAmt.getCounts() + "");
        int insert = statisticsB2StringMapper.insert(statisticsB2String);
        System.out.println("插入了"+insert+"条数据");
    }

    public List<StatisticsB2String> SelStatisticsB2ByYearAndTimes(int years, int times) {
        List<StatisticsB2String> statisticsB2Strings = statisticsB2StringMapper.SelStatisticsB2ByYearAndTimes(years, times);
        return statisticsB2Strings;
    }


    //----------------------------------------b3表处理----------------------------------------------------------------
    public long cfdSelStatisticsB3ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB3String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        Long aLong = statisticsB3StringMapper.selectCount(queryWrapper);
        return aLong;
    }

    public int cfdDelStatisticsB3ByYearAndTimes(int years, int times) {
        QueryWrapper<StatisticsB3String> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("years", years)// 等于条件
                .eq("times", times);// 等于条件
        int delete = statisticsB3StringMapper.delete(queryWrapper);
        return delete;
    }

    public void selMioLogByMioItemCodeAndPolCodeIII(StatisticsB3String statisticsB3String, String startTime, String endTime) {
        String polCode = statisticsB3String.getPolCode().trim();
        CountAndSumResult payRCntAndAmtB3 = mioLogStringMapper.payRCntAndAmtB3(polCode, startTime, endTime);
        statisticsB3String.setPayRCnt(payRCntAndAmtB3.getCounts()+"");
        statisticsB3String.setPayRAmt(payRCntAndAmtB3.getAmntSums());
        CountAndSumResult payXCntAndAmtB3 = mioLogStringMapper.payXCntAndAmtB3(polCode, startTime, endTime);
        statisticsB3String.setPayXCnt(payXCntAndAmtB3.getCounts()+"");
        statisticsB3String.setPayXAmt(payXCntAndAmtB3.getAmntSums());
        Long surXAmt1 = cntrSubStateStringMapper.surXAmt1(polCode);
        Long surXAmt = mioLogStringMapper.surXAmt(polCode, endTime);
        statisticsB3String.setSurXAmt(surXAmt1-surXAmt +"");
        System.out.println(statisticsB3String);
        int insert = statisticsB3StringMapper.insert(statisticsB3String);
        System.out.println("b3插入了"+insert+"条数据");
    }

    public List<StatisticsB3String> SelStatisticsB3ByYearAndTimes(int years, int times) {
        List<StatisticsB3String> statisticsB3Strings = statisticsB3StringMapper.SelStatisticsB3ByYearAndTimes(years, times);
        return statisticsB3Strings;
    }
}
