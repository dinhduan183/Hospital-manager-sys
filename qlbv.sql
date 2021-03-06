-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 23, 2022 lúc 04:17 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlbv`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bacsi`
--

CREATE TABLE `bacsi` (
  `id` int(11) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `tuoi` int(11) NOT NULL,
  `gioitinh` varchar(20) NOT NULL,
  `chuyenkhoa` varchar(200) NOT NULL,
  `chucvu` varchar(200) NOT NULL,
  `diachi` varchar(200) NOT NULL,
  `sdt` varchar(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `bacsi`
--

INSERT INTO `bacsi` (`id`, `hoten`, `tuoi`, `gioitinh`, `chuyenkhoa`, `chucvu`, `diachi`, `sdt`, `email`, `username`, `password`) VALUES
(1, 'Nguyễn Đình Duẩn', 22, 'Nam', 'Ngoại thần kinh', 'Viện trưởng', 'Nghệ An', '08545648445', '3333333@gmail.com', 'duan', 'duan'),
(2, 'Vũ Tài Cương', 20, 'Nam', 'Phụ sản', 'Bác sĩ', 'Bắc Ninh', '0999999999', 'cuongankhu@gmail.com', 'cuong', 'cuong'),
(3, 'Đinh Thị Phương Dung', 21, 'Nữ', 'Cấp cứu', 'Bác sĩ', 'Lạng Sơn', '0888888888', 'dungthi@gmail.com', 'dung', 'dung'),
(4, 'Diệp Thạch', 30, 'Nam', 'Xét nghiệm', '', 'Thôn Yên Huấn, Huyện 5 Tiền Giang', '02912566701', 'nga.ong@gmail.com', '', ''),
(5, 'Đới Đăng Kỳ', 42, 'Nam', 'Phụ sản', '', 'Quận Hảo Hiên, Hải Phòng', '0781827080', 'dangky2322@gmail.com', '', ''),
(6, 'Phí Tâm Thiên', 61, 'Nữ', 'Chấn thương chỉnh hình', '', 'Huyện Ngô Thiện, Ninh Thuận', '0710391066', 'bchu@gmail.com', '', ''),
(7, 'Trần Quang Bảo Thiên', 21, 'Nam', 'Bỏng', '', 'Từ Sơn - Bắc Ninh', '0123455223', 'baothien@gmail.com', '', ''),
(8, 'Mâu Mai Thủy', 54, 'Nữ', 'Bỏng', '', 'Huyện Thi Di, Hà Tĩnh', '0951079248', 'xhong@gmail.com', '', ''),
(9, 'Đào Khánh Hành', 53, 'Nam', 'Xét nghiệm', '', 'Thái Nguyên', '0534535383', 'nhien21@gmail.com', '', ''),
(10, 'Ma Đoàn Cẩn', 37, 'Nam', 'Cấp cứu', '', 'Huyện 2, Lâm Đồng', '0612152447', 'nghi.ma@gmail.com', '', ''),
(11, 'Tiếp Dã Loan', 39, 'Nữ', 'Nhi', '', 'Thái Nguyên', '0122355155', 'khong.hien@gmail.com', '', ''),
(12, 'Đinh Sắc Đính', 36, 'Nam', 'Chấn thương chỉnh hình', '', 'Lào Cai', '0888888888', 'dinhdinh@gmail.com', '', ''),
(13, 'Chu Thị Hồng', 31, 'Nữ', 'Ngoại thần kinh', '', 'Huyện Khâu Nữ, Đắk Nông', '0534574175', 'phat.luong@gmail.com', '', ''),
(14, 'Phạm Nguyễn Kiệt', 64, 'Nam', 'Ngoại thần kinh', '', 'Gia Bình, Bắc Ninh', '0510754634', 'nhan.tra@gmail.com', '', ''),
(15, 'An Hiền', 48, 'Nam', 'Cấp cứu', '', 'Thái Nguyên', '0681251025', 'yong@gmail.com', '', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baocaosuco`
--

CREATE TABLE `baocaosuco` (
  `id` int(11) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `chucvu` varchar(200) NOT NULL,
  `noidung` text NOT NULL,
  `thoigian` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `baocaosuco`
--

INSERT INTO `baocaosuco` (`id`, `hoten`, `chucvu`, `noidung`, `thoigian`) VALUES
(1, 'Vũ Tài Cương', 'Bác sĩ', 'Phần mềm sử dụng rất tốt!', '2022-01-22 09:47:52'),
(2, 'Nguyễn Đình Duẩn', 'Viện trưởng', 'Test', '2022-01-22 21:30:19');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `benhnhan`
--

CREATE TABLE `benhnhan` (
  `id` varchar(30) NOT NULL,
  `hoten` varchar(200) NOT NULL DEFAULT ' ',
  `ngaysinh` varchar(20) NOT NULL DEFAULT ' ',
  `gioitinh` varchar(20) NOT NULL DEFAULT ' ',
  `nghenghiep` varchar(200) NOT NULL DEFAULT ' ',
  `diachi` varchar(300) NOT NULL DEFAULT ' ',
  `sdt` varchar(20) NOT NULL DEFAULT ' ',
  `doituong` varchar(30) NOT NULL DEFAULT ' ',
  `tennguoithan` varchar(200) NOT NULL DEFAULT ' ',
  `sdtnguoithan` varchar(20) NOT NULL DEFAULT ' ',
  `vaovien` varchar(20) NOT NULL DEFAULT ' ',
  `chuandoan` varchar(1000) NOT NULL DEFAULT ' ',
  `khoadieutri` varchar(200) NOT NULL DEFAULT ' ',
  `ravien` varchar(20) NOT NULL DEFAULT ' ',
  `lidoravien` varchar(1000) DEFAULT ' ',
  `ketquadieutri` varchar(200) DEFAULT ' ',
  `tuvong` varchar(20) DEFAULT ' ',
  `nguyennhantuvong` varchar(1000) DEFAULT ' '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `benhnhan`
--

INSERT INTO `benhnhan` (`id`, `hoten`, `ngaysinh`, `gioitinh`, `nghenghiep`, `diachi`, `sdt`, `doituong`, `tennguoithan`, `sdtnguoithan`, `vaovien`, `chuandoan`, `khoadieutri`, `ravien`, `lidoravien`, `ketquadieutri`, `tuvong`, `nguyennhantuvong`) VALUES
('1', 'Bệnh nhân 1', '12/11/20022', 'Nữ', 'Kế toán', 'Mê Linh - Hà Nội', '0373555164', 'Thu phí', 'Người nhà 1', '0556115336', '12/11/2021', 'Đau ruột thừa -> mổ	', 'Tiêu hóa', '2021-11-02', 'Đã mổ thành công, hết bệnh', 'Khỏi', '2999-12-31', 'chưa tử vong'),
('222', 'Bệnh nhân 1', '2021-12-15', 'Nam', ' Sinhvien', 'Nghệ An', ' 0987654321', 'BHYT', 'ABC', '0908939281', '13/11/2021', ' Đau dạ dày -> viêm dạ dày', 'Tiêu hóa', ' ', ' ', NULL, '', ''),
('BN01', 'Nguyễn Duẩn', '2000-03-18', 'Nam', 'Sinh viên', 'Nghi Diên - Nghi Lộc', ' ', 'BHYT', 'Nguyễn Vinh', '0984941596', '11/12/2021', 'Sức khỏe bình thường', 'Hồi sức', '2021-12-12', 'Sức khỏe ổn định', 'Khỏi', ' ', ' '),
('BN02', 'Bệnh nhân 2', '1983-05-22', ' ', ' ', 'Gia Bình - Bắc Ninh', ' ', ' ', 'Người nhà 2', '0958446882', ' 23/12/2021', ' ', 'Răng hàm mặt', ' ', ' ', ' ', ' ', ' '),
('BN03', 'Bệnh nhân 3', '18-03-2000', 'Nam', ' ', 'HN', ' ', 'Thu phí', 'Người nhà 3', '09039398933', ' 23/12/2021', ' ', 'Nội tổng hợp', ' ', ' ', 'Không thay đổi', ' ', ' '),
('BN04', 'Bệnh nhân 4', '18-03-2000', ' ', ' ', 'HN', ' ', 'Thu phí', 'Người nhà 4', '09039398933', ' 24/12/2021', ' ', 'Nội tổng hợp', ' ', ' ', 'Không thay đổi', ' ', ' '),
('BN05', 'Bệnh nhân 5', '18-03-2000', 'Nữ', ' ', 'HN', ' ', 'BHYT', 'Người nhà 5', '09039398933', ' 24/12/2021', ' ', 'Nội tổng hợp', ' ', ' ', NULL, ' ', ' '),
('BN06', 'Nguyễn Thị Bích', '01/01/1980', 'Nữ', 'Kinh doanh', 'Hà Nội', '0985858585', 'BHYT', 'Nguyễn Thị A', '09983653', '01:59:10 02/01/2022', '- Đau nhẹ phần bụng\n- Chóng mặt buồn nôn', 'Tiêu hóa', ' ', ' ', ' ', ' ', ' '),
('BN07', 'Trần Chí Công', '08/12/1977', 'Nam', 'Lái xe', 'Cao Bằng', '0989181989', 'BHYT', 'Phan Thị Mai Hoa', '0898818898', '03:36:39 02/01/2022', '- Đau vai gáy trái\n- Buốt sống lưng khi hoạt động mạnh', 'Cột sống', ' ', ' ', ' ', ' ', ' '),
('BN08', 'Nguyễn Đức Bo', '02/07/2003', 'Nam', 'Sinh viên', 'Ba Vì - Hà Nội', '0395558446', 'BHYT', 'Nguyễn Đức Chu', '0985584623', '23:04:44 01/01/2022', '- Đau răng khôn', 'Răng hàm mặt', ' ', ' ', ' ', ' ', ' '),
('BN09', 'Chu Thị Phương', '01/01/1990', 'Nữ', 'Người mẫu', 'Nghệ An', '0987652543', 'Thu phí', 'Chu Ngọc Dũng', '095481235', '03:54:20 02/01/2022', '- Mỏi cơ, đau thắt lưng', 'Cột sống', ' ', ' ', ' ', ' ', ' '),
('BN10', 'Vũ Tài Cương', '01/01/1992', 'Nam', '545', 'ẻdrrd', '131312', 'Thu phí', 'dấdasda', '13123131', '22:37:29 13/01/2022', '-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n', 'Chấn thương chỉnh hình', ' ', ' ', ' ', ' ', ' '),
('BN11', 'Trần Mai Thương', '01/01/2000', 'Nữ', 'Sinh viên', 'Việt Nam', '0255448313', 'BHYT', 'Trần Mạnh Dũng', '0985584623', '13:15:44 10/01/2022', '- Đau lồng ngực', 'Lồng ngực', ' ', ' ', ' ', ' ', ' '),
('BN12', 'Chu Thị Trà', '01/01/1993', 'Nam', 'Người mẫu', 'Nghệ An', '0987652543', 'BHYT', 'Chu Ngọc Dũng', '095481235', '03:54:20 02/01/2022', '- Mỏi cơ, đau thắt lưng', 'Cấp cứu', ' ', ' ', ' ', ' ', ' '),
('BN13', 'Test1', '23/11/1992', 'Nam', 'nông dân', 'Quận 1, TP Hồ Chí Minh', '01454621324', 'BHYT', 'Trần Đức Nhân', '08885446213', '22:22:54 22/01/2022', '- Đau đầu liên tục\n- Thi thoảng buồn nôn\n- Có đau bụng, chóng mặt như tụt huyết áp', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN14', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN15', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN16', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN17', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN18', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN19', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN20', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN21', '', '23/11/1992', ' ', ' ', '', ' ', ' ', 'Trần Đức Nhân', '08885446213', ' ', ' ', 'Tim mạch', ' ', ' ', ' ', ' ', ' '),
('BN22', 'Trần Văn Tín', '01/01/1986', 'Nam', 'Lái xe', 'Sơn La', '0958446212', 'BHYT', 'Trần Văn Hà', '095481235', '03:54:20 02/01/2022', '- Đau vai gáy', 'Cột sống', ' ', ' ', ' ', ' ', ' '),
('BN23', 'Đặng Duy', '02/02/2003', 'Nam', 'Sinh viên', 'HN', '014747445', 'Thu phí', 'dấdasdasdad', '0545454', '16:56:14 10/01/2022', '- Tê bì chân tay\n- Đau nhức xương khớp', 'Cột sống', ' ', ' ', ' ', ' ', ' ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `benhnhanxuatvien`
--

CREATE TABLE `benhnhanxuatvien` (
  `id` int(11) NOT NULL,
  `hoten` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khothuoc`
--

CREATE TABLE `khothuoc` (
  `mathuoc` varchar(30) NOT NULL,
  `tenthuoc` varchar(200) NOT NULL,
  `nhom` varchar(200) NOT NULL,
  `cachdung` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khothuoc`
--

INSERT INTO `khothuoc` (`mathuoc`, `tenthuoc`, `nhom`, `cachdung`) VALUES
('DD001', 'Omeprazol', 'Dạ dày', 'Uống'),
('DD002', 'Lansoprazol', 'Dạ dày', 'Uống'),
('DD003', 'Pantoprazol', 'Dạ dày', 'Uống'),
('DD004', 'Rabeprazol', 'Dạ dày', 'Uống'),
('DD005', 'Esomeprazol', 'Dạ dày', 'Uống'),
('DL001', 'Dipolag-G', 'Da liễu', 'Bôi'),
('DL002', 'Kedermfa', 'Da liễu', 'Bôi'),
('DL003', 'Acyclovir', 'Da liễu', 'Uống'),
('DL004', 'Dermovate', 'Da liễu', 'Bôi'),
('GD001', 'Paracetamol', 'Giảm đau', 'Uống'),
('GD002', 'Acid acetylsalicylic (aspirin)', 'Giảm đau', 'Tiêm'),
('GD003', 'Floctafenin', 'Giảm đau', 'Uống'),
('GD004', 'Nefopam', 'Giảm đau', 'Tiêm'),
('GD005', 'Codein', 'Giảm đau', 'Tiêm'),
('KS001', 'Amoxicillin', 'Kháng sinh', 'Uống'),
('KS002', 'Ampicillin', 'Kháng sinh', 'Uống'),
('KS003', 'Benzathine benzylpenicillin', 'Kháng sinh', 'Tiêm'),
('KS004', 'Cefalexin', 'Kháng sinh', 'Tiêm'),
('KS005', 'Cefixime', 'Kháng sinh', 'Tiêm'),
('NAO001', 'Betaserc', 'Não', 'Uống'),
('NAO002', 'Cinarizin', 'Não', 'Uống'),
('NAO003', 'Flunarizin', 'Não', 'Uống'),
('NAO004', 'Tanakan', 'Não', 'Uống'),
('NAO005', 'Piracetam', 'Não', 'Uống'),
('NAO006', 'Hoạt huyết dưỡng não', 'Não', 'Uống'),
('TH001', 'Acetylcystein', 'Thuốc ho và long đờm', 'Uống'),
('TH002', 'Bromhexin', 'Thuốc ho và long đờm', 'Uống'),
('TH003', 'Ambroxol', 'Thuốc ho và long đờm', 'Uống'),
('TH004', 'Terpin Codein', 'Thuốc ho và long đờm', 'Uống'),
('TH005', 'Dextromethorphan', 'Thuốc ho và long đờm', 'Uống'),
('TIM001', 'Amlodpin', 'Tim mạch', 'Uống'),
('TIM002', 'Nifedipin', 'Tim mạch', 'Uống'),
('TIM003', 'Captoril', 'Tim mạch', 'Uống'),
('TIM004', 'Losarstan', 'Tim mạch', 'Uống'),
('TIM005', 'Concor', 'Tim mạch', 'Uống'),
('TM001', 'Amlodpin', 'Tim mạch', 'Uống');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `chucvu` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `hoten`, `chucvu`) VALUES
(1, 'admin', 'admin', '', 'Quản trị'),
(3, 'duan', 'duan', 'Nguyễn Đình Duẩn', 'Quản trị'),
(4, 'cuong', '12345', 'Vũ Tài Cương', 'Y Tá');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thamkham`
--

CREATE TABLE `thamkham` (
  `id` int(11) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `tuoi` varchar(11) NOT NULL,
  `namsinh` varchar(20) NOT NULL,
  `gioitinh` varchar(10) NOT NULL,
  `nghenghiep` varchar(200) NOT NULL,
  `diachi` varchar(2000) NOT NULL,
  `sdt` varchar(20) NOT NULL,
  `doituong` varchar(50) NOT NULL,
  `cmnd` varchar(20) NOT NULL,
  `hotennguoinha` varchar(200) NOT NULL,
  `sdtnguoinha` varchar(20) NOT NULL,
  `ngayvao` varchar(30) NOT NULL,
  `tinhtrang` varchar(2000) NOT NULL,
  `phong` varchar(20) NOT NULL,
  `henkham` varchar(30) NOT NULL,
  `trangthai` varchar(50) NOT NULL DEFAULT 'Chờ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `thamkham`
--

INSERT INTO `thamkham` (`id`, `hoten`, `tuoi`, `namsinh`, `gioitinh`, `nghenghiep`, `diachi`, `sdt`, `doituong`, `cmnd`, `hotennguoinha`, `sdtnguoinha`, `ngayvao`, `tinhtrang`, `phong`, `henkham`, `trangthai`) VALUES
(1, 'Nguyễn Đức Bo', '20', '02/07/2003', 'Nam', 'Sinh viên', 'Ba Vì - Hà Nội', '0395558446', 'BHYT', '187774658', 'Nguyễn Đức Chu', '0985584623', '23:04:44 01/01/2022', '- Đau răng khôn', '102', '__/__/2022', 'Nhập viện'),
(7, 'Trần Văn Tín', '36', '01/01/1986', 'Nam', 'Lái xe', 'Sơn La', '0958446212', 'BHYT', '187445623', 'Trần Văn Hà', '095481235', '03:54:20 02/01/2022', '- Đau vai gáy', '101', '__/__/2022', 'Nhập viện'),
(8, 'Chu Thị Trà', '29', '01/01/1993', 'Nam', 'Người mẫu', 'Nghệ An', '0987652543', 'BHYT', '189958998', 'Chu Ngọc Dũng', '095481235', '03:54:20 02/01/2022', '- Mỏi cơ, đau thắt lưng', '102', '__/__/2022', 'Nhập viện'),
(9, 'Trần Hữu Nam', '64', '01/01/1958', 'Nam', 'Tự do', 'Sơn La', '0958446212', 'Thu phí', '187445623', 'Trần Hữu Phúc', '095481235', '03:54:20 02/01/2022', '- Nhức khớp', '101', '28/01/2022', 'Hẹn khám'),
(10, 'Phan Thị Nhung', '47', '01/01/1975', 'Nữ', 'Tự do', 'Sơn La', '0958446212', 'BHYT', '187445623', 'Phan Thị Tuyết', '095481235', '03:54:20 02/01/2022', '- Đau phần bụng trên\n- buồn nôn, ăn ko ngon', '101', '__/__/2022', 'Đã xong'),
(11, 'Nguyễn Đức Tùng', '58', '01/01/1964', 'Nam', 'Lái xe', 'Ba Vì - Hà Nội', '0395558446', 'Thu phí', '187774658', 'Nguyễn Đức Chu', '0985584623', '05:27:48 02/01/2022', '- Đau răng khôn', '102', '__/__/2022', 'Nhập viện'),
(12, 'Chu Thị Phương', '32', '01/01/1990', 'Nữ', 'Người mẫu', 'Nghệ An', '0987652543', 'Thu phí', '189958998', 'Chu Ngọc Dũng', '095481235', '03:54:20 02/01/2022', '- Mỏi cơ, đau thắt lưng', '102', '__/__/2022', 'Nhập viện'),
(13, 'Trần Mai Thương', '22', '01/01/2000', 'Nữ', 'Sinh viên', 'Việt Nam', '0255448313', 'BHYT', '187774658', 'Trần Mạnh Dũng', '0985584623', '13:15:44 10/01/2022', '- Đau lồng ngực', '130', '__/__/2022', 'Nhập viện'),
(14, 'Nguyễn Hà', '60', '07/11/1962', 'Nữ', 'Giáo Viên', 'Đống Đa - Hà Nội', '0985441225', 'Thu phí', '187774658', 'Nguyễn Đức Chu', '0985584623', '22:01:44 03/02/2022', '- Đau chân', '102', '30/03/2022', 'Hẹn khám'),
(15, 'Đặng Duy', '19', '02/02/2003', 'Nam', 'Sinh viên', 'HN', '014747445', 'Thu phí', '155161656', 'dấdasdasdad', '0545454', '16:56:14 10/01/2022', '- Tê bì chân tay\n- Đau nhức xương khớp', '103', '__/__/2022', 'Nhập viện'),
(16, 'Vũ Tài Cương', '30', '01/01/1992', 'Nam', '545', 'ẻdrrd', '131312', 'Thu phí', '1311231', 'dấdasda', '13123131', '22:37:29 13/01/2022', '-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n-Đauuuuuuuuuuuuuuuuuu\n', '103', '15/02/2022', 'Nhập viện'),
(17, 'Trần Minh Nghi', '28', '07/09/1994', 'Nữ', 'Streamer', 'Quận 1, TP Hồ Chí Minh', '0978558774', 'Thu phí', '02000254864', 'Trần Đức Nhân', '08885446213', '21:00:14 22/01/2022', '- Đau đầu liên tục\n- Thi thoảng buồn nôn\n- Có đau bụng, chóng mặt như tụt huyết áp', '103', '14/02/2022', 'Hẹn khám'),
(18, 'Test1', '30', '23/11/1992', 'Nam', 'nông dân', 'Quận 1, TP Hồ Chí Minh', '01454621324', 'BHYT', '02000254864', 'Trần Đức Nhân', '08885446213', '22:22:54 22/01/2022', '- Đau đầu liên tục\n- Thi thoảng buồn nôn\n- Có đau bụng, chóng mặt như tụt huyết áp', '103', '14/02/2022', 'Nhập viện');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bacsi`
--
ALTER TABLE `bacsi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `baocaosuco`
--
ALTER TABLE `baocaosuco`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `benhnhan`
--
ALTER TABLE `benhnhan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `khothuoc`
--
ALTER TABLE `khothuoc`
  ADD PRIMARY KEY (`mathuoc`);

--
-- Chỉ mục cho bảng `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `thamkham`
--
ALTER TABLE `thamkham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bacsi`
--
ALTER TABLE `bacsi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=204;

--
-- AUTO_INCREMENT cho bảng `baocaosuco`
--
ALTER TABLE `baocaosuco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `thamkham`
--
ALTER TABLE `thamkham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
