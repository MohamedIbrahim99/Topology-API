import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Abstract class represents the generic Component class
 * */
public abstract class Component {
    private String type;
    private String id;
    private Specifications specs ;

    // Constructor
    public Component() {
    }

    // Constructor
    public Component(String type, String id, double default_Val, double min, double max, HashMap<String, String> netlist){
        this.type = type;
        this.id = id;
        this.specs = new Specifications(default_Val, min, max, netlist);
    }

    /* Setters and Getters */
    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setSpecifications(Specifications specs) {
        this.specs = specs;
    }

    public Specifications getSpecifications() {
        return this.specs;
    }

    // Build the Component from a json object
    public void buildComponent(JSONObject jsonObject) {}


    // Get a json object representing the Component from the class so it can be written to a json file
    public JSONObject getComponent() {
        return null;
    }

    // Print Component attributes
    public void printComponent(){
        System.out.println("type: "+getType());
        System.out.println("id: "+getId());

        Specifications s = getSpecifications();
        System.out.println("default: "+ s.getDefault_Val());
        System.out.println("min: "+ s.getMin());
        System.out.println("max: "+ s.getMax());
    }

}
