package com.guoshou.spark.Process.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.mapper.OB.*;
import com.guoshou.spark.po.Dto.CountAndSumResult;
import com.guoshou.spark.po.JavaOB.StatisticsB1String;
import com.guoshou.spark.po.JavaOB.StatisticsB2String;
import com.guoshou.spark.po.JavaOB.StatisticsB4String;
import com.guoshou.spark.po.JavaOB.StdContractString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OBJiTuanProcessFuntions {
    @Autowired
    private StdContractStringOBMapper stdContractStringOBMapper;
    @Autowired
    private SvrGroupStringOBMapper svrGroupStringOBMapper;
    @Autowired
    private StatisticsB4StringOBMapper statisticsB4StringOBMapper;
    @Autowired
    private IfOfAltStringOBMapper ifOfAltStringOBMapper;
    @Autowired
    private MioLogStringOBMapper mioLogStringOBMapper;

    @DS("OB")
    public void cfdSelEndOfTermDate2(StatisticsB1String statisticsB1String, String startTime, String endTime, String branchNo) {
        String polCode = statisticsB1String.getPolCode().trim();
        long countaaa = 0;
        long countbbb = 0;
        long countccc = 0;
        //期末有效
        //select count(*) from cl_cntr4_std_contract where polCode ='F01' and cntrStat ='K' and STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')>'2023-12-31'
        QueryWrapper<StdContractString> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("polCode", polCode)
                .eq("cntrStat", "K")
                .eq("mgrBranchNo", branchNo)
                .gt("STR_TO_DATE(cntrExpiryDate, '%Y-%m-%d')", endTime);
        countaaa = stdContractStringOBMapper.selectCount(queryWrapper1);

        countbbb = svrGroupStringOBMapper.selEndNorCountBJiTuan(polCode, endTime, branchNo);
        statisticsB1String.setEndNorCount(countaaa + countbbb + "");

        //期末满期
        countaaa = stdContractStringOBMapper.selectCountAJiTuan(polCode, branchNo);
        countbbb = stdContractStringOBMapper.selectCountBJiTuan(polCode, branchNo);
        countccc = stdContractStringOBMapper.selectCountCJiTuan(polCode, branchNo);
        statisticsB1String.setEndExpCount(countaaa + countbbb + countccc + "");

        //期末失效S
        long l = stdContractStringOBMapper.selectCountEndLoseCountJiTuan(polCode, branchNo);
        statisticsB1String.setEndLoseCount(l + "");
        //期末失效终止
        countaaa = stdContractStringOBMapper.selectCountEndLoseStopCountJiTuan(polCode, endTime, branchNo);
        countbbb = stdContractStringOBMapper.selectCountEndLoseStopCountBJiTuan(polCode, endTime, branchNo);
        statisticsB1String.setEndLoseStopCount(countaaa - countbbb + "");
        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount()) +
                Long.parseLong(statisticsB1String.getEndExpCount()) + Long.parseLong(statisticsB1String.getEndLoseCount()) + Long.parseLong(statisticsB1String.getEndLoseStopCount()) + "");
    }

    public void cfdSelThisPeriodDate2(StatisticsB1String statisticsB1String, String startTime, String endTime, String branchNo) {
        String polCode = statisticsB1String.getPolCode().trim();
        String years = statisticsB1String.getYears().trim();
        String times = statisticsB1String.getTimes().trim();
        //本期补录新增
        long nowRecNewCountJiTuan = statisticsB4StringOBMapper.selectCountNowRecNewCountJiTuan(polCode, years, times, branchNo);
        statisticsB1String.setNowRecNewCount(nowRecNewCountJiTuan + "");

        //本期复效
        long countNowReinCount = ifOfAltStringOBMapper.selectCountNowReinCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowReinCount(countNowReinCount + "");

        //本期失效
        long loseCount = ifOfAltStringOBMapper.selectCountNowLoseCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowLoseCount(loseCount + "");

        //本期满期
        long nowExpCount1 = stdContractStringOBMapper.selectNowExpCountJiTuan(polCode, startTime, endTime, branchNo);
        long nowExpCount2 = stdContractStringOBMapper.selectNowExpCount1JiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowExpCount(nowExpCount1 + nowExpCount2 + "");

        //本期满期终止
        long nowExpStopCount = stdContractStringOBMapper.selectNowExpStopCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowExpStopCount(nowExpStopCount + "");
        long newCount = Long.parseLong(statisticsB1String.getNowExpStopCount()) - Long.parseLong(statisticsB1String.getNowExpCount());
        statisticsB1String.setNowExpStopCount(newCount + "");

        //本期解约终止
        long nowSevStopCountA = stdContractStringOBMapper.selectNowSevStopCountAJiTuan(polCode, startTime, endTime,branchNo);
        statisticsB1String.setNowSevStopCount(nowSevStopCountA + "");
        //本期失效终止
        long nowSevStopCount = stdContractStringOBMapper.selectNowSevStopCountJiTuan(polCode, startTime, endTime,branchNo);
        long nowSevStopCount1 = stdContractStringOBMapper.selectNowSevStopCount1JiTuan(polCode, startTime, endTime,branchNo);
        statisticsB1String.setNowLoseStopCount(nowSevStopCount - nowSevStopCount1 + "");
        //本期其他终止
        long nowOtherStopCount = stdContractStringOBMapper.selectNowOtherStopCount(polCode, startTime, endTime);
        statisticsB1String.setNowOtherStopCount(nowOtherStopCount + "");

    }

    public StatisticsB2String SelMioLogByMioItemCodeAndPolCode2(StatisticsB2String statisticsB2String, String startTime, String endTime, String branchNo) {
        String polCode = statisticsB2String.getPolCode().trim();
        CountAndSumResult inPsCntAndAmtb2 = mioLogStringOBMapper.inPsCntAndAmtb2(polCode, startTime, endTime,branchNo);
        statisticsB2String.setInPsCnt(inPsCntAndAmtb2.getCounts()+"");
        statisticsB2String.setInPsAmt(inPsCntAndAmtb2.getAmntSums()+"");
        CountAndSumResult payRCntAndAmtb2 = mioLogStringOBMapper.payRCntAndAmtb2(polCode, startTime, endTime,branchNo);
        if (!("M".equals(statisticsB2String.getMrType()))){
            statisticsB2String.setPayRCnt(payRCntAndAmtb2.getCounts()+"");
            statisticsB2String.setPayRAmt(payRCntAndAmtb2.getAmntSums());
            statisticsB2String.setPayCrDrMrCnt("0");
            statisticsB2String.setPayCrDrMrAmt("0");
        }else {
            statisticsB2String.setPayCrDrMrCnt(payRCntAndAmtb2.getCounts()+"");
            statisticsB2String.setPayCrDrMrAmt(payRCntAndAmtb2.getAmntSums());
            statisticsB2String.setPayRCnt("0");
            statisticsB2String.setPayRAmt("0");
        }
        CountAndSumResult payE9CntAndAmt = mioLogStringOBMapper.payE9CntAndAmt(polCode, startTime, endTime, branchNo);
        statisticsB2String.setPayE9Amt(payE9CntAndAmt.getAmntSums());
        statisticsB2String.setPayE9Cnt(payE9CntAndAmt.getCounts()+"");
        CountAndSumResult payEPCntAndAmt = mioLogStringOBMapper.payEPCntAndAmt(polCode, startTime, endTime, branchNo);
        statisticsB2String.setPayEpAmt(payEPCntAndAmt.getAmntSums());
        statisticsB2String.setPayEpCnt(payEPCntAndAmt.getCounts()+"");
        CountAndSumResult payRpRsCntAndAmt = mioLogStringOBMapper.payRpRsCntAndAmt(polCode, startTime, endTime, branchNo);
        statisticsB2String.setPayRpRsAmt(payRpRsCntAndAmt.getAmntSums());
        statisticsB2String.setPayRpRsCnt(payRpRsCntAndAmt.getCounts()+"");
        return statisticsB2String;
    }
}
