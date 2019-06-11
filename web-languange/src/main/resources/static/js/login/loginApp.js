var loginApp = angular.module('loginApp', []).config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);

loginApp.controller('RequestLogin', function ($scope, $http, $rootScope, $location, $window) {
  
    $scope.login = function () {
    	debugger;
        var endpoint = location.protocol + '//' + location.host;
        var url = endpoint + "/languange/request-login";
        var config = {
            headers: {
                'Accept': 'text/plain'
            }
        };
        var body = {
            userName: $scope.userName,
            password: $scope.password
        }
        $http.post(url, body, config).then(function (response) {
        	debugger;
            $window.location.href = "/languange"+response.data;
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " + response.statusText;
        });

    }
});



