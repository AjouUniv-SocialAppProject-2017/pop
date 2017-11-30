package com.example.samsung.chat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SAMSUNG on 2017-11-23.
 */

public class User {

    private DatabaseReference reference;
    public User(){

    }

    public User(String id, String password, String user_name, String email, String major, String phone_num) {
        this.id = id;
        this.password = password;
        this.user_name = user_name;
        this.email = email;
        this.major = major;
        this.phone_num = phone_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    private String id;

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }

    private String password;
    private String user_name;
    private String team_name;
    private String email;
    private String school;
    private String major;
    private String phone_num;


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("password", password);
        result.put("user_name", user_name);
        result.put("team_name", team_name);
        result.put("email", email);
        result.put("school", school);
        result.put("major", major);
        result.put("phone_num", phone_num);

        return result;
    }

}
