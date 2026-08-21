<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/28/2026
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JTA-Bank-App</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .card {
            background-color: #ffffff;
            width: 100%;
            max-width: 440px;
            padding: 40px 30px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .bank-brand {
            font-size: 14px;
            font-weight: 700;
            letter-spacing: 2px;
            color: #2563eb;
            text-transform: uppercase;
            margin-bottom: 8px;
        }

        h1 {
            color: #0f172a;
            font-size: 26px;
            margin-bottom: 12px;
            font-weight: 600;
        }

        .tagline {
            color: #64748b;
            font-size: 14px;
            margin-bottom: 30px;
            line-height: 1.5;
        }

        .button-group {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.2s ease;
            box-sizing: border-box;
        }

        .btn-primary {
            background-color: #2563eb;
            color: #ffffff;
        }

        .btn-primary:hover {
            background-color: #1d4ed8;
        }

        .btn-secondary {
            background-color: #f1f5f9;
            color: #1e293b;
            border: 1px solid #cbd5e1;
        }

        .btn-secondary:hover {
            background-color: #e2e8f0;
            color: #0f172a;
        }

        .btn-outline {
            background-color: transparent;
            color: #64748b;
            font-size: 14px;
        }

        .btn-outline:hover {
            color: #2563eb;
        }
    </style>
</head>
<body>

<div class="card">
    <div class="bank-brand">JTA-Bank</div>
    <h1>Welcome to JTA-Bank</h1>
    <p class="tagline">Your trusted partner for secure digital banking and financial management.</p>

    <div class="button-group">
        <a href="login.jsp" class="btn btn-primary">Login Page</a>
        <a href="register.jsp" class="btn btn-secondary">Register Page</a>
        <a href="index.jsp" class="btn btn-outline">Home Page</a>
    </div>
</div>

</body>
</html>