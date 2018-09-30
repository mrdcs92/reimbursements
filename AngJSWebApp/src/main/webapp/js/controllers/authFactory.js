// authFactory.js

(function () {
    "use strict";

    angular.module('app')
        .factory('authFactory', ["$location", authFactory]);

    function authFactory($location) {

        let userId;
        let username = "";
        let role = "";

        let service = {
            setAuth: setAuth,
            clearAuth: clearAuth,
            getUID: getUID,
            getName: getName,
            getRole: getRole,
            logOut: logOut
        }

        return service;

        function setAuth(uId, uName, isMan) {
            userId = uId;
            username = uName;
            role = isMan;
        }

        function clearAuth() {
            userId = null;
            username = "";
            role = "";
        }

        function getUID() {
            return userId;
        }

        function getName() {
            return username;
        }

        function getRole() {
            return role;
        }

        function logOut() {
            clearAuth();
            $location.path("/");
        }

    }

})();