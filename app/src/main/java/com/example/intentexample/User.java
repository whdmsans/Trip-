package com.example.intentexample;

import java.util.HashMap;
import java.util.Map;

public class User {
    String profile;
    String name;
    String email;
    String uid;

    public User() {
    }

    public User(String profile, String name, String email, String uid) {
        this.profile = profile;
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("email", email);
        result.put("name", name);
        result.put("profile", profile);

        return result;
    }
}
