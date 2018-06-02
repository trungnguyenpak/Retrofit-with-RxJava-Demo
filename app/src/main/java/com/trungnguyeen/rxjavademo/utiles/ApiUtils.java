package com.trungnguyeen.rxjavademo.utiles;


import com.trungnguyeen.rxjavademo.data.remote.RetrofitClient;
import com.trungnguyeen.rxjavademo.data.remote.SOService;

/**
 * Created by trungnguyeen on 4/5/18.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.github.com/";

    public static SOService getSOService() {
        return RetrofitClient.getRetrofitClient(BASE_URL).create(SOService.class);
    }
}
