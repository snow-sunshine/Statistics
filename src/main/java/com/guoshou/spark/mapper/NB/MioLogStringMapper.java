package com.guoshou.spark.mapper.NB;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.Dto.CountAndSumResult;
import com.guoshou.spark.po.JavaNB.MioLogString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface MioLogStringMapper extends BaseMapper<MioLogString> {

    //select count(*) from cl_biz4_mio_log where polCode = 'F01' and mioItemCode in ('PS','PU') and mioClass=1
    //AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>="2023-01-01"  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<="2023-12-31
    @Select("select count(*) as counts,sum(amnt) as amntSums  from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('PS','PU') and mioClass=1 " +
            "AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
//    CountAndSumResult a(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //本期保费收入件数 InPsCntCount  和 本期保费收入金额 InPsAmtSum
    CountAndSumResult inPsCntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);


    //select count(*) from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('CR','DR','MR','HR','LP')
    //and mioClass=1  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>="2023-01-01"  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<="2023-12-31
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('CR','DR','MR','HR','LP')" +
            " and mioClass=1  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    //本期赔款支出件数 和  金额
    CountAndSumResult payRCntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期死亡、伤残、医疗支出件数 和  金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('CR','DR','MR','HR','LP')" +
            " and mioClass='-1'  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payCrDrMrCntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期满期给付件数  和 本期满期给付金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode ='E9' and mioClass='-1'  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payE9CntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期年金给付件数  和   本期年金给付金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('ER','AR') and mioClass='-1'  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payEPCntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期解约给付件数  和   本期解约给付金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('RP','RS','PU') and mioClass='-1'  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payRpRsCntAndAmt(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //----------------------------------表b3------------------------------------------------
    //本期赔款支出件数     本期赔款支出金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode not in ('RF','RP','RS','CR') and mioClass='-1'  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payRCntAndAmtB3(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //本期储金返还件数      本期储金返还金额
    @Select("select count(*) as counts,sum(amnt) as amntSums from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('RF','RP','RS','CR')  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')>=#{startTime}  AND  STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    CountAndSumResult payXCntAndAmtB3(@Param("polCode") String polCode, @Param("startTime") String startTime, @Param("endTime") String endTime);


    //期末储金余额
    @Select("select sum(amnt) from cl_biz4_mio_log where polCode = #{polCode} and mioItemCode in ('RF','RP','RS','CR') and STR_TO_DATE(mioDate, '%Y-%m-%d')<=#{endTime}")
    Long surXAmt(@Param("polCode") String polCode, @Param("endTime") String endTime);
}