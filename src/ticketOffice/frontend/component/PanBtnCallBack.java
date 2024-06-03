package ticketOffice.frontend.component;

import ticketOffice.frontend.component.listener.PanBtnCallBackListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PanBtnCallBack extends JPanel implements ActionListener {

    private JButton btnCallBack = new JButton("rapeller le client");

    private final Set<PanBtnCallBackListener> listeners = new HashSet<>();

    public PanBtnCallBack() {
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout());
        getBtnCallBack().addActionListener(this);
        getBtnCallBack().setActionCommand("call");
        add(getBtnCallBack());
    }

    public Set<PanBtnCallBackListener> getListeners() {
        return listeners;
    }

    public JButton getBtnCallBack() {
        return btnCallBack;
    }

    public void setBtnCallBack(JButton btnCallBack) {
        this.btnCallBack = btnCallBack;
    }



    public void addPanBtnCallBackListener(PanBtnCallBackListener panBtnCallBackListener){
        getListeners().add(panBtnCallBackListener);
    }

    public void removePanBtnCallBackListener(PanBtnCallBackListener panBtnCallBackListener){
        getListeners().remove(panBtnCallBackListener);
    }

    private void firePanBtnCallBackListener(){
        for (PanBtnCallBackListener listener: getListeners()) {
            listener.btnCallBack();
        }
    }

    public void enableCallBack(){
        getBtnCallBack().setEnabled(true);
    }

    public void disableCallBack(){
        getBtnCallBack().setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "call")) firePanBtnCallBackListener();
    }
}