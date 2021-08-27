package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dto.KHACHHANG_DTO;

public class KHACHHANG_DAL {
	private Connection con;

	public boolean openConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Library";
			String username = "sa";
			String password = "sa";
			con = DriverManager.getConnection(dbURL, username, password);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public void closeConnection() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public Vector<KHACHHANG_DTO> getAllKhachhangs() {
		Vector<KHACHHANG_DTO> arr = new Vector<KHACHHANG_DTO>();
		if (openConnection()) {
			try {
				String sql = "SELECT * from KHACHHANG where TrangThai='1'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					KHACHHANG_DTO kh = new KHACHHANG_DTO();
					kh.setMaKH(rs.getString("MaKH"));
					kh.setHoTenKH(rs.getString("HoTenKH"));
					kh.setNamSinh(rs.getInt("NamSinh"));
					kh.setGioiTinh(rs.getString("GioiTinh"));
					kh.setDiaChi(rs.getString("DiaChi"));
					kh.setDienThoai(rs.getString("DienThoai"));
					arr.add(kh);
				}
			} catch (SQLException ex) {
				System.out.println(ex);
			} finally {
				closeConnection();
			}
		}
		return arr;
	}

	// Thêm
	public boolean addKHACHHANGS(KHACHHANG_DTO kh) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "Insert into KHACHHANG values (?,?,?,?,?,?,1)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setNString(1, kh.getMaKH());
				stmt.setNString(2, kh.getHoTenKH());
				stmt.setInt(3, kh.getNamSinh());
				stmt.setNString(4, kh.getGioiTinh());
				stmt.setNString(5, kh.getDiaChi());
				stmt.setNString(6, kh.getDienThoai());
				if (stmt.executeUpdate() >= 1) {
					result = true;
				}
			} catch (SQLException ex) {
				System.out.println(ex);
			} finally {
				closeConnection();
			}
		}
		return result;
	}

	// Xóa
	public boolean deleteKHACHHANG(String MaKH) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "UPDATE KHACHHANG SET TrangThai ='0' WHERE MaKH='" + MaKH + "'";
				Statement stmt = con.createStatement();
				int rs = stmt.executeUpdate(sql);
				if (rs >= 1) {
					result = true;
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				closeConnection();
			}
		}
		return result;
	}

	// Sửa
	public boolean updateKHACHHANG(KHACHHANG_DTO kh) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "UPDATE KHACHHANG SET HoTenKH= ? , NamSinh=? , GioiTinh =? , DiaChi =? ,DienThoai= ? where MaKH='"
						+ kh.getMaKH() + "'";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setNString(1, kh.getHoTenKH());
				stmt.setInt(2, kh.getNamSinh());
				stmt.setNString(3, kh.getGioiTinh());
				stmt.setNString(4, kh.getDiaChi());
				stmt.setNString(5, kh.getDienThoai());
				if (stmt.executeUpdate() >= 1) {
					result = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				closeConnection();
			}
		}
		return result;

	}

	// Tìm kiếm
	public boolean searcKHACHHANG(String MaNV) {
		boolean result = false;

		return result;
	}

	public boolean hasKHACHHANG(String MaKH) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "Select *from KHACHHANG where MaKH=" + MaKH;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				result = rs.next();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				closeConnection();
			}
		}
		return result;
	}

}
