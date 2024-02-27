package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.Dto.JiTuanDto;
import com.guoshou.spark.po.JavaOB.AmendGenInfoString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AmendGenInfoStringOBMapper extends BaseMapper<AmendGenInfoString> {

    @DS("OB")
    @Select("SELECT b.mtnId, b.amendBranchNo, b.amendDate, b.amendSeq, b.oldCntrNo, c.cntrNo, b.polCode, b.polNameChn, b.applType, b.applDate, b.applBranchNo, \n" +
            "b.applClerkCode, b.applEnterBranch, b.applEnterClerk, b.oclkBranchNo, b.oclkClerkCode, b.vclkBranchNo, b.vclkClerkCode, b.commitDate, b.enterDate,\n" +
            " b.verifyDate, b.applStat, b.approvePsn, b.contactOTel, b.mobileNo, b.ipsnEmail, b.contactAddress, b.postCode, b.amendArcNo, b.outForceDate, \n" +
            "b.reInForceDate, b.cntrStat, b.cntrTermCause, b.verifyOpn, b.reverseReason\n" +
            "FROM cl_biz5_svr_group a, cl_biz5_amend_gen_info b, cl_biz5_svr_group_lst c, cl_biz5_insur_appl d, cl_cntr5_std_contract e\n" +
            "WHERE b.cntrNo='#'\n" +
            "AND a.sgId=c.sgId\n" +
            "AND c.cntrNo=d.cntrNo\n" +
            "AND d.bizAcceptDate>=b.amendDate\n" +
            "AND c.cntrNo=e.cntrNo\n" +
            "AND STR_TO_DATE(b.amendDate, '%Y-%m-%d') BETWEEN #{startTime} AND #{endTime} " +
            "AND b.mtnId IN (SELECT mtnId FROM cl_biz5_amend_item WHERE amendItem IN ('XP', 'XH', 'XG', '00', '01', '02'))\n" +
            "AND a.originalCntrNo=b.oldCntrNo\n" +
            "AND b.applStat!=5;")
    List<AmendGenInfoString> CurAmendGenInfo1(@Param("startTime") String startTime, @Param("endTime") String endTime);


    @DS("OB")
    @Select("SELECT mtnId,amendBranchNo,amendDate,amendSeq,oldCntrNo,cntrNo,polCode,polNameChn,applType,applDate,applBranchNo,applClerkCode,applEnterBranch,applEnterClerk,oclkBranchNo,oclkClerkCode,vclkBranchNo,\n" +
            "vclkClerkCode,commitDate,enterDate,verifyDate,applStat,approvePsn,contactOTel,mobileNo,ipsnEmail,contactAddress,postCode,amendArcNo,outForceDate,reInForceDate,cntrStat,cntrTermCause,verifyOpn,reverseReason FROM cl_biz5_amend_gen_info where STR_TO_DATE(amendDate, '%Y-%m-%d') BETWEEN #{startTime} AND #{endTime} " +
            "  AND mtnId IN (SELECT mtnId FROM cl_biz5_amend_item WHERE amendItem IN ('XP','XH','XG','00','01','02'))" +
            "  AND applStat = '3'")
    List<AmendGenInfoString> CurAmendGenInfo(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @DS("OB")
    @Select("SELECT distinct a.cntrNo," +
            "b.procDate,c.polCode,c.mgrBranchNo,c.inForceDate,c.originalCntrNo " +
            "FROM cl_biz5_mtn_osmb_shift a, cl_biz5_mtn_alt_cntr b,cl_cntr5_std_contract c " +
            "where a.mtnId=b.mtnId and c.cntrNo=a.cntrNo " +
            "and  STR_TO_DATE(b.procDate, '%Y-%m-%d') BETWEEN  #{startTime} AND #{endTime} " +
            " and a.shiftType='I'" +
            " and b.procStat='4'" +
            " and b.mtnItemCode='82';")
    List<JiTuanDto> CurAmendGenInfo2(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
