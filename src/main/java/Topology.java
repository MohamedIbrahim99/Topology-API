import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represent the topology and its components
 * */
public class Topology {
    private String id;
    private ArrayList<Component> components;
    private HashMap<String, ArrayList<String>> netlist;
    private static FileWriter file;

    public Topology(String id) {
        this.id = id;
        this.components = new ArrayList<Component>();
        this.netlist = new HashMap<String, ArrayList<String>>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Return all the components in the topology
    public ArrayList<Component> getComponents() {
        return components;
    }

    // Return the component with the specified id
    public Component getComponent(String id){
        for(Component c : components){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<String> getNetlistComponents(String netlistName){
        return netlist.get(netlistName);
    }

    // Remove the component with the specified id
    public boolean removeComponent(String id){
        for(int i = 0; i < components.size() ; i++){
            if(components.get(i).getId().equals(id)){
                components.remove(i);
                return true;
            }
        }
        return false;
    }

    // Add a component to the topology
    public void addComponent(Component c){
        components.add(c);
        if(c.getType().equals("resistor")){
            Resistor res = (Resistor) c;
            addToNetList(res.getT1(), res.getId());
            addToNetList(res.getT2(), res.getId());
        }
        else if(c.getType().equals("nmos")){
            NMOS res = (NMOS) c;
            addToNetList(res.getDrain(), res.getId());
            addToNetList(res.getGate(), res.getId());
            addToNetList(res.getSource(), res.getId());
        }
    }

    // Read the json file and transfer the info into topology class and hence to memory.
    public void buildTopology(String filePath){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject =  (JSONObject) obj;
            String name = (String) jsonObject.get("id");
            id = name;
            JSONArray comps = (JSONArray) jsonObject.get("components");
            for (int i = 0 ; i < comps.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) comps.get(i);
                if(jsonObject1.get("type").equals("resistor")){
                    Resistor resistor = new Resistor();
                    resistor.buildComponent(jsonObject1);
                    addComponent(resistor);
                }
                else if(jsonObject1.get("type").equals("nmos")){
                    NMOS nmos = new NMOS();
                    nmos.buildComponent(jsonObject1);
                    addComponent(nmos);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Write the topology to a json file
       the file name is the topology id */
    public void writeJson(){
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        JSONArray comps = new JSONArray();
        for(Component c: components){
            comps.add(c.getComponent());
        }
        obj.put("components", comps);

        try {
            file = new FileWriter(getId());
            file.write(obj.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                file.flush();
                file.close();
            }
            catch (IOException e) {
            }
        }
    }

    public synchronized void addToNetList(String mapKey, String newDev) {
        ArrayList<String> devList = netlist.get(mapKey);
        // if list does not exist create it
        if(devList == null) {
            devList = new ArrayList<String>();
            devList.add(newDev);
            netlist.put(mapKey, devList);
        }
        else {
            // add if item is not already in list
            if(!devList.contains(newDev)) devList.add(newDev);
        }
    }

    public void printTopology (){
        System.out.println("Topology : ");
        for (int i = 0 ; i < components.size() ; i++){
            Component c = components.get(i);
            System.out.println("Component number " + i + " : ");
            c.printComponent();
        }
        System.out.println("--------------");
    }

}
