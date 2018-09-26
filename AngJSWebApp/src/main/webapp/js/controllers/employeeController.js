// employeeController.js

(function () {

    "use strict";

    angular.module("app")
        .controller("employeeController", ["$routeParams", "$q", "$http", "$timeout", employeeController]);

    function employeeController($routeParams, $q, $http, $timeout) {

        var vm = this;
        
        vm.isBusy = false;
        vm.successMessage = "";
        vm.errorMessage = "";

        vm.filterBy = "";
        vm.sortType = "remDate";
        vm.sortReverse = true;

        vm.nameParam = $routeParams.username;
        let idParam = $routeParams.userid;
        let reimbursements;

        vm.getReimbursements = function () {
            vm.isBusy = true;
            let data = $.param({ employeeId: idParam });

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getReimbursements.do', data, config)
                .then(function (response) {
                    reimbursements = response.data;
                    console.log(response);
                    for (let i = 0; i < reimbursements.length; i++) {
                        for (let prop in reimbursements[i]) {
                            if (reimbursements[i][prop] == null) {
                                reimbursements[i][prop] = "N/A";
                            }
                        }
                    }
                    vm.isBusy = false;
                    vm.reimbursements = reimbursements;
                },
                    function (response) {
                        vm.isBusy = false;
                        console.log(response);
                    });
        }

        vm.submitReimbursement = function () {
            console.log(vm.submitAmount + " " + vm.submitDesc);
            vm.isBusy = true;
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
                        console.log(response);
                        vm.isBusy = false;
                        vm.successMessage = "Reimbursement submitted!";
                        $timeout(function () { vm.successMessage = ""; }, 2000);
                        vm.getReimbursements();
                    }, function (response) {
                        vm.isBusy = false;
                        console.log("error submitting reimbursement");
                    });
            }
            vm.isBusy = false;
        }

        vm.getReimbursements();

    }

})();