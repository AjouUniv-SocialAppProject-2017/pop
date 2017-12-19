package com.example.kyu.sap;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyu on 2017-11-07.
 */

public class Data {

    //팀이름(프로젝트 이름), 학교, 학과, 구성원, 요약, 해시태그, 좋아요, 썸넬, 발표자료(pdf), 시연영상(youtube)
    //"날짜" 가 새로 등록 되어야 함
    private String pj_name;
    private String university;
    private String major;
    private ArrayList<String> member_list;
    private String summary;
    private ArrayList<String> tech_list;
    private boolean like = false;
    private int img;

    private int face_img;
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

    Data(String pj_name,String university,String major,String summary,int img, int face_img, String presentation,String video,boolean like,int number_of_like){
        this.like = like;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
        setPj_name(pj_name);
        setUniversity(university);
        setMajor(major);
        setSummary(summary);
        setImg(img);
        setFace_img(face_img);
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

    public int getFace_img() {
        return face_img;
    }

    public void setFace_img(int face_img) {
        this.face_img = face_img;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("pj_name", pj_name);
        result.put("university", university);
        result.put("major", major);
        result.put("member_list",member_list);
        result.put("summary",summary);
        result.put("tech_list",tech_list);
        result.put("like",like);
        result.put("img",img);
        result.put("face_img",face_img);
        result.put("presentation",presentation);
        result.put("video",video);
        result.put("number_of_like",number_of_like);

        return result;
    }
}
