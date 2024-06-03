package ticketDispenser.numberManager;

public class NumberManager implements INumberManager {

    static int lastNumber = 1;
    private int number;

    public NumberManager() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNextNumber() {
        setNumber(lastNumber++);
        return getNumber();
    }
}
