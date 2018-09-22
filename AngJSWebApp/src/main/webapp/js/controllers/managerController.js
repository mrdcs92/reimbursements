// managerController.js

(function(){

    "use strict";

    angular.module("app")
        .controller("managerController", ["$routeParams", "$q", managerController]);

    function managerController($routeParams, $q){

        var vm = this;

        vm.nameParam = $routeParams.username;
        vm.idParam = $routeParams.userid;

        vm.testing = "this is the manager page!";
    }

})();