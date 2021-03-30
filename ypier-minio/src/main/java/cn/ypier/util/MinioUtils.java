package cn.ypier.util;

import cn.ypier.config.MinIoConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Ypier
 */
@Component
@Configuration
@EnableConfigurationProperties({MinIoConfig.class})
public class MinioUtils {

    private MinIoConfig minIoConfig;

    public MinioUtils(MinIoConfig minIoConfig) {
        this.minIoConfig = minIoConfig;
    }

    private MinioClient instance;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        try {
            instance = MinioClient.builder().endpoint(minIoConfig.getEndpoint()).credentials(minIoConfig.getAccessKey(),minIoConfig.getSecretKey()).build();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * 判断 bucket是否存在
     */
    @SneakyThrows(Exception.class)
    public boolean  bucketExists(String bucketName){
        return instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取全部桶
     * @return
     */
    public  List<Bucket> getAllBuckets() throws Exception{
        return instance.listBuckets();
    }


    /**
     * 文件上传
     * @param bucketName
     * @param filePath
     * @throws Exception
     */
    public  void upload(String bucketName, String filePath) throws Exception{
        instance.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath)
                .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                .build());
    }

    /**
     * 以路径方式上传文件
     */
    public  void upload(String bucketName, String objectName, String filePath) throws Exception {
        instance.uploadObject(UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(filePath)
                        .build());

    }

    /**
     * 以字节方式上传文件
     */
    public  void upload(String bucketName, String objectName, InputStream byteData, String contentType) throws Exception {
        instance.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(byteData, -1, 10485760)
                .contentType(contentType)
                .build());

    }


    /**
     * * 获取文件外链接
     */
    public  String getFileUrl(String bucketName, String objectName) throws Exception {
        return instance.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .build());


    }

    public  ObjectStat getState(String bucketName, String objectName) throws Exception {
        return instance.statObject(
                StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build());


    }
}
