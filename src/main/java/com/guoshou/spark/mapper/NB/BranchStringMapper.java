package com.guoshou.spark.mapper.NB;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.BranchString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BranchStringMapper extends BaseMapper<BranchString> {
    @Select("select branchNo,branchName,prioBranchNo,branchClassNo from cl_cntap4_branch where branchNo like #{branchNo}")
    List<BranchString> DeclareCurBranch(@Param("branchNo") String branchNo);

    @Select("select prioBranchNo from cl_cntap4_branch where branchNo = #{applBranchNo}")
    BranchString GetRegBranchNo(@Param("applBranchNo") String applBranchNo);

    @Select("select prioBranchNo from cl_cntap4_branch where branchNo = #{regBranchNo}")
    BranchString GetRegBranchNo1(@Param("regBranchNo") String regBranchNo);
}
