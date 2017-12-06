/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/6 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class SendEmailBody {
    String Recipient;
    String Sender;
    String Body;
    String Subject;
    @JSONField(name="IsHtmlBody")
    boolean IsHtmlBody;
    List<EmailAttachment> Attachments;

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
    @JSONField(name="IsHtmlBody")
    public boolean isHtmlBody() {
        return IsHtmlBody;
    }

    public void setIsHtmlBody(boolean htmlBody) {
        IsHtmlBody = htmlBody;
    }

    public List<EmailAttachment> getAttachments() {
        return Attachments;
    }

    public void setIsAttachments(List<EmailAttachment> attachments) {
        Attachments = attachments;
    }
}
