package top.devildyw.common.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.devildyw.common.constant.RegexConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Devil
 * @since 2022-12-14-19:52
 */
@Data
public class LoginDTO {

//    @NotBlank(message = "用户名不能为空")
//    private String username;

    @Pattern(regexp = RegexConstant.PHONE_REGEX,message = "手机号格式错误")
    private String telephone;

    @Length(min = 6, max = 255, message = "密码参数异常，密码必须大于等于六个字符")
    private String password;

    /**
     * 验证码
     */
//    @NotBlank(message = "验证码不能为空")
//    private String code;

    /**
     * 扩展字段 登录类型可以是手机验证码登录 也可以是用户名密码登录
     */
    private Integer type;

    /**
     * 是否记住我
     */
    private Boolean isRememberMe;

}
