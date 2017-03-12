//contains all functions for create_event.html

checkForOrganizer();

// store datetime
var startDate = createDateObj('_start');
var endDate = createDateObj('_end');
var deadDate = createDateObj('_dead');

// stores required informations for datefields
function createDateObj(name) {
	return {
		id : name,
		date : null
	};
}

// setup datefields on page load
var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
		nowTemp.getDate(), 0, 0, 0, 0);

window.onload = function() {
	var dateArray = [ startDate, endDate, deadDate ];

	dateArray.forEach(function(e) {
		$('#in' + e.id).val('');
		addDateFunction(e);
	});

}

function addDateFunction(dateObj) {
	$('#ev' + dateObj.id).datetimepicker({
		weekStart : 1,
		format : 'dd.MM.yyyy hh:mm',
		pickSeconds : false,
		startDate : now
	}).on('changeDate', function(ev) {
		dateObj.date = new Date(ev.date);
		checkDate();
	}).data('datetimepicker');

}

// compares datevalues and shows response on error
function checkDate() {
	var error = null;
	if (startDate.date != null) {
		if (endDate.date != null) {
			if (startDate.date >= endDate.date) {
				error = 'Der ' + $('#lb_in_start').text() + ' muss vor dem '
						+ $('#lb_in_end').text() + ' liegen.';
			}
		}
		if (deadDate.date != null) {
			if (startDate.date < deadDate.date) {
				error = 'Der ' + $('#lb_in_dead').text() + ' muss vor dem '
						+ $('#lb_in_end').text() + ' liegen.';
			}
		}
		if (startDate.date < now) {
			error = 'Der ' + $('#lb_in_start').text()
					+ ' muss in der Zukunft liegen.';
		}
		if (error == null) {
			$('#info-alert').hide();
			return true;
		}

	} else {
		error = 'Der ' + $('#lb_in_start').text() + ' muss angegeben werden.';
	}

	$('#info-alert').text(error);
	$('#info-alert').show();
	return false;
}

// setup form submit functions
$(document).ready(function() {

	$('#info-form').submit(function() {
		var error = 'Bitte überprüfend Sie die Eingaben.';
		if (checkDate() && checkEMails()) {

			$('#info-alert-submit').hide();
			return true;

		}
		$('#info-alert-submit').text(error);
		$('#info-alert-submit').show();
		return false;
	});

	$('#addedFields-form').submit(function() {
		return false;
	});

	var evlink = $.urlParam('event');
	if (evlink != null) {
		if (checkEditRights(evlink)) {
			$('#submitEvent').text('Änderungen übernehmen');
			fillEvent(evlink);
		}
	}

});

function checkEditRights(evlink) {
	var result = false;
	$.ajax({
		type : 'POST',
		url : 'Login',
		dataType : 'xml',
		async : false,
		data : {
			task : 'checkEditEvent',
			evlink : evlink
		},
		success : function(xml) {
			if ($(xml).find('status').text() == 'true') {
				result = true;
			}
		}
	});
	return result;
}

function fillEvent(evlink) {
	$.ajax({
		type : 'POST',
		url : 'CreateEvent',
		dataType : 'xml',
		data : {
			task : 'getEventData',
			evlink : evlink
		},
		success : function(xml) {
			var fields = [ 'title', 'surname', 'name', 'eMail', 'phonenumber',
					'company', 'street', 'postcode', 'city', 'min', 'max',
					'text' ]
			fields.forEach(function(e) {
				value = $(xml).find(e).text();
				if (value != '') {
					$('#ev_' + e).val(value);
				}
			});

			startDate.date = new Date($(xml).find('start').text());
			insertDateValues(startDate.date, 'start');

			endDate.date = new Date($(xml).find('end').text());
			insertDateValues(endDate.date, 'end');

			deadDate.date = new Date($(xml).find('dead').text());
			insertDateValues(deadDate.date, 'dead');

		}
	});

}

function insertDateValues(d, field) {
	$('#in_' + field).val(
			("00" + d.getDate()).slice(-2) + "."
					+ ("00" + (d.getMonth() + 1)).slice(-2) + "."
					+ d.getFullYear() + " " + ("00" + d.getHours()).slice(-2)
					+ ":" + ("00" + d.getMinutes()).slice(-2));
}

function miMaParticipant() {

	var maxVal = parseFloat($('#ev_max').val());

	if (maxVal != 0) {
		$('#ev_max').attr('min', $('#ev_min').val());

		var minVal = parseFloat($('#ev_min').val());
		if (minVal > maxVal) {
			$('#ev_max').val($('#ev_min').val());
		}
	} else {
		$('#ev_max').attr('min', '0');
	}
}

// send eMail input to server to check if input is real eMail
function checkEMails() {
	var eMails = 'task=checkEMails';
	var result = true;
	$('input[type=eMail]').each(
			function() {
				eMails = eMails + '&id=' + $(this).attr('id') + '&eMail='
						+ $(this).val();
				$(this).removeClass('errorField');
			});

	$.ajax({
		type : 'POST',
		url : 'CreateEvent',
		dataType : 'xml',
		async : false,
		data : eMails,
		success : function(xml) {
			$(xml).find("error").each(function() {
				result = false;
				var id = $(this).text();
				$('#' + id).addClass('errorField');
			});
		}
	});
	return result;
}

// TODO check Event ifno and show preview
function showEventWindow() {

	// check input
	// $('#modal-body').html(getEventStatus('buildEvent'));
	// $('#showModal').modal();
	// TODO show window
	alert('Diese Funktion steht in der Demo leider nicht zur Verfügung.');
}

var fieldID = 0;

function addField(type, question) {
	fieldID++;
	$('#addedFields')
			.append(
					'<div class="form-group addedField" id="af-'
							+ fieldID
							+ '"><h4><button type="button" class="btn btn-danger" onclick="$(\'#af-'
							+ fieldID
							+ '\').remove()">X</button>&nbsp;<span id="af-Type-'
							+ fieldID
							+ '">'
							+ type
							+ '</span></h4><div style="display: none" id="info-alert-af-'
							+ fieldID
							+ '" class="alert alert-danger col-sm-12">Bitte überprüfen Sie die Eingaben.</div><br><span id="fs-'
							+ fieldID
							+ '"><label for="FQ-'
							+ fieldID
							+ '">Frage</label><input id="FQ-'
							+ fieldID
							+ '" type="text" value="'
							+ question
							+ '" placeholder="Frage" class="form-control" required>'
							+ '<label for="requiredCB-' + fieldID
							+ '"><input type="checkbox" name="requiredCB-'
							+ fieldID + '" id="requiredCB-' + fieldID
							+ '" value="true">&nbsp;Pflichtangabe</label><br>'
							+ '</span><hr></div>');

}

function addText(question) {
	addField('Textfeld', question);
	$('#fs-' + fieldID)
			.append(
					'<label for="textMax-'
							+ fieldID
							+ '">Minimale Antwortlänge (0 = keine Pflichtangabe)</label><input id="textMin-'
							+ fieldID
							+ '" min="0" type="number" placeholder="Minimale Antwortlänge" value="0" class="form-control"required>'
							+ '<label for="textMax-'
							+ fieldID
							+ '">Maximale Antwortlänge (0 = unbegrenzt)</label><input id="textMax-'
							+ fieldID
							+ '" min="0" type="number" placeholder="Maximale Antwortlänge" value="0" class="form-control"required>'
							+ '<label for="textareaCB-'
							+ fieldID
							+ '"><input onclick="changeTextfield('
							+ fieldID
							+ ')" type="checkbox" name="textareaCB-'
							+ fieldID
							+ '" id="textareaCB-'
							+ fieldID
							+ '" value="true">&nbsp;Als Textarea darstellen</label>');
	$('#requiredCB-' + fieldID).attr('onchange',
			'reqMiMa(\'text\', \'' + fieldID + '\')');

	return fieldID;
}

function changeTextfield(fieldID) {

	var minIn = $('#textMin-' + fieldID);
	var maxIn = $('#textMax-' + fieldID);

	if ($('#textareaCB-' + fieldID).prop('checked')) {
		minIn.prop('disabled', true);
		maxIn.prop('disabled', true);
		minIn.val('0');
		maxIn.val('0');
	} else {
		minIn.prop('disabled', false);
		maxIn.prop('disabled', false);
	}

}

function addDate(question) {
	addField('Datum', question);
	$('#fs-' + fieldID)
			.append(
					'Datumsbereich angeben?<br>'
							+ '<div style="display: none" id="info-alert-'
							+ fieldID
							+ '"class="alert alert-danger col-sm-12">Das kleinste Datum muss kleiner sein als das größte Datum! Z.B. kleinstes Datum=1990 und größtes Datum=2017</div>'
							+ '<label for="minDateBox-'
							+ fieldID
							+ '"><input type="checkbox" name="minDateBox-'
							+ fieldID
							+ '" id="minDateBox-'
							+ fieldID
							+ '" value="true" onclick="addMiMaDate(\''
							+ fieldID
							+ '\', \'Min\')">&nbsp;kleinstes Datum</label>'
							+ '<div class="form-group" id="dateAddMinS-'
							+ fieldID
							+ '"></div><label for="maxDateBox-'
							+ fieldID
							+ '"><input type="checkbox" name="maxDateBox-'
							+ fieldID
							+ '" id="maxDateBox-'
							+ fieldID
							+ '" value="true" onclick="addMiMaDate(\''
							+ fieldID
							+ '\', \'Max\')">&nbsp;größtes Datum</label>'
							+ '<div class="form-group" id="dateAddMaxS-'
							+ fieldID + '"></div>');
	localStorage.setItem('dateMin-' + fieldID, '');
	localStorage.setItem('dateMax-' + fieldID, '');
	return fieldID;
}

function addMiMaDate(fID, miMa) {
	var el = $('#' + 'date' + miMa + '-' + fID);
	if (el.length) {
		localStorage.setItem('date' + miMa + '-' + fID, null);
		el.remove();
		$('#info-alert-' + fID).hide();
	} else {
		$('#dateAdd' + miMa + 'S-' + fID)
				.append(
						'<div id="date'
								+ miMa
								+ '-'
								+ fID
								+ '" class="input-append date">'
								+ '<input class="date-field" id="d'
								+ miMa
								+ '-'
								+ fID
								+ '" type="text" required readonly>'
								+ '<span class="add-on date-field">'
								+ '<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>'
								+ '</span>' + '</div>');
		addMinMaxDateFunction('date' + miMa + '-' + fID, fID);
	}
}

function addMinMaxDateFunction(id, fID) {
	$('#' + id).datetimepicker({
		weekStart : 1,
		format : 'dd.MM.yyyy hh:mm',
		pickSeconds : false,
	}).on('changeDate', function(ev) {
		localStorage.setItem(id, new Date(ev.date));
		checkAddedDates(fID);
	}).data('datetimepicker');
}
function checkAddedDates(fID) {
	var minDate = new Date(localStorage.getItem('dateMin-' + fID));
	var maxDate = new Date(localStorage.getItem('dateMax-' + fID));

	if (minDate != null && maxDate != null) {
		if (minDate >= maxDate) {
			$('#info-alert-' + fID).show();
			return false;
		}
	}
	$('#info-alert-' + fID).hide();
	return true;
}

function addSelect(question) {
	addField('Dropdownliste', question);
	addOptions(question);
	return fieldID;
}

function addCheckbox(question) {
	addField('Checkboxen', question);
	$('#fs-' + fieldID)
			.append(
					'<p>Bei Auswahl von Mindest Auswahl = 1 und Maximale gleichzeitige Auswahl = 1 werden Radiobuttons anstelle von Checkboxen dargestellt.</p><label for="checkboxMin-'
							+ fieldID
							+ '">Mindest Auswahl (0 = Es muss keine Option ausgewählt werden / keine Pflichtangabe)</label><input id="checkboxMin-'
							+ fieldID
							+ '" min="0" type="number" value="0" class="form-control"required>'
							+ '<label for="checkboxMax-'
							+ fieldID
							+ '">Maximale gleichzeitige Auswahl (0 = Alle können gleichzeitig ausgewählt werden)</label><input id="checkboxMax-'
							+ fieldID
							+ '" min="0" type="number" value="0" class="form-control"required>');
	$('#requiredCB-' + fieldID).attr('onchange',
			'reqMiMa(\'checkbox\', \'' + fieldID + '\')');
	addOptions(question);
}

function reqMiMa(type, fID) {

	if ($('#requiredCB-' + fID).prop('checked')) {
		$('#' + type + 'Min-' + fID).attr('min', '1');
		if (parseFloat($('#' + type + 'Min-' + fID).val()) < 1) {
			$('#' + type + 'Min-' + fID).val('1');
		}
	} else {
		$('#' + type + 'Min-' + fID).attr('min', '0');
	}
}

function addOptions(question) {
	$('#fs-' + fieldID)
			.append(
					'Antwortmöglichkeiten:'
							+ '<div id="selectOptions-'
							+ fieldID
							+ '" class="form-group"></div><p><button type="button" class="btn btn-success" onclick="addSelectOpField(\''
							+ fieldID
							+ '\', \'\')">Weitere Antwort</button></p>'
							+ '<div style="display: none" id="info-alert-'
							+ fieldID
							+ '"class="alert alert-danger col-sm-12">Es müssen mindestens zwei Antwortmöglichkeiten zur Auswahl stehen.</div>');
	if (question == '') {
		addSelectOpField(fieldID, '');
		addSelectOpField(fieldID, '');
	}
}

var selOpNr = 0;
function addSelectOpField(fID, value) {
	selOpNr++;
	$('#selectOptions-' + fID)
			.append(
					'<span id="selOpNr-'
							+ selOpNr
							+ '" class="selectSpan"><input class="form-control SelectOp-'
							+ fID
							+ '" type="text" value="'
							+ value
							+ '" placeholder="Antwort" required><button type="button" class="btn btn-danger selectBtn" onclick="removeSelOp(\''
							+ fID + '\', \'' + selOpNr + '\')">X</button>');
	$('#info-alert-' + fID).hide();
}

function removeSelOp(fID, selOpNr) {
	if ($('.SelectOp-' + fID).length > 2) {
		$('#selOpNr-' + selOpNr).remove()
	} else {
		$('#info-alert-' + fID).show();
	}
}

var eMailID = 0;
function addEMail() {
	eMailID++;
	$('#addedEMails')
			.append(
					'<span id="ae-'
							+ eMailID
							+ '" class="selectSpan"><input class="form-control AE" id="aeIn-'
							+ eMailID
							+ '" type="eMail" placeholder="E-Mail" required><button type="button" class="btn btn-danger selectBtn" onclick="javascript: $(\'#ae-'
							+ eMailID + '\').remove()">X</button>');
}

function showSubsWindow() {
	$('#modal-body').html(getAddedFieldsStatus('buildAddedField', ''));
	$('#showModal').modal();
}

function getAddedFieldsStatus(serverTask, link) {
	var result = true;
	var newHTML = '';
	$('.addedField').each(function() {
		var fID = $(this).attr('id').replace('af-', '')
		var field = {
			id : fID,
			question : $('#FQ-' + fID).val(),
			isRequired : $('#requiredCB-' + fID).prop('checked'),
			type : $('#af-Type-' + fID).html()
		}

		if (field.type == 'Textfeld') {
			field.type = 'text';
			field.min = $('#textMin-' + fID).val();
			field.max = $('#textMax-' + fID).val();
			field.isTextarea = $('#textareaCB-' + fID).prop('checked');
		} else if (field.type == 'Datum') {
			field.type = 'date';
			field.minDate = localStorage.getItem('dateMin-' + fID);
			field.maxDate = localStorage.getItem('dateMax-' + fID);
			if (field.minDate != '') {
				field.minDate = new Date(field.minDate).toISOString();
			}
			if (field.maxDate != '') {
				field.maxDate = new Date(field.maxDate).toISOString();
			}
		} else {
			field.options = new Array();

			$('.SelectOp-' + fID).each(function() {
				field.options.push($(this).val());
			});
			if (field.type == 'Checkboxen') {
				field.type = 'checkbox';
				field.min = $('#checkboxMin-' + fID).val();
				field.max = $('#checkboxMax-' + fID).val();
			} else {
				field.type = 'select';
			}
		}

		field.task = serverTask;
		field.link = link;

		$.ajax({
			type : 'POST',
			url : 'CreateEvent',
			dataType : 'text',
			async : false,
			data : field,
			success : function(response, status, xhr) {
				var ct = xhr.getResponseHeader("content-type") || "";
				if (ct.indexOf('html') > -1) {
					newHTML = newHTML + response;
					$('#af-Type-' + fID).removeClass('errorField');
					$('#info-alert-af-' + fID).hide();
					$('.dateID').each(function() {
						activateDateField($(this).text());
					});
				}
				if (ct.indexOf('xml') > -1) {
					$('#af-Type-' + fID).addClass('errorField').focus();
					$('#info-alert-af-' + fID).show();
					result = false;
				}
			}
		});
	});

	if (result) {
		return newHTML;
	}
	return result;

}
function activateDateField(id) {
	localStorage.setItem('dateAdd-' + id, '');

	var min = new Date($('#dateMin-' + id).text());
	var max = new Date($('#dateMin-' + id).text());

	$('#dateAdd-' + id).datetimepicker({
		weekStart : 1,
		format : 'dd.MM.yyyy hh:mm',
		pickSeconds : false,
		start : min,
		end : max
	}).on('changeDate', function(ev) {
		localStorage.setItem('dateAdd-' + id, new Date(ev.date));
	}).data('datetimepicker');
	alert('3');
}

function submitEvent() {
	// change onsubmit function to use browser field validation
	$('#event-form').attr('onsubmit', 'saveEvent()');
	getEventStatus('saveEvent');
	// $('#sendEventBtn').click();
}

function saveEvent() {

	// change onsubmit function back to show Modal function
	$('#event-form').attr('onsubmit', 'showEventWindow()');

	checkEMails();
	getEventStatus('saveEvent');

}

function getEventStatus(serverTask) {
	var result = true;
	$
			.ajax({
				type : 'POST',
				url : 'CreateEvent',
				dataType : 'text',
				async : false,
				data : {
					title : $('#ev_title').val(),
					surname : $('#ev_surname').val(),
					name : $('#ev_name').val(),
					eMail : $('#ev_eMail').val(),
					phonenumber : $('#ev_phonenumber').val(),
					company : $('#ev_company').val(),
					street : $('#ev_street').val(),
					postcode : $('#ev_postcode').val(),
					city : $('#ev_city').val(),
					start : new Date(startDate.date).toISOString(),
					end : new Date(endDate.date).toISOString(),
					dead : new Date(deadDate.date).toISOString(),
					min : $('#ev_min').val(),
					max : $('#ev_max').val(),
					text : $('#ev_text').val(),
					task : serverTask
				},
				success : function(xml) {
					$(xml)
							.find("status")
							.each(
									function() {
										var link = $(this).text();
										if (link != "false") {
											getAddedFieldsStatus(
													'saveAddedField', link);
											window.location.href = 'test_page.html?note=eventCreated&link='+link;
										} else {
											result = false;
										}
									});
				}
			});
	return result;
}
