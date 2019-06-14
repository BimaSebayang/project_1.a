masterWebUdaIndexApp.controller('dictionary', function ($scope, $http, $rootScope, $location, $window) {
  
	$scope.queryWord = function() {
		$scope.url = $scope.endpoint + "/all-search-query";

		if($scope.size==undefined){
			$scope.size = 0;
		}
		
		$http.post($scope.url, {
			search : $scope.search,
			size : $scope.size
		}, $scope.config).then(
				function(response) {
					debugger;
					$scope.datas = response.data.allDatas;
					$scope.wordSearch = $scope.search;
				},
				function error(response) {
					debugger;
				});
	}
	
	$scope.hai = function(){
		alert('im alaia');
	}
});