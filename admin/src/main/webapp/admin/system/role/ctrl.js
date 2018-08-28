mainApp.controller('roleController',function ($scope,$http,$location,$window) {
	//页面参数初始化	
	var modifyState = "";
	//数据初始化
	var	baseUrl = "/system/role/";
	$scope.listShow = true;
	$scope.formData = {};
	$scope.models = [];		
	//分页参数初始化  
	var	pagenumber = 3; //pagenumber：每页记录数 
	var customerArray = new Array();//往后台传的参数集合
	var page = {"pagenumber":pagenumber,"current":1};//往后台传的参数集合-分页功能
	var search = { "search": [{"whereFiled":"","where":"","likeFiled":"","like":""}]};
	//树初始化
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
	};
	
	function nodesToJson(treeName)
	{
		var nodesObj = $.fn.zTree.getZTreeObj(treeName);
		var simpleNodes = nodesObj.transformToArray(nodesObj.getNodes());
		var jsonArray = [];
		for(var i in simpleNodes)
		{
			if(simpleNodes[i].children != undefined)
			{
				jsonArray.push(simpleNodes[i]);
			}
		}	
		var json = JSON.stringify(jsonArray);
		return json;
	}

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
    					});
        			}
    	    	}

    	});  
    };
    
  //编辑与新增操作
	$scope.submit = function(){  //按钮事件
		//树数据填充
		$scope.formData.permission = nodesToJson("navTree");
		console.log($scope.formData);
		$http({
	        url:baseUrl+''+modifyState+'',
	        method: 'POST',            
	        data: JSON.stringify($scope.formData),
	    }).then(function (result) {  //正确请求成功时处理  
	        if(modifyState == "add")
	        {
	        	$scope.tips("新增成功！");
	        	totalcount = result.data[0].recordNum;//totalcount：记录数  
		    	pageCount = Math.ceil(totalcount/pagenumber); 	
		        init(pageCount);
	        }     	
	        else if(modifyState == "edit")
	        {
	        	$scope.tips("修改成功！");
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
		
		$(function() {
			$.getJSON("../admin/role/navTree.json",function(data){
				zNodes = data;
				$.fn.zTree.init($("#navTree"), setting, zNodes);
			});	
		});
    };
//显示按钮事件	
	$scope.list = function(){  
		$scope.listShow = true;
    };        
//编辑按钮事件
    $scope.edit = function(model){ 
    	$scope.listShow = false;
    	modifyState = "edit";
    	$scope.formData = model;//点击编辑后数据匹配
    	$(function() {
	    	zNodes = eval('(' + model.permission + ')');
	    	$.fn.zTree.init($("#navTree"), setting, zNodes);
    	});
    };

	$scope.tips = function(str){  //提示
		jQuery('dialog').html('<span class="icon-ok"></span>'+str)
		jQuery('dialog').show().delay(3000).hide(0);
	}
	
//  搜索
	 $scope.search = function(){  
		 search = { "search": [{"whereFiled":"role","where":$scope.searchData.drop,"likeFiled":"realname","like":$scope.searchData.str}]};
 	     init(1);	   
	 }

	
	
	
}); 