package bll;

import javax.swing.JOptionPane;

import dal.BookDAL;
import dto.BookDTO;

public class BookBLL {
	BookDAL dao = new BookDAL();

	public boolean addBook(BookDTO dto) throws Exception {
		if (dao.insert(dto)) {
			JOptionPane.showMessageDialog(null, "Book added successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Add failed");
			return false;
		}
	}

	public boolean editBook(BookDTO dto) throws Exception {
		if (dao.update(dto)) {
			JOptionPane.showMessageDialog(null, "Book updated successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}

	public boolean deleteBook(String id) throws Exception {
		if (dao.delete(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(null, "Book deleted successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}
}
