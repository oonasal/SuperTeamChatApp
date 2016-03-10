<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"     
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="appStylesheet.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        
        <link rel="stylesheet" href="chat_app_css.css" type="text/css">
        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        
        
        
        <title>Super Team Chat App</title>

    </head>

    <body>

        <!-- navigation bar -->

    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="defaultNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                <a class="navbar-brand" href="http://users.metropolia.fi/~mildap/MedApp/main.html">MedApp</a></div>
            <div class="collapse navbar-collapse" id="defaultNavbar1">
                <ul class="nav navbar-nav">
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <li><a href="http://users.metropolia.fi/~mildap/MedApp/login.html" class="nav_link">Log Out</a></li>
                </ul>
            </div>
        </div>
    </nav>


    <!--Side panel-->
    <div class="col-md-2 col-md-offset-1">
        <div class="list-group"> 
            <a href="" id="alertclick" class="list-group-item" onClick="replaceContentInContainer('motd', 'showAlerts'); return false;">My Alerts</a> 
            <a href="" id="taskclick" class="list-group-item" onClick="replaceContentInContainer('motd', 'showTasks'); return false;">My Tasks</a> 
            <a href=""  id="profileclick" class="list-group-item" onClick="replaceContentInContainer('motd', 'viewProfile'); return false;">View Profile</a> 
            <!--<a href="" class="list-group-item" onClick="replaceContentInContainer('motd', 'changePass'); return false;">Change Password</a>-->
            <!--<a href="" class="list-group-item" onClick="replaceContentInContainer('motd', 'changePic'); return false;">Add Profile Picture</a>-->
            <a href="" id="messagehistoryclick" class="list-group-item" onClick="replaceContentInContainer('motd', 'showMessageHistory'); return false;">Message history</a>
        </div>
    </div>




    <jsp:useBean id="ou" class="beans.OnlineUsersBean" scope="page"/>

    <!--Dropdown menu-->
    <div class="container-fluid">
        <h4>Pick a person from the categories below to start a conversation</h4>
        
        <jsp:useBean id="obj" class="beans.ListBean" scope="page"/>
        
        <jsp:useBean id="obj1" class="beans.DoctorsListBean" scope="page"/>
        <jsp:useBean id="obj2" class="beans.NursesListBean" scope="page"/>
        <jsp:useBean id="obj3" class="beans.SpecialistsListBean" scope="page"/>

        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Doctors <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <c:forEach var="doctor" items="${obj1.doctors}">
                                    <li><a href="#" onClick="replaceContentInContainer('motd', 'chat'); return false;">${doctor}</a></li>
                                    </c:forEach>
            </ul>
        </div>
        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Nurses <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <c:forEach var="nurse" items="${obj2.nurses}">
                                    <li><a href="#" onClick="replaceContentInContainer('motd', 'chat'); return false;">${nurse}</a></li>
                                    </c:forEach>
            </ul>
        </div>
        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Specialists <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <c:forEach var="specialist" items="${obj3.specialists}">
                                    <li><a href="#" onClick="replaceContentInContainer('motd', 'chat'); return false;">${specialist}</a></li>
                                    </c:forEach>
            </ul>
        </div>

        <div class="dropdown">
            <button id="onlinebutton" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Online <span class="caret"></span></button>
            <ul class="dropdown-menu" id="mylist"> 
            </ul>
        </div>
        <hr>


        <!--Chat window-->
        <div class="container" style="display:block" id="chat">
            <div id="wrapper" float:right>
                <div id="menu">

                    <!--chat heading -->
                    <div class="panel panel-primary">
                        <div id="chattingCompanion" class="panel-heading"> <span class="glyphicon glyphicon-comment"></span> Chat with Everyone </div>

                        <!-- patient tags -->
                        <div class="col-md-12">
                            <div class="tags">
                                <a href="" class="primary"><span class="glyphicon glyphicon-user"></span>Patient Name</a> 
                                <a href="" class="primary"><span class="glyphicon glyphicon-user"></span>Patient Name</a>
                            </div>
                        </div>
                        <br>
                        <hr>

                        <textarea id="messagesTextArea" readonly="readonly" style="width: 90%" rows="10" cols="45"></textarea>
                        <div class="panel-footer">

                            <div class="addButtons">
                                <button type="button" id="addAlertButton" onclick="addAlertToMessage(); return false;" class="btn btn-default btn-md" title="Add Alert"> <span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span></button>
                                <button type="button" onclick="addTaskToMessage(); return false;" class="btn btn-default btn-md" title="Add Task"> <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                                <!--<button type="button" class="btn btn-default btn-md" title="Add Patient"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span></button>-->
                                <!--<button type="button" class="btn btn-default btn-md" title="Add Attachment"> <span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span></button>-->
                                <!--<button type="button" class="btn btn-default btn-md" title="Add Picture"> <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>-->
                                <!--<button type="button" class="btn btn-default btn-md" title="Add Video"> <span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span></button>-->
                            </div>

                            <div>
                                <textarea id="messagearea"  placeholder="Type your message here..." autofocus></textarea>
                                <button type="submit" id="sendButton" onclick="sendMessage(); return false;">Send</button>


                            </div>


                            <br/>

                        </div>

                    </div>



                </div>
            </div>

            <br/>

        </div>


        <!--MOTD-->
        <div class="well well-lg" id="motd" style="display:block">
            <h2 class="text-center">Welcome to MedApp, ${firstName}!</h2>
            <p class="text-align">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
        </div>  

        <input type="hidden" id="userFirstName" value="${firstName}" />
        <input type="hidden" id="userEmail" value="${userEmail}" />
        <input type="hidden" id="userLastName" value="${lastName}" />



        <!-- show profile -->
        <div id="viewProfile" class="col-md-8" style="display:none">
            <span class="text-muted" id="fullname">Name Surname:</span><br>
            <span class="text-muted" id="jobtitle">Job Title:</span><br>
            <!--<span class="text-muted" id="phonenumber">Telephone Number:</span><br>-->
            <span class="text-muted" id="emailaddress">E-mail:</span><br>
        </div>

        <!-- show Alerts -->
        <div id="showAlerts" class="col-md-8 col-sm-offset-1" style="display:none">
            <p><strong>My alerts are shown here.</strong></p>
            <ul id="alertList">
                <!--<li>1</li>
                <li>2</li>
                <li>3</li>-->
            </ul>
        </div>

        <!-- show Tasks -->
        <div id="showTasks" class="col-md-8 col-md-offset-1" style="display:none">
            <p><strong>My tasks are shown here.</strong></p>
            <ul id="taskList">
                <!--<li>1</li>
                <li>2</li>
                <li>3</li>-->
            </ul>
        </div>

        <!-- change Password div-->
        <!--<div id="changePass" class="col-md-8 col-md-offset-1" style="display:none">
            <p style="display:none" id="passChangeMsg">Your password has been successfully changed!</p>
            <form role="changePass" class="form-horizontal" onsubmit="">
                <div class="form-group">
                    <label for="password" class="control-label col-sm-3">Your Old Password</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" name="old_password" placeholder="Enter your old password">
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
                        <button type="submit" class="btn btn-default" onSubmit="alert('passChangeMsg');">Change Password</button>
                    </div>
                </div>
            </form>
        </div>-->

        <!-- change Picture div-->
        <!--<div id="changePic" class="col-md-12" style="display:none">
            <p style="display:none" id="picChangeMsg">Your profile picture has been successfully changed!</p>
            <div class="col-sm-2"><img src="http://users.metropolia.fi/~mildap/MedApp/Images/default-placeholder.png" id="profilepic" alt="user_avatar" style="width:64px;height:64px;"></div>
            <div class="col-sm-offset-2" id="picChangeDiv">
                <p class="col-sm-12">Add profile picture <span class="file-input btn btn-primary btn-file col-xs-offset-1">Browse</span></p>
                <br>
                <button class="btn btn-default col-sm-">Change picture</button>
            </div>
        </div>-->

        <!-- show message history -->
        <div id="showMessageHistory" class="col-md-8 col-sm-offset-1" style="display:none">
            <p><strong>Message history:</strong></p>
            <ul id="messageList">

            </ul>
        </div>

        <script type="text/javascript" src="scripts.js"></script>
        <script type="text/javascript" src="websocket.js"></script>
    </body>
</html>