package com.werq.patient.service.model.chat;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;
import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by nisostech on 25/1/18.
 */

@Keep
public class Author implements IUser {


    private String userid;

    private String msg_id;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    private String username;

    private String avatar;

    private boolean online;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Author() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Author(String id, String name, String avatar, boolean online) {
        this.userid = id;
        this.username = name;
        this.avatar = avatar;
        this.online = online;
    }

    @Override
    public String getId() {
        return userid;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return online;
    }

    public void setId(String id) {
        this.userid = id;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}