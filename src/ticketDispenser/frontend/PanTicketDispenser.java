package ticketDispenser.frontend;

import garner.IGarner;
import ticketDispenser.backend.EnumStateTicketDispenser;
import ticketDispenser.backend.ITicketDispenser;
import ticketDispenser.backend.TicketDispenser;
import ticketDispenser.backend.TicketDispenserListener;
import ticketDispenser.exception.TicketDispenserException;
import ticketDispenser.numberManager.INumberManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanTicketDispenser extends JPanel implements ITicketDispenser, ActionListener {
    private TicketDispenser ticketDispenser;

    private final JButton btnTicket = new JButton("Prendre un ticket");

    public PanTicketDispenser(INumberManager iNumberManager, IGarner garner) throws TicketDispenserException {
        setTicketDispenser(new TicketDispenser(iNumberManager, garner));
        initComponent();
    }
    public TicketDispenser getTicketDispenser() {
        return ticketDispenser;
    }

    public void setTicketDispenser(TicketDispenser ticketDispenser) {
        this.ticketDispenser = ticketDispenser;
    }

    public JButton getBtnTicket() {
        return btnTicket;
    }

    private void initComponent(){
        setLayout(new GridLayout());
        getBtnTicket().addActionListener(this);
        getBtnTicket().setActionCommand("take");
        add(getBtnTicket());
    }


    @Override
    public void setState(EnumStateTicketDispenser state) {
        getTicketDispenser().setState(state);
    }

    @Override
    public EnumStateTicketDispenser getStateTicketDispenser() {
        return getTicketDispenser().getStateTicketDispenser();
    }

    @Override
    public void takeTicket() {
        getTicketDispenser().takeTicket();

    }

    @Override
    public void addTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getTicketDispenser().addTicketDispenserListener(ticketDispenserListener);
    }

    @Override
    public void removeTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getTicketDispenser().removeTicketDispenserListener(ticketDispenserListener);
    }

    @Override
    public void fireTicketDispenserListenerTicketWasGiven(int idCustomer) {
        getTicketDispenser().fireTicketDispenserListenerTicketWasGiven(idCustomer);
    }

    @Override
    public void fireTicketDispenserListenerStateChanged(EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState) {
        getTicketDispenser().fireTicketDispenserListenerStateChanged(oldState, newState);
    }


    @Override
    public int getId() {
        return getTicketDispenser().getId();
    }

    @Override
    public void close() {
        getTicketDispenser().close();
    }

    @Override
    public void open() {
        getTicketDispenser().open();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("take")) {
            takeTicket();
        }
    }
}
