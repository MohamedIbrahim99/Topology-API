import org.json.simple.JSONObject;

import java.util.HashMap;

public class Resistor extends Component{


    public Resistor(String type, String id, double default_Val, double min, double max, HashMap<String, String> netlist) {
        super(type, id, default_Val, min, max, netlist);
    }

    public Resistor() {
        super();
    }

    // Print the data of the resistor in ordered manner
    @Override
    public void printComponent() {
        System.out.println("Resistor");
        super.printComponent();
        System.out.println("t1: "+ getT1());
        System.out.println("t2: "+ getT2());
    }


    // Build the Component from a json object
    @Override
    public void buildComponent(JSONObject jsonObject) {
        String type = (String) jsonObject.get("type");
        String id = (String) jsonObject.get("id");

        JSONObject obj = (JSONObject) jsonObject.get("resistance");
        double default_Val = (long) obj.get("default");
        double min = (long) obj.get("min");
        double max = (long) obj.get("max");

        obj = (JSONObject) jsonObject.get("netlist");
        String T1 = (String) obj.get("t1");
        String T2 = (String) obj.get("t2");
        HashMap<String, String> netlist = new HashMap<>();
        netlist.put("t1", T1);
        netlist.put("t2", T2);

        Specifications s = new Specifications(default_Val, min, max, netlist);
        setSpecifications(s);
        setType(type);
        setId(id);
    }

    // Get a json object representing the Component from the class so it can be written to a json file
    public JSONObject getComponent() {
        JSONObject obj = new JSONObject();
        obj.put("type", getType());
        obj.put("id", getId());

        // resistance object
        JSONObject resistance = new JSONObject();
        Specifications s = getSpecifications();
        resistance.put("default", s.getDefault_Val());
        resistance.put("min", s.getMin());
        resistance.put("max", s.getMax());
        obj.put("resistance", resistance);

        // netlist object
        JSONObject netlist = new JSONObject();
        netlist.put("t1", getT1());
        netlist.put("t2", getT2());
        obj.put("netlist", netlist);

        return obj;
    }

    public String getT1() {
        return this.getSpecifications().getNetlist().get("t1");
    }

    public String getT2() {
        return this.getSpecifications().getNetlist().get("t2");
    }

}
