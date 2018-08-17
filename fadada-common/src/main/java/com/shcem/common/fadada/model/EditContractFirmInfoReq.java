package com.shcem.common.fadada.model;

public class EditContractFirmInfoReq extends BaseReq {
    // 客户编号
    private String customer_id;
    private String email;
    private String mobile;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
