var masterWebUdaIndexApp = angular.module('masterWebUdaIndexApp', [])
.directive('schrollBottom', function () {
	  return {
	    scope: {
	      schrollBottom: "="
	    },
	    link: function (scope, element) {
	      scope.$watchCollection('schrollBottom', function (newValue) {
	        if (newValue)
	        {
	        	debugger;
	          $(element).scrollTop($(element)[0].scrollHeight);
	        }
	      });
	    }
	  }
	});



masterWebUdaIndexApp.controller('indexConfiguration', function($scope, $http,
		$rootScope, $location, $window,$anchorScroll) {
	$scope.endpoint = location.origin + "/languange";
	$scope.controllerform = '';
	$scope.isFilterUserAdminSettingConfig = false;
	$scope.isFilteringRoleAdminSettingConfig = false;
	$scope.isFilteringRoleDetailAdminSettingConfig = false;
	$scope.config = {
		headers : {
			'Accept' : 'application/json, */*'
		}
	};
	
	$scope.chats = [];
	var i = 1;
	
	
	$scope.loadChat = function(){
		
	$http.get($scope.endpoint + "/all-chat-history").then(
			function(response) {
				$scope.chats = response.data;
				$scope.manipulation();
				debugger;
			},
			function error(response) {
			      alert('cannot retrieve data');
			});
	};
	
	$scope.loadChat();
	
	
	$scope.answerChat = function(text){
		    var url = $scope.endpoint + "/chat-with-roxas";
	        $http.post(url, text, $scope.config).then(function (response) {
	        	$scope.chats.push(response.data);
	        	
	        	$scope.manipulation();
	        }, function error(response) {
	            $scope.postResultMessage = "Error with status: " + response.statusText;
	        });
	}
	
	$scope.manipulation = function(){
		$http.get($scope.endpoint + "/manipulator").then(
				function(response) {
					$scope.chats.push(response.data);
				},
				function error(response) {
				      alert('cannot retrieve data');
				});
		};
	
	$scope.sendMyChat = function(chat){
		i++;
		$scope.chats.push({
			'text' : chat,
			'isOutgoing' : true,
			'isIncoming' : false
		});
		
		$scope.answerChat(chat);
		
	}
	
	$scope.clickSide = function(url) {
		
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