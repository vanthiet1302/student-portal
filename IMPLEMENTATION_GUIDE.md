# Hướng dẫn hoàn thiện Lecturer Management và i18n

## 📋 Danh sách các tệp được sửa/tạo

### ✅ Tệp đã sửa

1. **src/main/resources/i18n/messages_vi.properties**
   - Thêm 40+ i18n keys cho Student Management
   - Thêm 40+ i18n keys cho Lecturer Management

2. **src/main/resources/i18n/messages_en.properties**
   - Thêm 40+ i18n keys tiếng Anh

3. **src/main/webapp/WEB-INF/views/pages/admin/student/add.jsp**
   - Chuyển đổi text cứng thành i18n keys
   - Thêm data-i18n attributes
   - Sắp xếp form thành các section (Account, Personal, Academic)

4. **src/main/webapp/WEB-INF/views/pages/admin/student/edit.jsp**
   - Tương tự add.jsp nhưng cho chế độ edit
   - Có nút xóa sinh viên

5. **src/main/webapp/WEB-INF/views/pages/admin/student/list.jsp**
   - Sử dụng i18n keys cho tiêu đề, cột bảng, nút hành động
   - Hỗ trợ đổi ngôn ngữ động

6. **src/main/webapp/assets/images/js/i18n.js**
   - Nâng cấp hỗ trợ `data-i18n-placeholder`
   - Nâng cấp hỗ trợ `data-i18n-title`
   - Tự động lưu language preference

7. **src/main/java/dev/nlu/portal/api/I18nServlet.java**
   - Hỗ trợ tham số `lang` từ request
   - Fallback logic tốt hơn

8. **src/main/webapp/WEB-INF/views/pages/admin/lecturer/list.jsp**
   - Đã cập nhật

### ✅ Tệp đã tạo

1. **src/main/java/dev/nlu/portal/controller/command/admin/LecturerServlet.java**
   - Triển khai đầy đủ CRUD operations
   - Validation username, email
   - Error handling và keepFormValues

2. **src/main/webapp/WEB-INF/views/pages/admin/lecturer/add.jsp** (NEW)
   - Form thêm giảng viên với 6 sections
   - Hỗ trợ i18n

3. **src/main/webapp/WEB-INF/views/pages/admin/lecturer/edit.jsp** (NEW)
   - Form sửa giảng viên
   - Có nút xóa

4. **I18N_GUIDE.md** (NEW)
   - Hướng dẫn chi tiết sử dụng i18n
   - Danh sách tất cả i18n keys

5. **LECTURER_IMPLEMENTATION_SUMMARY.md** (NEW)
   - Tóm tắt các thay đổi
   - Chi tiết về LecturerServlet

## 🚀 Cách sử dụng

### Student Management
```
GET  /admin/students           → Danh sách
GET  /admin/students/add       → Form thêm
POST /admin/students/add       → Lưu thêm
GET  /admin/students/edit      → Form sửa
POST /admin/students/edit      → Lưu sửa
POST /admin/students/delete    → Xóa
```

### Lecturer Management
```
GET  /admin/lecturers          → Danh sách
GET  /admin/lecturers/add      → Form thêm
POST /admin/lecturers/add      → Lưu thêm
GET  /admin/lecturers/edit     → Form sửa
POST /admin/lecturers/edit     → Lưu sửa
POST /admin/lecturers/delete   → Xóa
```

### Language Switching
```
POST /lang?lang=vi             → Đổi thành Tiếng Việt
POST /lang?lang=en             → Đổi thành Tiếng Anh
GET  /api/i18n?lang=vi         → Lấy i18n JSON
```

## ✨ Tính năng

### Student Management
- ✅ Thêm sinh viên mới
- ✅ Sửa thông tin sinh viên
- ✅ Xóa sinh viên
- ✅ Danh sách sinh viên
- ✅ Validation: username, email
- ✅ Check duplicate username/email

### Lecturer Management
- ✅ Thêm giảng viên mới
- ✅ Sửa thông tin giảng viên
- ✅ Xóa giảng viên
- ✅ Danh sách giảng viên
- ✅ Validation: username, email
- ✅ 6 sections: Account, Personal, Academic, Work, Contact, Bank

### i18n (Internationalization)
- ✅ Hỗ trợ Tiếng Việt và Tiếng Anh
- ✅ Đổi ngôn ngữ động (không cần reload)
- ✅ Lưu preference vào session
- ✅ Support data-i18n attributes:
  - `data-i18n` - text content
  - `data-i18n-placeholder` - input placeholder
  - `data-i18n-title` - title attribute
  - `data-i18n-html` - HTML content

## 📝 Validation Rules

### Student
- **Username**: Bắt buộc, phải duy nhất
- **Email**: Tùy chọn nhưng phải duy nhất nếu có
- **First Name**: Bắt buộc
- **Last Name**: Bắt buộc
- Các trường khác: Tùy chọn

### Lecturer
- **Username**: Bắt buộc, phải duy nhất
- **Email công việc**: Bắt buộc, phải duy nhất
- **Full Name**: Bắt buộc
- Các trường khác: Tùy chọn

## 🔄 Flow

### Add Student/Lecturer
1. User truy cập `/admin/students/add` hoặc `/admin/lecturers/add`
2. Servlet forward đến VIEW_ADD (add.jsp)
3. User điền form và submit
4. Servlet xử lý POST request:
   - Validate dữ liệu
   - Check duplicate
   - Nếu có lỗi → keepFormValues() + forward về add.jsp
   - Nếu OK → gọi service.create() + redirect đến list
5. Nếu thành công → user thấy dòng mới trong danh sách

### Edit Student/Lecturer
1. User click nút "Edit" trên danh sách
2. Servlet GET request `/admin/students/edit?userId=xxx`
3. Servlet load student/lecturer từ database
4. Forward đến edit.jsp với dữ liệu
5. User sửa và submit
6. Servlet xử lý POST, validate, update
7. Redirect về danh sách

### Delete Student/Lecturer
1. User click nút "Delete"
2. Dialog confirm xuất hiện
3. Nếu OK → POST đến `/admin/students/delete`
4. Servlet delete và redirect

### Change Language
1. User click dropdown ngôn ngữ
2. JavaScript gọi `changeLanguage('vi')` hoặc `changeLanguage('en')`
3. Hàm fetch `/api/i18n?lang=xx`
4. Cập nhật tất cả `data-i18n*` elements
5. POST `/lang` để lưu preference

## 🎯 Các i18n Keys

### Tiêu đề
- `admin.title.student.*`
- `admin.title.lecturer.*`

### Form
- `form.student.*`
- `form.lecturer.*`

### List
- `admin.student.list.*`
- `admin.lecturer.list.*`

### Messages
- `admin.student.add.error.*`
- `admin.lecturer.add.error.*`
- `admin.student.success.*`
- `admin.lecturer.success.*`

Xem `I18N_GUIDE.md` để xem đầy đủ danh sách.

## 🛠️ Troubleshooting

### Text không đổi khi chọn ngôn ngữ
- Kiểm tra element có `data-i18n` attribute
- Kiểm tra key có trong messages_vi.properties/messages_en.properties
- Mở Developer Tools (F12) kiểm tra console

### Form không submit
- Kiểm tra validation lỗi gì
- Kiểm tra required fields có điền không
- Kiểm tra form action URL đúng không

### Duplicate error
- Username/Email đã tồn tại
- Cần sử dụng username/email khác

## 📚 Tài liệu thêm

- `I18N_GUIDE.md` - Hướng dẫn chi tiết i18n
- `LECTURER_IMPLEMENTATION_SUMMARY.md` - Tóm tắt implementation

## ✅ Checklist

- [x] Student management - CRUD operations
- [x] Lecturer management - CRUD operations
- [x] i18n support - Vietnamese + English
- [x] Dynamic language switching - No page reload
- [x] Form validation - Server side
- [x] Error handling - keepFormValues
- [x] Data i18n attributes - Multiple types
- [x] Documentation - Complete guides

## 🎉 Done!

Tất cả các tính năng đã được triển khai hoàn thiện!

