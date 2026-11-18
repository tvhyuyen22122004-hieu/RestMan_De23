# RESTMAN ĐỀ 23 – HƯỚNG DẪN CHẠY VỚI MYSQL

## 1. Yêu cầu
- JDK 21, Maven 3.9+, Apache Tomcat 9, MySQL 8.0+.
- Cần có mạng để Maven tải `mysql-connector-j` lần đầu.

## 2. Import database
Trong MySQL Workbench, mở `database/restman_de23.sql` rồi nhấn Execute (tia sét).
Hoặc PowerShell, đứng ở thư mục dự án:

```powershell
mysql -u root -p < database/restman_de23.sql
```

Lưu ý: Lệnh chuyển hướng `<` chạy trong CMD; trên PowerShell hãy dùng Workbench hoặc:

```powershell
cmd /c "mysql -u root -p < database\restman_de23.sql"
```

Script sẽ **xóa và tạo lại 4 bảng** `account`, `food`, `invoice`, `invoice_detail` nếu chúng đã tồn tại trong database `restman_de23`. Sao lưu dữ liệu trước khi import lại.

## 3. Cấu hình kết nối
Mặc định: `localhost:3306`, database `restman_de23`, user `root`, mật khẩu trống.
Nếu MySQL có mật khẩu, hãy đặt biến môi trường trước khi khởi động Tomcat:

```powershell
$env:RESTMAN_DB_HOST="localhost"
$env:RESTMAN_DB_PORT="3306"
$env:RESTMAN_DB_NAME="restman_de23"
$env:RESTMAN_DB_USER="root"
$env:RESTMAN_DB_PASSWORD="MAT_KHAU_MYSQL_CUA_BAN"
```

Biến môi trường phải được đặt **trong cùng terminal dùng để chạy Tomcat**. Không ghi mật khẩu thật vào mã nguồn hoặc GitHub.

## 4. Build và triển khai
Tại thư mục có `pom.xml`:

```powershell
mvn clean package
Copy-Item .\target\resmain.war "D:\apache-tomcat-9.0.115\webapps\resmain.war" -Force
```

Tắt Tomcat trước khi thay WAR, xóa thư mục triển khai `webapps/resmain` cũ nếu cần, rồi khởi động Tomcat.
Mở: http://localhost:8080/resmain/

Tài khoản demo: `khachhang` / `123456`; `quanly` / `123456`.
**Chỉ sử dụng mật khẩu demo trong môi trường học tập.** Đăng nhập demo đang dùng SHA-256 đơn thuần; ứng dụng sản xuất phải dùng Argon2/bcrypt/PBKDF2 có salt, chống CSRF và kiểm soát truy cập đầy đủ.

## 5. Kiểm tra theo báo cáo
- Khách hàng: tìm `vịt` → thấy 2 món; chọn món → xem chi tiết → quay lại.
- Không nhập từ khóa → thông báo; nhập `xyznotfound` → không có kết quả.
- Quản lý: thống kê `2026-11-01` đến `2026-11-30` → xem số lần gọi, số lượng và doanh thu.
- Chọn `Vịt quay` → thấy các hóa đơn 101, 102, 104; chọn một hóa đơn → xem chi tiết.
- Ngày bắt đầu sau ngày kết thúc → báo lỗi; khoảng không có hóa đơn → bảng rỗng.
- Truy cập trang quản lý khi đăng nhập khách hàng → HTTP 403.

## Phạm vi
Đây là **2 module theo đề bài** dùng dữ liệu MySQL thật; chưa triển khai các chức năng khác của hệ thống toàn diện như quản lý kho, combo, đặt bàn và thanh toán mới. Số lần gọi được tính là số hóa đơn chứa món; một hóa đơn có nhiều dòng cùng món vẫn tính một lần gọi.
