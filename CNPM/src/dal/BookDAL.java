package dal;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import dto.AuthorDTO;
import dto.BookDTO;
import dto.GenresDTO;

public class BookDAL {
	public boolean isUsed(String isbn) throws Exception {
		String query = "select * from book where isbn = '" + isbn + "'";
		boolean flag = false;

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				if (rs.next()) {
					flag = true;
				}
			}
		}

		return flag;
	}

	public HashMap<String, Integer> getGenresValue() throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		String query = "select * from genres where status = 1";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				while (rs.next()) {
					GenresDTO genres = GenresDAL.setGenres(rs);
					map.put(genres.getName(), genres.getId());
				}

				return map;
			}
		}
	}

	public String getKeyGenresToDisplay(int value) throws Exception {
		String display = "";
		HashMap<String, Integer> genres = getGenresValue();
		for (Map.Entry<String, Integer> add : genres.entrySet()) {
			if (add.getValue() == value) {
				display = add.getKey();
			}
		}
		return display;
	}

	public String getKeyAuthorToDisplay(int value) throws Exception {
		String display = "";
		HashMap<String, Integer> author = getAuthorValue();
		for (Map.Entry<String, Integer> add : author.entrySet()) {
			if (add.getValue() == value) {
				display = add.getKey();
			}
		}
		return display;
	}

	public HashMap<String, Integer> getAuthorValue() throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		String query = "select * from author where status = 1";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				while (rs.next()) {
					AuthorDTO author = AuthorDAL.setAuthor(rs);
					String fullName = author.getLastName() + " " + author.getFirstName();
					map.put(fullName, author.getId());
				}

				return map;
			}
		}
	}

	public boolean insert(BookDTO book) throws Exception {

		String query = "insert into book(isbn,booktitle,authorid,genresid,quantity,publisher,price,datereceived,description,"
				+ "picture,status) values(?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, book.getIsbn());
			ppstm.setString(2, book.getBookTitle());
			ppstm.setInt(3, book.getAuthorId());
			ppstm.setInt(4, book.getGenresId());
			ppstm.setInt(5, book.getQuantity());
			ppstm.setString(6, book.getPublisher());
			ppstm.setInt(7, book.getPrice());
			ppstm.setString(8, book.getDateReceived());
			ppstm.setString(9, book.getDescription());
			ppstm.setInt(11, 1);

			if (book.getPicture() != null) {
				Blob image = new SerialBlob(book.getPicture());
				ppstm.setBlob(10, image);
			} else {
				Blob image = null;
				ppstm.setBlob(10, image);
			}

			return ppstm.executeUpdate() > 0;
		}
	}

	public boolean update(BookDTO book) throws Exception {
		String query = "update book set isbn = ?, booktitle = ?, authorid = ?, genresid = ?, quantity = ?, "
				+ "publisher = ?, price = ?, datereceived = ?, description = ?, picture = ?, status = '1' "
				+ " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, book.getIsbn());
			ppstm.setString(2, book.getBookTitle());
			ppstm.setInt(3, book.getAuthorId());
			ppstm.setInt(4, book.getGenresId());
			ppstm.setInt(5, book.getQuantity());
			ppstm.setString(6, book.getPublisher());
			ppstm.setInt(7, book.getPrice());
			ppstm.setString(8, book.getDateReceived());
			ppstm.setString(9, book.getDescription());

			if (book.getPicture() != null) {
				Blob image = new SerialBlob(book.getPicture());
				ppstm.setBlob(10, image);
			} else {
				Blob image = null;
				ppstm.setBlob(10, image);
			}

			ppstm.setInt(11, book.getId());

			return ppstm.executeUpdate() > 0;
		}
	}

	public BookDTO findById(String id) throws Exception {
		String query = "select * from book where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, id);

			try (ResultSet rs = ppstm.executeQuery()) {

				if (rs.next()) {
					BookDTO s = setBook(rs);
					return s;
				}
			}

			return null;
		}
	}

	public boolean delete(int id) throws Exception {
		String query = "update book set status = '0' " + " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setInt(1, id);

			return ppstm.executeUpdate() > 0;
		}
	}

	public List<BookDTO> displayData(String input) throws Exception {
		String query = (input.equals("")) ? "select * from book where status = 1"
				: "select * from book where status = 1 and booktitle like N'" + input + "%'";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				List<BookDTO> list = new ArrayList<>();
				while (rs.next()) {
					BookDTO book = setBook(rs);
					list.add(book);
				}

				return list;
			}
		}
	}

	private BookDTO setBook(ResultSet rs) throws SQLException {
		BookDTO book = new BookDTO();
		book.setId(rs.getInt("id"));
		book.setIsbn(rs.getString("isbn"));
		book.setBookTitle(rs.getString("booktitle"));
		book.setAuthorId(rs.getInt("authorid"));
		book.setGenresId(rs.getInt("genresid"));
		book.setQuantity(rs.getInt("quantity"));
		book.setPublisher(rs.getString("publisher"));
		book.setPrice(rs.getInt("price"));
		book.setDateReceived(rs.getString("datereceived"));
		book.setDescription(rs.getString("description"));
		Blob blob = rs.getBlob("picture");
		if (blob != null)
			book.setPicture(blob.getBytes(1, (int) blob.length()));

		return book;
	}
}
