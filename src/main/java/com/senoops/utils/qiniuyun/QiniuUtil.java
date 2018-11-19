package com.senoops.utils.qiniuyun;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;


public class QiniuUtil {
	
	//七牛云密钥
    private static String ACCESS_KEY = null; 
    private static String SECRET_KEY = null;
    
    //存储空间名称
    public static String COLLEGE_IMAGES = null;
	public static String COLLEGE_VIDEO = null;
	
	//存储空间域名
	public static String COLLEGE_IMAGES_DOMAIN = null;
	public static String COLLEGE_VIDEO_DOMAIN = null;

	static {
		Properties prop = new Properties();     
        try{
        	String path = Config.class.getClassLoader().getResource("qiniu.properties").getPath();
            InputStream in = new BufferedInputStream (new FileInputStream(path));
            prop.load(in); 
            ACCESS_KEY = prop.getProperty("qiniu_ak").toString();
            SECRET_KEY = prop.getProperty("qiniu_sk").toString();
            COLLEGE_IMAGES = prop.get("college_images_zone").toString();
            COLLEGE_VIDEO = prop.get("college_video_zone").toString();
            COLLEGE_IMAGES_DOMAIN = prop.get("college_images_domain").toString();
            COLLEGE_VIDEO_DOMAIN = prop.get("college_video_domain").toString();
            in.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    
	// zone1 ：代表华北地区
    private static Zone zone = Zone.zone1();
    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    //Zone z = Zone.autoZone();
    
    //自定义返回的JSON格式的内容
    private static StringMap putPolicy = new StringMap();
    static{
    	putPolicy.put("returnBody", "{\"fileName\":\"$(key)\","
    								+ "\"hash\":\"$(etag)\","
    								+ "\"zoneName\":\"$(bucket)\","
    								+ "\"fileSize\":\"$(fsize)\","
    								+ "\"originalName\":\"$(fname)\","
    								+ "\"mimeType\":\"$(mimeType)\","
    								+ "\"suffix\":\"$(ext)\"}");
    }
    

   /**
    * 获取上传凭证
    * @Author Axin
    * @DateTime 2018年9月26日 下午4:23:53
    * @param zone
    * @return
    */
    public static String getUpToken(String zone) {
    	long expireSeconds = 3600;
        return auth.uploadToken(zone,null,expireSeconds,putPolicy);
        
    }

    
    /**
     * 文件上传
     * @Author Axin
     * @DateTime 2018年9月26日 下午4:24:06
     * @param filePath
     * @throws IOException
     */
    public static UpResult upload(String filePath,String zoneName) throws IOException {
        Configuration conf = new Configuration(zone);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(conf);
        String newFileName = getRandomFileName(filePath);
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, newFileName, getUpToken(zoneName));
            UpResult result = res.jsonToObject(UpResult.class);
            return result;
        } catch (QiniuException e) {
            Response r = e.response;
            //请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }
    
    /**
     * 字节数组上传
     * @Author Axin
     * @DateTime 2018年9月27日 上午11:09:49
     * @param data
     * @param fileName
     * @param zoneName
     * @return
     */
    public static UpResult upload(byte[] data, String fileName,String zoneName){
        Configuration cfg = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(cfg);
        String newFileName = getRandomFileName(fileName);
        try {
            Response res = uploadManager.put(data, newFileName, getUpToken(zoneName));
            UpResult result = res.jsonToObject(UpResult.class);
            result.setOriginalName(fileName);
            return result;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }
    
    
    /**
     * 数据流上传
     * @Author Axin
     * @DateTime 2018年9月27日 上午11:09:06
     * @param is
     * @param fileName
     * @param zoneName
     * @return
     */
    public static UpResult upload(InputStream is, String fileName,String zoneName){
        Configuration cfg = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(cfg);
        String newFileName = getRandomFileName(fileName);
        try {
            Response res = uploadManager.put(is,newFileName,getUpToken(zoneName),null, null);
            UpResult result = res.jsonToObject(UpResult.class);
            result.setOriginalName(fileName);
            return result;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
        }
        return null;
    }
    
    /**
     * 获取随机文件名
     * @Author Axin
     * @DateTime 2018年9月27日 上午10:28:28
     * @return
     */
    private static String getRandomFileName(String str){
    	int index = str.lastIndexOf(".");
    	if(index==-1){
    		throw new RuntimeException("文件名称或路径无后缀");
    	}
        String suffix = str.substring(index);
    	return  UUIDGenerator.generateUUID()+"_"+VeDate.getStringDateShort().replace("-", "")+suffix;
    }
    
    /**
     * 删除文件
     * @Author Axin
     * @DateTime 2018年9月26日 下午5:42:27
     * @param zoneName
     * @param filePath
     * @throws QiniuException  删除失败异常
     */
    public static void delete(String filePath,String zoneName) throws QiniuException{
    	Configuration conf = new Configuration(zone);
    	BucketManager bucketManager = new BucketManager(auth, conf);
    	bucketManager.delete(zoneName, filePath);
    }
    
    
    /**
     * 批量删除
     * @Author Axin
     * @DateTime 2018年9月26日 下午5:52:21
     * @param pathList
     * @param zoneName
     * @throws QiniuException 
     */
    public static BatchStatus[] deleteOfBatch(String[] pathList,String zoneName) throws QiniuException{
    	Configuration conf = new Configuration(zone);
    	BucketManager bucketManager = new BucketManager(auth, conf);
    	BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
    	batchOperations.addDeleteOp(zoneName, pathList);
    	Response response = bucketManager.batch(batchOperations);
    	BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
    	return batchStatusList;
    	
    	
    }
    /**
     * 获取文件url
     * @Author Axin
     * @DateTime 2018年9月26日 下午5:35:14
     * @param filePath
     * @param zoneDomain
     * @return
     */
    public static String getFileUrl(String filePath,String zoneDomain){
        String encodedFilePath = null;
		try {
			encodedFilePath = URLEncoder.encode(filePath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        String finalUrl = String.format("%s/%s", zoneDomain, encodedFilePath);
        return finalUrl;
    }
    
    /**
     * 获取文件输入流
     * @Author Axin
     * @DateTime 2018年9月26日 下午8:38:38
     * @param filePath
     * @param zoneDomain
     * @return
     */
    public static InputStream getFileStream(String filePath,String zoneDomain) {
    	String url = getFileUrl(filePath,zoneDomain);
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).build();
        okhttp3.Response resp = null;
        try {
          resp = client.newCall(req).execute();
          if (resp.isSuccessful()) {
            ResponseBody body = resp.body();
            return body.byteStream();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        
        return null;
      }
    
    
    public static void main(String args[]) throws IOException {
    	long start = System.currentTimeMillis();
    	String filePath = "D:\\photo\\0000002.jpg";
    	String zoneName = QiniuUtil.COLLEGE_IMAGES;
        
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        byte[] byt = new byte[is.available()];

        is.read(byt);
     
      /*  UpResult result = upload(is,"0000002.jpg",zoneName);*/
       UpResult result = upload(byt,"0000002.jpg",zoneName);
        System.out.println(result);
        
        
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000/60);
    }


}
