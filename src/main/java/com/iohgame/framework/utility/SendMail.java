package com.iohgame.framework.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends MainClass
{
    private Properties m_props;

    public SendMail()
    {
        m_props = new Properties();
        m_props.setProperty("mail.transport.protocol", "smtp");
        m_props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
        m_props.setProperty("mail.smtp.port", "25");
        m_props.setProperty("mail.smtp.auth", "true");
        m_props.setProperty("mail.debug", "false");
    }

    public void sendHtmlEmail(String address, String subject, String content) throws Exception
    {
        Session session = Session.getInstance(m_props, new MyAuthenticator());
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress("service@iohgame.com", "荣耀小助手"));
        message.setSentDate(new Date());
        message.setRecipients(RecipientType.TO, InternetAddress.parse(address));
        message.setContent(content, "text/html;charset=utf-8");
        message.saveChanges();
        Transport.send(message);
    }

    public static class MyAuthenticator extends Authenticator
    {
        private String m_username = "service@iohgame.com";
        private String m_password = "!QAZxsw2#EDCvfr4";

        public MyAuthenticator()
        {
            super();
        }

        public MyAuthenticator(String username, String password)
        {
            super();
            m_username = username;
            m_password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(m_username, m_password);
        }
    }
}
