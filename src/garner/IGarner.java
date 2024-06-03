package garner;

import garner.exception.GarnerInvalideNumberException;

public interface IGarner {
    int getNumberOfProduct();
    void addProduct(int x) throws GarnerInvalideNumberException;
    boolean removeProduct(int x) throws GarnerInvalideNumberException;

    public void addGarnerListener(GarnerListener garnerListener);

    public void removeGarnerListener(GarnerListener garnerListener);
}
