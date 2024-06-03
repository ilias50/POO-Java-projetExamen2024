package ticketOffice.frontend;

import garner.IGarner;
import ticketOffice.backend.EnumStateTicketOffice;
import ticketOffice.backend.ITicketOffice;
import ticketOffice.backend.ITicketOfficeAdministrator;
import ticketOffice.backend.TicketOfficeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTicketOffice extends JFrame implements ITicketOffice, TicketOfficeListener {

    private final JPanel panCont = (JPanel) getContentPane();
    private final PanTicketOffice panTicketOffice;

    static int x = 900;

    static int y = 500;

    static int xInit = 0;

    static int yInit = 100;

    static int deviationPercentage = 10;

    public WindowTicketOffice(ITicketOfficeAdministrator iTicketOfficeAdministrator, IGarner garner) {
        this.panTicketOffice = new PanTicketOffice(iTicketOfficeAdministrator, garner);
        addTicketOfficeListener(this);
        initComponent();
        setVisible(true);
    }

    public JPanel getPanCont() {
        return panCont;
    }

    public PanTicketOffice getPanTicketOffice() {
        return panTicketOffice;
    }

    private void initComponent(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Guichet " + getId());
        setBounds(x,y,280,150);
        addWindowListener(createListener());
        y = newY();
        getPanCont().setLayout(new GridLayout());
        getPanCont().add(getPanTicketOffice());


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
    public void callNextCustomer() {
        getPanTicketOffice().callNextCustomer();
    }

    @Override
    public void addTicketOfficeListener(TicketOfficeListener ticketOfficeListener) {
        getPanTicketOffice().addTicketOfficeListener(ticketOfficeListener);
    }

    @Override
    public void removeTicketOfficeListener(TicketOfficeListener ticketOfficeListener) {
        getPanTicketOffice().removeTicketOfficeListener(ticketOfficeListener);
    }

    @Override
    public void callBackCustomer() {
        getPanTicketOffice().callBackCustomer();

    }

    @Override
    public void startBreak() {
        getPanTicketOffice().startBreak();
    }

    @Override
    public void endOfBreak() {
        getPanTicketOffice().endOfBreak();
    }

    @Override
    public void fireTicketOfficeListener(EnumStateTicketOffice oldState, EnumStateTicketOffice newState) {
        getPanTicketOffice().fireTicketOfficeListener(oldState, newState);
    }

    @Override
    public void supportsTheCustomer() {
        getPanTicketOffice().supportsTheCustomer();
    }

    @Override
    public int getId() {
        return getPanTicketOffice().getId();
    }

    @Override
    public int getTheExpectedCustomer() {
        return getPanTicketOffice().getTheExpectedCustomer();
    }

    @Override
    public void StateTicketOfficeChanged(EnumStateTicketOffice oldState, EnumStateTicketOffice newState, ITicketOffice ticketOffice) {
        if (newState == EnumStateTicketOffice.CLOSE){
            dispose();
        }
    }

    @Override
    public EnumStateTicketOffice getStateTicketOffice(){
        return getPanTicketOffice().getStateTicketOffice();
    }

    @Override
    public void doNothing() {
        getPanTicketOffice().doNothing();
    }

    @Override
    public int getNumberForDo_Nothing() {
        return getPanTicketOffice().getNumberForDo_Nothing();
    }

    @Override
    public long getTimeSinceOnHold() {
        return getPanTicketOffice().getTimeSinceOnHold();
    }

    @Override
    public int getNumberOfCallBack() {
        return getPanTicketOffice().getNumberOfCallBack();
    }

    @Override
    public long getTimeSinceLastCallBack() {
        return getPanTicketOffice().getTimeSinceLastCallBack();
    }

    @Override
    public void close() {
        getPanTicketOffice().close();
    }

}
