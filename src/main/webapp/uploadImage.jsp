<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Menu Image</title>
</head>
<body>
    <h1>Upload Image for Menu</h1>
   <form action="UploadImageServlet" method="post" enctype="multipart/form-data">
    <label for="menuId">Menu ID:</label>
    <input type="text" id="menuId" name="menuId" required>
    <br><br>
    <label for="image">Select Image:</label>
    <input type="file" id="image" name="image" accept="image/*" required>
    <br><br>
    <button type="submit">Upload</button>
</form>
   
</body>
</html>
