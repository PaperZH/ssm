package com.youmeek.ssm.module.user.interceptor;

import java.util.ArrayList;
import java.util.List;

public class WhiteData {
    private static List<String> whiteApi = new ArrayList<>();
    static{
        whiteApi.add("/login");
    }
    public static  List<String> getWhiteApi(){
        return whiteApi;
    }

    public static void add(String url){
        whiteApi.add(url);
    }

}
