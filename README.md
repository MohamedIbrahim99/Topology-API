# Topology-API

### API library which does the following :
1. Read and write topologies to and from disk.
2. Stores multiple topologies in memory.
3. Execute operations on topologies.

All of this to provide the functionality to access, manage and store device topologies.

#### Topology Specification
A topology is a set of electronic components that are connected together.

### Functional Requirements
Provide the functionality to :
1. Read a topology from a given JSON file and store it in the memory.
2. Write a given topology from the memory to a JSON file.
3. Query about which topologies are currently in the memory.
4. Delete a given topology from memory.
5. Query about which devices are in a given topology.
6. Query about which devices are connected to a given netlist node in a given topology.

### Why JAVA is used as the programming language

- Java is an object-oriented programming language. So it's a good choice to be used to model the API in an appropriate way.
- Java is supported by powerful Serialization and Deserialization JSON parsers like JSON.simple, GSON and Jackson.

### ⛏️ Used Technologies

- IntelliJ - IDE
- Maven - Build tool
- JUnit - Testing
- JSON.simple - Json parser 
- [Code analysis] - Code analysis tools builtin IntelliJ


Developed this project is in an object-oriented manner.



### API Documentation

##### readJSON(String fileName)
- Description: Read a topology from a json file into the memory by loading the topology and appending it
    to topologies arrayList, Return true if read successfully.
- Parameters: fileName: the path of the given JSON file.
- Return: Boolean.

##### writeJson(String TopologyID)
- Description: Write a json file of the specified topology with the same name, Return true if wrote successfully.
- Parameters: TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
- Return: Boolean.
- throws: JSONException
    
##### deleteTopology(String TopologyID)
- Description: Delete the topology with the id topologyId from the topologies API's memory, Return true if deleted successfully.
- Parameters: TopologyID: the ID of the topology that wanted to be deleted.
- Return: Boolean.
    
##### queryTopologies()
- Description: Query about all topologies are currently in the API's memory.
- Parameters: void.
- Return: ArrayList<Topology>.

##### queryDevices(String TopologyID)
- Description: Query about all the devices in the specified topology.
- Parameters: TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
- Return: ArrayList<Component>.
  
##### queryDevicesWithNetlistNode(String TopologyID, String netlistNodeId)
- Description: Query about which devices are connected to netlist node in given topology.
- Parameters: 
  1- TopologyID: the ID of the topology that wanted to be written into disk as a JSON file.
  2- netlistNodeId: the given node to query components connected to it.
- Return: ArrayList<Component>.

### Unit Testing


![Screenshot 2022-05-20 080934](https://user-images.githubusercontent.com/61321123/169467770-7c294c61-21f0-46de-906b-e0db680f9992.png)

