var loginApp = angular.module('loginApp', [ 'ngRoute']); 

loginApp.controller('LoginController',function ($scope,$http,$location,$window) { 	
	$scope.formData = [];
	$scope.submit = function(){  //按钮事件
		console.log($scope.formData)
		$http({
	        url:'/login/check',
	        method: 'POST',            
	        data: JSON.stringify($scope.formData),
	    }).then(function (result) {  //正确请求成功时处理  
	       
	    	$('.mask,.dialog').show();
			$('.dialog .dialog-bd p').html('请输入管理员账号');
			
//			$('.mask,.dialog').hide();
//			location.href='index.html';
	    }).catch(function (result) { //捕捉错误处理  
	        alert(result.data.Message);  
	    });
    };  
}); 