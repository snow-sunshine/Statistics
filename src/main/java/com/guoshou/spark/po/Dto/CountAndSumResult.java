package com.guoshou.spark.po.Dto;

import lombok.Data;

@Data
public class CountAndSumResult {
    private Long counts;
    private String amntSums ;

    public CountAndSumResult() {
        this.counts = 0L;
        this.amntSums = "0";
    }
}
