# Tóm tắt các thay đổi - i18n và Lecturer Management

## 1. Thêm các i18n keys vào messages properties

### messages_vi.properties
- Thêm các key cho Student Management (đã thực hiện trước)
- Thêm các key cho Lecturer Management:
  - `admin.title.lecturer.*` - Tiêu đề
  - `admin.lecturer.add.error.*` - Lỗi khi thêm
  - `admin.lecturer.*` - Nút, thông báo
  - `admin.lecturer.list.*` - Danh sách

### messages_en.properties
- Thêm các key tương tự cho Tiếng Anh

## 2. Cập nhật các file JSP Student

### Danh sách file đã cập nhật:
1. **add.jsp** - Form thêm sinh viên
   - Thay thế text cứng bằng `<fmt:message key="..."/>`
   - Thêm `data-i18n` attribute cho mỗi label/title
   - Hỗ trợ `data-i18n-placeholder` cho input

2. **edit.jsp** - Form sửa sinh viên
   - Cấu trúc tương tự add.jsp
   - Hỗ trợ thay đổi ngôn ngữ động

3. **list.jsp** - Danh sách sinh viên
   - Sử dụng i18n key cho các cột bảng
   - Sử dụng i18n key cho các nút hành động
   - Thay thế text "Quay lại" bằng i18n key

## 3. Triển khai LecturerServlet

### Tệp: `src/main/java/dev/nlu/portal/controller/command/admin/LecturerServlet.java`

**Tính năng:**
- ✅ doGet: Hỗ trợ GET request cho list, add, edit
- ✅ doPost: Hỗ trợ POST request cho add, edit, delete
- ✅ list() - Lấy danh sách giảng viên
- ✅ showEditForm() - Hiển thị form sửa
- ✅ handleAddLecturer() - Xử lý thêm giảng viên mới
  - Validation: username, email
  - Check duplicate username/email
  - Parse full name thành firstName/lastName
  - Tạo User & Lecturer object
  - Gọi service để lưu
- ✅ handleEditLecturer() - Xử lý sửa thông tin giảng viên
  - Validate email (nếu thay đổi)
  - Cập nhật User & Lecturer
- ✅ handleDeleteLecturer() - Xử lý xóa giảng viên
- ✅ keepFormValues() - Giữ lại giá trị form khi có lỗi
- ✅ setErrorAndForward() - Xử lý lỗi và forward
- ✅ Utility methods: trim(), parseInteger()

**Validation:**
- Username bắt buộc
- Email công việc bắt buộc
- Username phải duy nhất
- Email phải duy nhất

## 4. Tạo file JSP cho Lecturer Management

### add.jsp - Form thêm giảng viên
**Sections:**
- Thông tin tài khoản (Account Information)
  - Username (required, unique)
  - Email công việc (required, unique)
  - Email cá nhân (optional)

- Thông tin cá nhân (Personal Information)
  - Họ và tên (required)
  - Năm sinh
  - Giới tính
  - Số CMND/CCCD

- Thông tin học thuật (Academic Information)
  - Học hàm
  - Học vị
  - Chuyên ngành
  - Chức vụ

- Thông tin công tác (Work Information)
  - Khoa/Bộ môn
  - Cơ quan công tác
  - Địa chỉ cơ quan

- Thông tin liên hệ (Contact Information)
  - Điện thoại cố định
  - Điện thoại di động
  - Fax

- Thông tin ngân hàng (Bank Information)
  - Số tài khoản
  - Tên ngân hàng
  - Chi nhánh

### edit.jsp - Form sửa giảng viên
- Cấu trúc tương tự add.jsp
- Hỗ trợ thay đổi các trường (trừ username)
- Có nút xóa giảng viên (Delete button)

### list.jsp - Danh sách giảng viên
**Cột bảng:**
- STT
- Mã giảng viên (username)
- Họ và tên
- Email
- Khoa/Bộ môn
- Hành động (Sửa, Xóa)

**Features:**
- Link đến form thêm giảng viên
- Nút Sửa - chuyển đến edit page
- Nút Xóa - xóa với confirm dialog
- Thông báo khi không có giảng viên

## 5. Nâng cấp i18n JavaScript

### Tệp: `src/main/webapp/assets/images/js/i18n.js`

**Tính năng mới:**
- ✅ `data-i18n-placeholder` - Cập nhật placeholder attribute
- ✅ `data-i18n-title` - Cập nhật title attribute
- ✅ `data-i18n-value` - Cập nhật value attribute
- ✅ `data-i18n-html` - Cập nhật HTML content
- ✅ Tự động lưu language preference vào session
- ✅ Support AJAX request cho POST /lang

## 6. Cập nhật I18nServlet

### Tệp: `src/main/java/dev/nlu/portal/api/I18nServlet.java`

**Thay đổi:**
- Hỗ trợ parameter `lang` từ request: `/api/i18n?lang=en`
- Fallback về locale từ request attribute nếu không có parameter
- Fallback về `vi` nếu không có cả hai

## Các i18n keys mới cho Lecturer

### Tiêu đề
- `admin.title.lecturer` = Danh sách giảng viên
- `admin.title.lecturer.add` = Thêm giảng viên
- `admin.title.lecturer.list` = Danh sách giảng viên
- `admin.title.lecturer.edit` = Chỉnh sửa giảng viên

### Lỗi
- `admin.lecturer.add.error.usernameRequired` = Vui lòng nhập tên đăng nhập
- `admin.lecturer.add.error.emailRequired` = Vui lòng nhập email công việc
- `admin.lecturer.add.error.usernameTaken` = Tên đăng nhập đã tồn tại
- `admin.lecturer.add.error.emailTaken` = Email đã tồn tại

### Form Fields
- `form.lecturer.account.info` = Thông tin tài khoản
- `form.lecturer.username` = Tên đăng nhập
- `form.lecturer.emailWork` = Email công việc
- `form.lecturer.emailPersonal` = Email cá nhân
- `form.lecturer.personal.info` = Thông tin cá nhân
- `form.lecturer.fullName` = Họ và tên
- `form.lecturer.birthYear` = Năm sinh
- `form.lecturer.gender` = Giới tính
- `form.lecturer.gender.male` = Nam
- `form.lecturer.gender.female` = Nữ
- `form.lecturer.gender.other` = Khác
- `form.lecturer.identityCard` = Số CMND/CCCD
- `form.lecturer.academic.info` = Thông tin học thuật
- `form.lecturer.academicRank` = Học hàm
- `form.lecturer.degree` = Học vị
- `form.lecturer.specialization` = Chuyên ngành
- `form.lecturer.position` = Chức vụ
- `form.lecturer.work.info` = Thông tin công tác
- `form.lecturer.department` = Khoa/Bộ môn
- `form.lecturer.workAgency` = Cơ quan công tác
- `form.lecturer.agencyAddress` = Địa chỉ cơ quan
- `form.lecturer.contact.info` = Thông tin liên hệ
- `form.lecturer.phoneFixed` = Điện thoại cố định
- `form.lecturer.phoneMobile` = Điện thoại di động
- `form.lecturer.fax` = Fax
- `form.lecturer.bank.info` = Thông tin ngân hàng
- `form.lecturer.bankAccountNumber` = Số tài khoản
- `form.lecturer.bankName` = Tên ngân hàng
- `form.lecturer.bankBranch` = Chi nhánh

### Danh sách
- `admin.lecturer.list.number` = STT
- `admin.lecturer.list.id` = Mã giảng viên
- `admin.lecturer.list.fullName` = Họ và tên
- `admin.lecturer.list.email` = Email
- `admin.lecturer.list.department` = Khoa/Bộ môn
- `admin.lecturer.list.action` = Hành động
- `admin.lecturer.list.noData` = Không có giảng viên nào
- `admin.lecturer.list.addNew` = Thêm giảng viên mới
- `admin.lecturer.list.edit` = Sửa
- `admin.lecturer.list.delete` = Xóa
- `admin.lecturer.list.confirm` = Bạn có chắc chắn muốn xóa giảng viên này không?

### Actions
- `admin.lecturer.add` = Thêm giảng viên
- `admin.lecturer.back` = Quay lại
- `admin.lecturer.success.add` = Thêm giảng viên thành công!
- `admin.lecturer.success.edit` = Chỉnh sửa giảng viên thành công!
- `admin.lecturer.error` = Có lỗi xảy ra. Vui lòng thử lại.

## Hướng dẫn sử dụng

### Để sử dụng Student/Lecturer Management:
1. Đăng nhập vào admin panel
2. Chọn "Student Management" hoặc "Lecturer Management"
3. Click "Add New Student/Lecturer" để thêm
4. Điền form và submit
5. Edit hoặc Delete từ danh sách

### Để đổi ngôn ngữ:
1. Click nút ngôn ngữ ở navbar (biểu tượng dịch)
2. Chọn "Tiếng Việt" hoặc "English"
3. Trang sẽ cập nhật tự động (không cần reload)

## URLs
- Danh sách sinh viên: `/admin/students`
- Thêm sinh viên: `/admin/students/add`
- Sửa sinh viên: `/admin/students/edit?userId=xxx`
- Xóa sinh viên: `/admin/students/delete` (POST)

- Danh sách giảng viên: `/admin/lecturers`
- Thêm giảng viên: `/admin/lecturers/add`
- Sửa giảng viên: `/admin/lecturers/edit?userId=xxx`
- Xóa giảng viên: `/admin/lecturers/delete` (POST)

## Ghi chú
- Tất cả form có validation ở server side
- Email validation: kiểm tra format + uniqueness
- Username validation: required + unique
- Full Name được parse thành firstName + lastName (lấy từ cuối chuỗi)
- Khi có lỗi, form values được giữ lại (keepFormValues)
- Support đổi ngôn ngữ động (không cần reload)
- Tất cả messages đều có dịch tiếng Việt và tiếng Anh

