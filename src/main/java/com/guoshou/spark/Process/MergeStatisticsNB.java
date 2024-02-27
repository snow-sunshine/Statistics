package com.guoshou.spark.Process;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoshou.spark.mapper.NB.StatisticsB1StringMapper;
import com.guoshou.spark.mapper.NB.StatisticsB2StringMapper;
import com.guoshou.spark.mapper.NB.StatisticsB3StringMapper;
import com.guoshou.spark.mapper.OB.StatisticsB1StringOBMapper;
import com.guoshou.spark.mapper.OB.StatisticsB2StringOBMapper;
import com.guoshou.spark.mapper.OB.StatisticsB3StringOBMapper;
import com.guoshou.spark.po.JavaOB.StatisticsB1String;
import com.guoshou.spark.po.JavaOB.StatisticsB2String;
import com.guoshou.spark.po.JavaOB.StatisticsB3String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MergeStatisticsNB {

    @Autowired
    private StatisticsB1StringMapper statisticsB1StringMapper;
    @Autowired
    private StatisticsB1StringOBMapper statisticsB1StringOBMapper;
    @Autowired
    private StatisticsB3StringMapper statisticsB3StringMapper;
    @Autowired
    private StatisticsB3StringOBMapper statisticsB3StringOBMapper;
    @Autowired
    private StatisticsB2StringMapper statisticsB2StringMapper;
    @Autowired
    private StatisticsB2StringOBMapper statisticsB2StringOBMapper;

    /**
     * nb、ob数据合并   StatisticsB1表    全部写入nb库表中
     * @param years
     * @param times
     */
    public void MergeStatisticsB1(String years, String times) {

        List<StatisticsB1String> statisticsB1OB = statisticsB1StringOBMapper.selAllStatisticsB1OB(years, times);
        int size = statisticsB1OB.size();
        if (size != 0) {
            for (StatisticsB1String statisticsB1StringOB : statisticsB1OB) {
                com.guoshou.spark.po.JavaNB.StatisticsB1String statisticsB1StringNB = new com.guoshou.spark.po.JavaNB.StatisticsB1String();
                statisticsB1StringNB.setYears(statisticsB1StringOB.getYears());
                statisticsB1StringNB.setTimes(statisticsB1StringOB.getTimes());
                statisticsB1StringNB.setPolFrom(statisticsB1StringOB.getPolFrom());
                statisticsB1StringNB.setPolCode(statisticsB1StringOB.getPolCode());
                statisticsB1StringNB.setPolName(statisticsB1StringOB.getPolName());
                statisticsB1StringNB.setDateFrom(statisticsB1StringOB.getDateFrom());
                statisticsB1StringNB.setBegCount(statisticsB1StringOB.getBegCount());
                statisticsB1StringNB.setBegNorCount(statisticsB1StringOB.getBegNorCount());
                statisticsB1StringNB.setBegExpCount(statisticsB1StringOB.getBegExpCount());
                statisticsB1StringNB.setBegLoseCount(statisticsB1StringOB.getBegLoseCount());
                statisticsB1StringNB.setBegLoseStopCount(statisticsB1StringOB.getBegLoseStopCount());
                statisticsB1StringNB.setNowRecNewCount(statisticsB1StringOB.getNowRecNewCount());
                statisticsB1StringNB.setNowReinCount(statisticsB1StringOB.getNowReinCount());
                statisticsB1StringNB.setNowLoseCount(statisticsB1StringOB.getNowLoseCount());
                statisticsB1StringNB.setNowExpCount(statisticsB1StringOB.getNowExpCount());
                statisticsB1StringNB.setEndExpCount(statisticsB1StringOB.getEndExpCount());
                statisticsB1StringNB.setNowExpStopCount(statisticsB1StringOB.getNowExpStopCount());
                statisticsB1StringNB.setNowSevStopCount(statisticsB1StringOB.getNowSevStopCount());
                statisticsB1StringNB.setNowLoseStopCount(statisticsB1StringOB.getNowLoseStopCount());
                statisticsB1StringNB.setNowOtherStopCount(statisticsB1StringOB.getNowOtherStopCount());
                statisticsB1StringNB.setEndCount(statisticsB1StringOB.getEndCount());
                statisticsB1StringNB.setEndNorCount(statisticsB1StringOB.getEndNorCount());
                statisticsB1StringNB.setEndLoseCount(statisticsB1StringOB.getEndLoseCount());
                statisticsB1StringNB.setEndLoseStopCount(statisticsB1StringOB.getEndLoseStopCount());
                statisticsB1StringNB.setMrType(statisticsB1StringOB.getMrType());
                statisticsB1StringNB.setProcDate(statisticsB1StringOB.getProcDate());
                statisticsB1StringMapper.insert(statisticsB1StringNB);
            }
        }


    }

    public void SelInsertStatisticsB1(String years, String times) {
        statisticsB1StringMapper.insertPolode0(years, times);
        statisticsB1StringMapper.insertPolode1(years, times);
        statisticsB1StringMapper.insertPolode2(years, times);
        statisticsB1StringMapper.insertPolode4(years, times);
        statisticsB1StringMapper.insertPolode5(years, times);
        statisticsB1StringMapper.insertPolode6(years, times);
        statisticsB1StringMapper.insertPolode7(years, times);
        statisticsB1StringMapper.insertPolode8(years, times);
        statisticsB1StringMapper.insertPolode9(years, times);
    }

    /**
     * nb、ob数据合并   StatisticsB2表
     * @param years
     * @param times
     */
    public void MergeStatisticsB2(String years, String times) {
        List<StatisticsB2String> statisticsB2Strings = statisticsB2StringOBMapper.selAllStatisticsB2OB(years,times);
        int size = statisticsB2Strings.size();
        if (size > 0) {
            for (StatisticsB2String statisticsB2String : statisticsB2Strings) {
                com.guoshou.spark.po.JavaNB.StatisticsB2String statisticsB2StringNB = new com.guoshou.spark.po.JavaNB.StatisticsB2String();
                statisticsB2StringNB.setYears(statisticsB2String.getYears());
                statisticsB2StringNB.setTimes(statisticsB2String.getTimes());
                statisticsB2StringNB.setPolFrom(statisticsB2String.getPolFrom());
                statisticsB2StringNB.setPolCode(statisticsB2String.getPolCode());
                statisticsB2StringNB.setPolName(statisticsB2String.getPolName());
                statisticsB2StringNB.setDateFrom(statisticsB2String.getDateFrom());
                statisticsB2StringNB.setInPsCnt(statisticsB2String.getInPsCnt());
                statisticsB2StringNB.setInPsAmt(statisticsB2String.getInPsAmt());
                statisticsB2StringNB.setPayRCnt(statisticsB2String.getPayRCnt());
                statisticsB2StringNB.setPayRAmt(statisticsB2String.getPayRAmt());
                statisticsB2StringNB.setPayCrDrMrCnt(statisticsB2String.getPayCrDrMrCnt());
                statisticsB2StringNB.setPayCrDrMrAmt(statisticsB2String.getPayCrDrMrAmt());
                statisticsB2StringNB.setPayE9Cnt(statisticsB2String.getPayE9Cnt());
                statisticsB2StringNB.setPayE9Amt(statisticsB2String.getPayE9Amt());
                statisticsB2StringNB.setPayEpCnt(statisticsB2String.getPayEpCnt());
                statisticsB2StringNB.setPayEpAmt(statisticsB2String.getPayEpAmt());
                statisticsB2StringNB.setPayRpRsCnt(statisticsB2String.getPayRpRsCnt());
                statisticsB2StringNB.setPayRpRsAmt(statisticsB2String.getPayRpRsAmt());
                statisticsB2StringNB.setMrType(statisticsB2String.getMrType());
                statisticsB2StringNB.setProcDate(statisticsB2String.getProcDate());
                statisticsB2StringMapper.insert(statisticsB2StringNB);
            }
        }
    }

    public void SelInsertStatisticsB2(String years, String times){
        statisticsB2StringMapper.insertPolode0(years, times);
        statisticsB2StringMapper.insertPolode1(years, times);
        statisticsB2StringMapper.insertPolode2(years, times);
        statisticsB2StringMapper.insertPolode3(years, times);
        statisticsB2StringMapper.insertPolode4(years, times);
        statisticsB2StringMapper.insertPolode5(years, times);
        statisticsB2StringMapper.insertPolode6(years, times);
        statisticsB2StringMapper.insertPolode7(years, times);
        statisticsB2StringMapper.insertPolode8(years, times);
    }

    public void MergeStatisticsB3(String years, String times) {
        List<StatisticsB3String> statisticsB3Strings = statisticsB3StringOBMapper.selAllStatisticsB3OB(years,times);
        int size = statisticsB3Strings.size();
        if (size > 0) {
            for (StatisticsB3String statisticsB3String : statisticsB3Strings) {
                com.guoshou.spark.po.JavaNB.StatisticsB3String statisticsB3StringNB = new com.guoshou.spark.po.JavaNB.StatisticsB3String();
                statisticsB3StringNB.setYears(statisticsB3String.getYears());
                statisticsB3StringNB.setTimes(statisticsB3String.getTimes());
                statisticsB3StringNB.setPolFrom(statisticsB3String.getPolFrom());
                statisticsB3StringNB.setPolCode(statisticsB3String.getPolCode());
                statisticsB3StringNB.setPolName(statisticsB3String.getPolName());
                statisticsB3StringNB.setDateFrom(statisticsB3String.getDateFrom());
                statisticsB3StringNB.setPayRCnt(statisticsB3String.getPayRCnt());
                statisticsB3StringNB.setPayRAmt(statisticsB3String.getPayRAmt());
                statisticsB3StringNB.setPayXCnt(statisticsB3String.getPayXCnt());
                statisticsB3StringNB.setPayXAmt(statisticsB3String.getPayXAmt());
                statisticsB3StringNB.setSurXAmt(statisticsB3String.getSurXAmt());
                statisticsB3StringNB.setMrType(statisticsB3String.getMrType());
                statisticsB3StringNB.setProcDate(statisticsB3String.getProcDate());
                statisticsB3StringMapper.insert(statisticsB3StringNB);
            }
        }
    }

    public void SelInsertStatisticsB3(String years, String times) {
        statisticsB3StringMapper.insertPolode0(years, times);
        statisticsB3StringMapper.insertPolode1(years, times);
        statisticsB3StringMapper.insertPolode2(years, times);
        statisticsB3StringMapper.insertPolode3(years, times);
        statisticsB3StringMapper.insertPolode4(years, times);
        statisticsB3StringMapper.insertPolode5(years, times);
        statisticsB3StringMapper.insertPolode6(years, times);
        statisticsB3StringMapper.insertPolode7(years, times);
        statisticsB3StringMapper.insertPolode8(years, times);
    }
}
