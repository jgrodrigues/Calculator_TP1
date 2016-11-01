/**
 * Created by jonas on 17/10/2016.
 */
public class Counter {
    private int counter;

    public Counter() {
        counter = 0;
    }

    public void inc() {
        counter++;
    }

    public void dec() {
        counter--;
    }

    public void reset() {
        counter = 0;
    }

    public int status() {
        return counter;
    }
}
