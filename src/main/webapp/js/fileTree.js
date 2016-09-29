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
    fileTree.getData();

    $('#collapse-file-list').on('click', function () {
        var collapse = $('#collapse-file-list');

        if ($(collapse).hasClass('glyphicon-chevron-up')) {
            $(collapse).removeClass('glyphicon-chevron-up')
                .addClass('glyphicon-chevron-down');
        } else {
            $(collapse).removeClass('glyphicon-chevron-down')
                .addClass('glyphicon-chevron-up');
        }
    });
});

/**
 * 文件对象
 * @type {{name: string, size: number, type: string}}
 */
var file = {
    name: '',
    size: 0,
    type: ''
};

/**
 * 文件目录树相关操作
 * @type {{path: *[], getCurrentPath: fileTree.getCurrentPath, checkFile: fileTree.checkFile, openChildDir: fileTree.openChildDir, openFile: fileTree.openFile, openDir: fileTree.openDir, goBack: fileTree.goBack, getData: fileTree.getData, showFileList: fileTree.showFileList, showPath: fileTree.showPath, addFile: fileTree.addFile, addDir: fileTree.addDir, getRelativePath: fileTree.getRelativePath}}
 */
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
     * 判断当前路径是否是文件
     * 若是,执行两次弹出操作,对应'..'和文件名
     */
    checkFile: function () {
        if (fileTree.path[fileTree.path.length - 1] === '..') {
            // 弹出两次,包括'..'和文件名
            fileTree.path.pop();
            fileTree.path.pop();
        }
    },
    /**
     * 在当前文件夹下打开子文件夹
     * @param dir 文件夹名称，不含路径
     */
    openChildDir: function (dir) {
        fileTree.checkFile();

        fileTree.path.push(dir);

        fileTree.getData();
    },
    /**
     * 打开文件
     * @param file 文件对象
     */
    openFile: function (file) {
        fileTree.checkFile();

        fileTree.path.push(file.name);
        loadPreview(fileTree.getCurrentPath(), file.type);

        // 向路径数组中添加文件名时,额外添加'..'以区分文件夹与文件
        // 文件(夹)名不能为'..',不会与引起歧义
        fileTree.path.push('..');
    },
    /**
     * 点击路径导航栏打开文件夹
     * @param dirNum 文件夹编号，项目根目录编号为0，以后各级目录依次递增，与path中的值一一对应
     */
    openDir: function (dirNum) {
        fileTree.checkFile();

        for (var i = fileTree.path.length - 1; i > dirNum; i--) {
            fileTree.path.pop();
        }

        fileTree.getData();
    },
    /**
     * 返回上一级目录
     */
    goBack: function () {
        fileTree.checkFile();

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

                fileTree.showPath(fileTree.getCurrentPath());
                fileTree.showFileList(fileList);

                // 隐藏代码手动填写
                $('#codeBlock').hide();
            },
            error: function () {
                // 无文件时隐藏文件路径列表
                $(dirPath).parents('.file-tree').hide().next().hide();
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

            if (file.type === 'dir') {
                fileTree.addDir(file);
            } else {
                fileTree.addFile(file);
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
    addFile: function (file) {
        if (file === '..') {
            $(fileList).append(
                '<tr>' +
                '<td>' + '<a href="javascript: void(0)" onclick="fileTree.goBack()">..</a></td>' +
                '<td></td><td></td>' +
                '</tr>'
            );

            return;
        }

        var fileName = decodeURIComponent(file.name);

        $(fileList).append(
            '<tr>' +
            '<td><img src="../img/file.png"><a href="javascript: void(0)">' + fileName + '</a></td>' +
            '<td><a href="" class="download"></a></td>' +
            '<td class="text-right">' + file.size + '</td>' +
            '</tr>'
        ).find('tr').last().find('a').first().on('click', function () { // 点击文件名打开文件
            fileTree.openFile(file);
        }).css('margin-left', '10px').parent().next().find('a').attr('href', // 下载文件
            fileTree.getRelativePath(fileName));
    },
    /**
     * 向文件列表中添加文件夹
     */
    addDir: function (dir) {
        var dirName = decodeURIComponent(dir.name);

        $(fileList).append(
            '<tr>' +
            '<td><img src="../img/folder.png"><a href="javascript: void(0)">' + dirName + '</a></td>' +
            '<td></td>' +
            '<td class="text-right">' + dir.size + '</td>' +
            '</tr>'
        ).find('tr').last().find('a').first().on('click', function () { // 点击文件夹名打开文件夹
            fileTree.openChildDir(dirName);
        }).css('margin-left', '10px');
    },
    /**
     * 获取当前路径下的某文件(夹)相对项目根木录的相对路径,用于下载路径
     * @param name 文件(夹)名称
     */
    getRelativePath: function (name) {
        var url = '/FileServlet?type=download&taskName=' + taskName + '&fileName=';

        if (fileTree.path.length == 1) { // 根目录下直接返回名称
            return url + name;
        }

        var path = '';
        for (var i = 1; i < fileTree.path.length; i++) {
            path = path + fileTree.path[i] + '/';
        }

        return url + path + name;
    }
};

