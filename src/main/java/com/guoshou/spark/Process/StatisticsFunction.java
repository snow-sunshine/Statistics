package com.guoshou.spark.Process;


import com.guoshou.spark.config.PublicFunctions;
import com.guoshou.spark.po.JavaNB.PolicyString;
import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import com.guoshou.spark.po.JavaNB.StatisticsB2String;
import com.guoshou.spark.po.JavaNB.StatisticsB3String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsFunction {

    @Autowired
    private NBTjProcessFuntions NBTjProcessFuntions;

    public void StatisticsB1(int years, int times, String obpsOrNbpsFlag) {
        long counts;
        //获取时间范围
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);

        //查询符合条数
        counts = NBTjProcessFuntions.cfdSelStatisticsB1ByYearAndTimes(years, times);
        System.out.println("查询statistics_b1 中满足条件的个数为" + counts);

        if (counts > 0) {
            NBTjProcessFuntions.cfdDelStatisticsB1ByYearAndTimes(years, times);
        }
        long l = System.currentTimeMillis();
        String currentDateTime = PublicFunctions.getCurrentDateTime(l);
        List<PolicyString> policyStrings = NBTjProcessFuntions.cfdSelPolicy();
        System.out.println("NB Policy 表总条数" + policyStrings.size());
        if (policyStrings.size() > 0) {
            for (PolicyString policyString : policyStrings) {
                System.out.println(policyString);
                StatisticsB1String statisticsB1String = new StatisticsB1String();
                statisticsB1String.setYears(years + "");
                statisticsB1String.setTimes(times + "");
                statisticsB1String.setProcDate(currentDateTime);
                if ("!!!!!!".equals(policyString.getBranchNo())) {
                    statisticsB1String.setPolFrom("总颁险种");
                } else {
                    statisticsB1String.setPolFrom("地方险种");
                }
                if (obpsOrNbpsFlag.startsWith("N")) {
                    statisticsB1String.setDateFrom("PICC3");
                } else {
                    statisticsB1String.setDateFrom("PICC1");
                }
                statisticsB1String.setPolCode(policyString.getPolCode().trim());
                statisticsB1String.setPolName(policyString.getPolNameChn().trim());
                String policyMrType = policyString.getMrType();
//                if (policyMrType != "M") {
//                    statisticsB1String.setMrType("A");
//                }
                if ("M".equals(policyMrType)) {
                    statisticsB1String.setMrType("M");
                } else {
                    statisticsB1String.setMrType("A");
                }
                //更新
                if ("L".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("1");
                } else if ("H".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("4");
                } else if ("D".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("2");
                } else if ("P".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("8");
                } else if ("S".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("6");
                } else {
                    policyString.setBusinessType("0");
                }
                List<StatisticsB1String> statisticsB1Strings = NBTjProcessFuntions.cfdSelStatisticsB1ByPolCode(statisticsB1String);
                System.out.println(statisticsB1Strings.size());
                if (statisticsB1Strings.size() == 1) {
                    StatisticsB1String statisticsB1String1 = statisticsB1Strings.get(0);
                    statisticsB1String.setBegCount(statisticsB1String1.getEndCount());
                    statisticsB1String.setBegNorCount(statisticsB1String1.getEndNorCount());
                    statisticsB1String.setBegExpCount(statisticsB1String1.getEndExpCount());
                    statisticsB1String.setBegLoseCount(statisticsB1String1.getEndLoseCount());
                    statisticsB1String.setBegLoseStopCount(statisticsB1String1.getEndLoseStopCount());
                } else {
                    statisticsB1String.setBegCount(0 + "");
                    statisticsB1String.setBegNorCount(0 + "");
                    statisticsB1String.setBegExpCount(0 + "");
                    statisticsB1String.setBegLoseCount(0 + "");
                    statisticsB1String.setBegLoseStopCount(0 + "");
                }
                System.out.println("----------------------------------cfdSelEndOfTermDate 111---------");
                NBTjProcessFuntions.cfdSelEndOfTermDate(statisticsB1String, startEnd[0], startEnd[1]);
                System.out.println("----------------------------------cfdSelThisPeriodDate 222---------");
                NBTjProcessFuntions.cfdSelThisPeriodDate(statisticsB1String, startEnd[0], startEnd[1]);
                Integer integer = NBTjProcessFuntions.InsertStatisticsB1(statisticsB1String);
                System.out.println("StatisticsB1 表插入" + integer + "条数据,数据为" + statisticsB1String);
            }
        }
    }

    public void StatisticsB2(int years, int times, String obpsOrNbpsFlag) {
        long counts = 0;
        //获取时间范围
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        counts = NBTjProcessFuntions.cfdSelStatisticsB2ByYearAndTimes(years, times);
        System.out.println("查询statistics_b2 中满足条件的个数为" + counts);

        if (counts > 0) {
            NBTjProcessFuntions.cfdDelStatisticsB2ByYearAndTimes(years, times);
        }
        long l = System.currentTimeMillis();
        String currentDateTime = PublicFunctions.getCurrentDateTime(l);
        List<PolicyString> policyStrings = NBTjProcessFuntions.cfdSelPolicy();
        if (policyStrings.size() > 0) {
            for (PolicyString policyString : policyStrings) {
                StatisticsB2String statisticsB2String = new StatisticsB2String();
                statisticsB2String.setYears(years + "");
                statisticsB2String.setTimes(times + "");
                statisticsB2String.setProcDate(currentDateTime);
                if ("!!!!!!".equals(policyString.getBranchNo())) {
                    statisticsB2String.setPolFrom("总颁险种");
                } else {
                    statisticsB2String.setPolFrom("地方险种");
                }
                if (obpsOrNbpsFlag.startsWith("N")) {
                    statisticsB2String.setDateFrom("PICC3");
                } else {
                    statisticsB2String.setDateFrom("PICC1");
                }
                statisticsB2String.setPolCode(policyString.getPolCode().trim());
                statisticsB2String.setPolName(policyString.getPolNameChn().trim());
                String policyMrType = policyString.getMrType();
                if ("M".equals(policyMrType)) {
                    statisticsB2String.setMrType("M");
                } else {
                    statisticsB2String.setMrType("A");
                }
                //更新
                if ("L".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("1");
                } else if ("H".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("4");
                } else if ("D".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("2");
                } else if ("P".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("8");
                } else if ("S".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("6");
                } else {
                    policyString.setBusinessType("0");
                }
                NBTjProcessFuntions.selMioLogByMioItemCodeAndPolCode(statisticsB2String, startEnd[0], startEnd[1]);
            }
        }
    }

    public void StatisticsB3(int years, int times, String obpsOrNbpsFlag) {
        long counts = 0;
        //获取时间范围
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        counts = NBTjProcessFuntions.cfdSelStatisticsB3ByYearAndTimes(years, times);
        System.out.println("查询statistics_b3 中满足条件的个数为" + counts);
        if (counts > 0) {
            NBTjProcessFuntions.cfdDelStatisticsB3ByYearAndTimes(years, times);
        }
        long l = System.currentTimeMillis();
        String currentDateTime = PublicFunctions.getCurrentDateTime(l);
        List<PolicyString> policyStrings = NBTjProcessFuntions.cfdSelPolicy();
        if (policyStrings.size() > 0) {
            for (PolicyString policyString : policyStrings) {
                StatisticsB3String statisticsB3String = new StatisticsB3String();
                statisticsB3String.setYears(years + "");
                statisticsB3String.setTimes(times + "");
                statisticsB3String.setProcDate(currentDateTime);
                if (!("S".equals(policyString.getBusinessType()))) continue;
                if ("!!!!!!".equals(policyString.getBranchNo())) {
                    statisticsB3String.setPolFrom("总颁险种");
                } else {
                    statisticsB3String.setPolFrom("地方险种");
                }
                if (obpsOrNbpsFlag.startsWith("N")) {
                    statisticsB3String.setDateFrom("PICC3");
                } else {
                    statisticsB3String.setDateFrom("PICC1");
                }
                statisticsB3String.setPolCode(policyString.getPolCode().trim());
                statisticsB3String.setPolName(policyString.getPolNameChn().trim());
                String policyMrType = policyString.getMrType();
                if ("M".equals(policyMrType)) {
                    statisticsB3String.setMrType("M");
                } else {
                    statisticsB3String.setMrType("A");
                }
                //更新
                if ("L".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("1");
                } else if ("H".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("4");
                } else if ("D".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("2");
                } else if ("P".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("8");
                } else if ("S".equals(policyString.getBusinessType())) {
                    policyString.setBusinessType("6");
                } else {
                    policyString.setBusinessType("0");
                }
                NBTjProcessFuntions.selMioLogByMioItemCodeAndPolCodeIII(statisticsB3String, startEnd[0], startEnd[1]);
            }
        }
    }
}
