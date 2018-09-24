// employeeController.js

(function(){

    "use strict";

    angular.module("app")
        .controller("employeeController", ["$routeParams", "$q", "$http", employeeController]);

    function employeeController($routeParams, $q, $http){

        var vm = this;
        vm.isBusy = false;

        vm.nameParam = $routeParams.username;
        let idParam = $routeParams.userid;
        let reimbursements;

        vm.getReimbursements = function (){
            vm.isBusy = true;
            let data = $.param({employeeId: idParam});

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getReimbursements.do', data, config)
                .then(function (response){
                    reimbursements = response.data;
                    for (let i = 0; i < reimbursements.length; i++) {
                        for (let prop in reimbursements[i]){
                            if (reimbursements[i][prop] == null) {
                                reimbursements[i][prop] = "N/A";
                            }
                        }                    
                    }
                    vm.isBusy = false;
                    vm.reimbursements = reimbursements;  
                },
                 function(response){
                     vm.isBusy = false;
                     console.log(response);
                 });
        }

        vm.getReimbursements();

    }

})();