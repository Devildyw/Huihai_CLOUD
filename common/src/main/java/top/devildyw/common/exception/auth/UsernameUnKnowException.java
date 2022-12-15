package top.devildyw.common.exception.auth;

import top.devildyw.common.exception.BaseException;
import top.devildyw.common.result.ResultCodeEnum;

/**
 * @author Devil
 * @since 2022-12-14-18:59
 */
public class UsernameUnKnowException extends BaseException {
    public UsernameUnKnowException(String message) {
        super(message);
    }

    public UsernameUnKnowException(Integer code, String message) {
        super(code, message);
    }

    public UsernameUnKnowException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }
}
