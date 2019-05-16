var masterWebUdaIndexApp = angular
		.module('masterWebUdaIndexApp', [])
		.config(
				[
						'$httpProvider',
						function($httpProvider) {
							$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
						} ]);

masterWebUdaIndexApp.controller('indexConfiguration', function($scope, $http,
		$rootScope, $location, $window) {
	$scope.endpoint = location.origin;
	$scope.config = {
		headers : {
			'Accept' : 'text/plain'
		}
	};

	$http.get($scope.endpoint + "/web-rest-uda/last-url", $scope.config).then(
			function(response) {
				$scope.lastUrl = response.data;
			},
			function error(response) {
				$scope.postResultMessage = "Error with status: "
						+ response.statusText;
			});

	$scope.clickSide = function(url) {
		$scope.lastUrl = url;
	};

	$scope.clickBait = function() {
		alert('masuk kagak');
	};
});

// state for placing your including js

$.getScript("/js/dashboard-admin/index.js");
$.getScript("/js/user-admin-setting/index.js");
$.getScript("/js/user-admin-setting/add.js");
$.getScript("/js/user-admin-setting/config.js");


