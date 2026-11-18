-- RESTMAN DE 23 - MySQL 8.0+, UTF8MB4
-- Import bằng MySQL Workbench hoặc: mysql -u root -p < database/restman_de23.sql
CREATE DATABASE IF NOT EXISTS restman_de23 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE restman_de23;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS invoice_detail;
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS account;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE account (
 id INT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(60) NOT NULL UNIQUE,
 password_hash CHAR(64) NOT NULL,
 full_name VARCHAR(120) NOT NULL,
 role ENUM('KHACH_HANG','QUAN_LY') NOT NULL,
 active TINYINT(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB;
CREATE TABLE food (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(150) NOT NULL,
 category VARCHAR(80) NOT NULL,
 price BIGINT NOT NULL,
 serving VARCHAR(80) NOT NULL,
 description TEXT,
 CHECK(price>=0), INDEX idx_food_name(name)
) ENGINE=InnoDB;
CREATE TABLE invoice (
 id INT PRIMARY KEY,
 customer_name VARCHAR(120) NOT NULL,
 table_name VARCHAR(50) NOT NULL,
 issued_at DATETIME NOT NULL,
 status ENUM('PAID','UNPAID','CANCELLED') NOT NULL DEFAULT 'PAID',
 INDEX idx_invoice_status_date(status,issued_at)
) ENGINE=InnoDB;
CREATE TABLE invoice_detail (
 id INT PRIMARY KEY AUTO_INCREMENT,
 invoice_id INT NOT NULL,
 food_id INT NOT NULL,
 quantity INT NOT NULL,
 unit_price BIGINT NOT NULL,
 CONSTRAINT fk_detail_invoice FOREIGN KEY(invoice_id) REFERENCES invoice(id),
 CONSTRAINT fk_detail_food FOREIGN KEY(food_id) REFERENCES food(id),
 CHECK(quantity>0), CHECK(unit_price>=0),
 INDEX idx_detail_food(food_id)
) ENGINE=InnoDB;
INSERT INTO account(username,password_hash,full_name,role) VALUES
 ('khachhang',SHA2('123456',256),'Khách hàng Demo','KHACH_HANG'),
 ('quanly',SHA2('123456',256),'Quản lý Demo','QUAN_LY');
INSERT INTO food(id,name,category,price,serving,description) VALUES
 (1,'Vịt quay','Món chính',300000,'2 người','Vịt quay giòn thơm, dùng kèm rau sống.'),
 (2,'Vịt Bắc Kinh','Món chính',350000,'3 người','Vịt quay kiểu Bắc Kinh, ăn kèm bánh tráng.'),
 (3,'Cơm rang thập cẩm','Cơm',60000,'1 người','Cơm rang với trứng, rau củ và thịt.'),
 (4,'Phở bò','Món nước',50000,'1 người','Phở bò nước dùng truyền thống.'),
 (5,'Gà nướng mật ong','Món chính',180000,'2 người','Gà nướng mật ong vàng óng.'),
 (6,'Salad rau củ','Khai vị',45000,'1 người','Rau tươi và sốt nhẹ.');
INSERT INTO invoice(id,customer_name,table_name,issued_at,status) VALUES
 (101,'Nguyễn An','Bàn 01','2026-11-01 12:30:00','PAID'),
 (102,'Trần Bình','Bàn 03','2026-11-03 18:45:00','PAID'),
 (103,'Lê Chi','Bàn 05','2026-11-05 19:10:00','PAID'),
 (104,'Phạm Dũng','Bàn 02','2026-11-08 11:15:00','PAID'),
 (105,'Hoàng Hà','Bàn 04','2026-11-12 20:00:00','PAID'),
 (106,'Đỗ Lan','Bàn 06','2026-10-25 17:30:00','PAID');
INSERT INTO invoice_detail(invoice_id,food_id,quantity,unit_price) VALUES
 (101,1,2,300000),(101,3,1,60000),
 (102,1,1,300000),(102,4,2,50000),
 (103,2,2,350000),(103,5,1,180000),
 (104,1,3,300000),(104,6,2,45000),
 (105,2,1,350000),(105,4,3,50000),
 (106,1,1,300000),(106,3,2,60000);
-- Thống kê theo doanh thu: chỉ tính hóa đơn PAID, giữ đơn giá tại thời điểm bán.
-- Không tạo bảng thống kê: báo cáo được tính từ invoice_detail và invoice.
