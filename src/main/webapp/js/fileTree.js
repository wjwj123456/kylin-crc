/**
 * Created by song on 16-9-23.
 *
 * 文件树相关
 */

// 文件列表
var fileList = $('#file-list');

// 文件路径
var dirPath = $('#dir-path');

$(function () {
    console.log(taskName);
    fileTree.getData();
});

var fileTree = {
    path: [
        taskName
    ],
    /**
     * 获取当前文件夹相对项目根目录的完整路径
     */
    getCurrentPath: function () {
        var path = '';

        for (var i = 0; i < fileTree.path.length; i++) {
            path = path + fileTree.path[i] + '/';
        }

        return path.substr(0, path.length - 1);
    },

    /**
     * 打开文件
     * @param name
     */
    openFile: function (name) {
        console.log(name);
    },
    /**
     * 在当前文件夹下打开子文件夹
     * @param dir 文件夹名称，不含路径
     */
    openChildDir: function (dir) {
        fileTree.path.push(dir);

        fileTree.getData();
    },
    /**
     * 点击路径导航栏打开文件夹
     * @param dirNum 文件夹编号，项目根目录编号为0，以后各级目录依次递增，与path中的值一一对应
     */
    openDir: function (dirNum) {
        alert(dirNum);

        for (var i = fileTree.path.length - 1; i > dirNum; i--) {
            fileTree.path.pop();
        }

        fileTree.getData();
    },
    /**
     * 返回上一级目录
     */
    goBack: function () {
        if (fileTree.path.length === 1) { // 根目录下，无法返回上一级
            return;
        }

        fileTree.path.pop();

        fileTree.getData();
    },
    /**
     * 获取文件目录树结构
     */
    getData: function () {
        jQuery.ajax({
            url: '/FileServlet',
            data: {
                'type': 'getFileStruct',
                'path': fileTree.getCurrentPath()
            },
            success: function (data) {
                var fileList = eval(data);

                fileTree.showFileList(fileList);
                fileTree.showPath(fileTree.getCurrentPath());
            }
        })
    },
    /**
     * 显示文件列表
     * @param fileList
     */
    showFileList: function (fileList) {
        // 清空文件列表
        $('#file-list').empty();

        // 返回上一级
        fileTree.addFile("..");
        // 文件and文件夹
        for (var i = 0; i < fileList.length; i++) {
            var file = eval(fileList[i]);

            if (file.name.charAt(0) === 'f') {
                fileTree.addFile(file.name.substr(1), file.size);
            } else {
                fileTree.addDir(file.name.substr(1), file.size);
            }
        }
    },

    /**
     * 在路径导航栏中显示路径
     * @param path
     */
    showPath: function (path) {
        // 清空
        $(dirPath).empty();

        var pathList = path.split('/');

        for (var i = 0; i < pathList.length - 1; i++) {
            $(dirPath).append(
                '<li>' +
                '<a href="javascript: void(0)">' + pathList[i] + '</a>' +
                '</li>'
            );
        }
        // 当前目录
        $(dirPath).append(
            '<li class="active">' + pathList[pathList.length - 1] + '</li>'
        );

        // 添加点击事件
        $(dirPath).find('a').each(function (index) {
            $(this).on('click', function () {
                fileTree.openDir(index);
            });
        });
    },
    /**
     * 向文件列表中添加文件
     */
    addFile: function (name, size) {
        if (name === '..') {
            $(fileList).append(
                '<tr>' +
                '<td>' + '<a href="javascript: void(0)" onclick="fileTree.goBack()">..</a></td>' +
                '<td></td><td></td>' +
                '</tr>'
            );

            return;
        }
        $(fileList).append(
            '<tr>' +
            '<td class="file"></td>' +
            '<td>' + '<a href="javascript: void(0)">' + name + '</a></td>' +
            '<td class="text-right">' + size + '</td>' +
            '</tr>'
        ).find('a').last().on('click', function () {
            fileTree.openFile(name);
        });
    },
    /**
     * 向文件列表中添加文件夹
     */
    addDir: function (name, size) {
        $(fileList).append(
            '<tr>' +
            '<td class="folder"></td>' +
            '<td>' + '<a href="javascript: void(0)">' + name + '</a></td>' +
            '<td class="text-right">' + size + '</td>' +
            '</tr>'
        ).find('a').last().on('click', function () {
            fileTree.openChildDir(name);
        });
    }
};

