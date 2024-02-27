package com.guoshou.spark.mapper.NB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.guoshou.spark.po.JavaNB.StdContractString;
import com.guoshou.spark.po.JavaOB.CntrSubStateString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface StdContractStringMapper extends BaseMapper<StdContractString> {

    //期末满期
    @Select("SELECT COUNT(DISTINCT a.cntrNo) \n" +
            "FROM cl_cntr4_std_contract a\n" +
            "JOIN cl_biz4_endow_acc b ON a.cntrNo = b.accNo\n" +
            "WHERE a.polCode = #{polCode}\n" +
            "  AND a.cntrStat = 'T'\n" +
            "  AND a.cntrTermCause = '7'\n" +
            "  AND b.accStat = 'N';\n")
    long selectCountA(@Param("polCode") String polCode);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a, cl_biz4_plnmio_rec b\n" +
            " WHERE a.polCode= #{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "and a.cntrNo=b.cntrNo;")
//    @Select("SELECT count(distinct(a.cntrNo)) \n" +
//            "FROM cl_cntr4_std_contract a, cl_biz4_plnmio_rec b\n" +
//            " WHERE a.polCode= #{polCode}\n" +
//            "AND a.cntrStat='T' \n")
    long selectCountB(@Param("polCode") String polCode);

    @Select("SELECT count(b.cntrNo) \n" +
            "FROM cl_biz4_svr_group a, cl_biz4_plnmio_rec b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "and a.sgNo=b.cntrNo;")
    long selectCountC(@Param("polCode") String polCode);

    //期末失效
    @Select("SELECT count(*) FROM cl_cntr4_std_contract WHERE polCode=#{polCode} AND  cntrStat='L'")
    long selectCountEndLoseCount(@Param("polCode") String polCode);

    //期末失效终止
    @Select("SELECT count(*) \n" +
            " FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat in ('T', 'O')\n" +
            "AND  cntrTermCause = '1'\n" +
            "and  cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}")
    long selectCountEndLoseStopCount(@Param("polCode") String polCode, @Param("endTime") String endTime);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a , cl_biz4_mtn_alt_cntr b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND  a.cntrStat in ('T', 'O')\n" +
            "AND  a.cntrTermCause = '1'\n" +
            "and a.cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.mtnBizNo\n" +
            "and b.procStat <>'1'\n" +
            "and b.mtnItemCode = '04';")
    long selectCountEndLoseStopCountB(@Param("polCode") String polCode, @Param("endTime") String endTime);

    @Select("SELECT count(distinct(a.cntrNo))  \n" +
            "FROM cl_cntr4_std_contract a, cl_biz4_endow_acc b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.accNo\n" +
            "AND STR_TO_DATE(b.accOpenDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(b.accOpenDate, '%Y-%m-%d') <=#{endTime}\n" +
            "  and b.accStat='N'")
    long selectNowExpCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a, cl_biz4_plnmio_rec b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.cntrNo\n" +
            "and b.mioItemCode in ('ER','AR')\n" +
            "AND STR_TO_DATE(b.plnmioDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(b.plnmioDate, '%Y-%m-%d') <=#{endTime}")
    long selectNowExpCount1(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            " AND  cntrStat='T'\n" +
            " AND  cntrTermCause='7'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d')  <=#{endTime}")
    long selectNowExpStopCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);


    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat in ('T', 'O')\n" +
            "AND  cntrTermCause = '1'\n" +
            "and  cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d')  <=#{endTime}")
    long selectNowSevStopCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a , cl_biz4_mtn_alt_cntr b\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  a.cntrStat in ('T', 'O')\n" +
            "AND  a.cntrTermCause = '1'\n" +
            "and a.cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.mtnBizNo\n" +
            "and b.procStat <>'1'\n" +
            "and b.mtnItemCode = '04';")
    long selectNowSevStopCount1(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期解约终止
    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat='T'\n" +
            "AND  cntrTermCause in ('2','3','5','A','B','D','H','I','J','M','N','P')\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}")
    long selectNowSevStopCountA(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat='T'\n" +
            "AND  (cntrTermCause not in('1','2','3','5','A','B','N','H','I','J','M','C','P','7','D') or cntrTermCause is null)\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}")
    long selectNowOtherStopCount(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);


    //--------------------------------------------------------------------------------------------------------------------------
    @Select("SELECT COUNT(DISTINCT a.cntrNo) " +
            "FROM cl_cntr4_std_contract a " +
            "JOIN cl_biz4_endow_acc b ON a.cntrNo = b.accNo " +
            "WHERE a.polCode = #{polCode} " +
            "  AND a.cntrStat = 'T' " +
            "  AND a.cntrTermCause = '7' and a.mgrBranchNo=#{branchNo} " +
            "  AND b.accStat = 'N'; ")
    long selectCountAJiTuan(@Param("polCode") String polCode,@Param("branchNo") String branchNo);

    @Select("SELECT count(distinct(a.cntrNo))  " +
            "FROM cl_cntr4_std_contract a, cl_biz4_plnmio_rec b " +
            " WHERE a.polCode= #{polCode} " +
            "AND a.cntrStat='T' and a.mgrBranchNo=#{branchNo}" +
            "AND a.cntrTermCause='7' " +
            "and a.cntrNo=b.cntrNo;")
    long selectCountBJiTuan(@Param("polCode") String polCode,@Param("branchNo") String branchNo);

    @Select("SELECT count(b.cntrNo) " +
            "FROM cl_biz4_svr_group a, cl_biz4_plnmio_rec b " +
            "WHERE a.polCode=#{polCode} " +
            "AND a.cntrStat='T' and a.mgrBranchNo=#{branchNo}" +
            "AND a.cntrTermCause='7' " +
            "and a.sgNo=b.cntrNo")
    long selectCountCJiTuan(@Param("polCode") String polCode,@Param("branchNo") String branchNo);

    //期末失效
    @Select("SELECT count(*) FROM cl_cntr4_std_contract WHERE polCode=#{polCode} AND  cntrStat='L' " +
            "and mgrBranchNo=#{branchNo}")
    long selectCountEndLoseCountJiTuan(@Param("polCode") String polCode,@Param("branchNo") String branchNo);

    //期末失效终止
    @Select("SELECT count(*) " +
            " FROM cl_cntr4_std_contract " +
            "WHERE polCode=#{polCode} " +
            "AND  cntrStat in ('T', 'O') " +
            "AND  cntrTermCause = '1' and mgrBranchNo=#{branchNo}" +
            "and  cntrType='P' " +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}")
    long selectCountEndLoseStopCountJiTuan(@Param("polCode") String polCode, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(distinct(a.cntrNo)) " +
            "FROM cl_cntr4_std_contract a , cl_biz4_mtn_alt_cntr b " +
            "WHERE a.polCode=#{polCode} " +
            "AND  a.cntrStat in ('T', 'O') " +
            "AND  a.cntrTermCause = '1' " +
            "and a.cntrType='P' " +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime} " +
            "and a.cntrNo=b.mtnBizNo " +
            "and b.procStat <>'1' " +
            "and b.mtnItemCode = '04' and mgrBranchNo=#{branchNo}")
    long selectCountEndLoseStopCountBJiTuan(@Param("polCode") String polCode, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(distinct(a.cntrNo))  \n" +
            "FROM cl_cntr4_std_contract a, cl_biz4_endow_acc b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.accNo\n" +
            "AND STR_TO_DATE(b.accOpenDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(b.accOpenDate, '%Y-%m-%d') <=#{endTime}\n" +
            "AND a.mgrBranchNo =#{branchNo} and b.accStat='N'")
    long selectNowExpCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a, cl_biz4_plnmio_rec b\n" +
            "WHERE a.polCode=#{polCode}\n" +
            "AND a.cntrStat='T'\n" +
            "AND a.cntrTermCause='7'\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(a.cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.cntrNo\n" +
            "and b.mioItemCode in ('ER','AR')\n" +
            "AND STR_TO_DATE(b.plnmioDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(b.plnmioDate, '%Y-%m-%d') <=#{endTime} AND a.mgrBranchNo =#{branchNo}")
    long selectNowExpCount1JiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            " AND  cntrStat='T'\n" +
            " AND  cntrTermCause='7'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d')  <=#{endTime} and mgrBranchNo =#{branchNo}")
    long selectNowExpStopCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    //本期解约终止
    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat='T'\n" +
            "AND  cntrTermCause in ('2','3','5','A','B','D','H','I','J','M','N','P')\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime} and mgrBranchNo =#{branchNo}")
    long selectNowSevStopCountAJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat in ('T', 'O')\n" +
            "AND  cntrTermCause = '1'\n" +
            "and  cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d')  <=#{endTime} and mgrBranchNo =#{branchNo}")
    long selectNowSevStopCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(distinct(a.cntrNo)) \n" +
            "FROM cl_cntr4_std_contract a , cl_biz4_mtn_alt_cntr b\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  a.cntrStat in ('T', 'O')\n" +
            "AND  a.cntrTermCause = '1'\n" +
            "and a.cntrType='P'\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime}\n" +
            "and a.cntrNo=b.mtnBizNo and a.mgrBranchNo =#{branchNo}" +
            "and b.procStat <>'1'\n" +
            "and b.mtnItemCode = '04';")
    long selectNowSevStopCount1JiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);

    @Select("SELECT count(*) \n" +
            "FROM cl_cntr4_std_contract\n" +
            "WHERE polCode=#{polCode}\n" +
            "AND  cntrStat='T'\n" +
            "AND  (cntrTermCause not in('1','2','3','5','A','B','N','H','I','J','M','C','P','7','D') or cntrTermCause is null)\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') >=#{startTime}\n" +
            "AND STR_TO_DATE(cntrTermDate, '%Y-%m-%d') <=#{endTime} and mgrBranchNo =#{branchNo}")
    long selectNowOtherStopCountJiTuan(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("branchNo") String branchNo);



    //---------------------------jituan   b4-------------------------------------------
    @Select("SELECT inForceDate FROM cl_cntr4_std_contract WHERE cntrNo=#{cntrNo}")
    com.guoshou.spark.po.JavaOB.StdContractString getInForceDate(@Param("cntrNo") String cntrNo);

    @Select("SELECT faceFmnt FROM cl_cntr4_cntr_sub_state WHERE cntrId in (SELECT cntrId FROM cl_cntr4_std_contract WHERE cntrNo=#{cntrNo})")
    CntrSubStateString getInFaceAmnt(@Param("cntrNo") String cntrNo);
}
