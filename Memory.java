/**
 * Created by jonas on 29/10/2016.
 */
public class Memory {
    private double memory1;
    private double memory2;
    private String memory1Name;
    private String memory2Name;


    public Memory() {
        memory1 = 0;
        memory2 = 0;
        memory1Name = "primeira";
        memory2Name = "segunda";
    }

    public Memory(int memory1, int memory2) {
        this.memory1 = memory1;
        this.memory2 = memory2;
    }

    public Memory(String memory1Name, String memory2Name) {
        this.memory1Name = memory1Name;
        this.memory2Name = memory2Name;
    }

    public double getMemoryValue(String memoryName) {
        if (memoryName == memory1Name) {
            return memory1;
        } else if (memoryName == memory2Name) {
            return memory2;
        } else {
            return 0;
        }
    }

    public String getMemoryName(int memoryValue) {
        if (memoryValue == memory1) {
            return memory1Name;
        } else if (memoryValue == memory2) {
            return memory2Name;
        } else {
            return "Nao existe";
        }
    }
}
