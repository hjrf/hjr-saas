mainApp.controller('LogController',function ($scope,$http,$location,$window) { 	
	var	pagenumber = 3; //pagenumber：每页记录数  
	var	baseUrl = "/logger/"; //pagenumber：每页记录数
	var customerArray = new Array();//往后台传的参数集合
	var page = {"pagenumber":pagenumber,"current":1};//往后台传的参数集合-分页功能
	var search = { "search": [{"whereFiled":"","where":"","likeFiled":"","like":""}]};
	//参数初始化	
	var modifyState = "";
	$scope.listShow = true;
	$scope.formData = {};
	$scope.models = [];
	//页面初始化	
	init(1);
	function init(p)
	{	 
		page = { "page": [{"pagenumber":pagenumber,"current":p}]};
		customerArray.push(page);
		customerArray.push(search);
		$http({
			url:baseUrl+'show',
            method: 'POST',
            data:JSON.stringify(customerArray),
        }).then(function (result) {  //正确请求成功时处理  
    	    $scope.models = result.data;
    	    if(result.data == ""){return}
        	totalcount = result.data[0].recordNum;//totalcount：记录数  
    	    pageCount = Math.ceil(totalcount/pagenumber); 	
        	$("#pager").pager({
        		pagenumber: p,
        		pagecount: pageCount,
        		buttonClickCallback: init 
        	});	       
        }).catch(function (result) { //捕捉错误处理  
            alert(result.data.Message);  
        });
	}

//	全选
	$scope.selectAllChild = [];
	$scope.selectAll = function(){  
		if(jQuery('#selectAllFather')[0].checked==true)
		{
			for(var i=0;i<jQuery('.selectAllChild').length;i++)
			{
				jQuery('.selectAllChild')[i].checked=true;
			}
		}
		else
		{
			for(var i=0;i<jQuery('.selectAllChild').length;i++)
			{
				jQuery('.selectAllChild')[i].checked=false;
			}
		}  
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
    					    $scope.tips("删除成功！");
    					    $scope.models = result.data;
    					    $scope.modifyShow = false;	
    						$scope.listShow = true;
    					    if("" == result.data || jQuery('#selectAllFather')[0].checked==true){init(1);}
    					}).catch(function (result) { //捕捉错误处理  
    					    alert(result.data.Message);  
    					});
        			}
    	    	}

    	});  
    };
    
//  搜索
	 $scope.search = function(){  
		 search = { "search": [{"whereFiled":"role","where":$scope.searchData.drop,"likeFiled":"realname","like":$scope.searchData.str}]};
 	     init(1);	   
	 }
	
}); 