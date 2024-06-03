package ticketOffice.frontend;

import garner.IGarner;
import ticketOffice.backend.*;
import ticketOffice.frontend.component.*;
import ticketOffice.frontend.component.listener.PanBtnBreakListener;
import ticketOffice.frontend.component.listener.PanBtnCallBackListener;
import ticketOffice.frontend.component.listener.PanBtnNextCustomerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PanTicketOffice extends JPanel implements ITicketOffice, TicketOfficeListener, PanBtnNextCustomerListener, PanBtnBreakListener, PanBtnCallBackListener, ActionListener {

    private TicketOffice ticketOffice;
    private final PanLabelName panLabelName;

    private final PanLabelSate panLabelSate = new PanLabelSate();

    private final PanBtnBreak panBtnBreak = new PanBtnBreak();

    private final PanBtnNextCustomer panBtnNextCustomer = new PanBtnNextCustomer();

    private final PanBtnCallBack panBtnCallBack = new PanBtnCallBack();

    private final JPanel panBtn = new JPanel(new GridLayout(2,0));

    private final Timer timer = new Timer(0, this);

    private final String actionCommandTimer = "timer";

    private String sentenceDO_NOTHING = "ne fais rien";
    private String sentenceON_HOLD = "attend le client ";

    private String sentenceON_BREAK = "en pause";

    private String sentenceMANAGE_CLIENT = "gere le client ";


    private long timeBetweenCallBack = 60000;


    public PanTicketOffice(ITicketOfficeAdministrator iTicketOfficeAdministrator, IGarner garner) {
        setTicketOffice(new TicketOffice(iTicketOfficeAdministrator, garner));
        this.panLabelName = new PanLabelName(getId());
        addTicketOfficeListener(this);
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout(2, 2));
        add(getPanLabelName());
        add(getPanLabelSate());
        getPanBtnNextCustomer().addPanBtnNextCustomerListener(this);

        getPanBtn().add(getPanBtnNextCustomer());
        getPanBtnCallBack().addPanBtnCallBackListener(this);
        getPanBtn().add(getPanBtnCallBack());
        add(getPanBtn());
        getPanBtnBreak().addPanBtnBreakListener(this);
        add(getPanBtnBreak());
        doNothing();

    }

    private void changeColor(Color color){
        getPanLabelSate().setBackground(color);
        getPanLabelName().setBackground(color);
        getPanBtnBreak().changeColor(color);
    }

    public JPanel getPanBtn() {
        return panBtn;
    }

    public long getTimeBetweenCallBack() {
        return timeBetweenCallBack;
    }

    public void setTimeBetweenCallBack(long timeBetweenCallBack) {
        if (timeBetweenCallBack < 0) this.timeBetweenCallBack = 0;
        this.timeBetweenCallBack = timeBetweenCallBack;
    }

    public String getSentenceDO_NOTHING() {
        return sentenceDO_NOTHING;
    }

    public void setSentenceDO_NOTHING(String sentenceDO_NOTHING) {
        this.sentenceDO_NOTHING = sentenceDO_NOTHING;
    }

    public String getSentenceON_HOLD() {
        return sentenceON_HOLD;
    }

    public void setSentenceON_HOLD(String sentenceON_HOLD) {
        this.sentenceON_HOLD = sentenceON_HOLD;
    }

    public String getSentenceON_BREAK() {
        return sentenceON_BREAK;
    }

    public void setSentenceON_BREAK(String sentenceON_BREAK) {
        this.sentenceON_BREAK = sentenceON_BREAK;
    }

    public String getSentenceMANAGE_CLIENT() {
        return sentenceMANAGE_CLIENT;
    }

    public void setSentenceMANAGE_CLIENT(String sentenceMANAGE_CLIENT) {
        this.sentenceMANAGE_CLIENT = sentenceMANAGE_CLIENT;
    }

    private void changeLblState(String text){
        panLabelSate.setLblText(text);
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }

    public void setTicketOffice(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public PanLabelName getPanLabelName() {
        return panLabelName;
    }

    public PanLabelSate getPanLabelSate() {
        return panLabelSate;
    }

    public PanBtnBreak getPanBtnBreak() {
        return panBtnBreak;
    }

    public PanBtnNextCustomer getPanBtnNextCustomer() {
        return panBtnNextCustomer;
    }

    public PanBtnCallBack getPanBtnCallBack() {
        return panBtnCallBack;
    }

    public Timer getTimer() {
        return timer;
    }

    public String getActionCommandTimer() {
        return actionCommandTimer;
    }

    @Override
    public void callNextCustomer() {
        getTicketOffice().callNextCustomer();

    }

    @Override
    public void addTicketOfficeListener(TicketOfficeListener ticketOfficeListener) {
        getTicketOffice().addTicketOfficeListener(ticketOfficeListener);
    }

    @Override
    public void removeTicketOfficeListener(TicketOfficeListener ticketOfficeListener) {
        getTicketOffice().removeTicketOfficeListener(ticketOfficeListener);
    }

    @Override
    public void callBackCustomer() {
        getTicketOffice().callBackCustomer();
    }

    @Override
    public void startBreak() {
        getTicketOffice().startBreak();
    }

    @Override
    public void endOfBreak() {
        getTicketOffice().endOfBreak();
    }

    @Override
    public void fireTicketOfficeListener(EnumStateTicketOffice oldState, EnumStateTicketOffice newState) {
        getTicketOffice().fireTicketOfficeListener(oldState, newState);
    }

    @Override
    public void supportsTheCustomer() {
        getTicketOffice().supportsTheCustomer();
    }

    @Override
    public int getId() {
        return getTicketOffice().getId();
    }

    @Override
    public int getTheExpectedCustomer() {
        return getTicketOffice().getTheExpectedCustomer();
    }

    @Override
    public EnumStateTicketOffice getStateTicketOffice() {
        return getTicketOffice().getStateTicketOffice();
    }

    @Override
    public void doNothing() {
        getTicketOffice().doNothing();
    }

    @Override
    public int getNumberForDo_Nothing() {
        return getTicketOffice().getNumberForDo_Nothing();
    }

    @Override
    public long getTimeSinceOnHold() {
        return getTicketOffice().getTimeSinceOnHold();
    }

    @Override
    public int getNumberOfCallBack() {
        return getTicketOffice().getNumberOfCallBack();
    }

    @Override
    public long getTimeSinceLastCallBack() {
        return getTicketOffice().getTimeSinceLastCallBack();
    }

    @Override
    public void close() {
        getTicketOffice().close();
    }


    @Override
    public void StateTicketOfficeChanged(EnumStateTicketOffice oldState, EnumStateTicketOffice newState, ITicketOffice ticketOffice) {
        switch (newState){
            case DO_NOTHING -> {
                changeLblState(getSentenceDO_NOTHING());
                getPanBtnNextCustomer().enableNextCustomer();
                getPanBtnCallBack().disableCallBack();
                changeColor(Color.WHITE);
            }
            case ON_HOLD -> {
                changeLblState(getSentenceON_HOLD());
                getPanBtnNextCustomer().enableNextCustomer();
                getPanBtnCallBack().enableCallBack();
                startTimer();
                changeColor(Color.orange);
            }
            case ON_BREAK -> {
                changeLblState(getSentenceON_BREAK());
                getPanBtnNextCustomer().disableNextCustomer();
                getPanBtnCallBack().disableCallBack();
                changeColor(Color.GRAY);
            }
            case MANAGE_CLIENT -> {
                changeLblState(getSentenceMANAGE_CLIENT());
                getPanBtnNextCustomer().enableNextCustomer();
                getPanBtnCallBack().disableCallBack();
                changeColor(Color.GREEN);

            }
        }
    }

    private void startTimer(){
        if (getTimer().isRunning()){
            getTimer().restart();
            getTimer().setActionCommand(getActionCommandTimer());
        }else {
            getTimer().setActionCommand(getActionCommandTimer());
            getTimer().start();
        }

    }

    @Override
    public void btnOnBreak(boolean isOnBreak) {
        if (isOnBreak){
            startBreak();
        }else {
            endOfBreak();
        }
    }

    @Override
    public void btnNextCustomer() {
        callNextCustomer();
    }

    @Override
    public void btnCallBack() {
        callBackCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), getActionCommandTimer())){
            long time;
            if (getTimeSinceLastCallBack() == 0){
                time = System.currentTimeMillis() - getTimeSinceOnHold();
            }else{
                time = System.currentTimeMillis() - getTimeSinceLastCallBack();
            }

            if (time >= getTimeBetweenCallBack()){
                callBackCustomer();
            }
        }
    }
}
