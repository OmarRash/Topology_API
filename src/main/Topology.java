package main;

import java.util.ArrayList;
import java.util.List;


public class Topology {
    private String id;
    List<Component> components= new ArrayList<Component>(5);

    public Topology(String id, List<Component> components){
        this.id =id;
        this.components=components;
    }

    public Topology(String id){
        this.id =id;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void add_component(Component com){
        components.add(com);
    }

    public String getId() {
        return id;
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topology topology = (Topology) o;

        return getId().equals(topology.getId())&&components.size()==((Topology) o).components.size();
    }


}

