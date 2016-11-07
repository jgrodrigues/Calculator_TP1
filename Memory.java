/**
 * Created by jonas on 29/10/2016.
 */
public class Memory {

    private double memoryValue;
    private String memoryName;

    public Memory(String name) {
        memoryValue = 0;
        memoryName = name;
    }

    /**
     * Get Memory Value
     *
     * @return
     */
    public double getMemoryValue() {
        return memoryValue;
    }

    /**
     * Get Memory Name
     *
     * @return
     */
    public String getMemoryName() {
        return memoryName;
    }

    /**
     * Get Memory Value
     *
     * @param memoryValue
     */
    public void setValue(double memoryValue) {
        this.memoryValue = memoryValue;
    }


}
