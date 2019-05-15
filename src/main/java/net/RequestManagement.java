package net;


import com.google.gson.Gson;
import dto.*;
import net.request.CommonRequest;
import net.request.RequestParams;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/*
 * 外部请求同一放这里处理，同步获取
 */
public class RequestManagement {

    private final static String BASE_URL = "http://localhost:8080/wms/";

    /*
     * 获取公司列表
     */
    public static GetCompanyListDto getCompanyList() {
        Request request = CommonRequest.createGetRequest(BASE_URL + "getCompanyList",null);
        GetCompanyListDto subject = null;
        try {
            Response response = CommonOkHttpClient.getSync(request);
            if (response.isSuccessful()) {
                String result = response.body().string();
                Gson gson = new Gson();
                subject = gson.fromJson(result, GetCompanyListDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }

    /*
     * 获取插件列表
     */
    public static PluginListDto getPluginList() {
        Request request = CommonRequest.createGetRequest(BASE_URL + "queryAllPlugin",null);
        PluginListDto subject = null;
        try {
            Response response = CommonOkHttpClient.getSync(request);
            if (response.isSuccessful()) {
                String result = response.body().string();
                Gson gson = new Gson();
                subject = gson.fromJson(result, PluginListDto.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }

    /*
     * 上传插件
     */
    public static UploadPluginDto uploadPlugin(File plugin, String name, String version, String nick) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", plugin.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), plugin))
                .addFormDataPart("pluginName", name)
                .addFormDataPart("version", version)
                .addFormDataPart("nick", nick)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "uploadPlugin")
                .post(requestBody)
                .build();
        UploadPluginDto subject = null;
        try {
            Response response = CommonOkHttpClient.getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                Gson gson = new Gson();
                subject = gson.fromJson(result, UploadPluginDto.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }

    /*
     * 获取公司员工列表
     */
    public static GetCompanyUserDto getCompanyUserList(int companyId) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("company_id", String.valueOf(companyId));
        Request request = CommonRequest.createGetRequest(BASE_URL + "getCompanyUser",requestParams);
        GetCompanyUserDto subject = null;
        try {
            Response response = CommonOkHttpClient.getSync(request);
            if (response.isSuccessful()) {
                String result = response.body().string();
                Gson gson = new Gson();
                subject = gson.fromJson(result, GetCompanyUserDto.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }

    /*
     * 获取全部权限信息
     */
    public static PluginAccessDto queryPluginAccessList(int companyId) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("company_id", String.valueOf(companyId));
        Request request = CommonRequest.createGetRequest(BASE_URL + "getAccessList",requestParams);
        PluginAccessDto subject = null;
        try {
            Response response = CommonOkHttpClient.getSync(request);
            if (response.isSuccessful()) {
                String result = response.body().string();
                Gson gson = new Gson();
                subject = gson.fromJson(result, PluginAccessDto.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }

}
