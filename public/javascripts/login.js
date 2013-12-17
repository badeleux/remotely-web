var app = angular.module('ladda', []);

app.controller("LoginController", function($scope, $http, $window) {
  $scope.formData = {}

  $scope.processForm = function() {
  $scope.loading = true
    $http({
        method : 'POST',
        url    : 'signin',
        data   : $scope.formData,
    })
        .success(function(data) {
            $scope.loading = false
            console.log(data)
            if(!data.success) {
                console.log("Some errors in validation")
                $scope.message = "Blędy!"
            }
            else {
                console.log("Redirecting")
                $scope.message = "Zalogowano"
                $window.location.href = "http://" + $window.location.host
            }
        })
  };
});