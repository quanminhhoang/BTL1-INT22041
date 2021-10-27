package com.example.dictionaryapp;

public class Word {
    private String world_target;
    private String world_explain;

    public Word(String world_target, String world_explain) {
        this.world_target = world_target;
        this.world_explain = world_explain;
    }

    public String getWordTarget() {
        return world_target;
    }

    public void setWordTarget(String world_target) {

        this.world_target = world_target;
    }

    public String getWordExplain() {

        return world_explain;
    }

    public void setWordExplain(String world_explain) {

        this.world_explain = world_explain;
    }
}
