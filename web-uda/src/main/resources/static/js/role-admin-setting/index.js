masterWebUdaIndexApp.controller('role-admin-setting-index', function ($scope, $http, $rootScope, $location, $window) {
   
    $scope.tester = undefined;
    $scope.isActivePaging(false);
    $scope.clickBaitDot = function () {
        alert('masuk kagak ke dashboard-admin');
        $scope.tester = "masuk kagak ke dashboard-admin";
    };
});