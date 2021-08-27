package dto;

public class NHANVIEN_DTO {
	private String MaNV;
	private String HoTenNV;
	private int NamSinh;
	private String GioiTinh;
	private String DiaChi;
	private String DienThoai;
	private int TrangThai;

	public NHANVIEN_DTO() {

	}

	public NHANVIEN_DTO(String MaNV, String HoTenNV, int NamSinh, String GioiTinh, String DiaChi, String DienThoai,
			int TrangThai) {
		this.MaNV = MaNV;
		this.HoTenNV = HoTenNV;
		this.NamSinh = NamSinh;
		this.GioiTinh = GioiTinh;
		this.DiaChi = DiaChi;
		this.DienThoai = DienThoai;
		this.TrangThai = TrangThai;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String MaNV) {
		this.MaNV = MaNV;
	}

	public String getHoTenNV() {
		return HoTenNV;
	}

	public void setHoTenNV(String HoTenNV) {
		this.HoTenNV = HoTenNV;
	}

	public int getNamSinh() {
		return NamSinh;
	}

	public void setNamSinh(int NamSinh) {
		this.NamSinh = NamSinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String GioiTinh) {
		this.GioiTinh = GioiTinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String DiaChi) {
		this.DiaChi = DiaChi;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String DienThoai) {
		this.DienThoai = DienThoai;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int TrangThai) {
		this.TrangThai = TrangThai;
	}

}
