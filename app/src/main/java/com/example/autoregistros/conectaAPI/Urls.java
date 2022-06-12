package com.example.autoregistros.conectaAPI;

public class Urls {


    //ENDPOINTS USUARIO
    //ENDPOINTS USUARIO
    //IP PABLO
    //public static final String URL_USER = "http://192.168.56.1:8086/app/users/user";
    //public static final String URL_EMOTION = "http://192.168.56.1:8086/app/emotions";

    //IP SCHERE
    public static final String URL_USER = "http://192.168.1.31:8086/app/users/user";
    public static final String URL_EMOTION = "http://192.168.1.31:8086/app/emotions";


    public static final String URL_GET_USERS = URL_USER + "/getAll";
    public static final String URL_GET_USER = URL_USER + "/get";
    public static final String URL_GET_LOGIN = URL_USER + "/getLogIn";
    public static final String URL_POST_USER = URL_USER + "/create";
    public static final String URL_PUT_USER = URL_USER + "/update";
    public static final String URL_DELETE_USER = URL_USER + "/delete";

    //ENDPOINTS EMOCIONES
    public static final String URL_GET_EMOTION_ID = URL_EMOTION + "/emotion";
    public static final String URL_POST_EMOTION = URL_EMOTION + "/create/emotion";
    public static final String URL_PUT_EMOTION = URL_EMOTION + "/update/emotion";
    public static final String URL_GET_EMOTION_FECHA = URL_EMOTION + "/emotion/range";
    public static final String URL_GET_EMOTION_FECHA_TIPO = URL_EMOTION + "/emotion/type_and_range";
    public static final String URL_DELETE_EMOTION = URL_EMOTION + "/delete/emotion";



}
