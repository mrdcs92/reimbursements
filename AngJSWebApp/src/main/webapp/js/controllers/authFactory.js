// authFactory.js

(function () {
    "use strict";

    angular.module('app')
        .factory('authFactory', ["$http", authFactory]);

    function authFactory($http) {

        let userId;
        let role = "";

        let service = {
            setAuth: setAuth,
            clearAuth: clearAuth,
            getRole: getRole,
            getUID: getUID
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

        // function initCreds() {
        //     let data = $.param({});
        //     let config = {
        //         headers: {
        //             'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
        //         }
        //     }

        //     $http.post('/AngJSWebApp/getCreds.do', data, config)
        //     .then(function (response) {
        //         let credData = response.data;
        //         userId = credData.userId;
        //         role = credData.isManager;
        //     },
        //         function (response) {
        //             console.log("big error :(");
        //         });
        // }

       
    }

})();