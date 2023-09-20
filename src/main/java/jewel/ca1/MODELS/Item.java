package jewel.ca1.MODELS;

import jewel.ca1.MyLinkedList;

public class Item {

    private String description;
    private String gender;
    private String type;
    private String ImgURL;

    private double retail;

    public MyLinkedList<Material> materials;

    public Item(String description, String gender, String type, String imgURL, double retail){
        this.description = description;
        this.gender = gender;
        this.type = type;
        this.ImgURL = imgURL;
        this.retail = retail;
        materials = new MyLinkedList<>();
    }

    public MyLinkedList<Material> getMaterials() {
        return materials;
    }

    public void clear(){
        materials.clear();
    }
    //Getters and setters

    public String getDescription(){
        return description;
    }

    public String getGender(){
        return gender;
    }

    public String getType(){
        return type;
    }

    public String getImgURL(){
        return ImgURL;
    }

    public double getRetail(){
        return retail;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setImgURL(String imgURL){
        this.ImgURL = imgURL;
    }

    public void setRetail(double retail){
        this.retail = retail;
    }

    public String toString(){
        return "Type: " + type + " | Gender : " + gender + " | Description:  " + description + " | Retail:  €" + retail;
    }

}
