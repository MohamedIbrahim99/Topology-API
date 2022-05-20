public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/topology.json";
        TopologyApi api = new TopologyApi();
        api.readJSON(fileName);
        api.queryDevicesWithNetlistNode("top1", "vss");
    }
}
