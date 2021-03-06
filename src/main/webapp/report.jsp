<%@page import="java.util.ArrayList"%>
<%@page import="bl.InviteBlImpl"%>
<%@page import="blservice.InviteBlService"%>
<%@page import="vo.ReportVO" %>
<%@page import="tools.Encode" %>
<%@page import="vo.State" %>
<%@page import="bl.ReviewBlImpl" %>
<%@page import="blservice.ReviewBlService" %>
<%@page import="vo.Type" %>
<%@page import="vo.TaskVO" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="tools.Cast" %>
<%@page import="vo.AssessmentVO" %>
<%@page import="java.util.List" %>
<%@page import="bl.AssessmentBlImpl" %>
<%@page import="blservice.AssessmentBlService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link
            href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
            rel="stylesheet">

    <link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
          rel="stylesheet">
    <link href="css/otherTheme.css" rel="stylesheet">
    <link href="http://v3.bootcss.com/assets/css/docs.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="css/waitMe.min.css">
    <link rel='icon' href='img/icon.ico ' type=‘image/x-ico’/>
    <style type="text/css">
        .drop {
            text-decoration: line-through;
        }
    </style>
    <title>CRC Task</title>
</head>
<script type="text/javascript">
    username = '<%=session.getAttribute("username")%>';
    taskName = '<%=request.getParameter("taskName")%>';
    <%String taskName = Encode.transfer((String) request.getParameter("taskName"));%>
    <%TaskVO taskVO = Cast.cast(session.getAttribute("taskVO"));%>
</script>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://echarts.baidu.com/dist/echarts.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src='js/report.js'></script>
<body role="document">
<nav class="navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">CRC在线评审系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <%
                    if (session.getAttribute("username") != null) {
                %>
                <li class="dropdown" id="mesSpan"><a href="#"
                                                     class="dropdown-toggle" data-toggle="dropdown" role="button"
                                                     aria-haspopup="true" aria-expanded="false"><img alt=""
                                                                                                     src="${userInfo.picture} "
                                                                                                     async width="27px"
                                                                                                     class="img-circle"
                                                                                                     height="27px"> <%=session.getAttribute("username")%><span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a style="cursor: pointer;" href="myTasks.jsp">我的评审 </a></li>
                        <li><a style="cursor: pointer;" href="message.jsp">我的消息 </a></li>
                        <li><a style="cursor: pointer;" href="My CRC.jsp">我的资料</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a id="login" style="cursor: pointer;">登出</a></li>
                    </ul>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
<div class="container" style="text-align: center;" id="waitArea">
    <%
        int[][] taskHis = Cast.cast(session.getAttribute("taskHis_" + taskName));
    %>
    <script type="text/javascript">
        var dataMt = new Array();
        var dataMh = new Array();
        var assessmenMt = new Array();
        var assessmenMh = new Array();
        var fault = new Array();
        var res1;
        var res2;
        <%for (int i = 0; i < taskHis[0].length; i++) {%>
        <%if (taskHis[1][i] == 0) {%>
        res1 = 0;
        <%} else {%>
        res1 = <%=(taskHis[0][i] + 0.0) / taskHis[1][i] * 100%>;
        res1 = res1.toFixed(2);
        <%}%>
        <%if (taskHis[2][i] == 0) {%>
        res2 = 0;
        <%} else {%>
        res2 = <%=(taskHis[0][i] + 0.0) / taskHis[2][i] * 100%>;
        res2 = res2.toFixed(2);
        <%}%>
        dataMt.push(res1);
        dataMh.push(res2);
        assessmenMt.push(
                <%=taskHis[1][i]%>
        );
        assessmenMh.push(
                <%=taskHis[2][i]%>
        );
        fault.push(
                <%=taskHis[0][i]%>
        );
        <%}%>
        $(function () {
            if (fault.length == 0) {
                $('#reportZone').hide();
                $('#noReportZone').show();
            } else {
                $('#reportZone').show();
                $('#noReportZone').hide();
            }
        });
    </script>
    <h1 id="report">评审报告</h1>
    <hr>

    <div id="reportZone">
        <ul id="myTab" class="nav nav-tabs" style="text-align: center;">
            <li class="active"><a href="#mt" data-toggle="tab">mt估计报告</a></li>
            <li><a href="#mh" data-toggle="tab">mh估计报告</a></li>
            <li><a href="#report-result" data-toggle="tab">报告结果</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="mt"
                 style="margin-top: 50px;">
                <div class="row" id="resultGraph_mt" style="height: 400px"></div>
                <script type="text/javascript">
                    var myChart = echarts.init(document
                            .getElementById('resultGraph_mt'));
                    var option = {
                        title: {
                            text: '总体评审效率'
                        },
                        tooltip: {
                            trigger: 'axis',

                            axisPointer: {
                                animation: true
                            }
                        },
                        legend: {
                            data: ['效率', '总体缺陷发现', '估值']
                        },
                        xAxis: {
                            name: '合并次数',
                            type: 'category',
                            data: ['合并一', '合并二', '合并三', '合并四', '合并五', '合并六',
                                '合并七']
                        },
                        yAxis: [{
                            name: '数量',
                            type: 'value',
                            splitLine: {
                                show: false
                            }
                        }, {
                            name: '效率',
                            type: 'value',
                            splitLine: {
                                show: false
                            }
                        }],
                        series: [{
                            name: '总体缺陷发现',
                            type: 'bar',
                            barWidth: '15%',
                            barCategoryGap: '5%',
                            data: fault
                        }, {
                            name: '估值',
                            type: 'bar',
                            barWidth: '15%',
                            barCategoryGap: '5%',
                            data: assessmenMt
                        }, {
                            name: '效率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: dataMt
                        }]
                    };
                    myChart.setOption(option);
                </script>
                <%
                    List<AssessmentVO> userHis = Cast.cast(session.getAttribute("userHis_" + taskName));
                %>
                <h2>
                    <%
                        if (userHis.size() > 0) {
                    %>
                    最新Mt预估缺陷值：<%=userHis.get(0).getAssessfaults_mt()%>
                </h2>
                <%
                    }
                %>

                <div class="row">
                    <div class="col-md-6">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>评审人</th>
                                <th>发现缺陷数</th>
                                <th>评审效率</th>
                                <th>花费时间</th>
                                <th>每小时缺陷发现数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                NumberFormat ddf1 = NumberFormat.getNumberInstance();
                                ddf1.setMaximumFractionDigits(2);
                                double resMt;
                                for (AssessmentVO assessmentVO : userHis) {
                                    if (assessmentVO.getAssessfaults_mt() != 0) {
                                        resMt = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults_mt();
                                    } else {
                                        resMt = 0;
                                    }
                            %>
                            <tr
                                    onmouseover="show(<%=assessmentVO.getFindedfaults()%>, <%=assessmentVO.getUniquefaults()%>, <%=userHis.get(0).getAssessfaults_mt()%>)">
                                <td><%=assessmentVO.getReviewerName()%>
                                </td>
                                <td><%=assessmentVO.getFindedfaults()%>
                                </td>
                                <td><%=ddf1.format(resMt * 100)%>%</td>
                                <td><%=assessmentVO.getTime()%>
                                </td>
                                <td><%=assessmentVO.getFaultsperhour()%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6" id="userGraph" style="height: 400px"></div>
                    <script type="text/javascript">
                        var userChart = echarts.init(document
                                .getElementById('userGraph'));
                        initUserOption(<%=userHis.get(0).getFindedfaults()%>, <%=userHis.get(0).getUniquefaults()%>, <%=userHis.get(0).getAssessfaults_mt()%>);
                        userChart.clear();
                        userChart.setOption(userOption);
                        function show(found, unique, total) {
                            initUserOption(found, unique, total);
                            userChart.clear();
                            userChart.setOption(userOption);
                        }

                    </script>
                </div>
                <hr>
            </div>
            <div class="tab-pane fade in active" id="mh"
                 style="margin-top: 50px;">
                <div class="row" id="resultGraph_mh" style="height: 400px"></div>
                <script type="text/javascript">
                    var myChart2 = echarts.init(document
                            .getElementById('resultGraph_mh'));
                    var option2 = {
                        title: {
                            text: '总体评审效率'
                        },
                        tooltip: {
                            trigger: 'axis',

                            axisPointer: {
                                animation: true
                            }
                        },
                        legend: {
                            data: ['效率', '总体缺陷发现', '估值']
                        },
                        xAxis: {
                            name: '合并次数',
                            type: 'category',
                            data: ['合并一', '合并二', '合并三', '合并四', '合并五', '合并六',
                                '合并七']
                        },
                        yAxis: [{
                            name: '数量',
                            type: 'value',
                            splitLine: {
                                show: false
                            }
                        }, {
                            name: '效率',
                            type: 'value',
                            splitLine: {
                                show: false
                            }
                        }],
                        series: [{
                            name: '总体缺陷发现',
                            type: 'bar',
                            barWidth: '15%',
                            barCategoryGap: '5%',
                            data: fault
                        }, {
                            name: '估值',
                            type: 'bar',
                            barWidth: '15%',
                            barCategoryGap: '5%',
                            data: assessmenMh
                        }, {
                            name: '效率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: dataMh
                        }]
                    };
                    myChart2.setOption(option2);
                </script>
                <%
                    List<AssessmentVO> userHis2 = Cast.cast(session.getAttribute("userHis_" + taskName));
                %>
                <h2>
                    <%
                        if (userHis.size() > 0) {
                    %>
                    最新Mh预估缺陷值：<%=userHis.get(0).getAssessfaults_mh()%>
                </h2>
                <%
                    }
                %>
                <div class="row">
                    <div class="col-md-6">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>评审人</th>
                                <th>发现缺陷数</th>
                                <th>评审效率</th>
                                <th>花费时间</th>
                                <th>每小时缺陷发现数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ddf1.setMaximumFractionDigits(2);
                                double resMh;
                                for (AssessmentVO assessmentVO : userHis) {
                                    if (assessmentVO.getAssessfaults_mh() != 0) {
                                        resMh = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults_mh();
                                    } else {
                                        resMh = 0;
                                    }
                            %>
                            <tr
                                    onmouseover="show2(<%=assessmentVO.getFindedfaults()%>, <%=assessmentVO.getUniquefaults()%>, <%=userHis.get(0).getAssessfaults_mh()%>)">
                                <td><%=assessmentVO.getReviewerName()%>
                                </td>
                                <td><%=assessmentVO.getFindedfaults()%>
                                </td>
                                <td><%=ddf1.format(resMh * 100)%>%</td>
                                <td><%=assessmentVO.getTime()%>
                                </td>
                                <td><%=assessmentVO.getFaultsperhour()%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6" id="userGraph2" style="height: 400px"></div>
                    <script type="text/javascript">
                        var userChart2 = echarts.init(document
                                .getElementById('userGraph2'));
                        initUserOption(<%=userHis.get(0).getFindedfaults()%>, <%=userHis.get(0).getUniquefaults()%>, <%=userHis.get(0).getAssessfaults_mt()%>);
                        userChart2.clear();
                        userChart2.setOption(userOption);
                        function show2(found, unique, total) {
                            initUserOption(found, unique, total);
                            userChart2.clear();
                            userChart2.setOption(userOption);
                        }

                    </script>

                </div>
                <hr>
            </div>
            <div class="tab-pane fade active" id="report-result">
            <%
                 List<ReportVO> toMergeVOs = Cast.cast(session.getAttribute("toMerge_" + taskVO.getTaskName()));
                 AssessmentBlService assessmentBlService = new AssessmentBlImpl();
                 List<List<String>> reporters = assessmentBlService.getAllFindedReviewerNames(toMergeVOs);
                 InviteBlService inviteBl = new InviteBlImpl();
                 List<String> users = inviteBl.getAgreeUser(taskName);
                 ArrayList<int[]> matrix = new ArrayList<int[]>();
             %>
             <%for(int i = 0;i<reporters.size();i++){
            	 for(int j = 0;j<reporters.get(i).size();j++){
            		 if(users.contains(reporters.get(i).get(j))){
            			 int[] temp = {users.indexOf(reporters.get(i).get(j)),i+1};
 						matrix.add(temp);
 					}
            	 }
             }
             %>
            <div class="row" id="resultTableGraph" style="height: 400px"></div>
                <script type="text/javascript">
                	var users = new Array();
                	<%for(String s : users){%>
                	users.push('<%=s%>');
                	<%}%>
                	var matrix = new Array();
                	<%for(int[] is : matrix){%>
                	var temp = new Array();
                	temp.push(<%=is[0]%>);
                	temp.push(<%=is[1]%>);
                	matrix.push(temp);
                	<%}%>
                    var myChart3 = echarts.init(document
                            .getElementById('resultTableGraph'));
                    var option3 = {
                        title: {
                            text: '具体缺陷发现状况'
                        },
                        
                        legend: {
                           
                        },
                        xAxis: {
                            name: '参与者',
                            type: 'category',
                            data: users,
                            axisLine: {
                            	show:true,
                            	onZero:false,
                            },
                        },
                        yAxis: {
                            name: '缺陷编号',
                            type: 'value',
                            scale: true,
                            interval: 1,
                            axisLabel:{
                            	formatter: function (value, index) {
                                    // 格式化成月/日，只在第一个刻度显示年份
                                    if(index==0){
                                    	return"";
                                    }else {
    									return value;
    								}
                                }
                            }
                        
                            
                        },
                        series: {
                            name: '总体缺陷发现',
                            type: 'scatter',
                            data: matrix
                        },
                        color:['#347ABF']
                    };
                    myChart3.setOption(option3);
                </script>
                <table id="bug" class="table table-striped table-bordered"
                       data-name="cool-table">
                    <thead class="text-center">
                    <tr>
                    	<th class="text-center" width="5%">编号</th>
                        <th class="text-center" width="20%">文件名</th>
                        <%
                            if (taskVO.getType() == Type.document) {
                        %>
                        <th class="text-center" width="10%">页码</th>
                        <%
                            }
                        %>
                        <th class="text-center" width="10%">位置</th>
                        <th class="text-center">描述</th>
                        <th class="text-center" width="20%">评审人</th>
                    </tr>
                    </thead>
                    <tbody class="text-center">
                    
                    <%
                        for (ReportVO reportVO : toMergeVOs) {
                    %>
                    <tr>
                    	<td><%=toMergeVOs.indexOf(reportVO)+1%>
                        </td>
                        <td><%=reportVO.getFileName()%>
                        </td>
                        <%
                            if (taskVO.getType() == Type.document) {
                        %>
                        <td><%=reportVO.getPage()%>
                        </td>
                        <%
                            }
                        %>
                        <td><%=reportVO.getLocation()%>
                        </td>
                        <td><%=reportVO.getDescription()%>
                        </td>
                        <td class="text-left">
                            <%
                                if (reporters.get(toMergeVOs.indexOf(reportVO)).size() != 1) {
                            %>
                            <select class="form-control">
                                <%for (String s : reporters.get(toMergeVOs.indexOf(reportVO))) { %>
                                <option><%=s %>
                                </option>
                                <%} %>
                            </select>
                            <%} else {%>
                            &nbsp;&nbsp;&nbsp;&nbsp;<%=reporters.get(toMergeVOs.indexOf(reportVO)).get(0)%>
                            <%}%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>






<!---->
                <table id="BugReport" class="table table-striped table-bordered"
                       data-name="cool-table" >
                    <thead class="text-center">
                    <tr>
                      <!--  <th>project</th>  -->
                        <th hidden>Type</th>
                        <th hidden>Summary</th>
                        <th hidden>Description</th>
                        <th hidden>Reporter</th>
                    </tr>
                    </thead>
                    <tbody hidden>

                    <%
                        for (ReportVO reportVO : toMergeVOs) {
                        String s2="";
                        for (String s : reporters.get(toMergeVOs.indexOf(reportVO))) {
                            s2+=s+"&";
                        }
                        s2=s2.substring(0,s2.lastIndexOf('&'));
                    %>
                    <tr>
                        <td>Bug</td>
                        <td><%="文件:"+reportVO.getFileName()+"-位置:"+reportVO.getLocation()%></td>
                        <td><%=reportVO.getDescription()%></td>
                        <td>
                            <%=s2%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            <!-->
            </div>
        </div>
    </div>
    <div id="noReportZone">
        <p>暂无可显示的报告数据</p>
        <hr>
    </div>
</div>
<div class="mastfoot">
    <div class="inner">
        <p>
            page for <a href="index.jsp">CRC</a>, by <a
                href="https://github.com/wjwj123456/kylin-crc" target="_blank">kylin</a>.
        </p>
    </div>
</div>
<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script
        src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="js/login.js"></script>
<script src='js/waitFunction.js'></script>
<script src='js/waitMe.min.js'></script>
<script src='js/countDown.js'></script>
<script type="text/javascript">
	$('#mh').removeClass('active');
	$('#report-result').removeClass('active');
    var num = ${messageNum};
</script>
<script src='js/mesSpan.js'></script>

<script src="js/xlsx.core.min.js"></script>
<script src="js/blob.js"></script>
<script src="js/FileSaver.min.js"></script>
<script src="js/tableexport.js"></script>
<script type="text/javascript">
    $(function(){
        $('#BugReport').tableExport({formats:["xlsx","xls","csv","txt"]});
    })
</script>


</body>

</html>