package com.xyh.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.util.Date;

/**
 * @Author SQ  文件上传的工具类
 * @Date 2020/8/19 0019 12:19
 * @Version 1.0
 */
public class QiniuUploadUtil {
    private static final String accessKey = "aCulzMZBI_5P_1pq8L06akvFVbC_E0ivAfuGxzQd ";
    private static final String secretKey = "jdHMgbFAIssMDwHyrntutyuzXY9ojh_7D0jqPRlY  ";
    private static final String bucket = "shaojiaqi007";
    private static final String prix = "qfalmihlt.hd-bkt.clouddn.com";
    private UploadManager manager;
    public QiniuUploadUtil() {
        //初始化基本配置
        Configuration cfg = new Configuration(Region.region0());
        //创建上传管理器
        manager = new UploadManager(cfg);
    }
    public String upload(String imgName , byte [] bytes) {
        Auth auth = Auth.create(accessKey, secretKey);
        //构造覆盖上传token
        String upToken = auth.uploadToken(bucket,imgName);
        try {
            Response response = manager.put(bytes, imgName, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),
                    DefaultPutRet.class);
            //返回请求地址
            return prix+putRet.key+"?t="+new Date().getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
