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
    <title>测试文件上传</title>
</head>
<body>
<form id="upload" action="/FileServlet?type=upload" method="POST" enctype="multipart/form-data">
    <div>
        <label for="fileSelect">Files to upload:</label>
        <input type="file" id="fileSelect" name="fileSelect[]" multiple="multiple" webkitdirectory/>
    </div>
    <div id="submitButton">
        <button type="submit" id="submit">Upload Files</button>
    </div>
</form>

<div id="messages">
</div>
<script src="js/jquery.min.js"></script>
<script src="js/fileDrag.js"></script>
<script>
    $("#submit").on('click', function (e) {
        e.preventDefault();

        checkFile();
    });
    var maxsize = 2 * 1024 * 1024;//2M
    var errMsg = "上传的附件文件不能超过2M！！！";
    var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
    var browserCfg = {};
    var ua = window.navigator.userAgent;
    if (ua.indexOf("MSIE") >= 1) {
        browserCfg.ie = true;
    } else if (ua.indexOf("Firefox") >= 1) {
        browserCfg.firefox = true;
    } else if (ua.indexOf("Chrome") >= 1) {
        browserCfg.chrome = true;
    }

    function checkFile() {
        try {
            var obj_file = document.getElementById("fileselect");
            if (obj_file.value == "") {
                alert("请先选择上传文件");
                return;
            }
            var fileSize = 0;
            if (browserCfg.firefox || browserCfg.chrome) {
                fileSize = obj_file.files[0].size;
            } else if (browserCfg.ie) {
                var obj_img = document.getElementById('tempimg');
                obj_img.dynsrc = obj_file.value;
                fileSize = obj_img.fileSize;
            } else {
                alert(tipMsg);
                return;
            }
            if (fileSize == -1) {
                alert(tipMsg);
            } else if (fileSize > maxsize) {
                alert(errMsg);
            } else {
                alert("文件大小符合要求");
            }
        } catch (e) {
            alert(e);
        }
    }
</script>
</body>
</html>
