create database do_an_javaXYZ
go
use do_an_javaXYZ
create table phong_benh(
	id_benh_nhan  nvarchar(15) FOREIGN KEY REFERENCES thong_tin_bnN(id_benh_nhan),
	tenphongbenh nvarchar(15),
	sogiuongbenh nvarchar(15) ,
	loaigiuong nvarchar(15),
	ngay_nhap_vien date,
	ngay_xuat_vien date,
)
go
create table thong_tin_bnN(
	id_benh_nhan nvarchar(15)  primary key,
	ho_ten nvarchar(50),
	gioi_tinh bit,
	dia_chi nvarchar(200),
	so_cmnd nvarchar(15),
	so_dien_thoai nvarchar(15),
	ngay_sinh date,
	di_ung_thuoc bit,
	
)
go
create table xet_nghiem(
	tien nvarchar(25),
	id_ten_loai_xet_nghiem nvarchar(25),
	ket_qua nvarchar(100),
	ngay_xet_nghiem date,
	BHYT bit,
	id_benh_nhan  nvarchar(15) FOREIGN KEY REFERENCES thong_tin_bnN(id_benh_nhan)
)
go
create TABLE BENH_AN(
	id_benh_an  nvarchar(15) primary key,
	chieu_cao nvarchar(5),
	can_nang nvarchar(5),
	huyet_ap nvarchar(5),
	tieu_su nvarchar(200),
	nhom_mau nvarchar(5),
	id_benh_nhan  nvarchar(15) FOREIGN KEY REFERENCES thong_tin_bnN(id_benh_nhan)

)
go
create TABLE Login (
tk nvarchar(15),
pass nvarchar(15)
)
go
create proc GetAllTK
as
BEGIN
	Select * from Login  ;
END
go
create proc GetAllphong_benh 
as
BEGIN
	Select * from phong_benh  ;
END
go
CREATE PROCEDURE GetAllTTBNN
AS
BEGIN
    SELECT * FROM thong_tin_bnN
    ORDER BY id_benh_nhan ASC;
END;


go
Create proc GetAllBenhAn
as
BEGIN
	Select * from BENH_AN 
	ORDER BY id_benh_nhan ASC;
END
go
create proc GetAllxetnghiem
as
BEGIN
	Select * from xet_nghiem 
	 ORDER BY id_benh_nhan ASC;
END
go

Create proc GetAll
as
BEGIN
SELECT c.id_benh_an, ho_ten,gioi_tinh,dia_chi,ngay_sinh,so_cmnd,so_dien_thoai,
 ho_ten,gioi_tinh,dia_chi,ngay_sinh,so_cmnd,so_dien_thoai,
di_ung_thuoc,can_nang,chieu_cao,huyet_ap,nhom_mau,id_ten_loai_xet_nghiem,ket_qua,tien
FROM thong_tin_bnN a
JOIN xet_nghiem b ON a.id_benh_nhan = b.id_benh_nhan
JOIN BENH_AN c ON a.id_benh_nhan = c.id_benh_nhan 
ORDER BY  a.id_benh_nhan ASC;
END
GO

Create proc GetAllTTBN
as
BEGIN
SELECT [id_benh_nhan]
FROM thong_tin_bnN 

END
GO
/*DROP PROCEDURE  GetAllTTBN;*/
