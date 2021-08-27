package dto;

public class KHACHHANG_DTO {
	private String MaKH;
	private String HoTenKH;
	private int NamSinh;
	private String GioiTinh;
	private String DiaChi;
	private String DienThoai;
	private int TrangThai;

	public KHACHHANG_DTO() {

	}

	public KHACHHANG_DTO(String MaKH, String HoTenKH, int NamSinh, String GioiTinh, String DiaChi, String DienThoai,
			int TrangThai) {
		this.MaKH = MaKH;
		this.HoTenKH = HoTenKH;
		this.NamSinh = NamSinh;
		this.GioiTinh = GioiTinh;
		this.DiaChi = DiaChi;
		this.DienThoai = DienThoai;
		this.TrangThai = TrangThai;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String MaKH) {
		this.MaKH = MaKH;
	}

	public String getHoTenKH() {
		return HoTenKH;
	}

	public void setHoTenKH(String HoTenKH) {
		this.HoTenKH = HoTenKH;
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