package cn.ypier.pojo;
/*
 * @Author Ypier
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private Integer parentId;
    private Date create_time;
    private Date update_time;
    private String title;
    private Integer level;
    private Integer sort;
    private String name;
    private String icon;
    /**
     *   0 显示  1不显示
     */
    private Integer hidden;
}
