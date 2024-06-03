package ticketOffice.frontend.component;

import ticketOffice.frontend.component.listener.PanBtnNextCustomerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PanBtnNextCustomer extends JPanel implements ActionListener {

    private JButton btnNextCustomer = new JButton("client suivant");

    private final Set<PanBtnNextCustomerListener> listeners = new HashSet<>();

    public PanBtnNextCustomer() {
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout());
        getBtnNextCustomer().addActionListener(this);
        getBtnNextCustomer().setActionCommand("next");
        add(getBtnNextCustomer());
    }

    public JButton getBtnNextCustomer() {
        return btnNextCustomer;
    }

    public void setBtnNextCustomer(JButton btnNextCustomer) {
        this.btnNextCustomer = btnNextCustomer;
    }

    public Set<PanBtnNextCustomerListener> getListeners() {
        return listeners;
    }

    public void enableNextCustomer(){
        getBtnNextCustomer().setEnabled(true);
    }

    public void disableNextCustomer(){
        getBtnNextCustomer().setEnabled(false);
    }

    public void addPanBtnNextCustomerListener(PanBtnNextCustomerListener listener){
        getListeners().add(listener);
    }

    public void removePanBtnNextCustomerListener(PanBtnNextCustomerListener listener){
        getListeners().remove(listener);
    }

    private void fireListener(){
        for (PanBtnNextCustomerListener listener: getListeners()) {
            listener.btnNextCustomer();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "next")) fireListener();
    }
}
