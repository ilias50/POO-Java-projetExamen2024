package ticketOffice.frontend.component;

import javax.swing.*;
import java.awt.*;

public class PanLabelName extends JPanel {
    private int idTicketOffice;
    private JLabel lblId;

    private JPanel panLbl = new JPanel(new GridLayout(1, 1));

    public PanLabelName(int idTicketOffice) {
        setIdTicketOffice(idTicketOffice);
        setLblId(new JLabel("guichet " + getIdTicketOffice()));
        initComponent();
    }

    public int getIdTicketOffice() {
        return idTicketOffice;
    }

    private void setIdTicketOffice(int idTicketOffice) {
        this.idTicketOffice = idTicketOffice;
    }

    public JLabel getLblId() {
        return lblId;
    }

    private void setLblId(JLabel lblId) {
        this.lblId = lblId;
    }

    public JPanel getPanLbl() {
        return panLbl;
    }

    private void setPanLbl(JPanel panLbl) {
        this.panLbl = panLbl;
    }

    private void initComponent(){
        setLayout(new FlowLayout());
        getPanLbl().add(getLblId());
        getPanLbl().setBackground(Color.WHITE);
        add(getPanLbl());


    }


    public void changeColor(Color color){
        setBackground(color);
    }

    public void changeColorLabel(Color color){
        getPanLbl().setBackground(color);
    }

}
