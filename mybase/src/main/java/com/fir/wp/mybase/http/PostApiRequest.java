package com.fir.wp.mybase.http;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
/**
 * Created by wp on 2016/1/15.
 * about
 */
public class PostApiRequest extends HttpRequest{
    @Override
    public void getMessage() {
        BaseCallBack callBack=getCallBack();
        Params params=getParams();
        //实现网络请求
        try {
            OkHttpClient mOkhttpClient=getmOkHttpClient();
            String url=getUrl();
            callBack.onStart(url,params);
            RequestBody body = fromBody(params);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = mOkhttpClient.newCall(request);
            call.enqueue(callBack);
        }catch(IllegalArgumentException ex){
            callBack.onFailed("Url路径不合法");
        }catch(Exception ex){
            callBack.onFailed("网络异常，稍后访问");
        }finally {
            if (params != null) params.clear();
        }
    }
    /** 默认参数 **/
    private static final String DEFAULT_CONTENT = "Content-Disposition";

    /** 拼装字符串参数和文件参数 **/
    private RequestBody fromBody(Params params) {
        //拼装一个字符串
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        if (params == null) return builder.build();
        if (params.hasParams()) {
            Map<String, String> P = params.getStrParam();
            for (Map.Entry<String, String> entry : P.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addPart(Headers.of(DEFAULT_CONTENT, getParamValue(key)),
                        RequestBody.create(MediaType.parse(chars.name()), value));
            }
        }
        //一个是文件
        if (params.hasFileParams()) {
            Map<String, File> F = params.getFileParam();
            for (Map.Entry<String, File> entry : F.entrySet()) {
                String name = entry.getKey();
                File file = entry.getValue();
                String fileName = file.getName();
                builder.addPart(Headers.of(DEFAULT_CONTENT, getFileValue(name, fileName)),
                        RequestBody.create(MediaType.parse(guessMimeType(fileName)), file));
            }
        }
        return builder.build();
    }

    /**\
     * 拼接字符
     * @param name
     * @return
     */
    private static String getParamValue(String name) {
        return "form-data; name=\"" + name + "\"";
    }
    private static String getFileValue(String name, String fileName) {
        return "form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"";
    }

    /**
     * 此处有疑问
     * @param path
     * @return
     */
    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
