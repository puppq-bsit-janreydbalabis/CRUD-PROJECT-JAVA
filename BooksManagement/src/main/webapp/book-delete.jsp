<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card delete-dialog">
                    <div class="card-header">
                        <h2 class="text-center">Delete Book Confirmation</h2>
                    </div>
                    <div class="card-body">
                        <p class="text-center lead">Are you sure you want to permanently delete this book?</p>
                        <div class="p-3 mb-2 bg-light text-dark rounded">
                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <th style="width: 20%;">ID</th>
                                        <td><c:out value="${book.id}" /></td>
                                    </tr>
                                    <tr>
                                        <th>Title</th>
                                        <td><c:out value="${book.title}" /></td>
                                    </tr>
                                    <tr>
                                        <th>Author</th>
                                        <td><c:out value="${book.author}" /></td>
                                    </tr>
                                    <tr>
                                        <th>Price</th>
                                        <td>$<c:out value="${book.price}" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-center mt-4">
                            <form action="${pageContext.request.contextPath}/confirmDelete" method="post" style="display: inline-block;">
                                <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                                <button type="submit" class="btn btn-danger btn-custom">
                                    <i data-feather="trash-2"></i> Yes, Delete It
                                </button>
                            </form>
                            <a href="${pageContext.request.contextPath}/list" class="btn btn-secondary btn-custom">
                                <i data-feather="x"></i> No, Cancel
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        feather.replace()
    </script>
</body>
</html>