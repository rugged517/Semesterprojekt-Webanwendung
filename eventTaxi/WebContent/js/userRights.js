function checkForLogin() {
	if(checkLogin('checkCookie')){
		$(document).ready(function() {
			$(document.body).show();
		});
		return true;		
	}else{
		window.stop();
		window.location.href = 'login.html?note=noLogin';
		return false;
	}
}

function noRightsReLoc(){
	window.stop();
	window.location.href = 'test_page.html?note=noRights';
}


function checkForOrganizer() {
	if(checkLogin('checkOrganizer')){
		$(document).ready(function() {
			$(document.body).show();
		});
		return true;
	}else{
		noRightsReLoc()
	}
}

function checkForAdmin() {
	if(checkLogin('checkAdmin')){
		$(document).ready(function() {
			$(document.body).show();
		});
		return true;
	}else{
		noRightsReLoc()
	}
}

function checkLogin(checkTask) {
	var result=false;
	$.ajax({
		type : 'POST',
		url : 'Login',
		dataType : 'xml',
		async: false,
		data : {
			task : checkTask
		},
		success : function(xml) {
			if ($(xml).find('status').text() == 'true') {
				// if login ok
				result= true;
			
			}
		}
	});
	return result;
}


function checkForRights(checkTask){
	var result=false
	$.ajax({
		type : 'POST',
		url : 'Login',
		dataType : 'xml',
		async: false,
		data : {
			task : checkTask
		},
		success : function(xml) {
			if ($(xml).find('status').text() == 'true') {
				// if user is Organizer
				result=true;
			
			} else {
				// if user is not an Organizer
				noRightsReLoc();
				result=false;
			}
		}
	});
	return result;
}

function setRights(newRights, eMail){
	var result=false;
	$.ajax({
		type : 'POST',
		url : 'UserStatus',
		dataType : 'xml',
		async: false,
		data : {
			userEMail : eMail,
			task : newRights
		},
		success : function(xml) {
			if ($(xml).find('status').text() != 'true') {
			var error=$(xml).find('status').text();
			
			$('#info-alert').removeClass('alert-success').addClass('alert-danger');
			
				$('#info-alert').show();
				if(error=='noLogin'){
					window.location.href = 'login.html?note=noLogin';
				}else if(error=='noRights'){
					error='Sie besitzen nicht die Berechtigung für diese Aktion.';
				}else{
					error='Ein interner Fehler ist aufgetreten.';
				}
				$('#info-alert').text(error);
			} else {
				// if update/delete ok
				$('#info-alert').removeClass('alert-danger').addClass('alert-success');
				
				if(newRights=='delete'){
					var target=$(xml).find('target').text();
					if(target=='self'){
						window.stop();
						window.location.href = 'login.html?note=accountDeleted';
					}else{
						$('#info-alert').text('Der User wurde gelöscht.');
					}
				}else{				
					// new rights are ok
					$('#info-alert').text('Die neuen Berechtigungen wurden übernommen.');
				}
				result= true;
			}
		}
	});
	$('#info-alert').show();
	return result;
}
