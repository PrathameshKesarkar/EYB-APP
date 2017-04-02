package in.prathameshkesarkar.eyb.model.exception;

import java.io.IOException;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class NetworkException extends IOException {
    @Override
    public String getMessage() {

        return "No InternetConnection";
    }
}
