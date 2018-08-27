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

import com.alibaba.fastjson.JSON;
import com.shcem.Encrypt.EncrytHelper;
import com.shcem.common.HttpUtlis;
import com.shcem.utils.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * @author lizhihua
 * @version 1.0
 */
public class sendEmailTest {
    @Test
    public void sendEmail() throws Exception {
        SendEmailBody sendEmailBody=new SendEmailBody();
        sendEmailBody.setBody("<h1>测试邮件带附件</h1>");
        sendEmailBody.setIsHtmlBody(true);
        sendEmailBody.setRecipient("wul@shcem.com;luoheng@shcem.com");
        sendEmailBody.setSender("shcem_service@shcem.com");
        sendEmailBody.setSubject("测试");
        List<EmailAttachment> attachments=new ArrayList<>();
        EmailAttachment attachment=new EmailAttachment();
        attachment.Content=getFileString();
        attachment.FileName="生鲜港平台架构(V1.0)文件服务.pptx";
        attachments.add(attachment);
        sendEmailBody.setIsAttachments(attachments);

        Map<String,String> sendBody=new HashMap<>();
        sendBody.put("sendBody", JSON.toJSONString(sendEmailBody));

        String result= HttpUtlis.Instance().postByForm("http://fms.uat.shcem.com/mapi/email/EmailSendWithAttachment",sendBody,null);
        System.out.println(result);
    }

    private String getFileString() {
        try {
            byte[] contents = FileUtils.readFileToByteArray(new File("生鲜港平台架构(V1.0)文件服务.pptx"));
            return gzipCompress(contents);
        } catch (Exception ex) {
            return null;
        }
    }
    private String gzipCompress(byte[] source) throws Exception{
        if (source == null || source.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(source);
            gzip.close();
        } catch (IOException e) {

        }
        String gb2312= EncrytHelper.encryptBASE64(out.toByteArray());
        return gb2312;
    }
}
