<%-- 
    Document   : ajaxTrial
    Created on : Mar 8, 2016, 9:30:32 AM
    Author     : Oona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <button type="button" title="click" id="clickybutton">Click</button>
        
        <div id="results"></div>

        <script type="text/javascript">
            //jQuery.support.cors = true;

            $(document).ready(function () {
                $("#clickybutton").click(function (e) {
                    e.preventDefault();
                    $.ajax({
                        type: "GET",
                        dataType: 'json',
                        url: "http://localhost:8080/SuperTeamApp/webresources/alerts",
                        async: true,
                        contentType: "application/json; charset=utf-8",
                        success: function (msg) {
                            console.log(msg);
                            //var j = msg;
                            var j = JSON.stringify(msg);
                            //loops through all the elements in the alert and prints them out (both element name and value)
                            msg.forEach(function (data) {
                                $.each(data, function (key, value) {
                                    $('#results').append('<div> Stuff: ' + key + " " + value + '</>');
                                })
                            })

                            //prints out a particular value
                            $('#results').append('<div> Alert: ' + msg[0].alertContent + '</>');
                            $('#results').append('<div> From: ' + msg[0].senderName + '</>');
                            $('#results').append('<div> Time: ' + msg[0].sendingDateAndTime + '</>');
                            $('#results').append('<div> something: ' + msg[0].receivers.entry.key + '</>');
                            console.log(j);
                        }
                    });
                });
            });
        </script>
    </body>
</html>
