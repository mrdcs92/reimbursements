// app.js

(function () {

    "use strict";

    var app = angular.module("app", ["loadDirective", "ngRoute"]);

    app.config(function ($routeProvider, $locationProvider) {

        $routeProvider
        .when("/", {
            controller: "loginController",
            controllerAs: "vm",
            templateUrl: "./views/loginView.html"
        })
        .when("/home", {
            controller: "homeController",
            controllerAs: "vm",
            templateUrl: "./views/homeView.html"
        })
        .when("/manager/:username/:userid",{
            controller: "managerController",
            controllerAs: "vm",
            templateUrl: "./views/managerView.html"
        })
        .when("/employee/:username/:userid",{
            controller: "employeeController",
            controllerAs: "vm",
            templateUrl: "./views/employeeView.html"
        })
        .otherwise({ redirectTo: "/" });

    });


})();