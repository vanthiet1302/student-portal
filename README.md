## 1. 💡 Phân tích Yêu cầu và Phạm vi Dự án (Requirements Analysis & Scoping)

* **Chức năng Cốt lõi:** 
  * Đăng nhập/Đăng xuất
  * Xem thông tin cá nhân
  * Xem điểm
  * Xem thời khóa biểu
  * Đăng ký môn học
* **Đối tượng:** Sinh viên, Giảng viên, Quản trị viên.
* **User stories:**

| STT | User Story |
| :--- | :--- |
| **S1** | Là một **Sinh viên**, tôi muốn **đăng nhập an toàn** bằng mã sinh viên và mật khẩu để **truy cập vào thông tin và chức năng cá nhân** của mình. |
| **S2** | Là một **Sinh viên**, tôi muốn **xem thời khóa biểu chi tiết** của mình theo chế độ xem Tuần hoặc Tháng để **biết rõ lịch học, phòng học, và giảng viên** của mỗi môn. |
| **S3** | Là một **Sinh viên**, tôi muốn **tra cứu bảng điểm tổng kết** của tất cả các kỳ học để **theo dõi chính xác kết quả học tập** và tính điểm trung bình tích lũy (GPA). |
| **S4** | Là một **Sinh viên**, tôi muốn **đăng ký các học phần** cho kỳ học sắp tới để **chủ động xây dựng kế hoạch học tập** cá nhân và đảm bảo đủ tín chỉ. |
| **S5** | Là một **Sinh viên**, tôi muốn **nhận và xem các thông báo tức thời** từ nhà trường/khoa để **không bỏ lỡ bất kỳ thông tin quan trọng** nào về học vụ, sự kiện, hoặc học bổng. |
| **S6** | Là một **Sinh viên**, tôi muốn **cập nhật thông tin cá nhân** (như số điện thoại, email) một cách dễ dàng để **đảm bảo thông tin liên lạc** với nhà trường luôn được chính xác. |
| **S7** | Là một **Sinh viên**, tôi muốn **tra cứu tình trạng học phí** của mình để **nắm rõ số tiền cần đóng** và tránh bị xử lý kỷ luật do chậm trễ. |
| **T1** | Là một **Giảng viên**, tôi muốn **xem danh sách chi tiết sinh viên** của các lớp tôi đang giảng dạy để **quản lý sĩ số** và tiện cho việc điểm danh. |
| **T2** | Là một **Giảng viên**, tôi muốn **nhập điểm thành phần và điểm cuối kỳ** cho sinh viên một cách trực quan để **hoàn tất việc đánh giá kết quả học tập** theo đúng thời hạn. |
| **T3** | Là một **Giảng viên**, tôi muốn **tải lên tài liệu học tập** (slide, đề cương) cho một học phần cụ thể để **hỗ trợ sinh viên ôn tập và học tập** hiệu quả hơn. |
| **A1** | Là một **Admin**, tôi muốn **quản lý (thêm, sửa, xóa) tài khoản và thông tin chi tiết của sinh viên** để **đảm bảo dữ liệu người dùng** luôn được cập nhật và chính xác. |
| **A2** | Là một **Admin**, tôi muốn **tạo và đăng tải các thông báo chung hoặc riêng theo nhóm đối tượng** để **truyền tải thông tin** một cách nhanh chóng và có mục tiêu. |
| **A3** | Là một **Admin**, tôi muốn **quản lý danh mục học phần và thời khóa biểu** của toàn trường để **tổ chức và sắp xếp kế hoạch đào tạo** cho từng học kỳ. |

* **Phân chia Mô-đun/Tính năng:** 
  * model
  * service
  * dao
  * controller
* **Phân công Chức năng:**
---

## 2. 🧱 Thiết kế Database (Database Design)

Thiết kế cơ sở dữ liệu là **nền tảng** cho một cổng thông tin. Nó cần được làm sớm để định hình dữ liệu và các mối quan hệ.

* **Vẽ Sơ đồ Quan hệ Thực thể (ERD - Entity-Relationship Diagram):** Thiết kế các bảng (SinhVien, MonHoc, DiemSo, LopHocPhan, v.v.) và xác định mối quan hệ giữa chúng (một-một, một-nhiều, nhiều-nhiều).
    * **

[Image of a basic Entity-Relationship Diagram for a Student Management System]
**
* **Xác định Trường Dữ liệu:** Đảm bảo các trường cần thiết (ví dụ: `MaSV`, `HoTen`, `NgaySinh`, `DiaChi`, `DiemA`, `DiemB`, v.v.) được xác định đúng kiểu dữ liệu và ràng buộc.

---

## 3. 🎨 Thiết kế Giao diện Người dùng & Prototype (UI/UX Design & Figma)

Thiết kế giao diện giúp hình dung sản phẩm và nhận phản hồi sớm.

* **Figma/Adobe XD:** Dùng công cụ này để tạo **Wireframes** (bố cục cơ bản) và **Mockups** (thiết kế chi tiết).
* **Prototype:** Tạo luồng tương tác cơ bản (ví dụ: nhấn nút Đăng nhập thì chuyển đến trang Thông tin cá nhân) để kiểm tra trải nghiệm người dùng (UX).
* **Lợi ích:** Có thiết kế trước giúp đội ngũ lập trình (Frontend & Backend) hiểu rõ họ cần xây dựng những gì.

---

## 4. ⚙️ Thiết kế Mô hình Tương tác (Sequence Diagram)

Sơ đồ tuần tự (Sequence Diagram) là hữu ích **sau khi bạn đã có chức năng cốt lõi và thiết kế database cơ bản**.

* **Khi nào cần dùng:** Chỉ cần tạo Sequence Diagram cho các **luồng tương tác phức tạp** hoặc **quan trọng** (ví dụ: Quy trình Đăng ký môn học, Quy trình Đăng nhập có xác thực nhiều bước, Tương tác giữa Frontend, Backend API và Database).
    * **

[Image of a Sequence Diagram showing user login process]
**
* **Mục đích:** Giúp 2 bạn hình dung rõ ràng **thứ tự** các đối tượng (User/Browser, Frontend, Backend API, Database) tương tác với nhau để hoàn thành một tác vụ.

---

## 5. 💻 Bắt đầu Triển khai (Implementation)

Bắt đầu code theo sự phân công đã thống nhất.

* **Backend:** Xây dựng API và triển khai Logic nghiệp vụ dựa trên Database đã thiết kế.
* **Frontend:** Xây dựng giao diện theo Figma và tích hợp với API Backend.
* **Công cụ Hỗ trợ:** Sử dụng **Git/GitHub** để quản lý mã nguồn, giúp 2 người làm việc đồng thời mà không bị xung đột code.

## 📌 Tóm tắt Thứ tự Ưu tiên:

1.  **Phân tích Yêu cầu** (Định hình **cái gì** cần làm).
2.  **Thiết kế Database (ERD)** (Định hình **dữ liệu** và cấu trúc lưu trữ).
3.  **Figma + Prototype** (Định hình **giao diện** và trải nghiệm).
4.  **Sequence Diagram** (Định hình **cách thức** tương tác cho các luồng phức tạp).
5.  **Code** (Triển khai).

Bạn muốn tôi **phân tích sâu hơn về các thực thể chính** (Entities) cho phần **Thiết kế Database** của Cổng thông tin sinh viên không?
