package ru.xbitly.quest;

public class Situation {

    String title, text, variant1, variant2, variant3;
    int dHealth,dBrain,dStar;
    int count;

    public Situation (String title, String text, String variant1, String variant2, String variant3,
                      int count, int dHealth,int dBrain,int dStar) {
        this.title=title;
        this.text=text;
        this.dHealth = dHealth;
        this.dBrain = dBrain;
        this.dStar = dStar;
        this.variant1 = variant1;
        this.variant2 = variant2;
        this.variant3 = variant3;
        this.count = count;
    }
}
