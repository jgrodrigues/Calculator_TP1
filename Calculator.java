/**
 * Created by jonas on 29/10/2016.
 */
public class Calculator {

    //Variaveis
    private double memory1;
    private double memory2;
    private String memory1Name;
    private String memory2Name;
    private boolean hasMemory = false;
    private int memoryNumber;

    //Constructor
    public Calculator(String memory1Name, String memory2Name) {
        if (memory1Name.equals(memory2Name)) {
            this.memory1Name = memory1Name;
            memory1 = 0;
            hasMemory = true;
            memoryNumber = 1;

        }else {
            this.memory1Name = memory1Name;
            this.memory2Name = memory2Name;
            memory1 = 0;
            memory2 = 0;
            hasMemory = true;
            memoryNumber = 2;
        }
    }

    public Calculator (String memory1Name) {
        this.memory1Name = memory1Name;
        memory1 = 0;
        memoryNumber = 2;
        hasMemory = true;
    }


    public Calculator () {
        memoryNumber = 0;
    }

    public boolean getHasMemory() {
        return hasMemory;
    }

    public int getMemoryNumber() {
        return memoryNumber;
    }


    /**
     * RETURN VALUE OF NAMED MEMORY
     *
     * @param memoryName
     * @return
     */
    public double getMemoryValue(String memoryName) {
        double value;
        if (memoryName.equals(memory1Name)) {
            value = memory1;
        } else if(memoryName.equals(memory2Name)) {
            value = memory2;
        } else {
            value = 0;
        }
        return value;
    }

    public boolean memoryExists(String memoryName) {
        return (memoryName.equals(memory1Name)  || memoryName.equals(memory2Name));
    }

    public String[] getMemoryNames() {
        String[] arr;
        for(int i = 0; i < 2;i++) {
            arr = new String[]{memory1Name, memory2Name};
            return arr;
        }
        arr = new String[]{memory1Name, memory2Name};
        return arr;

    }

//    public double simpleExpression () {
//
//    }

//    public double complexExpression() {
//
//    }

}
