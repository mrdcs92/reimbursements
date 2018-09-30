// authFactory.js

(function () {
    "use strict";

    angular.module('app')
        .factory('authFactory', ["$location", authFactory]);

    function authFactory($location) {

        let userId;
        let role = "";

        let service = {
            setAuth: setAuth,
            clearAuth: clearAuth,
            getRole: getRole,
            getUID: getUID,
            logOut: logOut
        }

        return service;

        function setAuth(uId, isMan) {
            userId = uId;
            role = isMan;
        }

        function clearAuth() {
            userId = null;
            role = "";
        }

        function getRole() {
            return role;
        }

        function getUID() {
            return userId;
        }

        function logOut() {
            clearAuth();
            $location.path("/");
        }

    }

})();