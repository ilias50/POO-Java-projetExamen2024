package customer.frontend.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class PanBtnCustomer extends JPanel implements ActionListener {
    private JButton btnLeave = new JButton("partir");
    private JButton btnGoTicketOffice = new JButton("aller au guichet");

    private final Set<PanBtnCustomerListener> listeners = new HashSet<>();

    public PanBtnCustomer() {
        initComponent();
    }

    public JButton getBtnLeave() {
        return btnLeave;
    }

    public void setBtnLeave(JButton btnLeave) {
        this.btnLeave = btnLeave;
    }

    public JButton getBtnGoTicketOffice() {
        return btnGoTicketOffice;
    }

    public void setBtnGoTicketOffice(JButton btnGoTicketOffice) {
        this.btnGoTicketOffice = btnGoTicketOffice;
    }

    public Set<PanBtnCustomerListener> getListeners() {
        return listeners;
    }

    private void initComponent(){
        setLayout(new GridLayout(0, 2));

        getBtnLeave().addActionListener(this);
        getBtnLeave().setActionCommand("leave");
        add(getBtnLeave());

        getBtnGoTicketOffice().addActionListener(this);
        getBtnGoTicketOffice().setActionCommand("go");
        disableBtnGoTicketOffice();
        add(getBtnGoTicketOffice());
    }

    public void enableBtnGoTicketOffice(){
        getBtnGoTicketOffice().setEnabled(true);
    }

    public void disableBtnGoTicketOffice(){
        getBtnGoTicketOffice().setEnabled(false);
    }

    public void enableBtnLeave(){
        getBtnLeave().setEnabled(true);
    }

    public void disableBtnLeave(){
        getBtnLeave().setEnabled(false);
    }

    public void addPanBtnCustomerListener(PanBtnCustomerListener listener){
        getListeners().add(listener);
    }

    public void removePanBtnCustomerListener(PanBtnCustomerListener listener){
        getListeners().remove(listener);
    }

    private void fireListenerBtnLeave(){
        for (PanBtnCustomerListener listener: getListeners()) {
            listener.btnLeave();
        }
    }

    private void fireListenerBtnGo(){
        for (PanBtnCustomerListener listener: getListeners()) {
            listener.btnGoTicketOffice();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "leave" -> fireListenerBtnLeave();
            case "go" -> fireListenerBtnGo();
        }

    }
}
