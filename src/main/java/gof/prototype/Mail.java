package gof.prototype;

public class Mail implements Cloneable {
	private String content;
	private String name;
	private String emailAddress;

	public Mail() {
		System.err.println("Mail Class Constructor");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Mail [content=" + content + ", name=" + name + ", emailAddress=" + emailAddress + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		System.err.println("clone mail object");
		return super.clone();
	}

}
