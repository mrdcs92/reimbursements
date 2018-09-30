// managerController.js

(function () {

    "use strict";

    angular.module("app")
        .controller("managerController", ["$location", "$http", "$timeout", "authFactory", managerController]);

    function managerController($location, $http, $timeout, authFactory) {

        var vm = this;

        vm.isBusy = false;
        vm.tableLoading = false;
        vm.submitting = false;
        vm.loadingEmps = false;
        vm.successMessage = "";
        vm.errorMessage = "";
        vm.inputType = "password";

        vm.filterBy = "";
        vm.sortType = "remDate";
        vm.sortReverse = true;

        let idParam = authFactory.getUID();
        vm.idParam = authFactory.getUID();
        vm.displayName = authFactory.getName();
        let reimbursements;

        vm.tempRem = null;

        vm.getAllReimbursements = function () {
            vm.tableLoading = true;
            let data = $.param({});

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getAllReimbursements.do', data, config)
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

        vm.resolveReimbursement = function (newStatus) {

            if (vm.tempRem.status == "pending") {

                let data = $.param({
                    remId: vm.tempRem.remId,
                    status: newStatus,
                    manId: idParam
                });
                let config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }

                $http.post('/AngJSWebApp/resolveReimbursement.do', data, config)
                    .then(function (response) {
                        if (response.data) {
                            vm.tempRem.status = response.data.status;
                            vm.tempRem.manId = response.data.manId;
                            vm.tempRem.resDate = response.data.resDate;
                            vm.tempRem.manName = response.data.manName;
                            vm.successMessage = "Reimbursement resolved!";
                            $timeout(function () { vm.successMessage = ""; }, 2000);

                        } else {
                            vm.errorMessage = "Error resolving reimbursement.";
                            $timeout(function () { vm.errorMessage = ""; }, 2000);
                        }
                    });

            }
        }

        vm.getAllEmployees = function () {
            vm.loadingEmps = true;
            let data = $.param({});
            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getAllEmployees.do', data, config)
                .then(function (response) {
                    let repData = response.data;
                    vm.loadingEmps = false;
                    vm.empTable = repData;
                },
                    function (response) {
                        vm.loadingEmps = false;
                    });
        }

        vm.logOut = function () {
            authFactory.clearAuth();
            $location.path("/");
        }

        vm.setRem = function (rem) {
            if (rem.status == "pending") {
                vm.tempRem = rem;
                $("#exampleModal").modal("show");
            }
        }

        if (authFactory.getRole() == "manager") {
            vm.getAllEmployees();
            vm.getAllReimbursements();
        }
    }

})();