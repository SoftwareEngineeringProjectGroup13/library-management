package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AuthorDTO;

public class AuthorDAL {
	public boolean isUsed(String email) throws Exception {
		String query = "select * from author where email like N'%" + email + "%'";
		boolean flag = false;

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {
				if (rs.next())
					flag = true;
			}
		}

		return flag;
	}

	public boolean insert(AuthorDTO author) throws Exception {
		String query = "insert into author(firstname,lastname,expertise,email,dateofbirth,about,status) values(?,?,?,?,?,?,?)";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, author.getFirstName());
			ppstm.setString(2, author.getLastName());
			ppstm.setString(3, author.getExpertise());
			ppstm.setString(4, author.getEmail());
			ppstm.setString(5, author.getDateOfBirth());
			ppstm.setString(6, author.getAbout());
			ppstm.setInt(7, 1);

			return ppstm.executeUpdate() > 0;
		}
	}

	public boolean update(AuthorDTO author) throws Exception {
		String query = "update author set firstname = ?, lastname = ?, expertise = ?, email = ?, dateofbirth = ?,"
				+ " about = ?, status = '1' " + " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, author.getFirstName());
			ppstm.setString(2, author.getLastName());
			ppstm.setString(3, author.getExpertise());
			ppstm.setString(4, author.getEmail());
			ppstm.setString(5, author.getDateOfBirth());
			ppstm.setString(6, author.getAbout());
			ppstm.setInt(7, author.getId());

			return ppstm.executeUpdate() > 0;
		}
	}

	public boolean delete(int id) throws Exception {
		String query = "update author set status = '0' " + " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setInt(1, id);

			return ppstm.executeUpdate() > 0;
		}
	}

	public List<AuthorDTO> displayData(String input) throws Exception {
		String query = (input.equals("")) ? "select * from author where status = 1"
				: "select * from author where status = 1 and firstname like N'" + input + "%'";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				List<AuthorDTO> list = new ArrayList<>();
				while (rs.next()) {
					AuthorDTO author = setAuthor(rs);
					list.add(author);
				}

				return list;
			}
		}
	}

	public AuthorDTO findById(String id) throws Exception {
		String query = "select * from author where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, id);

			try (ResultSet rs = ppstm.executeQuery()) {

				if (rs.next()) {
					AuthorDTO s = setAuthor(rs);
					return s;
				}
			}

			return null;
		}
	}

	public static AuthorDTO setAuthor(ResultSet rs) throws SQLException {
		AuthorDTO author = new AuthorDTO();
		author.setId(rs.getInt("id"));
		author.setFirstName(rs.getString("firstName"));
		author.setLastName(rs.getString("lastname"));
		author.setExpertise(rs.getString("expertise"));
		author.setEmail(rs.getString("email"));
		author.setDateOfBirth(rs.getString("dateofbirth"));
		author.setAbout(rs.getString("about"));

		return author;
	}
}
