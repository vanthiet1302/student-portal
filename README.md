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

## 4. ⚙️ Mô hình Tương tác (Sequence Diagram)
* **User Login Flow**
![User Login Flow](http://www.plantuml.com/plantuml/png/ZL8_ZzCm5D_zAPuwfT8TtHdGwTG1489JsvDO7VjTuvDh1_lbqCsCJ4oiT41C34X8IEd0KBw8lmdF8QaQAi5biUFVd_VxMRw890Cr6oka8X_WEc8GfM-Sbk4U9Y_yshX7jx6UW8pmV7abb7SKlEsX9OOxYzIX_LxKR6YKgQKZc6I_31Y51nBlXPOaInaH9lD-ry7pIo6o1CwVv6dm65vMgVrQGyE7JcvmLiiOt_gW1SFC6gOoy-hLSWKpcsD1CV3FO11CSqDp9maFIVkLROPSR2CRgj1n0sRcAF-todtwbQsu0xlS6AUp-lB-kfSVV8xKN9VPvwFvNNrVtg8YA2gVsXyAN6FjL6Wy5Wff2LxljmgeIhjlUTb_SXMe_NUt5i3V8TFecYWqsC4QJreCgimSYLQftNh-Zz5u1qMNyTdy31RUudGiOrs6MRH0RKAkyUj3Qdywc5L-Wr2iWdHhK1pworIWZVXNYzH-9YXveFb7YnjfumDhz0zfWMyQZ0Hp4sj9gkA8nLEJTkzSD_ezsBJxQBeoMN5SP4IZAbymbFkj_oCHwBJGUFAwBlYysTZV)

* **View & Update Profile Flow**

![View & Update Profile Flow](http://www.plantuml.com/plantuml/png/jLEnZjD04Etz5QDKEKICe3m9T9V4nu4E4iKEq6xilNXFpjhipbwKceA2YZ-0WWgTQAZYWkBu4Vy9itOSROnGAb9aHplpvisRjpxJo1IQLGPl15y3amdCYuGXXwdAhqN6uIBBrvx7OimLp3LNNfnBL7a6lLr5oDLjnh47JCCkzWfgAc9HC8dGiwZn-QGkeDEZzcp1D8VUU1SrgQ6to8rCcDhiU_itkgYJByEfvorLRWfuxCCxle8elVyfbu12Glzv49ruG3zB1ODdxHnm2fI2HyLEI_-gsdxR0EwX3Oh4YLlItCBiPHiVTclmbkN56B0glrDEQv7BBeHqKlcIOosU8nEPxXkBIpgSL6alx5a3XuGBWwjW5C43k9XDNZMYtbu6im14yjJt_QP-28CzaSC2ayKDZniD2NTfkZFIJWawu-yDrmXZeGk6SKfS_L5gDbNvGK9IbJyW4rNvqPou7RlBigreccRKKxWKDHhJglm4rxbQjQ_9up9fVNpYmwZQtXKWKphmmD3f9EmusWmRLkKTS_nhoGzT8hHh-ck-tktcFzjjwY_dRySVWZl2KVVdq_5v53JAmo02Slo8_Q69OneCGcHez77h7ObHAghjBuJCMj5kTd7_DRVn5vb2R6zSl6lqZ2T2yHZXzsVg8fsbMmy6RNfWly8fG-HA_lCTsQTnHdzcbVq1)

* **Course Registration Flow**

![Course Registration Flow](http://www.plantuml.com/plantuml/png/bPF1RjCm6CVlVefFFRLYOo2nImLeMrj4qRPMJJWZrx6QqDIfR6UYGXpG3XmGqZXnuCA4q5u02QaHuj2-IDw4p-dNaIWQqtg8NVlt_zl_p_Q1iLpRT1OpBcoYmRDf89LbuoHL0TSBQ8pa939MwlklpRm1tC0BRyX4egnEuksivZPAb2VrMInj0L6VpT4z4j6SArk78o49BlfLk9EasiZkuQ10i6K1jtpCZOH6btgRgIF6QDkm-zHj3zhG2VFiauADIRyBwyzvTexVzRcQm7Jrco7cQDedAeO3puSzJMcXQJQM_M07nCObQBaYHMVSokjyr3ehIe5MSvq8QKmvxcrsL0567UU7aT5BX59CJ_WRIcYQTRKZcQETw0WrNk-ur_51f5gZPAlSWTa_6tWs6fnGUHYATgyNV4koDZpQBoHjs7_mZWMoiXpZiONJVFadBKfxXTyepzwdCCNcWu8ckS1ZPz5YWBzwDYtHx_OoEN5bTJTG3jm_zNeZ7nj_G9bUbLrh_CjXzz3l4OhbgjKAdjH6xi73mgTIB2yLxgIBYMg5geahDwsnljXUGx3XwfiAGQn-gad3QOYhNELqWPTN8Uie6AykauhEoHnRB4FxHPr_4ovZ8-diR9WlVrWOvylB29h7UVPrxivpURM0CCcNlmJ-MtqllPlM_tALzNlhYuhmBk7YFFiItP3gUKHuddqaKAg0iK3M7bCWQy_r0Dbq5ly5)

* **Teacher Grade Entry Flow**

![Teacher Grade Entry Flow](http://www.plantuml.com/plantuml/png/ZLN1Rjj64BtpAmOwIO0Y8Jq6JHhReXN7hYI8D7fUBRVY9jIIsLsguB78iIZGd7gjK1G5sWOea9waGmyC-X_yayukjO8NPjqSJAztPjwydNapVguqaRhSP10pGbCcOIf9mY0KMbPmcUNV106XEfVE7j0SJNa6FUje_I8cjndJFI0AZkUWG5XEUK64FheQ2wVCUPbpXzVaR6uzy7UG44rMH37eJOwdrdGUhF9I94HMTrcCNgd2sbz6Yo1OoxmiuF48ucR_CuS94IceUaTJw4_3U100mEd9Zvuvsl04q0hZjK5LOvgNKh4lUFBquh6Dm5BmBT7C-PjBT_PXRfhzBvNBS3K1tHn-HxDIF1TTKLWDZDgIZ2U8VqOfklPFuKEui5Ik9WEFXmjrYERi8rxpvl1s0reIc78iXLZ3bjU_2Q3r1tXJLitXMm59izzLa3Q7xoaKQRF_LO2eVwhWTLh_9TO-kFTABKis_ffagcMGi4vYBULCmwpP_rrwcH6W0jhitnVMoI-C4G4cMJCTwJ9XGjzmfS-hjXHzlo9ULJ3ENPgpXJf7f2Yy2IzYY4POKVpEo8ODOJgAQ2uPN2xdNy8n4KJmSdurQuS1FKOZXsU07fse6KAV7sEixxEJNy9yElTVXpf5Y2LxKpAbOS9LGRJLO_yYDULvAphm1nq97AWp6MHBqWdy2RpW5azZZx-3LQk3Z--iDcmKmnQsxJas_MwN_fEy3virXnyvzAxzUB_BlV-ZpqGIu8yRuCz6SFFFdoLy_A7LSNynZ-vFRqjcbfeCxbcOlDvXvleFaOvfsHpUSNZT73u4ha2Ua0tiQUmLsJ8uwMlOTi6AH1thtqczSw8BcPjHRZTWkTfmkm4-6VpXuOxhzo85dMkIMOGXO7xDfCqsX7L1xBKFuBHhNZcNjuNPDpP0zIqNUvwlNZ6gJuny9Vj3TRk8mcKC_Tl5v2mEnrSpy-V0ZGvyzI9SXa3RzoPFSPJWR3O1rOgwlGdk3LPKsWqulYGyA-qBEYUgUojTeuurzXzb_j1QwjpRSSfjT6Qrf5ijh-fTxeTumxLa2PUCQfGUnXgv4mnAxlpBMIodi22cDM8GTAlVgluvVifDzYy0)

* **Internal Notification Flow**

![Internal Notification Flow](http://www.plantuml.com/plantuml/png/bLCnRzD05DxlLpnvIgKqsHbGqwQ5q2gfwXInNiv7Sktb7CxFGHbH1mOc9bQg2Z5bG3350uFv8_TFU1UxnYV4G9QxsD_xtlU-x_aeGsunNseO6PJMS0tZ5DLh9JYgrC2PJjyonWMc5c9f4cc1Pt2TISjcQMuIRZSGjIlEKhlitMIhoEDUn9TCf0PjgaDKBErQIzo3wZjRaH0br8eR_1kiX6p0TFytU3YOx85qieGZd_5CGZIiRzMhOyRy173ur2k49p1UkDrs1LE5MdRXX1HBWrsOSZkNU9LgoGZduRLMAhcSn5FecrRdVaR-G6SeaIkT7NZJr9gZR0Qipv3dmks-R6fhlQZHi0klodi1k735Dm5lyeqhtXcOksAheCEJfJ9zBGNcLje3YaOZZDtkPmwY_1wY6T0lREgzewR2IXBMjgtZ5LJTcm5yUJ045JUST0yvpvKhxfQ0bWEMMxC0ql-m0ksAzo1SyPMtxGnPV5Z4aF5rgAdzPpAxeQ53KRxEF_3ddkQOM4RZ-FHgIiTq4gvo1wjunMEysH_JaovWewvR-pF00tWvkBW-ZQ7Jw_MgtjJZi57SvXyDz-yJ6QWB1Ws2Zvxj4yL5-PdC4kKFCuy2Xj3qfh9PsfE53_lE48KlXbbvdmPqJEfCrijnMs5QE_8Vf9JX9mNHUVNerqTAyv4bOcGY2oLxnkg38LflB_4-LpMhApx0BKb2Od2x1tP4zVbI_mO0)

* **Curriculum Lookup Flow**

![Curriculum Lookup Flow](http://www.plantuml.com/plantuml/png/RPB1RXCn48RlVefHJmbI6-WHqLAocqAgj47PHQAdol5QMQURTR37AJbpu82GoXkqGfm0WSG1AIl48L7VOzy4EmbPHt3QjMU-cNz-pv56gj2CCWYCKeAPp8oW8-Mb6SDn9gy8eGobWXUQAy9aZafci5FbHbnDCeuxG3Mi_ycOAXHCZ6cEVchufBjCir-IKAHzgZdiXEk_LQX9-jBa2LLJdzmRwl4oVX8z9yH9WTt3l-tW8JnjnT1Wc_J6HF2hno8v22w2ipZUlx__m4qX9XJvHkJwkrseKyu-JJqdsY7KKbdEVZ8ufKEf6kUSgZhnO3UD1GSSlK4vKf7fcb-dNYbmyplAmKtBHgrEAuZX7lYNnxtkgVyamHPoqcsVUT6BLKS8GkZQ-psHm84zxB5AGF8l7vd-a3E4o48kMw_ERGT7rMaBxFR2LW-QvyjyFk8QkI8sihjneiUroJ3Y2BKW5O2YdFr62EB5TGoOSWbO5f_p0QHbyPx1PLdyg9E4Rvdp_xTvMHPV8Pl_yZfWEh_9K-W8ZO_ybLpfEeHQHmu4WrIKnPiSqBPyL_UAktLQAdvbB02Xq6EABENAIJVJ9PIKnJV8R86t1XOVvX_XzLf6fTfRAbVGMU5Mr5P-vZM68BtxVdTh9qSr_s9LBwxdDywDsQrS6SFiyXaNdR56vGuXHpnFp2Zx0m00)

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
