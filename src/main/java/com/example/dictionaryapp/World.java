package com.example.dictionaryapp;

public class World{
    private String world_target;
    private String world_explain;

    public World(String world_target, String world_explain) {
        this.world_target = world_target;
        this.world_explain = world_explain;
    }

    public String getWorld_target() {
        return world_target;
    }

    public void setWorld_target(String world_target) {

        this.world_target = world_target;
    }

    public String getWorld_explain() {

        return world_explain;
    }

    public void setWorld_explain(String world_explain) {

        this.world_explain = world_explain;
    }
}
