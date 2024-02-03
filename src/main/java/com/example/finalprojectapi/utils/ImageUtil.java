package com.example.finalprojectapi.utils;

import org.apache.commons.lang3.StringUtils;

public class ImageUtil {

    public static String getImageUrl(String baseUrl, String imageUrl) {
        if(StringUtils.isBlank(imageUrl)) return "";
        return baseUrl + "/" + imageUrl;
    }
}
