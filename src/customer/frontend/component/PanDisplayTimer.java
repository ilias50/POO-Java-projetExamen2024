package customer.frontend.component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PanDisplayTimer extends JPanel {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private final JLabel lblTime = new JLabel("00:00:00");

    private final JPanel panLbl = new JPanel(new GridLayout(1, 1));

    private Color color = Color.WHITE;



    public PanDisplayTimer() {
        initComponent();
    }

    private void initComponent(){
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        getPanLbl().add(getLblTime());
        getPanLbl().setBackground(getColor());
        add(getPanLbl());

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        getPanLbl().setBackground(getColor());
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public JLabel getLblTime() {
        return lblTime;
    }

    public JPanel getPanLbl() {
        return panLbl;
    }

    public void setLblTime(long time) {
        lblTime.setText(simpleDateFormat.format(time));
    }

    public void changeColor(Color color){
        setBackground(color);
    }


}
