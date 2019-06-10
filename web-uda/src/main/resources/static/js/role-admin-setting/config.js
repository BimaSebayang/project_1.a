masterWebUdaIndexApp.controller('role-admin-setting-config', function($scope,
		$http, $rootScope, $location, $window, $filter) {
	debugger;
	$scope.isActivePaging(false);

	$scope.defaultActivePaging = function(response) {
		$scope.isActivePaging(true, 'role-admin-setting-config',
				response.data.filtering, response.data.page,
				response.data.totalPage);
	}

	$scope.defaultActivePagingDetail = function(response) {
		$scope.isActivePaging(true, 'role-detail-admin-setting-config',
				response.data.filtering, response.data.page,
				response.data.totalPage);
	}

	$scope.buttonActivation = function(arr, cond, realCond) {
		// debugger;
		if (cond == realCond) {
			if (cond == 1) {
				$scope.discontinu = true;
			} else if (cond == 0) {
				$scope.discontinu = true;
			}
		} else {
			// debugger;
			$scope.discontinu = false;
			$scope.roleId = arr.roleId;
			$scope.roleName = arr.roleName;
		}
	}
	
	$scope.buttonDtlActivation = function(arr, cond, realCond) {
		// debugger;
		if (cond == realCond) {
			if (cond == 1) {
				$scope.discontinu = true;
			} else if (cond == 0) {
				$scope.discontinu = true;
			}
		} else {
			// debugger;
			$scope.discontinu = false;
			$scope.roleDtlId = arr.roleDtlId;
			$scope.roleDtlName = arr.roleDtlName;
		}
	}

	$scope.actionActivation = function(id, cond) {
		// debugger;
		$scope.url = $scope.endpoint
				+ "/web-rest-uda/action-activation/role-admin-setting";

		// debugger;
		$http.post($scope.url, {
			roleId : id,
			actionResult : cond,
		}, $scope.config).then(function(response) {
			// debugger;
			if (response.data.updateResult == 1) {
				$scope.loadData();
			}
		}, function error(response) {
			// debugger;
		});

	}
	
	$scope.actionDtlActivation = function(id, cond) {
		// debugger;
		$scope.url = $scope.endpoint
				+ "/web-rest-uda/action-activation/role-dtl-admin-setting";

		// debugger;
		$http.post($scope.url, {
			roleDtlId : id,
			actionResult : cond,
		}, $scope.config).then(function(response) {
			// debugger;
			if (response.data.updateResult == 1) {
				$scope.loadDataDetail();
			}
		}, function error(response) {
			// debugger;
		});

	}

	
	
	$scope.onEditRole = function(arg) {
		debugger;
		if(arg.tblRoleDtlDtos.length>0)
		$scope.sizeModel = [];
		else{
			$scope.sizeModel = [{
				'roleDtlFunc' : 'AUTHWS',
				'roleDtlName' : '',
				'roleDtlId' : ''
			}]
		}
		angular.forEach(arg.tblRoleDtlDtos, function(value, key) {
			$scope.sizeModel.push({
				'roleDtlId' : value.roleDtlId,
				'roleDtlFunc' : value.roleDtlFunc,
				'roleDtlName' : value.roleDtlName
			});
		});
		$scope.roleName = arg.roleName;
		$scope.roleUpdateId = arg.roleId;
	}

	$scope.addModel = function() {
		$scope.sizeModel.push({
			'roleDtlFunc' : '',
			'roleDtlName' : '',
			'roleDtlId' : ''
		});
	}
	
	$scope.removeModel = function(arg){
		$scope.sizeModel.splice(arg, 1);
	}
	
	$scope.updateRoleAdminSetting = function(arg) {
		debugger;
		$scope.url = $scope.endpoint + "/web-rest-uda/update/role-admin-setting";

		$http.post($scope.url, {
			roleId : $scope.roleUpdateId,
			roleName : $scope.roleName,
			tblRoleDtlDtos : $scope.sizeModel
		}, $scope.config).then(
				function(response) {
					debugger;
					if(response.data.updateResult == '1')
						$scope.loadData ();
				},
				function error(response) {
					debugger;
					alert('not save');
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
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

	$scope.loadTblRoleDtlDtos = function(arg1) {
		debugger;
		$scope.tblRoleDtlDtos = $scope.roleList[arg1].tblRoleDtlDtos;
	}

	$scope.loadTblUserDtos = function(arg1) {
		debugger;
		$scope.tblUserDtos = $scope.roleList[arg1].tblUserDtos;
	}

	$scope.loadData();

	$scope.loadDataDetail = function() {
		debugger;

		if ($scope.filterDetailRoleId == undefined) {
			$scope.filterDetailRoleId = '';
		}
		if ($scope.filterDetailIsActive == undefined) {
			$scope.filterDetailIsActive = '';
		}
		if ($scope.filterDetailStartDate == undefined) {
			$scope.filterDetailStartDate = '';
		}
		if ($scope.filterDetailEndDate == undefined) {
			$scope.filterDetailEndDate = '';
		}
		if ($scope.filterDetailName == undefined) {
			$scope.filterDetailName = '';
		}
		if ($scope.detailPage == undefined) {
			$scope.detailPage = '';
		}
		if ($scope.detailSize == undefined) {
			$scope.detailSize = '';
		}
		if ($scope.detailSearch == undefined) {
			$scope.detailSearch = '';
		}
		if ($scope.detailSort == undefined) {
			$scope.detailSort = [];
		}

		$scope.url = $scope.endpoint
				+ "/web-rest-uda/select-all/role-detail-admin-setting";
		$http.post($scope.url, {
			roleId : $scope.filterDetailRoleId,
			isActive : $scope.filterDetailIsActive,
			startDate : $scope.filterDetailStartDate,
			endDate : $scope.filterDetailEndDate,
			filterName : $scope.filterDetailName,
			page : $scope.detailPage,
			size : $scope.detailSize,
			search : $scope.detailSearch,
			sort : $scope.detailSort
		}, $scope.config).then(
				function(response) {
					debugger;
					$scope.roleListDetail = response.data.allDatas;
					if (response.data.length == 0) {
						$scope.isEmpty = true;
					} else {
						$scope.isEmpty = false;
					}
					$scope.defaultActivePagingDetail(response);
				},
				function error(response) {
					// debugger;
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}

	$scope.navActivatorRAT = "active";
	$scope.navActivatorRDAT = "";

	$scope.navChange = function(arg1) {
		if (arg1 == "role-add-tab") {
			$scope.navActivatorRAT = "active";
			$scope.navActivatorRDAT = "";
			$scope.loadData();
		}

		else if (arg1 == "role-detail-add-tab") {
			$scope.loadDataDetail();
			$scope.navActivatorRDAT = "active";
			$scope.navActivatorRAT = "";
		}
	}

});