mainApp.controller('roomController',function ($scope,$http,$location,$window,$hjr) {
	//数据初始化
	$scope.baseUrl = "/mud/room/";


	$scope = $hjr.defineInit($scope);
	//页面初始化
	$hjr.listInit($scope,$http,1);

	$scope.listInit =  function (p) {
		$hjr.listInit($scope,$http,p);
	}


	$scope.customerArraySearch = {};//往后台传的参数集合
	$scope.customerArraySearch.page  = {"pagenumber":10,"current":1};//分页
	$scope.customerArraySearch.search  = {"whereFiled":"","where":"","likeFiled":"","like":""};//搜索
	$scope.customerArraySearch.para  = {};//参数
	$scope.modelsSearch = [];//返回数据容器

	$scope.urlSearch = "";
	$scope.customerListDataWithArrayInit =  function (url){
		$scope.urlSearch = url;
		$hjr.customerListDataWithArray($scope,$http,1,url,"pagerSearch") ;
	}

	$scope.customerListDataWithArray =  function (p){
		$hjr.customerListDataWithArray($scope,$http,p,$scope.urlSearch,"pagerSearch") ;
	}


	function initDropData(){
		$http({
			url:'/mud/map/show',
			method: 'POST',
			data:{},
		}).then(function (result) {  //正确请求成功时处理
			$scope.dropData.push({"name": "---请选择---", "value": ""});
			for (var i = 0; i < result.data.length; i++) {
				$scope.dropData.push({"name": result.data[i].name, "value": result.data[i].name});
			}
			$scope.searchData.drop = $scope.searchData.drop == undefined?$scope.dropData[0]:$scope.searchData.drop;//设置默认值

		});
	}

//	全选
	$scope.selectAllChild = [];
	$scope.selectAll = function(){  
		$hjr.selectAll($scope,"selectAllFather","selectAllChild");
	};

	$scope.selectAllSearch = function(){
		$hjr.selectAll($scope,"selectAllFatherSearch","selectAllChildSearch");
	};
  //删除操作
    $scope.del = function(){  //删除按钮事件,分为批量删除与单独删除
		$hjr.del($scope,"selectAllFather","selectAllChild");
    };

	//添加按钮事件
	$scope.add = function(){
		$hjr.add($scope);
	};

	//编辑按钮事件
	$scope.edit = function(model){
		$model = model;
		$hjr.edit($scope,model);
		modelsRoomCha(model);
	};

  //编辑与新增操作
	$scope.submit = function(){  //按钮事件
		$http({
	        url:baseUrl+''+modifyState+'',
	        method: 'POST',            
	        data: JSON.stringify($scope.formData),
	    }).then(function (result) {  //正确请求成功时处理  
	    	console.log(modifyState);
	        if(modifyState == "add")
	        {
	        	$hjr.tips("新增成功！");
	        	totalcount = result.data[0].recordNum;//totalcount：记录数  
		    	pageCount = Math.ceil(totalcount/pagenumber); 	
		        init(pageCount);
	        }     	
	        else if(modifyState == "edit")
	        {
	        	$hjr.tips("修改成功！");
	        }  
	        $scope.models = result.data;
	        $scope.listShow = true;
	    });
    };



	$scope.modelsRoomCha = [];
	function modelsRoomCha(model) {
		$model = model;
		page.pagenumber = 100;
		$scope.customerArray.para = {"roomId":model.id};
		var para = JSON.stringify($scope.customerArray);
		$http({
			url:'/mud/joinRoomCha/showRoomChaByRoomId',
			method: 'POST',
			data:para,
		}).then(function (result) {  //正确请求成功时处理
			$scope.modelsRoomCha = result.data;
			for(index in $scope.modelsRoomCha){//a为对象的键值，person为对象
				$scope.modelsRoomCha[index].characterModelToString = angular.toJson($scope.modelsRoomCha[index].characterModel);

				$scope.modelsRoomCha[index].aa = "aaaaaa";
			}
			console.log(result.data)
		});
	}



	//显示按钮事件
	$scope.list = function(){
		$hjr.list($scope);
	};

	$scope.view = function(model){
		$scope.listShow = false;
		modifyState = "edit";
		$scope.formData = model;//点击编辑后数据匹配

	};

	$scope.modelSearchSubmit = function(){
		for(var i=0;i<jQuery('.selectAllChildSearch').length;i++)
		{
			if(jQuery('.selectAllChildSearch')[i].checked == true)
			{
				var chaId = jQuery('.selectAllChildSearch')[i].id;
				$scope.customerArray.para = {"roomId":$model.id,"chaId": chaId};
				var para = JSON.stringify($scope.customerArray);
				$http({
					url:'/mud/joinRoomCha/addRoomChaByRoomAndChaId',
					method: 'POST',
					data:para,
				}).then(function (result) {  //正确请求成功时处理
					$hjr.tips(result.data.msg);
					console.log(result.data.msg);
					modelsRoomCha($scope.model)
				});
			}
		}

	};
//  搜索

	 $scope.search = function(){
		 search.whereFiled = "mapName";
		 search.where = $scope.searchData.drop.value;
		 if($scope.searchData.drop.value != "") {
			 page.pagenumber = 100;
		 }else{
			 page.pagenumber = 10;
		 }
		 search.likeFiled = "name";
		 search.like = $scope.searchData.str;

 	     $hjr.search($scope);
	 };



	$scope.show = function() {
		console.log($scope.models);
		var html = '<caption>标题</caption><table border="1">' +
			'';
		for (var i = 0; i < $scope.models.length; i++) {
			html += "<tr>";
			for (var key in $scope.models) {
				var x = $scope.models[key].roomXY.split(",")[0];
				var y = $scope.models[key].roomXY.split(",")[1];
				if(y == i) {
					html += "<td title="+JSON.stringify($scope.models[key])+">" + $scope.models[key].name + "</td>";
				}
			}
			html += "</tr>";
		}

		html+='</table>';
		$.MsgBox.Panel("预览",html);
	};

});