package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.GenresDTO;

public class GenresDAL {
	public boolean loadDataByName(String name) throws Exception {
		String query = "select * from genres where genresname like N'%" + name + "%'";
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

	public boolean insert(GenresDTO genres) throws Exception {
		String query = "insert into genres(genresname,status) values(?,?)";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, genres.getName());
			ppstm.setInt(2, 1);

			return ppstm.executeUpdate() > 0;
		}
	}

	public boolean update(GenresDTO genres) throws Exception {
		String query = "update genres set genresname = ?, status = '1' " + " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setInt(2, genres.getId());
			ppstm.setString(1, genres.getName());

			return ppstm.executeUpdate() > 0;
		}
	}

	public boolean delete(int id) throws Exception {
		String query = "update genres set status = '0' " + " where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setInt(1, id);

			return ppstm.executeUpdate() > 0;
		}
	}

	public List<GenresDTO> displayData(String input) throws Exception {
		String query = (input.equals("")) ? "select * from genres where status = 1"
				: "select * from genres where status = 1 and genresname like N'" + input + "%'";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery()) {

				List<GenresDTO> list = new ArrayList<>();
				while (rs.next()) {
					GenresDTO genres = setGenres(rs);
					list.add(genres);
				}

				return list;
			}
		}
	}

	public GenresDTO findById(String id) throws Exception {
		String query = "select * from genres where id = ?";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, id);

			try (ResultSet rs = ppstm.executeQuery()) {

				if (rs.next()) {
					GenresDTO s = setGenres(rs);
					return s;
				}
			}

			return null;
		}
	}

	public static GenresDTO setGenres(ResultSet rs) throws SQLException {
		GenresDTO genres = new GenresDTO();
		genres.setId(rs.getInt("id"));
		genres.setName(rs.getString("genresname"));

		return genres;
	}
}
