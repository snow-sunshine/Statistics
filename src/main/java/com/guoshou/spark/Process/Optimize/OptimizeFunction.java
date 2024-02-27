//package com.guoshou.spark.Process.Optimize;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.guoshou.spark.mapper.NB.StdContractStringMapper;
//import com.guoshou.spark.mapper.NB.SvrGroupStringMapper;
//import com.guoshou.spark.po.JavaNB.StatisticsB1String;
//import com.guoshou.spark.po.JavaNB.StdContractString;
//import com.guoshou.spark.po.JavaNB.SvrGroupString;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OptimizeFunction {
//    @Autowired
//    private StdContractStringMapper stdContractStringMapper;
//    @Autowired
//    private SvrGroupStringMapper svrGroupStringMapper;
//
//    public void OptimizeSelEndOfTermDate(StatisticsB1String statisticsB1String, String startTime, String endTime) {
//        long start = System.currentTimeMillis();
//        String polCode = statisticsB1String.getPolCode().trim();
//        long countaaa = 0;
//        long countbbb = 0;
//        long countccc = 0;
//        //期末保有
//        QueryWrapper<StdContractString> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("polCode", polCode);
//        Long aLong = stdContractStringMapper.selectCount(queryWrapper);
//        statisticsB1String.setEndCount(aLong + "");
////        System.out.println(statisticsB1String);
//        //期末有效
//        QueryWrapper<StdContractString> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("polCode", polCode)
//                .eq("cntrStat", "K")
////                .le("cntrExpiryDate",endTime);
//                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')", endTime);
//        countaaa = stdContractStringMapper.selectCount(queryWrapper1);
//
//        QueryWrapper<SvrGroupString> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2
//                .eq("polCode", polCode)
//                .eq("cntrStat", "K")
//                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')", endTime)
//                .notExists("select * from cl_biz4_svr_group_lst where sgId = cl_biz4_svr_group.sgId");
//        countbbb = svrGroupStringMapper.selectCount(queryWrapper2);
//        statisticsB1String.setEndNorCount(countaaa + countbbb + "");
//
//        //期末满期
//        countaaa = stdContractStringMapper.selectCountA(polCode);
//        countbbb = stdContractStringMapper.selectCountB(polCode);
//        countccc = stdContractStringMapper.selectCountC(polCode);
//        statisticsB1String.setEndExpCount(countaaa + countbbb + countccc + "");
//
//        //期末失效
//        long l = stdContractStringMapper.selectCountEndLoseCount(polCode);
//        statisticsB1String.setEndLoseCount(l + "");
//
//        countaaa = stdContractStringMapper.selectCountEndLoseStopCount(polCode, endTime);
//        countbbb = stdContractStringMapper.selectCountEndLoseStopCountB(polCode, endTime);
//        statisticsB1String.setEndLoseStopCount(countaaa - countbbb + "");
//        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount()) +
//                Long.parseLong(statisticsB1String.getEndExpCount()) + Long.parseLong(statisticsB1String.getEndLoseCount()) + Long.parseLong(statisticsB1String.getEndLoseStopCount()) + "");
//        long end = System.currentTimeMillis();
//        System.out.println("cfdSelEndOfTermDate 耗时" + (end - start) + "ms");
//    }
//}
