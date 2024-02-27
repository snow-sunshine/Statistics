package com.guoshou.spark.mapper.OB;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaOB.PolicyString;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyStringOBMapper extends BaseMapper<PolicyString> {

    @DS("OB")
    @Select("select branchNo,trim(polCode) as polCode,chiefPolCode,polNameEng,polNameChn,SUBSTRING(polEffDate, 1, 10) as polEffDate," +
            "SUBSTRING(polExpDate, 1, 10) as polExpDate,businessType,insurDurType,yieldType,premiumType,botAgeLmt," +
            "topAgeLmt,sexSaleTo,moneyinItrvl,moneyinType,mrType,uwRiskFact,siRiskFact,premAlgo,ageAlgo,cashAlgo,subpolOptionFlag," +
            "coverOptionFlag,coverOptionFlag,faceAmntType,singleSalesFlag from product5_policy")
    List<PolicyString> SelPolicy();
}
