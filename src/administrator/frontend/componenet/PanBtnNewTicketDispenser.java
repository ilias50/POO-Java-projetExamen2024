package administrator.frontend.componenet;

import administrator.frontend.componenet.listener.PanBtnNewTicketDispenserListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PanBtnNewTicketDispenser extends JPanel implements ActionListener {

    private final Set<PanBtnNewTicketDispenserListener> listeners = new HashSet<>();
    private JButton btnNewTicketDispenser = new JButton("nouveau distributeur de ticket ");

    private final String actionCommend = "new";

    public PanBtnNewTicketDispenser() {
        initComponent();
    }

    public Set<PanBtnNewTicketDispenserListener> getListeners() {
        return listeners;
    }

    public JButton getBtnNewTicketDispenser() {
        return btnNewTicketDispenser;
    }

    public void setBtnNewTicketDispenser(JButton btnNewTicketDispenser) {
        this.btnNewTicketDispenser = btnNewTicketDispenser;
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

    public void addPanBtnNewTicketDispenserListener(PanBtnNewTicketDispenserListener listener){
        getListeners().add(listener);
    }

    public void removePanBtnNewTicketDispenserListener(PanBtnNewTicketDispenserListener listener){
        getListeners().remove(listener);
    }


    private void fireListener(){
        for (PanBtnNewTicketDispenserListener listener: getListeners()) {
            listener.newTicketDispenser();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), getActionCommend())){
            fireListener();
        }
    }
}
