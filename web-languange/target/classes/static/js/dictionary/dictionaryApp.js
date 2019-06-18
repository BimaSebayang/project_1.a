masterWebUdaIndexApp.controller('dictionary', function ($scope, $http, $rootScope, $location, $window) {
	$scope.suggestionRole = "-- No Suggestion --";
	$scope.suggestionWords = [];
	$scope.queryWord = function() {
		$scope.queryLang($scope.search);
	}
	
	$scope.queryWordSuggest = function(word) {
		$scope.queryLang(word);
		$scope.search = word;
	}
	
	$scope.queryLang = function(lang){
		$scope.url = $scope.endpoint + "/all-search-query";

		if($scope.size==undefined){
			$scope.size = 0;
		}
		
		$http.post($scope.url, {
			search : lang,
			size : $scope.size
		}, $scope.config).then(
				function(response) {
					debugger;
					$scope.datas = response.data.allDatas;
					$scope.wordSearch = $scope.search;
					if(response.data.additionalCondition.suggestion != undefined){
						$scope.suggestionRole = "-- Suggestion --";
						$scope.suggestionWords = response.data.additionalCondition.suggestion;
					}
					else{
						$scope.suggestionRole = "-- No Suggestion --";
						$scope.suggestionWords = [];
					}
				},
				function error(response) {
					debugger;
				});
	}
	
	
	
	$scope.hai = function(){
		alert('im alaia');
	}
});