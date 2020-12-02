package ru.xbitly.quest;

public class Situation {
    Situation[] direction;
    String title, text, variant1, variant2, variant3;
    int dHealth,dBrain,dStar;

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
        direction = new Situation[count];
    }
}
