// app.js

(function () {

    "use strict";

    var app = angular.module("app", ["simpleControls", "ngRoute"]);

    app.config(function ($routeProvider) {

        $routeProvider.when("/", {
            controller: "loginController",
            controllerAs: "vm",
            templateUrl: "./views/loginView.html"
        });

        $routeProvider.when("/home", {
            controller: "homeController",
            controllerAs: "vm",
            templateUrl: "./views/homeView.html"
        });

        $routeProvider.when("/manager/:username/:userid",{
            controller: "managerController",
            controllerAs: "vm",
            templateUrl: "./views/managerView.html"
        });

        $routeProvider.when("/employee/:username/:userid",{
            controller: "employeeController",
            controllerAs: "vm",
            templateUrl: "./views/employeeView.html"
        });



        $routeProvider.otherwise({ redirectTo: "/" });

    });


})();