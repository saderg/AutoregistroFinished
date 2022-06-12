package com.example.autoregistros.entidades;

import java.util.Date;

public class Emotion {

    private Integer id_emocion;
    private Integer id_usuario;
    private String emotion_type;
    private String emotion_reason;
    private Date emotion_date;


    public Emotion(Integer id_emocion, Integer id_usuario, String emotion_type, String emotion_reason, Date emotion_date) {
        this.id_emocion = id_emocion;
        this.id_usuario = id_usuario;
        this.emotion_type = emotion_type;
        this.emotion_reason = emotion_reason;
        this.emotion_date = emotion_date;
    }

    public Emotion() {

    }

    public Emotion(Integer id_usuario, String emotion_type, String emotion_reason, Date emotion_date) {
        this.id_usuario = id_usuario;
        this.emotion_type = emotion_type;
        this.emotion_reason = emotion_reason;
        this.emotion_date = emotion_date;
    }

    public Integer getId_emocion() {
        return id_emocion;
    }

    public void setId_emocion(Integer id_emocion) {
        this.id_emocion = id_emocion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmotion_type() {
        return emotion_type;
    }

    public void setEmotion_type(String emotion_type) {
        this.emotion_type = emotion_type;
    }

    public String getEmotion_reason() {
        return emotion_reason;
    }

    public void setEmotion_reason(String emotion_reason) {
        this.emotion_reason = emotion_reason;
    }

    public Date getEmotion_date() {
        return emotion_date;
    }

    public void setEmotion_date(Date emotion_date) {
        this.emotion_date = emotion_date;
    }

    @Override
    public String toString() {
        return "Emotion{" +
                "id_emocion=" + id_emocion +
                ", id_usuario=" + id_usuario +
                ", emotion_type='" + emotion_type + '\'' +
                ", emotion_reason='" + emotion_reason + '\'' +
                ", emotion_date=" + emotion_date +
                '}';
    }
}
