<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Restaurant Image</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Upload Restaurant Image</h1>

    <form action="UploadRestaurantImage" method="post" enctype="multipart/form-data">
    <label for="menuId">Menu ID:</label>
    <input type="text" id="menuId" name="menuId" required>
    <br><br>
    <label for="image">Select Image:</label>
    <input type="file" id="image" name="image" accept="image/*" required>
    <br><br>
    <button type="submit">Upload</button>
</form>

    <!-- Optional: Display server response -->
    <c:if test="${not empty requestScope.successMessage}">
        <p style="color: green;">${requestScope.successMessage}</p>
    </c:if>
    <c:if test="${not empty requestScope.errorMessage}">
        <p style="color: red;">${requestScope.errorMessage}</p>
    </c:if>

</body>
</html>
