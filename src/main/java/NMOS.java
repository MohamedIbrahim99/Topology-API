import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class NMOS extends Component{
    public NMOS(String type, String id, double default_Val, double min, double max, HashMap<String, String> netlist) {
        super(type, id, default_Val, min, max, netlist);
    }

    // Print the data of the resistor in ordered manner
    @Override
    public void printComponent() {
        System.out.println("NMOS");
        super.printComponent();
        System.out.println("drain: "+ this.getSpecifications().getNetlist().get("drain"));
        System.out.println("gate: "+ this.getSpecifications().getNetlist().get("gate"));
        System.out.println("source: "+ this.getSpecifications().getNetlist().get("source"));
    }


    // Build the Component from a json object
    @Override
    public void buildComponent(JSONObject jsonObject) throws JSONException {
        String type = (String) jsonObject.get("type");
        String id = (String) jsonObject.get("id");

        JSONObject obj = (JSONObject) jsonObject.get("m(l)");
        double default_Val = (double) obj.get("default");
        double min = (double) obj.get("min");
        double max = (double) obj.get("max");

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
    public JSONObject getComponent() throws JSONException {
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
        netlist.put("drain", s.getNetlist().get("drain"));
        netlist.put("gate", s.getNetlist().get("gate"));
        netlist.put("source", s.getNetlist().get("source"));
        obj.put("netlist", netlist);

        return obj;
    }

}
