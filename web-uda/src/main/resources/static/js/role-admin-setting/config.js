masterWebUdaIndexApp.controller('role-admin-setting-config', function($scope,
		$http, $rootScope, $location, $window) {
   
    $scope.isActivePaging(false);

	$scope.defaultActivePaging = function(response){
		$scope.isActivePaging(true, 'role-admin-setting-config', 
				response.data.filtering,response.data.page,response.data.totalPage);
	}
    
	$scope.loadData = function() {
		debugger;
		
		if ($scope.filterRoleId == undefined) {
			$scope.filterRoleId = '';
		}
		if ($scope.filterIsActive == undefined) {
			$scope.filterIsActive = '';
		}
		if ($scope.filterStartDate == undefined) {
			$scope.filterStartDate = '';
		}
		if ($scope.filterEndDate == undefined) {
			$scope.filterEndDate = '';
		}
		if ($scope.filterRoleDtlId == undefined) {
			$scope.filterRoleDtlId = '';
		}
		if ($scope.page == undefined) {
			$scope.page = '';
		}
		if ($scope.size == undefined) {
			$scope.size = '';
		}
		if ($scope.search == undefined) {
			$scope.search = '';
		}
		if ($scope.sort == undefined) {
			$scope.sort = [];
		}
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
		
		$scope.url = $scope.endpoint
				+ "/web-rest-uda/select-all/role-admin-setting";
		$http.post($scope.url, {
			roleId : $scope.filterRoleId,
			isActive : $scope.filterIsActive,
			startDate : $scope.filterStartDate,
			endDate : $scope.filterEndDate,
			roleDtlId : $scope.filterRoleDtlId,
			page : $scope.page,
			size : $scope.size,
			search : $scope.search,
			sort : $scope.sort
		}, $scope.config).then(
				function(response) {
					debugger;
					$scope.roleList = response.data.allDatas;
					if (response.data.length == 0) {
						$scope.isEmpty = true;
					} else {
						$scope.isEmpty = false;
					}
					$scope.defaultActivePaging(response);
				},
				function error(response) {
					// debugger;
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}

    $scope.loadData();
	
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