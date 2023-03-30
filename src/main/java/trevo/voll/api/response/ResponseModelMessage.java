package trevo.voll.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModelMessage extends ResponseModel {
    public ResponseModelMessage (String message) {
        this.setMessage(message);
    }

}
