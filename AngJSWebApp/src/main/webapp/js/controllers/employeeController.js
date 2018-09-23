// employeeController.js

(function(){

    "use strict";

    angular.module("app")
        .controller("employeeController", ["$routeParams", "$q", "$http", employeeController]);

    function employeeController($routeParams, $q, $http){

        var vm = this;

        vm.nameParam = $routeParams.username;
        let idParam = $routeParams.userid;

        vm.getReimbursements = function (){
            let data = $.param({employeeId: idParam});

            let config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

            $http.post('/AngJSWebApp/getReimbursements.do', data, config)
                .then(function (response){
                    let data = response.data;
                    console.log(data);   
                },
                 function(response){
                     console.log(response);
                 });
        }

        vm.getReimbursements();

    }

})();