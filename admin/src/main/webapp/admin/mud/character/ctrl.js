mainApp.controller('characterController',function ($scope,$http,$location,$window,$hjr) {
	//数据初始化
	var	baseUrl = "/mud/character/";
	$scope.listShow = true;
	$scope.formData = {};
	$scope.models = [];
	$scope.modelsSearch = [];
	$scope.dropData = [];
	$scope.searchData = [];
	//页面初始化
	init(1);
	function init(p)
	{
		page.pagenumber = page.pagenumber == undefined?pagenumber:page.pagenumber;
		page.current = p;

		customerArray.page = page;
		customerArray.search = search;
		var para = JSON.stringify(customerArray);
		$http({
			url:baseUrl+'show',
            method: 'POST',
            data:para,
        }).then(function (result) {  //正确请求成功时处理  
			$scope.models = result.data;
			console.log($scope.models);
    	    if(result.data == ""){return}
        	totalcount = result.data[0].recordNum;//totalcount：记录数  
    	    pageCount = Math.ceil(totalcount/pagenumber); 	
        	$("#pager").pager({
        		pagenumber: p,
        		pagecount: pageCount,
        		buttonClickCallback: init 
        	});	       
        });

		initDropData();
	}

	$scope.modelSearch =  function (url){
		page.pagenumber = 100;
		var para = JSON.stringify(customerArray);
		$http({
			url:url,
			method: 'POST',
			data:para,
		}).then(function (result) {  //正确请求成功时处理
			$scope.modelsSearch = result.data;
			console.log(result.data)
		});
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
		$hjr.selectAll($scope);		
//		if(jQuery('#selectAllFather')[0].checked==true)
//		{
//			for(var i=0;i<jQuery('.selectAllChild').length;i++)
//			{
//				jQuery('.selectAllChild')[i].checked=true;
//			}
//		}
//		else
//		{
//			for(var i=0;i<jQuery('.selectAllChild').length;i++)
//			{
//				jQuery('.selectAllChild')[i].checked=false;
//			}
//		}  
	};

	$scope.selectAll = function(){
		$hjr.selectAll($scope);
	};
	$scope.selectAllSearch = function(){
		$hjr.selectAllSearch ($scope);
	};
  //删除操作
    $scope.del = function(){  //删除按钮事件,分为批量删除与单独删除
    	$.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () {
    		for(var i=0;i<jQuery('.selectAllChild').length;i++)
    	    	{	
        			if(jQuery('.selectAllChild')[i].checked == true)
        			{
    		    		$http({
    		    			url:baseUrl+'del',
    					    method: 'POST',            
    					    data: jQuery('.selectAllChild')[i].id,
    					}).then(function (result) {  //正确请求成功时处理  
    					    $hjr.tips("删除成功！");
    					    $scope.models = result.data;
    					    $scope.modifyShow = false;	
    						$scope.listShow = true;
    					    if("" == result.data || jQuery('#selectAllFather')[0].checked==true){init(1);}
    					});
        			}
    	    	}

    	});  
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
   
//添加按钮事件	
	$scope.add = function(){
		$hjr.add($scope);
    };

//编辑按钮事件
    $scope.edit = function(model){
		$hjr.edit($scope,model);
    };

	$scope.view = function(model){
		$scope.listShow = false;
		modifyState = "edit";
		$scope.formData = model;//点击编辑后数据匹配

	};
//显示按钮事件
	  $scope.list = function(){
		  $hjr.list($scope);
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
 	     init(1);	   
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