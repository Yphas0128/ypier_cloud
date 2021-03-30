package cn.ypier.verify;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/*
 * @Author Ypier
 */
@Data
public class RoleVerify {
    private Integer id;
    @NotEmpty(message = "角色名不可为空")
    private String  name;
    private String  description;
    private Integer admin_count ;
    private Integer status;
}
