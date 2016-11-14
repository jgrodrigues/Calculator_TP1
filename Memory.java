/**
 * Created by Jonas Rodrigues, ID 49806 on 29/10/2016.
 */

public class Memory {

    /* Instance Variables */
    private double memoryValue;
    private String memoryName;

    /* Constructor */
    public Memory(String memoryName) {
        memoryValue = 0;
        this.memoryName = memoryName;
    }

    /* Other methods */

    /**
     * Get Memory Value
     *
     * @return the value of the memory
     */
    public double getMemoryValue() {
        return memoryValue;
    }

    /**
     * Get Memory Name
     *
     * @return the name of the memory
     */
    public String getMemoryName() {
        return memoryName;
    }

    /**
     * Get Memory Value
     *
     * @param memoryValue the new value to assign to the memory
     */
    public void setValue(double memoryValue) {
        this.memoryValue = memoryValue;
    }


}
