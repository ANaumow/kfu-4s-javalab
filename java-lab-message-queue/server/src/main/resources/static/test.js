/*import SockJS from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'
import 'jquery'*/

/*const { SockJS } = require('sockjs-client')
const { Stomp } = require('@stomp/stompjs')
require('jquery')*/

var stompClient;

function connect() {
    let socket = new SockJS('/web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(/*'/user/echo'*/'/user/jlmq', function (message) {
            console.log("new message: ")
            console.log(message)
            console.log("message body: " + message.body)
            console.log("message command" + message.command)
            $("#message").val(message.body)
        });
    });

}

function send() {
    let data = {
        command: 'send',
        queueName: 'documents_for_generate',
        body: 'information'
    }
    sendMessage(JSON.stringify(data))
}

function subscribe() {
    let data = {
        command: 'subscribe',
        queueName: 'documents_for_generate'
    }
    sendMessage(JSON.stringify(data))
}

function accepted() {
    let data = {
        command: 'accepted',
        messageId: $("#message_id").val()
    }
    sendMessage(JSON.stringify(data))
}

function completed() {
    let data = {
        command: 'completed',
        messageId: $("#message_id").val()
    }
    sendMessage(JSON.stringify(data))
}

function sendMessage(message) {
    console.log("sending message: " + message)
    stompClient.send(/*'/echo'*/ '/jlmq', {}, message);
}

$(function () {
    connect();

    console.log("hello world")
    $("#sendButton").click(function () {
        
    });
});
