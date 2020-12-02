package ru.xbitly.quest;

public class Story {

    public Situation current_situation;

    Story(Situation start_story){
        current_situation = start_story;
    }

    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}
