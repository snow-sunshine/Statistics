package com.guoshou.spark.po.NB

case class StatisticsB1String(
                         years: String,
                         times: String,
                         polFrom: String,
                         polCode: String,
                         polName: String,
                         dateFrom: String,
                         begCount: String,
                         begNorCount: String,
                         begExpCount: String,
                         begLoseCount: String,
                         begLoseStopCount: String,
                         nowRecNewCount: String,
                         nowReinCount: String,
                         nowLoseCount: String,
                         nowExpCount: String,
                         nowExpStopCount: String,
                         nowSevStopCount: String,
                         nowLoseStopCount: String,
                         nowOtherStopCount: String,
                         endCount: String,
                         endNorCount: String,
                         endExpCount: String,
                         endLoseCount: String,
                         endLoseStopCount: String,
                         mrType: String,
                         procDate: String
                       )

case class StatisticsB2String(
                         years: String,
                         times: String,
                         polFrom: String,
                         polCode: String,
                         polName: String,
                         dateFrom: String,
                         inPsCnt: String,
                         inPsAmt: String,
                         payRCnt: String,
                         payRAmt: String,
                         payCrDrMrCnt: String,
                         payCrDrMrAmt: String,
                         payE9Cnt: String,
                         payE9Amt: String,
                         payEpCnt: String,
                         payEpAmt: String,
                         payRpRsCnt: String,
                         payRpRsAmt: String,
                         mrType: String,
                         procDate: String
                       )

case class PolicyString (
                    branchNo: String,
                    polCode: String,
                    chiefPolCode: String,
                    polNameEng: String,
                    polNameChn: String,
                    polEffDate: String,
                    polExpDate: String,
                    businessType: String,
                    insurDurType: String,
                    yieldType: String,
                    premiumType: String,
                    botAgeLmt: String,
                    topAgeLmt: String,
                    sexSaleTo: String,
                    moneyinItrvl: String,
                    moneyinType: String,
                    mrType: String,
                    uwRiskFact: String,
                    siRiskFact: String,
                    premAlgo: String,
                    ageAlgo: String,
                    cashAlgo: String,
                    subpolOptionFlag: String,
                    coverOptionFlag: String,
                    faceAmntType: String,
                    singleSalesFlag: String
                  )

case class MtnAltCntrString(mtnId: String,
                      mtnBizNo: String,
                      ipsnNo: String,
                      mtnItemCode: String,
                      procStat: String,
                      procDate: String)

case class MioLogString(mioLogId: String,
                  plnmioRecId: String,
                  polCode: String,
                  cntrType: String,
                  cgNo: String,
                  sgNo: String,
                  cntrNo: String,
                  mtnId: String,
                  mtnItemCode: String,
                  ipsnNo: String,
                  mioCustNo: String,
                  mioCustName: String,
                  plnmioDate: String,
                  mioDate: String,
                  mioLogUpdTime: String,
                  premDeadline: String,
                  mioItemCode: String,
                  mioTypeCode: String,
                  mgrBranchNo: String,
                  oclkBranchNo: String,
                  oclkClerkNo: String,
                  salesBranchNo: String,
                  salesChannel: String,
                  salesNo: String,
                  mioClass: String,
                  amnt: String,
                  bankCode: String,
                  bankAccNo: String,
                  mioTxClass: String,
                  mioTxNo: String,
                  corrMioDate: String,
                  corrMioTxNo: String,
                  receiptNo: String,
                  voucherNo: String,
                  clearingMioTxNo: String,
                  finPlnmioDate: String,
                  remark: String,
                  netIncome: String,
                  vat: String,
                  vatRate: String,
                  vatId: String,
                  branchTypeFlag: String,
                  finAccNo: String,
                  finChannel: String,
                  chargeNo: String,
                  systemId: String,
                  topayLogId: String)




case class StatisticsB3String(
                         years: String,
                         times: String,
                         polFrom: String,
                         polCode: String,
                         polName: String,
                         dateFrom: String,
                         payRCnt: String,
                         payRAmt: String,
                         payXCnt: String,
                         payXAmt: String,
                         surXAmt: String,
                         mrType: String,
                         procDate: String
                       )

case class StatisticsB4String(
                         years: String,
                         times: String,
                         busSys: String,
                         proBranchNo: String,
                         regBranchNo: String,
                         applBranchNo: String,
                         cntrNo: String,
                         polCode: String,
                         inForceDate: String,
                         amendDate: String,
                         faceAmnt: String,
                         amendType: String,
                         remark: String
                       )

case class SvrGroupString(
                     sgId: String,
                     custNo: String,
                     contactSeq: String,
                     applNo: String,
                     sgNo: String,
                     sgType: String,
                     mgrBranchNo: String,
                     sgCntrNum: String,
                     salesChannel: String,
                     salesBranchNo: String,
                     salesCode: String,
                     moneyinItrvl: String,
                     moneyinDate: String,
                     moneyinType: String,
                     sumPrem: String,
                     applDate: String,
                     evDate: String,
                     billLastPrtDate: String,
                     inForceDate: String,
                     autoInForceDate: String,
                     signDate: String,
                     bankCode: String,
                     bankAccNo: String,
                     accCustName: String,
                     cntrExpiryDate: String,
                     loseRegNum: String,
                     cntrTermCause: String,
                     cntrTermDate: String,
                     cntrStat: String,
                     masterSgId: String,
                     mrType: String,
                     polCode: String,
                     renewTimes: String,
                     originalCntrNo: String
                   )

case class SvrGroupLstString(
                        sgId: String,
                        cntrNo: String,
                        ipsnCategory: String,
                        ipsnNo: String
                      )

case class BizParameterString(
                         bizParamCode: String,
                         bizParamVal: String,
                         bizParamChn: String
                       )


case class MtnOsmbShiftString(
                         mtnId: String,
                         shiftType: String,
                         cntrNo: String,
                         outBranchNo: String,
                         outBranchChn: String,
                         outAddr: String,
                         outPostcode: String,
                         outOaAddr: String,
                         outTele: String,
                         outConnector: String,
                         outReason: String,
                         applName: String,
                         applSex: String,
                         applIdNo: String,
                         applAddr: String,
                         applPostcode: String,
                         applTel: String,
                         phrAddr: String,
                         phrPostcode: String,
                         phrTel: String,
                         osmbReserve: String,
                         osmbFee: String,
                         inBranchNo: String,
                         inBranchChn: String,
                         inAddr: String,
                         inPostcode: String,
                         inOaAddr: String,
                         inTel: String,
                         inConnector: String,
                         cgCount: String,
                         isNew: String,
                         idType: String,
                         newHometel: String,
                         newOfficetel: String,
                         newEmail: String,
                         centerCode: String
                       )

case class PlnmioRecString(
                      plnmioRecId: String,
                      cntrType: String,
                      sgNo: String,
                      cgNo: String,
                      archiveBranch: String,
                      signYear: String,
                      polCode: String,
                      cntrNo: String,
                      mtnId: String,
                      mtnItemCode: String,
                      ipsnNo: String,
                      mioCustNo: String,
                      mioCustName: String,
                      mioClass: String,
                      plnmioDate: String,
                      premDeadline: String,
                      mioItemCode: String,
                      mioTypeCode: String,
                      mgrBranchNo: String,
                      salesChannel: String,
                      salesBranchNo: String,
                      salesNo: String,
                      amnt: String,
                      lockFlag: String,
                      bankCode: String,
                      bankAccNo: String,
                      holdFlag: String,
                      voucherNo: String,
                      finPlnmioDate: String,
                      clearingMioTxNo: String,
                      mioProcFlag: String,
                      accId: String,
                      remark: String,
                      plnmioCreateTime: String,
                      mtnNo: String,
                      sysNoOriginal: String,
                      systemId: String,
                      topayLogId: String
                    )

case class SgApplString(
                   applId: String,
                   applNo: String,
                   unevCustId: String,
                   contactSeq: String,
                   applType: String,
                   inForceStFlag: String,
                   prtInvoiceType: String,
                   sgNo: String,
                   sgType: String,
                   sgCntrNum: String,
                   moneyinItrvl: String,
                   polCode: String,
                   moneyinDate: String,
                   moneyinType: String,
                   bankCode: String,
                   bankaccNo: String,
                   bankaccName: String,
                   sumPrem: String,
                   applDate: String,
                   evDate: String,
                   mgrBranchNo: String,
                   acceptBranchNo: String,
                   acceptDeptType: String,
                   acceptDeptNo: String,
                   bizAcceptDate: String,
                   sysAcceptDate: String,
                   salesChannel: String,
                   salesBranchNo: String,
                   salesCode: String,
                   oclkClerkNo: String,
                   oclkBranchNo: String,
                   prtDate: String,
                   ernstPrem: String,
                   applEntBranchNo: String,
                   applEntClerkNo: String,
                   listEntBranchNo: String,
                   listEntClerkNo: String,
                   applChkBranchNo: String,
                   applChkClerkNo: String,
                   listChkBranchNo: String,
                   listChkClerkNo: String,
                   recvMode: String,
                   accesschannel: String,
                   amendArcNo: String,
                   oldCntrNo: String,
                   nextMoneyinDate: String
                 )


case class StdContractString(
                        cntrId: String,
                        cntrFrom: String,
                        applNo: String,
                        cntrType: String,
                        cntrNo: String,
                        loseRegNum: String,
                        originalCntrNo: String,
                        polCode: String,
                        mrType: String,
                        masterCntrId: String,
                        cntrStat: String,
                        cntrTermCause: String,
                        cntrTermDate: String,
                        cgNo: String,
                        sgNo: String,
                        ipsnNum: String,
                        termIpsnNum: String,
                        applDate: String,
                        signDate: String,
                        inForceDate: String,
                        autoInForceDate: String,
                        renewFlag: String,
                        renewTimes: String,
                        mgrBranchNo: String,
                        salesChannel: String,
                        oSalesBranchNo: String,
                        oSalesCode: String,
                        nSalesBranchNo: String,
                        nSalesCode: String,
                        cntrExpiryDate: String,
                        moneyinItrvl: String,
                        moneyinItrvlMon: String,
                        bddType: String,
                        paidupFlag: String,
                        cvForPremFlag: String,
                        moneyinType: String,
                        bankCode: String,
                        bankAccNo: String,
                        accCustName: String,
                        saleCode: String,
                        schemeCode: String,
                        giftType: String,
                        otherInsurFlag: String,
                        currencyCode: String
                      )


case class EndowAccString(accId: String,
                    mgrBranchNo: String,
                    accNo: String,
                    accNoSeq: String,
                    custNo: String,
                    accOpenDate: String,
                    maturity: String,
                    accCloseDate: String,
                    accTypeCode: String,
                    cashType: String,
                    accStat: String,
                    settledIntRate: String,
                    lastUpdDate: String,
                    balance: String,
                    frozenBalance: String,
                    intCalBase: String,
                    unsettledInt: String,
                    lastSettleDate: String,
                    lastSettleBalanc: String,
                    intCalType: String,
                    settleCycle: String,
                    settleDay: String,
                    settleType: String,
                    accExtractDate: String,
                    unsettledDate: String,
                    polCode: String,
                    oriAccTypeCode: String)

case class IfOfAltString(cntrId: String,
                   ipsnNo: String,
                   outForceDate: String,
                   reInForceDate: String)

case class CntrSubStateString(cntrId: String,
                        ipsnNo: String,
                        polCode: String,
                        inForceDate: String,
                        stopMoneyinDate: String,
                        termDate: String,
                        stdPremium: String,
                        premium: String,
                        faceAmnt: String,
                        hldrExPremFlag: String,
                        ipsnExPremFlag: String,
                        paidupFaceAmnt: String,
                        moneyinItrvl: String,
                        moneyinDur: String,
                        annAmnt: String)

case class BranchString(branchNo: String,
                  cityCode: String,
                  branchName: String,
                  prioBranchNo: String,
                  branchPostCode: String,
                  branchTel: String,
                  branchAddress: String,
                  branchFax: String,
                  branchEmailNo: String,
                  branchPolDefflag: String,
                  branchSearchName: String,
                  processingCenter: String,
                  branchClassNo: String,
                  printInfoBranch: String,
                  archiveBranch: String,
                  printBranchName: String,
                  branchGrp: String,
                  branchPosition: String,
                  businessLic: String,
                  taxpayerId: String,
                  serviceTel: String,
                  webAddr: String)