package ticketDispenser.frontend;


import garner.IGarner;
import ticketDispenser.backend.EnumStateTicketDispenser;
import ticketDispenser.backend.ITicketDispenser;
import ticketDispenser.backend.TicketDispenserListener;
import ticketDispenser.exception.TicketDispenserException;
import ticketDispenser.numberManager.INumberManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTicketDispenser extends JFrame implements ITicketDispenser, TicketDispenserListener {

    private PanTicketDispenser panTicketDispenser;

    private final JPanel panCont = (JPanel) getContentPane();

    static int x = 200;

    static int y = 500;

    static int xInit = 0;

    static int yInit = 100;

    static int deviationPercentage = 10;

    public WindowTicketDispenser(INumberManager iNumberManager, IGarner garner) throws TicketDispenserException {
        setPanTicketDispenser(new PanTicketDispenser(iNumberManager, garner));
        initComponent();
        setVisible(true);
    }

    public PanTicketDispenser getPanTicketDispenser() {
        return panTicketDispenser;
    }

    public void setPanTicketDispenser(PanTicketDispenser panTicketDispenser) {
        this.panTicketDispenser = panTicketDispenser;
    }

    public JPanel getPanCont() {
        return panCont;
    }

    private void initComponent(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(x,y,280,150);
        addWindowListener(createListener());
        y=newY();
        setTitle("Distributeur " + getId());
        getPanCont().setLayout(new GridLayout());
        getPanCont().add(getPanTicketDispenser());
    }

    private WindowListener createListener(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close();
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

    @Override
    public void setState(EnumStateTicketDispenser state) {
        getPanTicketDispenser().setState(state);
    }

    @Override
    public EnumStateTicketDispenser getStateTicketDispenser() {
        return getPanTicketDispenser().getStateTicketDispenser();
    }

    @Override
    public void takeTicket() {
        getPanTicketDispenser().takeTicket();
    }

    @Override
    public void addTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getPanTicketDispenser().addTicketDispenserListener(ticketDispenserListener);
    }

    @Override
    public void removeTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getPanTicketDispenser().removeTicketDispenserListener(ticketDispenserListener);
    }

    @Override
    public void fireTicketDispenserListenerTicketWasGiven(int idCustomer) {
        getPanTicketDispenser().fireTicketDispenserListenerTicketWasGiven(idCustomer);
    }

    @Override
    public void fireTicketDispenserListenerStateChanged(EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState) {

    }


    @Override
    public int getId() {
        return getPanTicketDispenser().getId();
    }

    @Override
    public void close() {
        getPanTicketDispenser().close();
    }

    @Override
    public void open() {
        getPanTicketDispenser().open();
    }


    @Override
    public void ticketWasGiven(int idCustomer, ITicketDispenser ticketDispenser) {

    }

    @Override
    public void StateTicketDispenserChanged(ITicketDispenser ticketDispenser, EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState) {
        if (newState == EnumStateTicketDispenser.CLOSE){
            dispose();
        }
    }


}
