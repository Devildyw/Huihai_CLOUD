package top.devildyw.common.exception;


import lombok.Data;
import top.devildyw.common.result.ResultCodeEnum;

/**
 * 自定义全局异常类
 */
@Data
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(String message) {
        super(message);
        this.code = ResultCodeEnum.UNKNOWN_ERROR.getCode();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "BaseException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
