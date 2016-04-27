// JavaScript Document

var userEmail = document.getElementById('userEmail');
var userFirstName = document.getElementById('userFirstName');
var userLastName = document.getElementById('userLastName');
var messageReceiver = "receiver";
var messageContents;
var userFullName;

function replaceContentInContainer(current, replacement) {
    document.getElementById(current).innerHTML = document.getElementById(replacement).innerHTML;
}

/* When the user clicks on the button, 
 toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};

//adds chosen user's name to the message input area 
function addUserToChat(uservalue) {
    document.getElementById("messagearea").value = uservalue + ": ";
    displayReceiverName(uservalue);
    keepMessageReceiver(uservalue);
    messageReceiver = uservalue;
}

//displays the chosen user on top of the chat window
function displayReceiverName(uservalue) {
    document.getElementById('chattingCompanion').innerHTML = "Chat with " + uservalue;
}

//adds alert to the message input area
function addAlertToMessage() {
    //document.getElementById("messagearea").value += "Alert: ";
    document.getElementById("messagearea").value = messageReceiver + ": Alert: ";
}

//adds task to the message input area
function addTaskToMessage() {
    document.getElementById("messagearea").value = messageReceiver + ": Task: ";
}

//saves the variable to be accessible later
function accessMessageContent(receivedMessage) {
    messageContents = receivedMessage;
}

//saves current user's name for later use
function accessUserName(userName) {
    userFullName = userName;
}

//creates a new alert
function createAlert() {
    var alertContent = messageContents;
    var alertReceiverName = messageReceiver;
    var alertSenderName = userFullName;

    if (alertReceiverName != "receiver") {
        var newAlert = {
            "alertContent": alertContent,
            "receiverName": alertReceiverName,
            "senderName": alertSenderName
        };
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/alerts",
            async: true,
            data: JSON.stringify(newAlert),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
            }
        });
    }
}

//creates a new task
function createTask() {
    var taskContent = messageContents;
    var taskReceiverName = messageReceiver;
    var taskSenderName = userFullName;

    if (taskReceiverName != "receiver") {
        var newTask = {
            "taskContent": taskContent,
            "receiverName": taskReceiverName,
            "sentBy": taskSenderName
        };
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/tasks",
            async: true,
            data: JSON.stringify(newTask),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
            }
        });
    }
}

//get alert info when clicking the alert tab
$(document).ready(function () {
    $("#alertclick").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/alerts",
            async: true,
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $.each(msg, function (index, dataItem) {
                    if (dataItem.receiverName == (userFirstName.value + " " + userLastName.value) || dataItem.receiverName == "Everyone") {
                        $('#alertList').append('<div> Alert: ' + msg[index].alertContent + '</>');
                        $('#alertList').append('<div> From: ' + msg[index].senderName + '</>');
                        $('#alertList').append('<div> To: ' + msg[index].receiverName + '<br/><br/></>');
                    }
                });
            }
        });
    });
});

//get task info when clicking the task tab
$(document).ready(function () {
    $("#taskclick").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/tasks",
            async: true,
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $.each(msg, function (index, dataItem) {
                    if (dataItem.receiverName == (userFirstName.value + " " + userLastName.value) || dataItem.receiverName == "Everyone") {
                        $('#taskList').append('<div> Task: ' + msg[index].taskContent + '</>');
                        $('#taskList').append('<div> From: ' + msg[index].sentBy + '</>');
                        $('#taskList').append('<div> To: ' + msg[index].receiverName + '<br/><br/></>');
                        //$('#taskList').append('<div> Time: ' + msg[index].taskGivenDate + '</>');
                    }
                });
            }
        });
    });
});

//get profile info when the file is loaded
/*$(document).ready(function () {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: "http://localhost:8080/SuperTeamApp/webresources/profiles",
        async: true,
        contentType: "application/json; charset=utf-8",
        success: function (msg) {
            $('#fullname').append('<span>&nbsp;&nbsp;' + msg[0].fullName + '</>');
            $('#jobtitle').append('<span>&nbsp;&nbsp;' + msg[0].jobTitle + '</>');
            $('#phonenumber').append('<span>&nbsp;&nbsp;' + msg[0].phoneNumber + '</>');
            $('#emailaddress').append('<span>&nbsp;&nbsp;' + msg[0].email + '</>');
            $('#alertList').append('<div> From: ' + msg[0].senderName + '</ > ');
            $('#alertList').append('<div> Time: ' + msg[0].sendingDateAndTime + '</>');
        }
    });
});*/


//get profile info when the profile tab is clicked
$(document).ready(function () {
    $("#profileclick").click(function (e) {
        e.preventDefault();
        /*$.ajax({
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/profiles",
            async: true,
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $.each(msg, function (index, dataItem) {
                    if (dataItem.email == userEmail.value) {
                        $('#fullname').append('<span>&nbsp;&nbsp;' + msg[index].firstName + " " + msg[index].lastName + '</>');
                        $('#jobtitle').append('<span>&nbsp;&nbsp;' + msg[index].role + '</>');
                        $('#phonenumber').append('<span>&nbsp;&nbsp;' + msg[index].phoneNumber + '</>');
                        $('#emailaddress').append('<span>&nbsp;&nbsp;' + msg[index].email + '</>');
                    }
                });
            }
        });*/
        
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/users",
            async: true,
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                console.log("works");
                $.each(msg, function (index, dataItem) {
                    if (dataItem.email == userEmail.value) {
                        $('#fullname').append('<span>&nbsp;&nbsp;' + msg[index].firstName + " " + msg[index].lastName + '</>');
                        $('#jobtitle').append('<span>&nbsp;&nbsp;' + msg[index].role + '</>');
                        //$('#phonenumber').append('<span>&nbsp;&nbsp;' + msg[index].phoneNumber + '</>');
                        $('#emailaddress').append('<span>&nbsp;&nbsp;' + msg[index].email + '</>');
                    console.log(msg[index].email);
                    }
                });
            }
        });
    });
});


//get message history when the message history tab is clicked
$(document).ready(function () {
    $("#messagehistoryclick").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/SuperTeamAppWithLogin2/webresources/messages",
            async: true,
            contentType: "application/json; charset=utf-8",
            success: function (msg) {
                $.each(msg, function (index, dataItem) {
                    if (dataItem.receiver == (userFirstName.value + " " + userLastName.value) || dataItem.sender == (userFirstName.value + " " + userLastName.value) || dataItem.receiver == "Everyone") {
                        $('#messageList').append('<div> Message: ' + msg[index].messageContent + '</>');
                        $('#messageList').append('<div> From: ' + msg[index].sender + ' To: ' + msg[index].receiver + '<br/><br/></>');
                        //$('#messageList').append('<div> To: ' + msg[index].receiver + '<br/><br/></>');
                    }
                });
            }
        });
    });
});








	