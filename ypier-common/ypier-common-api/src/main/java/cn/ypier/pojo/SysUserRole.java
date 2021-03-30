package cn.ypier.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Ypier
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role_relation")
public class SysUserRole implements Serializable {

    private Integer userId;
    private Integer roleId;
}
