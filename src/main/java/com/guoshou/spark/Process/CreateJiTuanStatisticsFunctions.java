package com.guoshou.spark.Process;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.Process.OB.OBJiTuanProcessFuntions;
import com.guoshou.spark.config.PublicFunctions;
import com.guoshou.spark.mapper.NB.*;
import com.guoshou.spark.mapper.OB.*;
import com.guoshou.spark.po.Dto.JiTuanDto;
import com.guoshou.spark.po.JavaOB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateJiTuanStatisticsFunctions {

    @Autowired
    private BizParameterStringOBMapper bizParameterStringOBMapper;
    @Autowired
    private BranchStringOBMapper branchStringOBMapper;
    @Autowired
    private PolicyStringOBMapper policyStringOBMapper;
    @Autowired
    private OBJiTuanProcessFuntions obJiTuanProcessFuntions;
    @Autowired
    private StatisticsB4StringOBMapper statisticsB4StringOBMapper;
    @Autowired
    private AmendGenInfoStringOBMapper amendGenInfoStringOBMapper;
    @Autowired
    private SgApplStringOBMapper sgApplStringOBMapper;
    @Autowired
    private SvrGroupLstStringOBMapper svrGroupLstStringOBMapper;
    @Autowired
    private StdContractStringOBMapper stdContractStringOBMapper;
    @Autowired
    private StatisticsB4StringMapper statisticsB4StringMapper;

    //nb
    @Autowired
    private BizParameterStringMapper bizParameterStringMapper;
    @Autowired
    private AmendGenInfoStringMapper amendGenInfoStringMapper;
    @Autowired
    private SgApplStringMapper sgApplStringMapper;
    @Autowired
    private SvrGroupLstStringMapper svrGroupLstStringMapper;
    @Autowired
    private BranchStringMapper branchStringMapper;
    @Autowired
    private StdContractStringMapper stdContractStringMapper;

    public void CreateJiTuanStatisticsB1(int years, int times, String branchNo) {
        String polType = "";
        String currentDateTime = PublicFunctions.getCurrentDateTime(System.currentTimeMillis());
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        String obpsOrNbpsFlag = bizParameterStringOBMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        String branchNo2 = branchNo.substring(0, 2);
        branchNo2 += "%";
        List<BranchString> branchStrings = branchStringOBMapper.DeclareCurBranch(branchNo2);
        Map<Integer, Map<String, Object>> b1StringMap = new HashMap<>();
        int counts = 0;
        for (BranchString branchString : branchStrings) {
            System.out.println(branchString);
            System.out.println("********************************");
            List<PolicyString> policyStrings = policyStringOBMapper.SelPolicy();
            for (PolicyString policyString : policyStrings) {
                System.out.println(policyString);
                System.out.println("-----------------------------------------------");
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
                obJiTuanProcessFuntions.cfdSelEndOfTermDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                obJiTuanProcessFuntions.cfdSelThisPeriodDate2(statisticsB1String, startEnd[0], startEnd[1], branchString.getBranchNo());
                System.out.println(statisticsB1String);
                System.out.println("--------------------------end   ob-------------------------------");
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("polType", polType);
                stringObjectHashMap.put("polBusinessType", policyString.getBusinessType());
                stringObjectHashMap.put("statisticsB1String", statisticsB1String);
                b1StringMap.put(counts, stringObjectHashMap);
                counts++;
//                if (counts > 3) {
//                    break;
//                }
            }
//            if (counts > 3) {
//                break;
//            }
        }
//文件生成
        PublicFunctions.CreateTxtFile(startEnd[0], startEnd[1], branchNo, b1StringMap);

    }


    @DS("OB")
    public void CreateJiTuanStatisticsB4(int years, int times, String branchNo) {
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        QueryWrapper<StatisticsB4String> b4StringQueryWrapper = new QueryWrapper<>();
        b4StringQueryWrapper.eq("years", years)
                .eq("times", times);
        Long b4Count = statisticsB4StringOBMapper.selectCount(b4StringQueryWrapper);
        if (b4Count > 0) {
            statisticsB4StringOBMapper.delete(b4StringQueryWrapper);
        }
        String obpsOrNbpsFlag = bizParameterStringOBMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        List<AmendGenInfoString> amendGenInfoStrings = amendGenInfoStringOBMapper.CurAmendGenInfo(startEnd[0], startEnd[1]);
        if (amendGenInfoStrings.size() > 0) {
            for (AmendGenInfoString amendGenInfoString : amendGenInfoStrings) {
                StatisticsB4String statisticsB4String = new StatisticsB4String();
                if (amendGenInfoString.getCntrNo().startsWith("#")) {
                    String cntrNo = sgApplStringOBMapper.IntoCntrNo(amendGenInfoString.getAmendArcNo());
                    amendGenInfoString.setAmendArcNo(cntrNo.trim());
                }
                Integer byCntrNo = svrGroupLstStringOBMapper.SelSvrGroupLstByCntrNo(amendGenInfoString.getCntrNo());
                if (byCntrNo ==null) {
                    byCntrNo = 0;
                }
                if (b4Count>0){
                    SvrGroupLstString svrGroupLstString = svrGroupLstStringOBMapper.CurSvrGroupLst(amendGenInfoString.getCntrNo());
                    String cntrNo1 = svrGroupLstString.getCntrNo();
                    if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                        statisticsB4String.setBusSys("NBPS");
                    }else {
                        statisticsB4String.setBusSys("OBPS");
                    }
                    statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
                    statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
                    statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
                    statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
                    statisticsB4String.setAmendType(amendGenInfoString.getApplType());
                    statisticsB4String.setRemark("补录原因未明（分单个单号："+ cntrNo1);
                    BranchString branchString = branchStringOBMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                    statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                        statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                    }
                    BranchString branchString1 = branchStringOBMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                    statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                        statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                    }
//                    statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(cntrNo1).getInForceDate());
//                    statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(cntrNo1).getFaceAmnt());
                    statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                    statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                    System.out.println(statisticsB4String);
                    //写入文件
                }else {
                    if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                        statisticsB4String.setBusSys("NBPS");
                    }else {
                        statisticsB4String.setBusSys("OBPS");
                    }
                    statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
                    statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
                    statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
                    statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
                    statisticsB4String.setAmendType("真实补录");
                    BranchString branchString = branchStringOBMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                    statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                        statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                    }
                    BranchString branchString1 = branchStringOBMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                    statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                        statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                    }
                    statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                    statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                    //写入文件
                    System.out.println(statisticsB4String);
                }
            }
        }

        List<AmendGenInfoString> amendGenInfoStrings1 = amendGenInfoStringOBMapper.CurAmendGenInfo1(startEnd[0], startEnd[1]);
        for (AmendGenInfoString amendGenInfoString : amendGenInfoStrings1) {
            StatisticsB4String statisticsB4String = new StatisticsB4String();
            if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                statisticsB4String.setBusSys("NBPS");
            }else {
                statisticsB4String.setBusSys("OBPS");
            }
            statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
            statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
            statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
            statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
            statisticsB4String.setAmendType("真实补录");
            BranchString branchString = branchStringOBMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
            statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
            if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
            }
            BranchString branchString1 = branchStringOBMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
            statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
            if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
            }
            statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
            statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
            //写入文件
            System.out.println(statisticsB4String);
        }

        List<JiTuanDto> jiTuanDtos = amendGenInfoStringOBMapper.CurAmendGenInfo2(startEnd[0], startEnd[1]);
        for (JiTuanDto jiTuanDto : jiTuanDtos) {
            StatisticsB4String statisticsB4String = new StatisticsB4String();
            statisticsB4String.setCntrNo(jiTuanDto.getCntrNo());
            statisticsB4String.setAmendDate(jiTuanDto.getProcDate());
            statisticsB4String.setPolCode(jiTuanDto.getPolCode());
            statisticsB4String.setApplBranchNo(jiTuanDto.getMgrBranchNo());
            statisticsB4String.setInForceDate(jiTuanDto.getInForceDate());
            statisticsB4String.setRemark(jiTuanDto.getOriginalCntrNo());
            Integer byCntrNo = svrGroupLstStringOBMapper.SelSvrGroupLstByCntrNo(statisticsB4String.getCntrNo());
            if (byCntrNo ==null) {
                byCntrNo = 0;
            }
            if(byCntrNo > 0) {
                SvrGroupLstString svrGroupLstString = svrGroupLstStringOBMapper.CurSvrGroupLst(statisticsB4String.getCntrNo());
                String cntrNo1 = svrGroupLstString.getCntrNo();
                if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                    statisticsB4String.setBusSys("NBPS");
                }else {
                    statisticsB4String.setBusSys("OBPS");
                }
                statisticsB4String.setAmendType("保单转移");
                BranchString branchString = branchStringOBMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                    statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                }
                BranchString branchString1 = branchStringOBMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                    statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                }
                statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(cntrNo1).getInForceDate());
                statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                System.out.println(statisticsB4String);
                //写入文件
            }else {
                if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                    statisticsB4String.setBusSys("NBPS");
                }else {
                    statisticsB4String.setBusSys("OBPS");
                }
                statisticsB4String.setAmendType("保单转移");
                BranchString branchString = branchStringOBMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                    statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                }
                BranchString branchString1 = branchStringOBMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                    statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                }
                statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                //写入文件
                System.out.println(statisticsB4String);
            }
        }
    }

    public void CreateJiTuanStatisticsB4NB(int years, int times, String branchNo) {
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        QueryWrapper<com.guoshou.spark.po.JavaNB.StatisticsB4String> b4StringQueryWrapper = new QueryWrapper<>();
        b4StringQueryWrapper.eq("years", years)
                .eq("times", times);
        Long b4Count = statisticsB4StringMapper.selectCount(b4StringQueryWrapper);
        if (b4Count > 0) {
            statisticsB4StringMapper.delete(b4StringQueryWrapper);
        }

        String obpsOrNbpsFlag = bizParameterStringMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        List<com.guoshou.spark.po.JavaNB.AmendGenInfoString> amendGenInfoStrings = amendGenInfoStringMapper.CurAmendGenInfo(startEnd[0], startEnd[1]);
        if (amendGenInfoStrings.size() > 0) {
            for (com.guoshou.spark.po.JavaNB.AmendGenInfoString amendGenInfoString : amendGenInfoStrings) {
                StatisticsB4String statisticsB4String = new StatisticsB4String();
                if (amendGenInfoString.getCntrNo().startsWith("#")) {
                    String cntrNo = sgApplStringMapper.IntoCntrNo(amendGenInfoString.getAmendArcNo());
                    amendGenInfoString.setAmendArcNo(cntrNo.trim());
                }
                Integer byCntrNo = svrGroupLstStringMapper.SelSvrGroupLstByCntrNo(amendGenInfoString.getCntrNo());
                if (byCntrNo ==null) {
                    byCntrNo = 0;
                }
                if (b4Count>0){
                    //写到这里
                    com.guoshou.spark.po.JavaNB.SvrGroupLstString svrGroupLstString = svrGroupLstStringMapper.CurSvrGroupLst(amendGenInfoString.getCntrNo());
                    String cntrNo1 = svrGroupLstString.getCntrNo();
                    if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                        statisticsB4String.setBusSys("NBPS");
                    }else {
                        statisticsB4String.setBusSys("OBPS");
                    }
                    statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
                    statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
                    statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
                    statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
                    statisticsB4String.setAmendType(amendGenInfoString.getApplType());
                    statisticsB4String.setRemark("补录原因未明（分单个单号："+ cntrNo1);
                    com.guoshou.spark.po.JavaNB.BranchString branchString = branchStringMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                    statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                        statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                    }
                    com.guoshou.spark.po.JavaNB.BranchString branchString1 = branchStringMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                    statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                        statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                    }
//                    statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(cntrNo1).getInForceDate());
//                    statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(cntrNo1).getFaceAmnt());
                    statisticsB4String.setInForceDate(stdContractStringMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                    statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                    System.out.println(statisticsB4String);
                    //写入文件
                }else {
                    if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                        statisticsB4String.setBusSys("NBPS");
                    }else {
                        statisticsB4String.setBusSys("OBPS");
                    }
                    statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
                    statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
                    statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
                    statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
                    statisticsB4String.setAmendType("真实补录");
                    com.guoshou.spark.po.JavaNB.BranchString branchString = branchStringMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                    statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                        statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                    }
                    com.guoshou.spark.po.JavaNB.BranchString branchString1 = branchStringMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                    statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                    if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                        statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                    }
                    statisticsB4String.setInForceDate(stdContractStringMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                    statisticsB4String.setFaceAmnt(stdContractStringMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                    //写入文件
                    System.out.println(statisticsB4String);
                }
            }
        }

        List<com.guoshou.spark.po.JavaNB.AmendGenInfoString> amendGenInfoStrings1 = amendGenInfoStringMapper.CurAmendGenInfo1(startEnd[0], startEnd[1]);
        for (com.guoshou.spark.po.JavaNB.AmendGenInfoString amendGenInfoString : amendGenInfoStrings1) {
            StatisticsB4String statisticsB4String = new StatisticsB4String();
            if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                statisticsB4String.setBusSys("NBPS");
            }else {
                statisticsB4String.setBusSys("OBPS");
            }
            statisticsB4String.setApplBranchNo(amendGenInfoString.getApplBranchNo());
            statisticsB4String.setPolCode(amendGenInfoString.getPolCode());
            statisticsB4String.setCntrNo(amendGenInfoString.getCntrNo());
            statisticsB4String.setAmendDate(amendGenInfoString.getAmendDate());
            statisticsB4String.setAmendType("真实补录");
            com.guoshou.spark.po.JavaNB.BranchString branchString = branchStringMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
            statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
            if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
            }
            com.guoshou.spark.po.JavaNB.BranchString branchString1 = branchStringMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
            statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
            if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
            }
            statisticsB4String.setInForceDate(stdContractStringMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
            statisticsB4String.setFaceAmnt(stdContractStringMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
            //写入文件
            System.out.println(statisticsB4String);
        }

        List<JiTuanDto> jiTuanDtos = amendGenInfoStringMapper.CurAmendGenInfo2(startEnd[0], startEnd[1]);
        for (JiTuanDto jiTuanDto : jiTuanDtos) {
            StatisticsB4String statisticsB4String = new StatisticsB4String();
            statisticsB4String.setCntrNo(jiTuanDto.getCntrNo());
            statisticsB4String.setAmendDate(jiTuanDto.getProcDate());
            statisticsB4String.setPolCode(jiTuanDto.getPolCode());
            statisticsB4String.setApplBranchNo(jiTuanDto.getMgrBranchNo());
            statisticsB4String.setInForceDate(jiTuanDto.getInForceDate());
            statisticsB4String.setRemark(jiTuanDto.getOriginalCntrNo());
            Integer byCntrNo = svrGroupLstStringMapper.SelSvrGroupLstByCntrNo(statisticsB4String.getCntrNo());
            if (byCntrNo ==null) {
                byCntrNo = 0;
            }
            if(byCntrNo > 0) {
                com.guoshou.spark.po.JavaNB.SvrGroupLstString svrGroupLstString = svrGroupLstStringMapper.CurSvrGroupLst(statisticsB4String.getCntrNo());
                String cntrNo1 = svrGroupLstString.getCntrNo();
                if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                    statisticsB4String.setBusSys("NBPS");
                }else {
                    statisticsB4String.setBusSys("OBPS");
                }
                statisticsB4String.setAmendType("保单转移");
                com.guoshou.spark.po.JavaNB.BranchString branchString = branchStringMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                    statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                }
                com.guoshou.spark.po.JavaNB.BranchString branchString1 = branchStringMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                    statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                }
                statisticsB4String.setInForceDate(stdContractStringOBMapper.getInForceDate(cntrNo1).getInForceDate());
                statisticsB4String.setFaceAmnt(stdContractStringOBMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                System.out.println(statisticsB4String);
                //写入文件
            }else {
                if ("N".equals(obpsOrNbpsFlag.substring(0,1))){
                    statisticsB4String.setBusSys("NBPS");
                }else {
                    statisticsB4String.setBusSys("OBPS");
                }
                statisticsB4String.setAmendType("保单转移");
                com.guoshou.spark.po.JavaNB.BranchString branchString = branchStringMapper.GetRegBranchNo(statisticsB4String.getApplBranchNo());
                statisticsB4String.setRegBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getRegBranchNo().substring(0, 6))) {
                    statisticsB4String.setRegBranchNo(statisticsB4String.getApplBranchNo());
                }
                com.guoshou.spark.po.JavaNB.BranchString branchString1 = branchStringMapper.GetRegBranchNo1(statisticsB4String.getRegBranchNo());
                statisticsB4String.setProBranchNo(branchString.getPrioBranchNo());
                if ("!!!!!!".equals(statisticsB4String.getProBranchNo().substring(0, 6))) {
                    statisticsB4String.setProBranchNo(statisticsB4String.getRegBranchNo());
                }
                statisticsB4String.setInForceDate(stdContractStringMapper.getInForceDate(statisticsB4String.getCntrNo()).getInForceDate());
                statisticsB4String.setFaceAmnt(stdContractStringMapper.getInFaceAmnt(statisticsB4String.getCntrNo()).getFaceAmnt());
                //写入文件
                System.out.println(statisticsB4String);
            }
        }
    }

    public void CreateJiTuanStatisticsB2(int years, int times, String branchNo){
        String polType = "";
        String currentDateTime = PublicFunctions.getCurrentDateTime(System.currentTimeMillis());
        String[] startEnd = PublicFunctions.getNowPeriodBeginEndDateB4(years, times);
        String obpsOrNbpsFlag = bizParameterStringOBMapper.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        String branchNo2 = branchNo.substring(0, 2);
        branchNo2 += "%";
        List<BranchString> branchStrings = branchStringOBMapper.DeclareCurBranch(branchNo2);
        for (BranchString branchString : branchStrings) {
            List<PolicyString> policyStrings = policyStringOBMapper.SelPolicy();
            for (PolicyString policyString : policyStrings) {
                StatisticsB2String statisticsB2String = new StatisticsB2String();
                statisticsB2String.setYears(years + "");
                statisticsB2String.setTimes(times + "");
                statisticsB2String.setProcDate(currentDateTime);
                if ("!!!!!!".equals(policyString.getBranchNo())) {
                    statisticsB2String.setPolFrom("总颁险种");
                    polType = "1";
                } else {
                    statisticsB2String.setPolFrom("地方险种");
                    polType = "0";
                }
                if (obpsOrNbpsFlag.startsWith("N")) {
                    statisticsB2String.setDateFrom("PICC3");
                } else {
                    statisticsB2String.setDateFrom("PICC1");
                }

                statisticsB2String.setPolCode(policyString.getPolCode());
                statisticsB2String.setPolName(policyString.getPolNameChn());
                statisticsB2String.setMrType(policyString.getMrType());
                String policyMrType = policyString.getMrType();
                if ("M".equals(policyMrType)) {
                    statisticsB2String.setMrType("M");
                } else {
                    statisticsB2String.setMrType("A");
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
                StatisticsB2String statisticsB2String1 = obJiTuanProcessFuntions.SelMioLogByMioItemCodeAndPolCode2(statisticsB2String, startEnd[0], startEnd[1], branchNo);

                //statisticsB2String1   写入文件
            }
        }
    }
}
