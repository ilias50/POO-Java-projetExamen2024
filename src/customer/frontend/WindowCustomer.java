package customer.frontend;

import customer.backend.CustomerListener;
import customer.backend.EnumStateCustomer;
import customer.backend.ICustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowCustomer extends JFrame implements ICustomer, CustomerListener {

    private final PanCustomer panCustomer;

    private final JPanel panCont = (JPanel) getContentPane();

    static int x = 500;

    static int y = 100;

    static int xInit = 0;

    static int yInit = 100;

    static int deviationPercentage = 10;

    public WindowCustomer(int idCustomer) {
        this.panCustomer = new PanCustomer(idCustomer);
        initComponent();
        setVisible(true);
    }

    private void initComponent(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(createListener());
        setBounds(x,y,300,200);
        y = newY();
        setTitle("Client " + getId());
        getPanCustomer().addCustomerListener(this);
        getPanCont().add(getPanCustomer());
    }

    private WindowListener createListener(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                leave();
            }
        };
    }

    private int newX(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if (dimension.getWidth() - getWidth() <= x){
            return xInit;
        }
        return (int) (getX() + (dimension.getWidth()/100*deviationPercentage));
    }

    private int newY(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if (dimension.getHeight()- getHeight() <= y ){
            x = newX();
            return yInit;
        }
        return (int) (getY() + (dimension.getHeight()/100*deviationPercentage));

    }

    public PanCustomer getPanCustomer() {
        return panCustomer;
    }

    public JPanel getPanCont() {
        return panCont;
    }

    @Override
    public void call(int idTicketOffice) {
        getPanCustomer().call(idTicketOffice);
        requestFocus();
    }

    @Override
    public void goToTheTicketOffice() {
        getPanCustomer().goToTheTicketOffice();

    }

    @Override
    public void leave() {
        getPanCustomer().leave();


    }

    @Override
    public void fireCustomerListenerStateChanged(EnumStateCustomer oldState, EnumStateCustomer newState) {
        getPanCustomer().fireCustomerListenerStateChanged(oldState, newState);
    }

    @Override
    public void addCustomerListener(CustomerListener customerListener) {
        getPanCustomer().addCustomerListener(customerListener);
    }

    @Override
    public void removeCustomerListener(CustomerListener customerListener) {
        getPanCustomer().removeCustomerListener(customerListener);
    }

    @Override
    public EnumStateCustomer getCustomerState() {
        return getPanCustomer().getCustomerState();
    }

    @Override
    public void setState(EnumStateCustomer state) {
        getPanCustomer().setState(state);
    }


    @Override
    public int getId() {
        return getPanCustomer().getId();
    }

    @Override
    public long getArrivingTime() {
        return getPanCustomer().getArrivingTime();
    }

    @Override
    public long getPickUpTime() {
        return getPanCustomer().getPickUpTime();
    }

    @Override
    public int getIdTicketOffice() {
        return getPanCustomer().getIdTicketOffice();
    }

    @Override
    public void stateCustomerChanged(EnumStateCustomer oldSate, EnumStateCustomer newState, ICustomer customer) {
        switch (newState){
            case LEFT -> dispose();
        }
    }


    @Override
    public String toString() {
        return getPanCustomer().toString();
    }
}
