# API Documentation - Student & Lecturer Management

## StudentServlet

**URL Pattern**: `/admin/students/*`

### GET Endpoints

#### List Students
```
GET /admin/students
```
- **Description**: Lấy danh sách tất cả sinh viên
- **Parameters**: Không
- **Response**: Forward đến list.jsp với:
  - `students` - List<Student>
  - `title` - "admin.title.student.list"
  - `content` - "/WEB-INF/views/pages/admin/student/list.jsp"

#### Show Add Form
```
GET /admin/students/add
```
- **Description**: Hiển thị form thêm sinh viên
- **Parameters**: Không
- **Response**: Forward đến add.jsp với:
  - `title` - "admin.title.student.add"

#### Show Edit Form
```
GET /admin/students/edit?userId={userId}
```
- **Description**: Hiển thị form sửa sinh viên
- **Parameters**:
  - `userId` (required) - ID của sinh viên
- **Response**: Forward đến edit.jsp với:
  - `student` - Student object
  - `title` - "admin.title.student.edit"
- **Error**: Redirect đến `/admin/students` nếu không tìm thấy

### POST Endpoints

#### Create Student
```
POST /admin/students/add
Content-Type: application/x-www-form-urlencoded
```
- **Description**: Tạo sinh viên mới
- **Parameters**:
  - `username` (required) - Tên đăng nhập, phải duy nhất
  - `firstName` (required)
  - `lastName` (required)
  - `email` (auto) - `{username}@st.hcmuaf.edu.vn`
  - `dob` - Ngày sinh (YYYY-MM-DD)
  - `pob` - Nơi sinh
  - `gender` - Giới tính
  - `phoneNumber`
  - `citizenId` - Số CMND/CCCD
  - `nation` - Dân tộc
  - `religion` - Tôn giáo
  - `nationality` - Quốc tịch
  - `address` - Địa chỉ
  - `classId` - Mã lớp

- **Validation**:
  - Username: Bắt buộc
  - Username: Phải duy nhất (check existsByUsername)
  - FirstName: Bắt buộc
  - LastName: Bắt buộc

- **Success**: Redirect đến `/admin/students`

- **Error**: Forward lại add.jsp với:
  - `error` - Mã lỗi i18n (vd: "admin.student.add.error.usernameRequired")
  - Form values được giữ lại

#### Update Student
```
POST /admin/students/edit
```
- **Description**: Cập nhật thông tin sinh viên
- **Parameters**:
  - `userId` (required)
  - Các trường như Create

- **Validation**:
  - Email phải duy nhất (nếu thay đổi)

- **Success**: Redirect đến `/admin/students`

- **Error**: Forward lại edit.jsp với error message

#### Delete Student
```
POST /admin/students/delete
Content-Type: application/x-www-form-urlencoded
```
- **Description**: Xóa sinh viên
- **Parameters**:
  - `userId` (required)

- **Success**: Redirect đến `/admin/students`

- **Error**: Forward lại list.jsp với error message

## LecturerServlet

**URL Pattern**: `/admin/lecturers/*`

### GET Endpoints

#### List Lecturers
```
GET /admin/lecturers
```
- **Description**: Lấy danh sách tất cả giảng viên
- **Response**: Forward đến list.jsp với:
  - `lecturers` - List<Lecturer>
  - `title` - "admin.title.lecturer.list"

#### Show Add Form
```
GET /admin/lecturers/add
```
- **Description**: Hiển thị form thêm giảng viên
- **Response**: Forward đến add.jsp

#### Show Edit Form
```
GET /admin/lecturers/edit?userId={userId}
```
- **Description**: Hiển thị form sửa giảng viên
- **Parameters**:
  - `userId` (required)
- **Response**: Forward đến edit.jsp với lecturer data

### POST Endpoints

#### Create Lecturer
```
POST /admin/lecturers/add
```
- **Description**: Tạo giảng viên mới
- **Parameters**:
  - `username` (required) - Phải duy nhất
  - `emailWork` (required) - Phải duy nhất
  - `emailPersonal` (optional)
  - `fullName` (required) - Parse thành firstName/lastName
  - `birthYear` - Năm sinh (Integer)
  - `gender` - Giới tính (Nam/Nữ/Khác)
  - `identityCard` - Số CMND/CCCD
  - `academicRank` - Học hàm (GS/PGS/...)
  - `degree` - Học vị (Thạc sĩ/Tiến sĩ/...)
  - `specialization` - Chuyên ngành
  - `position` - Chức vụ
  - `department` - Khoa/Bộ môn
  - `workAgency` - Cơ quan công tác
  - `agencyAddress` - Địa chỉ cơ quan
  - `phoneFixed` - Điện thoại cố định
  - `phoneMobile` - Điện thoại di động
  - `fax` - Fax
  - `bankAccountNumber` - Số tài khoản
  - `bankName` - Tên ngân hàng
  - `bankBranch` - Chi nhánh

- **Validation**:
  - Username: Bắt buộc
  - emailWork: Bắt buộc
  - Username phải duy nhất
  - Email phải duy nhất

- **Success**: Redirect đến `/admin/lecturers`

- **Error**: Forward lại add.jsp với error message

#### Update Lecturer
```
POST /admin/lecturers/edit
```
- **Description**: Cập nhật thông tin giảng viên
- **Parameters**:
  - `userId` (required)
  - Các trường như Create

- **Validation**:
  - Email phải duy nhất (nếu thay đổi)

- **Success**: Redirect đến `/admin/lecturers`

#### Delete Lecturer
```
POST /admin/lecturers/delete
```
- **Description**: Xóa giảng viên
- **Parameters**:
  - `userId` (required)

- **Success**: Redirect đến `/admin/lecturers`

## Language Service API

### Get i18n Messages
```
GET /api/i18n?lang={lang}
```
- **Description**: Lấy tất cả i18n messages theo ngôn ngữ
- **Parameters**:
  - `lang` (optional) - "vi" hoặc "en" (mặc định: "vi")
- **Response**: JSON object
  ```json
  {
    "admin.title.student": "Danh sách sinh viên",
    "admin.student.add": "Thêm sinh viên",
    ...
  }
  ```

### Save Language Preference
```
POST /lang
Content-Type: application/x-www-form-urlencoded
```
- **Description**: Lưu ngôn ngữ được chọn vào session
- **Parameters**:
  - `lang` (required) - "vi" hoặc "en"
- **Response**:
  - Nếu AJAX: JSON `{"success":true,"lang":"vi"}`
  - Nếu form submit: Redirect lại referrer

## Error Codes (i18n keys)

### Student
- `admin.student.add.error.usernameRequired` - Vui lòng nhập tên đăng nhập
- `admin.student.add.error.usernameTaken` - Tên đăng nhập đã tồn tại
- `admin.student.add.error.emailTaken` - Email đã tồn tại

### Lecturer
- `admin.lecturer.add.error.usernameRequired` - Vui lòng nhập tên đăng nhập
- `admin.lecturer.add.error.emailRequired` - Vui lòng nhập email công việc
- `admin.lecturer.add.error.usernameTaken` - Tên đăng nhập đã tồn tại
- `admin.lecturer.add.error.emailTaken` - Email đã tồn tại

## HTTP Status Codes

- **200 OK**: Request thành công
- **302 Found**: Redirect (thành công hoặc có lỗi)
- **400 Bad Request**: Missing required parameters
- **404 Not Found**: Endpoint không tồn tại hoặc student/lecturer không tìm thấy
- **500 Internal Server Error**: Lỗi server

## Example Usage

### Create Student (cURL)
```bash
curl -X POST http://localhost:8080/admin/students/add \
  -d "username=sv001" \
  -d "firstName=Văn" \
  -d "lastName=Anh" \
  -d "classId=K46-A"
```

### Get i18n (JavaScript)
```javascript
fetch('/api/i18n?lang=en')
  .then(res => res.json())
  .then(data => {
    console.log(data['admin.title.student']); // "Student List"
  });
```

### Change Language (JavaScript)
```javascript
fetch('/lang', {
  method: 'POST',
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  body: 'lang=en'
});
```

## Data Models

### Student
```java
{
  userId: String,
  user: {
    username: String,
    email: String,
    firstName: String,
    lastName: String,
    enabled: boolean
  },
  dob: LocalDate,
  pob: String,
  gender: String,
  phoneNumber: String,
  citizenId: String,
  nation: String,
  religion: String,
  nationality: String,
  address: String,
  classId: String
}
```

### Lecturer
```java
{
  userId: String,
  user: {
    username: String,
    email: String,
    firstName: String,
    lastName: String,
    enabled: boolean
  },
  birthYear: Integer,
  gender: String,
  identityCard: String,
  academicRank: String,
  degree: String,
  specialization: String,
  position: String,
  department: String,
  workAgency: String,
  agencyAddress: String,
  phoneFixed: String,
  phoneMobile: String,
  fax: String,
  bankAccountNumber: String,
  bankName: String,
  bankBranch: String
}
```

## Session Attributes

- `lang` - Ngôn ngữ hiện tại ("vi" hoặc "en")
- `theme` - Theme hiện tại ("light", "dark", "auto")

## Form Submission

### Multipart
Không yêu cầu, sử dụng `application/x-www-form-urlencoded`

### CSRF
Nếu có CSRF protection, cần thêm CSRF token vào form

## Notes

1. **Full Name Parsing**: Khi tạo lecturer, full name được parse thành:
   - firstName = phần từ dấu cách cuối cùng đến cuối
   - lastName = từ đầu đến dấu cách cuối cùng

2. **Email Auto-generation**: Sinh viên email được tự động tạo thành `{username}@st.hcmuaf.edu.vn`

3. **Password**: Mật khẩu mặc định = username (được hash bằng bcrypt)

4. **Role**: Mặc định là `STUDENT` hoặc `LECTURER`

5. **Error Handling**: Tất cả lỗi được forward lại form với form values giữ nguyên

6. **Transaction**: Create/Update được wrapped trong transaction để đảm bảo data consistency

