package cn.ypier.verify;
/*
 * @Author Ypier
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserAddVerify {
    @NotEmpty(message = "用户名不可为空")
    private String username;
    @NotEmpty(message = "姓名不可为空")
    private String account;
    private String email;
    @NotEmpty(message = "密码不可为空")
    private String password;
    private String note;
    private Integer status;
}
