package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Information {
    private Integer informationid;
    private String informationname;
    private Integer typeid;
    private Date date;
    private String filetype;
    private String uploader;

    private Infotype infotype;

    public Infotype getInfotype() {
        return infotype;
    }

    public void setInfotype(Infotype infotype) {
        this.infotype = infotype;
    }

    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public String getInformationname() {
        return informationname;
    }

    public void setInformationname(String informationname) {
        this.informationname = informationname;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
