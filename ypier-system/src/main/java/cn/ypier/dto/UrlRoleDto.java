package cn.ypier.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Ypier
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRoleDto {
    private String url;
    private List<String> roles;
}
