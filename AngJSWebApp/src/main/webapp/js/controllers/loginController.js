// loginController.js

(function () {

    "use strict";

    angular.module("app").controller("loginController", ["$q", "$timeout", "$location", "$http", loginController]);

    function loginController($q, $timeout, $location, $http) {

        let vm = this;
        vm.isBusy = false;

        vm.testing = "please work";

        vm.loginUser = function () {

            vm.isBusy = true;
            let isManager = $("#subCheck").is(':checked');

            let data = $.param({
                email: $("#subEmail").val(),
                password: $("#subPass").val(),
                isManager: isManager
            });

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/tryLogin.do', data, config)
                .then(function (response) {
                    let data = response.data;
                    if (data) {
                        vm.isBusy = false;
                        console.log(data);

                        if (isManager) {
                            $location.path("/manager/" + data.username + "/" + data.userId);
                        } else {
                            $location.path("/employee/" + data.username + "/" + data.userId);
                        }

                    } else {
                        vm.isBusy = false;
                        $("#test").append("<p>incorrect login</p>");
                    }
                }, function (response) {
                    vm.isBusy = false;
                });

        }
        /*
                vm.submitForm = function () {
        
                    vm.isBusy = true;
        
                    let subEmail = $("#subEmail").val();
                    let subPass = $("#subPass").val();
                    let subCheck = $("#subCheck").is(':checked');
        
                    console.log(subEmail + " " + subPass + " " + subCheck);
                    console.log(subCheck + "haha");
        
                    let xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 & this.status == 200) {
                            let loginJSON = JSON.parse(this.response);
                            console.log(loginJSON);
                            if (loginJSON) {
                                vm.isBusy = false;
                                $("#test").append("<p>" + loginJSON.userId + " " + loginJSON.username + " " + loginJSON.password + " " + loginJSON.email + "<p>");
                            } else {
                                vm.isBusy = false;
                                $("#test").append("<p>incorrect login</p>");
                            }
        
                            console.log("this should turn off the loading?");
                        }
                    };
                    xhttp.open("post", "/AngJSWebApp/tryLogin.do", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8;");
                    xhttp.send("email=" + subEmail + "&password=" + subPass + "&isManager=" + subCheck);
        
                } */
    }

})();