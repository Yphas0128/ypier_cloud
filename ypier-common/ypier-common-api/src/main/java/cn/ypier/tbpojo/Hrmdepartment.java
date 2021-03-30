package cn.ypier.tbpojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ypier
 */

@Data
public class Hrmdepartment implements Serializable {

    private Integer id;
   private String departmentmark;
   private String departmentname;
   private String supdepid;
}
