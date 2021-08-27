package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dto.NHANVIEN_DTO;

public class NHANVIEN_DAL {
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

	public Vector<NHANVIEN_DTO> getAllNhanviens() {
		Vector<NHANVIEN_DTO> arr = new Vector<NHANVIEN_DTO>();
		if (openConnection()) {
			try {
				String sql = "SELECT * from NHANVIEN where TrangThai='1'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NHANVIEN_DTO nv = new NHANVIEN_DTO();
					nv.setMaNV(rs.getString("MaNV"));
					nv.setHoTenNV(rs.getString("HoTenNV"));
					nv.setNamSinh(rs.getInt("NamSinh"));
					nv.setGioiTinh(rs.getString("GioiTinh"));
					nv.setDiaChi(rs.getString("DiaChi"));
					nv.setDienThoai(rs.getString("DienThoai"));
					arr.add(nv);
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
	public boolean addNHANVIENS(NHANVIEN_DTO nv) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "Insert into NHANVIEN values (?,?,?,?,?,?,1)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setNString(1, nv.getMaNV());
				stmt.setNString(2, nv.getHoTenNV());
				stmt.setInt(3, nv.getNamSinh());
				stmt.setNString(4, nv.getGioiTinh());
				stmt.setNString(5, nv.getDiaChi());
				stmt.setNString(6, nv.getDienThoai());
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
	public boolean deleteNHANVIEN(String MaNV) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "UPDATE NHANVIEN SET TrangThai ='0' WHERE MaNV='" + MaNV + "'";
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
	public boolean updateNHANVIEN(NHANVIEN_DTO nv) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "UPDATE NHANVIEN SET HoTenNV= ? , NamSinh=? , GioiTinh =? , DiaChi =? ,DienThoai= ? where MaNV='"
						+ nv.getMaNV() + "'";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setNString(1, nv.getHoTenNV());
				stmt.setInt(2, nv.getNamSinh());
				stmt.setNString(3, nv.getGioiTinh());
				stmt.setNString(4, nv.getDiaChi());
				stmt.setNString(5, nv.getDienThoai());
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
	public boolean searchNHANVIEN(String MaNV) {
		boolean result = false;

		return result;
	}

	public boolean hasNHANVIEN(String MaNV) {
		boolean result = false;
		if (openConnection()) {
			try {
				String sql = "Select *from NHANVIEN where MaNV=" + MaNV;
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
