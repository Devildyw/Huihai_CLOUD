package top.devildyw.common.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.devildyw.common.constant.RegexConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Devil
 * @since 2022-12-14-22:51
 */
@Data
public class RegisterDTO {

//    @NotBlank(message = "用户名不能为空")
//    private String username;

    @Pattern(regexp = RegexConstant.PHONE_REGEX,message = "手机号格式错误")
    private String telephone;

//    @Pattern(regexp = RegexConstant.EMAIL_REGEX,message = "邮箱格式错误")
//    private String email;

    @Length(min = 6, max = 255, message = "密码参数异常，密码必须大于等于六个字符")
    private String password;

//    @Length(min = 6, max = 255, message = "密码参数异常，密码必须大于等于六个字符")
//    private String repeatPassword;

    /**
     * 验证码
     */
//    @NotBlank(message = "验证码不能为空")
//    private String code;
}
