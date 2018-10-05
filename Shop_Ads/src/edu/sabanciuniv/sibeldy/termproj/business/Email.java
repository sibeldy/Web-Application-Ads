package edu.sabanciuniv.sibeldy.termproj.business;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email implements Serializable {
	
	public static void sendMail(String mailAddress,String title , String content ){
        try {
    String from = "my mail address";
   String pass = "my password";
   String[] to = { mailAddress };
   String host = "smtp.gmail.com";
   Properties props = System.getProperties();
   props.put("mail.smtp.starttls.enable", "true");
   props.put("mail.smtp.host", host);
   props.put("mail.smtp.user", from);
   props.put("mail.smtp.password", pass);
   props.put("mail.smtp.port", "587");
   props.put("mail.smtp.auth", "true");
   Session session = Session.getDefaultInstance(props, null);
   MimeMessage message = new MimeMessage(session);
   message.setFrom(new InternetAddress(from));
   InternetAddress[] toAddress = new InternetAddress[to.length];
   for (int i = 0; i < to.length; i++) {
    toAddress[i] = new InternetAddress(to[i]);
   }
   for (int i = 0; i < toAddress.length; i++) {
    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
   }
   message.setSubject(title);
   message.setText(content);
   Transport transport = session.getTransport("smtp");
   transport.connect(host, from, pass);
   transport.sendMessage(message, message.getAllRecipients());
   transport.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
}

}
