USE master
GO
CREATE DATABASE QuanLyMuaBanLinhKien
GO
USE QuanLyMuaBanLinhKien
GO
CREATE TABLE LoaiLinhKien(
	MaLoai INT PRIMARY KEY IDENTITY,
	TenLoai NVARCHAR(50)
)
CREATE TABLE LinhKien(
	MaLinhKien INT PRIMARY KEY IDENTITY,
	TenLinhKien NVARCHAR(50) NOT NULL,
	MaLoai INT FOREIGN KEY REFERENCES LoaiLinhKien(MaLoai),
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
	(N'Nguyễn Hồng Đức','Duc','11111111',1),
	(N'La Minh Tâm','Tam','11111111',1),
	(N'Nguyễn Thiên Tứ','Tu','11111111',1),
	(N'Văn Quí','Qui','11111111',1)
GO
INSERT INTO KhachHang(TenKhachHang, SoDienThoai, DiaChi)
VALUES 
		(N'Nguyễn Văn A','1111111111','126A NVB'),
		(N'Nguyễn Văn B','1111111111','126A NVB'),
		(N'Nguyễn Văn C','1111111111','126A NVB'),
		(N'Nguyễn Văn D','1111111111','126A NVB'),
		(N'Nguyễn Văn E','1111111111','126A NVB'),
		(N'Nguyễn Văn F','1111111111','126A NVB')
GO
INSERT INTO LoaiLinhKien(TenLoai)
VALUES 
		('CPU'),
		('RAM'),
		('VGA'),
		('Mainboard'),
		(N'Nguồn máy tính'),
		(N'Ổ cứng')
GO
INSERT INTO LinhKien(TenLinhKien, MaLoai,NgaySanXuat, HangSanXuat, SoLuong, DonGia)
VALUES
	('Core i3-10100F', 1,'2020-4-1','Intel',100,1990000),
	('Core i5 10400', 1,'2020-4-1','Intel',100,3399000),
	('Core i5 12400', 1,'2022-1-1','Intel',100,5399000),
	('Core i5-12600K', 1,'2021-11-1','Intel',100,7699000),
	('Ryzen 5 3600', 1,'2019-7-7','AMD',100,4499000),
	('Ryzen 5 5600G', 1,'2021-4-13','AMD',100,6499000),
	('Ryzen 5 5600X', 1,'2020-11-1','AMD',100,4899000),
	('Core i9-10900K', 1,'2020-4-1','Intel',100,11880000),
	('Core i9-11900K', 1,'2021-3-1','Intel',100,20789000),

	('Spectrix 8GB 3200MHz',2,'2021-12-27','ADATA',100,799000),
	('Fury 8GB 3200MHz',2,'2021-7-19','Kingston',100,549000),
	('Fury 16GB 3200MHz',2,'2021-7-19','Kingston',100,1590000),
	('Vengeance 8GB 3200MHz',2,'2021-9-1','Corsair',100,939000),
	('Vengeance 16GB 3200MHz',2,'2021-9-1','Corsair',100,1590000),
	('Lexar 16GB 3200MHz',2,'2021-8-14','Lexar',100,1050000),
	('Aegis 8GB 3200MHz',2,'2017-1-1','G.skill',100,550000),
	('Zeus 8GB 3200MHz',2,'2021-8-3','Kingmax',100,899000),
	('Trident 16GB 3000MHz',2,'2021-12-27','ADATA',100,1999000),
	('Lancer',2,'2021-1-1','ADATA',100,4999000),

	('RTX 3060 Ti DUAL',3,'2020-11-1','Asus',100,9999000),
	('GTX 1660 Ti',3,'2019-2-23','Asus',100,5499000),
	('AORUS RTX 3060 Ti',3,'2020-11-1','Gigatbyte',100,12499000),
	('DUAL-RX6500XT',3,'2022-1-19','Asus',100,9999000),
	('RTX 3060',3,'2021-2-1','MSI',100,11899000),
	('QUADRO P1000',3,'2017-2-7','Gigabyte',100,8490000),
	('RTX 4070 VENTUS',3,'2023-4-13','MSI',100,17490000),
	('RTX 4070 Ti',3,'2023-1-5','Asus',100,27990000),
	('GT 1030',3,'2020-11-1','MSI',100,2290000),
	
	('B660M-Plus',4, '2022-2-1','Asus',100,3990000),
	('GA-Z590 UD',4, '2021-1-27','Gigabyte',100,3990000),
	('STRIX Z690-I',4, '2021-10-27','Asus',100,9499000),
	('X570',4, '2019-7-7','MSI',100,4990000),
	('MPG Z690',4, '2022-11-19','MSI',100,11490000),
	('H510M PRO-E ',4, '2021-2-1','Asus',100,1399000),
	('Z590',4, '2021-2-16','ASROCK',100,5733000),
	('B450M',4, '2020-1-1','Asus',100,3990000),
	('Z790',4, '2022-9-27','Asus',100,27990000),
	('Z690',4, '2021-11-19','Asus',100,16590000),
	('X570SI',4, '2022-2-8','Gigabyte',100,7250000),
	('X299 PRO',4, '2020-5-5','MSI',100,9500000),

	('CV450',5, '2019-11-11','Corsair',100,799000),
	('CV650',5, '2019-11-20','Corsair',100,1399000),
	('CV750',5, '2021-4-20','Corsair',100,1799000),
	('MAG A550BN',5, '2021-8-26','MSI',100,1199000),
	('MAG A650BN',5, '2021-8-26','MSI',100,1399000),
	('CX550',5, '2017-3-1','Corsair',100,1199000),

	('870 EVO',6, '2021-1-1','Samsung',100,2800000),
	('980 PRO',6, '2022-11-1','Samsung',100,3700000),
	('CS900',6, '2018-3-12','PNY',100,2800000),
	('870 EVO',6, '2021-1-1','Samsung',100,2800000),
	('MX500 ',6, '2018-1-1','Samsung',100,4999000)
GO
INSERT INTO DonHang(MaKhachHang, NgayMua, MaNhanVien, GiamGia, ThanhTien)
VALUES 
	(1,'2023-4-20','3',0,6499000),
	(2,'2007-12-3','3',0,2889000),
	(3,'2007-12-3','3',0,18497000),
	(4,'2007-12-3','3',0,23927000),
	(5,'2007-12-3','3',0,23336000),
	(6,'2007-12-3','3',0,62157000)
GO
INSERT INTO ChiTietDonHang (MaDon, MaLinhKien, SoLuong)
VALUES
	(1,6,1),
	(2,1,1),
	(2,17,1),
	(3,4,1),
	(3,10,1),
	(3,20,1),
	(4,6,1),
	(4,13,1),
	(4,22,1),
	(4,30,1),
	(5,2,1),
	(5,11,1),
	(5,25,1),
	(5,31,1),
	(5,42,1),
	(6,9,1),
	(6,14,1),
	(6,27,1),
	(6,32,1),
	(6,43,1),
	(6,51,1)
GO
CREATE VIEW BillView AS 
SELECT MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien 
FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
GO
CREATE VIEW ProductView AS
SELECT MaLinhKien, TenLinhKien, TenLoai, NgaySanXuat, HangSanXuat, SoLuong, DonGia
FROM LinhKien LK JOIN LoaiLinhKien LLK ON LK.MaLoai = LLK.MaLoai
GO
CREATE PROC GetBillDetail @billID INT
AS
	SELECT MaChiTietDon, MaDon, TenLinhKien, TenLoai, CTD.SoLuong, DonGia 
	FROM ChiTietDonHang CTD JOIN LinhKien LK ON CTD.MaLinhKien = LK.MaLinhKien
	JOIN LoaiLinhKien LLK ON LLK.MaLoai = LK.MaLoai
	WHERE MaDon = @billID
GO
CREATE PROC GetBillByDate @startDay DATE, @endDay DATE
AS
	SELECT MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien 
	FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
	WHERE NgayMua >= @startDay AND NgayMua < DATEADD(DAY, 1, @endDay)
GO
CREATE PROC addNewBill
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM DonHang WHERE ThanhTien = 0)
		INSERT INTO DonHang(MaKhachHang,NgayMua,MaNhanVien,GiamGia,ThanhTien)
		VALUES (null,GETDATE(),null,0,0)
END
GO
CREATE TRIGGER addNewCustomer ON DonHang
FOR INSERT
AS
	INSERT INTO KhachHang(TenKhachHang, SoDienThoai, DiaChi)
	VALUES (null, null, null)
GO
CREATE TRIGGER addCustomerToBill ON KhachHang
FOR INSERT
AS
	DECLARE @MaKhachHang INT
	SELECT @MaKhachHang = MaKhachHang FROM KhachHang
	UPDATE DonHang SET MaKhachHang = @MaKhachHang
	WHERE ThanhTien = 0
GO
CREATE PROC GetNewBill
AS
	SELECT MaDon FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang
	WHERE ThanhTien = 0
GO
CREATE PROC UpdateBillDate
AS
	UPDATE DonHang SET NgayMua = GETDATE() WHERE ThanhTien = 0
GO
CREATE PROC PayBill @BillID INT, @EmpID INT, @Discount INT, @Total MONEY, @CustomerName NVARCHAR(50), @PhoneNumber NVARCHAR(10), @Address NVARCHAR(100)
AS
	DEClARE @MaKhachHang NVARCHAR(50)
	SELECT @MaKhachHang = MaKhachHang FROM DonHang WHERE MaDon = @BillID

	UPDATE KhachHang 
	SET TenKhachHang = @CustomerName, SoDienThoai = @PhoneNumber, DiaChi = @Address
	WHERE @MaKhachHang = MaKhachHang

 	UPDATE DonHang
	SET MaNhanVien = @EmpID, GiamGia = @Discount, ThanhTien = @Total
	WHERE MaDon = @BillID
GO
CREATE PROC PayBillDetail @BillID INT, @ProductID INT, @Qty INT
AS
	INSERT INTO ChiTietDonHang (MaDon,MaLinhKien,SoLuong)
	VALUES (@BillID, @ProductID, @Qty)
GO
CREATE PROC Login @username NVARCHAR(50), @password NVARCHAR(50)
AS
	SELECT * FROM NhanVien WHERE TenDangNhap = @username AND MatKhau = @password
GO
CREATE PROC GetProductByCategory @CategoryID INT
AS
	SELECT MaLinhKien, TenLinhKien, TenLoai, NgaySanXuat, HangSanXuat, SoLuong, DonGia FROM LinhKien LK JOIN LoaiLinhKien LLK ON LK.MaLoai = LLK.MaLoai
	WHERE LLK.MaLoai = @CategoryID
GO
CREATE PROC GetProductByName @ProductName NVARCHAR(255), @CategoryID INT
AS
	IF @CategoryID != 0
		SELECT MaLinhKien, TenLinhKien, TenLoai, NgaySanXuat, HangSanXuat, SoLuong, DonGia 
		FROM LinhKien LK JOIN LoaiLinhKien LLK ON LK.MaLoai = LLK.MaLoai WHERE TenLinhKien LIKE '%' + @ProductName + '%' AND LK.MaLoai = @CategoryID
	ELSE
		SELECT MaLinhKien, TenLinhKien, TenLoai, NgaySanXuat, HangSanXuat, SoLuong, DonGia 
		FROM LinhKien LK JOIN LoaiLinhKien LLK ON LK.MaLoai = LLK.MaLoai WHERE TenLinhKien LIKE '%' + @ProductName + '%'
GO
CREATE PROC statisticByProduct @productId INT, @fromDate DATE, @toDate DATE
AS
	SELECT DISTINCT DH.MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien 
	FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang 
	JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
	JOIN ChiTietDonHang CTDH ON CTDH.MaDon = DH.MaDon
	WHERE MaLinhKien = @productId AND NgayMua >= @fromDate AND NgayMua < DATEADD(DAY, 1, @toDate)
GO
CREATE PROC statisticByCategory @categoryId INT, @fromDate DATE, @toDate DATE
AS
	SELECT DISTINCT DH.MaDon, TenKhachHang, NgayMua, TenNhanVien, GiamGia, ThanhTien
	FROM DonHang DH JOIN KhachHang KH ON DH.MaKhachHang = KH.MaKhachHang 
	JOIN NhanVien NV ON DH.MaNhanVien = NV.MaNhanVien
	JOIN ChiTietDonHang CTDH ON CTDH.MaDon = DH.MaDon
	JOIN LinhKien LK ON LK.MaLinhKien = CTDH.MaLinhKien
	WHERE Maloai = @categoryId AND NgayMua >= @fromDate AND NgayMua < DATEADD(DAY, 1, @toDate)
GO