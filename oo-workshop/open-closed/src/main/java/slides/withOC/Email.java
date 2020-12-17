package slides.withOC;

public class Email extends Message {
    private String toEmail;

    public Email(String message, String toEmail) {
        super(message);
        this.toEmail = toEmail;
    }

    public String send(){
        return "Email: " + this.message + " To: " + this.toEmail;
    }
}
