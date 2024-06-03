package customer.frontend;

import customer.backend.Customer;
import customer.backend.CustomerListener;
import customer.backend.EnumStateCustomer;
import customer.backend.ICustomer;
import customer.frontend.component.PanBtnCustomer;
import customer.frontend.component.PanBtnCustomerListener;
import customer.frontend.component.PanDisplayIdCustomer;
import customer.frontend.component.PanDisplayTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PanCustomer extends JPanel implements ICustomer, CustomerListener, PanBtnCustomerListener, ActionListener {

    private final Customer customer;

    private final PanBtnCustomer panBtnCustomer = new PanBtnCustomer();
    private final PanDisplayTimer panDisplayTimer = new PanDisplayTimer();

    private final PanDisplayIdCustomer panDisplayIdCustomer;


    private final Timer timer = new Timer(10, this);


    private long timePass;

    private final Color colorON_HOLD = Color.WHITE;
    private final Color colorSUPPORTED = Color.GREEN;

    private final Color colorIS_EXPECTED = Color.ORANGE;

    private final Color colorMISSED_APPOINTMENT = Color.red;

    public PanCustomer(int idCustomer) {
        this.customer = new Customer(idCustomer);
        getCustomer().addCustomerListener(this);
        panDisplayIdCustomer = new PanDisplayIdCustomer(getId());
        initComponent();
    }

    private void initComponent(){
        setLayout(new BorderLayout());
        getPanBtnCustomer().addPanBtnCustomerListener(this);
        add(getPanBtnCustomer(), BorderLayout.SOUTH);
        getPanDisplayIdCustomer().setAlignmentX(CENTER_ALIGNMENT);
        getPanDisplayIdCustomer().setAlignmentY(CENTER_ALIGNMENT);
        add(getPanDisplayIdCustomer(), BorderLayout.CENTER);
        add(getPanDisplayTimer(), BorderLayout.NORTH);
        startTimer();

    }


    public long getTimePass() {
        return timePass;
    }

    public void setTimePass(long timePass) {
        this.timePass = timePass;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PanBtnCustomer getPanBtnCustomer() {
        return panBtnCustomer;
    }

    public PanDisplayTimer getPanDisplayTimer() {
        return panDisplayTimer;
    }

    public PanDisplayIdCustomer getPanDisplayIdCustomer() {
        return panDisplayIdCustomer;
    }

    public Color getColorON_HOLD() {
        return colorON_HOLD;
    }

    public Color getColorSUPPORTED() {
        return colorSUPPORTED;
    }

    public Color getColorIS_EXPECTED() {
        return colorIS_EXPECTED;
    }

    public Color getColorMISSED_APPOINTMENT() {
        return colorMISSED_APPOINTMENT;
    }

    public Timer getTimer() {
        return timer;
    }

    private void startTimer(){
        getTimer().start();
        getTimer().setActionCommand("time");
    }
    private void changeColor(Color color){
        getPanDisplayIdCustomer().changeColor(color);
        getPanDisplayTimer().changeColor(color);
    }




    @Override
    public void stateCustomerChanged(EnumStateCustomer oldSate, EnumStateCustomer newState, ICustomer customer) {
        switch (newState) {
            case ON_HOLD -> {
                changeColor(getColorON_HOLD());
                getPanBtnCustomer().disableBtnGoTicketOffice();
            }
            case IS_EXPECTED -> {
                changeColor(getColorIS_EXPECTED());
                getPanBtnCustomer().enableBtnGoTicketOffice();
            }
            case SUPPORTED -> {
                changeColor(getColorSUPPORTED());
                getPanBtnCustomer().disableBtnGoTicketOffice();
            }
            case MISSED_APPOINTMENT -> {
                changeColor(getColorMISSED_APPOINTMENT());
                getPanBtnCustomer().disableBtnGoTicketOffice();
            }
        }
    }



    @Override
    public void call(int idTicketOffice) {
        customer.call(idTicketOffice);
    }

    @Override
    public void goToTheTicketOffice() {
        getCustomer().goToTheTicketOffice();

    }

    @Override
    public void leave() {
        getCustomer().leave();

    }

    @Override
    public void fireCustomerListenerStateChanged(EnumStateCustomer oldState, EnumStateCustomer newState) {
        getCustomer().fireCustomerListenerStateChanged(oldState, newState);
    }


    @Override
    public void addCustomerListener(CustomerListener customerListener) {
        getCustomer().addCustomerListener(customerListener);
    }

    @Override
    public void removeCustomerListener(CustomerListener customerListener) {
        getCustomer().removeCustomerListener(customerListener);
    }

    @Override
    public EnumStateCustomer getCustomerState() {
        return getCustomer().getCustomerState();
    }

    @Override
    public void setState(EnumStateCustomer state) {
        getCustomer().setState(state);
    }


    @Override
    public int getId() {
        return getCustomer().getId();
    }

    @Override
    public long getArrivingTime() {
        return getCustomer().getArrivingTime();
    }

    @Override
    public long getPickUpTime() {
        return getCustomer().getPickUpTime();
    }

    @Override
    public int getIdTicketOffice() {
        return getCustomer().getIdTicketOffice();
    }

    @Override
    public void btnLeave() {
        leave();

    }

    @Override
    public void btnGoTicketOffice() {
        goToTheTicketOffice();
    }


    @Override
    public String toString() {
        return getCustomer().toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(timer.getActionCommand(), "time")){
            setTimePass((System.currentTimeMillis() - getArrivingTime()) - 3600000 );
            getPanDisplayTimer().setLblTime(getTimePass());
        }
    }
}
