<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
    <script src="https://unpkg.com/feather-icons"></script>
</head>
<body style="height: 100vh; margin: 0; display: flex; align-items: center; justify-content: center;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-9 col-lg-8">
                <div class="card">
                    <div class="card-header" style="background-color: #800000 !important; color: white !important;">
                        <h2 class="text-center" style="color: white !important; margin: 0;">
                            <c:if test="${book != null}">Edit Book</c:if>
                            <c:if test="${book == null}">Add New Book</c:if>
                        </h2>
                    </div>
                    <div class="card-body">
                        <c:if test="${error != null}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${error}" />
                            </div>
                        </c:if>
                        <form action="<c:choose><c:when test='${book != null}'>${pageContext.request.contextPath}/update</c:when><c:otherwise>${pageContext.request.contextPath}/insert</c:otherwise></c:choose>" method="post">
                            <c:if test="${book != null}">
                                <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                            </c:if>
                            <div class="form-group">
                                <label for="title">Title</label>
                                <input type="text" class="form-control" id="title" name="title" value="<c:out value='${book.title}' />" placeholder="e.g. The Great Gatsby" required>
                            </div>
                            <div class="form-group">
                                <label for="author">Author</label>
                                <input type="text" class="form-control" id="author" name="author" value="<c:out value='${book.author}' />" placeholder="e.g. F. Scott Fitzgerald" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="text" class="form-control" id="price" name="price" value="<c:out value='${book.price}' />" placeholder="e.g. 19.99" required>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary btn-custom">
                                    <i data-feather="save"></i> Save Changes
                                </button>
                                <a href="${pageContext.request.contextPath}/list" class="btn btn-secondary btn-custom">Cancel</a>
                            </div>
                        </form>
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