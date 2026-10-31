package com.codequest.model;

public class User {
    private int id, xp, level;
    private String username, email;
    public int getId(){ return id; } public void setId(int id){ this.id=id; }
    public int getXp(){ return xp; } public void setXp(int xp){ this.xp=xp; }
    public int getLevel(){ return level; } public void setLevel(int level){ this.level=level; }
    public String getUsername(){ return username; } public void setUsername(String username){ this.username=username; }
    public String getEmail(){ return email; } public void setEmail(String email){ this.email=email; }
}
