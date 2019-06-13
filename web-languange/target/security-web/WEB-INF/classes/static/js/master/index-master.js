var masterWebUdaIndexApp = angular.module('masterWebUdaIndexApp', []);

masterWebUdaIndexApp.controller('indexConfiguration', function($scope, $http,
		$rootScope, $location, $window) {
	$scope.endpoint = location.origin;
	$scope.controllerform = '';
	$scope.isFilterUserAdminSettingConfig = false;
	$scope.isFilteringRoleAdminSettingConfig = false;
	$scope.isFilteringRoleDetailAdminSettingConfig = false;
	$scope.config = {
		headers : {
			'Accept' : 'application/json, */*'
		}
	};
	
	$scope.clickSide = function(url) {
		debugger;
		$scope.lastUrl = url;
	};
	
	$scope.clickBait = function() {
		alert('masuk kagak');
	};

	$scope.isActiveButton = function(arg) {
		if (arg == 1)
			return true;
		else
			return false;
	}
	$scope.isInActiveButton = function(arg) {
		if (arg == 0)
			return true;
		else
			return false;
	}

	$scope.convertActivation = function(args) {
		if (args == '1' || args == 1) {
			return 'Active';
		} else if (args == '0' || args == 0) {
			return 'Disactive';
		}
		return 'undefined';
	}

});

$.getScript("/languange/js/dictionary/dictionaryApp.js");
$.getScript("/languange/js/spell-checker/spellCheckerApp.js");