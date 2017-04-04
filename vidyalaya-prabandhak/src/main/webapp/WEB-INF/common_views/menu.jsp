<%@page import="com.samarthsoft.prabandhak.enums.UserRole"%>
<%@page import="com.samarthsoft.prabandhak.form.entities.ApplicationSession"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="plugins/ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <!-- <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> -->
    <link rel="stylesheet" href="dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<% 
	ApplicationSession applicationSession = (ApplicationSession) request.getSession().getAttribute("session");
%> 
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      <header class="main-header">
        <!-- Logo -->
        <a href="index2.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>School</b>Prabandhak</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li><!-- end message -->
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Sales Department
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Reviewers
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li class="dropdown notifications-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 10 notifications</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">View all</a></li>
                </ul>
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger">9</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 9 tasks</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Design some buttons
                            <small class="pull-right">20%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">20% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Create a nice theme
                            <small class="pull-right">40%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">40% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Some task I need to do
                            <small class="pull-right">60%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">60% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Make beautiful transitions
                            <small class="pull-right">80%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">80% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                    </ul>
                  </li>
                  <li class="footer">
                    <a href="#">View all tasks</a>
                  </li>
                </ul>
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN) {%>
                  	<span class="hidden-xs">School Administrator</span>
                  <% } else if (applicationSession.getUserRole() == UserRole.SYSTEM_OWNER) { %>
                  	<span class="hidden-xs">School Administrator</span>
                  <% } else { %>
                  	<span class="hidden-xs"><%= applicationSession.getUserName() %></span>
                  <% } %>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    <p>
                  <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN) {%>
                  	<span class="hidden-xs">School Administrator</span>
                  <% } else if (applicationSession.getUserRole() == UserRole.SYSTEM_OWNER) { %>
                  	<span class="hidden-xs">School Administrator</span>
                  <% } else { %>
                  	<span class="hidden-xs"><%= applicationSession.getUserName() %></span>
                  <% } %>
                  
                  <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN || applicationSession.getUserRole() == UserRole.SYSTEM_OWNER) {%>
                  	<small>Administrator</small>
                  <% } else { %>
                  	<small><%= applicationSession.getUserRole().toString() %></small>
                  <% } %>
                      
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout.htm" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header>

      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
<!--           <div class="user-panel">
            <div class="pull-left image">
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Mahesh Gurav</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div> -->
          <!-- search form -->
<!--           <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form> -->
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->


          <ul class="sidebar-menu">
            <!-- <li class="header">MAIN NAVIGATION</li> -->
            <li class="active treeview">
              <a href="dashboard.htm">
<i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <!--<ul class="treeview-menu">
                <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
              </ul>-->
            </li>
          <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SUPPORTING_STAFF || 
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER || 
        		  applicationSession.getUserRole() == UserRole.TEACHER){ %>
            <li class="treeview">
              <a href="#">
                <i class="fa ion-android-person"></i>
                <span>Students</span><i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="students.htm"><i class="fa fa-circle-o"></i> Students</a></li>
               <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER){ %>
                <li><a href="certificates.htm"><i class="fa fa-circle-o"></i> Certificates</a></li>
                <li><a href="studentoperations.htm"><i class="fa fa-circle-o"></i> Operations</a></li>
                <% } %>
                <li><a href="fillmarks.htm"><i class="fa fa-circle-o"></i> Exam Marks</a></li>
<li><a href="attendance.htm"><i class="fa fa-circle-o"></i> Attendance</a></li>
              </ul>
            </li>
	<%}%>

          <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN || 
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER){ %>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-users"></i>
                <span>Staff</span><i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
		<li><a href="teachers.htm"><i class="fa fa-circle-o"></i> Teachers</a></li>
                <li><a href="supportingstaffmembers.htm"><i class="fa fa-circle-o"></i> Support Staff</a></li>
              </ul>
            </li>
	<%}%>

          <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SUPPORTING_STAFF || 
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER || 
        		  applicationSession.getUserRole() == UserRole.TEACHER){ %>
	<li>
              <a href="reports.htm">
                <i class="fa fa-pie-chart"></i> <span>Reports</span>
              </a>
            </li>
	<%}%>

          <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER){ %>
	<li>
              <a href="alumnis.htm">
                <i class="fa fa-graduation-cap"></i> <span>Alumni</span>
              </a>
            </li>
	<%}%>

          <% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER){ %>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-money"></i>
                <span>Accounts</span><i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="income.htm"><i class="fa fa-circle-o"></i> Income</a></li>
                <li><a href="expense.htm"><i class="fa fa-circle-o"></i> Expense</a></li>
              </ul>
            </li>
	<%}%>

	<li>
              <a href="calendar.htm">
                <i class="fa fa-calendar"></i> <span>Calendar</span>
              </a>
            </li>

<% if(applicationSession.getUserRole() == UserRole.SCHOOL_ADMIN ||
        		  applicationSession.getUserRole() == UserRole.SYSTEM_OWNER){ %>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-gear"></i>
                <span>Settings</span><i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="showcasts.htm"><i class="fa fa-circle-o"></i> Castes</a></li>
				<li><a href="showcategories.htm"><i class="fa fa-circle-o"></i> Categories</a></li>
				<li><a href="showscholarshiptypes.htm"><i class="fa fa-circle-o"></i> Scholarship Types</a></li>
				<li><a href="showclasses.htm"><i class="fa fa-circle-o"></i> Classes</a></li>
				<li><a href="showschooltypes.htm"><i class="fa fa-circle-o"></i> School types</a></li>
				<li><a href="showsubjects.htm"><i class="fa fa-circle-o"></i> Subjects</a></li>
				<li><a id="btn-exams" href="showexams.htm"><i class="fa fa-circle-o"></i> Exams</a></li>
				<li><a id="btn-exams" href="nationalities.htm"><i class="fa fa-circle-o"></i> Nationalities</a></li>
				<li><a id="btn-exams" href="teacherdesignations.htm"><i class="fa fa-circle-o"></i> Designation-Teacher</a></li>
				<li><a id="btn-exams" href="staffdesignations.htm"><i class="fa fa-circle-o"></i> Designation-Staff</a></li>
				<li><a href="settings.htm"><i class="fa fa-circle-o"></i> Roll Numbers Update</a></li>
              </ul>
            </li>
			<% } %>

		<% if(applicationSession.getUserRole() == UserRole.TEACHER){ %>
            <li class="treeview">
              <a href="teacherprofile.htm?id=<%=applicationSession.getUserGuid()%>">
                <i class="fa fa-gear"></i>
                <span>Profile</span><i class="fa fa-angle-left pull-right"></i>
              </a>
			</li>
		<% } %>

		<% if(applicationSession.getUserRole() == UserRole.SUPPORTING_STAFF){ %>
            <li class="treeview">
              <a href="staffprofile.htm?id=<%=applicationSession.getUserGuid()%>">
                <i class="fa fa-gear"></i>
                <span>Profile</span><i class="fa fa-angle-left pull-right"></i>
              </a>
			</li>
		<% } %>
		
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

