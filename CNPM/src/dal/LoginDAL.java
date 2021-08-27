package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAL {
	public boolean checkLogin(String un, String pw) throws Exception {
		String query = "select * from account where username = ? and password = ?";
		boolean flag = false;
		try (Connection con = ConnectionDB.getConnection(); PreparedStatement ppstm = con.prepareStatement(query);) {

			ppstm.setString(1, un);
			ppstm.setString(2, pw);
			try (ResultSet rs = ppstm.executeQuery()) {
				if (rs.next()) {
					flag = true;
				}
			}
		}
		return flag;
	}
}
