// homeController.js

(function () {

    "use strict";

    angular.module("app")
        .controller("homeController", ["$firebase", "$firebaseArray", homeController]);

    function homeController($firebase, $firebaseArray) {

        var vm = this;
        vm.testing = "aha";

        var postsRef = firebase.database().ref().child("posts").orderByChild("timestamp").limitToFirst(3);
        //postsRef = postsRef.orderByChild("timestamp").limitToFirst(3);
        vm.blogPosts = $firebaseArray(postsRef);

    }

})();