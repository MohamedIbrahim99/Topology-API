import org.json.simple.JSONObject;

import java.util.HashMap;

public class NMOS extends Component{
    public NMOS(String type, String id, double default_Val, double min, double max, HashMap<String, String> netlist) {
        super(type, id, default_Val, min, max, netlist);
    }

    public NMOS() {
        super();
    }

    // Print the data of the resistor in ordered manner
    @Override
    public void printComponent() {
        System.out.println("NMOS");
        super.printComponent();
        System.out.println("drain: "+ getDrain());
        System.out.println("gate: "+ getGate());
        System.out.println("source: "+ getSource());
    }


    // Build the Component from a json object
    @Override
    public void buildComponent(JSONObject jsonObject){
        String type = (String) jsonObject.get("type");
        String id = (String) jsonObject.get("id");

        JSONObject obj = (JSONObject) jsonObject.get("m(l)");
        double default_Val = (double) obj.get("default");
        double min = (long) obj.get("min");
        double max = (long) obj.get("max");

        obj = (JSONObject) jsonObject.get("netlist");
        String d = (String) obj.get("drain");
        String g = (String) obj.get("gate");
        String s = (String) obj.get("source");
        HashMap<String, String> netlist = new HashMap<>();
        netlist.put("drain", d);
        netlist.put("gate", g);
        netlist.put("source", s);

        Specifications specs = new Specifications(default_Val, min, max, netlist);
        setSpecifications(specs);
        setType(type);
        setId(id);
    }

    // Get a json object representing the Component from the class so it can be written to a json file
    @Override
    public JSONObject getComponent(){
        JSONObject obj = new JSONObject();
        obj.put("type", getType());
        obj.put("id", getId());

        // ml1 object
        JSONObject ml1 = new JSONObject();
        Specifications s = getSpecifications();
        ml1.put("default", s.getDefault_Val());
        ml1.put("min", s.getMin());
        ml1.put("max", s.getMax());
        obj.put("m(l)", ml1);

        // netlist object
        JSONObject netlist = new JSONObject();
        netlist.put("drain", getDrain());
        netlist.put("gate", getGate());
        netlist.put("source", getSource());
        obj.put("netlist", netlist);

        return obj;
    }

    public String getDrain() {
        return this.getSpecifications().getNetlist().get("drain");
    }

    public String getGate() {
        return this.getSpecifications().getNetlist().get("gate");
    }

    public String getSource() {
        return this.getSpecifications().getNetlist().get("source");
    }

}
