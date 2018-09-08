package com.app.fixy_worker.models;

public class SelectServiceModel {

    String id,name,cat_name;
    int headerPos;

    public int getHeaderPos() {
        return headerPos;
    }

    public void setHeaderPos(int headerPos) {
        this.headerPos = headerPos;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    boolean isHeader,isSelected;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
