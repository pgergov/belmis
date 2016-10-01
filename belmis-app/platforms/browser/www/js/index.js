/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
        console.log('device ready')
        $('.alarm-hour').timepicker({ 'timeFormat': 'H:i:s' });
        $('.touchend').on("click", function(){
            console.log($('.alarm-hour').val())
            $('.alarm-hour').append('<div>stana</div>')
            navigator.notification.alert('stana')
            window.wakeuptimer.wakeup( function(){
                navigator.notification.alert(result.time)
                console.log('alalalall')
            },  
               errorCallback, 
               // a list of alarms to set
               {
                    alarms : [{
                        type : 'onetime',
                        time : { hour : parseInt($('.alarm-hour').val().split(':')[0]), minute : parseInt($('.alarm-hour').val().split(':')[1]) },
                        message : 'Alarm has expired!'
                   }] 
               }
            );
        });
        $('.set-alarm').on("click", function(){
            console.log($('.alarm-hour').val())
            $('.alarm-hour').append('<div>stana</div>')
            navigator.notification.alert('stana')

            setTimeout(function(){ navigator.notification.alert('stana be'); }, 5000);

            window.wakeuptimer.wakeup( function(){
                navigator.notification.alert(result.time)
                console.log('alalalall')
            },  
               errorCallback, 
               // a list of alarms to set
               {
                    alarms : [{
                        type : 'onetime',
                        time : { hour : parseInt($('.alarm-hour').val().split(':')[0]), minute : parseInt($('.alarm-hour').val().split(':')[1]) },
                        message : 'Alarm has expired!'
                   }] 
               }
            );


            // // snooze...
            // window.wakeuptimer.snooze( successCallback,
            //     errorCallback,
            //     {
            //         alarms : [{
            //             type : 'snooze',
            //             time : { seconds : 60 }, // snooze for 60 seconds 
            //             extra : { message : 'json containing app-specific information to be posted when alarm triggers' }, 
            //             message : 'Alarm has snoozed!'
            //         }]
            //     }
            //  );

            // example of a callback method
            var successCallback = function(result) {
                navigator.notification.alert(result.time)
                console.log(result)
                alert(result.time)
                if (result.type==='wakeup') {
                    console.log('wakeup alarm detected--' + result.extra);
                } else if(result.type==='set'){
                    console.log('wakeup alarm set--' + result);
                } else {
                    console.log('wakeup unhandled type (' + result.type + ')');
                }
            }; 

            var errorCallback = function(result) {
                console.log(result)
                // if (result.type==='wakeup') {
                //     console.log('wakeup alarm detected--' + result.extra);
                // } else if(result.type==='set'){
                //     console.log('wakeup alarm set--' + result);
                // } else {
                //     console.log('wakeup unhandled type (' + result.type + ')');
                // }
            }; 
        })
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
};