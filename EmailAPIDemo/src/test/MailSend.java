package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailSend
 */
@WebServlet("/MailSend")
public class MailSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out=response.getWriter();
		 String to=request.getParameter("to");
		 String sub=request.getParameter("subject");
		 String msg=request.getParameter("message");
		 sendMail(to,sub,msg);
	     out.println("Sent message successfully....");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void sendMail(String to,String sub,String msg)
	{
	
		String from = "nitingour999@gmail.com";
	    final String username =  "nitingour999@gmail.com";//change accordingly
	    final String password = "YYYYYYYYYYYY";//change accordingly

	    // Assuming you are sending email through relay.jangosmtp.net
	    String host = "smtp.gmail.com";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");

	    // Get the Session object.
	    Session session = Session.getInstance(props,
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(username, password);
	       }
	    });

	    try {
	       // Create a default MimeMessage object.
	       Message message = new MimeMessage(session);

	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(from));

	       // Set To: header field of the header.
	       message.setRecipients(Message.RecipientType.TO,
	       InternetAddress.parse(to));

	       // Set Subject: header field
	       message.setSubject(sub);

	       // Now set the actual message
	       message.setText(msg);

	       // Send message
	       
	       Transport.send(message);
	       } catch (MessagingException e) {
	  	  e.printStackTrace();
	  	     }		
	}
	
}

