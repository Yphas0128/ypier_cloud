package cn.ypier.verify;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/*
 * @Author Ypier
 */
@Data
public class LoginVerify {

    @NotEmpty(message = "用户名不可为空")
    private String username;
    @NotEmpty(message = "密码不可为空")
    private String password;
}
