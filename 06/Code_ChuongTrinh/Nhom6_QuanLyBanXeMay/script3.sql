CREATE DATABASE QLBX
GO

USE [QLBX]
GO
ALTER TABLE [dbo].[HoaDon] DROP CONSTRAINT [fk_HD_NV]
GO
ALTER TABLE [dbo].[HoaDon] DROP CONSTRAINT [fk_HD_KH]
GO
ALTER TABLE [dbo].[ChiTietHD] DROP CONSTRAINT [fk_CTHD_XM]
GO
ALTER TABLE [dbo].[ChiTietHD] DROP CONSTRAINT [fk_CTHD_HD]
GO
ALTER TABLE [dbo].[BaoHanh] DROP CONSTRAINT [fk_BH_XM]
GO
/****** Object:  Table [dbo].[XeMay]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[XeMay]') AND type in (N'U'))
DROP TABLE [dbo].[XeMay]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NhanVien]') AND type in (N'U'))
DROP TABLE [dbo].[NhanVien]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhachHang]') AND type in (N'U'))
DROP TABLE [dbo].[KhachHang]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
DROP TABLE [dbo].[HoaDon]
GO
/****** Object:  Table [dbo].[ChiTietHD]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietHD]') AND type in (N'U'))
DROP TABLE [dbo].[ChiTietHD]
GO
/****** Object:  Table [dbo].[BaoHanh]    Script Date: 12/19/2021 7:50:53 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BaoHanh]') AND type in (N'U'))
DROP TABLE [dbo].[BaoHanh]
GO
/****** Object:  UserDefinedFunction [dbo].[ThongKeTheoThang]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP FUNCTION [dbo].[ThongKeTheoThang]
GO
/****** Object:  UserDefinedFunction [dbo].[ThongKeTheoNam]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP FUNCTION [dbo].[ThongKeTheoNam]
GO
/****** Object:  User [nv04]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP USER [nv04]
GO
/****** Object:  User [nv03]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP USER [nv03]
GO
/****** Object:  User [nv02]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP USER [nv02]
GO
/****** Object:  User [nv01]    Script Date: 12/19/2021 7:50:53 PM ******/
DROP USER [nv01]
GO
/****** Object:  User [nv01]    Script Date: 12/19/2021 7:50:53 PM ******/
CREATE USER [nv01] FOR LOGIN [nv01] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [nv02]    Script Date: 12/19/2021 7:50:53 PM ******/
CREATE USER [nv02] FOR LOGIN [nv02] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [nv03]    Script Date: 12/19/2021 7:50:53 PM ******/
CREATE USER [nv03] FOR LOGIN [nv03] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [nv04]    Script Date: 12/19/2021 7:50:53 PM ******/
CREATE USER [nv04] FOR LOGIN [nv04] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  UserDefinedFunction [dbo].[ThongKeTheoNam]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[ThongKeTheoNam](@nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), soXeDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.ma, a.hoTen, SUM(c.soLuong), SUM(soLuong * donGia) from NhanVien a join HoaDon b on a.ma = b.maNV join ChiTietHD c on b.maHD = c.maHD
			where YEAR(ngayLap) = @nam
			group by a.ma, a.hoTen
		return
	end
GO
/****** Object:  UserDefinedFunction [dbo].[ThongKeTheoThang]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


create function [dbo].[ThongKeTheoThang](@thang int, @nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), soXeDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.ma, a.hoTen, SUM(c.soLuong), SUM(soLuong * donGia) from NhanVien a join HoaDon b on a.ma = b.maNV join ChiTietHD c on b.maHD = c.maHD
			where YEAR(ngayLap) = @nam and MONTH(ngayLap) = @thang
			group by a.ma, a.hoTen
		return
	end
GO
/****** Object:  Table [dbo].[BaoHanh]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BaoHanh](
	[MaBH] [varchar](12) NOT NULL,
	[MaXe] [varchar](20) NULL,
	[Tenxe] [varchar](50) NULL,
	[ThongtinBH] [varchar](60) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaBH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHD]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHD](
	[maHD] [varchar](20) NULL,
	[maXeMay] [varchar](20) NULL,
	[soLuong] [int] NULL,
	[donGia] [money] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [varchar](20) NOT NULL,
	[maKH] [varchar](20) NULL,
	[maNV] [varchar](20) NULL,
	[ngayLap] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[ma] [varchar](20) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[diaChi] [nvarchar](50) NULL,
	[sdt] [varchar](20) NULL,
	[email] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[ma] [varchar](20) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[diaChi] [nvarchar](50) NULL,
	[sdt] [varchar](20) NULL,
	[email] [varchar](30) NULL,
	[quanLyVien] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[XeMay]    Script Date: 12/19/2021 7:50:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XeMay](
	[maXe] [varchar](20) NOT NULL,
	[tenXe] [nvarchar](50) NULL,
	[loaiXe] [nvarchar](30) NULL,
	[hangXe] [nvarchar](30) NULL,
	[dungTich] [int] NULL,
	[mauXe] [nvarchar](30) NULL,
	[nuocSX] [nvarchar](30) NULL,
	[soLuongTon] [int] NULL,
	[donGia] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[maXe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[BaoHanh] ([MaBH], [MaXe], [Tenxe], [ThongtinBH]) VALUES (N'BH01', N'XM01', N'JUPITER FI GP', N'Ðu?c b?o hành ch?n d?i ')
INSERT [dbo].[BaoHanh] ([MaBH], [MaXe], [Tenxe], [ThongtinBH]) VALUES (N'BH02', N'XM02', N'JUPITER FI GP', N'Ðu?c b?o hành 36 tháng k? t? ngoày s?n xu?t ')
INSERT [dbo].[BaoHanh] ([MaBH], [MaXe], [Tenxe], [ThongtinBH]) VALUES (N'BH03', N'XM03', N'abc', N'48 thang')
GO
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD03', N'XM05', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD07', N'XM01', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD010', N'XM02', 21, 29400000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD03', N'XM02', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD04', N'XM02', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD05', N'XM01', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD06', N'XM04', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD07', N'XM02', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD08', N'XM06', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD09', N'XM02', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD010', N'XM01', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD07', N'XM03', 23, 30000000.0000)
INSERT [dbo].[ChiTietHD] ([maHD], [maXeMay], [soLuong], [donGia]) VALUES (N'HD07', N'XM05', 23, 30000000.0000)
GO
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD01', N'KH02', N'NV01', CAST(N'2021-12-18' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD010', N'KH01', N'NV02', CAST(N'2021-10-16' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD03', N'KH03', N'NV01', CAST(N'2021-10-12' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD04', N'KH04', N'NV02', CAST(N'2021-10-12' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD05', N'KH05', N'NV01', CAST(N'2021-10-12' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD06', N'KH06', N'NV02', CAST(N'2021-10-13' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD07', N'KH07', N'NV02', CAST(N'2021-10-13' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD08', N'KH08', N'NV02', CAST(N'2021-10-14' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD09', N'KH03', N'NV01', CAST(N'2021-10-15' AS Date))
INSERT [dbo].[HoaDon] ([maHD], [maKH], [maNV], [ngayLap]) VALUES (N'HD11', N'KH01', N'NV01', CAST(N'2021-11-12' AS Date))
GO
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH01', N'Nguyễn Văn Lâm', 1, N'Phường 3, Quận Bình Tân', N'0386934153', NULL)
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH02', N'Hoàng Đình Chiến', 1, N'Phường 7, Quận Gò Vấp', N'0986635476', NULL)
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH03', N'Hoàng Kim Thoa', 0, N'Phường 4, Quận Gò Vấp', N'0367922251', NULL)
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH04', N'Trần Xuân Mạnh', 0, N'Phường 1, Quận Gò Vấp', N'0367162251', N'xuanmanh@gmail.com')
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH05', N'Kim Nguyên', 1, N'Phường 8, Quận Bình Thạnh', N'0986478153', NULL)
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH06', N'Vũ Quang Khải', 1, N'Phường 8, Quận Bình Thạnh', N'0932146789', N'quangkhai@yahoo.com')
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH07', N'Bình Minh', 1, N'Quận 9', N'0768394675', N'binhminh@gmail.com')
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH08', N'Nông Vĩnh Kha', 1, N'Phường 6, Quận 12', N'0658943156', NULL)
INSERT [dbo].[KhachHang] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email]) VALUES (N'KH09', N'Lý Tiểu Bảo', 1, N'Phường 3, Quận Bình Tân 123', N'0794276105', N'lytieubao2001@gmail.com')
GO
INSERT [dbo].[NhanVien] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email], [quanLyVien]) VALUES (N'NV01', N'Lý Tiểu Bảo', 1, N'Phường 4, Quận Gò Vấp', N'0794276105', N'lytieubao2001@gmail.com', 1)
INSERT [dbo].[NhanVien] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email], [quanLyVien]) VALUES (N'NV02', N'Nguyễn Trung Hải', 1, N'Phường 4, Quận Gò Vấp', N'098689746', N'trunghai@gmail.com', 1)
INSERT [dbo].[NhanVien] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email], [quanLyVien]) VALUES (N'NV03', N'Hoàng Xuân Khang', 1, N'Phường 4, Quận Gò Vấp', N'0367418965', N'xuankhang@gmail.com', 0)
INSERT [dbo].[NhanVien] ([ma], [hoTen], [gioiTinh], [diaChi], [sdt], [email], [quanLyVien]) VALUES (N'NV04', N'Lý Tiểu Bảoo', 1, N'Phường 4, Quận Gò Vấp', N'0794276104', N'lytieubao2001@gmail.com', 1)
GO
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM01', N'JUPITER FI GP', N'Xe số', N'Yamaha', 114, N'Xanh', N'Nhật Bảnn', 13, 30000000.0000)
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM02', N'JUPITER FI RC', N'Xe số', N'Yamaha', 114, N'Đen nhám', N'Nhật Bản', 11, 29400000.0000)
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM03', N'GRANDE BLUE CORE HYBRID', N'Xe tay ga', N'Yamaha', 125, N'Trắng ngọc trai', N'Trung Quốc', 6, 50000000.0000)
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM04', N'GRANDE BLUE CORE DELUX', N'Xe tay ga', N'Yamaha', 124, N'Xanh lá', N'Trung Quốc', 8, 41990000.0000)
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM05', N'JANUS LIMITED', N'Xe tay ga', N'Yamaha', 125, N'Trắng xanh', N'Hàn Quốc', 3, 31990000.0000)
INSERT [dbo].[XeMay] ([maXe], [tenXe], [loaiXe], [hangXe], [dungTich], [mauXe], [nuocSX], [soLuongTon], [donGia]) VALUES (N'XM06', N'NVX 155 ABS', N'Xe tay ga', N'Yamaha', 155, N'Đỏ', N'Nhật Bản', 16, 52240000.0000)
GO
ALTER TABLE [dbo].[BaoHanh]  WITH CHECK ADD  CONSTRAINT [fk_BH_XM] FOREIGN KEY([MaXe])
REFERENCES [dbo].[XeMay] ([maXe])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BaoHanh] CHECK CONSTRAINT [fk_BH_XM]
GO
ALTER TABLE [dbo].[ChiTietHD]  WITH CHECK ADD  CONSTRAINT [fk_CTHD_HD] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietHD] CHECK CONSTRAINT [fk_CTHD_HD]
GO
ALTER TABLE [dbo].[ChiTietHD]  WITH CHECK ADD  CONSTRAINT [fk_CTHD_XM] FOREIGN KEY([maXeMay])
REFERENCES [dbo].[XeMay] ([maXe])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietHD] CHECK CONSTRAINT [fk_CTHD_XM]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [fk_HD_KH] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([ma])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [fk_HD_KH]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [fk_HD_NV] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([ma])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [fk_HD_NV]
GO
