<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${error}
<form action="/login.action" method="post">
    <label>用户名：</label>
    <br>
    <input type="text" name="username">
    <br>
    <label>密码：</label>
    <br>
    <input type="password" name="password">
    <br>
    <input type="submit">
</form>

</body>
</html>