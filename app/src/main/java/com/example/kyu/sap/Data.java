package com.example.kyu.sap;

import java.util.ArrayList;

/**
 * Created by Kyu on 2017-11-07.
 */

public class Data {
    private String pj_name;
    private String university;
    private String major;
    private ArrayList<String> member_list;
    private String summary;
    private ArrayList<String> tech_list;
    private boolean like = false;
    private int img;
    private String presentation;
    private String video;
    private int number_of_like;


    Data(){
        like = false;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
    }

    Data(String pj_name,String university,String major,String summary,int img,String presentation,String video){
        like = false;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
        setPj_name(pj_name);
        setUniversity(university);
        setMajor(major);
        setSummary(summary);
        setImg(img);
        setPresentation(presentation);
        setVideo(video);
    }

    Data(String pj_name,String university,String major,String summary,int img,String presentation,String video,boolean like){
        this.like = like;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
        setPj_name(pj_name);
        setUniversity(university);
        setMajor(major);
        setSummary(summary);
        setImg(img);
        setPresentation(presentation);
        setVideo(video);
    }

    Data(String pj_name,String university,String major,String summary,int img,String presentation,String video,boolean like,int number_of_like){
        this.like = like;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
        setPj_name(pj_name);
        setUniversity(university);
        setMajor(major);
        setSummary(summary);
        setImg(img);
        setPresentation(presentation);
        setVideo(video);
        setNumber_of_like(number_of_like);
    }

    public String getPj_name() {
        return pj_name;
    }

    public void setPj_name(String pj_name) {
        this.pj_name = pj_name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void addMember(String member){
        member_list.add(member);
    }
    public int memberLength(){ return member_list.size();}
    public String getMember(int index){
        return member_list.get(index);
    }
    public ArrayList<String> getMembers(){return member_list;}

    public void addTech(String tech){
        tech_list.add(tech);
    }
    public int techLength(){ return tech_list.size(); }
    public String getTech(int index){
        return tech_list.get(index);
    }
    public ArrayList<String> getTechs(){return tech_list;}

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getNumber_of_like() {
        return number_of_like;
    }

    public void setNumber_of_like(int number_of_like) {
        this.number_of_like = number_of_like;
    }
}
