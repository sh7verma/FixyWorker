package com.app.fixy_worker.models;

public class CreateActivityModel {
    String user_image,user_name,user_email,referal_code,gender;
    String back_image,front_image,document_type,document_number,documented_name,services;

    public static CreateActivityModel getInstance() {
        if (instance == null){
            instance = new CreateActivityModel();
        }
        return instance;
    }

    public static void setInstance(CreateActivityModel instance) {
        CreateActivityModel.instance = instance;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public static CreateActivityModel instance;


    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getReferal_code() {
        return referal_code;
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBack_image() {
        return back_image;
    }

    public void setBack_image(String back_image) {
        this.back_image = back_image;
    }

    public String getFront_image() {
        return front_image;
    }

    public void setFront_image(String front_image) {
        this.front_image = front_image;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getDocumented_name() {
        return documented_name;
    }

    public void setDocumented_name(String documented_name) {
        this.documented_name = documented_name;
    }
}
