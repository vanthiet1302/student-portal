# Test Cases - Student & Lecturer Management

## Test Environment Setup

**URL Base**: `http://localhost:8080`
**Admin User**: Đã đăng nhập và có quyền admin

## Student Management Tests

### 1. List Students
**Test**: GET /admin/students

| # | Step | Expected | Status |
|---|------|----------|--------|
| 1 | Navigate to `/admin/students` | Hiển thị danh sách sinh viên | ✓ |
| 2 | Kiểm tra tiêu đề | Hiển thị "Danh sách sinh viên" | ✓ |
| 3 | Kiểm tra bảng | Có cột STT, Mã, Họ tên, Email, Lớp, Hành động | ✓ |
| 4 | Kiểm tra nút thêm | Có nút "Thêm sinh viên mới" | ✓ |
| 5 | Kiểm tra i18n | Đổi sang EN, tất cả text đổi sang Tiếng Anh | ✓ |

### 2. Add Student - Valid Data
**Test**: POST /admin/students/add

```
username: sv001
firstName: Văn
lastName: Anh
dob: 2005-01-15
pob: Hà Nội
gender: Nam
phoneNumber: 0912345678
citizenId: 123456789
nation: Kinh
religion: Không
nationality: Việt Nam
address: 123 Nguyễn Huệ, TP HCM
classId: K46-A
```

| # | Expected | Status |
|---|----------|--------|
| 1 | Form được submit thành công | ✓ |
| 2 | Redirect về danh sách | ✓ |
| 3 | Sinh viên mới xuất hiện trong danh sách | ✓ |
| 4 | Email tự động là `sv001@st.hcmuaf.edu.vn` | ✓ |

### 3. Add Student - Missing Username
**Test**: POST /admin/students/add (username trống)

| # | Expected | Status |
|---|----------|--------|
| 1 | Hiển thị lỗi "admin.student.add.error.usernameRequired" | ✓ |
| 2 | Form values được giữ lại | ✓ |
| 3 | Vẫn ở trang add | ✓ |

### 4. Add Student - Duplicate Username
**Test**: POST /admin/students/add (username đã tồn tại)

| # | Expected | Status |
|---|----------|--------|
| 1 | Hiển thị lỗi "admin.student.add.error.usernameTaken" | ✓ |
| 2 | Form values được giữ lại | ✓ |

### 5. Edit Student - Valid Data
**Test**: GET /admin/students/edit?userId=xxx

| # | Expected | Status |
|---|----------|--------|
| 1 | Form hiển thị với dữ liệu sinh viên | ✓ |
| 2 | Tiêu đề: "Chỉnh sửa sinh viên" | ✓ |
| 3 | Có nút "Lưu", "Quay lại", "Xóa" | ✓ |

**Test**: POST /admin/students/edit

```
userId: xxx
firstName: Hùng
lastName: Anh
email: new@email.com
...
```

| # | Expected | Status |
|---|----------|--------|
| 1 | Cập nhật thành công | ✓ |
| 2 | Redirect về danh sách | ✓ |
| 3 | Thông tin mới xuất hiện trong danh sách | ✓ |

### 6. Delete Student
**Test**: POST /admin/students/delete?userId=xxx

| # | Expected | Status |
|---|----------|--------|
| 1 | Click nút xóa | Hiển thị confirm dialog | ✓ |
| 2 | Click "OK" | Sinh viên bị xóa | ✓ |
| 3 | Redirect về danh sách | ✓ |
| 4 | Sinh viên không còn trong danh sách | ✓ |

## Lecturer Management Tests

### 1. List Lecturers
**Test**: GET /admin/lecturers

| # | Step | Expected | Status |
|---|------|----------|--------|
| 1 | Navigate to `/admin/lecturers` | Hiển thị danh sách giảng viên | ✓ |
| 2 | Kiểm tra tiêu đề | "Danh sách giảng viên" | ✓ |
| 3 | Kiểm tra bảng | Cột: STT, Mã, Họ tên, Email, Khoa, Hành động | ✓ |

### 2. Add Lecturer - Full Form
**Test**: POST /admin/lecturers/add

```
username: gv001
emailWork: gv001@university.edu.vn
emailPersonal: gv001@gmail.com
fullName: Nguyễn Văn Anh
birthYear: 1980
gender: Nam
identityCard: 123456789
academicRank: Phó Giáo sư
degree: Tiến sĩ
specialization: Công nghệ thông tin
position: Giảng viên
department: Khoa Công nghệ Thông tin
workAgency: Trường Đại học XYZ
agencyAddress: 123 Đường Lê Lợi, TP HCM
phoneFixed: (08) 1234 5678
phoneMobile: 0987654321
fax: (08) 1234 5679
bankAccountNumber: 1234567890
bankName: Ngân hàng A
bankBranch: Chi nhánh TP HCM
```

| # | Expected | Status |
|---|----------|--------|
| 1 | Form được submit thành công | ✓ |
| 2 | Redirect về danh sách | ✓ |
| 3 | Giảng viên mới xuất hiện trong danh sách | ✓ |

### 3. Add Lecturer - Missing Email
**Test**: POST /admin/lecturers/add (emailWork trống)

| # | Expected | Status |
|---|----------|--------|
| 1 | Hiển thị lỗi "admin.lecturer.add.error.emailRequired" | ✓ |
| 2 | Form values được giữ lại | ✓ |

### 4. Edit Lecturer
**Test**: GET/POST /admin/lecturers/edit?userId=xxx

| # | Expected | Status |
|---|----------|--------|
| 1 | Hiển thị form với dữ liệu giảng viên | ✓ |
| 2 | Có nút xóa giảng viên | ✓ |
| 3 | Cập nhật thành công | ✓ |

### 5. Delete Lecturer
**Test**: POST /admin/lecturers/delete?userId=xxx

| # | Expected | Status |
|---|----------|--------|
| 1 | Hiển thị confirm dialog | ✓ |
| 2 | Xóa thành công | ✓ |
| 3 | Redirect về danh sách | ✓ |

## i18n (Language) Tests

### 1. Vietnamese
**Test**: Chọn "Tiếng Việt"

| # | Element | Expected Vietnamese | Status |
|---|---------|---------------------|--------|
| 1 | Title | Danh sách sinh viên | ✓ |
| 2 | Button Add | Thêm sinh viên mới | ✓ |
| 3 | Table Header | STT, Mã sinh viên, Họ và tên, Email, Lớp, Hành động | ✓ |
| 4 | Delete confirm | Bạn có chắc chắn muốn xóa sinh viên này không? | ✓ |

### 2. English
**Test**: Chọn "English"

| # | Element | Expected English | Status |
|---|---------|-------------------|--------|
| 1 | Title | Student List | ✓ |
| 2 | Button Add | Add New Student | ✓ |
| 3 | Table Header | No., Student ID, Full Name, Email, Class, Action | ✓ |
| 4 | Delete confirm | Are you sure you want to delete this student? | ✓ |

### 3. Dynamic Language Switch
**Test**: Đổi ngôn ngữ không reload trang

| # | Action | Expected | Status |
|---|--------|----------|--------|
| 1 | Scroll để thấy form content | Nội dung không bị reset | ✓ |
| 2 | Click dropdown ngôn ngữ | Chọn ngôn ngữ khác | ✓ |
| 3 | Tất cả text thay đổi | Ngôn ngữ mới xuất hiện | ✓ |
| 4 | Trang không reload | Vị trí scroll giữ nguyên | ✓ |

### 4. Form Placeholder i18n
**Test**: Kiểm tra placeholder translations

```
Vietnamese:
- Username: "Nhập tên đăng nhập"
- Email: "Nhập email"

English:
- Username: "Enter username"
- Email: "Enter email"
```

| # | Field | Vietnamese | English | Status |
|---|-------|-----------|---------|--------|
| 1 | Username | ✓ | ✓ | ✓ |
| 2 | Email | ✓ | ✓ | ✓ |

## Validation Tests

### Student Validation
| Field | Requirement | Test Case | Expected | Status |
|-------|-------------|-----------|----------|--------|
| Username | Required | Empty | Error message | ✓ |
| Username | Unique | Duplicate | Error message | ✓ |
| FirstName | Required | Empty | Error on submit | ✓ |
| LastName | Required | Empty | Error on submit | ✓ |
| Gender | Valid | Nam/Nữ/Khác | Accept | ✓ |
| Date | Format | YYYY-MM-DD | Accept | ✓ |

### Lecturer Validation
| Field | Requirement | Test Case | Expected | Status |
|-------|-------------|-----------|----------|--------|
| Username | Required | Empty | Error message | ✓ |
| Username | Unique | Duplicate | Error message | ✓ |
| EmailWork | Required | Empty | Error message | ✓ |
| EmailWork | Unique | Duplicate | Error message | ✓ |
| FullName | Required | Empty | Error on submit | ✓ |
| BirthYear | Number | Text | Reject | ✓ |

## Session & Persistence Tests

### 1. Language Preference
| # | Action | Expected | Status |
|---|--------|----------|--------|
| 1 | Chọn ngôn ngữ "English" | Lưu vào session | ✓ |
| 2 | Navigate sang trang khác | Ngôn ngữ giữ nguyên | ✓ |
| 3 | Reload trang | Ngôn ngữ vẫn là English | ✓ |
| 4 | Đổi sang Vietnamese | Tất cả trang đổi thành Tiếng Việt | ✓ |

### 2. Form Values After Error
| # | Action | Expected | Status |
|---|--------|----------|--------|
| 1 | Điền form, submit với lỗi | Form values giữ nguyên | ✓ |
| 2 | Có error message | Hiển thị lỗi | ✓ |
| 3 | Sửa và submit lại | Thành công | ✓ |

## Performance Tests

### 1. Load Time
| Action | Target | Current |
|--------|--------|---------|
| List Students (100 records) | < 2s | ? |
| Search/Filter | < 1s | ? |
| Add Student | < 1s | ? |
| Edit Student | < 1s | ? |
| Delete Student | < 1s | ? |

### 2. Browser Compatibility
| Browser | Version | Tested | Status |
|---------|---------|--------|--------|
| Chrome | Latest | Yes | ✓ |
| Firefox | Latest | Yes | ? |
| Safari | Latest | Yes | ? |
| Edge | Latest | Yes | ? |

## Security Tests

### 1. SQL Injection
| Test | Payload | Expected | Status |
|------|---------|----------|--------|
| Username | `'; DROP TABLE Students; --` | Reject/Escape | ✓ |
| Email | `' OR '1'='1` | Reject/Escape | ✓ |

### 2. XSS Prevention
| Test | Payload | Expected | Status |
|------|---------|----------|--------|
| Name | `<script>alert('XSS')</script>` | Escape/Remove | ✓ |
| Email | `<img src=x onerror=alert('XSS')>` | Escape | ✓ |

### 3. CSRF Protection
| Test | Method | Expected | Status |
|------|--------|----------|--------|
| POST without token | Form submit | May fail if CSRF enabled | ? |

## Edge Cases

### 1. Empty Lists
| Test | Expected | Status |
|------|----------|--------|
| List students (no data) | "Không có sinh viên nào." | ✓ |
| List lecturers (no data) | "Không có giảng viên nào." | ✓ |

### 2. Long Names
| Test | Input | Expected | Status |
|------|-------|----------|--------|
| Long name (>100 chars) | "Nguyễn Văn Anh Đặng Xuân Hùng..." | Truncate or accept | ? |

### 3. Special Characters
| Test | Input | Expected | Status |
|------|-------|----------|--------|
| Name with accents | "Nguyễn Thái Hùng" | Accept | ✓ |
| Email with + | "user+test@example.com" | Accept | ✓ |

## Regression Tests

Chạy sau mỗi deployment:

1. ✓ Danh sách sinh viên hiển thị
2. ✓ Thêm sinh viên mới
3. ✓ Sửa thông tin sinh viên
4. ✓ Xóa sinh viên
5. ✓ Danh sách giảng viên hiển thị
6. ✓ Thêm giảng viên mới
7. ✓ Sửa thông tin giảng viên
8. ✓ Xóa giảng viên
9. ✓ Đổi ngôn ngữ Tiếng Việt
10. ✓ Đổi ngôn ngữ English
11. ✓ Form validation hoạt động
12. ✓ Error messages hiển thị

## Bug Report Template

```
Title: [Component] Brief Description

Browser/OS: Chrome 120 on Windows 10

Steps to Reproduce:
1. Go to /admin/students/add
2. Leave username empty
3. Click Submit

Expected Result:
Error message appears

Actual Result:
[Description of bug]

Screenshot/Video:
[Attach if possible]
```

