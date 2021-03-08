package com.example.projetandroid.Entities;

import com.google.gson.annotations.SerializedName;

public class Message {
    public int id;
    @SerializedName("message")
    public String message;
    @SerializedName("idsender")
    public int idsender;
    @SerializedName("idreceiver")
    public int idreceived;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int idsender) {
        this.idsender = idsender;
    }

    public int getIdreceived() {
        return idreceived;
    }

    public void setIdreceived(int idreceived) {
        this.idreceived = idreceived;
    }

    public Message() {
    }

    public Message(String message, int idsender, int idreceived) {
        this.message = message;
        this.idsender = idsender;
        this.idreceived = idreceived;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", idsender=" + idsender +
                ", idreceived=" + idreceived +
                '}';
    }
}
