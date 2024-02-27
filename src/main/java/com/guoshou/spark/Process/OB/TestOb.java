package com.guoshou.spark.Process.OB;

import com.guoshou.spark.po.JavaNB.PolicyString;

public class TestOb {
    public static void main(String[] args) {
        String s ="PolicyString(branchNo=!!!!!!, polCode=F02-2, chiefPolCode=F02, polNameEng=#, polNameChn=附加意外伤害医疗保险(日津贴), polEffDate=1997-12-01, polExpDate=2009-09-30, businessType=L, insurDurType=S, yieldType=N, premiumType=N, botAgeLmt=Y0, topAgeLmt=Y69, sexSaleTo=B, moneyinItrvl=Y, moneyinType=C,K,T, mrType=R, uwRiskFact=W0, siRiskFact=W0, premAlgo=4, ageAlgo=0, cashAlgo=0, subpolOptionFlag=0, coverOptionFlag=0, faceAmntType=0, singleSalesFlag=Y)";
        PolicyString policyString = new PolicyString();
        policyString.setMrType("M");
        String policyMrType = policyString.getMrType();
        System.out.println(policyMrType);
        System.out.println(policyMrType != "M");
        if (policyMrType != "M") {
            System.out.println("aaaaa");
        }else{
            System.out.println("nnnnn");
        }

        if (!("M".equals(policyMrType))) {
            System.out.println("aaaaa111111");
        }else{
            System.out.println("nnnnn1111111");
        }
    }
}
