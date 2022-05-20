import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);


        //api.deleteTopology("top1");

        System.out.println("queryTopologies : ");
		ArrayList<Topology> list = api.queryTopologies();
		for(Topology t : list) {
			t.printTopology();
            System.out.println("---");
		}

        System.out.println("queryDevices : ");
		ArrayList<Component> devices0 = api.queryDevices("top1");
		for(Component c : devices0) {
			c.printComponent();
            System.out.println("---");
		}
        System.out.println("---------------------");

        System.out.println("queryDevicesWithNetlistNode : ");
        ArrayList<Component> devices1 = api.queryDevicesWithNetlistNode("top1", "n1");
        for(Component c : devices1) {
            c.printComponent();
            System.out.println("---");
        }
        System.out.println("---------------------");
        System.out.println("queryDevicesWithNetlistNode : ");
        ArrayList<Component> devices2 = api.queryDevicesWithNetlistNode("top1", "vss");
        for (Component c : devices2){
            c.printComponent();
            System.out.println("---");
        }
        System.out.println("---------------------");
        System.out.println("Done !!");
    }
}
