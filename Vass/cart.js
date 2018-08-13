<script>
		var app = angular.module('myApp', []);
		app.controller('MainCtrl', function($scope) {
			$scope.user = {
				websites: [
				{}
				]
			};
			$scope.remove = function(index) {
				$scope.user.websites.splice(index, 1);
			};
			$scope.add = function() {
				$scope.user.websites.push({ });
			};
		});
	</script>