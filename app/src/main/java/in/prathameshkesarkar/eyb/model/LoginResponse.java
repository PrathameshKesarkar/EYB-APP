package in.prathameshkesarkar.eyb.model;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class LoginResponse {

    private String name;
    private String id;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}