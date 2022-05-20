import org.json.JSONException;

import java.util.ArrayList;

/**
 * Topology api class, access, manage and store topologies
 * */
public class TopologyApi {

    ArrayList<Topology> topologies = new ArrayList<Topology>();

    /*
    Read a topology from a json file into the memory by loading the topology and appending it
    to topologies arrayList
    */
    public boolean readJSON(String fileName){
        Topology topology = new Topology("");
        topology.buildTopology(fileName);
        topologies.add(topology);
        return true;
    }

    // Write a json file of the specified topology with the same name
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

    // Delete the topology with the name topologyId from the topologies array list
    public boolean deleteTopology(String TopologyID){
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            topologies.remove(idx);
            return true;
        }
        else
            return false;
    }

    // Return all the topologies in memory
    public ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    // Return a list of the devices in the specified topology
    public ArrayList<Component> queryDevices(String TopologyID){
        int idx = getTopIdx(TopologyID);
        if(getTopIdx(TopologyID) != -1){
            return topologies.get(idx).getComponents();
        }
        else
            return null;
    }

    // Return a list of the devices that share the specified node list
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
