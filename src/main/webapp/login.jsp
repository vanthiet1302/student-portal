<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" content="width=device-width, initial-scale=1.0" />
    <title>Cổng thông tin sinh viên - NLU</title>
    <link rel="stylesheet" href="css/loginstyle.css" />
  </head>
  <body>
    <div class="screen-box">
      <div class="layout">
        <!-- bên trái: hình thiên lý-->
        <div class="left-side">
          <img src="AQ1.png" class="bg-img" alt="" />
        </div>

        <!-- BÊN PHẢI: FORM LOGIN -->
        <div class="right-side">
          <img src="logo.png" class="logo" alt="" />

          <div class="login-box">
            <div class="input-group">
              <span class="icon">👤</span>
              <input type="text" placeholder="MSSV" inputmode="numeric" />
            </div>

            <div class="input-group">
              <span class="icon">🔒</span>
              <input type="password" placeholder="Password" />
            </div>

            <a href="#" class="forgot">Quên mật khẩu ?</a>

            <button class="btn login">Đăng nhập</button>
            <button class="btn notify">🔔 Xem thông báo - tin tức</button>
          </div>
        </div>
      </div>
      <!-- FOOTER -->
      <footer class="footered">
        <div class="footer-run">
          TRƯỜNG ĐẠI HỌC NÔNG LÂM THÀNH PHỐ HỒ CHÍ MINH
        </div>
      </footer>
    </div>
  </body>
</html>
