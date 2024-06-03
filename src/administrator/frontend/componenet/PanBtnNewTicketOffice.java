package administrator.frontend.componenet;

import administrator.frontend.componenet.listener.PanBtnNewTicketOfficeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PanBtnNewTicketOffice extends JPanel implements ActionListener {
    private final Set<PanBtnNewTicketOfficeListener> listeners = new HashSet<>();
    private JButton btnNewTicketOffice = new JButton("nouveau guichet");

    private final String actionCommend = "new";

    public PanBtnNewTicketOffice() {
        initComponent();
    }

    public Set<PanBtnNewTicketOfficeListener> getListeners() {
        return listeners;
    }

    public JButton getBtnNewTicketDispenser() {
        return btnNewTicketOffice;
    }

    public void setBtnNewTicketDispenser(JButton btnNewTicketDispenser) {
        this.btnNewTicketOffice = btnNewTicketDispenser;
    }

    public String getActionCommend() {
        return actionCommend;
    }

    private void initComponent(){
        setLayout(new GridLayout());

        getBtnNewTicketDispenser().addActionListener(this);
        getBtnNewTicketDispenser().setActionCommand(getActionCommend());
        add(getBtnNewTicketDispenser());
    }

    public void addPanBtnNewTicketDispenserListener(PanBtnNewTicketOfficeListener listener){
        getListeners().add(listener);
    }

    public void removePanBtnNewTicketDispenserListener(PanBtnNewTicketOfficeListener listener){
        getListeners().remove(listener);
    }


    private void fireListener(){
        for (PanBtnNewTicketOfficeListener listener: getListeners()) {
            listener.newTicketOffice();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), getActionCommend())){
            fireListener();
        }
    }
}
