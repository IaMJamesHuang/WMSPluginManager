package net;


import com.google.gson.Gson;
import dto.GetCompanyListDto;
import net.request.CommonRequest;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

/*
 * 外部请求同一放这里处理，同步获取
 */
public class RequestManagement {

    private final static String BASE_URL = "http://localhost:8080/wms/";

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

}
