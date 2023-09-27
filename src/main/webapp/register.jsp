<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Registration or Sign Up form in HTML CSS | CodingLab </title>
    <link rel="stylesheet" href="style.css">
   </head>
<body>
  <%





    String fullName_error = (String) request.getAttribute("fullName_error");
    String email_error = (String) request.getAttribute("email_error");
    String password_error = (String) request.getAttribute("password_error");
    String confirm_password_error = (String) request.getAttribute("confirm_password_error");
    String password_match = (String) request.getAttribute("password_match");
  %>
  <div class="wrapper">
    <h2>Registration</h2>
    <form action="/register" method="post">
      <div class="input-box">
        <input type="text" name="full_name" placeholder="Enter your name surname" required>
        <%if(fullName_error != null){%>
          <span style="color: red"><%=fullName_error%></span>
        <%}%>
      </div>
      <div class="input-box">
        <input type="text" name="email" placeholder="Enter your email" required>
        <%if(email_error != null){%>
        <span style="color: red"><%=email_error%></span>
        <%}%>
      </div>
      <div class="input-box">
        <input type="password" name="password" placeholder="Create password" required>
        <%if(password_error != null){%>
        <span style="color: red"><%=password_error%></span>
        <%}%>
      </div>
      <div class="input-box">
        <input type="password" name="confirm_password" placeholder="Confirm password" required>
        <%if(confirm_password_error != null || password_match != null){%>
        <span style="color: red"><%=confirm_password_error%> <%=password_match%></span>
        <%}%>
      </div>
      <div class="policy">
        <input type="checkbox">
        <h3>I accept all terms & condition</h3>
      </div>
      <div class="input-box button">
        <input type="Submit" value="Register Now">
      </div>
      <div class="text">
        <h3>Already have an account? <a href="/login">Login now</a></h3>
      </div>
    </form>
  </div>

</body>
</html>
