package top.devildyw.core.advice;


import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.devildyw.common.exception.BaseException;
import top.devildyw.common.exception.NotLoginException;
import top.devildyw.common.result.RestResult;
import top.devildyw.common.result.ResultCodeEnum;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 该注解为统一异常处理的核心
 * <p>
 * 是一种作用于控制层的切面通知（Advice），该注解能够将通用的@ExceptionHandler、@InitBinder和@ModelAttributes方法收集到一个类型，并应用到所有控制器上
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult error(Exception e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);

        return RestResult.fail();    // 通用异常结果
    }

    /**
     * -------- 指定异常处理方法 --------
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult error(NullPointerException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.setResult(ResultCodeEnum.NULL_POINT);
    }

    /**
     * -------- 下标越界处理方法 --------
     **/
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.setResult(ResultCodeEnum.INDEX_OUT_OF_BOUNDS);
    }

//    @ExceptionHandler(UploadException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
//    public RestResult error(UploadException e) {
//        e.printStackTrace();
//        log.error("全局异常捕获：" + e);
//        return RestResult.setResult(ResultCodeEnum.REQUEST_TIMEOUT);
//    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult error(NotLoginException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.setResult(ResultCodeEnum.NOT_LOGIN_ERROR);
    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public RestResult error(UsernameNotFoundException e) {
//        e.printStackTrace();
//        log.error("全局异常捕获：" + e);
//        return RestResult.setResult(ResultCodeEnum.NOT_LOGIN_ERROR);
//    }

//    /**
//     * 方法参数校验
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public RestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error(e.getMessage(), e);
//        return RestResult.setResult(ResultCodeEnum.PARAM_ERROR).message(e.getBindingResult().getFieldError().getDefaultMessage());
//    }


    /**
     * -------- 自定义定异常处理方法 --------
     **/
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult error(BaseException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.fail().message(e.getMessage()).code(e.getCode());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResult<String> error(ConstraintViolationException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder res = new StringBuilder("参数异常: ");
        constraintViolations.forEach(c -> res.append(c.getMessage()).append(" "));
        return RestResult.fail().message(res.toString().trim());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public RestResult argumentError(Exception e) {
        e.printStackTrace();
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        StringBuilder msg = new StringBuilder();
        assert bindingResult != null;
        bindingResult.getFieldErrors().forEach((fieldError) ->
                msg.append(fieldError.getDefaultMessage()).append(" ")
        );
        log.error("msg:{}",msg);
        return RestResult.fail().message(msg.toString().trim());
    }
}
