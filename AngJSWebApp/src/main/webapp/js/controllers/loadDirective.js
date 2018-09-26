// loadDirective.js

(function () {

    "use strict";

    angular.module("loadDirective", [])
        .directive("waitCursor", waitCursor);

    function waitCursor() {
        return {
            scope: {
                displayWhen: "=displayWhen"
            },
            restrict: "E",
            templateUrl: "./views/waitCursor.html"
        };
    }

})();