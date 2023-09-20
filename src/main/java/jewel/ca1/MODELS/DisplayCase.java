package jewel.ca1.MODELS;

import jewel.ca1.MyLinkedList;

public class DisplayCase {
    private String ID;
    private String type;
    private String lighting;

    private int position;

    public MyLinkedList<DisplayTray> trays;

    public DisplayCase(String ID, String type, String lighting, int position) {
        this.ID = ID;
        this.type = type;
        this.lighting = lighting;
        this.position = position;
        trays = new MyLinkedList<>();
    }

    //Getters and setters

    public String getID() {
        return ID;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getLighting() {
        return lighting;
    }

    public MyLinkedList<DisplayTray> getTrays() {
        return trays;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    @Override
    public String toString(){
        return "ID: " + ID + " | Type: " + type + " | Lighting: " + lighting;
    }

}
