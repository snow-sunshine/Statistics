package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.IfOfAltString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface IfOfAltStringOBMapper extends BaseMapper<IfOfAltString> {
    @DS("OB")
    @Select("SELECT count(*) " +
            "FROM cl_cntr5_if_of_alt " +
            "WHERE cntrId in (select cntrId from cl_cntr5_std_contract where polCode=#{polCode}) " +
            "AND STR_TO_DATE(outForceDate, '%Y-%m-%d') < STR_TO_DATE(reInForceDate, '%Y-%m-%d') " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') <> '9999-12-31' " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') >= #{startTime} " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') <= #{endTime}")
    //select count(*)
    long selectCountNowReinCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //    @Select("SELECT count(*) \n" +
//            "FROM cl_cntr4_if_of_alt\n" +
//            "WHERE cntrId in (select cntrId from cl_cntr4_std_contract where cntrStat='L' and polCode=#{polCode})\n" +
//            "AND STR_TO_DATE(outForceDate, '%Y-%m-%d') > STR_TO_DATE(reInForceDate, '%Y-%m-%d') " +
//            "or STR_TO_DATE(reInForceDate, '%Y-%m-%d') = '9999-12-31'\n" +
//            "\tAND STR_TO_DATE(outForceDate, '%Y-%m-%d') >= #{startTime}\n" +
//            "AND STR_TO_DATE(outForceDate, '%Y-%m-%d') <= #{endTime}")
    @DS("OB")
    @Select("SELECT COUNT(*) \n" +
            "FROM cl_cntr5_if_of_alt\n" +
            "WHERE cntrId IN (\n" +
            "    SELECT cntrId \n" +
            "    FROM cl_cntr5_std_contract \n" +
            "    WHERE cntrStat = 'L' AND polCode = #{polCode}\n" +
            ")\n" +
            "AND (\n" +
            "    (\n" +
            "        STR_TO_DATE(outForceDate, '%Y-%m-%d') > STR_TO_DATE(reInForceDate, '%Y-%m-%d') \n" +
            "        OR STR_TO_DATE(reInForceDate, '%Y-%m-%d') = '9999-12-31'\n" +
            "    )\n" +
            "    AND STR_TO_DATE(outForceDate, '%Y-%m-%d') BETWEEN #{startTime} AND #{endTime}\n" +
            ");")
    long selectCountNowLoseCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //-------------------------------------------------------------------------------------------------
    @DS("OB")
    @Select("SELECT count(*) " +
            "FROM cl_cntr5_if_of_alt " +
            "WHERE cntrId in (select cntrId from cl_cntr5_std_contract where polCode=#{polCode} and mgrBranchNo=#{branchNo}) " +
            "AND STR_TO_DATE(outForceDate, '%Y-%m-%d') < STR_TO_DATE(reInForceDate, '%Y-%m-%d') " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') <> '9999-12-31' " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') >= #{startTime} " +
            "AND STR_TO_DATE(reInForceDate, '%Y-%m-%d') <= #{endTime}")
    long selectCountNowReinCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("branchNo") String branchNo);

    @DS("OB")
    @Select("SELECT COUNT(*) \n" +
            "FROM cl_cntr5_if_of_alt\n" +
            "WHERE cntrId IN (\n" +
            "    SELECT cntrId \n" +
            "    FROM cl_cntr5_std_contract \n" +
            "    WHERE cntrStat = 'L' AND polCode = #{polCode} and mgrBranchNo=#{branchNo}" +
            ")\n" +
            "AND (" +
            "(" +
            "        STR_TO_DATE(outForceDate, '%Y-%m-%d') > STR_TO_DATE(reInForceDate, '%Y-%m-%d') \n" +
            "        OR STR_TO_DATE(reInForceDate, '%Y-%m-%d') = '9999-12-31'\n" +
            "    )" +
            "    AND STR_TO_DATE(outForceDate, '%Y-%m-%d') BETWEEN #{startTime} AND #{endTime}\n" +
            ");")
    long selectCountNowLoseCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);
}
