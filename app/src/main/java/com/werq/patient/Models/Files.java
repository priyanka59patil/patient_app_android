package com.werq.patient.Models;

public class Files {
    int file;
    String fileType;
    String fileName;
    String userType;
    String userName;
    String time;

    public Files(int file, String fileType, String fileName, String userType, String userName, String time) {
        this.file = file;
        this.fileType = fileType;
        this.fileName = fileName;
        this.userType = userType;
        this.userName = userName;
        this.time = time;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
