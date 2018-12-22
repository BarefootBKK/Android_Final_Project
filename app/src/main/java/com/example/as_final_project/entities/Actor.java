package com.example.as_final_project.entities;

public class Actor {
    private String actorName;
    private String roleName;
    private String imageUrl;

    public Actor() {
    }

    public Actor(String actorName, String roleName, String imageUrl) {
        this.actorName = actorName;
        this.roleName = roleName;
        this.imageUrl = imageUrl;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
