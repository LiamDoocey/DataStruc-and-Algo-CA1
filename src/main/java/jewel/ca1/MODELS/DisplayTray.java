package jewel.ca1.MODELS;

import jewel.ca1.MyLinkedList;

public class DisplayTray {
    private String CaseID;
    private String colour;
    private String ID;

    private double width;
    private double height;

    public MyLinkedList<Item> items;

    public DisplayTray(String CaseID, String ID,  String colour, double width, double height) {
        this.CaseID = CaseID;
        this.ID = ID;
        this.width = width;
        this.height = height;
        this.colour = colour;
        items = new MyLinkedList<>();
    }

    public MyLinkedList<Item> getItems() {
        return items;
    }

    public void clear(){
        items.clear();
    }


    //Getters and setters
    public String getCaseID() {
        return CaseID;
    }

    public String getID() {
        return ID;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getColour() {
        return colour;
    }

    public void setCaseID(String CaseID) {
        this.CaseID = CaseID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString(){
        return  "ID: " + ID + " | Colour: " + colour + " | Width (cm): " + width + " | Height (cm): " + height;
    }
}
