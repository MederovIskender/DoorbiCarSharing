package kg.megacom.carsharing.dtos.base;

import kg.megacom.carsharing.enums.RestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String message;
    private Object data;
    private RestStatus status;

}
