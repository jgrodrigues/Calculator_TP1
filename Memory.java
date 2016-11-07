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

    public double getMemoryValue() {
        return memoryValue;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setValue(double memoryValue) {
        this.memoryValue = memoryValue;
    }

    /**
     * GET MEMORY VALUE
     *
     * @param memoryName
     * @return
//     */
//    public double getMemoryValue (String memoryName) {
//        double value = 0;
//        if (memoryName.equalsIgnoreCase(memory1Name)) {
//            value = memory1Value;
//        } else if (memoryName.equalsIgnoreCase(memory2Name)) {
//            value = memory2Value;
//        } else {
//            value = Double.NaN;
//        }
//        return value;
//    }
//
//    /**
//     * RETURN MEMORY NAME
//     *
//     * @param value
//     * @return
//     */
//    public String getMemoryName (double value) {
//        String memory = null;
//        if (memory1Value == value) {
//            memory = memory1Name;
//        } else if (memory2Value == value) {
//            memory = memory2Name;
//        }
//        return memory;
//    }
//
//    public boolean hasMemories() {
//         return !memory1Name.isEmpty() || !memory2Name.isEmpty();
//    }
//
//    public String getMemory1Name() {
//        return memory1Name;
//    }
//
//    public String getMemory2Name() {
//        return memory2Name;
//    }
//
//    public void setMemoryValue(String memoryName, double value) {
//        if (memoryName.equalsIgnoreCase(memory1Name)) {
//            memory1Value = value;
//        } else if(memoryName.equalsIgnoreCase(memory2Name)){
//            memory2Value = value;
//        }
//    }

}
