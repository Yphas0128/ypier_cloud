package cn.ypier.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Ypier
 */

@Data
@ConfigurationProperties(prefix = "min.io")
public class MinIoConfig {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}
