package com.fir.wp.mybase.http;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by wp on 2016/1/18.
 * about
 */
public abstract class DownCallBack extends BaseCallBack{
    private  String fileSavePath;
    private  String fileSaveName;
    public DownCallBack(String filePath,String fileName){
        this.fileSavePath=filePath;
        this.fileSaveName=fileName;
        listener=this;
    }
    @Override
    public void onFailure(Request request, IOException e) {
        onFailed(Faile.NETERROR);
    }
    @Override
    public void onUIProgress(long currentBytes, long contentLength, boolean done) {
        onProgress(currentBytes, contentLength, done);
    }
    @Override
    public void onResponse(Response response) throws IOException {
        if (response != null && response.isSuccessful()) {
            ResponseBody body = response.body();
            try {
                byte[] bytes = body != null ? body.bytes() : null;
                File file = doParse(bytes);
                if (file == null || !file.exists())
                    throw new IllegalArgumentException("Download result is empty");
            } catch (Exception e) {
                    errorMessage(e);
            } finally {
                if (body != null) body.close();
            }
        } else {
            throw new IOException("Network error：Unexpected code " + response);
        }
    }
    private File doParse(byte[] data) throws IOException {
        if (data == null) return null;
        File file = new File(fileSavePath, fileSaveName);
        if (!file.getParentFile().exists() && file.getParentFile().isDirectory()) {//判断文件目录是否存在
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try {
            bos.write(data);
        } finally {
            bos.close();
            fos.close();
        }
        return file;
    }
    public String getFileSavePath() {
        return fileSavePath;
    }

    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }

    public String getFileSaveName() {
        return fileSaveName;
    }

    public void setFileSaveName(String fileSaveName) {
        this.fileSaveName = fileSaveName;
    }
}
