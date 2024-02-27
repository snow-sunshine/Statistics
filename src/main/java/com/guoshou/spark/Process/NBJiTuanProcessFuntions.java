package com.guoshou.spark.Process;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.mapper.NB.IfOfAltStringMapper;
import com.guoshou.spark.mapper.NB.StatisticsB4StringMapper;
import com.guoshou.spark.mapper.NB.StdContractStringMapper;
import com.guoshou.spark.mapper.NB.SvrGroupStringMapper;
import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import com.guoshou.spark.po.JavaNB.StdContractString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NBJiTuanProcessFuntions {
    @Autowired
    private StdContractStringMapper stdContractStringMapper;
    @Autowired
    private SvrGroupStringMapper svrGroupStringMapper;
    @Autowired
    private StatisticsB4StringMapper statisticsB4StringMapper;
    @Autowired
    private IfOfAltStringMapper ifOfAltStringMapper;

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
        countaaa = stdContractStringMapper.selectCount(queryWrapper1);

        countbbb = svrGroupStringMapper.selEndNorCountBJiTuan(polCode, endTime, branchNo);
        statisticsB1String.setEndNorCount(countaaa + countbbb + "");

        //期末满期
        countaaa = stdContractStringMapper.selectCountAJiTuan(polCode, branchNo);
        countbbb = stdContractStringMapper.selectCountBJiTuan(polCode, branchNo);
        countccc = stdContractStringMapper.selectCountCJiTuan(polCode, branchNo);
        statisticsB1String.setEndExpCount(countaaa + countbbb + countccc + "");

        //期末失效
        long l = stdContractStringMapper.selectCountEndLoseCountJiTuan(polCode, branchNo);
        statisticsB1String.setEndLoseCount(l + "");
        //期末失效终止
        countaaa = stdContractStringMapper.selectCountEndLoseStopCountJiTuan(polCode, endTime, branchNo);
        countbbb = stdContractStringMapper.selectCountEndLoseStopCountBJiTuan(polCode, endTime, branchNo);
        statisticsB1String.setEndLoseStopCount(countaaa - countbbb + "");
        statisticsB1String.setEndCount(Long.parseLong(statisticsB1String.getEndNorCount()) +
                Long.parseLong(statisticsB1String.getEndExpCount()) + Long.parseLong(statisticsB1String.getEndLoseCount()) + Long.parseLong(statisticsB1String.getEndLoseStopCount()) + "");
        System.out.println(statisticsB1String.getPolCode()+"*********cfdSelEndOfTermDate2方法结束*******************");
    }

    public void cfdSelThisPeriodDate2(StatisticsB1String statisticsB1String, String startTime, String endTime, String branchNo) {
        String polCode = statisticsB1String.getPolCode().trim();
        String years = statisticsB1String.getYears().trim();
        String times = statisticsB1String.getTimes().trim();
        //本期补录新增
        long nowRecNewCountJiTuan = statisticsB4StringMapper.selectCountNowRecNewCountJiTuan(polCode, years, times, branchNo);
        statisticsB1String.setNowRecNewCount(nowRecNewCountJiTuan + "");

        //本期复效
        long countNowReinCount = ifOfAltStringMapper.selectCountNowReinCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowReinCount(countNowReinCount + "");

        //本期失效
        long loseCount = ifOfAltStringMapper.selectCountNowLoseCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowLoseCount(loseCount + "");

        //本期满期
        long nowExpCount1 = stdContractStringMapper.selectNowExpCountJiTuan(polCode, startTime, endTime, branchNo);
        long nowExpCount2 = stdContractStringMapper.selectNowExpCount1JiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowExpCount(nowExpCount1 + nowExpCount2 + "");

        //本期满期终止
        long nowExpStopCount = stdContractStringMapper.selectNowExpStopCountJiTuan(polCode, startTime, endTime, branchNo);
        statisticsB1String.setNowExpStopCount(nowExpStopCount + "");
        long newCount = Long.parseLong(statisticsB1String.getNowExpStopCount()) - Long.parseLong(statisticsB1String.getNowExpCount());
        statisticsB1String.setNowExpStopCount(newCount + "");

        //本期解约终止
        long nowSevStopCountA = stdContractStringMapper.selectNowSevStopCountAJiTuan(polCode, startTime, endTime,branchNo);
        statisticsB1String.setNowSevStopCount(nowSevStopCountA + "");
        //本期失效终止
        long nowSevStopCount = stdContractStringMapper.selectNowSevStopCountJiTuan(polCode, startTime, endTime,branchNo);
        long nowSevStopCount1 = stdContractStringMapper.selectNowSevStopCount1JiTuan(polCode, startTime, endTime,branchNo);
        statisticsB1String.setNowLoseStopCount(nowSevStopCount - nowSevStopCount1 + "");
        //本期其他终止
        long nowOtherStopCount = stdContractStringMapper.selectNowOtherStopCount(polCode, startTime, endTime);
        statisticsB1String.setNowOtherStopCount(nowOtherStopCount + "");

    }
}
