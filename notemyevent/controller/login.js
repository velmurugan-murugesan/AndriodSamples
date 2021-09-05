var app = angular.module('noteMyEvent');

app.controller('loginCtrl', function($scope,$rootScope,$location,$window,store){

    $scope.userLogin ={
        username : "test" ,
        password : "test"
    };
    $scope.login = function(){
        if($scope.userLogin.username === 'test' && $scope.userLogin.password === 'test'){
            $location.path('/main');

            // if user is valid, lets store it in the local storage ok?
            var storeObj = {
                username: $scope.userLogin.username
            };

            store.set('user', storeObj);


        }
        else{
            alert("Invalid username and passweord");
        }
    }
});


