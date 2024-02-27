package com.guoshou.spark.mapper.NB;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshou.spark.po.JavaNB.PolicyString;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyStringMapper extends BaseMapper<PolicyString> {

    @Select("select branchNo,trim(polCode) as polCode,chiefPolCode,polNameEng,polNameChn,SUBSTRING(polEffDate, 1, 10) as polEffDate," +
            "SUBSTRING(polExpDate, 1, 10) as polExpDate,businessType,insurDurType,yieldType,premiumType,botAgeLmt," +
            "topAgeLmt,sexSaleTo,moneyinItrvl,moneyinType,mrType,uwRiskFact,siRiskFact,premAlgo,ageAlgo,cashAlgo,subpolOptionFlag," +
            "coverOptionFlag,coverOptionFlag,faceAmntType,singleSalesFlag from product4_policy")
    List<PolicyString> SelPolicy();
}
