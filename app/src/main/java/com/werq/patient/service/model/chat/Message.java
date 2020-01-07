package com.werq.patient.service.model.chat;

import androidx.annotation.Keep;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Created by nisostech on 25/1/18.
 */
@Keep
public class Message implements IMessage,
        MessageContentType.Image /*this is for default image messages implementation*/ {

    private String id;
    private String text;
    private Date createdAt;
    private String channelId;
    private String author_id;
    private Long timestamp;
    private String  msg_id;
    private String media;
    Author user;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }
    private Image image;
    private String voice_id;
    private String thumbnail;
    private String mediaType;
    private String infoType;
    private boolean isRead;
    private Long startTime;
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getisRead() {

        return isRead;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }



    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getVoice_id() {
        return voice_id;
    }

    public void setVoice_id(String voice_id) {
        this.voice_id = voice_id;
    }

    public void setisRead(boolean read) {
        isRead = read;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public Image getImage() {
        return image;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Message(String id, Author user, String text) {
        this(id, user, text, new Date());
    }

    public Message(String id, Author user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Message() {

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public Author getUser() {
        return this.user;
    }

    @Override
    public String getImageUrl() {
        return image == null ? null : image.url;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(Author user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public static class Image {






        public void setUrl(String url) {
            this.url = url;
        }

        private String url;


        public String getUrl(){
            return url;
        }
        public Image(String url) {
            this.url = url;

        }

    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", image=" + image +
                ", thumbnail='" + thumbnail + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", infoType='" + infoType + '\'' +
                ", isRead=" + isRead +
                ", media='" + media + '\'' +
                '}';
    }
}