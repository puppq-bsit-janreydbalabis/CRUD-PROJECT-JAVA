<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center page-header">
            <h1>Books Management</h1>
            <a href="${pageContext.request.contextPath}/new" class="btn btn-primary">
                <i data-feather="plus"></i> Add New Book
            </a>
        </div>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${listBook}">
                        <tr>
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.title}" /></td>
                            <td><c:out value="${book.author}" /></td>
                            <td>$<c:out value="${book.price}" /></td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/edit?id=<c:out value='${book.id}' />" class="btn btn-sm btn-warning">
                                    <i data-feather="edit-2"></i> Edit
                                </a>
                                <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${book.id}' />" class="btn btn-sm btn-danger ml-2">
                                    <i data-feather="trash-2"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script>
        feather.replace()
    </script>
</body>
</html>