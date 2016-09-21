/**
 * filedrag.js - HTML5 File Drag & Drop demonstration
 * Featured on SitePoint.com
 * Developed by Craig Buckler (@craigbuckler) of OptimalWorks.net
 *
 * 文件拖拽支持
 */
(function () {

    // 文件最大传输大小
    var maxSize = 1024 * 1024 * 50;

    // output information
    function Output(msg) {
        var m = document.getElementById("messages");
        m.innerHTML = msg + m.innerHTML;
    }

    // file selection
    function FileSelectHandler(e) {
        // fetch FileList object
        var files = e.target.files || e.dataTransfer.files;

        var totalSize = 0;
        // process all File objects
        for (var i = 0, f; f = files[i]; i++) {
            totalSize = totalSize + f.size;
            ParseFile(f);
        }

        console.log(e.target);
        alert(1);
        if (totalSize > maxSize) {
            alert("文件(夹)过大，无法上传");
        } else {
            console.log(e.target);
        }
    }

    // output file information
    function ParseFile(file) {
        Output(
            "<p>文件名: <strong>" + file.name +
            "</strong> 文件类型: <strong>" + file.type +
            "</strong> 文件大小: <strong>" + file.size +
            "</strong> bytes</p>"
        );
    }

    // initialize
    function Init() {
        var fileSelect = document.getElementById("fileInput");
        // file select
        fileSelect.addEventListener("change", FileSelectHandler, false);
    }

    // call initialization file
    if (window.File && window.FileList && window.FileReader) {
        Init();
    }
})();
