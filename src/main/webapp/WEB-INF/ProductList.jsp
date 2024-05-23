<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <style>
        body {
            margin: 40px; /* Margin between the window and the table */
        }

        table {
            border-collapse: collapse;
            width: calc(100% - 20px); /* Adjusting the table width to account for the window margin */
            border-radius: 10px;
            overflow: hidden;
            margin-bottom: 20px; /* Margin between the table and other content */
            border: none;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            border: none;
        }

        th:first-child {
            padding-left: 20px; /* Adding padding to the first column (ID) */
        }

        th {
            background-color: #444;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: white;
        }
        tr:first-child th {
            text-align: left; /* Align text in the first row to the right */
        }
        h2 {
            color: white;
            text-align: center; /* Center the text */
            font-size: 28px; /* Increase font size */
        }
    </style>
</head>
<body style="background-color:#6ef592;">
    <h2>Product list</h2>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Photo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list1}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td><img src="data:image/jpeg;base64,${product.photoAsBase64}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>


</html>
