/**
 * Created by song on 16-9-23.
 *
 * 文件树相关
 */

var fileList = $('#file-list');

$(function () {
    console.log(taskName);
    fileTree.getData();
});

var fileTree = {
    path: [],
    /**
     * 获取相对项目根目录的完整路径
     */
    getPath: function () {
        var path = '';

        for (var str in fileTree.path) {
            path = path + '/' + str;
        }

        return path;
    },
    openFile: function(name) {

    },
    /**
     * 打开文件夹
     * @param dir 文件夹名称，不含路径
     */
    openDir: function (dir) {
        fileTree.path.push(dir);

        fileTree.getData();
    },
    /**
     * 返回上一级目录
     */
    goBack: function () {
        fileTree.path.pop();

        fileTree.getData();
    },
    getData: function () {
        jQuery.ajax({
            url: '/FileServlet',
            data: {
                'type': 'getFileStruct',
                'taskName': taskName,
                'path': fileTree.getPath()
            },
            success: function (data) {
                var fileList = eval(data);

                console.log(data);
                fileTree.show(fileList);
            }
        })
    },
    show: function (fileList) {
        console.log(fileList);
        fileTree.addFile('..');

        for (var file in fileList) {
            if (file.charAt(0) === 'f') {
                fileTree.addFile(file.substr(1));
            } else {
                fileTree.addDir(file.substr(1));
            }
        }
    },
    /**
     * 向文件列表中添加文件
     */
    addFile: function (name) {
        $(fileList).append(
            '<tr>' +
            '<td>' + '文件' + '</td>' +
            '<td>' + '<a onclick="' + fileTree.openFile(name) + '">' + name + '</a></td>' +
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
            '<td>' + '<a onclick="' + fileTree.openDir(name) + '">' + name + '</a></td>' +
            '<td>' + 'test' + '</td>' +
            '</tr>'
        );
    }
};

