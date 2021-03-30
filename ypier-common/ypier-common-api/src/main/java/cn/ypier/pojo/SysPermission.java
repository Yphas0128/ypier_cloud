package cn.ypier.pojo;
/*
 * @Author Ypier
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements Serializable {
    private Integer id;
    private String url;
}
