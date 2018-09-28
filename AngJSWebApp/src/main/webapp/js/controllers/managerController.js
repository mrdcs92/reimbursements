// managerController.js

(function () {

    "use strict";

    angular.module("app")
        .controller("managerController", ["$routeParams", "$q", "$http", "$timeout", managerController]);

    function managerController($routeParams, $q, $http, $timeout) {

        var vm = this;

        vm.isBusy = false;
        vm.tableLoading = false;
        vm.submitting = false;
        vm.successMessage = "";
        vm.errorMessage = "";
        vm.inputType = "password";

        vm.filterBy = "";
        vm.sortType = "remDate";
        vm.sortReverse = true;

        vm.nameParam = $routeParams.username;
        let idParam = $routeParams.userid;
        let reimbursements;

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



        vm.getAllReimbursements();
    }

})();