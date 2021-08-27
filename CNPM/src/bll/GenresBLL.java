package bll;

import javax.swing.JOptionPane;

import dal.GenresDAL;
import dto.GenresDTO;

public class GenresBLL {
	GenresDAL dao = new GenresDAL();

	public boolean addGenres(GenresDTO dto) throws Exception {
		if (dao.insert(dto)) {
			JOptionPane.showMessageDialog(null, "Genres added successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Add failed");
			return false;
		}
	}

	public boolean editGenres(GenresDTO dto) throws Exception {
		if (dao.update(dto)) {
			JOptionPane.showMessageDialog(null, "Genres updated successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}

	public boolean deleteGenres(String id) throws Exception {
		if (dao.delete(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(null, "Genres deleted successfully");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid id");
			return false;
		}
	}
}
