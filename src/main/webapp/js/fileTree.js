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
            path = path  + fileTree.path[i] + '/';
        }

        return path.substr(0, path.length - 1);
    },

    /**
     * 打开文件
     * @param name
     */
    openFile: function (name) {
        console.log(2);
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
     * @param dir
     */
    openDir: function (dir) {

    },
    /**
     * 返回上一级目录
     */
    goBack: function () {
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
        $(fileList).empty();

        // 返回上一级
        fileTree.addFile('..');
        // 文件and文件夹
        for (var i = 0; i < fileList.length; i++) {
            var file = fileList[i];

            if (file.charAt(0) === 'f') {
                fileTree.addFile(file.substr(1));
            } else {
                fileTree.addDir(file.substr(1));
            }
        }
    },
    /**
     * 获取路径导航栏中某个文件夹相对项目根目录的路径
     * @param dirNum 文件夹序号，根目录为0，以后每级目录递增
     */
    getPath: function(dirNum) {

    },
    /**
     * 在路径导航栏中显示路径
     * @param path
     */
    showPath: function (path) {
        // 清空
        $(dirPath).empty();

        var pathList = path.split('/');
        console.log(pathList);

        for (var i = 0; i < pathList.length; i++) {
            $(dirPath).append(
                '<li>' +
                '<a href="javascript: void(0)" onclick="fileTree.getPath(i)">' + pathList[i] + '</a>' +
                '</li>'
            );
        }
        $(dirPath).find('li').last().addClass('active');
    },
    /**
     * 向文件列表中添加文件
     */
    addFile: function (name) {
        $(fileList).append(
            '<tr>' +
            '<td>' + '文件' + '</td>' +
            '<td>' + '<a href="" onclick="fileTree.openFile(' + name + ')">' + name + '</a></td>' +
            '<td>' + 'test' + '</td>' +
            '</tr>'
        );
    },
    /**
     * 向文件列表中添加文件夹
     */
    addDir: function (name) {
        $(fileList).append(
            '<tr>' +
            '<td>' + '文件夹' + '</td>' +
            '<td>' + '<a href="javascript: void(0)">' + name + '</a></td>' +
            '<td>' + 'test' + '</td>' +
            '</tr>'
        ).find('a').last().on('click', function() {
            fileTree.openChildDir(name);
        });
    }
};

