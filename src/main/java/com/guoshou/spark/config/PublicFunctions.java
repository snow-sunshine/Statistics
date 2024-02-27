package com.guoshou.spark.config;

import com.guoshou.spark.po.JavaNB.StatisticsB1String;
import com.guoshou.spark.po.JavaNB.StatisticsB2String;
import com.guoshou.spark.po.JavaNB.StatisticsB3String;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PublicFunctions {
    public static String[] getNowPeriodBeginEndDateB4(int year, int times) {
        String[] strings = new String[2];
        String beginDateStr;
        String endDateStr;
        if (times == 1) {
            beginDateStr = String.format("%04d-01-01", year);
            endDateStr = String.format("%04d-06-30", year);
            strings[0] = beginDateStr;
            strings[1] = endDateStr;
            return strings;
        } else {
            beginDateStr = String.format("%04d-01-01", year);
            endDateStr = String.format("%04d-12-31", year);
            strings[0] = beginDateStr;
            strings[1] = endDateStr;
            return strings;
        }
    }

    public static String getCurrentDateTime(long sCurrentTime) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = currentDate.format(formatter);
        return format;
    }

    public static void CreateCsvFileB1(int years, int times, List<String> branchNo, List<StatisticsB1String> stringList) {
        String[] nowPeriodBeginEndDateB4 = getNowPeriodBeginEndDateB4(years, times);
        //当前路径
        String currentPath = System.getProperty("user.dir");
        String fileName = String.format("%s/csv/b1.%s.%s.%s.csv", currentPath, branchNo.get(0), nowPeriodBeginEndDateB4[0], nowPeriodBeginEndDateB4[1]);
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (success) {
                System.out.println("目录已创建：" + file.getParentFile().getAbsolutePath());
            } else {
                System.out.println("目录创建失败：" + file.getParentFile().getAbsolutePath());
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // 写入标题行
            String[] header1 = {"", "", "", "", "", "", "", "", "代理业务件数统计表"};
            writer.writeNext(header1);
            String[] header2 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "表号:集团(业)统表一"};
            writer.writeNext(header2);
            // 写入报告单位行
            String[] reportUnit = {"报告单位:", "集团公司", "", "", "", "", "报告期间:", nowPeriodBeginEndDateB4[0] + "至" + nowPeriodBeginEndDateB4[1], "", "", "", "", "", "单位:件"};
            writer.writeNext(reportUnit);
            // 写入险种行
            String[] insuranceCategory = {"险种来源", "险种代码", "险种名称", "数据来源", "期初", "", "", "", "", "本期", "", "", "", "", "", "", "", "期末"};
            writer.writeNext(insuranceCategory);
            // 写入字段行
            String[] fields = {"", "", "", "", "保有", "有效", "满期", "失效", "失效终止", "补录新增", "复效", "失效", "满期", "满期终止", "解约终止", "失效终止", "其他终止", "保有", "有效", "满期", "失效", "失效终止"};
            writer.writeNext(fields);
            System.out.println(stringList.size());
            for (int i = 0; i < stringList.size(); i++) {
                StatisticsB1String statisticsB1String = stringList.get(i);
                String begCount = statisticsB1String.getBegCount();
                String begNorCount = statisticsB1String.getBegNorCount();
                String begExpCount = statisticsB1String.getBegExpCount();
                String begLoseCount = statisticsB1String.getBegLoseCount();
                String begLoseStopCount = statisticsB1String.getBegLoseStopCount();
                String nowRecNewCount = statisticsB1String.getNowRecNewCount();
                String nowReinCount = statisticsB1String.getNowReinCount();
                String nowLoseCount = statisticsB1String.getNowLoseCount();
                String nowExpCount = statisticsB1String.getNowExpCount();
                String nowExpStopCount = statisticsB1String.getNowExpStopCount();
                String nowSevStopCount = statisticsB1String.getNowSevStopCount();
                String nowLoseStopCount = statisticsB1String.getNowLoseStopCount();
                String nowOtherStopCount = statisticsB1String.getNowOtherStopCount();
                String endCount = statisticsB1String.getEndCount();
                String endNorCount = statisticsB1String.getEndNorCount();
                String endExpCount = statisticsB1String.getEndExpCount();
                String endLoseCount = statisticsB1String.getEndLoseCount();
                String endLoseStopCount = statisticsB1String.getEndLoseStopCount();
                int i1 = Integer.parseInt(begCount);  //保有
                int i2 = Integer.parseInt(begNorCount);//有效
                int i3 = Integer.parseInt(begExpCount);//满期
                int i4 = Integer.parseInt(begLoseCount);//失效
                int i5 = Integer.parseInt(begLoseStopCount);//失效终止
                int i6 = Integer.parseInt(nowRecNewCount);//补录新增
                int i7 = Integer.parseInt(nowReinCount);//复效
                int i8 = Integer.parseInt(nowLoseCount);
                int i9 = Integer.parseInt(nowExpCount);
                int i10 = Integer.parseInt(nowExpStopCount);
                int i11 = Integer.parseInt(nowSevStopCount);
                int i12 = Integer.parseInt(nowOtherStopCount);
                int i13 = Integer.parseInt(nowLoseStopCount);
                int i14 = Integer.parseInt(endCount);
                int i15 = Integer.parseInt(endNorCount);
                int i16 = Integer.parseInt(endExpCount);
                int i17 = Integer.parseInt(endLoseCount);
                int i18 = Integer.parseInt(endLoseStopCount);
                int i19 = i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9 + i10 + i11 + i12 + i13 + i14 + i15 + i16 + i17 + i18;
                if (i19 == 0) {
                    continue;
                }
                String[] datas = {statisticsB1String.getPolFrom(),
                        statisticsB1String.getPolCode(),
                        statisticsB1String.getPolName(),
                        statisticsB1String.getDateFrom(),
                        begCount,
                        begNorCount,
                        begExpCount,
                        begLoseCount,
                        begLoseStopCount,
                        nowRecNewCount,
                        nowReinCount,
                        nowLoseCount,
                        nowExpCount,
                        nowExpStopCount,
                        nowSevStopCount,
                        nowLoseStopCount,
                        nowOtherStopCount,
                        endCount,
                        endNorCount,
                        endExpCount,
                        endLoseCount,
                        endLoseStopCount,
                };
                writer.writeNext(datas);
            }

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void CreateTxtFile(String startTime, String endTime, String branchNo, Map<Integer, Map<String, Object>> stringList) {
        String currentPath = System.getProperty("user.dir");
        String fileName = String.format("%s/jituan/b1.%s", currentPath, branchNo);
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (success) {
                System.out.println("目录已创建：" + file.getParentFile().getAbsolutePath());
            } else {
                System.out.println("目录创建失败：" + file.getParentFile().getAbsolutePath());
            }
        }
        try {
            // 创建 FileWriter 对象，设置为追加模式
            FileWriter fileWriter = new FileWriter(fileName, true);
            // 创建 BufferedWriter 对象来包装 FileWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // 要写入文件的内容
            System.out.println(stringList.size());
            for (int i = 0; i < stringList.size(); i++) {
                Map<String, Object> stringObjectMap = stringList.get(i);
                com.guoshou.spark.po.JavaOB.StatisticsB1String statisticsB1String = (com.guoshou.spark.po.JavaOB.StatisticsB1String) stringObjectMap.get("statisticsB1String");
                String nowRecNewCount = statisticsB1String.getNowRecNewCount();
                String nowReinCount = statisticsB1String.getNowReinCount();
                String nowLoseCount = statisticsB1String.getNowLoseCount();
                String nowExpCount = statisticsB1String.getNowExpCount();
                String nowExpStopCount = statisticsB1String.getNowExpStopCount();
                String nowSevStopCount = statisticsB1String.getNowSevStopCount();
                String nowLoseStopCount = statisticsB1String.getNowLoseStopCount();
                String nowOtherStopCount = statisticsB1String.getNowOtherStopCount();
                String endCount = statisticsB1String.getEndCount();
                String endNorCount = statisticsB1String.getEndNorCount();
                String endExpCount = statisticsB1String.getEndExpCount();
                String endLoseCount = statisticsB1String.getEndLoseCount();
                String endLoseStopCount = statisticsB1String.getEndLoseStopCount();
                int i6 = Integer.parseInt(nowRecNewCount);//补录新增
                int i7 = Integer.parseInt(nowReinCount);//复效
                int i8 = Integer.parseInt(nowLoseCount);
                int i9 = Integer.parseInt(nowExpCount);
                int i10 = Integer.parseInt(nowExpStopCount);
                int i11 = Integer.parseInt(nowSevStopCount);
                int i12 = Integer.parseInt(nowOtherStopCount);
                int i13 = Integer.parseInt(nowLoseStopCount);
                int i14 = Integer.parseInt(endCount);
                int i15 = Integer.parseInt(endNorCount);
                int i16 = Integer.parseInt(endExpCount);
                int i17 = Integer.parseInt(endLoseCount);
                int i18 = Integer.parseInt(endLoseStopCount);
                int i19 = i6 + i7 + i8 + i9 + i10 + i11 + i12 + i13 + i14 + i15 + i16 + i17 + i18;
//                if (i19 == 0) {
//                    continue;
//                }
                String content = branchNo + "|" +
                        startTime + "|" +
                        endTime + "|" +
                        statisticsB1String.getPolCode() + "|" +
                        statisticsB1String.getPolName() + "|" +
                        stringObjectMap.get("polType") + "|" +
                        statisticsB1String.getMrType() + "|" +
                        stringObjectMap.get("polBusinessType") + "|" +
                        statisticsB1String.getDateFrom() + "|" +
                        nowRecNewCount + "|" +
                        nowReinCount + "|" +
                        nowLoseCount + "|" +
                        nowExpCount + "|" +
                        nowExpStopCount + "|" +
                        nowSevStopCount + "|" +
                        nowLoseStopCount + "|" +
                        nowOtherStopCount + "|" +
                        endCount + "|" +
                        endNorCount + "|" +
                        endExpCount + "|" +
                        endLoseCount + "|" +
                        endLoseStopCount;
                // 使用 BufferedWriter 写入内容
                bufferedWriter.write(content);
                bufferedWriter.newLine(); // 写入换行符
            }
            // 关闭资源
            bufferedWriter.close();
            System.out.println("内容已成功写入文件。");
        } catch (IOException e) {
            System.out.println("写入文件时出现错误：" + e.getMessage());
        }


        System.out.println(fileName);
    }

    public static void CreateCsvFileB2(int years, int times, List<String> branchNo, List<StatisticsB2String> stringList) {
        String[] nowPeriodBeginEndDateB4 = getNowPeriodBeginEndDateB4(years, times);
        //当前路径
        String currentPath = System.getProperty("user.dir");
        String fileName = String.format("%s/csv/b2.%s.%s.%s.csv", currentPath, branchNo.get(0), nowPeriodBeginEndDateB4[0], nowPeriodBeginEndDateB4[1]);
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (success) {
                System.out.println("目录已创建：" + file.getParentFile().getAbsolutePath());
            } else {
                System.out.println("目录创建失败：" + file.getParentFile().getAbsolutePath());
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // 写入标题行
            String[] header1 = {"", "", "", "", "", "", "", "", "代理业务收支统计表"};
            writer.writeNext(header1);
            String[] header2 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "表号:集团(业)统表二"};
            writer.writeNext(header2);
            // 写入报告单位行
            String[] reportUnit = {"报告单位:", "集团公司", "", "", "", "", "报告期间:", nowPeriodBeginEndDateB4[0] + "至" + nowPeriodBeginEndDateB4[1], "", "", "", "件数单位：件", "金额单位：元"};
            writer.writeNext(reportUnit);
            // 写入险种行
            String[] insuranceCategory = {"险种来源", "险种代码", "险种名称", "数据来源", "保费收入", "", "赔款支出", "", "死亡/伤残/医疗给付", "", "满期给付", "", "年金给付", "", "解约给付"};
            writer.writeNext(insuranceCategory);
            // 写入字段行
            String[] fields = {"", "", "", "", "本期件数", "本期金额", "本期件数", "本期金额", "本期件数", "本期金额", "本期件数", "本期金额", "本期件数", "本期金额", "本期件数", "本期金额"};
            writer.writeNext(fields);
            System.out.println(stringList.size());
            for (int i = 0; i < stringList.size(); i++) {
                StatisticsB2String statisticsB2String = stringList.get(i);
                String inPsCnt = statisticsB2String.getInPsCnt();
                String payRCnt = statisticsB2String.getPayRCnt();
                String payCrDrMrCnt = statisticsB2String.getPayCrDrMrCnt();
                String payE9Cnt = statisticsB2String.getPayE9Cnt();
                String payEpCnt = statisticsB2String.getPayEpCnt();
                String payRpRsCnt = statisticsB2String.getPayRpRsCnt();
                int i1 = Integer.parseInt(inPsCnt);
                int i2 = Integer.parseInt(payRCnt);
                int i3 = Integer.parseInt(payCrDrMrCnt);
                int i4 = Integer.parseInt(payE9Cnt);
                int i5 = Integer.parseInt(payEpCnt);
                int i6 = Integer.parseInt(payRpRsCnt);
                int i7 = i1 + i2 + i3 + i4 + i5 + i6;
                if (i7 == 0) {
                    continue;
                }
                String[] datas = {statisticsB2String.getPolFrom(),
                        statisticsB2String.getPolCode(),
                        statisticsB2String.getPolName(),
                        statisticsB2String.getDateFrom(),
                        inPsCnt,
                        statisticsB2String.getInPsAmt(),
                        payRCnt,
                        statisticsB2String.getPayRAmt(),
                        payCrDrMrCnt,
                        statisticsB2String.getPayCrDrMrAmt(),
                        payE9Cnt,
                        statisticsB2String.getPayE9Amt(),
                        payEpCnt,
                        statisticsB2String.getPayEpAmt(),
                        payRpRsCnt,
                        statisticsB2String.getPayRpRsAmt()
                };
                writer.writeNext(datas);
            }

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateCsvFileB3(int years, int times, List<String> branchNo, List<StatisticsB3String> stringList) {
        String[] nowPeriodBeginEndDateB4 = getNowPeriodBeginEndDateB4(years, times);
        //当前路径
        String currentPath = System.getProperty("user.dir");
        String fileName = String.format("%s/csv/b3.%s.%s.%s.csv", currentPath, branchNo.get(0), nowPeriodBeginEndDateB4[0], nowPeriodBeginEndDateB4[1]);
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdirs();
            if (success) {
                System.out.println("目录已创建：" + file.getParentFile().getAbsolutePath());
            } else {
                System.out.println("目录创建失败：" + file.getParentFile().getAbsolutePath());
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // 写入标题行
            String[] header1 = {"", "", "", "", "", "", "", "", "代理储金业务统计表"};
            writer.writeNext(header1);
            String[] header2 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "表号:集团(业)统表三"};
            writer.writeNext(header2);
            // 写入报告单位行
            String[] reportUnit = {"报告单位:", "集团公司", "", "", "", "", "报告期间:", nowPeriodBeginEndDateB4[0] + "至" + nowPeriodBeginEndDateB4[1], "", "", "", "单位：件", "金额单位：元"};
            writer.writeNext(reportUnit);
            // 写入险种行
            String[] insuranceCategory = {"险种来源", "险种代码", "险种名称", "数据来源", "赔款支出", "", "储金返还", "", "期末储金余额"};
            writer.writeNext(insuranceCategory);
            // 写入字段行
            String[] fields = {"", "", "", "", "本期件数", "本期金额", "本期件数", "本期金额"};
            writer.writeNext(fields);
            for (int i = 0; i < stringList.size(); i++) {
                StatisticsB3String statisticsB3String = stringList.get(i);
                String[] datas = {statisticsB3String.getPolFrom(),
                        statisticsB3String.getPolCode(),
                        statisticsB3String.getPolName(),
                        statisticsB3String.getDateFrom(),
                        statisticsB3String.getPayRCnt(),
                        statisticsB3String.getPayRAmt(),
                        statisticsB3String.getPayXCnt(),
                        statisticsB3String.getPayXAmt(),
                        statisticsB3String.getSurXAmt()
                };
                writer.writeNext(datas);
            }

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}