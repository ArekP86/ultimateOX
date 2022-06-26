package UX;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;

public class History extends JPanel {
    private final ArrayList<Object[]> moves = new ArrayList<>();
    private final String[] columnNames = {"Move", "Player", "Location"};
    Object[][] data = {{"Round Starts"}};
    private final JTable table = new JTable(new DefaultTableModel(data, columnNames));
    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    private int currentTurn = 1;

    public History() {
        clear();

        JPanel tablePanel = new JPanel();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tablePanel.add(new JScrollPane(table));
        add(tablePanel);
    }

    public void clear() {
        moves.clear();
        currentTurn = 1;
        Object[] o = {"Round Starts", "", ""};
        moves.add(o);

        data = new Object[moves.size()][];
        data = moves.toArray(data);

        ((DefaultTableModel) table.getModel()).setDataVector(data, columnNames);
        Collections.list(table.getColumnModel().getColumns()).forEach(c -> {
            c.setCellRenderer(cellRenderer);
        });
    }

    public void addEntry(Game.Turn turn, Game.Field boardField, Game.Field field) {
        Object[] o = {String.valueOf(currentTurn++), turn.toString(), boardField.toString() + " : " + field.toString()};
        moves.add(o);

        data = new Object[moves.size()][];
        data = moves.toArray(data);

        ((DefaultTableModel) table.getModel()).setDataVector(data, columnNames);
        Collections.list(table.getColumnModel().getColumns()).forEach(c -> {
            c.setCellRenderer(cellRenderer);
        });
    }
}
