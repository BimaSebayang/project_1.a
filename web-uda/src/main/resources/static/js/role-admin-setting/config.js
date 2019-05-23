masterWebUdaIndexApp.controller('role-admin-setting-config', function($scope,
		$http, $rootScope, $location, $window) {
   
    $scope.isActivePaging(false);

	$scope.navActivatorRAT = "active";
	$scope.navActivatorRDAT = "";

	$scope.navChange = function(arg1,arg2){
		if(arg1=="active"){
			$scope.navActivatorRAT = "";
		}
		else{
			$scope.navActivatorRAT = "active";
		}
		
		if(arg2=="active"){
			$scope.navActivatorRDAT = "";
		}
		else{
			$scope.navActivatorRDAT = "active";
		}
	}
	
});