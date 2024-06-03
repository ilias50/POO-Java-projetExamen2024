package ticketDispenser.backend;

import garner.IGarner;
import garner.exception.GarnerInvalideNumberException;
import garner.exception.GarnerIsNullException;

public class Producer {

    private IGarner garner;

    public Producer(IGarner garner) {
        setGarner(garner);
    }

    public IGarner getGarner() {
        return garner;
    }

    private void setGarner(IGarner garner) {
        this.garner = garner;
    }

    public void addProduct(int x) throws GarnerInvalideNumberException, GarnerIsNullException {
        if (getGarner() == null) throw new GarnerIsNullException("le grenier ne peut pas etre null");
        getGarner().addProduct(x);

    }
}
