package ticketOffice.frontend.component;

import javax.swing.*;
import java.awt.*;

public class PanLabelSate extends JPanel{

    private JPanel panLbl = new JPanel(new GridLayout(1, 1));

    private JLabel lblSentence = new JLabel();

    public PanLabelSate() {
        initComponent();
    }

    public JLabel getLblSentence() {
        return lblSentence;
    }

    public void setLblSentence(JLabel lblSentence) {
        this.lblSentence = lblSentence;
    }

    public JPanel getPanLbl() {
        return panLbl;
    }

    private void setPanLbl(JPanel panLbl) {
        this.panLbl = panLbl;
    }

    private void initComponent(){
        setLayout(new FlowLayout());
        getPanLbl().add(getLblSentence());
        getPanLbl().setBackground(Color.WHITE);
        add(getPanLbl());


    }


    public void changeColor(Color color){
        setBackground(color);
    }

    public void changeColorLabel(Color color){
        getPanLbl().setBackground(color);
    }

    public void setLblText(String text){
        getLblSentence().setText(text);
    }
}
