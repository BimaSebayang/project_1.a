masterWebUdaIndexApp.controller('user-admin-setting-config', function($scope,
		$http, $rootScope, $location, $window) {

	$scope.defaultActivePaging = function(response){
		$scope.isActivePaging(true, 'user-admin-setting-config', 
				response.data.filtering,response.data.page,response.data.totalPage);
	}
	
	
	$scope.access = true;
	$scope.tester = undefined;
	$scope.clickBaitDot = function() {
		$scope.tester = "masuk kagak ke dashboard-admin";
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

	$scope.buttonActivation = function(user, cond, realCond) {
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
			$scope.userId = user.userId;
			$scope.name = user.userName;
		}
	}

	$scope.actionActivation = function(user, cond) {
		// debugger;
		$scope.url = $scope.endpoint
				+ "/web-rest-uda/action-activation/user-admin-setting";
		var action = undefined;
		if (cond == 1) {
			action = 1;
		} else if (cond == 0) {
			action = 0;
		}

		// debugger;
		$http.post($scope.url, {
			userId : user,
			actionResult : action,
		}, $scope.config).then(function(response) {
			// debugger;
			if (response.data.updateResult == 1) {
				$scope.loadData();
			}
		}, function error(response) {
			// debugger;
		});

	}

	$http.get($scope.endpoint + "/web-rest-uda/role/select-all").then(
			function(response) {
				$scope.roleList = response.data;
			}, function error(response) {
				alert('cannot retrieve data');
			});

	$scope.onEdit = function(user) {
		$scope.userId = user.userId;
		$scope.userName = user.userName;
		$scope.userBatch = user.userBatch;
		$scope.userEmail = user.userEmail;
		$scope.userPhone = user.userPhone;
		$scope
		for (var i = 0; i < $scope.roleList.length; i++) {
			if ($scope.roleList[i].roleId == user.roleId.roleId) {
				$scope.roleId = $scope.roleList[i];
			}
		}
	}

	$scope.updateUserAdminSetting = function() {
		debugger;
		$scope.url = $scope.endpoint
				+ "/web-rest-uda/update/user-admin-setting";
		var config = {
			headers : {
				'Accept' : 'text/plain'
			}
		};
		$http.post($scope.url, {
			userId : $scope.userId,
			userName : $scope.userName,
			userEmail : $scope.userEmail,
			userPhone : $scope.userPhone,
			userBatch : $scope.userBatch,
			roleId : $scope.roleId
		}, $scope.config).then(
				function(response) {
					debugger;
					if (response.data.updateResult == 1) {
						$scope.loadData();
					}
				},
				function error(response) {
					debugger;
					alert('not save');
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}

	$scope.url = $scope.endpoint
			+ "/web-rest-uda/select-all/user-admin-setting";
	var config = {
		headers : {
			'Accept' : 'text/plain'
		}
	};

	

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
				+ "/web-rest-uda/select-all/user-admin-setting";
		$http.post($scope.url, {
			roleId : $scope.filterRoleId,
			isActive : $scope.filterIsActive,
			startDate : $scope.filterStartDate,
			endDate : $scope.filterEndDate,
			page : $scope.page,
			size : $scope.size,
			search : $scope.search,
			sort : $scope.sort
		}, $scope.config).then(
				function(response) {
					debugger;
					$scope.userList = response.data.allDatas;
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

	$scope.$on('searchParent', function(event, args) {
		debugger;
		if (args.message == undefined || args.message == '') {
			$scope.search = '';
		} else {
			$scope.search = args.message;
		}
		$scope.page = "0";
		$scope.loadData();
	})

	$scope.$on('filterParentUASC', function(event, args) {
		debugger;
		$scope.filterIsActive = args.filterIsActive;
		$scope.filterRoleId = args.filterRoleId;
		$scope.filterStartDate = args.filterStartDate;
		$scope.filterEndDate = args.filterEndDate;
		$scope.page = "0";
		$scope.loadData();
	})

	$scope.$on('refreshParent', function(event, args) {
		if (args.message == 'refresh') {
			debugger;
			$scope.filterRoleId = '';
			$scope.filterIsActive = '';
			$scope.filterStartDate = '';
			$scope.filterEndDate = '';
			$scope.page = '';
			$scope.size = '';
			$scope.search = '';
			$scope.sort = [];
			$scope.refreshFilterSearch();
			$scope.loadData();
		}
	})

	$scope.$on('pagingParent',function(event,args){
	 debugger;
	 $scope.page = args.message;
	 $scope.loadData();
	})


});
