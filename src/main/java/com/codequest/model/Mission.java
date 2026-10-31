package com.codequest.model;

public class Mission {
    private int id, xpReward;
    private String title, description, category, difficulty, option1, option2, option3, option4;
    public int getId(){return id;} public void setId(int v){id=v;}
    public int getXpReward(){return xpReward;} public void setXpReward(int v){xpReward=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getDifficulty(){return difficulty;} public void setDifficulty(String v){difficulty=v;}
    public String getOption1(){return option1;} public void setOption1(String v){option1=v;}
    public String getOption2(){return option2;} public void setOption2(String v){option2=v;}
    public String getOption3(){return option3;} public void setOption3(String v){option3=v;}
    public String getOption4(){return option4;} public void setOption4(String v){option4=v;}
}
