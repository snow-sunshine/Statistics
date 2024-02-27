package com.guoshou.spark.mapper.NB;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.SvrGroupString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface SvrGroupStringMapper extends BaseMapper<SvrGroupString> {

    //countbbb 优化
    @Select("SELECT COUNT(*) FROM cl_biz4_svr_group a LEFT JOIN cl_biz4_svr_group_lst b ON a.sgId = b.sgId\n" +
            "WHERE a.polCode =#{polCode} AND a.cntrStat = 'K' AND STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') >= #{endTime} AND b.sgId IS NULL")
    long selEndNorCountB(@Param("polCode") String polCode, @Param("endTime") String endTime);


    @Select("select count(*) from cl_biz4_svr_group a where a.polCode = #{polCode} and a.cntrStat='K' and " +
            "STR_TO_DATE(a.cntrExpiryDate, '%Y-%m-%d') > #{endTime} and not exists (select * from cl_biz4_svr_group_lst b where a.sgId = b.sgId) and a.mgrBranchNo=#{branchNo}")
    long selEndNorCountBJiTuan(@Param("polCode") String polCode, @Param("endTime") String endTime,@Param("branchNo") String branchNo);
}
