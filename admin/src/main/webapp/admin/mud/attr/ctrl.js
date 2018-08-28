mainApp.controller('attrController',function ($scope,$http,$location,$window,$hjr) {
	//数据初始化
	var	baseUrl = "/mud/attr/";
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
		$scope.formData = {};
		modifyState = "add";
		$scope.listShow = false;
		$scope.editHide = true;//使能编辑隐藏的字段
    };

//编辑按钮事件
    $scope.edit = function(model){ 
    	$scope.listShow = false;
    	modifyState = "edit";
    	$scope.formData = model;//点击编辑后数据匹配
    	
    };
//显示按钮事件
	  $scope.list = function(){
		  $scope.listShow = true;
	  };
	
//  搜索
	 $scope.search = function(){  
		 search = { "search": [{"whereFiled":"role","where":$scope.searchData.drop,"likeFiled":"realname","like":$scope.searchData.str}]};
 	     init(1);	   
	 }
	
}); 