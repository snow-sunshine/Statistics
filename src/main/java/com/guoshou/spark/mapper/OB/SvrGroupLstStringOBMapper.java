package com.guoshou.spark.mapper.OB;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.SvrGroupLstString;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SvrGroupLstStringOBMapper extends BaseMapper<SvrGroupLstString> {

    @DS("OB")
    @Select("SELECT count(*) FROM cl_biz5_svr_group_lst WHERE sgId =(select sgid from cl_biz5_svr_group where sgNo=#{cntrNo}")
    Integer SelSvrGroupLstByCntrNo(@Param("cntrNo") String cntrNo);

    @DS("OB")
    @Select("SELECT cntrNo FROM cl_biz5_svr_group_lst WHERE sgId =(select sgid from cl_biz5_svr_group where sgNo=#{cntrNo}")
    SvrGroupLstString CurSvrGroupLst(@Param("cntrNo") String cntrNo);
}


;