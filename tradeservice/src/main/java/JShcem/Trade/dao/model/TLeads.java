package JShcem.Trade.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class TLeads {
    private Long id;

    private Short disabled;

    private String recCreateby;

    private Date recCreatetime;

    private String recModifyby;

    private Date recModifytime;

    private String usercode;

    private String traderid;

    private String firmid;

    private Integer tradetmptid;

    private Short isrealname;

    private Integer categoryleafid;

    private Integer brandid;

    private Integer sourceplaceid;

    private Short direction;

    private Short tradeunit;

    private BigDecimal tradeunitnumber;

    private Integer quantity;

    private Integer minquantity;

    private BigDecimal price;

    private Short packagestandard;

    private Short settlementmethod;

    private Date deliveryenddate;

    private Integer goodstype;

    private Integer paymentstatus;

    private Short leadsstatus;

    private Integer addressid;

    private Integer deliveryplaceid;

    private String storehousefn;

    private String storecontactname;

    private Short feealgr;

    private BigDecimal feerate;

    private Short depositalgr;

    private BigDecimal depositrate;

    private Integer dealtquantity;

    private BigDecimal creditfee;

    private BigDecimal creditdeposit;

    private BigDecimal tradefee;

    private BigDecimal takentradefee;

    private BigDecimal tradedeposit;

    private BigDecimal takentradedeposit;

    private String objid;

    private String leadscode;

    private String whaddress;

    private Integer warehouseid;

    private Integer whcontactid;

    private Date deliverystartdate;

    private Short conformproduct;

    private BigDecimal extralogisticscost;

    private BigDecimal pettycost;

    private BigDecimal pettyfloor;

    private BigDecimal pettyceiling;

    private Short tmpdepositalgr;

    private BigDecimal tmpdepositrate;

    private Integer whgruopid;

    private String whgruopname;

    private Integer cargoagentid;

    private Short sourceplacetype;

    private Short pallettype;

    private Short needinsurance;

    private Integer lockquantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getDisabled() {
        return disabled;
    }

    public void setDisabled(Short disabled) {
        this.disabled = disabled;
    }

    public String getRecCreateby() {
        return recCreateby;
    }

    public void setRecCreateby(String recCreateby) {
        this.recCreateby = recCreateby == null ? null : recCreateby.trim();
    }

    public Date getRecCreatetime() {
        return recCreatetime;
    }

    public void setRecCreatetime(Date recCreatetime) {
        this.recCreatetime = recCreatetime;
    }

    public String getRecModifyby() {
        return recModifyby;
    }

    public void setRecModifyby(String recModifyby) {
        this.recModifyby = recModifyby == null ? null : recModifyby.trim();
    }

    public Date getRecModifytime() {
        return recModifytime;
    }

    public void setRecModifytime(Date recModifytime) {
        this.recModifytime = recModifytime;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getTraderid() {
        return traderid;
    }

    public void setTraderid(String traderid) {
        this.traderid = traderid == null ? null : traderid.trim();
    }

    public String getFirmid() {
        return firmid;
    }

    public void setFirmid(String firmid) {
        this.firmid = firmid == null ? null : firmid.trim();
    }

    public Integer getTradetmptid() {
        return tradetmptid;
    }

    public void setTradetmptid(Integer tradetmptid) {
        this.tradetmptid = tradetmptid;
    }

    public Short getIsrealname() {
        return isrealname;
    }

    public void setIsrealname(Short isrealname) {
        this.isrealname = isrealname;
    }

    public Integer getCategoryleafid() {
        return categoryleafid;
    }

    public void setCategoryleafid(Integer categoryleafid) {
        this.categoryleafid = categoryleafid;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getSourceplaceid() {
        return sourceplaceid;
    }

    public void setSourceplaceid(Integer sourceplaceid) {
        this.sourceplaceid = sourceplaceid;
    }

    public Short getDirection() {
        return direction;
    }

    public void setDirection(Short direction) {
        this.direction = direction;
    }

    public Short getTradeunit() {
        return tradeunit;
    }

    public void setTradeunit(Short tradeunit) {
        this.tradeunit = tradeunit;
    }

    public BigDecimal getTradeunitnumber() {
        return tradeunitnumber;
    }

    public void setTradeunitnumber(BigDecimal tradeunitnumber) {
        this.tradeunitnumber = tradeunitnumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinquantity() {
        return minquantity;
    }

    public void setMinquantity(Integer minquantity) {
        this.minquantity = minquantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getPackagestandard() {
        return packagestandard;
    }

    public void setPackagestandard(Short packagestandard) {
        this.packagestandard = packagestandard;
    }

    public Short getSettlementmethod() {
        return settlementmethod;
    }

    public void setSettlementmethod(Short settlementmethod) {
        this.settlementmethod = settlementmethod;
    }

    public Date getDeliveryenddate() {
        return deliveryenddate;
    }

    public void setDeliveryenddate(Date deliveryenddate) {
        this.deliveryenddate = deliveryenddate;
    }

    public Integer getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Integer goodstype) {
        this.goodstype = goodstype;
    }

    public Integer getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(Integer paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Short getLeadsstatus() {
        return leadsstatus;
    }

    public void setLeadsstatus(Short leadsstatus) {
        this.leadsstatus = leadsstatus;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getDeliveryplaceid() {
        return deliveryplaceid;
    }

    public void setDeliveryplaceid(Integer deliveryplaceid) {
        this.deliveryplaceid = deliveryplaceid;
    }

    public String getStorehousefn() {
        return storehousefn;
    }

    public void setStorehousefn(String storehousefn) {
        this.storehousefn = storehousefn == null ? null : storehousefn.trim();
    }

    public String getStorecontactname() {
        return storecontactname;
    }

    public void setStorecontactname(String storecontactname) {
        this.storecontactname = storecontactname == null ? null : storecontactname.trim();
    }

    public Short getFeealgr() {
        return feealgr;
    }

    public void setFeealgr(Short feealgr) {
        this.feealgr = feealgr;
    }

    public BigDecimal getFeerate() {
        return feerate;
    }

    public void setFeerate(BigDecimal feerate) {
        this.feerate = feerate;
    }

    public Short getDepositalgr() {
        return depositalgr;
    }

    public void setDepositalgr(Short depositalgr) {
        this.depositalgr = depositalgr;
    }

    public BigDecimal getDepositrate() {
        return depositrate;
    }

    public void setDepositrate(BigDecimal depositrate) {
        this.depositrate = depositrate;
    }

    public Integer getDealtquantity() {
        return dealtquantity;
    }

    public void setDealtquantity(Integer dealtquantity) {
        this.dealtquantity = dealtquantity;
    }

    public BigDecimal getCreditfee() {
        return creditfee;
    }

    public void setCreditfee(BigDecimal creditfee) {
        this.creditfee = creditfee;
    }

    public BigDecimal getCreditdeposit() {
        return creditdeposit;
    }

    public void setCreditdeposit(BigDecimal creditdeposit) {
        this.creditdeposit = creditdeposit;
    }

    public BigDecimal getTradefee() {
        return tradefee;
    }

    public void setTradefee(BigDecimal tradefee) {
        this.tradefee = tradefee;
    }

    public BigDecimal getTakentradefee() {
        return takentradefee;
    }

    public void setTakentradefee(BigDecimal takentradefee) {
        this.takentradefee = takentradefee;
    }

    public BigDecimal getTradedeposit() {
        return tradedeposit;
    }

    public void setTradedeposit(BigDecimal tradedeposit) {
        this.tradedeposit = tradedeposit;
    }

    public BigDecimal getTakentradedeposit() {
        return takentradedeposit;
    }

    public void setTakentradedeposit(BigDecimal takentradedeposit) {
        this.takentradedeposit = takentradedeposit;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid == null ? null : objid.trim();
    }

    public String getLeadscode() {
        return leadscode;
    }

    public void setLeadscode(String leadscode) {
        this.leadscode = leadscode == null ? null : leadscode.trim();
    }

    public String getWhaddress() {
        return whaddress;
    }

    public void setWhaddress(String whaddress) {
        this.whaddress = whaddress == null ? null : whaddress.trim();
    }

    public Integer getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(Integer warehouseid) {
        this.warehouseid = warehouseid;
    }

    public Integer getWhcontactid() {
        return whcontactid;
    }

    public void setWhcontactid(Integer whcontactid) {
        this.whcontactid = whcontactid;
    }

    public Date getDeliverystartdate() {
        return deliverystartdate;
    }

    public void setDeliverystartdate(Date deliverystartdate) {
        this.deliverystartdate = deliverystartdate;
    }

    public Short getConformproduct() {
        return conformproduct;
    }

    public void setConformproduct(Short conformproduct) {
        this.conformproduct = conformproduct;
    }

    public BigDecimal getExtralogisticscost() {
        return extralogisticscost;
    }

    public void setExtralogisticscost(BigDecimal extralogisticscost) {
        this.extralogisticscost = extralogisticscost;
    }

    public BigDecimal getPettycost() {
        return pettycost;
    }

    public void setPettycost(BigDecimal pettycost) {
        this.pettycost = pettycost;
    }

    public BigDecimal getPettyfloor() {
        return pettyfloor;
    }

    public void setPettyfloor(BigDecimal pettyfloor) {
        this.pettyfloor = pettyfloor;
    }

    public BigDecimal getPettyceiling() {
        return pettyceiling;
    }

    public void setPettyceiling(BigDecimal pettyceiling) {
        this.pettyceiling = pettyceiling;
    }

    public Short getTmpdepositalgr() {
        return tmpdepositalgr;
    }

    public void setTmpdepositalgr(Short tmpdepositalgr) {
        this.tmpdepositalgr = tmpdepositalgr;
    }

    public BigDecimal getTmpdepositrate() {
        return tmpdepositrate;
    }

    public void setTmpdepositrate(BigDecimal tmpdepositrate) {
        this.tmpdepositrate = tmpdepositrate;
    }

    public Integer getWhgruopid() {
        return whgruopid;
    }

    public void setWhgruopid(Integer whgruopid) {
        this.whgruopid = whgruopid;
    }

    public String getWhgruopname() {
        return whgruopname;
    }

    public void setWhgruopname(String whgruopname) {
        this.whgruopname = whgruopname == null ? null : whgruopname.trim();
    }

    public Integer getCargoagentid() {
        return cargoagentid;
    }

    public void setCargoagentid(Integer cargoagentid) {
        this.cargoagentid = cargoagentid;
    }

    public Short getSourceplacetype() {
        return sourceplacetype;
    }

    public void setSourceplacetype(Short sourceplacetype) {
        this.sourceplacetype = sourceplacetype;
    }

    public Short getPallettype() {
        return pallettype;
    }

    public void setPallettype(Short pallettype) {
        this.pallettype = pallettype;
    }

    public Short getNeedinsurance() {
        return needinsurance;
    }

    public void setNeedinsurance(Short needinsurance) {
        this.needinsurance = needinsurance;
    }

    public Integer getLockquantity() {
        return lockquantity;
    }

    public void setLockquantity(Integer lockquantity) {
        this.lockquantity = lockquantity;
    }
}