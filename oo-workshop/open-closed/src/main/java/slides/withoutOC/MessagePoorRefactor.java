package slides.withoutOC;

public class MessagePoorRefactor {
    private final String message;
    private final String toEmail;
    private final String toPhone;

    public MessagePoorRefactor(String message, String toEmail, String toPhone) {
        this.message = message;
        this.toEmail = toEmail;
        this.toPhone = toPhone;
    }

    public String sendEmail() {
        return "Email: " + this.message + " To: " + this.toEmail;
    }

    public String sendSMS() {
        return "SMS: " + this.message + " To: " + this.toPhone;
    }
}
