import java.util.HashMap;

/**
 * Class Specifications: represents the specifications of the component
 **/
public class Specifications {
    private double default_Val;
    private double min;
    private double max;
    private HashMap<String, String> netlist = new HashMap<>();

    // Constructor
    public Specifications(double default_Val, double min, double max, HashMap<String, String> netlist) {
        this.default_Val = default_Val;
        this.min = min;
        this.max = max;
        this.netlist = netlist;
    }

    /* Setters and Getters */
    public double getDefault_Val() {
        return default_Val;
    }

    public void setDefault_Val(double deafult_Val) {
        this.default_Val = deafult_Val;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public HashMap<String, String> getNetlist() {
        return netlist;
    }

    public void setNetlist(HashMap<String, String> netlist) {
        this.netlist = netlist;
    }
}

