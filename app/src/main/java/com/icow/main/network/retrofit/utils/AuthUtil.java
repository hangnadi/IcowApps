package com.icow.main.network.retrofit.utils;

import android.support.v4.util.ArrayMap;
import android.util.Base64;

import com.icow.main.BuildConfig;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by hangnadi on 2/9/17.
 */
public class AuthUtil {

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String MAC_ALGORITHM = "HmacSHA1";
    private static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss ZZZ";

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_X_METHOD = "X-Method";
    private static final String HEADER_REQUEST_METHOD = "Request-Method";
    private static final String HEADER_CONTENT_MD5 = "Content-MD5";
    private static final String HEADER_DATE = "Date";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_DEVICE = "X-Device";
    private static final String HEADER_X_APP_VERSION = "X-APP-VERSION";

    private static final String PARAM_USER_ID = "user_id";
    private static final String PARAM_DEVICE_ID = "device_id";
    private static final String PARAM_HASH = "hash";
    private static final String PARAM_OS_TYPE = "os_type";
    private static final String PARAM_TIMESTAMP = "device_time";

    public static class KEY {
        public static final String KEY_WSV4 = "";
    }

    @SuppressWarnings("all")
    public static Map<String, String> generateHeaders(String path, String strParam, String method, String authKey) {
        Map<String, String> finalHeader = getDefaultHeaderMap(path, strParam, method, CONTENT_TYPE, authKey, DATE_FORMAT);
        finalHeader.put(HEADER_X_APP_VERSION, Integer.toString(BuildConfig.VERSION_CODE));
        return finalHeader;
    }

    @SuppressWarnings("all")
    public static Map<String, String> generateHeaders(String path, String method, String authKey) {
        Map<String, String> finalHeader = getDefaultHeaderMap(path, "", method, CONTENT_TYPE_JSON, authKey, DATE_FORMAT);
        finalHeader.put(HEADER_DEVICE, "android-" + BuildConfig.VERSION_NAME);
        return finalHeader;
    }

    @SuppressWarnings("all")
    public static Map<String, String> getDefaultHeaderMap(String path, String strParam, String method,
                                                          String contentType, String authKey, String dateFormat) {
        String date = generateDate(dateFormat);
        String contentMD5 = generateContentMd5(strParam);

        String authString = method + "\n" + contentMD5 + "\n" + contentType + "\n" + date + "\n" + path;
        String signature = calculateRFC2104HMAC(authString, authKey);

        Map<String, String> headerMap = new ArrayMap<>();
        headerMap.put(HEADER_CONTENT_TYPE, contentType);
        headerMap.put(HEADER_X_METHOD, method);
        headerMap.put(HEADER_REQUEST_METHOD, method);
        headerMap.put(HEADER_CONTENT_MD5, contentMD5);
        headerMap.put(HEADER_DATE, date);
        headerMap.put(HEADER_AUTHORIZATION, "ICW Icow:" + signature.trim());
        headerMap.put(HEADER_X_APP_VERSION, String.valueOf(BuildConfig.VERSION_CODE));
        return headerMap;
    }

    private static String generateContentMd5(String s) {
        return md5(s);
    }

    private static String generateDate(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    private static String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String calculateRFC2104HMAC(String authString, String authKey) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(authKey.getBytes(), MAC_ALGORITHM);
            Mac mac = Mac.getInstance(MAC_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(authString.getBytes());
            String asB64 = Base64.encodeToString(rawHmac, Base64.DEFAULT);
            System.out.println(asB64);
            return asB64;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return "";
        }
    }
}
