// employeeController.js

(function () {

    "use strict";

    angular.module("app")
        .controller("employeeController", ["$routeParams", "$q", "$http", "$timeout", employeeController]);

    function employeeController($routeParams, $q, $http, $timeout) {

        var vm = this;

        vm.isBusy = false;
        vm.tableLoading = false;
        vm.isSubmitting = false;
        vm.successMessage = "";
        vm.errorMessage = "";
        vm.inputType = "password";

        vm.filterBy = "";
        vm.sortType = "remDate";
        vm.sortReverse = true;

        vm.nameParam = $routeParams.username;
        let idParam = $routeParams.userid;
        let reimbursements;

        vm.getReimbursements = function () {
            vm.tableLoading = true;
            let data = $.param({ employeeId: idParam });

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getReimbursements.do', data, config)
                .then(function (response) {
                    reimbursements = response.data;
                    for (let i = 0; i < reimbursements.length; i++) {
                        for (let prop in reimbursements[i]) {
                            if (reimbursements[i][prop] == null) {
                                reimbursements[i][prop] = "N/A";
                            }
                        }
                    }
                    vm.tableLoading = false;
                    vm.reimbursements = reimbursements;
                },
                    function (response) {
                        vm.tableLoading = false;
                    });
        }

        vm.submitReimbursement = function () {
            vm.isSubmitting = true;
            if (vm.submitAmount > 0) {

                let data = $.param({
                    employeeId: idParam,
                    amount: vm.submitAmount,
                    remDesc: vm.submitDesc
                });

                let config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }

                $http.post('/AngJSWebApp/submitReimbursement.do', data, config)
                    .then(function (response) {
                        vm.isSubmitting = false;
                        if (response.result) {
                            vm.successMessage = "Reimbursement submitted!";
                            $timeout(function () { vm.successMessage = ""; }, 2000);
                        } else {
                            vm.errorMessage = "Error submitting reimbursement.";
                            $timeout(function () { vm.errorMessage = ""; }, 2000);
                        }
                        vm.getReimbursements();
                    }, function (response) {
                        vm.isSubmitting = false;
                        vm.errorMessage = "Error submitting reimbursement.";
                        $timeout(function () { vm.errorMessage = ""; }, 2000);
                    });
            }
            vm.isSubmitting = false;
        }

        vm.submitCredentials = function () {

            vm.isBusy = true;
            let data = $.param({
                email: vm.credEmail,
                password: vm.credPass,
                username: vm.credName,
                userId: idParam
            });
            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/updateCredentials.do', data, config)
                .then(function (response) {
                    vm.isBusy = false;
                    if (!response.result) {
                        vm.errorMessage = "Error: entered email already exists.";
                        $timeout(function () { vm.errorMessage = ""; }, 2000);
                    } else {
                        vm.successMessage = "Credentials updated!";
                        $timeout(function () { vm.successMessage = ""; }, 2000);
                    }

                }, function (response) {
                    vm.isBusy = false;
                    vm.errorMessage = "Error updating credentials.";
                    $timeout(function () { vm.errorMessage = ""; }, 2000);
                });

        }

        vm.getCredentials = function () {

            vm.isBusy = true;
            let data = $.param({ employeeId: idParam });
            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getCredentials.do', data, config)
                .then(function (response) {
                    let data = response.data;
                    vm.credName = data.username;
                    vm.credEmail = data.email;
                    vm.credPass = data.password;
                    vm.isBusy = false;
                }, function (response) {
                    vm.isBusy = false;
                });
        }

        vm.hidePassword = function () {
            if (vm.inputType == "password")
                vm.inputType = "text";
            else
                vm.inputType = "password";
        }

        vm.getReimbursements();
        vm.getCredentials();

    }

})();