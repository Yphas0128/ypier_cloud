package cn.ypier.tbpojo;/*
 * @Author Ypier
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerBean implements Serializable {
//    private Integer id;
//    private String name;
//    private String company;
    private String name_whole;
    private String name_easy;
    private String node_name_array;

}
