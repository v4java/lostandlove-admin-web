<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="http://static.lostandlove.com/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap-table -->
	<link rel="stylesheet" href="http://static.lostandlove.com/bootstrap-table/bootstrap-table.css"  type="text/css">
	<!-- datetimepicker -->
	<link rel="stylesheet" href="http://static.lostandlove.com/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"  type="text/css">
    <!-- Theme style -->
    <link href="http://static.lostandlove.com/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="http://static.lostandlove.com/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	<!--  -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue sidebar-mini">
   
    <div class="wrapper">
      
	<jsp:include page="/header.jsp"></jsp:include>
      <!-- Left side column. contains the logo and sidebar -->
      <jsp:include page="/menu.jsp"></jsp:include>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            用户管理
          </h1>
          <ol class="breadcrumb">
          </ol>
        </section>
        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">用户</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
							
					<div id="custom-toolbar">
					    <div class="form-inline" role="form">
					        <div class="form-group">
					            <div class="input-group">
					                <input class="form-control" type="text" placeholder="帐号" name="account">
					            </div>
					        </div>
					        <div class="form-group">
					            <div class="input-group">
					                <input class="form-control" type="date" placeholder="创建时间(开始)" name="createTimeStart">
					            </div>
					        	<div class="input-group">
					                <input class="form-control" type="date" placeholder="创建时间(结束)" name="createTimeEnd">
					            </div>
					        </div>
					        <button type="submit" class="btn btn-default" name="querySearch">搜索</button>
					    </div>
					</div>
					<table id="data-table" data-url="getAdminUserJson.do" data-height="555" data-method="post" data-show-refresh="true" 
					data-side-pagination="server" data-pagination="true" data-page-list="[10, 20, 50]" data-search="false">
						   <thead>
						    <tr>
					        <th data-field="account" data-align="center" >帐号</th>
					        <th data-field="pwd" data-align="center" >密码</th>
					        <th data-field="statusName" data-align="center" class="col-md-1 status">是否可用</th>
					        <th data-field="isDeleteName" data-align="center" class="col-md-1 is_delete">是否删除</th>
					        <th data-field="createTimeName" data-align="center" >创建时间</th>
					        <th data-field="operation" data-align="center" class="col-md-2">操作</th>
   						 	</tr>	
   						 	</thead>
					</table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
		<jsp:include page="/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <!-- jQuery 2.1.4 -->
    <script src="http://static.lostandlove.com/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="http://static.lostandlove.com/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- DATA TABES SCRIPT -->
    <script src="../bootstrap-table.js"></script>
    <script src="http://static.lostandlove.com/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <!-- datetimepicker -->
    <script src="http://static.lostandlove.com/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="http://static.lostandlove.com/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <!-- SlimScroll -->
    <script src="http://static.lostandlove.com/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='http://static.lostandlove.com/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="http://static.lostandlove.com/dist/js/app.min.js" type="text/javascript"></script>
    <!-- page script -->
<script>
    $(function () {
    	$('#data-table').bootstrapTable();
        
    	 $(".form_datetime").datetimepicker({
    		 	language:"zh-CN",
    	        format: "yyyy-mm-dd HH:ii",
    	        showMeridian: true,
    	        autoclose: true,
    	        todayBtn: true
    	    });

    	function params(){
    		var table = $('#data-table');
    		return '';
    	}
    	
    	
    	
    });

    
</script>

</body>
</html>

