<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Sign Up form</title>
    <link rel="stylesheet" href="style.css">
   </head>
<body>
<%


  String mail_error = (String) request.getAttribute("mail_error");
  String password_error = (String) request.getAttribute("password_error");
%>
  <div class="wrapper">
    <h2>Sign Up</h2>
    <form action="/login" method="post">
      <div class="input-box">
        <input type="text" placeholder="Enter your email" required name="email">
        <%if(mail_error != null){%>
        <span style="color: red"><%=mail_error%></span>
        <%}%>
      </div>
      <div class="input-box">
        <input type="password" placeholder="Create password" required name="password">
        <%if(password_error != null){%>
        <span style="color: red"><%=password_error%></span>
        <%}%>
      </div>
      <div class="input-box button">
        <input type="Submit" value="Sign Up">
      </div>
      <div class="text">
        <h3>Does not have an account? <a href="/register">Register now</a></h3>
      </div>
    </form>
  </div>

</body>
</html>
