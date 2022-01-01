package main;

import main.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class APIsTest {
    APIs api;
    @BeforeEach
    public void API_Creation() {
        api = new APIs();
    }


    @DisplayName("Can read and write JSON")
    @Test
    public void testWriteJson() throws IOException{
        String type="resistor";
        String type2="nmos";
        String id = "id1";
        String id2= "id2";
        // create resistor component
        Values resistance = new Values(1.3f,1.2f,2);
        HashMap<String, String> netlist = new HashMap<>();
        netlist.put("t1", "v");
        netlist.put("t2", "n");
        Resistor com = new Resistor(type, id,netlist, resistance);
        // create transistor component
        netlist = new HashMap<>();
        netlist.put("drain", "v1");
        netlist.put("gate", "n1");
        netlist.put("source", "v2");
        Values ml = new Values(1.3f,1.1f,2);
        Transistor com2 = new Transistor(type2, id2,netlist, ml);
        // create list of components
        List<Component> list = new ArrayList<Component>(2);
        list.add(com);
        list.add(com2);
        Topology t1 = new Topology("1", list);
        //write
        Path path = api.writeJson(t1);
        //read
        Topology t2 = api.readJson(path);
        assertTrue(t1.equals(t2));
    }
    @DisplayName("Candelete")
    @Test
    public void testdelete() throws IOException{
        String type="resistor";
        String type2="nmos";
        String id = "id1";
        String id2= "id2";
        // create resistor component
        Values resistance = new Values(1.3f,1.2f,2);
        HashMap<String, String> netlist = new HashMap<>();
        netlist.put("t1", "v");
        netlist.put("t2", "n");
        Resistor com = new Resistor(type, id,netlist, resistance);
        // create transistor component
        netlist = new HashMap<>();
        netlist.put("drain", "v1");
        netlist.put("gate", "n1");
        netlist.put("source", "v2");
        Values ml = new Values(1.3f,1.1f,2);
        Transistor com2 = new Transistor(type2, id2,netlist, ml);
        // create list of components
        List<Component> list = new ArrayList<Component>(2);
        list.add(com);
        list.add(com2);
        Topology t1 = new Topology("1", list);
        api.setTopology(t1);
        api.deleteTopology("1");
        assertTrue( api.getTopologyList().size()==0);
    }

}