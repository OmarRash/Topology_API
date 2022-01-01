package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class APIs {
    static List<Topology> topologyList = new ArrayList<>();
    public APIs(){

    }

    public static void setTopology(Topology topology) {
        APIs.topologyList.add(topology);
    }


    /**
     * creates a json file of a given topology.
     *
     * @param  topology  a topology that wanted to be serialized
     *
     * @return path of generated json file
     *
     */


    public static Path writeJson(Topology topology) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(topology);
        Path path = Paths.get("jsonfiles/topology.json");
        int i = 0;
        while (Files.exists(path)) {
            path = Paths.get("jsonfiles/topology" + i + ".json");
            i++;
        }

        try {
            Files.writeString(path, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    /**
     * creates topology Object out of a given json file.
     *
     * @param  filePath  a path of the file that will be Deserialized
     * @return topology generated from file
     *
     * @throws IOException throws an exception if the file is not found.
     */

    public static Topology readJson(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            String json = Files.readString(filePath);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Component.class, new PolymorphDeserializer<Component>())
                    .create();
            Topology top = gson.fromJson(json, Topology.class);
            topologyList.add(top);
            return top;
        } else {
            throw new IOException("No such file");
        }
    }
    /**
     * A method to return list of topologies stored in the program.
     *
     * @return list of topologies
     *
     */
    public static List<Topology> getTopologyList() {
        return topologyList;
    }
    /**
     * A method to delete topology by .
     * @param id id of a topology that will be deleted.
     */
    public static void deleteTopology(String id) {
        topologyList.removeIf(top -> top.getId().equals(id));
    }
    /**
     * A method to return list of topologies stored in the program.
     * @param id id of a topology to return all its components.
     * @return list of components.
     *
     */

    public static List<Component> getDevices(String id) {
        for (Topology top : topologyList) {
            if (top.getId().equals(id)) {
                return top.getComponents();
            }
        }
        return Collections.emptyList();
    }
    /**
     * A method to return list of topologies stored in the program.
     * @param topologyId id of a topology to get all its components.
     *
     * @param componentID id of a component to return its netlist.
     * @return list of connected components with their connection(netlist).
     *
     */
    public static HashMap<String, String> connectedDevices (String topologyId, String componentID) {
        for (Topology top : topologyList) {
            if (top.getId().equals(topologyId)){
                for (Component comp : top.getComponents()) {
                    if (comp.getId().equals(componentID)) {
                        return comp.getNetlist();
                    }
                }
            }
        }
        return (HashMap<String, String>) Collections.emptyList();
    }

}
