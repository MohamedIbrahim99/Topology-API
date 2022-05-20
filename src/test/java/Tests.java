import org.json.JSONException;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void readJSON_test() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        assertEquals(true, api.readJSON(fileName));
    }

    @Test
    public void writeJson_test() throws JSONException {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        // write the topology with the id in the file
        assertEquals(true, api.writeJson("top1"));
    }

    @Test
    public void queryTopologies_test() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        assertEquals(1, api.queryTopologies().size());
    }

    @Test
    public void queryTopologies_test2() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        assertEquals("top1", api.queryTopologies().get(0).getId());
    }

    @Test
    public void deleteTopology_test() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName); // reading to get a topology
        api.deleteTopology("top1"); // getting one and deleting one
        assertEquals(0, api.queryTopologies().size());
    }

    @Test
    public void queryDevices_test() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        assertEquals(2, api.queryDevices("top1").size());
    }

    @Test
    public void queryDevices_test2() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        ArrayList<Component> devices = api.queryDevices("top1");
        assertEquals("res1", devices.get(0).getId());
    }

    @Test
    public void queryDevices_test3() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        ArrayList<Component> devices = api.queryDevices("top1");
        assertEquals("m1", devices.get(1).getId());
    }

    @Test
    public void queryDevicesWithNetlistNode_test1() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);// reading to get a topology
        // we can see in the file that only one node is in vdd nodelist
        assertEquals(1, api.queryDevicesWithNetlistNode("top1", "vdd").size());
    }

    @Test
    public void queryDevicesWithNetlistNode_test2() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);// reading to get a topology
        // we can see in the file that only one node is in vdd nodelist
        assertEquals(2, api.queryDevicesWithNetlistNode("top1", "n1").size());
    }

    @Test
    public void queryDevicesWithNetlistNode_test3() {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);// reading to get a topology
        // we can see in the file that only one node is in vdd nodelist
        assertEquals(1, api.queryDevicesWithNetlistNode("top1", "vss").size());
    }

}
