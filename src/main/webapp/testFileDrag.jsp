<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-9-17
  Time: 下午7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件拖拽上传</title>
    <link rel="stylesheet" href="css/filedrag.css">
</head>
<body>
<form id="upload" action="/FileServlet?type=upload" method="POST" enctype="multipart/form-data">
    <legend>HTML File Upload</legend>
    <div>
        <label for="fileselect">Files to upload:</label>
        <input type="file" id="fileselect" name="fileselect[]" multiple="multiple"/>
        <div id="filedrag">or drop files here</div>
    </div>
    <div id="submitbutton">
        <button type="submit">Upload Files</button>
    </div>
</form>

<div id="messages">
</div>

<script src="js/filedrag.js"></script>
</body>
</html>
