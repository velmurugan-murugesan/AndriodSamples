var app = angular.module('noteMyEvent');

app.controller('homeCtrl', function($scope,$modal,$log){


   $scope.checkBoxModel = {
       value1 : true,
       value2 :'YES'
   }

    $scope.openAddModal = function(template) {
        var modalInstance = $modal.open({
            templateUrl: "views/common/modals/" + template + '.html',
            controller: 'addModalCtrl',
            size: 'lg',
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });
        modalInstance.result.then(function(selectedItem) {
            $scope.selected = selectedItem;
        }, function() {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };


    $scope.checkedModel ={
        value1 : '',
        value2 : '',
        value3 : ''
    };


});
app.controller('addModalCtrl', function($scope, $modalInstance, items) {
            $scope.items = items;

            $scope.close = function() {
                $modalInstance.dismiss('cancel');
           };
           $scope.saveChanges = function(){
               $modalInstance.dismiss('cancel');
           };

    $scope.inputOnTimeSet = inputOnTimeSet;

    function inputOnTimeSet(newDate) {
        // If you are not using jQuery or bootstrap.js,
        // this will throw an error.
        // However, can write this function to take any
        // action necessary once the user has selected a
        // date/time using the picker
        $log.info(newDate);
        $('#dropdown3').dropdown('toggle');
    }

});
