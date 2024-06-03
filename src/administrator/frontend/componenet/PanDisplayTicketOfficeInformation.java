package administrator.frontend.componenet;

import ticketOffice.backend.EnumStateTicketOffice;
import ticketOffice.backend.ITicketOffice;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanDisplayTicketOfficeInformation extends JPanel {

    private final Map<Integer, JLabel> mapITicketOfficeAndJLabel = new HashMap<>();
    private final JLabel title = new JLabel("<html><h1><u>" + "Guichets" + "</u></h1></html>");

    public PanDisplayTicketOfficeInformation() {
        initComponent();
    }

    public Map<Integer, JLabel> getMapITicketOfficeAndJLabel() {
        return mapITicketOfficeAndJLabel;
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

    public void addITicketOffice(ITicketOffice iTicketOffice) {
        JLabel label = new JLabel(createSentence(iTicketOffice));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        getMapITicketOfficeAndJLabel().put(iTicketOffice.getId(), label);
        add(label);
        revalidate();
        repaint();
    }

    public void removeITicketOffice(ITicketOffice iTicketOffice) {
        if (!getMapITicketOfficeAndJLabel().containsKey(iTicketOffice.getId())) return;
        JLabel label = getMapITicketOfficeAndJLabel().remove(iTicketOffice.getId());
        if (label != null) {
            remove(label);
            revalidate();
            repaint();
        }
    }

    private String createSentence(ITicketOffice ticketOffice) {
        StringBuilder sentence = new StringBuilder("guichet " + ticketOffice.getId() + ": ");
        switch (ticketOffice.getStateTicketOffice()) {
            case MANAGE_CLIENT -> sentence.append("gère le client ").append(ticketOffice.getTheExpectedCustomer());
            case ON_BREAK -> sentence.append("est en pause");
            case ON_HOLD -> sentence.append("attend le client ").append(ticketOffice.getTheExpectedCustomer());
            case DO_NOTHING -> sentence.append("ne fais rien");
            default -> sentence.append("aucune information trouvée");
        }
        return sentence.toString();
    }

    public void updateInformation(ITicketOffice ticketOffice) {
        if (!getMapITicketOfficeAndJLabel().containsKey(ticketOffice.getId())) return;
        if (ticketOffice.getStateTicketOffice() == EnumStateTicketOffice.CLOSE){
            removeITicketOffice(ticketOffice);
            return;
        }
        JLabel label = getMapITicketOfficeAndJLabel().get(ticketOffice.getId());
        if (label != null) {
            label.setText(createSentence(ticketOffice));
            revalidate();
            repaint();
        }
    }
}

