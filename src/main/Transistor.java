package main;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Transistor extends Component {
    @SerializedName("m(l)")
    private Values ml ;
    public Transistor(String type, String id,HashMap<String,String> netlist , Values ml) {
        super(type, id,netlist);
        this.ml = new Values(ml.getDefault_value(), ml.getMin(), ml.getMax());
    }
    


}

