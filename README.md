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

* **ERD Portal**

![Database](http://www.plantuml.com/plantuml/png/jLTHRzn447w_Np6wXnOGGIhm59MgLvzxCKtkYkshH5-ghRsDjx6zr_KwD6qgqLQY4WY9qhw0GBHKg0XHWO2dd2eU7FL_-9ymNj_6MJUc2YbvEDapuvdvPcQ_sPpE914oJsAmtI5OF5q7FrA_B0MFfH5SO3JjzOwZc9KxVqk8ovrdCzXWvVnk3jcBnx1xlvm_K9zl5ezWetWE4IhlvH14nOyfNClBdSUzY8KK0YQ2cFQo3PREY209n2obScj6WGiPyIhA1__j3xyyyGwCLue7Qp0UlVYjd7_dmB3uPZo2O-2NyuTWhPJpdwqZHEZHL3Av1VsfPxjz81bCuMOFu2t8CogS49OXhWftykJvSwTEeM9f2NzcWYL4RDa9OJ5QR18HH4IWqJHbrtAg36SaopxX8bmXMTJOeE8A4vaSauGQqfWS80oOP3TeweHTGGIFgQ4xUsuPN1GgBSi6WMIRbSLbZaAIeZ3ab_4z99BsRZNmtScghU2xDNpbsSodLOEGPe5WCybusjZiTpcqpmzSVyqU-yhnq5vu3YcEYKpGhAkyQRiECnnUKqBDqYl74INXvCfmp_tKxA2gZKz94561iVPbwji3QsNHVd-HfQmDkr9ipuVkojjGHQaYesqtsigeILIPae24D661Iz8D0nZc6BVAmJFMNNThTN3nei9YhIx0134EOnSKfRHuIBk6x10W4dALYrPRIBZ9CYx-lHi0Yh8-fgJwdALoFoBFdmxr67ar93JFGzGVkJyAVJilAoOtRYW3cWeUnqrQwl3pTQGv0oKcB_FCxCTn-6Ww60Cor6SlizU5WGE-MtmDRmppBP2Y-1LaHBcYtTjm7Tdr9mbhosCbk0jnnTaHHywLR_QciBszjCHlWey1-t7ngD-8F2t2y2EazYVAmHTfj0md56m8YZyn53fLJsd4C62kE0Ezt48lszlWLbvGZN1ObTmVVGEEmRr3sqYdMWnFKeYBv_r6XPFOrlbAjNqJq0XLKhpulPp_W1tOY3XfXVESyGfSS8hFnx04g-Nys_CzTAWHwropgaQRhFWbtVEk0mU8zscDzLw2pe-qajxLAsdtg-BJ2TPYvz7aTMmWRthcGzkgfnoVzPpdbw_Ie6lEjRhx-6gJ0vQBe26JMJtNXpZc1jTCfgvdrsGpqMJ3Sv7HZeGDH4O8hnQZfKc_tOH14vf9QjADPb4yiQOY8TUzcZ8Qn1dw2lCMkGhEamvYdRgkOqrNfsiACBvgnBaGBCZZFEb2tUR5Gv6gv9B4bj6j0xEwDBJzWREganikicCTENNbhGXmJuFshswQsGzTNePR9PdbCPB_LdC5QL6ZSOeQmi03O3869CLVUB0hkUQKIMMH86i07kyl0vX5vSvJjF5DY_MA1Z7Z4Dbpgz_3ocXzLIJD7a74GAgxi154co2bgWpgK4UbaFSQ2jczW_lWNF4z7CCNfvpVMJk2vzQvlEJQ8yVptO7lJCRrMEnTx-WwowGWrJMYOsYDesmCXvAYdRc7pS6enSgeZaN5a4Ywk7fAbj2EjQaXZDp1i6QMaKuQrsv8_-ziSRq7DCkyW0kLBGrOGc9rRqz9_B9OSdcGEAOI8O-gZBkl1GUstzkRFRM6T-_ltgtMOhKqhKbRLstD9m7iCsg-7-arZcg62pzH3hgKHvtfz_LC-ol5GmHrfhZzMjRYUE8xPnshcU5Ai1Y8b4jsXGNTKtpWzJdWgKJlA9FqkgpdWgOXVUcAQbmL9H7hLBgl-8VBfIJiv3gZDFhAfl0edYhdpnXsuJJcaYVnFm00)

* **Trường Dữ liệu:**

**NGƯỜI DÙNG**  
**(User: userId long, primaryEmail varchar, passwordHash varchar, firstName varchar, lastName varchar, phoneNumber varchar, citizenId varchar, nation varchar, religion varchar, pob varchar, nationality varchar, secondaryEmail varchar, address varchar, role varchar, isMale boolean, isActive boolean, dob date, createAt datetime, updateAt datetime)**  
**Mô tả:** Mỗi người dùng trong hệ thống (sinh viên, giảng viên, admin) đều có thông tin chung: mã người dùng duy nhất (userId), email chính dùng đăng nhập (primaryEmail), họ tên, số điện thoại, căn cước công dân, dân tộc, tôn giáo, nơi sinh, quốc tịch, địa chỉ, giới tính, trạng thái hoạt động, ngày sinh, thời gian tạo và cập nhật.

**VAI TRÒ**  
**(Role: roleName varchar)**  
**Mô tả:** Bảng lưu các vai trò trong hệ thống: ADMIN (quản trị viên), LECTURER (giảng viên), STUDENT (sinh viên). Mỗi người dùng chỉ có một vai trò duy nhất.

**KHOA**  
**(Department: departmentId long, name varchar, establishDate date, phone varchar, email varchar, headOfDepartmentUserId long)**  
**Mô tả:** Mỗi khoa có mã khoa duy nhất (departmentId), tên khoa, ngày thành lập, số điện thoại, email liên hệ và trưởng khoa (headOfDepartmentUserId) là một giảng viên (FK → User.userId).

**GIẢNG VIÊN**  
**(Teacher: teacherId long, userId long, departmentId long, academicRank varchar, title varchar, position varchar, startDate date)**  
**Mô tả:** Mỗi giảng viên có mã giảng viên (teacherId), liên kết với một người dùng (userId → User.userId), thuộc một khoa (departmentId → Department.departmentId), học hàm (GS, PGS), học vị (TS, ThS), chức vụ (Trưởng bộ môn, Phó trưởng khoa…), ngày bắt đầu công tác.

**SINH VIÊN**  
**(Student: studentId long, userId long, classId long, enrollYear int, program varchar, status varchar)**  
**Mô tả:** Mỗi sinh viên có mã sinh viên (studentId), liên kết với một người dùng (userId → User.userId), thuộc một lớp (classId → Clazz.classId), năm nhập học, chương trình đào tạo (chính quy, chất lượng cao, tiên tiến…), trạng thái (đang học, bảo lưu, thôi học…).

**LỚP HỌC**  
**(Clazz: classId long, classCode varchar, departmentId long, major varchar, advisorUserId long, startYear int, expectedGradYear int)**  
**Mô tả:** Mỗi lớp hành chính có mã lớp duy nhất (classId), mã lớp (VD: D21CQCN01), thuộc khoa (departmentId), ngành học, chủ nhiệm lớp (advisorUserId → User.userId là giảng viên), năm bắt đầu và dự kiến tốt nghiệp.

**MÔN HỌC**  
**(Subject: subjectId long, subjectCode varchar, subjectName varchar, credits int, theoryHours int, practiceHours int, departmentId long, isCompulsory boolean)**  
**Mô tả:** Mỗi môn học có mã môn (subjectId), mã môn học (VD: INT1234), tên môn, số tín chỉ, số giờ lý thuyết/thực hành, thuộc khoa quản lý (departmentId), bắt buộc hay tự chọn.

**HỌC PHẦN** (Lớp môn học mở trong một học kỳ)  
**(Course: courseId long, subjectId long, teacherId long, semester varchar, academicYear varchar, maxStudents int, currentStudents int, schedule varchar, room varchar, status varchar)**  
**Mô tả:** Một học phần là một lớp môn học cụ thể được mở trong một học kỳ: liên kết với môn học (subjectId → Subject), giảng viên dạy (teacherId → Teacher), học kỳ (HK1, HK2, Hè), năm học (2024-2025), số lượng SV tối đa, số SV hiện tại, lịch học, phòng học, trạng thái (đang mở, đã đóng…).

**ĐĂNG KÝ HỌC PHẦN**  
**(Course_Registration: registrationId long, courseId long, studentId long, registerDate datetime, status varchar, note varchar)**  
**Mô tả:** Bảng trung gian quản lý việc sinh viên đăng ký học phần: mỗi bản ghi là một lần đăng ký, liên kết học phần (courseId) và sinh viên (studentId), ngày đăng ký, trạng thái (đã đăng ký, đã hủy, chờ duyệt…).

**ĐIỂM**  
**(Grade: gradeId long, courseId long, studentId long, processScore decimal, finalScore decimal, totalScore decimal, letterGrade varchar, gpaScore decimal, note varchar)**  
**Mô tả:** Bảng lưu điểm của sinh viên theo từng học phần: điểm quá trình, điểm thi, điểm tổng (thang 10), điểm chữ (A, B+, …), điểm hệ 4, ghi chú (thi lại, cải thiện…).

**CHƯƠNG TRÌNH ĐÀO TẠO**  
**(Curriculum: curriculumId long, major varchar, departmentId long, totalCredits int, startYear int, endYear int)**  
**Mô tả:** Mỗi chương trình đào tạo của một ngành (ví dụ: CNTT 2021-2025), tổng số tín chỉ bắt buộc, thuộc khoa nào, áp dụng từ năm nào đến năm nào.

**CHI TIẾT CHƯƠNG TRÌNH ĐÀO TẠO**  
**(Curriculum_Detail: id long, curriculumId long, subjectId long, semester int, isCompulsory boolean)**  
**Mô tả:** Quy định môn học nào thuộc học kỳ nào trong chương trình đào tạo, bắt buộc hay tự chọn.

**THÔNG BÁO**  
**(Notification: notificationId long, title varchar, content text, senderUserId long, targetRole varchar, createAt datetime, isRead boolean)**  
**Mô tả:** Hệ thống thông báo: gửi từ người dùng nào, dành cho vai trò nào (toàn bộ sinh viên, giảng viên khoa X…), có đọc chưa.

**QUAN HỆ:**

**One to one:**  
- User → Student 
- User → Teacher

**One to many:**  
- Department → Teacher (một khoa có nhiều giảng viên)  
- Department → Clazz (một khoa quản lý nhiều lớp)  
- Department → Subject (một khoa quản lý nhiều môn học)  
- Teacher → Course (một giảng viên dạy nhiều học phần)  
- Clazz → Student (một lớp có nhiều sinh viên)  
- Subject → Course (một môn học có thể mở nhiều học phần qua các kỳ)  
- Course → Course_Registration (một học phần có nhiều sinh viên đăng ký)  
- Student → Course_Registration (một sinh viên đăng ký nhiều học phần)  
- Student → Grade (một sinh viên có nhiều bản ghi điểm)  
- Course → Grade (một học phần có nhiều điểm của các sinh viên)  
- Curriculum → Curriculum_Detail (một CTĐT có nhiều môn học theo kỳ)  
- User → Notification (một người gửi nhiều thông báo)
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
