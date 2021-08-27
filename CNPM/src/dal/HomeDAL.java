package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeDAL {
	public int getQuantity(String table) throws Exception {
		int quantity = 0;
		String query = "select count(*) as quantity from " + table + " where status = 1";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery();) {
				if (rs.next()) {
					quantity = rs.getInt("quantity");
				}
			}
		}
		return quantity;
	}

	public int getMember(String table) throws Exception {
		int quantity = 0;
		String query = "select count(*) as quantity from " + table + " where trangthai = 1";

		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			try (ResultSet rs = ppstm.executeQuery();) {
				if (rs.next()) {
					quantity = rs.getInt("quantity");
				}
			}
		}
		return quantity;
	}
}
