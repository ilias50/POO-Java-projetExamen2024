package garner;

import garner.exception.GarnerInvalideNumberException;

import java.util.HashSet;
import java.util.Set;

public class Garner implements IGarner {
    private int numberOfProduct = 0;

    private final Set<GarnerListener> listeners = new HashSet<>();
    @Override
    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public Set<GarnerListener> getListeners() {
        return listeners;
    }

    private synchronized void setNumberOfProduct(int numberOfProduct) throws GarnerInvalideNumberException {
        if (numberOfProduct < 0) throw new GarnerInvalideNumberException("la valeur du grenier ne peut pas etre négatif");
        this.numberOfProduct = numberOfProduct;
        fireListener();
    }
    @Override
    public void addProduct(int x) throws GarnerInvalideNumberException {
        setNumberOfProduct(getNumberOfProduct() + x);
    }
    @Override
    public boolean removeProduct(int x) throws GarnerInvalideNumberException {
        if (getNumberOfProduct()-x < 0) return false;

        setNumberOfProduct(getNumberOfProduct()-x);
        return true;
    }


    @Override
    public void addGarnerListener(GarnerListener garnerListener){
        getListeners().add(garnerListener);
    }
    @Override
    public void removeGarnerListener(GarnerListener garnerListener){
        getListeners().remove(garnerListener);
    }


    private void fireListener(){
        for (GarnerListener listener: getListeners()) {
            listener.numberOfProductChange(getNumberOfProduct());
        }
    }

    @Override
    public String toString() {
        return "Garner{" +
                "numberOfProduct=" + numberOfProduct +
                '}';
    }
}
