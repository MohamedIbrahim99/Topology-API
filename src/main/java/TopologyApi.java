import org.json.JSONException;

import java.util.ArrayList;

/**
 * Topology api class, access, manage and store topologies
 * */
public class TopologyApi {

    ArrayList<Topology> topologies = new ArrayList<Topology>();

    /*
    Description: Read a topology from a json file into the memory
    by loading the topology and appending it to topologies arrayList.
    Parameters: fileName: the path of the given JSON file.
    Return: Boolean, Return true if read successfully.
    */
    public boolean readJSON(String fileName){
        Topology topology = new Topology("");
        topology.buildTopology(fileName);
        topologies.add(topology);
        return true;
    }

    /*
    Description: Write a json file of the specified topology with the same name.
    Parameters: TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
    Return: Boolean, Return true if wrote successfully.
    throws: JSONException
    */
    public boolean writeJson(String TopologyID) throws JSONException {
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            topologies.get(idx).writeJson();
            return true;
        }
        return false;
    }

    // Return the index of the desired topology in the topologies list
    private int getTopIdx(String TopologyID){
        for(int i = 0; i < topologies.size() ;i++){
            if(topologies.get(i).getId().equals(TopologyID)){
                return i;
            }
        }
        return -1;
    }

    /*
    Description: Delete the topology with the id topologyId from the topologies API's memory.
    Parameters: TopologyID: the ID of the topology that wanted to be deleted.
    Return: Boolean, Return true if deleted successfully.
    */
    public boolean deleteTopology(String TopologyID){
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            topologies.remove(idx);
            return true;
        }
        else
            return false;
    }

    /*
    Description: Query about all topologies are currently in the API's memory.
    Parameters: void.
    Return: ArrayList.
    */
    public ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    /*
    Description: Query about all the devices in the specified topology.
    Parameters: TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
    Return: ArrayList.
    */
    public ArrayList<Component> queryDevices(String TopologyID){
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            return topologies.get(idx).getComponents();
        }
        else
            return null;
    }

    /*
    Description: Query about which devices are connected to netlist node in given topology.
    Parameters:
        1- TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
        2- netlistNodeId: the given node to query components connected to it.
    Return: ArrayList.
    */
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String netlistNodeId){
        Topology topology = null;
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            topology =  topologies.get(idx);
            ArrayList<Component> devs = new ArrayList<Component>();
            for(String s: topology.getNetlistComponents(netlistNodeId)){
                devs.add(topology.getComponent(s));
            }
            return devs;
        }
        else return null;
    }

}
