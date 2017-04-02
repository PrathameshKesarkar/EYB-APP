package in.prathameshkesarkar.eyb.model.error;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class Error {

    private Integer code;
    private String message;
    private String description;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}