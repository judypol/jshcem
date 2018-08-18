package model;

import com.shcem.utils.excel.annotation.ExcelField;

import java.math.BigDecimal;

public class ExcelModel {
    @ExcelField(title = "流水编号",ColumnIndex = 0)
    private String SN;
    @ExcelField(title = "支付企业账户账号",ColumnIndex = 1)
    private String enSN;
    @ExcelField(title = "支付企业账户名称",ColumnIndex = 2)
    private String enName;
    @ExcelField(title = "支付企业支行名称",ColumnIndex = 3)
    private String bank;
    @ExcelField(title = "流水金额",ColumnIndex = 4)
    private BigDecimal price;
    @ExcelField(title = "流水发生时间",ColumnIndex = 5)
    private String date;
    @ExcelField(title = "摘要",ColumnIndex = 6)
    private String note;
    @ExcelField(title = "收入企业账户账号",ColumnIndex = 7)
    private String account;
    @ExcelField(title = "收入企业账户名称",ColumnIndex = 8)
    private String enEnName;

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getEnSN() {
        return enSN;
    }

    public void setEnSN(String enSN) {
        this.enSN = enSN;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEnEnName() {
        return enEnName;
    }

    public void setEnEnName(String enEnName) {
        this.enEnName = enEnName;
    }
}
