<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Book</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; }
        .container { width: 50%; margin: 50px auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #f0f0f0; }
        input[type=submit] { 
            background: #d9534f; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;
        }
        input[type=submit]:hover { background: #c9302c; }
        a { color: #337ab7; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .header { text-align: center; margin-bottom: 30px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Books Management</h1>
            <h2>
                <a href="<%=request.getContextPath()%>/new">Add New Book</a> &nbsp;|&nbsp;
                <a href="<%=request.getContextPath()%>/list">List All Books</a>
            </h2>
        </div>

        <h2 style="color: #d9534f; text-align: center;">Delete Book Confirmation</h2>

        <form action="<%=request.getContextPath()%>/confirmDelete" method="post">
            <input type="hidden" name="id" value="<c:out value='${book.id}' />" />

            <table>
                <tr><th>ID</th><td><c:out value="${book.id}" /></td></tr>
                <tr><th>Title</th><td><c:out value="${book.title}" /></td></tr>
                <tr><th>Author</th><td><c:out value="${book.author}" /></td></tr>
                <tr><th>Price</th><td>$<c:out value="${book.price}" /></td></tr>
            </table>

            <p style="text-align: center; color: #d9534f; font-weight: bold;">
                Are you sure you want to delete this book?
            </p>

            <div style="text-align: center; margin-top: 20px;">
                <input type="submit" value="Yes, Delete It" />
                &nbsp;&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/list">No, Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>