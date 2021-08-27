package bll;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

public class CustomizeJTable {
	public void customizeTable(JTable table) {

		table.setSelectionBackground(Color.lightGray);
		table.setRowHeight(30);
		table.setSelectionForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));

		table.getTableHeader().setBackground(new Color(13, 211, 255));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
	}

	public void setColumnWidth(JTable table, int columnIndex, int width) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(columnIndex).setPreferredWidth(width);
	}
}
