package cn.ypier.pojo;
/**
 * @Author Ypier
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import javafx.scene.control.DateCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private  String  username;
    private  String  account;
    private  String  password;
    private  Integer enabled;
    @TableField(exist = false)
    private List<String> roleStrs;
    private Date createTime;
    private Date updateTime;

}
