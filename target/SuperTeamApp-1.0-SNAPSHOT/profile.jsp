<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome to MedApp!</title>
        <link rel="stylesheet" href="chat_app_css.css" type="text/css">

        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

        <script src="scripts.js"></script>

    </head>

    <body>

        <!-- navigation bar -->
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="defaultNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="index.jsp">MedApp</a></div>
                <div class="collapse navbar-collapse" id="defaultNavbar1">
                    <ul class="nav navbar-nav">
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                        <li><a href="http://users.metropolia.fi/~mildap/MedApp/login.html">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--aler msg line-->
        <div class="for col-md-12">
            <div class="alert alert-danger" role="alert">
                <div class="container-fluid">
                    <p>Alert Message</p>
                </div>
            </div>
        </div>
        <hr>
        <!-- side panel-->
        <div class="container-fluid">
            <div class="col-md-2 col-md-offset-1"><img src="http://users.metropolia.fi/~mildap/MedApp/Images/default-placeholder.png" class="profilepic" alt="user_avatar">
                <div class="list-group"> 
                    <a href="" class="list-group-item" id="alertclick" onClick="replaceContentInContainer('viewProfile', 'showAlerts'); return false;">My Alerts</a>
                    <a href="" class="list-group-item" id="taskclick" onClick="replaceContentInContainer('viewProfile', 'showTasks'); return false;">My Tasks</a>
                    <a href="profile.jsp" id="profileclick" class="list-group-item">View Profile</a>
                    <a href="" class="list-group-item" onClick="replaceContentInContainer('viewProfile', 'changePass'); return false;">Change Password</a>
                    <a href="" class="list-group-item" onClick="replaceContentInContainer('viewProfile', 'changePic'); return false;">Add Profile Picture</a> 
                </div>
            </div>
            <div class="col-md-6 col-md-offset-1">
                <div class="row">
                    <a href="main.jsp">Back to main page</a>
                    <br>
                    <h3>Name Surname's profile</h3>
                    <br>

                    <!-- show profile -->
                    <div id="viewProfile" class="well well-lg" style="display:block">
                        <span class="text-muted" id="fullname">Name Surname:</span><br>
                        <span class="text-muted" id="jobtitle">Job Title:</span><br>
                        <span class="text-muted" id="phonenumber">Telephone Number:</span><br>
                        <span class="text-muted" id="emailaddress">E-mail:</span><br>

                    </div>
                </div>

                <!-- show Alerts -->
                <div id="showAlerts" class="col-md-6 col-md-offset-1" style="display:none">
                    <p><strong>My alerts are shown here</strong></p>
                    <ul id="alertList">
                        <!--<li>1</li>
                        <li>2</li>
                        <li>3</li>-->
                    </ul>
                </div>

                <!-- show Tasks -->
                <div id="showTasks" class="col-md-6 col-md-offset-1" style="display:none">
                    <p><strong>My tasks are shown here.</strong></p>
                    <ul id="taskList">
                        <!--<li>1</li>
                        <li>2</li>
                        <li>3</li>-->
                    </ul>
                </div>

                <!-- change Password div-->
                <div id="changePass" class="col-md-6 col-md-offset-1" style="display:none">
                    <p style="display:none" id="passChangeMsg">Your password has been successfully changed!</p>
                    <form role="changePass" class="form-horizontal" onsubmit="">
                        <div class="form-group">
                            <label for="password" class="control-label col-sm-3">Your Old Password</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" name="old_password" placeholder="Enter your old password" id="changeInput">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label col-sm-3">Your New Password</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" name="new_password" placeholder="Enter your new password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-5">
                                <button type="submit" class="btn btn-default changeButton" onSubmit="alert('passChangeMsg');">Change Password</button>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- change Picture div-->
                <div id="changePic" class="col-md-12" style="display:none">
                    <p style="display:none" id="picChangeMsg">Your profile picture has been successfully changed!</p>
                    <div class="col-sm-3"><img src="http://users.metropolia.fi/~mildap/MedApp/Images/default-placeholder.png" id="profilepic" alt="user_avatar"></div>
                    <div class="col-sm-offset-3" id="picChangeDiv">
                        <p class="col-sm-12">Add profile picture <span class="file-input btn btn-primary btn-file col-xs-offset-1">Browse</span></p>
                        <br>
                        <button class="btn btn-default col-sm-">Change picture</button>
                    </div>
                </div>
            </div>
        </div>
        <hr>

        <!-- footer -->
        <div class="row">
            <div class="text-center col-md-6 col-md-offset-3">
                <p>Copyright &copy; 2016 &middot; All Rights Reserved &middot; <a href="" >Super Team</a></p>
            </div>
        </div>
        <hr>
    </body>
</html>
