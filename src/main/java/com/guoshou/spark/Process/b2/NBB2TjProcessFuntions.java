//package com.guoshou.spark.Process.b2;
//
////import com.baomidou.dynamic.datasource.annotation.DS;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.guoshou.spark.mapper.NB.*;
//import com.guoshou.spark.po.JavaNB.BizParameterString;
//import com.guoshou.spark.po.JavaNB.PolicyString;
//import com.guoshou.spark.po.JavaNB.StatisticsB1String;
//import com.guoshou.spark.po.JavaNB.StdContractString;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class NBB2TjProcessFuntions {
//
//    @Autowired
//    private StatisticsB2StringMapper statisticsB2StringMapper;
//
//    @Autowired
//    private BizParameterStringMapper bizParameterStringMapper;
//
//    @Autowired
//    private PolicyStringMapper policyStringMapper;
//
//    @Autowired
//    private StdContractStringMapper stdContractStringMapper;
//
//    @Autowired
//    private SvrGroupStringMapper svrGroupStringMapper;
//
//    @Autowired
//    private StatisticsB4StringMapper statisticsB4StringMapper;
//
//    @Autowired
//    private IfOfAltStringMapper ifOfAltStringMapper;
//
//
//    //----------------------------------------b1表处理----------------------------------------------------------------
//    public Integer InsertStatisticsB1(StatisticsB1String statisticsB1String){
//        return statisticsB1StringMapper.insert(statisticsB1String);
//    }
//
//
//    /**
//     * DelStatisticsB1DateFrom  删除 STATISTICS_B1 表中符合条件的记录
//     *  dateFrom 等于 'PICC1'或'手工' 或 '自有'
//     */
//    public int DelStatisticsB1DateFrom(List<String> dateFrom){
//        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .eq("dateFrom", dateFrom.get(0))// 等于条件
//                .or().eq("dateFrom", "手工")
//                .or().eq("dateFrom", "自有");
//
////        List<StatisticsB1String> statisticsB1Strings = statisticsB1StringMapper.selectList(queryWrapper);
////        for (StatisticsB1String statisticsB1String : statisticsB1Strings) {
////            System.out.println(statisticsB1String);
////        }
//        int delete = statisticsB1StringMapper.delete(queryWrapper);
//        return  delete;
//    }
//
//    /**
//     * 通过 year 和 times 查询 STATISTICS_B1 表中符合条件的记录数量，
//     */
//    public long cfdSelStatisticsB1ByYearAndTimes(int years,int times){
//        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .eq("years", years)// 等于条件
//                .eq("times", times);// 等于条件
//        Long aLong = statisticsB1StringMapper.selectCount(queryWrapper);
//        return aLong;
//    }
//
//    /**
//     * 通过 year 和 times 删除 STATISTICS_B1 表中符合条件的记录数量，
//     * @param years
//     * @param times
//     * @return
//     */
//    public int cfdDelStatisticsB1ByYearAndTimes(int years,int times){
//        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .eq("years", years)// 等于条件
//                .eq("times", times);// 等于条件
//        int delete = statisticsB1StringMapper.delete(queryWrapper);
//        System.out.println("cfdDelStatisticsB1ByYearAndTimes  方法删除了"+ delete +"条数据");
//        return delete;
//    }
//
//    /**
//     * CfdSelBizParameterObOrNbFlag  查询 BizParameter表中 bizParamCode字段等于 OBPSORNBPS_FLAG 的条数
//     * @return
//     */
//    public List<BizParameterString> CfdSelBizParameterObOrNbFlag(){
//        QueryWrapper<BizParameterString> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("bizParamCode","OBPSORNBPS_FLAG");
//        List<BizParameterString> bizParameterStrings = bizParameterStringMapper.selectList(queryWrapper);
//        return bizParameterStrings;
//    }
//
//    /**
//     * cfdSelPolicy  查询NB Policy 表对日期字段进行处理 日期值修改为 年-月-日
//     * 返回Policy中所有数据
//     * @return
//     */
//    public List<PolicyString> cfdSelPolicy(){
//        QueryWrapper<PolicyString> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("branchNo", "trim(polCode) as polCode", "chiefPolCode", "polNameEng", "polNameChn",
//                "SUBSTRING(polEffDate, 1, 10) as polEffDate","SUBSTRING(polExpDate, 1, 10) as polExpDate",
//                "businessType", "insurDurType", "yieldType", "premiumType", "botAgeLmt", "topAgeLmt",
//                "sexSaleTo", "moneyinItrvl", "moneyinType", "mrType", "uwRiskFact", "siRiskFact", "premAlgo",
//                "ageAlgo", "cashAlgo", "subpolOptionFlag", "coverOptionFlag", "faceAmntType,singleSalesFlag");
//        List<PolicyString> policyStrings = policyStringMapper.selectList(queryWrapper);
////        for (PolicyString policyString : policyStrings) {
////            System.out.println(policyString);
////        }
//        return policyStrings;
//    }
//
//    /**
//     * 根据 years 和times 以及 polCode  查询满足的 statisticsB1String
//     * @param statisticsB1String
//     * @return
//     * select * from 表 where years ='' and polCode='' and times ='2'
//     */
//    public List<StatisticsB1String> cfdSelStatisticsB1ByPolCode(StatisticsB1String statisticsB1String){
//        QueryWrapper<StatisticsB1String> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("polCode",statisticsB1String.getPolCode())
////                    .eq("years", statisticsB1String.getYears())
//                    .eq("times", statisticsB1String.getTimes());
//        System.out.println("-----select * from 表 where years ='' and polCode='' and times ='2'------");
//        if ("1".equals(statisticsB1String.getTimes())) {
////            if (statisticsB1String.getTimes() =="1") {
////            "select endCount,endNorCount,endExpCount,endLoseCount,endLoseStopCount"
//            System.out.println("2023");
//            queryWrapper.eq("years", statisticsB1String.getYears());
//        }else {
//            System.out.println("2022");
//            queryWrapper.eq("years", Integer.parseInt(statisticsB1String.getYears())-1);
//        }
//        List<StatisticsB1String> statisticsB1StringList = statisticsB1StringMapper.selectList(queryWrapper);
//        return statisticsB1StringList;
//    }
//
//    //取本次报表的期末数据
//    public void cfdSelEndOfTermDate(StatisticsB1String statisticsB1String,String startTime,String endTime){
//        long start = System.currentTimeMillis();
//        String polCode = statisticsB1String.getPolCode().trim();
//        long countaaa = 0;
//        long countbbb = 0;
//        long countccc = 0;
//        //期末保有
////        QueryWrapper<StdContractString> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("polCode",polCode);
////        Long aLong = stdContractStringMapper.selectCount(queryWrapper);
////        statisticsB1String.setEndCount(aLong+"");
////        System.out.println(statisticsB1String);
//        //期末有效
//        //select count(*) from cl_cntr4_std_contract where polCode ='F01' and cntrStat ='K' and STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')>'2023-12-31'
//        QueryWrapper<StdContractString> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("polCode",polCode)
//                .eq("cntrStat","K")
//                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')",endTime);
//        countaaa = stdContractStringMapper.selectCount(queryWrapper1);
//
////        QueryWrapper<SvrGroupString> queryWrapper2 = new QueryWrapper<>();   老写法
////        queryWrapper2
////                .eq("polCode", polCode)
////                .eq("cntrStat", "K")
////                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')",endTime)
////                .notExists("select * from cl_biz4_svr_group_lst where sgId = cl_biz4_svr_group.sgId");
////        countbbb = svrGroupStringMapper.selectCount(queryWrapper2);
//        //优化 SELECT COUNT(*) FROM cl_biz4_svr_group a LEFT JOIN cl_biz4_svr_group_lst b ON a.sgId = b.sgId
//        //WHERE a.polCode = "y06" AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= '2023-12-31' AND b.sgId IS NULL;
//        //SELECT COUNT(*) FROM cl_biz4_svr_group a LEFT JOIN cl_biz4_svr_group_lst b ON a.sgId = b.sgId
//        //WHERE a.polCode ="" AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= "" AND b.sgId IS NULL
//        countbbb = svrGroupStringMapper.selEndNorCountB(polCode,endTime);
//        statisticsB1String.setEndNorCount(countaaa+countbbb+"");
//
//        //期末满期
//        countaaa = stdContractStringMapper.selectCountA(polCode);
//        countbbb = stdContractStringMapper.selectCountB(polCode);
//        countccc = stdContractStringMapper.selectCountC(polCode);
//        statisticsB1String.setEndExpCount(countaaa+countbbb+countccc+"");
//
//        //期末失效
//        long l = stdContractStringMapper.selectCountEndLoseCount(polCode);
//        statisticsB1String.setEndLoseCount(l+"");
//
//        countaaa = stdContractStringMapper.selectCountEndLoseStopCount(polCode,endTime);
//        countbbb = stdContractStringMapper.selectCountEndLoseStopCountB(polCode,endTime);
//        statisticsB1String.setEndLoseStopCount(countaaa - countbbb+"");
//        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount())+
//                Long.parseLong(statisticsB1String.getEndExpCount())+Long.parseLong(statisticsB1String.getEndLoseCount())+Long.parseLong(statisticsB1String.getEndLoseStopCount())+"");
//        long end = System.currentTimeMillis();
//        System.out.println("cfdSelEndOfTermDate 耗时" +(end-start)+"ms");
//    }
//
//    //取本次报表的本期相关数据
//    public void cfdSelThisPeriodDate(StatisticsB1String statisticsB1String,String startTime,String endTime){
//        String polCode = statisticsB1String.getPolCode().trim();
//        String years = statisticsB1String.getYears().trim();
//        String times = statisticsB1String.getTimes().trim();
//        long countaaa = 0;
//        long countbbb = 0;
//        long countccc = 0;
//        //本期补录新增
//        long start = System.currentTimeMillis();
//        long nowRecNewCount = statisticsB4StringMapper.selectCountNowRecNewCount(polCode, years, times);
//        long end = System.currentTimeMillis();
//        statisticsB1String.setNowRecNewCount(nowRecNewCount+"");
//        System.out.println("selectCountNowRecNewCount花费时间"+(end-start)+"ms");
////
////        //本期复效  需优化
//        start = System.currentTimeMillis();
//        long countNowReinCount = ifOfAltStringMapper.selectCountNowReinCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        statisticsB1String.setNowReinCount(countNowReinCount+"");
//        System.out.println("selectCountNowReinCount  花费时间"+(end-start)+"ms");
////
////        //本期失效  selectCountNowLoseCount未执行
//        start = System.currentTimeMillis();
//        long loseCount = ifOfAltStringMapper.selectCountNowLoseCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        statisticsB1String.setNowLoseCount(loseCount+"");
//        System.out.println("selectCountNowLoseCount  花费时间"+(end-start)+"ms");
//
//        //本期满期
//        start = System.currentTimeMillis();
//        long nowExpCount1 = stdContractStringMapper.selectNowExpCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowExpCount  花费时间"+(end-start)+"ms");
//        start = System.currentTimeMillis();
//        long nowExpCount2 = stdContractStringMapper.selectNowExpCount1(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowExpCount1  花费时间"+(end-start)+"ms");
//        statisticsB1String.setNowExpCount(nowExpCount1+nowExpCount2+"");
//
//
//        //本期满期终止
//        start = System.currentTimeMillis();
//        long nowExpStopCount = stdContractStringMapper.selectNowExpStopCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowExpStopCount  花费时间"+(end-start)+"ms");
//        statisticsB1String.setNowExpStopCount(nowExpStopCount+"");
//        long newCount = Long.parseLong(statisticsB1String.getNowExpStopCount()) -
//                Long.parseLong(statisticsB1String.getNowExpCount());
//        statisticsB1String.setNowExpStopCount(newCount+"");
//
//        //本期解约终止
//        start = System.currentTimeMillis();
//        long nowSevStopCountA = stdContractStringMapper.selectNowSevStopCountA(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowSevStopCountA  花费时间"+(end-start)+"ms");
//        statisticsB1String.setNowSevStopCount(nowSevStopCountA+"");
//
//        //本期失效终止
//        start = System.currentTimeMillis();
//        long nowSevStopCount = stdContractStringMapper.selectNowSevStopCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowSevStopCount  花费时间"+(end-start)+"ms");
//        start = System.currentTimeMillis();
//        long nowSevStopCount1 = stdContractStringMapper.selectNowSevStopCount1(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowSevStopCount1  花费时间"+(end-start)+"ms");
//        statisticsB1String.setNowLoseStopCount(nowSevStopCount-nowSevStopCount1+"");
//
//        //本期其他终止
//        start = System.currentTimeMillis();
//        long nowOtherStopCount = stdContractStringMapper.selectNowOtherStopCount(polCode, startTime, endTime);
//        end = System.currentTimeMillis();
//        System.out.println("selectNowOtherStopCount  花费时间"+(end-start)+"ms");
//        statisticsB1String.setNowOtherStopCount(nowOtherStopCount+"");
//    }
//
//
//    /**
//     * 通过 year 和 times 查询 STATISTICS_B1 表中符合条件的记录数量，
//     * select * from  cl_biz4_statistics_b1 where years='2023' and times ='2' order by polFrom desc,polCode asc
//     */
//    public List<StatisticsB1String> SelStatisticsB1ByYearAndTimes(int years,int times){
//        List<StatisticsB1String> statisticsB1Strings = statisticsB1StringMapper.SelStatisticsB1ByYearAndTimes(years,times);
//        return statisticsB1Strings;
//    }
//
//    //----------------------------------------b2表处理----------------------------------------------------------------
//}
