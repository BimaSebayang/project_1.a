masterWebUdaIndexApp.controller('user-admin-setting-add', function($scope,
		$http, $rootScope, $location, $window) {

	$scope.tester = undefined;

	$scope.saveUserAdminSetting = function() {
		$scope.url = $scope.endpoint + "/web-rest-uda/save/user-admin-setting";
		var config = {
			headers : {
				'Accept' : 'text/plain'
			}
		};
		$http.post($scope.url, {
			userName : $scope.userName,
			userEmail : $scope.userEmail,
			userPhone : $scope.userPhone,
			userPassword : $scope.userPassword,
			roleId : $scope.roleId
		}, $scope.config).then(
				function(response) {
					debugger;
					alert('save');
				},
				function error(response) {
					debugger;
					alert('not save');
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}

	$scope.clickBaitDot = function() {
		alert('masuk kagak ke user-admin-setting-add');
		$scope.tester = "masuk kagak ke dashboard-admin";
	};

	$http.get($scope.endpoint + "/web-rest-uda/role/select-all").then(
			function(response) {
				$scope.roleList = response.data;
			},
			function error(response) {
			      alert('cannot retrieve data');
			});
	
	
});