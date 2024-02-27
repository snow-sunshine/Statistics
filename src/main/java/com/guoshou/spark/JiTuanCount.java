package com.guoshou.spark;

import com.guoshou.spark.Process.CreateJiTuanNBStatisticsFunctions;
import com.guoshou.spark.Process.CreateJiTuanStatisticsFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JiTuanCount {

    @Autowired
    private CreateJiTuanStatisticsFunctions createJiTuanStatisticsFunctions;
    @Autowired
    private CreateJiTuanNBStatisticsFunctions createJiTuanNBStatisticsFunctions;

//    @PostConstruct
//    public void JituanCount(){
//        System.out.println("---------------JituanCount--------------------------------");
////        createJiTuanStatisticsFunctions.CreateJiTuanStatisticsB1(2023,2,"330200");
//        new Thread(() -> {
//            createJiTuanStatisticsFunctions.CreateJiTuanStatisticsB1(2023,2,"330200");
//        }).start();
//    }

//    @PostConstruct
    public void JituanCount1(){
        System.out.println("---------------JituanCount1--------------------------------");
//        createJiTuanNBStatisticsFunctions.CreateJiTuanStatisticsB1(2023,2,"330200");
//        new Thread(() -> {
//            createJiTuanNBStatisticsFunctions.CreateJiTuanStatisticsB2(2023,2,"330200");
//        }).start();
//        createJiTuanNBStatisticsFunctions.CreateJiTuanStatisticsB2(2023,2,"330200");
        createJiTuanNBStatisticsFunctions.CreateJiTuanStatisticsB3(2023,2,"330200");
    }

    @PostConstruct
    public void JituanCount4(){
        System.out.println("开始");
        createJiTuanStatisticsFunctions.CreateJiTuanStatisticsB4(2023,2,"330200");
        System.out.println("开始nb");
        createJiTuanStatisticsFunctions.CreateJiTuanStatisticsB4NB(2023,2,"330200");
    }
}
