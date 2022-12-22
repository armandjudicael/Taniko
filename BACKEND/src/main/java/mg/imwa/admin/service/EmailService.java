//package mg.imwa.admin.service;
//import mg.imwa.admin.model.EmailInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import java.time.Instant;
//import java.util.Date;
//
//@Service
//public class EmailService{
//    @Autowired
//   // private JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}") private String sender;
//    public void sendEmail(String to ,String body,String subject){
//        SimpleMailMessage smm = new SimpleMailMessage();
//        smm.setFrom(sender);
//        smm.setText(body);
//        smm.setTo(to);
//        smm.setSentDate(Date.from(Instant.now()));
//        smm.setSubject(subject);
//    //    javaMailSender.send(smm);
//    }
//    public void sendEmail(EmailInfo info){
//        SimpleMailMessage smm = new SimpleMailMessage();
//        smm.setFrom(sender);
//        smm.setText(info.getMsgBody());
//        smm.setTo(info.getRecipient());
//        smm.setSentDate(Date.from(Instant.now()));
//        smm.setSubject(info.getSubject());
//   //     javaMailSender.send(smm);
//    }
//}
