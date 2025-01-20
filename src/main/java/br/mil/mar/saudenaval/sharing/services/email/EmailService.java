package br.mil.mar.saudenaval.sharing.services.email;

import br.mil.mar.saudenaval.sharing.entities.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static String defaultPath = "/compartilhamento/default/files";

    public void sendMail(List<User> destinatarios, String subject, String body, String attachmentPath) throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("naoresponder@saudenaval.com.br");
       // helper.setTo(destinatario);
        helper.setTo("reginaldo.elias@marinha.mil.br");
        helper.setSubject(subject);
        helper.setText(body);
        destinatarios.forEach(user->{
            try {
                if(user.getStatus().equals("Ativo")){
                    helper.setBcc(user.getEmail());
                }

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        //helper.setCc(destinatarios);
        FileSystemResource file = new FileSystemResource(new File(defaultPath.concat(attachmentPath)));
      //  InputStreamSource attachment = attachmentPath.
        helper.addAttachment(file.getFilename(),file);
        javaMailSender.send(mimeMessage);



    }
}
