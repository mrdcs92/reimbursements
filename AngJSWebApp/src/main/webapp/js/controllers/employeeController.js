// employeeController.js

(function(){

    "use strict";

    angular.module("app")
        .controller("employeeController", ["$routeParams", "$q", employeeController]);

    function employeeController($routeParams, $q){

        var vm = this;

        vm.nameParam = $routeParams.username;
        vm.idParam = $routeParams.userid;

    }

})();