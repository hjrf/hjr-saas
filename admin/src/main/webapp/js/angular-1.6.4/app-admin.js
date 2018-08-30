var mainApp = angular.module('mainApp', [ 'ngRoute'])
var baseUrl = "../admin/"
mainApp.config(function($routeProvider, $locationProvider) {
$routeProvider
	.when('/', {
		templateUrl: baseUrl+'system/user/show.html',
	    controller: 'userController'
	})
	.when('/system/user', {
		templateUrl: baseUrl+'system/user/show.html',
	    controller: 'userController'
	})
	.when('/system/role', {
		templateUrl: baseUrl+'system/role/show.html',
	    controller: 'roleController'
	})
	.when('/system/log', {
		templateUrl: baseUrl+'system/log/show.html',
	    controller: 'logController'
	})
	.when('/mud/attr', {
		templateUrl: baseUrl+'mud/attr/show.html',
		controller: 'attrController'
	})
	.when('/mud/character', {
		templateUrl: baseUrl+'mud/character/show.html',
		controller: 'characterController'
	})
	.when('/mud/items', {
		templateUrl: baseUrl+'mud/items/show.html',
		controller: 'itemsController'
	})
	.when('/mud/joinChaIte', {
		templateUrl: baseUrl+'mud/joinChaIte/show.html',
		controller: 'joinChaIteController'
	})
	.when('/mud/joinChaSki', {
		templateUrl: baseUrl+'mud/joinChaSki/show.html',
		controller: 'joinChaSkiController'
	})
	.when('/mud/joinChaTalk', {
		templateUrl: baseUrl+'mud/joinChaTalk/show.html',
		controller: 'joinChaTalkController'
	})
	.when('/mud/joinIteSki', {
		templateUrl: baseUrl+'mud/joinIteSki/show.html',
		controller: 'joinIteSkiController'
	})
	.when('/mud/pangbai', {
		templateUrl: baseUrl+'mud/pangbai/show.html',
		controller: 'pangbaiController'
	})
	.when('/mud/room', {
		templateUrl: baseUrl+'mud/room/show.html',
		controller: 'roomController'
	})
	.when('/mud/skill', {
		templateUrl: baseUrl+'mud/skill/show.html',
		controller: 'skillController'
	})
	.when('/mud/task', {
		templateUrl: baseUrl+'mud/task/show.html',
		controller: 'taskController'
	})
	.when('/mud/work', {
		templateUrl: baseUrl+'mud/work/show.html',
		controller: 'workController'
	})
	.when('/mud/map', {
		templateUrl: baseUrl+'mud/map/show.html',
		controller: 'mapController'
	})
	.otherwise({
		redirectTo: '/404'
	});
});    

mainApp.controller('MainController',function ($scope,$http,$location,$window) { 	
	$scope.navTree = [];
	$(function() {
		$.getJSON("../admin/system/role/navTree.json",function(data){
			$scope.navTree = data;
		});
	});
	
	
	
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
		$(function(){
			//左侧导航菜单效果
			$('.side-menu li dt').click(function(){
				$(this).parents('li').addClass('open');
				$(this).parents('.side-menu').find('.InitialPage').removeClass('active');
				$(this).parents('li').siblings().removeClass('open');
				
			});
			$('.side-menu dd a').click(function(){
				$(this).parents('li').addClass('open');
				$(this).parents('li').siblings().removeClass('open')
			});
			$('.side-menu li').each(function(){
				//判断链接相当（当前页面导航样式）
				if($(this).find('a').attr('href') == window.location.pathname){
					$(this).addClass('open');
					$(this).siblings().find('.InitialPage').removeClass('active');
					$('.InitialPage').removeClass('active');
				}else if($('.side-menu h2 a').attr('href') == window.location.pathname){
					$('.InitialPage').addClass('active');
				}   
			});
		    //Tab选项卡.
		    $('.tab-nav li').click(function(){
		    	var liIndex = $('.tab-nav li').index(this);
		    	$(this).addClass('active').siblings().removeClass('active');
		    	$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
		    });
		    //Button下拉菜单
		    $('.btn-drop-group .btn').click(function(e){
		    	$(this).siblings('.drop-list').show();
		    	$(this).parent().siblings().find('.drop-list').hide();
		    	$(document).one('click', function(){
			        $('.btn-drop-group .drop-list').hide();
			    });
			    e.stopPropagation();
		    });
		    	//点击list将当前值复制于button
			    $('.btn-drop-group .drop-list li').click(function(){
			    	$(this).parent().parent().hide();
			    });
			//左侧菜单收缩
			$('.top-hd .hd-lt').click(function(){
				$('.side-nav').toggleClass('hide');
				$('.top-hd,.main-cont,.btm-ft').toggleClass('switchMenu');
				$('.top-hd .hd-lt a').toggleClass('icon-exchange');
				localStorage.setItem('setLayOut1','hide');
				localStorage.setItem('setLayOut2','switchMenu');
				localStorage.setItem('setLayOut3','icon-exchange');
				if(!$('.side-nav').hasClass('hide')){
					localStorage.removeItem('setLayOut1');
					localStorage.removeItem('setLayOut2');
					localStorage.removeItem('setLayOut3');
				}
			});
			$('.side-nav').addClass(localStorage.getItem('setLayOut1'));
			$('.top-hd,.main-cont,.btm-ft').addClass(localStorage.getItem('setLayOut2'));
			$('.top-hd .hd-lt a').addClass(localStorage.getItem('setLayOut3'));
			

			//弹出层基础效果（确认/取消/关闭）
			$('.JyesBtn').click(function(){
				$(this).parents('.dialog').hide();
				if($('.mask').attr('display','block')){
					$('.mask').hide();
				}
			});
			$('.JnoBtn,.JclosePanel').click(function(){
				$(this).parents('.dialog').hide();
				if($('.mask').attr('display','block')){
					$('.mask').hide();
				}
			});
			//基础弹出窗
			$('.JopenPanel').click(function(){
				$('.dialog').show();
				$('.dialog').css('box-shadow','0 0 30px #d2d2d2');
			});
			//带遮罩
			$('.JopenMaskPanel').click(function(){
				$('.dialog,.mask').show();
				$('.dialog').css('box-shadow','none');
			});
			$('.mask').click(function(){
				$(this).hide();
				$('.dialog').hide();
			});
			//自动关闭消息窗口
			$('.Jmsg').click(function(){
				$('dialog').show().delay(5000).hide(0);
			});	
			//安全退出
			$('#JsSignOut').click(function(){
				layer.confirm('确定登出管理中心？', {
				  title:'系统提示',
				  btn: ['确定','取消']
				}, function(){
				  location.href = 'login.html';
				});
			});
		});
    });	
}).directive('ngRepeatFinish', function($timeout) {
    return {
        link: function(scope, element, attr) {
            if(scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});


//全局配置
mainApp.run(function($rootScope) {  

})


mainApp.filter('unique', function () {
	return function (collection, keyname) {
		var output = [],
			keys = [];
		angular.forEach(collection, function (item) {
			var key = item[keyname];
			if (keys.indexOf(key) === -1) {
				keys.push(key);
				output.push(item);
			}
		});
		return output;
	};
});


mainApp.service('$hjr', function() {
    //自动消失提示
	this.tips = function (str) {  	
    	jQuery('dialog').html('<span class="icon-ok"></span>'+str)
		jQuery('dialog').show().delay(3000).hide(0);   		 	
    }
//	全选
	
	this.selectAll = function($scope,fatherId,chaileClass){
		$scope.selectAllChild = [];
		if(jQuery('#'+fatherId)[0].checked==true) {
			for(var i=0;i<jQuery('.'+chaileClass).length;i++) {jQuery('.'+chaileClass)[i].checked=true;}
		}
		else {
			for(var i=0;i<jQuery('.'+chaileClass).length;i++) {jQuery('.'+chaileClass)[i].checked=false;}
		}
	};


	this.defineInit = function($scope){
		$scope.listShow = true;
		$scope.editHide = false;
		$scope.addHide = false;
		$scope.formData = {};
		$scope.models = [];
		$scope.modelsSearch = [];
		$scope.dropData = [];
		$scope.searchData = [];
		$scope.model = [];

		$scope.customerArray = {};//往后台传的参数集合
		$scope.customerArray.page  = {"pagenumber":10,"current":1};//分页
		$scope.customerArray.search  = {"whereFiled":"","where":"","likeFiled":"","like":""};//搜索
		$scope.customerArray.para  = {};//参数
		return $scope;
	};


	this.listInit = function($scope,$http,p){
		$scope.customerArray.page.current =p;
		var para = JSON.stringify($scope.customerArray);
		$http({
			url:$scope.baseUrl+'show',
			method: 'POST',
			data:para,
		}).then(function (result) {  //正确请求成功时处理
			$scope.models = result.data;
			console.log($scope.models);
			if(result.data == ""){return}
			pageCount = Math.ceil( result.data[0].recordNum/$scope.customerArray.page.pagenumber);//页数
			$("#pager").pager({
				pagenumber: $scope.customerArray.page.current,
				pagecount: pageCount,
				buttonClickCallback: $scope.listInit
			});
		});
	};

	this.customerListDataWithArray = function($scope,$http,p,url,pageId){
		$scope.customerArraySearch.page.current = p;
		var para = JSON.stringify($scope.customerArraySearch);
		$http({
			url:url,
			method: 'POST',
			data:para,
		}).then(function (result) {  //正确请求成功时处理
			$scope.modelsSearch = result.data
			if(result.data == ""){return}
			pageCount = Math.ceil(result.data[0].recordNum/$scope.customerArraySearch.page.pagenumber);
			$("#"+pageId).pager({
				pagenumber: $scope.customerArraySearch.page.current,
				pagecount: pageCount,
				buttonClickCallback: $scope.customerListDataWithArray
			});
		});
	};

	this.add = function($scope){
		modifyState = "add";
		$scope.formData = {};

		$scope.listShow = false;
		$scope.editHide = true;
		$scope.addHide = false;

	};


	this.search = function($scope){
		$scope.listInit(1);
	};

	this.del = function($scope,fatherId,chaileClass){
		$.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () {
			for(var i=0;i<jQuery('.'+chaileClass).length;i++)
			{
				if(jQuery('.'+chaileClass)[i].checked == true)
				{
					$http({
						url:$scope.baseUrl+'del',
						method: 'POST',
						data: jQuery('.'+chaileClass)[i].id,
					}).then(function (result) {  //正确请求成功时处理
						$hjr.tips("删除成功！");
						$scope.models = result.data;
						$scope.modifyShow = false;
						$scope.listShow = true;
						if("" == result.data || jQuery('#'+fatherId)[0].checked==true){$scope.listInit(1);}
					});
				}
			}

		})
	};

//编辑按钮事件
	this.edit = function($scope,model){
		console.log(model)
		modifyState = "edit";
		$scope.formData = model;//点击编辑后数据匹配

		$scope.listShow = false;
		$scope.editHide = false;
		$scope.addHide = true;
	};

	//显示按钮事件
	this.list = function($scope){
		$scope.listShow = true;
		$scope.formData = {};
	};

      
});


//页面参数初始化	
var modifyState = "";
var jsonData = {};