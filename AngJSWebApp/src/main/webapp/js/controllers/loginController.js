// loginController.js

(function () {

    "use strict";

    angular.module("app").controller("loginController", ["$q", "$timeout", "$location", "$http", "authFactory", loginController]);

    function loginController($q, $timeout, $location, $http, authFactory) {

        let vm = this;
        vm.isBusy = false;
        vm.errorMessage = "";

        authFactory.clearAuth();

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

                        if (isManager) {
                            authFactory.setAuth(data.userId, data.username, "manager");
                            $location.path("/manager");
                        } else {
                            authFactory.setAuth(data.userId, data.username, "employee");
                            $location.path("/employee");
                        }

                    } else {
                        vm.isBusy = false;
                        vm.errorMessage = "Email or password is incorrect.";
                        $timeout(function () { vm.errorMessage = ""; }, 2000);
                    }
                }, function (response) {
                    vm.isBusy = false;
                });

        }
    }

})();