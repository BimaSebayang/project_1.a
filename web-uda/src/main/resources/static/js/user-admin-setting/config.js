masterWebUdaIndexApp.controller('user-admin-setting-config', function ($scope, $http, $rootScope, $location, $window) {
  
    $scope.tester = undefined;

    $scope.clickBaitDot = function () {
        alert('masuk kagak ke dashboard-admin');
        $scope.tester = "masuk kagak ke dashboard-admin";
    };
});