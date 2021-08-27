package bll;


import java.util.Vector;

import dal.NHANVIEN_DAL;
import dto.NHANVIEN_DTO;
public class NHANVIEN_BLL {
    NHANVIEN_DAL nvDAL= new NHANVIEN_DAL();
    public Vector<NHANVIEN_DTO> getAllNhanvien() {
        return nvDAL.getAllNhanviens();
    }
    public String addNHANVIEN (NHANVIEN_DTO nv) {
        if (nvDAL.hasNHANVIEN(nv.getMaNV()))
           return "Mã NV đã tồn tại";
        if (nvDAL.addNHANVIENS(nv))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    public String deleteNHANVIEN (String MaNV) {
        if(nvDAL.deleteNHANVIEN(MaNV))
            return "Xóa thành công";
            return "Xóa thất bại";
    }
    public String updateNHANVIEN (NHANVIEN_DTO nv) {
        if (nvDAL.updateNHANVIEN(nv))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
}

