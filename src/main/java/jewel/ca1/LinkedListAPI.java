package jewel.ca1;

import jewel.ca1.MODELS.DisplayCase;
import jewel.ca1.MODELS.DisplayTray;
import jewel.ca1.MODELS.Item;
import jewel.ca1.MODELS.Material;

// This class is used to store all the linked lists in the system & make them accessible to all controllers
public class LinkedListAPI {

    // The linked lists
    private MyLinkedList<DisplayCase> cases;
    private MyLinkedList<DisplayTray> trays;
    private MyLinkedList<Item> items;
    private MyLinkedList<Material> materials;
    private MyLinkedList<String> results;

    // Constructor
    public LinkedListAPI(){
        cases = new MyLinkedList<>();
        trays = new MyLinkedList<>();
        items = new MyLinkedList<>();
        materials = new MyLinkedList<>();
        results = new MyLinkedList<>();
    }

    // Getters & setters
    public MyLinkedList<DisplayCase> getCases() {
        return cases;
    }

    public MyLinkedList<DisplayTray> getTrays() {
        return trays;
    }

    public MyLinkedList<Item> getItems() {
        return items;
    }

    public MyLinkedList<Material> getMaterials() {
        return materials;
    }

    public MyLinkedList<String> getResults(){
        return results;
    }

    public DisplayCase getCase(int index){
        return cases.get(index);
    }

    public DisplayTray getTray(int index){
        return trays.get(index);
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public Material getMaterial(int index){
        return materials.get(index);
    }

    public void destroySystem(){
        cases.clear();
        trays.clear();
        items.clear();
        materials.clear();
    }
}
