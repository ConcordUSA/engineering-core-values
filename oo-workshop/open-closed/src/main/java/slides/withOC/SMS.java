package slides.withOC;

public class SMS extends Message {
    private String toPhone;

    public SMS(String message, String toPhone) {
        super(message);
        this.toPhone = toPhone;
    }

    public String send(){
        return "SMS: " + this.message + " To: " + this.toPhone;
    }
}
