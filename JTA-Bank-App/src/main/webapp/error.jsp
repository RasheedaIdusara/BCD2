<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/28/2026
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - JTA-Bank</title>
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

        .error-icon-container {
            width: 64px;
            height: 64px;
            background-color: #fef2f2;
            color: #dc2626;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px auto;
            font-size: 32px;
            font-weight: bold;
        }

        .bank-brand {
            font-size: 13px;
            font-weight: 700;
            letter-spacing: 2px;
            color: #2563eb;
            text-transform: uppercase;
            margin-bottom: 8px;
        }

        h1 {
            color: #0f172a;
            font-size: 24px;
            margin-bottom: 12px;
            font-weight: 600;
        }

        .error-box {
            background-color: #f8fafc;
            border: 1px solid #e2e8f0;
            border-left: 4px solid #dc2626;
            border-radius: 8px;
            padding: 14px 16px;
            color: #475569;
            font-size: 14px;
            line-height: 1.5;
            margin-bottom: 28px;
            text-align: left;
            word-break: break-word;
        }

        .btn-home {
            display: inline-block;
            width: 100%;
            padding: 12px;
            background-color: #2563eb;
            color: #ffffff;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            text-decoration: none;
            transition: background-color 0.2s ease;
            box-sizing: border-box;
        }

        .btn-home:hover {
            background-color: #1d4ed8;
        }
    </style>
</head>
<body>

<div class="card">
    <div class="error-icon-container">&#33;</div>
    <div class="bank-brand">JTA-Bank System</div>
    <h1>Something went wrong!</h1>

    <div class="error-box">
        <%= exception != null ? exception.getMessage() : "An unexpected error occurred. Please try again later." %>
    </div>

    <a href="index.jsp" class="btn-home">Go To Home</a>
</div>

</body>
</html>