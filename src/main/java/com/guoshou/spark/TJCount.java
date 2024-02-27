package com.guoshou.spark;

import com.guoshou.spark.Process.MergeStatisticsNB;
import com.guoshou.spark.Process.OB.OBStatisticsFunction;
import com.guoshou.spark.Process.StatisticsFunction;
import com.guoshou.spark.Process.NBTjProcessFuntions;
import com.guoshou.spark.Process.OB.OBTjProcessFuntions;
import com.guoshou.spark.config.PublicFunctions;
import com.guoshou.spark.po.JavaNB.BizParameterString;
import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import com.guoshou.spark.po.JavaNB.StatisticsB2String;
import com.guoshou.spark.po.JavaNB.StatisticsB3String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TJCount {

    @Autowired
    private NBTjProcessFuntions NBTjProcessFuntions;
    @Autowired
    private OBTjProcessFuntions OBTjProcessFuntions;
    @Autowired
    private StatisticsFunction statisticsFunction;
    @Autowired
    private OBStatisticsFunction obStatisticsFunction;
    @Autowired
    private MergeStatisticsNB mergeStatisticsNB;

    //    @PostConstruct
    public void countsB1() {
        int times = 2;
        int years = 2023;
        List<String> branchNo = new ArrayList<>();
        branchNo.add("330200");
        String obpsOrNbpsFlag = "";
        List<String> dateFrom = new ArrayList<>();
//
//        years = Integer.parseInt(args[0])
//        times = Integer.parseInt(args[1]);
//        branchNo = Arrays.asList(args[2]);
        System.out.println("正在统计NB数据……");
        dateFrom.add("PICC1");
        dateFrom.add("PICC3");
//         删除 STATISTICS_B1 表中符合条件的记录，条件是 'PICC1'或'手工' 或 '自有'。
        int delTol = NBTjProcessFuntions.DelStatisticsB1DateFrom(dateFrom);
        System.out.println("NB DelStatisticsB1DateFrom 删除了" + delTol + "条数据");
        if (delTol < 0) {
            System.out.println("DelStatisticsB1DateFrom 删除失败");
        }

        //查询biz_param_code='OBPSORNBPS_FLAG的  obpsOrNbpsFlag赋值
        List<BizParameterString> bizParameterStrings = NBTjProcessFuntions.CfdSelBizParameterObOrNbFlag();
        if (bizParameterStrings.size() == 0) {
            System.out.println("未查询到");
        } else {
            obpsOrNbpsFlag = bizParameterStrings.get(0).getBizParamVal();
            System.out.println("obpsOrNbpsFlag 的值为  " + obpsOrNbpsFlag);
        }


        System.out.println("--------StatisticsB1方法-----------------");
        long l = System.currentTimeMillis();
        statisticsFunction.StatisticsB1(years, times, obpsOrNbpsFlag);
        long l1 = System.currentTimeMillis();
        System.out.println("统计NB数据用时" + (l1 - l) / 1000 + "秒");
        System.out.println("统计NB数据完毕……\n");

        System.out.println("正在统计OB数据…………");
        Long delStatisticsB1DateFrom = OBTjProcessFuntions.DelStatisticsB1DateFrom(dateFrom);
        System.out.println("OB DelStatisticsB1DateFrom 删除了" + delStatisticsB1DateFrom + "条数据");
        obpsOrNbpsFlag = OBTjProcessFuntions.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        obStatisticsFunction.StatisticsB1(2023, 2, obpsOrNbpsFlag);
        System.out.println("统计OB数据完毕…… ");


        System.out.println("正在合并NB、OB数据…………");
        mergeStatisticsNB.MergeStatisticsB1(years + "", times + "");
        System.out.println("合并NB、OB数据完毕…………");

        System.out.println("统计总和完毕…………");
        mergeStatisticsNB.SelInsertStatisticsB1(years + "", times + "");
        List<StatisticsB1String> statisticsB1Strings = NBTjProcessFuntions.SelStatisticsB1ByYearAndTimes(years, times);
        PublicFunctions.CreateCsvFileB1(years, times, branchNo, statisticsB1Strings);
        System.out.println("正在生成文件……\n");
    }

    //    @PostConstruct
    public void countsB2() {
        int times = 2;
        int years = 2023;
        List<String> branchNo = new ArrayList<>();
        branchNo.add("330200");
        String obpsOrNbpsFlag = "";
        List<String> dateFrom = new ArrayList<>();
        System.out.println("正在统计NB数据……");
        dateFrom.add("PICC1");
        dateFrom.add("PICC3");
//         删除 STATISTICS_B1 表中符合条件的记录，条件是 'PICC1'或'手工' 或 '自有'。
        int delTol = NBTjProcessFuntions.DelStatisticsB1DateFrom(dateFrom);
        System.out.println("NB DelStatisticsB1DateFrom 删除了" + delTol + "条数据");
        if (delTol < 0) {
            System.out.println("DelStatisticsB1DateFrom 删除失败");
        }

        //查询biz_param_code='OBPSORNBPS_FLAG的  obpsOrNbpsFlag赋值
        List<BizParameterString> bizParameterStrings = NBTjProcessFuntions.CfdSelBizParameterObOrNbFlag();
        if (bizParameterStrings.size() == 0) {
            System.out.println("未查询到");
        } else {
            obpsOrNbpsFlag = bizParameterStrings.get(0).getBizParamVal();
            System.out.println("obpsOrNbpsFlag 的值为  " + obpsOrNbpsFlag);
        }
        System.out.println("--------StatisticsB2方法-----------------");
        statisticsFunction.StatisticsB2(years, times, obpsOrNbpsFlag);
        System.out.println("-------- ob StatisticsB2方法-----------------");
        obpsOrNbpsFlag = OBTjProcessFuntions.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        obStatisticsFunction.StatisticsB2(years, times, obpsOrNbpsFlag);
        mergeStatisticsNB.MergeStatisticsB2(years + "", times + "");
        mergeStatisticsNB.SelInsertStatisticsB2(years + "", times + "");
        List<StatisticsB2String> statisticsB2Strings = NBTjProcessFuntions.SelStatisticsB2ByYearAndTimes(years, times);
        PublicFunctions.CreateCsvFileB2(years, times, branchNo, statisticsB2Strings);
    }

//    @PostConstruct
    public void countsB3() {
        int times = 2;
        int years = 2023;
        List<String> branchNo = new ArrayList<>();
        branchNo.add("330200");
        String obpsOrNbpsFlag = "";
        List<String> dateFrom = new ArrayList<>();
        System.out.println("正在统计NB数据……");
        dateFrom.add("PICC1");
        dateFrom.add("PICC3");
        //查询biz_param_code='OBPSORNBPS_FLAG的  obpsOrNbpsFlag赋值
        List<BizParameterString> bizParameterStrings = NBTjProcessFuntions.CfdSelBizParameterObOrNbFlag();
        if (bizParameterStrings.size() == 0) {
            System.out.println("未查询到");
        } else {
            obpsOrNbpsFlag = bizParameterStrings.get(0).getBizParamVal();
            System.out.println("obpsOrNbpsFlag 的值为  " + obpsOrNbpsFlag);
        }
        System.out.println("--------StatisticsB3方法-----------------");
        statisticsFunction.StatisticsB3(years, times, obpsOrNbpsFlag);
        System.out.println("-------- ob StatisticsB2方法-----------------");
        obpsOrNbpsFlag = OBTjProcessFuntions.SelBizParameterObOrNbFlag().get(0).getBizParamVal();
        obStatisticsFunction.StatisticsB3(years, times, obpsOrNbpsFlag);
        mergeStatisticsNB.MergeStatisticsB3(years + "", times + "");
        mergeStatisticsNB.SelInsertStatisticsB3(years + "", times + "");
        List<StatisticsB3String> statisticsB3Strings = NBTjProcessFuntions.SelStatisticsB3ByYearAndTimes(years, times);
        PublicFunctions.CreateCsvFileB3(years, times, branchNo, statisticsB3Strings);
    }
}