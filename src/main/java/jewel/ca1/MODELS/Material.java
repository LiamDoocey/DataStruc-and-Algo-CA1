package jewel.ca1.MODELS;

public class Material {

    private String type;
    private String description;
    private int quantity;
    private int quality;

    public Material(String type, String description, int quantity, int quality){
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.quality = quality;
    }

    //Getters and setters

    public String getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getQuality(){
        return quality;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setQuality(int quality){
        this.quality = quality;
    }

    @Override
    public String toString(){
        return "Type: " + type + " | Description " + description + " | Quantity " + quantity + " | Quality" + quality;
    }
}
