	USE master
GO
CREATE DATABASE QuanLyMuaBanLinhKien
GO
USE QuanLyMuaBanLinhKien
GO
CREATE TABLE LinhKien(
	MaLinhKien INT PRIMARY KEY IDENTITY,
	TenLinhKien NVARCHAR(50) NOT NULL,
	NgaySanXuat DATETIME NOT NULL,
	HangSanXuat NVARCHAR(50) NOT NULL,
	SoLuong INT CHECK(SoLuong >= 0) DEFAULT 1,
	DonGia MONEY CHECK(DonGia >= 0) DEFAULT 1000
)
GO
CREATE TABLE KhachHang(
	MaKhachHang INT PRIMARY KEY IDENTITY,
	TenKhachHang NVARCHAR(50),
	SoDienThoai NVARCHAR(10) ,
	DiaChi NVARCHAR(100),
)
CREATE TABLE NhanVien(
	MaNhanVien INT PRIMARY KEY IDENTITY,
	TenNhanVien NVARCHAR(50) NOT NULL,
	TenDangNhap NVARCHAR(50) NOT NULL,
	MatKhau NVARCHAR(50) NOT NULL,
	VaiTro BIT DEFAULT 1
)
GO
CREATE TABLE DonHang(
	MaDon INT PRIMARY KEY IDENTITY,
	MaKhachHang INT FOREIGN KEY REFERENCES KhachHang(MaKhachHang),
	NgayMua DATETIME NOT NULL DEFAULT GETDATE(),
	MaNhanVien INT FOREIGN KEY REFERENCES NhanVien(MaNhanVien),
	GiamGia INT DEFAULT 0,
	ThanhTien MONEY CHECK(ThanhTien >= 0) DEFAULT 1000,
)
GO
CREATE TABLE ChiTietDonHang(
	MaChiTietDon INT PRIMARY KEY IDENTITY,
	MaDon INT FOREIGN KEY REFERENCES DonHang(MaDon),
	MaLinhKien INT FOREIGN KEY REFERENCES LinhKien(MaLinhKien),
	SoLuong INT CHECK(SoLuong >0) DEFAULT 1
)
GO
INSERT INTO NhanVien(TenNhanVien, TenDangNhap, MatKhau, VaiTro)
VALUES 
	('admin','admin','1',0),
	('Nguyễn Hồng Đức','Duc','11111111',1),
	('La Minh Tâm','Tam','11111111',1),
	('Nguyễn Thiên Tứ','Tu','11111111',1),
	('Văn Quí','Qui','11111111',1)
GO
INSERT INTO KhachHang(TenKhachHang, SoDienThoai, DiaChi)
VALUES 
		('Nguyễn Văn A','1111111111','126A NVB'),
		('Nguyễn Văn B','1111111111','126A NVB'),
		('Nguyễn Văn C','1111111111','126A NVB'),
		('Nguyễn Văn D','1111111111','126A NVB'),
		('Nguyễn Văn E','1111111111','126A NVB'),
		('Nguyễn Văn F','1111111111','126A NVB')
GO
INSERT INTO LinhKien(TenLinhKien, NgaySanXuat, HangSanXuat, SoLuong, DonGia)
VALUES
	('RAM','2006-1-1','Corsair',100,500000),
	('CHIP','2002-12-5','Intel',50,100000),
	('MainBoard','2007-1-1','Asus',100,200000),
	('VGA','2005-5-5','NVIDIA',40,100000),
	('Case','2007-10-10','Corsair',25,150000),
	('Keyboard','2006-3-4','Logitech',50,3000000),
	('SSD','2006-10-25','Samsung',50,150000)
GO
INSERT INTO DonHang(MaKhachHang, NgayMua, MaNhanVien, GiamGia, ThanhTien)
VALUES 
	(1,'2007-12-3','3',0,1000000),
	(2,'2007-12-3','3',0,2000000),
	(3,'2007-12-3','3',0,300000),
	(4,'2007-12-3','3',0,4050000),
	(5,'2007-12-3','3',0,500000),
	(6,'2007-12-3','3',0,6000000)
GO
INSERT INTO ChiTietDonHang (MaDon, MaLinhKien, SoLuong)
VALUES
	(1,1,2),
	(2,2,6),
	(2,3,16),
	(3,4,1),
	(3,2,3),
	(4,1,1),
	(4,2,1),
	(4,3,1),
	(4,4,1),
	(4,5,1),
	(4,6,1),
	(5,7,2),
	(5,3,1),
	(6,1,2)
GO
CREATE VIEW HoaDonView AS 
SELECT MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien 
FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
GO
CREATE PROC GetChiTietHoaDon @MaDon INT
AS
	SELECT MaChiTietDon, MaDon, TenLinhKien, CTD.SoLuong, DonGia FROM ChiTietDonHang CTD JOIN LinhKien LK ON CTD.MaLinhKien = LK.MaLinhKien
	WHERE MaDon = @Madon
GO
CREATE PROC GetHoaDonTheoNgay @TuNgay DATE, @DenNgay DATE
AS
	SELECT MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien 
	FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
	WHERE NgayMua BETWEEN @TuNgay AND @DenNgay
GO
CREATE PROC TaoDonHangMoi
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM DonHang WHERE ThanhTien = 0)
		INSERT INTO DonHang(MaKhachHang,NgayMua,MaNhanVien,GiamGia,ThanhTien)
		VALUES (null,GETDATE(),null,0,0)
END
GO
CREATE TRIGGER TaoKhachHangMoi ON DonHang
FOR INSERT
AS
	INSERT INTO KhachHang(TenKhachHang, SoDienThoai, DiaChi)
	VALUES (null, null, null)
GO
CREATE TRIGGER ThemKhachHangVaoDonHang ON KhachHang
FOR INSERT
AS
	DECLARE @MaKhachHang INT
	SELECT @MaKhachHang = MaKhachHang FROM KhachHang
	UPDATE DonHang SET MaKhachHang = @MaKhachHang
	WHERE ThanhTien = 0
GO
CREATE PROC GetDonHangMoi
AS
	SELECT MaDon FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang
	WHERE ThanhTien = 0
GO
CREATE PROC UpdateDateDonHang
AS
	UPDATE DonHang SET NgayMua = GETDATE() WHERE ThanhTien = 0
GO
ALTER PROC ThanhToanHoaDon @MaDon INT, @GiamGia INT, @ThanhTien MONEY, @TenKhachHang NVARCHAR(50), @SDT NVARCHAR(10), @DiaChi NVARCHAR(100)
AS
	DEClARE @MaKhachHang NVARCHAR(50)
	SELECT @MaKhachHang = MaKhachHang FROM DonHang WHERE MaDon = @MaDon

	UPDATE KhachHang 
	SET TenKhachHang = @TenKhachHang
	WHERE @MaKhachHang = MaKhachHang

 	UPDATE DonHang
	SET GiamGia = @GiamGia, ThanhTien = @ThanhTien
	WHERE MaDon = @MaDon
GO
CREATE PROC ThanhToanChiTietHoaDon @MaDon INT, @MaLinhKien INT, @SoLuong INT
AS
	INSERT INTO ChiTietDonHang (MaDon,MaLinhKien,SoLuong)
	VALUES (@MaDon, @MaLinhKien, @SoLuong)
GO
SELECT * FROM DonHang WHERE ThanhTien = 0