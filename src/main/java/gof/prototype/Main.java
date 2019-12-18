package gof.prototype;

public class Main {
	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setContent("初始模板");
		
		for (int i = 0; i < 10; i++) {
			try {
				Mail tmpMail = (Mail) mail.clone();
				tmpMail.setContent("内容" + i);
				tmpMail.setName("姓名：" + i);
				tmpMail.setEmailAddress("邮件：" + i);
				MailUtil.sendMail(tmpMail);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		MailUtil.saveOriginMailRecord(mail);
	}
}
