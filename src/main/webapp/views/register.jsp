<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title> 
</head>
<body>
<div class="main">
    <h1>Register</h1>
    <form action="register" method="POST">
        <label for="username">
            Username:
        </label>
        <input type="text"
               id="username"
               name="username"
               placeholder="Enter your username" required>

        <label for="fullName">
            Full name:
        </label>
        <input type="text"
               id="fullName"
               name="fullName"
               placeholder="Enter your full name" required>

        <label for="email">
            Email:
        </label>
        <input type="text"
               id="email"
               name="email"
               placeholder="Enter your email" required>

        <label for="phone">
            Phone number:
        </label>
        <input type="text"
               id="phone"
               name="phone"
               placeholder="Enter your phone number" required>

        <label for="password">
            Password:
        </label>
        <input type="password"
               id="password"
               name="password"
               placeholder="Enter your password" required>

        <label for="re-password">
            Password:
        </label>
        <input type="password"
               id="re-password"
               name="re-password"
               placeholder="Re-enter your password" required>

        <div class="wrap">
            <button type="submit"
                    onclick="solve()">
                Register
            </button>
        </div>
    </form>
    <h5 style="color: red">
        <%
            Object alert = request.getAttribute("alert");
            if (alert != null) {
                out.print(alert);
            }
        %>
    </h5>
    <p>Already have an account?
        <a href="login"
           style="text-decoration: none;">
            Login
        </a>
    </p>
</div>
</body>
</html>