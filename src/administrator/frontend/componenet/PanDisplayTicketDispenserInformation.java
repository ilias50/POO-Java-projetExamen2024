package administrator.frontend.componenet;

import ticketDispenser.backend.ITicketDispenser;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanDisplayTicketDispenserInformation extends JPanel {

    private final Map<Integer, JLabel> mapITicketDispenserAndJLabel = new HashMap<>();
    private final JLabel title = new JLabel("<html><h1><u>" + "Distributeurs" + "</u></h1></html>");

    public PanDisplayTicketDispenserInformation() {
        initComponent();
    }

    public Map<Integer, JLabel> getMapITicketDispenserAndJLabel() {
        return mapITicketDispenserAndJLabel;
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

    public void addITicketDispenser(ITicketDispenser ticketDispenser) {
        JLabel label = new JLabel("distributeur de ticket " + ticketDispenser.getId());
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        getMapITicketDispenserAndJLabel().put(ticketDispenser.getId(), label);
        add(label);
        revalidate();
        repaint();
    }

    public void removeITicketDispenser(ITicketDispenser ticketDispenser) {
        if (!getMapITicketDispenserAndJLabel().containsKey(ticketDispenser.getId())) return;
        JLabel label = getMapITicketDispenserAndJLabel().remove(ticketDispenser.getId());
        if (label != null) {
            remove(label);
            revalidate();
            repaint();
        }
    }
}
