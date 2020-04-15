package com.example.gmail;

public class ContactModel {
    String fullname;
    String avatarResource;
    String description;
    boolean isSelected;

    public ContactModel(String fullname, String avatarResource, String description) {
        this.fullname = fullname;
        this.avatarResource = avatarResource;
        this.description = description;
        this.isSelected = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(String avatarResource) {
        this.avatarResource = avatarResource;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
