package main;

import java.util.HashMap;

public class Resistor extends Component {
    private Values resistance;
    public Resistor(String type, String id,HashMap<String,String> netlist, Values resistance) {
        super(type, id,netlist);
        this.resistance = new Values(resistance.getDefault_value(),resistance.getMin(),resistance.getMax());
    }


}
