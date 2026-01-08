# Hướng dẫn sử dụng i18n (Quốc tế hóa) trong StudentPortal

## Tổng quan

Ứng dụng StudentPortal hỗ trợ đầy đủ quốc tế hóa (i18n) với hai ngôn ngữ: Tiếng Việt (vi) và Tiếng Anh (en).

## Cấu trúc i18n

### 1. File Properties
- **File Tiếng Việt**: `src/main/resources/i18n/messages_vi.properties`
- **File Tiếng Anh**: `src/main/resources/i18n/messages_en.properties`

Cấu trúc key i18n:
```
##<scope>.<module>.<component>.<action>
# Ví dụ: admin.student.list.edit
```

### 2. Cơ chế Backend

#### LocaleFilter
- Tệp: `src/main/java/dev/nlu/portal/filter/LocaleFilter.java`
- Vai trò: Tự động thiết lập ngôn ngữ cho mỗi request
- Thứ tự ưu tiên:
  1. Parameter `lang` từ URL
  2. Attribute `lang` từ Session
  3. Mặc định: `vi` (Tiếng Việt)

#### I18nServlet & LangServlet
- **I18nServlet** (`/api/i18n`): Trả về JSON chứa tất cả i18n key cho một ngôn ngữ
- **LangServlet** (`/lang`): Lưu ngôn ngữ được chọn vào session

### 3. Cơ chế Frontend

#### Script Chính
- Tệp: `src/main/webapp/assets/images/js/i18n.js`
- Hàm: `changeLanguage(lang)`
- Tính năng:
  - Gửi request đến `/api/i18n?lang=xx` để lấy dữ liệu i18n
  - Cập nhật nội dung, placeholder, title của các phần tử
  - Lưu preference vào session

## Cách sử dụng i18n trong JSP

### 1. Thiết lập cơ bản trong mỗi JSP

```jsp
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>
```

### 2. Hiển thị text có dịch

#### Cách 1: Chỉ sử dụng fmt:message (cho phần tử không cần thay đổi động)
```jsp
<label><fmt:message key="form.student.firstName"/></label>
```

#### Cách 2: Kết hợp fmt:message + data-i18n (khuyến nghị)
```jsp
<label data-i18n="form.student.firstName">
    <fmt:message key="form.student.firstName"/>
</label>
```

**Lợi ích**: 
- Hiển thị text ban đầu bằng `fmt:message`
- Khi người dùng thay đổi ngôn ngữ, JavaScript sẽ cập nhật `data-i18n` mà không cần reload trang

### 3. Các loại data-i18n khác

#### data-i18n (text content)
```jsp
<h1 data-i18n="page.title"><fmt:message key="page.title"/></h1>
```

#### data-i18n-placeholder (cho input placeholders)
```jsp
<input type="text" 
       data-i18n-placeholder="form.student.firstName.placeholder"
       placeholder="<fmt:message key='form.student.firstName.placeholder'/>">
```

#### data-i18n-title (cho title attribute)
```jsp
<button data-i18n-title="button.save.title"
        title="<fmt:message key='button.save.title'/>">
    Save
</button>
```

#### data-i18n-html (cho HTML content)
```jsp
<div data-i18n-html="page.welcome.message">
    <fmt:message key="page.welcome.message"/>
</div>
```

## Danh sách i18n Key cho Student Management

### Tiêu đề
- `admin.title.student` - Danh sách sinh viên
- `admin.title.student.add` - Thêm sinh viên
- `admin.title.student.list` - Danh sách sinh viên
- `admin.title.student.edit` - Chỉnh sửa sinh viên

### Lỗi
- `admin.student.add.error.usernameRequired` - Vui lòng nhập tên đăng nhập
- `admin.student.add.error.usernameTaken` - Tên đăng nhập đã tồn tại
- `admin.student.add.error.emailTaken` - Email đã tồn tại

### Form Fields

#### Thông tin tài khoản
- `form.student.account.info` - Thông tin tài khoản
- `form.student.username` - Tên đăng nhập
- `form.student.email` - Email

#### Thông tin cá nhân
- `form.student.personal.info` - Thông tin cá nhân
- `form.student.lastName` - Họ
- `form.student.firstName` - Tên
- `form.student.dob` - Ngày sinh
- `form.student.pob` - Nơi sinh
- `form.student.gender` - Giới tính
- `form.student.gender.male` - Nam
- `form.student.gender.female` - Nữ
- `form.student.gender.other` - Khác
- `form.student.gender.select` - Chọn giới tính
- `form.student.phoneNumber` - Số điện thoại
- `form.student.citizenId` - Số CMND/CCCD
- `form.student.nation` - Dân tộc
- `form.student.religion` - Tôn giáo
- `form.student.nationality` - Quốc tịch
- `form.student.address` - Địa chỉ

#### Thông tin học tập
- `form.student.academic.info` - Thông tin học tập
- `form.student.classId` - Lớp

### Danh sách (List)
- `admin.student.list.number` - STT
- `admin.student.list.id` - Mã sinh viên
- `admin.student.list.fullName` - Họ và tên
- `admin.student.list.email` - Email
- `admin.student.list.classId` - Lớp
- `admin.student.list.phoneNumber` - Số điện thoại
- `admin.student.list.action` - Hành động
- `admin.student.list.noData` - Không có sinh viên nào
- `admin.student.list.addNew` - Thêm sinh viên mới
- `admin.student.list.edit` - Sửa
- `admin.student.list.delete` - Xóa
- `admin.student.list.confirm` - Bạn có chắc chắn muốn xóa sinh viên này không?

### Nút và Actions
- `admin.student.add` - Thêm sinh viên
- `admin.student.back` - Quay lại
- `admin.student.success.add` - Thêm sinh viên thành công!
- `admin.student.success.edit` - Chỉnh sửa sinh viên thành công!
- `admin.student.error` - Có lỗi xảy ra. Vui lòng thử lại.

## Thêm i18n key mới

### Bước 1: Thêm vào file properties
```properties
# src/main/resources/i18n/messages_vi.properties
my.new.key=Nội dung Tiếng Việt

# src/main/resources/i18n/messages_en.properties
my.new.key=English Content
```

### Bước 2: Sử dụng trong JSP
```jsp
<span data-i18n="my.new.key">
    <fmt:message key="my.new.key"/>
</span>
```

### Bước 3: Kiểm tra
- Reload trang để thấy nội dung ban đầu
- Click nút đổi ngôn ngữ để kiểm tra thay đổi động

## JavaScript API

### Đổi ngôn ngữ
```javascript
// Chỉ cần gọi hàm này
changeLanguage('en');  // Tiếng Anh
changeLanguage('vi');  // Tiếng Việt
```

Hàm `changeLanguage()` sẽ:
1. Lấy dữ liệu i18n từ server
2. Cập nhật tất cả phần tử có `data-i18n*`
3. Lưu preference vào session

## Best Practices

1. **Luôn sử dụng data-i18n**: Kết hợp `fmt:message` với `data-i18n` để hỗ trợ thay đổi ngôn ngữ động
2. **Tránh text cứng**: Không hard-code text bằng tiếng Việt hoặc Anh
3. **Đặt tên key rõ ràng**: Sử dụng cấu trúc `scope.module.component.action`
4. **Dịch đầy đủ**: Thêm key cho cả tiếng Việt và tiếng Anh
5. **Kiểm tra placeholder**: Sử dụng `data-i18n-placeholder` cho input field

## Troubleshooting

### Text không thay đổi khi chọn ngôn ngữ
- Kiểm tra xem element có `data-i18n` attribute không
- Kiểm tra key có tồn tại trong file properties không
- Mở console (F12) xem có error không

### Placeholder không thay đổi
- Sử dụng `data-i18n-placeholder` thay vì `data-i18n`
- Đảm bảo giá trị của placeholder ban đầu là từ `fmt:message`

### Message hiển thị nhưng không dịch
- Kiểm tra `sessionScope.lang` trong server
- Đảm bảo LocaleFilter được apply cho request
- Kiểm tra file properties có chứa key đó không

## Ví dụ hoàn chỉnh

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="container">
    <h1 data-i18n="admin.title.student.add">
        <fmt:message key="admin.title.student.add"/>
    </h1>
    
    <form>
        <div class="mb-3">
            <label class="form-label" data-i18n="form.student.firstName">
                <fmt:message key="form.student.firstName"/>
            </label>
            <input type="text" 
                   class="form-control"
                   data-i18n-placeholder="form.student.firstName.placeholder"
                   placeholder="<fmt:message key='form.student.firstName.placeholder'/>">
        </div>
        
        <button class="btn btn-primary" data-i18n="admin.student.add">
            <fmt:message key="admin.student.add"/>
        </button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/assets/images/js/i18n.js"></script>
```

## Kết nối với layout

Các file layout chính sử dụng i18n:
- `layout/head.jsp` - Thiết lập i18n cho mỗi page
- `layout/navbar.jsp` - Nút đổi ngôn ngữ
- `layout/scripts.jsp` - Script cho đổi ngôn ngữ và theme

Không cần thêm `fmt:setLocale` nếu page được include từ layout.

