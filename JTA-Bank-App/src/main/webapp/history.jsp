<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/28/2026
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - JTA-Bank</title>
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
            max-width: 420px;
            padding: 40px 30px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
        }

        .bank-brand {
            text-align: center;
            font-size: 14px;
            font-weight: 700;
            letter-spacing: 2px;
            color: #2563eb;
            text-transform: uppercase;
            margin-bottom: 8px;
        }

        h1 {
            text-align: center;
            color: #0f172a;
            font-size: 26px;
            margin-bottom: 24px;
            font-weight: 600;
        }

        .error-msg {
            background-color: #fef2f2;
            color: #dc2626;
            padding: 12px 16px;
            border-radius: 8px;
            border-left: 4px solid #dc2626;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            color: #475569;
            font-size: 14px;
            font-weight: 500;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 12px 14px;
            border: 1.5px solid #cbd5e1;
            border-radius: 8px;
            font-size: 15px;
            transition: border-color 0.2s, box-shadow 0.2s;
            outline: none;
        }

        .form-group input:focus {
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #2563eb;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
            margin-top: 10px;
        }

        .btn-submit:hover {
            background-color: #1d4ed8;
        }

        .footer-text {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #64748b;
        }

        .footer-text a {
            color: #2563eb;
            text-decoration: none;
            font-weight: 600;
            margin-left: 4px;
        }

        .footer-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="card">
    <div class="bank-brand">JTA-Bank</div>
    <h1>History for ${requestScope.accountNo}</h1>

    <table>
        <tr>
            <th>Date/Time</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Related Account</th>
            <th>Balance After</th>
        </tr>

        <c:forEach var="transaction" items="${requestScope.transactons}">

            <tr>
                <td>${transaction.timestamp}</td>
                <td>${transaction.type}</td>
                <td>

                    <fmt:formatNumber value="${transaction.amount}"
                                      type="number"
                                      minFractionDigits="2"
                                      maxFractionDigits="2"
                                      groupingUsed="true"/>

                </td>

                <td>${transaction.relatedAccountNo eq null ? "-" : transaction.relatedAccountNo}</td>

                <td>

                    <fmt:formatNumber value="${transaction.balanceAfter}"
                                      type="number"
                                      minFractionDigits="2"
                                      maxFractionDigits="2"
                                      groupingUsed="true"/>
                </td>


            </tr>

        </c:forEach>

    </table>


</div>

</body>
</html>
