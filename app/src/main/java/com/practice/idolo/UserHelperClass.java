package com.practice.idolo;

public class UserHelperClass {

    String username,password,instagram,twitter,youtube;

    public UserHelperClass() {
    }

    public UserHelperClass(String username, String password, String instagram, String twitter, String youtube) {
        this.username = username;
        this.password = password;
        this.instagram = instagram;
        this.twitter = twitter;
        this.youtube = youtube;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
