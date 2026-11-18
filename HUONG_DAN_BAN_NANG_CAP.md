# RestMan Đề 23 — bản nâng cấp giao diện

## Các trang đã cập nhật
- Đăng nhập: bỏ thông tin tài khoản demo.
- Trang chủ khách hàng: tìm kiếm món ăn, đặt bàn, đặt món (hai mục sau chỉ hiển thị theo sơ đồ tổng thể, chưa triển khai).
- Trang tìm kiếm: hiển thị sẵn món ăn đề xuất từ MySQL, tìm theo từ khóa, xem chi tiết.
- Trang chủ quản lý → Xem báo cáo → Thống kê món ăn theo doanh thu → Chi tiết lần gọi → Hóa đơn.
- Trang thống kê: KPI, xếp hạng món ăn bằng thanh doanh thu, bảng thống kê; tất cả dựa trên dữ liệu truy vấn MySQL, không phải số liệu giả.
- Chi tiết món: KPI số lần gọi, số lượng, doanh thu.
- Thanh trên cùng: bỏ chữ Demo, hiển thị tiếng Việt UTF-8.

## Chạy
1. Import `database/restman_de23.sql` bằng MySQL Workbench **chỉ khi chưa có database**. LƯU Ý: file SQL này có lệnh DROP TABLE và sẽ xóa dữ liệu cũ nếu import lại.
2. Kiểm tra `src/main/java/dao/Database.java` để cấu hình MySQL. File gốc đang lưu mật khẩu trực tiếp trong Java; chỉ phù hợp demo trên máy cá nhân, không đưa lên GitHub công khai.
3. Mở PowerShell tại thư mục chứa `pom.xml`: `mvn clean package`.
4. Dừng Tomcat, copy `target/resmain.war` vào `D:\apache-tomcat-9.0.115\webapps\resmain.war`; xóa thư mục webapps/resmain cũ khi Tomcat đã dừng.
5. Chạy Tomcat, mở `http://localhost:8080/resmain/`.

## Giới hạn kiểm thử
Đã kiểm tra cấu trúc và các luồng mã nguồn, nhưng chưa chạy Maven/Tomcat/MySQL trong môi trường tạo file. Cần kiểm tra thực tế trên máy Windows của bạn.
