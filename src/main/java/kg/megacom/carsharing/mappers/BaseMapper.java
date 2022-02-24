package kg.megacom.carsharing.mappers;

import kg.megacom.carsharing.dtos.base.BaseResponse;
import kg.megacom.carsharing.enums.RestStatus;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    public BaseResponse mapToBaseResponse(String message, Object data, RestStatus status) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        baseResponse.setStatus(status);
        return baseResponse;
    }
}