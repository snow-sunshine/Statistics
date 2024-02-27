package com.guoshou.spark.Process;

import com.guoshou.spark.config.PublicFunctions;
import com.guoshou.spark.mapper.NB.BizParameterStringMapper;

import com.guoshou.spark.mapper.NB.BranchStringMapper;
import com.guoshou.spark.mapper.NB.PolicyStringMapper;
import com.guoshou.spark.po.JavaNB.PolicyString;
import com.guoshou.spark.po.JavaNB.BranchString;
import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CreateJiTuanNBStatisticsFunctions {
    @Autowired
    private BizParameterStringMapper bizParameterStringMapper;
    @Autowired
    private BranchStringMapper branchStringMapper;
    @Autowired
    private PolicyStringMapper policyStringMapper;
    @Autowired
    private NBJiTuanProcessFuntions nbJiTuanProcessFuntions;

    public void CreateJiTuanStatisticsB1(int years, int times, String branchNo) {
        String polType = "";
        String currentDateTime = PublicFunctions.getCurrentDateTime(System.currentTimeMillis());
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        String obpsOrNbpsFlag = bizParameterStringMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        String branchNo2 = branchNo.substring(0, 2);
        branchNo2 += "%";
        List<BranchString> branchStrings = branchStringMapper.DeclareCurBranch(branchNo2);
        Map<Integer, Map<String, Object>> b1StringMap = new HashMap<>();
        int counts = 0;
        for (BranchString branchString : branchStrings) {
            List<PolicyString> policyStrings = policyStringMapper.SelPolicy();
            for (PolicyString policyString : policyStrings) {
                StatisticsB1String statisticsB1String = new StatisticsB1String();
                statisticsB1String.setYears(years + "");
                statisticsB1String.setTimes(times + "");
                statisticsB1String.setProcDate(currentDateTime);
                if ("!!!!!!".equals(policyString.getBranchNo())) {
                    statisticsB1String.setPolFrom("总颁险种");
                    polType = "1";
                } else {
                    statisticsB1String.setPolFrom("地方险种");
                    polType = "0";
                }
                if (obpsOrNbpsFlag.startsWith("N")) {
                    statisticsB1String.setDateFrom("PICC3");
                } else {
                    statisticsB1String.setDateFrom("PICC1");
                }

                statisticsB1String.setPolCode(policyString.getPolCode());
                statisticsB1String.setPolName(policyString.getPolNameChn());
                statisticsB1String.setMrType(policyString.getMrType());
                String policyMrType = policyString.getMrType();
                if ("M".equals(policyMrType)) {
                    statisticsB1String.setMrType("M");
                } else {
                    statisticsB1String.setMrType("A");
                }
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
                nbJiTuanProcessFuntions.cfdSelEndOfTermDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                nbJiTuanProcessFuntions.cfdSelThisPeriodDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                System.out.println(statisticsB1String);
                System.out.println("--------------------------end   NB-------------------------------");
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("polType", polType);
                stringObjectHashMap.put("polBusinessType", policyString.getBusinessType());
                stringObjectHashMap.put("statisticsB1String", statisticsB1String);
                b1StringMap.put(counts, stringObjectHashMap);
                counts++;
            }
        }
        PublicFunctions.CreateTxtFile(startEnd[0], startEnd[1], branchNo, b1StringMap);
    }


    public void CreateJiTuanStatisticsB2(int years, int times, String branchNo) {
//        String polType = "";
        String currentDateTime = PublicFunctions.getCurrentDateTime(System.currentTimeMillis());
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        String obpsOrNbpsFlag = bizParameterStringMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        String branchNo2 = branchNo.substring(0, 2);
        branchNo2 += "%";
        List<BranchString> branchStrings = branchStringMapper.DeclareCurBranch(branchNo2);
        Map<Integer, Map<String, Object>> b1StringMap = new ConcurrentHashMap<>(); // 使用线程安全的ConcurrentHashMap
        AtomicInteger counts = new AtomicInteger(0); // 使用原子整数来保证并发计数的正确性

        for (BranchString branchString : branchStrings) {
            List<PolicyString> policyStrings = policyStringMapper.SelPolicy();
            int finalCounts = counts.getAndAdd(policyStrings.size()); // 原子地获取并增加计数
            CountDownLatch latch = new CountDownLatch(policyStrings.size()); // 使用CountDownLatch来等待所有线程完成

            for (PolicyString policyString : policyStrings) {
                new Thread(() -> {
                    String polType = "";
                    StatisticsB1String statisticsB1String = new StatisticsB1String();
                    statisticsB1String.setYears(years + "");
                    statisticsB1String.setTimes(times + "");
                    statisticsB1String.setProcDate(currentDateTime);
                    if ("!!!!!!".equals(policyString.getBranchNo())) {
                        statisticsB1String.setPolFrom("总颁险种");
                        polType = "1";
                    } else {
                        statisticsB1String.setPolFrom("地方险种");
                        polType = "0";
                    }
                    if (obpsOrNbpsFlag.startsWith("N")) {
                        statisticsB1String.setDateFrom("PICC3");
                    } else {
                        statisticsB1String.setDateFrom("PICC1");
                    }

                    statisticsB1String.setPolCode(policyString.getPolCode());
                    statisticsB1String.setPolName(policyString.getPolNameChn());
                    statisticsB1String.setMrType(policyString.getMrType());
                    String policyMrType = policyString.getMrType();
                    if ("M".equals(policyMrType)) {
                        statisticsB1String.setMrType("M");
                    } else {
                        statisticsB1String.setMrType("A");
                    }
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
                    System.out.println(policyString.getPolCode()+"--------------------cfdSelEndOfTermDate2 方法---------------------");
                    nbJiTuanProcessFuntions.cfdSelEndOfTermDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                    System.out.println(policyString.getPolCode()+"--------------------cfdSelThisPeriodDate2 方法---------------------");
                    nbJiTuanProcessFuntions.cfdSelThisPeriodDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                    System.out.println(statisticsB1String);
                    System.out.println("--------------------------end   NB-------------------------------");
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    stringObjectHashMap.put("polType", polType);
                    stringObjectHashMap.put("polBusinessType", policyString.getBusinessType());
                    stringObjectHashMap.put("statisticsB1String", statisticsB1String);
                    b1StringMap.put(finalCounts, stringObjectHashMap);

                    latch.countDown(); // 完成一个线程的操作，减少CountDownLatch计数
                }).start();
            }

            try {
                latch.await(); // 等待所有线程完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PublicFunctions.CreateTxtFile(startEnd[0], startEnd[1], branchNo, b1StringMap);
    }

    public void CreateJiTuanStatisticsB3(int years, int times, String branchNo) {
        String currentDateTime = PublicFunctions.getCurrentDateTime(System.currentTimeMillis());
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        String obpsOrNbpsFlag = bizParameterStringMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        String branchNo2 = branchNo.substring(0, 2);
        branchNo2 += "%";
        List<BranchString> branchStrings = branchStringMapper.DeclareCurBranch(branchNo2);
        Map<Integer, Map<String, Object>> b1StringMap = new ConcurrentHashMap<>();
//        AtomicInteger counts = new AtomicInteger(0);
        int counts =0;
        // 创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (BranchString branchString : branchStrings) {
            List<PolicyString> policyStrings = policyStringMapper.SelPolicy();
            CountDownLatch latch = new CountDownLatch(policyStrings.size());

            for (PolicyString policyString : policyStrings) {
                executorService.execute(() -> {
                    String polType = "";
                    StatisticsB1String statisticsB1String = new StatisticsB1String();
                    statisticsB1String.setYears(years + "");
                    statisticsB1String.setTimes(times + "");
                    statisticsB1String.setProcDate(currentDateTime);
                    if ("!!!!!!".equals(policyString.getBranchNo())) {
                        statisticsB1String.setPolFrom("总颁险种");
                        polType = "1";
                    } else {
                        statisticsB1String.setPolFrom("地方险种");
                        polType = "0";
                    }
                    if (obpsOrNbpsFlag.startsWith("N")) {
                        statisticsB1String.setDateFrom("PICC3");
                    } else {
                        statisticsB1String.setDateFrom("PICC1");
                    }

                    statisticsB1String.setPolCode(policyString.getPolCode());
                    statisticsB1String.setPolName(policyString.getPolNameChn());
                    statisticsB1String.setMrType(policyString.getMrType());
                    String policyMrType = policyString.getMrType();
                    if ("M".equals(policyMrType)) {
                        statisticsB1String.setMrType("M");
                    } else {
                        statisticsB1String.setMrType("A");
                    }
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
                    System.out.println(policyString.getPolCode()+"----------"+counts+"--------------------cfdSelEndOfTermDate2 方法---------------------");
                    nbJiTuanProcessFuntions.cfdSelEndOfTermDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                    System.out.println(policyString.getPolCode()+"--------------------cfdSelThisPeriodDate2 方法---------------------");
                    nbJiTuanProcessFuntions.cfdSelThisPeriodDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                    System.out.println(statisticsB1String);
                    System.out.println("--------------------------end   NB-------------------------------");
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    stringObjectHashMap.put("polType", polType);
                    stringObjectHashMap.put("polBusinessType", policyString.getBusinessType());
                    stringObjectHashMap.put("statisticsB1String", statisticsB1String);

                    b1StringMap.put(counts, stringObjectHashMap);
                    latch.countDown();
                });
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown(); // 关闭线程池

        PublicFunctions.CreateTxtFile(startEnd[0], startEnd[1], branchNo, b1StringMap);
    }

}
