/**
 * Created by bhargmak on 4/4/2016.
 */

var app = angular.module('noteMyEvent',['ngRoute','angular-storage','ui.bootstrap.datetimepicker','ui.dateTimeInput','ngAnimate','ui.bootstrap.modal','pageslide-directive',"template/modal/backdrop.html","template/modal/window.html"]);

app.config(function($routeProvider)
{
    $routeProvider
        .when('/',{
            templateUrl : 'views/loginform.html',
            controller:'loginCtrl'
        })

        .when('/main',{
            templateUrl : 'views/mainpage_tpl.html',
            controller:'homeCtrl'
        })

        .otherwise({ redirectTo: '/' });



});

