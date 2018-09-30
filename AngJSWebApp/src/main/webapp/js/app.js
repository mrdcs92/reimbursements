// app.js

(function () {

    "use strict";

    var app = angular.module("app", ["loadDirective", "ngRoute"]);

    app.config(function ($routeProvider) {

        $routeProvider
        .when("/", {
            controller: "loginController",
            controllerAs: "vm",
            templateUrl: "./views/loginView.html"
        })
        .when("/manager",{
            controller: "managerController",
            controllerAs: "vm",
            templateUrl: "./views/managerView.html",
            resolve:{
                "check": function(authFactory, $location) {
                    if (authFactory.getRole() != "manager") {
                        $location.path("/");
                    }
                }
            }
        })
        .when("/employee",{
            controller: "employeeController",
            controllerAs: "vm",
            templateUrl: "./views/employeeView.html",
            resolve:{
                "check": function(authFactory, $location) {
                    if (authFactory.getRole() != "employee") {
                        $location.path("/");
                    }
                }
            }
        })
        .otherwise({ redirectTo: "/" });
    });

    app.run(["$rootScope", "$location", function ($rootScope, $location) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if(next == current) {
                event.preventDefault();
                $location.path("/");
            }
        });
    }]);

})();