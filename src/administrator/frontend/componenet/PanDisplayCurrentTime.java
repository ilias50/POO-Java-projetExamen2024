package administrator.frontend.componenet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class PanDisplayCurrentTime extends JPanel implements ActionListener {


    private JLabel lblTime = new JLabel("");

    private Timer timer = new Timer(10, this);

    private String actionCommand = "time";



    public PanDisplayCurrentTime() {
        initComponent();
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }

    public JLabel getLblTime() {
        return lblTime;
    }

    public void setLblTime(JLabel lblTime) {
        this.lblTime = lblTime;
    }

    private Timer getTimer() {
        return timer;
    }

    private void setTimer(Timer timer) {
        this.timer = timer;
    }

    private void initComponent(){
        setLayout(new FlowLayout());
        getTimer().setActionCommand(getActionCommand());
        getTimer().start();
        add(getLblTime());
    }

    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long time = System.currentTimeMillis();
        return simpleDateFormat.format(time);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), getActionCommand())){
            getLblTime().setText(getCurrentTime());
        }
    }
}
