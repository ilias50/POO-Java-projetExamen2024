package ticketOffice.backend;

import garner.IGarner;
import garner.exception.GarnerInvalideNumberException;
import garner.exception.GarnerIsNullException;

public class Consumer {

    private IGarner garner;

    public Consumer(IGarner garner) {
        setGarner(garner);
    }

    public IGarner getGarner() {
        return garner;
    }

    public void setGarner(IGarner garner) {
        this.garner = garner;
    }

    public boolean removeProduct(int x) throws GarnerIsNullException {
        if (getGarner() == null) throw new GarnerIsNullException("Le grenier ne peut pas etre null");

        try {
            return getGarner().removeProduct(x);
        } catch (GarnerInvalideNumberException e) {
            return false;
        }
    }
}
