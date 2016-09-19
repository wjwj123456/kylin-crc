/**
 * filedrag.js - HTML5 File Drag & Drop demonstration
 * Featured on SitePoint.com
 * Developed by Craig Buckler (@craigbuckler) of OptimalWorks.net
 *
 * 文件拖拽支持
 */
(function () {
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

        console.log(files.length);
        console.log(totalSize);
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
        var fileSelect = document.getElementById("fileSelect");
        // file select
        fileSelect.addEventListener("change", FileSelectHandler, false);
    }

    // call initialization file
    if (window.File && window.FileList && window.FileReader) {
        Init();
    }
})();
