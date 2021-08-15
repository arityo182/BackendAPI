package com.arbud.backendapi;

public class Konfigurasi {
    public static String BASE_URL ="http://192.168.71.1/pegawai/";
    public static final String URL_GET_ALL = BASE_URL + "/tampilsemuapgw.php";
    public static final String URL_ADD = BASE_URL +"/tambahpgw.php";

    // JSON TAGS

    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID ="id";
    public static final String TAG_NAME = "name";

}
