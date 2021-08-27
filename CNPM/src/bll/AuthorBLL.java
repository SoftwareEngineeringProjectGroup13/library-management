package bll;

import javax.swing.JOptionPane;

import dal.AuthorDAL;
import dto.AuthorDTO;

public class AuthorBLL {
	AuthorDAL dao = new AuthorDAL();

	public boolean addAuthor(AuthorDTO dto) throws Exception {
		if (dao.insert(dto)) {
			JOptionPane.showMessageDialog(null, "Author added successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Add failed");
			return false;
		}
	}

	public boolean editAuthor(AuthorDTO dto) throws Exception {
		if (dao.update(dto)) {
			JOptionPane.showMessageDialog(null, "Author updated successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}

	public boolean deleteAuthor(String id) throws Exception {
		if (dao.delete(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(null, "Author deleted successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}
}
