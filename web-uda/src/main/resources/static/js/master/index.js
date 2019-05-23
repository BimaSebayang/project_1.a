var masterWebUdaIndexApp = angular
		.module('masterWebUdaIndexApp', []);

masterWebUdaIndexApp.controller('indexConfiguration', function($scope, $http,
		$rootScope, $location, $window) {
	$scope.endpoint = location.origin;
	$scope.controllerform = '';
	$scope.isFilterUserAdminSettingConfig = false;
	$scope.config = {
		headers : {
			'Accept': 'application/json, */*'
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
	
	$scope.convertActivation = function(args){
		if(args=='1' || args == 1){
			return 'Active';
		}
		else if(args=='0' || args == 0){
			return 'Disactive';
		}
		return 'undefined';
	}
	
	//please notice! arg1 and arg2 only use for active pagination and detect form menu,
	//and do not change position arg and $scope, it will defect other module.
	$scope.isActivePaging = function(arg1,arg2,arg3,arg4){
		debugger;
		$scope.activePaging = arg1;
		$scope.controllerform = arg2;
	}
	
	$scope.lookForwardForm = function(arg){
		debugger;
		if(arg == 'user-admin-setting-config'){
			$scope.isFilterUserAdminSettingConfig = true;
		}
	}
	
	$scope.searchParent = function(arg){
		 $scope.$broadcast('searchParent', { message: arg});
	}
	
	$scope.refreshParent = function(){
		 $scope.$broadcast('refreshParent', { message: 'refresh'});
	}
	$scope.pagingParent = function(arg){
		debugger;
		 $scope.$broadcast('pagingParent', {message : arg});
	}
	//notice that arg1 for activation pagination, arg 2 for notify controller, arg 3 for identify filter value,
	//and arg 4 for identify paging, and arg 5 for identify total paging
	$scope.isActivePaging = function(arg1,arg2,arg3,arg4,arg5){
		debugger;
		$scope.activePaging = arg1;
		$scope.controllerform = arg2;
		if(arg2 == 'user-admin-setting-config'){
		     $scope.flagName = arg3.flagName;
		     $scope.roleName = arg3.roleName;
		}
		$scope.serialPaging = arg5;
	}
	
	$scope.filterParentUASC = function(arg1,arg2,arg3,arg4){
		debugger;
			$scope.$broadcast('filterParentUASC', 
					{ filterIsActive: arg1, 
				      filterRoleId : arg2,
				      filterStartDate : arg3,
				      filterEndDate : arg4});
	}
	
	$scope.refreshFilterSearch = function(){
		debugger;
		$scope.searchText = '';
	}
	
});

// state for placing your including js

$.getScript("/js/dashboard-admin/index.js");
$.getScript("/js/user-admin-setting/index.js");
$.getScript("/js/user-admin-setting/add.js");
$.getScript("/js/user-admin-setting/config.js");
$.getScript("/js/role-admin-setting/index.js");
$.getScript("/js/role-admin-setting/add.js");
$.getScript("/js/role-admin-setting/config.js");

