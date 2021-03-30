package cn.ypier.tbpojo;/*
 * @Author Ypier
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserBean implements Serializable {
    private String i_id;
    private String job_num;
    private String name_english;
    private String name_chinese;
    private String password;
    private String role_id_array;
    private Date in_time;
    private Date up_time;
}
