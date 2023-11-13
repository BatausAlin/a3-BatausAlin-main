package GUI.TOOLS;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Color;

// Clasă internă care extinde DefaultTableCellRenderer
public class CustomRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verificați dacă valoarea este null sau goale (sau orice altă condiție pe care doriți să o verificați)
        if (value == null || value.toString().trim().isEmpty()) {
            c.setBackground(Color.GRAY); // Setează culoarea de fundal pentru celulele goale
        } else {
            c.setBackground(Color.GRAY); // Setează culoarea de fundal pentru celulele ne-goale
        }

        if (isSelected) {
            c.setBackground(Color.GRAY);
            c.setForeground(Color.GRAY); // culoarea textului pentru rândul selectat
        } else {
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }

        return c;
    }
}
