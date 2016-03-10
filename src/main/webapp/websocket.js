var uri = "ws://" + document.location.host + document.location.pathname + "/../chat";
var websocket = new WebSocket(uri);
var messagesTextArea = document.getElementById('messagesTextArea');
//var usersTextArea = document.getElementById('usersTextArea');
var messagearea = document.getElementById('messagearea');
var mylist = document.getElementById('list');
var userEmail = document.getElementById('userEmail');
var userFirstName = document.getElementById('userFirstName');
var userLastName = document.getElementById('userLastName');
var messageReceived = "";
var messageReceiverValue = "receiver";


websocket.onopen = function (event) {
    onOpen(event);
};

websocket.onerror = function (event) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + event.data);
};

websocket.onmessage = function processMessage(message) {
    var jsonData = JSON.parse(message.data); //converts a JSON text into a JavaScript object

    if (jsonData.message != null) { //if the message element isn't null
        messagesTextArea.value += jsonData.message + "\n"; //add the message element to the text area
    }
    if (jsonData.users != null) { //if the users element isn't null
        $('#mylist').empty();
        $('#mylist').append('<li><a href="#" onclick="addUserToChat(\'Everyone\'); return false;">Everyone</a></li>');

        var i = 0;
        while (i < jsonData.users.length) { //for all the user elements that came with the message
            i++;
            $('#mylist').append('<li><a href="#" onclick="addUserToChat(\'' + jsonData.users[i - 1] + '\'); return false;">' + jsonData.users[i - 1] + '</a></li>');
        }
    }

    //to save the message for creating alerts and tasks
    messageReceived = jsonData.message;
    
    var messageAfterParsing;
    messageTokens = messageReceived.split(":");
    
    //send this user's name
    accessUserName(userFirstName.value + " " + userLastName.value);

    //if the message includes the word "Alert:"
    if (messageReceived.indexOf("Alert:") > -1) {
        if (messageTokens.length >= 3) {
            messageAfterParsing = messageTokens[2];
        } else {
            var messageAfterParsing = messageTokens[1];
        }
        accessMessageContent(messageAfterParsing);
        createAlert();
    }
    
    //if the message includes the word "Task:"
    if (messageReceived.indexOf("Task:") > -1) {
        if (messageTokens.length >= 3) {
            messageAfterParsing = messageTokens[2];
        } else {
            var messageAfterParsing = messageTokens[1];
        }
        accessMessageContent(messageAfterParsing);
        createTask();
    }
};

function keepMessageReceiver(uservalue) {
    messageReceiverValue = uservalue;
}

function sendMessage() {
    websocket.send(messagearea.value);
    if (messageReceiverValue == "receiver") {
        messagearea.value = "Everyone: ";
    } else {
        messagearea.value = messageReceiverValue + ": ";
    }
}

window.onbeforeunload = function () {
    websocket.onclose = function () {};
    websocket.close();
};

function onOpen(event) {
    console.log('Connection successful');
    websocket.send(userFirstName.value + " " + userLastName.value);
}

