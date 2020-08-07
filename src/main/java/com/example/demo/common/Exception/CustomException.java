package com.example.demo.common.Exception;

import com.example.demo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private Integer code;

    /**
     * 接收自定传递的状态码和消息
     * @param code
     * @param message
     */
    public CustomException(Integer code, String message){
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型参数
     */
    public CustomException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


    @Override
    public String toString() {
        return "CodingException{" +
                "message=" + this.getMessage() +
                "code=" + code +
                '}';
    }
}