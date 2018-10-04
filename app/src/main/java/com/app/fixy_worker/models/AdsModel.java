package com.app.fixy_worker.models;

public class AdsModel extends BaseModel{

    LoginModel.ResponseBean.SelectedServicesBean subcategoriesBean;
    String original_price, percentage, period, description;

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static AdsModel instance;

    public static AdsModel getInstance() {
        if (instance == null) {
            instance = new AdsModel();
        }
        return instance;
    }

    public static void setInstance(AdsModel instance) {

        AdsModel.instance = instance;
    }

    public LoginModel.ResponseBean.SelectedServicesBean getSubcategoriesBean() {
        return subcategoriesBean;
    }

    public void setSubcategoriesBean(LoginModel.ResponseBean.SelectedServicesBean subcategoriesBean) {
        this.subcategoriesBean = subcategoriesBean;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
