package cn.ypier.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  name;
    private String  description;
    private Integer admin_count ;
    private Integer status;
}
