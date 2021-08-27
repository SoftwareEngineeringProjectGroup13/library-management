package bll;

import java.util.Vector;

import dal.KHACHHANG_DAL;
import dto.KHACHHANG_DTO;

public class KHACHHANG_BLL {
	KHACHHANG_DAL nvDAL = new KHACHHANG_DAL();

	public Vector<KHACHHANG_DTO> getAllKhachhang() {
		return nvDAL.getAllKhachhangs();
	}

	public String addKHACHHANG(KHACHHANG_DTO kh) {
		if (nvDAL.hasKHACHHANG(kh.getMaKH()))
			return "Mã KH đã tồn tại";
		if (nvDAL.addKHACHHANGS(kh))
			return "Thêm thành công";
		return "Thêm thất bại";
	}

	public String deleteKHACHHANG(String MaKH) {
		if (nvDAL.deleteKHACHHANG(MaKH))
			return "Xóa thành công";
		return "Xóa thất bại";
	}

	public String updateKHACHHANG(KHACHHANG_DTO kh) {
		if (nvDAL.hasKHACHHANG(kh.getMaKH()))
			return "Mã KH đã tồn tại";
		if (nvDAL.updateKHACHHANG(kh))
			return "Sửa thành công";
		return "Sửa thất bại";
	}
}
