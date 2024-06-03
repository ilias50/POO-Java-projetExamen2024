package customer.frontend.component;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PanDisplayIdCustomer extends JPanel {

    private int idCustomer;
    private JLabel lblId;

    private Set<JPanel> fakePanel = new HashSet<>();

    public PanDisplayIdCustomer(int idCustomer) {
        setIdCustomer(idCustomer);
        setLblId(new JLabel("" + getIdCustomer()));
        initComponent();
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    private void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public JLabel getLblId() {
        return lblId;
    }

    private void setLblId(JLabel lblId) {
        this.lblId = lblId;
    }

    public Set<JPanel> getFakePanel() {
        return fakePanel;
    }

    public void setFakePanel(Set<JPanel> fakePanel) {
        this.fakePanel = fakePanel;
    }

    private void initComponent(){
        setLayout(new GridLayout(3, 3));
        addFakePanel(4);
        add(getLblId());
        getLblId().setHorizontalAlignment(JLabel.CENTER);
        getLblId().setVerticalAlignment(JLabel.CENTER);
        Font labelFont = getLblId().getFont();
        getLblId().setFont(labelFont.deriveFont(labelFont.getSize() * 2.5f));
        addFakePanel(4);

    }

    private void addFakePanel(int numberOfPanel){
        for (int i = 0; i < numberOfPanel; i++) {
            JPanel panel = new JPanel();
            getFakePanel().add(panel);
            add(panel);
        }
    }


    public void changeColor(Color color){
        for (JPanel panel: getFakePanel()) {
            panel.setBackground(color);
        }

    }

    public void changeColorForId(Color color){
        setBackground(color);

    }



}
