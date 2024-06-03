package administrator.frontend.componenet;


import customer.backend.EnumStateCustomer;
import customer.backend.ICustomer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanDisplayCustomerInformation extends JPanel {

    private final Map<Integer, JLabel> mapCustomerAndJLabel = new HashMap<>();

    private final JLabel title = new JLabel("<html><h1><u>" + "Clients" + "</u></h1></html>");

    public PanDisplayCustomerInformation() {
        initComponent();
    }

    public Map<Integer, JLabel> getMapCustomerAndJLabel() {
        return mapCustomerAndJLabel;
    }

    public JLabel getTitle() {
        return title;
    }

    private void initComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        getTitle().setHorizontalAlignment(SwingConstants.CENTER);
        add(getTitle());

    }

    public void addICustomer(ICustomer customer) {
        JLabel label = new JLabel(createSentence(customer));
        label.setBorder(BorderFactory.createLineBorder(Color.black));

        getMapCustomerAndJLabel().put(customer.getId(), label);
        add(label);
        revalidate();
        repaint();
    }

    public void removeICustomer(ICustomer customer) {
        if (!getMapCustomerAndJLabel().containsKey(customer.getId())) return;
        JLabel label = getMapCustomerAndJLabel().remove(customer.getId());
        if (label != null) {
            remove(label);
            revalidate();
            repaint();
        }
    }

    private String createSentence(ICustomer customer) {
        StringBuilder sentence = new StringBuilder("le client " + customer.getId() + ": ");
        switch (customer.getCustomerState()) {
            case SUPPORTED -> sentence.append("est pris en charge par le guichet  ").append(customer.getIdTicketOffice());
            case IS_EXPECTED -> sentence.append("est attendu au guichet ").append(customer.getIdTicketOffice());
            case ON_HOLD -> sentence.append("attend son tour ");
            case MISSED_APPOINTMENT -> sentence.append("a raté son rendez-vous");
            default -> sentence.append("aucune information trouvée");
        }
        return sentence.toString();
    }

    public void updateInformation(ICustomer customer) {
        if (!getMapCustomerAndJLabel().containsKey(customer.getId())) return;
        JLabel label = getMapCustomerAndJLabel().get(customer.getId());
        if (customer.getCustomerState() == EnumStateCustomer.LEFT){
            removeICustomer(customer);
            return;
        }
        if (label != null) {
            label.setText(createSentence(customer));
            revalidate();
            repaint();
        }
    }
}