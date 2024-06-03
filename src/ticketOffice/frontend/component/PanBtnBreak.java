package ticketOffice.frontend.component;

import ticketOffice.frontend.component.listener.PanBtnBreakListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PanBtnBreak extends JPanel implements ActionListener {

    private final JCheckBox checkBox = new JCheckBox("en pause");
    private final Set<PanBtnBreakListener> listeners = new HashSet<>();



    public PanBtnBreak() {
        initComponent();
    }

    private void initComponent(){
        setLayout(new GridLayout());
        getCheckBox().addActionListener(this);
        getCheckBox().setActionCommand("break");
        add(getCheckBox());

    }


    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public Set<PanBtnBreakListener> getListeners() {
        return listeners;
    }

    public void addPanBtnBreakListener(PanBtnBreakListener listener){
        getListeners().add(listener);
    }

    public void removePanBtnBreakListener(PanBtnBreakListener listener){
        getListeners().remove(listener);
    }

    private void fireListener(boolean isOnBreak){
        for (PanBtnBreakListener listener: getListeners()) {
            listener.btnOnBreak(isOnBreak);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "break")){
               fireListener(getCheckBox().isSelected());
        }
    }

    public void changeColor(Color color){
        getCheckBox().setBackground(color);
    }
}
