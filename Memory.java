/**
 * Created by jonas on 29/10/2016.
 */
public class Memory {

    private double memory1Value;
    private double memory2Value;
    private String memory1Name;
    private String memory2Name;

    public Memory() {
        memory1Value = 0;
        memory2Value = 0;
        memory1Name = null;
        memory2Name = null;
    }

    /**
     * CREATE 2 MEMORIES
     *
     * @param memory1Name
     * @param memory2Name
     */
    public Memory(String memory1Name, String memory2Name) {
        this.memory1Name = memory1Name;
        this.memory2Name = memory2Name;
        memory1Value = 0;
        memory2Value = 0;
    }

    /**
     * CREATE 1 MEMORY
     *
     * @param memory1Name
     */
    public Memory(String memory1Name) {
        this.memory1Name = memory1Name;
        memory1Value = 0;
        memory2Value = Double.NaN;
    }

    /**
     * GET MEMORY VALUE
     *
     * @param memoryName
     * @return
     */
    public double getMemoryValue (String memoryName) {
        double value = 0;
        if (memoryName.equalsIgnoreCase(memory1Name)) {
            value = memory1Value;
        } else if (memoryName.equalsIgnoreCase(memory2Name)) {
            value = memory2Value;
        }
        return value;
    }

    /**
     * RETURN MEMORY NAME
     *
     * @param value
     * @return
     */
    public String getMemoryName (double value) {
        String memory = null;
        if (memory1Value == value) {
            memory = memory1Name;
        } else if (memory2Value == value) {
            memory = memory2Name;
        }
        return memory;
    }

    public boolean hasMemories() {
         return !memory1Name.isEmpty() || !memory2Name.isEmpty();
    }

    public String getMemory1Name() {
        return memory1Name;
    }

    public String getMemory2Name() {
        return memory2Name;
    }

    public void setMemoryValue(String memoryName, double value) {
        if (memoryName.equalsIgnoreCase(memory1Name)) {
            memory1Value = value;
        } else if(memoryName.equalsIgnoreCase(memory2Name)){
            memory2Value = value;
        }
    }

}
