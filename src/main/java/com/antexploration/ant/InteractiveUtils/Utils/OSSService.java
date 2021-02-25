package com.antexploration.ant.InteractiveUtils.Utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;

@Service
@Configuration
public class OSSService {

    private String endpoint = "oss-cn-beijing.aliyuncs.com";

    private String accessKeyId = "LTAI4G2Z3qzTFJwAmgpjXieT";

    private String accessKeySecret = "eAQ0ZTmtaGv4MH0CSw4oizqiRi3HY2";

    private String bucketName = "sh-web";


    /**
     * 上传文件
     *
     * @param b
     * @param yourObjectName
     * @throws Exception
     */
    public void uploading(byte[] b, String yourObjectName) throws Exception {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流
        try {
            ossClient.putObject(bucketName, yourObjectName, new ByteArrayInputStream(b));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            throw new Exception("oss上传文件异常");
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            throw new Exception("oss上传文件异常");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }

    /**
     * 删除文件
     *
     * @param objectName 文件在oss的路径
     */
    public void deleteOSS(String objectName) {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * base64编码转byte[] 数组
     *
     * @param base64
     * @return
     */
    public static byte[] base64ToByte(String base64) {
        if (base64 == null) {
            return null;
        }
        try {
            // Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}