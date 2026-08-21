<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.rasheeda.bank.entity.Accounts" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/30/2026
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JTA-Bank Dashboard</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f8fafc;
            color: #0f172a;
            min-height: 100vh;
        }

        /* Navigation Bar */
        .navbar {
            background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
            padding: 0 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 70px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .brand-logo {
            color: #ffffff;
            font-size: 20px;
            font-weight: 700;
            letter-spacing: 1.5px;
        }

        .brand-logo span {
            color: #3b82f6;
        }

        .nav-links {
            display: flex;
            gap: 8px;
            list-style: none;
        }

        .nav-links a {
            color: #cbd5e1;
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 500;
            transition: all 0.2s ease;
        }

        .nav-links a:hover {
            color: #ffffff;
            background-color: rgba(255, 255, 255, 0.1);
        }

        .nav-links a.active {
            color: #ffffff;
            background-color: #2563eb;
        }

        .nav-links a.logout-btn {
            color: #f87171;
        }

        .nav-links a.logout-btn:hover {
            background-color: rgba(239, 68, 68, 0.15);
            color: #ef4444;
        }

        /* Main Dashboard Container Layout */
        .dashboard-container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .welcome-header {
            margin-bottom: 28px;
        }

        .welcome-header h1 {
            font-size: 28px;
            font-weight: 700;
            color: #0f172a;
        }

        .section-title {
            font-size: 20px;
            font-weight: 600;
            color: #334155;
            margin-bottom: 20px;
        }

        /* Account Cards Grid */
        .accounts-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 20px;
        }

        .account-card {
            background: #ffffff;
            border-radius: 16px;
            padding: 24px;
            border: 1px solid #e2e8f0;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05), 0 2px 4px -1px rgba(0, 0, 0, 0.03);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .account-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.08);
        }

        .account-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 16px;
        }

        .account-number {
            font-size: 16px;
            font-weight: 700;
            color: #0f172a;
            letter-spacing: 0.5px;
        }

        .account-badge {
            background-color: #eff6ff;
            color: #2563eb;
            font-size: 12px;
            font-weight: 600;
            padding: 4px 10px;
            border-radius: 20px;
            text-transform: uppercase;
        }

        .balance-section {
            margin-bottom: 20px;
        }

        .balance-label {
            font-size: 12px;
            text-transform: uppercase;
            color: #64748b;
            letter-spacing: 0.5px;
            margin-bottom: 4px;
        }

        .balance-amount {
            font-size: 26px;
            font-weight: 700;
            color: #1e293b;
        }

        .action-link {
            display: inline-block;
            text-decoration: none;
            color: #2563eb;
            font-size: 14px;
            font-weight: 600;
            transition: color 0.2s ease;
        }

        .action-link:hover {
            color: #1d4ed8;
            text-decoration: underline;
        }

        /* Empty State Styling */
        .empty-state {
            background-color: #ffffff;
            border-radius: 16px;
            padding: 40px;
            text-align: center;
            border: 1px dashed #cbd5e1;
            color: #64748b;
        }

        .empty-state a {
            color: #2563eb;
            text-decoration: none;
            font-weight: 600;
        }

        .empty-state a:hover {
            text-decoration: underline;
        }

        /* Responsive Mobile View */
        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                height: auto;
                padding: 15px 20px;
                gap: 15px;
            }

            .nav-links {
                flex-wrap: wrap;
                justify-content: center;
                width: 100%;
            }

            .nav-links a {
                padding: 8px 12px;
                font-size: 13px;
            }
        }
    </style>
</head>
<body>

<!-- Navigation Bar -->
<nav class="navbar">
    <div class="brand-logo">JTA-<span>BANK</span></div>
    <div class="nav-links">
        <a href="dashboard">Dashboard</a>
        <a href="deposit.jsp">Deposit</a>
        <a href="withdraw.jsp" class="active">Withdraw</a>
        <a href="transfer.jsp">Transfer</a>
        <a href="new-account.jsp">New Account</a>
        <a href="logout" class="logout-btn">Logout</a>
    </div>
</nav>

<!-- Main Dashboard Body -->
<div class="dashboard-container">
    <h2 class="section-title">Withdraw</h2>

    <form action="withdraw" method="post">

        <table>
            <tr>
                <th>Account No</th>
                <td>
                    <select name="account_number" required>

                        <option value="" disabled selected>Select Account</option>
                        <c:forEach var="account" items="${requestScope.accounts}">
                            <option value="${account.accNo}">${account.accNo}</option>
                        </c:forEach>

                    </select>
                </td>

            </tr>

            <tr>
                <th>Amount</th>

                <td>
                    <input type="number" step="0.01" name="amount" placeholder="Account Number" required>
                </td>
            </tr>

            <tr>
                <th></th>
                <td><input type="submit" value="withdraw"/></td>
            </tr>


        </table>

    </form>


</div>

</body>
</html>
