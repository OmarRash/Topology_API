package main;

import java.util.HashMap;

@JsonType(property="type",
        subtypes = {@JsonSubtype(clazz=Resistor.class,name="resistor"),
                @JsonSubtype(clazz=Transistor.class,name="nmos")})
public class Component {
    protected String type;
    protected String id;
    private HashMap<String,String> netlist=new HashMap<String,String>();

    public Component(String type, String id, HashMap<String,String> map) {
        this.id=id;
        this.type=type;
        this.netlist=map;
    }

    public Component(String type, String id, HashMap<String,String> map, Values val) {
        this.id=id;
        this.type=type;
        if(type.equals("resistor")){
            Resistor resistor = new Resistor(type,id,map,val);
        }
        else{
            Transistor transistor= new Transistor(type,id,map,val);
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getNetlist() {
        return netlist;
    }
}
