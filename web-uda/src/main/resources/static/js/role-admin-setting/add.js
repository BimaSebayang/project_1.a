masterWebUdaIndexApp.controller('role-admin-setting-add', function ($scope, $http, $rootScope, $location, $window) {
   
	$scope.navActivatorRAT = "active";
	$scope.navActivatorRDAT = "";
    $scope.isActivePaging(false);
	$http.get($scope.endpoint + "/web-rest-uda/role/select-all/no-condition").then(
			function(response) {
				$scope.roleList = response.data;
			},
			function error(response) {
			      alert('cannot retrieve data');
			});
	
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
	
	$scope.roleName = '';
	
	$scope.sizeModel = [ {
		'roleDtlFunc' : '',
		'roleDtlName' : ''
	} ];

	$scope.addModel = function() {
		$scope.sizeModel.push({
			'roleDtlFunc' : '',
			'roleDtlName' : ''
		});
	}
	
	$scope.sizeModelDetail = [ {
		'roleDtlFunc' : '',
		'roleDtlName' : '',
		'roleId' : {
			'roleId' : '',
			'roleName' : '',
		}
	} ];

	$scope.addModelDetail = function() {
		$scope.sizeModelDetail.push({
			'roleDtlFunc' : '',
			'roleDtlName' : '',
			'roleId' : {
				'roleId' : '',
				'roleName' : '',
			}
		});
	}
	
	$scope.saveRoleDtl = function(){
		debugger;
		$scope.url = $scope.endpoint + "/web-rest-uda/save/detail/role-admin-setting";

		$http.post($scope.url,$scope.sizeModelDetail, $scope.config).then(
				function(response) {
					debugger;
					if(response.data.saveResult == '1')
						$scope.clickSide('role-admin-setting-config');
				},
				function error(response) {
					debugger;
					alert('not save');
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}
	
	$scope.saveRole = function(){
		debugger;
		$scope.url = $scope.endpoint + "/web-rest-uda/save/role-admin-setting";

		$http.post($scope.url, {
			roleName : $scope.roleName,
			tblRoleDtlDtos : $scope.sizeModel
		}, $scope.config).then(
				function(response) {
					debugger;
					if(response.data.saveResult == '1')
						$scope.clickSide('role-admin-setting-config');
				},
				function error(response) {
					debugger;
					alert('not save');
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}
	
	$scope.removeModel = function(arg){
		$scope.sizeModel.splice(arg, 1);
	}
	
	$scope.removeModelDetail = function(arg){
		$scope.sizeModelDetail.splice(arg, 1);
	}
	
    $scope.tester = undefined;
    $scope.isActivePaging(false);
    $scope.clickBaitDot = function () {
        alert('masuk kagak ke dashboard-admin');
        $scope.tester = "masuk kagak ke dashboard-admin";
    };
});