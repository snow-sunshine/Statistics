package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.SvrGroupString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface SvrGroupStringOBMapper extends BaseMapper<SvrGroupString> {

    //SELECT COUNT(*) FROM tjob.cl_biz5_svr_group a LEFT JOIN tjob.cl_biz5_svr_group_lst b ON a.sgId = b.sgId
    //            WHERE a.polCode ="y06" AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= "2023-12-31" AND b.sgId IS NULL
    //select count(*) from tjob.cl_biz5_svr_group a where a.polCode = "y06" and a.cntrStat='K' and
    //            STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') > "2023-12-31" and not exists (select * from tjob.cl_biz5_svr_group_lst b where a.sgId = b.sgId)
    @DS("OB")
    @Select("select count(*) from cl_biz5_svr_group a where a.polCode = #{polCode} and a.cntrStat='K' and " +
            "STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') > #{endTime} and not exists (select * from cl_biz5_svr_group_lst b where a.sgId = b.sgId)")
    long selEndNorCountB(@Param("polCode") String polCode, @Param("endTime") String endTime);

    @DS("OB")
    @Select("select count(*) from cl_biz5_svr_group a where a.polCode = #{polCode} and a.cntrStat='K' and " +
            "STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') > #{endTime} and not exists (select * from cl_biz5_svr_group_lst b where a.sgId = b.sgId) and a.mgrBranchNo=#{branchNo}")
    //select count(*) from tjob.cl_biz5_svr_group a where a.polCode = "y06" and a.cntrStat='K' and
      //STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') > "2023-12-31" and not exists (select * from tjob.cl_biz5_svr_group_lst b where a.sgId = b.sgId and a.mgrBranchNo='330200')
    long selEndNorCountBJiTuan(@Param("polCode") String polCode, @Param("endTime") String endTime,@Param("branchNo") String branchNo);
}
