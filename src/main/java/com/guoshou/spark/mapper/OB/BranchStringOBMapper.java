package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.BranchString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchStringOBMapper extends BaseMapper<BranchString> {

//    @DS("OB")
//    @Select("select bizParamVal  from biz_proc5_biz_parameter where bizParamCode ='OBPSORNBPS_FLAG'")
//    List<BizParameterString> SelBizParameterObOrNbFlag();
    //ELECT branch_no,branch_name,prio_branch_no,branch_class_no
    //             FROM branch
    //             where branch_no like :branch_no2
    @DS("OB")
    @Select("select branchNo,branchName,prioBranchNo,branchClassNo from cl_cntap5_branch where branchNo like #{branchNo}")
    List<BranchString> DeclareCurBranch(@Param("branchNo") String branchNo);

    @DS("OB")
    @Select("select prioBranchNo from cl_cntap5_branch where branchNo = #{applBranchNo}")
    BranchString GetRegBranchNo(@Param("applBranchNo") String applBranchNo);

    @DS("OB")
    @Select("select prioBranchNo from cl_cntap5_branch where branchNo = #{regBranchNo}")
    BranchString GetRegBranchNo1(@Param("regBranchNo") String regBranchNo);
}