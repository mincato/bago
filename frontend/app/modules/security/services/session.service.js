'use strict';

angular.module('security').factory('SessionService', ['StorageService', 'Configuration',
	function(StorageService, Configuration) {
        
        var userCached = null;
        var permissionsCached = null;

		var saveUser = function(user) {
			StorageService.user = JSON.stringify(user);
            userCached = user;
		};
        
        var savePermissions = function(permissions) {
			StorageService.permissions = JSON.stringify(permissions);
            permissionsCached = permissions;
        };

		var loadUser = function() {
            
            if (userCached !== null) {
                return userCached;
            }
            
			var user = StorageService.user;
			if (typeof user === 'string') {
				user = JSON.parse(user);
			}
            userCached = user;
			return user;
		};
        
        var loadPermissions = function() {
            
            if (permissionsCached !== null) {
                return permissionsCached;
            }
            
			var permissions = StorageService.permissions;
			if (typeof user === 'string') {
				permissions = JSON.parse(permissions);
			}
            permissionsCached = permissions;
			return permissions;
		};


		var getToken = function() {
			var user = loadUser();
			return (user) ? user.token : null;
		};

		var invalidate = function() {
			StorageService.removeItem('user');
            StorageService.removeItem('permissions');
            userCached = null;
            permissionsCached = null;
		};

		var updateToken = function(newToken) {
			var user = loadUser();
			user.token = newToken;
			saveUser(user);
		};
        
        var isLogged = function() {
            return (typeof window.sessionStorage.user !== 'undefined');
        };

		return {
			saveUser: saveUser,
            savePermissions: savePermissions,
			loadUser: loadUser,
            loadPermissions: loadPermissions,
			getToken: getToken,
			invalidate: invalidate,
			updateToken : updateToken,
            isLogged: isLogged
		};
	}
]);
