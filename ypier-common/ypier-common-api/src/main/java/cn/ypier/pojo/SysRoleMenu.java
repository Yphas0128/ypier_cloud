package cn.ypier.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Ypier
 */
@TableName("sys_role_menu_relation")
@Data
public class SysRoleMenu {
    /**
     * ID 自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
