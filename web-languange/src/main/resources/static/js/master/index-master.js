var masterWebUdaIndexApp = angular.module('masterWebUdaIndexApp', []);

masterWebUdaIndexApp.controller('indexConfiguration', function($scope, $http,
		$rootScope, $location, $window) {
	$scope.endpoint = location.origin;
	$scope.controllerform = '';
	$scope.isFilterUserAdminSettingConfig = false;
	$scope.isFilteringRoleAdminSettingConfig = false;
	$scope.isFilteringRoleDetailAdminSettingConfig = false;
	$scope.config = {q
		headers : {
			'Accept' : 'application/json, */*'
		}
	};

});

