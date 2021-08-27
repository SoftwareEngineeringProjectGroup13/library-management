package bll;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class NoticeMissingInformation {
	public JLabel getNotice(String notice) {
		JLabel lb = new JLabel("* enter the " + notice);
		return lb;
	}

	public boolean isFilled(String input, JLabel lb) {
		boolean flag = false;
		if (input.isEmpty()) {
			lb.setVisible(true);
			flag = true;
		} else {
			lb.setVisible(false);
			flag = false;
		}
		return flag;
	}

	public boolean isSelectedImg(byte[] img) {
		boolean flag = false;
		if (img == null) {
			JOptionPane.showMessageDialog(null, "Request to choose an image");
			flag = true;
		}
		return flag;
	}

	public boolean isSelectedCalendar(JDateChooser dateChooser, JLabel lbNote) {
		boolean flag = false;
		if (dateChooser.getCalendar() == null) {
			lbNote.setVisible(true);
			flag = true;
		} else {
			lbNote.setVisible(false);
			flag = false;
		}
		return flag;
	}

	public boolean isNumber(String input, JLabel lb) {
		boolean flag = false;
		if (!input.matches("-?\\d+(\\.\\d+)?")) {
			lb.setVisible(true);
			flag = true;
		} else {
			lb.setVisible(false);
			flag = false;
		}
		return flag;
	}

	public boolean isId(String input) {
		return !input.matches("-?\\d+(\\.\\d+)?") ? true : false;
	}
}
