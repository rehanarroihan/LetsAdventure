package id.sch.smktelkom_mlg.letsadventure;

public class Response {

    private Boolean success;
    private String message;


    private String passwordToken;

    public Response() {

    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
