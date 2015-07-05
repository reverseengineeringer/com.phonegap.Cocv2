/*global clearInterval: false, clearTimeout: false, document: false, event: false, frames: false, history: false, Image: false, location: false, name: false, navigator: false, Option: false, parent: false, screen: false, setInterval: false, setTimeout: false, window: false, XMLHttpRequest: false */
/*global alert: false, confirm: false, console: false, Debug: false, opera: false, prompt: false, WSH: false */
/*global $:false, jQuery:false */


var init,
	googleCallback;
	
(function () {

	//"use strict";


// -----------------------------------------------------------------------------
//
// !GLOBALS
//
// -----------------------------------------------------------------------------

	/**
	 Lifecycle
	 */
	 
	// var network = false,
	// 	i_o = false,
	
	/**
	 DOM
	 */
	var $ = xui,
		content = null,
		header = null,
		activeTab = null,
		activeTitle = null,
		feedback = null,
		hider = null,
        signup = null,
        login = null,
        lastUnclaimedTicket = "",
	/**
	 Map
	 */
		activeMap = null,
		addressBar = null,
		geocoder = null,
        geoCodeIntervalID = null,
        geoCodeIntervalCount = 0,
		crosshair = null,
		maxAccAttempts = 5,
        bestAcc = 9999,
        bestLat = 42.376768,
        bestLon = -71.120967,
		accAttempts = 1,
		reqAccuracy = 40,
		geoAttempts = 1,
		locating = false,
		// mapHeight = false,
		windowHeight = false,
		tip_shown = false,
	/**
	 Report
	 */
		submit_but = null,
        submitTime = 0,
		cancel_but = null,
		first_name = null,
		last_name = null,
		phone_number = null,
		email_address = null,
		description = null,
		// added
		pole_number = null,
		restaurant = null,
		profanity = null,
		missed = null,
		collection_day = null,
 ReportTypes = {pothole: "pothole", streetlight: "streetlight", rodent: "rodent", graffiti: "graffiti", missedpickup: "missedpickup", unshoveledsidewalk: "unshoveledsidewalk", freshpondissue: "freshpondissue", treemaintenance: "treemaintenance", parkmaintenance: "parkmaintenance", trafficsignal: "trafficsignal", trafficsign: "trafficsign", bikerack: "bikerack", sidewalkdefect: "sidewalkdefect", abandonedbicycle:"abandonedbicycle", taxicomplaint: "taxicomplaint"},
	/**
	 Change Address
	 */
		change_street_number = null,
		change_street_name = null,
		matchesList = null,
	/**
	 Status
	 */
		// reports = [
		// 	["01/01/1930", "Pothole", "1620 NE Dekum St.", "COMPLETED"],
		// 	["01/03/1990", "Graffitti", "115 NE 1st Ave.", "IN PROGRESS"],
		// 	["01/08/1910", "Pothole", "1620 NE Dekum St.", "COMPLETED"]
		// ],
	/**
	 HTML
	 */
		Templates = {
			tabs: "<div class='navigation'><ul class='nav'><li id='report' class='selected'><span class='icon'></span> Create Report</li><li id='status'><span class='icon'></span> View Status</li><li id='help'><span class='icon'></span> Help</li></ul></div><div id='tab-divider'></div>",
            index: "<div class=drop-shadow></div><div id=main-selector class=content-home> <h1>I want to report...</h1> <div id=bug><img src=img/bug.png width=80 height=50> </div><fieldset> <select id=report-list name=reports> <option disabled value=()>Please select an item to report<option value=abandonedbicycle>Abandoned bicycle<option value=bikerack>Bike rack issue<option value=freshpondissue>Issue at Fresh Pond<option value=graffiti>Graffiti<option value=icystreet>Icy or snowy street<option value=parkmaintenance>Park maintenance issue<option value=pothole>Pothole<option value=rodent>Rodent<option value=missedpickup>Missed rubbish / recycling pickup<option value=sidewalkdefect>Sidewalk defect<option value=streetlight>Defective streetlight or park light<option value=taxicomplaint>Taxi Complaint or Compliment<option value=trafficsign>Traffic sign complaint<option value=trafficsignal>Traffic signal complaint<option value=treemaintenance>Tree maintenance issue<option value=unshoveledsidewalk>Unshoveled or icy sidewalk </select> <a id=report-list-confirm class='button right'>Continue</a> </fieldset> <div id=help-blurbs> <ul> <li><p>Report an illegally parked or abandoned bicycle. Requests are checked each business day and initial inspections scheduled within two business days of receipt (weather and other circumstances permitting.) If you feel the situation is in need of immediate attention, please call <a href=tel:+16173494800>(617) 349-4800</a> to speak to a staff member.</p></li><li><p>Request installation of a new bike rack, or report issues with an existing bike rack such as damage or vandalism. Please be as specific as you can about the location of your request in the additional info field. The Traffic, Parking, and Transportation Department will respond to this complaint.</p></li><li><p>Issues reported at the Fresh Pond Reservation are reviewed by the Cambridge Water Department within 1-2 business days.</p></li><li><p>Please note that graffiti removal equipment cannot be used from mid-November through March. However, obscene or offensive tags will be painted over immediately year-round until removal can be scheduled.</p></li><li><p>During snow operations, Public Works goals are to chemically treat all major arteries within three hours of when snow begins, to keep main arteries plowed during all stages of a storm, and to clear all streets and the sidewalks bordering City property once a storm has stopped.</p><p>Snow/ice clearing iReport requests are checked on a regular basis when our snow operations center is open. If you feel the situation is an urgent safety issue, please feel free to call our 24 hour emergency line at <a href=tel:+16173494800>(617) 349-4800</a>.</p></li><li><p>Requests are checked each business day and initial inspections scheduled within two business days of receipt (weather and other circumstances permitting.)</p><p><strong>If you feel the situation is an urgent safety concern, please call our 24 hour emergency line at <a href=tel:+16173494800>(617) 349-4800</a> to speak to a staff member.</strong></p></li><li><p>Pothole reports are checked each business day and initial inspections are scheduled within two business days of receipt, weather and other circumstances permitting.</p><p><strong>If you feel the situation is an urgent safety concern, please call our 24 hour emergency line at <a href=tel:+16173494800>(617) 349-4800</a> to speak to a staff member.</strong></p></li><li><p>To assist inspectors with the Inspectional Services Department, please provide detailed information with your report, such as where you saw the rodent, time of day, etc.</p></li><li><p>If you think your recycling, trash, or yard waste was missed, please submit your request for pickup no later than 12 noon the day following collection. Please keep your missed material at the curb.</p><p>Please note that contact information is <strong>required</strong> for this type of report. In the additional info field please indicate what was missed (e.g. 1 trash barrel and 2 recycling toters.)</p></li><li><p>Requests are checked each business day and initial inspections scheduled within two business days of receipt (weather and other circumstances permitting.)</p><p><strong>If you feel the situation is an urgent safety concern, please call our 24 hour emergency line at <a href=tel:+16173494800>(617) 349-4800</a> to speak to a staff member.</strong></p></li><li><p>When filing a report please be as descriptive as possible about the location of the light, ideally including the number attached to the pole approximately six feet above the base, and the behavior of the light (flickering bulb, burned out, buzzing, etc.)</p></li><li><p>Report a complaint or compliment about a Cambridge taxi driver. Please include as much information as possible to assist in identifying the taxi.</p></li><li><p>Report an issue with traffic signs in the City, ranging from missing or vandalized signs to signs that you feel should be moved or changed. The Traffic, Parking, and Transportation Department will respond to these complaints.</p></li><li><p>Report an issue with traffic signals (traffic lights, pedestrian walk signals, etc.) in the city, from non-functional lights or pedestrian signals that are too quick. The Traffic, Parking, and Transportation Department will respond to these complaints.</p></li><li><p>Requests are checked each business day and initial inspections scheduled within two business days of receipt (weather and other circumstances permitting.)</p><p><strong>If you feel the situation is an urgent safety concern, please call our 24 hour emergency line at <a href=tel:+16173494800>(617) 349-4800</a> to speak to a staff member.</strong></p></li><li><p>Property owners must remove snow from sidewalks next to their property within 12 hours of daytime snowfall and before 1:00 PM of overnight snowfall. Ice must be removed within 6 hours of forming.</p></li></ul> </div><div id=logotype></div></div>",
            map: "<div id='top-bar'>%text%</div><div id='map-holder'><div id='map'></div><div id='map-target' class='map-loader'></div></div><div id='map-tools'><div id='map-info-bar'>Finding you on map...</div><div id='bottom-holder' class='trans-on'><figure  class='button left'><div id='photo-holder' class='icon'></div></figure><a id='confirm-but' class='button confirm right'>Confirm location</a></div></div>",
			report: "<div id='top-bar'>%text%</div><div class='content'><form id='report-form' onsubmit='return!1'><fieldset><legend class='hide'>located near:</legend><div class='location'><h3>Located near:</h3><p id='form-address'>%address%</p><a id='change-location' class='button green'>Change</a></div><div class='decor'><hr/></div><ol class='report-form'><li><label for='first_name'>First Name:</label><input id='first_name'></li><li><label for='last_name'>Last Name:</label><input id='last_name'></li><li><label for='phone_number'>Phone:</label><input id='phone_number' type='tel'></li><li><label for='email_address'>Email:</label><input id='email_address' type='email'></li><li id='pole_number_li' class='hidden'><label for='pole_number'>Pole Number:</label><input id='pole_number' type=number></li><li id='restaurant_li' class='hidden'><label for='restaurant'>Is this a restaurant?</label><select id='restaurant'><option value='Yes'>Yes</option><option value='No' selected>No</option></select></li><li id='profanity_li' class='hidden'><label for='profanity'>Graffiti is profane?</label><select id='profanity'><option value='Yes'>Yes</option><option value='No' selected>No</option></select></li><li id='missed_li' class='hidden'><label>What was missed?</label><ol class='sub'><li><label><input type='checkbox' id='check1' name='missed' value='rubbish' checked class='short'> Rubbish</label></li><li><label><input type='checkbox' id='check2' name='missed' value='recycling' class='short'> Recycling</label></li><li><label><input type='checkbox' id='check3' name='missed' value='yard waste' class='short'> Yard Waste</label></li></ol></li><li id='collection_day_li' class='hidden'><label for='collection_day'>Regular collection day:</label><select id='collection_day'><option value='Monday' selected>Monday</option><option value='Tuesday'>Tuesday</option><option value='Wednesday'>Wednesday</option><option value='Thursday'>Thursday</option><option value='Friday'>Friday</option></select></li><li id='traffic_signal_complaint_type_li' class='hidden'><label for='traffic_signal_complaint_type'>Type of complaint:</label><select id='traffic_signal_complaint_type'><option value='General' selected>General Maintenance</option><option value='Driving'>Driving</option><option value='Walking'>Walking</option><option value='Cycling'>Cycling</option></select></li><li id='traffic_signal_complaint_li' class='hidden'><label for='traffic_signal_complaint'>Complaint:</label><select id='traffic_signal_complaint'><option></option></select></li><li id='traffic_sign_type_li' class='hidden'><label for='traffic_sign_type'>Type of sign:</label><select id='traffic_sign_type'><option>Do not enter</option><option>Disabled</option><option>Loading zone</option><option>No parking</option><option>No stopping</option><option>One way</option><option>Resident permit parking</option><option>Snow emergency</option><option>Speed limit</option><option>Stop</option><option>Street cleaning</option><option>Street name</option><option>Other</option></select></li><li id='traffic_sign_type_other_li' class='hidden'><label for='traffic_sign_type_other'>Other:</label><input id='traffic_sign_type_other'></li><li id='traffic_sign_complaint_li' class='hidden'><label for='traffic_sign_complaint'>Complaint:</label><select id='traffic_sign_complaint'><option>Sign is missing</option><option>Sign has been vandalized</option><option>Sign is faded or illegible</option><option>Sign is facing the wrong direction</option><option>Move the sign to another location</option><option>Remove sign</option><option>Change the sign</option></select></li><li id='bike_rack_request_li' class='hidden'><label for='bike_request_type'>Request:</label><select id='bike_request_type'><option value='Repair damaged rack'>Repair damaged rack</option><option value='Install new rack'>Install new rack</option></select></li><li id='bike_rack_type_li' class='hidden'><label for='bike_rack_type_li'>Type of Rack:</label><select id='bike_rack_type'><option>Inverted U</option><option>Post and ring</option><option>Swerve</option><option>Multiple racks on a rail</option><option>Other / not sure</option></select></li><li id='bike_rack_damage_type_li' class='hidden'><label for='bike_rack_damage_type'>Type of Damage:</label><select id='bike_rack_damage_type'><option>Rack is damaged</option><option>Rack has been vandalized</option><option>Rack is missing</option></select></li><li id='tree_maint_action_li' class='hidden'><label for='tree_maint_action'>Action:</label><select id='tree_maint_action'><option>Prune tree</option><option>Plant tree</option><option>Inspect tree for pest/disease</option><option>Other</option></select></li><li id='taxi_complaints_medallion_num_li' class='hidden'><label for='taxi_complaint_medallion_num'>Medallion #:</label><input type='text' id='medallion_number'></li><li id='taxi_complaints_plate_num_li' class='hidden'><label for='taxi_complaint_plate_num'>Plate #:</label><input type='text' id='plate_number'></li><li id='taxi_complaints_pickup_time_li' class='hidden'><label for='taxi_complaint_pickup_time'>Pickup Time:</label><input type='time' class='fullwidthfix' id='time'></li><li id='taxi_complaints_date_li' class='hidden'><label for='taxi_complaint_date'>Date:</label><input type='date' class='fullwidthfix' id='date'></li><li id='taxi_complaints_report_type_li' class='hidden'><label for='taxi_complaint_report_type'>Report:</label><select id='report'><option>Refused to transport</option><option>Overcharged</option><option>Rude and Discourteous</option><option>Refused to take credit card</option><option>Wrong route or location</option><option>Compliment</option><option>Other</option></select></li><li><label for='description' class='textarea' type='text'>Include additional info (optional)</label><textarea id='description' name='Additional Info'></textarea></li></ol><button id='cancel-form' type='reset' class='red'>Cancel</button><button id='submit-form' type='submit' class='gold'>Submit</button></fieldset></form></div>",
            status: "<div class='drop-shadow'></div><div class='content view-status'><a id='reports-logout-but' class='gold' style='height:28px;display:inline-block;width:50px;margin-top:0;margin-right:10px;position:absolute;right: 0;border-radius:3px;font: bold 12px/31px \"Helvetica Neue\", Helvetica, Arial, sans-serif;text-align:center;padding:0 5px;'>Sign Out</a><a href='#' class='gold' onclick=\"window.open(encodeURI('http://cambridgema.gov/iReport/Account'), '_system');\" style='display:inline-block;margin-top:30px;margin-right:10px;position:absolute;right: 0;border-radius:3px;font: bold 12px/31px \"Helvetica Neue\", Helvetica, Arial, sans-serif;text-align:center;padding:0 5px;color:White;text-decoration:none;'>Settings</a><h1>Your reports:</h1><table id='reports-table' class='reports' cellspacing='0'></table><div class='support'><p>If you would like more information about a specific report, please call <a href='tel:+1-617-349-4000'>(617) 349-4000</a></p></div></div>",
			help: "<div class='drop-shadow'></div><div class='content'><a name='top'><h1>Cambridge iReport</h1></a><p>For support with Cambridge iReport, please visit <a href='#' onclick=\"window.open(encodeURI('http://www.cambridgema.gov/iReport/Support'), '_system');\">www.cambridgema.gov/iReport/Support</a>.</p></div>",
			camera1: "<div id='choose-camera' class='menu-item'>From Camera</div><div id='choose-library' class='menu-item'>From Library</div><div id='feedback-close' class='menu-item'>Close</div>",
			camera2: "<div id='choose-camera' class='menu-item'>From Camera</div><div id='choose-library' class='menu-item'>From Library</div><div id='remove-photo' class='menu-item'>Remove</div><div id='feedback-close' class='menu-item'>Close</div>",
			statusRow: "<tr><td class='detail'><strong>%date%</strong>%type% located near:<strong>%address%</strong></td><td class='status' valign='middle'>%status%</td></tr><tr><td colspan='2' class='decor'><hr /></td></tr>",
			address: "<div id='top-bar'>%text%</div><div class='content'><div id='matches-list'></div><form id='address-form' onSubmit='return false'><fieldset><legend class='hide'>located near:</legend><ol class='report-form'><li><label for='street_number'>Street #:</label><input id='change-street-number' type='text' /></li><li><label for='street_name'>Street Name:</label><input id='change-street-name' type='text' /></li></ol><button id='cancel-address' type='reset' class='red left'>Cancel</button><button id='submit-address' type='submit' class='gold right'>Submit</button></fieldset></form></div>"
		},
		currentPage = "index",
	/**
	 Params
	 */
		Params = {
			device_id: false,
			platform: false,
			request_type: false,
			location_streetNum: false,
			location_streetName: false,
			location_city: false,
			location_zipcode: false,
			location_latitude: false,
			location_longitude: false,
			first_name: false,
			last_name: false,
			phone_number: false,
			email_address: false,
			description: false,
			attachment_data: false,
			// added
			pole_number: false,
			restaurant: false,
			profanity: false,
			missed: false,
			collection_day: false,
            // v2.0 params
            traffic_signal_complaint_type: false,
            traffic_signal_complaint: false,
            bike_request_type: false,
            bike_rack_type: false,
            bike_damage_type: false,
            traffic_sign_type: false,
            traffic_sign_type_other: false,
            traffic_sign_complaint: false,
            tree_maint_action: false,
            taxi_complaints_medallion_num: false,
            taxi_complaints_plate_num: false,
            taxi_complaints_pickup_time: false,
            taxi_complaints_date: false,
            taxi_complaints_report_type: false,
            starting_new_ticket: false
		},
		coc = {},
	/**
	 Objects
	 */
		tabs,
		index,
		map,
		report,
		status,
		help,
		address,
	/**
	 Production not needed for deploy
	 */
		cnt = 0,
		max = 9,
	/**
	 Server
	 */
        prod_server = "http://devmobile.cambridgema.gov",
		deploy_server = "https://www.cambridgema.gov",
 /*
        prod_server = "https://www.cambridgema.gov/",
        deploy_server = "https://www.cambridgema.gov",
  */
        app_location = "iReport/webservices",
        server = deploy_server,
		server_timeout_limit = 30000,
		server_timeout = null,
		server_xhr = null;

// -----------------------------------------------------------------------------
//
// !EXTEND XUI
//
// -----------------------------------------------------------------------------

	$.extend({
	
		val: function(v) {
			if (v) {
				this[0].value = v;
			}
			 
			return this[0].value;
		},
		
		selected: function() {
			var o = this[0];
			
			return o.item(o.options.selectedIndex);
			
		},
		
		offsetTop: function(v) {
			if (v) {
				this[0].offsetTop = v;
			}
			
			return this[0].offsetTop;
		}
		
	});
	

// -----------------------------------------------------------------------------
//
// !MISC
//
// -----------------------------------------------------------------------------

	function setCurrentPage(p, cb) {
		window.scrollTo(0, 0);
		currentPage = p;

		setTimeout(function () {
			// Clean up
			
			hideSignup();
			hideLogin();
			
			content.setStyle("visibility", "hidden");
			content.html("");
			cb();
			content.setStyle("visibility", "visible");
		
		}, 500);

		return false;
	}

	function reg(s, t, v) {
		return s.replace(t, v);
	}
	
	function reg2(s, t, v) {
		var i = 0, ns = s;
		while (i < t.length) {
			ns = ns.replace(t[i], v[i]);
			i = i + 1;
		}
		return ns;
	}
	
	// can be commented out for deployment
	function get(url, cb) {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", url, true);
		xmlhttp.onreadystatechange = function () {
			if (xmlhttp.readyState == 4) {
				cb(xmlhttp.responseText);
			}
		}
		xmlhttp.send("");				
	}
	
	// TODO: Depricated. Remove.
	function S4() {
		return (Math.floor((1 + Math.random()) * 0x10000)).toString(16).substring(1);
	}	

	// TODO: Depricated. Remove.
	function makeUuid() {
		// local storage
		var uuid;
		try {
			uuid = window.localStorage.getItem("uuid");
			if (!uuid) {
				uuid = (S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4());
				window.localStorage.setItem("uuid", uuid);
			}
		} catch (e) {
			navigator.notification.alert(
			    "Your device does not support local storage!",
			    null,
			    "Alert",
			    "Close"
			);
		}
		
		return uuid || "unknown";
	}
	
	function getUserID() {
		var userID;
		
		try {
			userID = window.localStorage.getItem("userID");
		} catch (error) {
			navigator.notification.alert(
				"Your device does not support local storage!",
				null,
				"Alert",
			    "Close"
			);
		}
	
        // console.log(">>> " + userID);
		return userID || "";
	}
	
	function storeUserID(userID) {
		try {
			window.localStorage.setItem("userID", userID);
			return true;
		} catch (error) {
			navigator.notification.alert(
				"Your device does not support local storage!", 
				null, 
				"Alert", 
				"Close"
			);
		}
		
		return false;
	}
 
     function confirmUserLogout() {
        navigator.notification.confirm("Are you sure you want to sign out?", logoutUser, "Confirm", ["Yes","No"])
     }
 
     function logoutUser(confirm) {
        // Check that the user confirms
        if (confirm == 1)
        {
            try {
                window.localStorage.removeItem("userID");
                 navigator.notification.alert("You have been signed out.", postLogout);
            }
            catch (error) {
                navigator.notification.alert(
                     "Your device does not support local storage!",
                     null,
                     "Alert",
                     "Close"
                 );
            }
        }
    }

     function postLogout() {
        // Switch to Report tab
         index();
         switchTabs($("#report"));
     }
	
	function claimTicket() {
		// console.log("claimTicket");
        
		var formData = {
			ticketToken: lastUnclaimedTicket,
			deviceID: getUserID()
		};
				
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
        
		server_xhr = content.xhr(server + "/" + app_location + "/ClaimTicket.aspx?", {
			async: true, 
			method: "POST", 
			headers: {"Content-Type": "application/x-www-form-urlencoded"}, 
			data: paramaterizeObject(formData),
			error: function (e) {
				clearXHR();
			},
			callback: function () {
				clearXHR();
                
				try {
					var r = JSON.parse(this.responseText);
				} catch (e) {
                } finally {
                    hideFeedback();
                    status();
                }
			}
		});
		
		updateFeedbackSpinner("<p>Associating last submitted report with your account...</p>");
		showFeedback();
 
        return false;
	}
 	
 	// Return a URL-encoded string of name-value pairs from 
 	// an object. Suitable for sending as query string.
	function paramaterizeObject(obj) { 
		var a = [], o; 
		for (o in obj) { 
			if (obj.hasOwnProperty(o)) {
				a.push(o + "=" + encodeURIComponent(obj[o]) || "");
			}
		}
		return a.join("&"); 
	}
	
	
// -----------------------------------------------------------------------------
//
// !VALIDATION
//
// -----------------------------------------------------------------------------

 	function validateEmail(email) {
	 	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/gi;
	 	
	 	return re.test(email);
 	}
 
    function validateSignupForm(event) {
    	// console.log("validateSignupForm");
    	
        var form = event.target.form;
        var invalid = true;
        var errorTitle = "";
        var errorMessage = "";

        if (form.elements["su_first_name"].value.length == 0) {
            errorTitle = "Error: First Name";
            errorMessage = "A first name is required.";
        }
        else if (form.elements["su_last_name"].value.length == 0) {
            errorTitle = "Error: Last Name";
            errorMessage = "A last name is required.";
        }
        else if (form.elements["su_phone_number"].value.length == 0) {
            errorTitle = "Error: Phone";
            errorMessage = "A phone number is required.";
        }
        else if (form.elements["su_email_address"].value.length == 0) {
            errorTitle = "Error: Email";
            errorMessage = "An email address is required.";
        }
        else if (!validateEmail(form.elements["su_email_address"].value)) {
            errorTitle = "Error: Email";
            errorMessage = "Please enter a valid email address.";
        }
        else if (form.elements["su_password"].value.length == 0) {
            errorTitle = "Error: Password";
            errorMessage = "A password is required.";
        }
        else if (form.elements["su_password_again"].value.length == 0) {
            errorTitle = "Error: Confirm Password";
            errorMessage = "Please re-type your password.";
        }
        else if (form.elements["su_password"].value != form.elements["su_password_again"].value) {
            errorTitle = "Error: Confirm Password";
            errorMessage = "Passwords do not match.";
        }
        else {
            invalid = false;
        }

        if (invalid) {
            navigator.notification.alert(errorMessage, null, errorTitle, "OK");
            return false;
        } else {
/* 			setTimeout(function(){window.blur();}, 250); */
			updateFeedbackSpinner("<p>Signing up! Please wait...</p>");
			setTimeout(showFeedback, 400);
			setTimeout(function(){submitSignup(event);}, 500);
        }
        
        return false;
    }
 
    function validateLoginForm(event) {
    	// console.log("validateLoginForm");
    	
        var form = event.target.form;
        var invalid = true;
        var errorTitle = "";
        var errorMessage = "";

        if (form.elements["li_email_address"].value.length == 0) {
            errorTitle = "Error: Email";
            errorMessage = "An email address is required.";
        }
        else if (!validateEmail(form.elements["li_email_address"].value)) {
            errorTitle = "Error: Email";
            errorMessage = "Please enter a valid email address.";
        }
        else if (form.elements["li_password"].value.length == 0) {
            errorTitle = "Error: Password";
            errorMessage = "A password is required.";
        }
		else {
            invalid = false;
        }

        if (invalid) {
            navigator.notification.alert(errorMessage, null, errorTitle, "OK");
            return false;
        } else {
			updateFeedbackSpinner("<p>Logging in, please wait...</p>");
			setTimeout(showFeedback, 400);
			setTimeout(function(){submitLogin(event);}, 500);
        }
        
        return false;
    }    


// -----------------------------------------------------------------------------
//
// !SIGN UP
//
// -----------------------------------------------------------------------------
 
	function showSignup() {
		hideLogin();
		signup.setStyle("display", "block");
		signup.setStyle("left", "0");
	}
	
	function hideSignup() {
		signup.setStyle("display", "none");
		signup.setStyle("left", "-9999");
	}

	/**
	
	 The submit button was pressed on the sign up form
	 
	 */
    function submitSignup(event) {
        // console.log("submitSignup");
        
        var form = event.target.form;
        var errorTitle = "";
        var errorMessage = "";
        var errorCallback = null;
	
		var formData = {
			email: form.elements["su_email_address"].value,
			firstname: form.elements["su_first_name"].value,
			lastname: form.elements["su_last_name"].value,
			password: form.elements["su_password"].value,
			passwordConfirm: form.elements["su_password_again"].value,
			phone: form.elements["su_phone_number"].value
		};
				
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
        
		server_xhr = content.xhr(server + "/" + app_location + "/CreateUser.aspx?", {
			async: true, 
			method: "POST", 
			headers: {"Content-Type": "application/x-www-form-urlencoded"}, 
			data: paramaterizeObject(formData),
			error: function (e) {
				clearXHR();
			},
			callback: function () {
				clearXHR();
                
				try {
					var r = JSON.parse(this.responseText);
					if (r.success) {
					
						// Save user ID to device
                        storeUserID(r.userID);
                        
                        errorTitle = "Success!";
                        errorMessage = r.message || "Sign up was successful.";
                        
                        // Claim the last ticket, if need be
                        if (lastUnclaimedTicket) {
	                        errorCallback = claimTicket;
                        } else {
                        	errorCallback = status;
                        }
					} else {
                        errorTitle = "Error";
                        errorMessage = r.message || "We are unable to sign you up at this time.";
					}
				} catch (e) {
					errorTitle = "Uh oh";
                    errorMessage = "Something went wrong.";
                } finally {
                    hideFeedback();
                    navigator.notification.alert(
                        errorMessage,
                        errorCallback,
                        errorTitle,
                        "OK"
                    );
                }
			}
		});
 
        return false;
    }
 
	/**
	
	 The cancel button was pressed on the sign up form
	 
	 */
    function cancelSignup(event) {
        event.target.form.reset();
        $("#report").fire("click");
 
        return false;
    }
 
 
// -----------------------------------------------------------------------------
//
// !LOGIN
//
// -----------------------------------------------------------------------------
	
	function showLogin() {
		hideSignup();
		login.setStyle("display", "block");
		login.setStyle("left", "0");
	}
	
	function hideLogin() {
		login.setStyle("display", "none");
		login.setStyle("left", "-9999");
	}
 
	/**
	
	 The submit button was pressed on the login form
	 
	 */
    function submitLogin(event) {
        // console.log("submitLogin");
        
        var form = event.target.form;
        var errorTitle = "";
        var errorMessage = "";
        var errorCallback = null;
	
		var formData = {
			email: form.elements["li_email_address"].value,
			password: form.elements["li_password"].value
		};
				
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
        
		server_xhr = content.xhr(server + "/" + app_location + "/Login.aspx?", {
			async: true, 
			method: "POST", 
			headers: {"Content-Type": "application/x-www-form-urlencoded"}, 
			data: paramaterizeObject(formData),
			error: function (e) {
				clearXHR();
			},
			callback: function () {
				clearXHR();
                
				try {
					var r = JSON.parse(this.responseText);
					if (r.success) {
					
						// Save user ID to device
                        storeUserID(r.userID);
                        
                        errorTitle = "Success!";
                        errorMessage = r.message || "You can now view your report statuses whenever you like.";
                        
						// Claim the last ticket, if need be
                        if (lastUnclaimedTicket) {
	                        errorCallback = claimTicket;
                        } else {
                        	errorCallback = status;
                       }
					} else {
                        errorTitle = "Error";
                        errorMessage = r.message || "We were unable to log you in at this time.";
					}
				} catch (e) {
					errorTitle = "Uh oh";
                    errorMessage = "Something went wrong.";
                } finally {
                    hideFeedback();
                    navigator.notification.alert(
                        errorMessage,
                        errorCallback,
                        errorTitle,
                        "OK"
                    );
                }
			}
		});
 
        return false;
    }
 
	/**
	
	 The cancel button was pressed on the login form
	 
	 */
    function cancelLogin(event) {
        event.target.form.reset();
        $("#report").fire("click");
 
        return false;
    }
    
    
// -----------------------------------------------------------------------------
//
// !INIT AUTHENTICATION
//
// -----------------------------------------------------------------------------

	function initAuthentication() {
		// console.log("initAuthentication");
		var a, b, c, d, e, f, g;
		
		a = new coc.ui.FastButton(document.getElementById("signup-home-but"), function(){$("#report").fire("click")});
		b = new coc.ui.FastButton(document.getElementById("signup-login-but"), showLogin);
		c = new coc.ui.FastButton(document.getElementById("login-back-but"), showSignup);
		d = new coc.ui.FastButton(document.getElementById("submit-signup-form"), validateSignupForm);
		e = new coc.ui.FastButton(document.getElementById("cancel-signup-form"), cancelSignup);
		f = new coc.ui.FastButton(document.getElementById("submit-login-form"), validateLoginForm);
		g = new coc.ui.FastButton(document.getElementById("cancel-login-form"), cancelLogin);
		
		hideLogin();
		hideSignup();
	}
 
	
// -----------------------------------------------------------------------------
//
// !FEEDBACK
//
// -----------------------------------------------------------------------------
	
	function showFeedback() {
		// width is set in css		
		feedback.setStyle("left", (window.innerWidth / 2) - 100 + "px");
		feedback.setStyle("top", "60px");
		window.scroll(0, 0);
		hider.setStyle("display", "block");
		feedback.setStyle("display", "block");
		
		return false;
	}

	function updateFeedback(t) {
		feedback.html('<span id="wait-text">' + t + '</span>');
		
		return false;
	}
	
	function hideFeedback() {
		updateFeedback("");
		hider.setStyle("display", "none");
		feedback.setStyle("display", "none");
		
		return false;
	}
	
	function updateFeedbackButtons(t) {
		feedback.html(t);
		
		return false;
	}

	function updateFeedbackSpinner(t) {
		feedback.html('<img src="img/ajax-loader-2.gif" width="31" height="31" border="0" /><span id="wait-text">' + t + '</span>');
		
		return false;
	}

	function abortXHR() {
        // console.log("abortXHR");
		try {
			server_xhr.xmlHttpRequest.abort();
		} catch (e) {
			//
		}
		server_timeout = null;
		hideFeedback();
		navigator.notification.alert(
		    "Server timed out. Please try again.", // message
		    null, // callback
		    "Alert", // title
		    "Close" // buttonName
		);

		return false;
	}

	function clearXHR() {
		clearTimeout(server_timeout);
		server_timeout = null;
	}	
	
	
// -----------------------------------------------------------------------------
//
// !HELP
//
// -----------------------------------------------------------------------------
	
	help = function () {
		setCurrentPage("help", function () {
			content.html(Templates.help);
		});
		return true;
	};


// -----------------------------------------------------------------------------
//
// !STATUS
//
// -----------------------------------------------------------------------------

	function showReports(a) {
		var table = $("#reports-table"), t = ["%date%", "%type%", "%address%", "%status%"], html = "", i = 0, l = a.length, o;
		if (l > 0) {
			while (i < l) {
				o = a[i];
				html = html + reg2(Templates.statusRow, t, [o.SubmittedDate, o.RequestType, o.Address, o.Status]);
				i = i + 1;
			}
			table.html(html);
		} else {
			table.html("No reports");
		}
		hideFeedback();
 
        // Set up the logout button
        var a = new coc.ui.FastButton(document.getElementById("reports-logout-but"), confirmUserLogout);
	}	
	
	function getReports() {
 
		if (getUserID() == "") {
			showSignup();
			return false;
		} else {
			hideSignup();
		}
		
		Params.device_id = getUserID();
  
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
/* 		server_xhr = content.xhr(server + '/iReport/GetTickets.aspx?deviceID=' + Params.device_id, { //?deviceID='+Params.device_id */
		server_xhr = content.xhr(server + "/" + app_location + "/GetTickets.aspx?deviceID=" + Params.device_id, { //?deviceID='+Params.device_id
			async: true,
			method: 'get',
			headers: {'Content-Type': 'text/plain'},
			error: function() {
				clearXHR();
			},
			callback: function() {
				clearXHR();
				//i_o = false;
				try {
					var r = JSON.parse(this.responseText);
					if (r.success) {
						showReports(r.tickets);
					}
				} catch (e) {
					hideFeedback();
					//alert("Parse failed : " + e);
				}
			}
		});
		updateFeedbackSpinner("<p>Fetching reports, please wait...</p>");
		showFeedback();
		return false;
	}

	status = function() {
		setCurrentPage("status", function () {
			content.html(Templates.status);
			getReports();
		});
		
		return true;
	};	


// -----------------------------------------------------------------------------
//
// !MAP
//
// -----------------------------------------------------------------------------

	function positionMap() {
		var mh, map, mt, mtoo, mapHeight;
		mh = $("#map-holder");
		mtoo = $("#map-tools");
		map = $("#map");
		mt = $("#map-target");
		mapHeight = (windowHeight - mh.offsetTop() - 120) + 'px';
		mh.setStyle("height", mapHeight);
		map.setStyle("height", mapHeight);
		mt.setStyle("height", mapHeight);
		addressBar = $("#map-info-bar");
		crosshair = mt;
		return false;
	}
				
	function prepareMap(e) {
		var item = $("#report-list").selected();

        // flag the user as starting a new ticket
        Params.starting_new_ticket = true;
 
        if (item.value == "()")
        {
            navigator.notification.alert(
                "You need to select a report type before continuing",  // message
                null,         // callback
                'Whoops',            // title
                'Okay'                  // buttonName
            );			
            return;
        }
 
		activeTitle = item.text;
		Params.request_type = item.value;
		map();
	}
	
	function chooseAddressMethod() {
		var a, b;
		
		updateFeedbackButtons("<div id='use-map' class='menu-item'>Use Map</div><div id='use-form' class='menu-item'>Enter Address</div>");
		showFeedback();
		
		a = new coc.ui.FastButton(document.getElementById("use-map"), map);
		b = new coc.ui.FastButton(document.getElementById("use-form"), address);
		
		return false;
	}
	
	function addMapEvents() {
		var a, b;
		
		a = new coc.ui.FastButton(document.getElementById("confirm-but"), report); // report
		b = new coc.ui.FastButton(document.getElementById("photo-holder"), onAddPhoto);
		
		return false;
	}	
	
	function initMapDom() {
		//addressBar.html("Here is a very long string that should be very long when it is placed on the page");
		addressBar.html((Params.location_streetNum || '') + " " + (Params.location_streetName || ''));
		crosshair.toggleClass("map-loader");
		crosshair.addClass("blue-dot");
		$("#bottom-holder").setStyle("opacity", 1);
		return false;
	}
	
	function onGeoCode(results, status) {
		if (status === "OK") {
			var oo = results[0].address_components, i = 0, type, e;
			while (i < oo.length) {
				e = oo[i];
				type = e.types[0];
				switch (type) {
				case 'street_number':
					Params.location_streetNum = e.short_name;
					break;
				case 'route':
					Params.location_streetName = e.short_name;
					break;
				case 'locality':
					Params.location_city = e.short_name;
					break;
				case 'postal_code':
					Params.location_zipcode = e.short_name;
					break;
				}
				i = i + 1;
			}
			initMapDom();
        } else if (status === "OVER_QUERY_LIMIT") {
            // Relax, man.
		} else {
			navigator.notification.alert(
			    "Address not found : " + status,  // message
			    null,         // callback
			    "Alert",            // title
			    "Close"                  // buttonName
			);			
		}
	}
	
	function onMapDragEnd() {
        //console.log("onMapDragEnd");
        if (geoCodeIntervalID) {
            clearInterval(geoCodeIntervalID);
        }
 
		Params.location_latitude = activeMap.getCenter().lat();
		Params.location_longitude = activeMap.getCenter().lng();
 
        geoCodeIntervalCount = 0;
        geoCodeIntervalID = setInterval(doGeoCode, 1000);
 
        addressBar.html("Looking up address...");
 
		// geocoder.geocode({'latLng': new google.maps.LatLng(Params.location_latitude, Params.location_longitude)}, onGeoCode);
	}
 
    function doGeoCode() {
        Params.location_latitude = activeMap.getCenter().lat();
        Params.location_longitude = activeMap.getCenter().lng();

        geocoder.geocode({'latLng': new google.maps.LatLng(Params.location_latitude, Params.location_longitude)}, onGeoCode);
 
        if (geoCodeIntervalCount++ > 2) {
            clearInterval(geoCodeIntervalID);
        }
    }
	
	function acceptPosition(lon, lat) {
		locating = false;
		Params.location_latitude = lat;
		Params.location_longitude = lon;
		setMap();		
	}

	function onLocWin(e) {
		var acc = e.coords.accuracy, lon = e.coords.longitude, lat = e.coords.latitude;
 
        if (acc < bestAcc)
        {
            bestAcc = acc;
            bestLat = lat;
            bestLon = lon;
        }
 
		if (geoAttempts < 2) {
			if (accAttempts < maxAccAttempts) {
				if (bestAcc < reqAccuracy) {
					acceptPosition(bestLon, bestLat);
				} else {
					addressBar.html("Refining... (pass " + accAttempts + " of 4)");
					setTimeout(getNavigator, 2500);
				}
			} else {
				acceptPosition(bestLon, bestLat);
			}
			accAttempts = accAttempts + 1;
		} else {
			acceptPosition(bestLon, bestLat);
		}
	}
	
	function onLocFail(e) {
		locating = false;		
		switch (e.code) {
		case e.PERMISSION_DENIED:
            navigator.notification.alert("iReport was denied access to your device's GPS receiver. The app will work without this, but for the best user experience we strongly encourage you to allow access to this app.", null, "Notice", "OK");
            acceptPosition(-71.120967, 42.376768);
			break;
		case e.POSITION_UNAVAILABLE:
			addressBar.html("Position unavailable");
			//Params.location_latitude = 42.376768;
			//Params.location_longitude = -71.120967;
			acceptPosition(-71.120967, 42.376768);
			break;
		case e.TIMEOUT:
			// Only allow 3 attempts, give up if we can't get the location by then
			if (geoAttempts >= 3) {
				navigator.notification.alert("We were unable to determine your location, please drag the blue dot to the location of your report.");
				acceptPosition(-71.120967, 42.376768);
			}
			else {
				addressBar.html("Location request timed out, retrying...");
				geoAttempts = geoAttempts + 1;
				getNavigator();
			}
			break;
		case e.UNKNOWN_ERROR:
			addressBar.html("Unknown error requesting location, defaulting.");
			acceptPosition(-71.120967, 42.376768);
			break;
		}	
	}	
	
	function getNavigator() {
		try {
			navigator.geolocation.getCurrentPosition(onLocWin, onLocFail, {maximumAge: 3000, enableHighAccuracy: true, timeout: 5000});
			locating = true;
		} catch (e) {
			locating = false;
			navigator.notification.alert(
			    'Device has no navigator capabilities.',
			    null,
			    "Alert",
			    "Close"
			);			
		}
	}

	function addPhotoToDom() {
		if (Params.attachment_data) {
			var image = $("#photo-holder");
			image.html("<img src='" + Params.attachment_data + "' width='100%' height='100%' border='0'>"); // "#" + new Date() +
		}
	}	
	
	function restorePhoto() {
		if (Params.attachment_data) {
			addPhotoToDom();
		}
		return false;
	}
	
	function addCameraSpinner() {
		var ph = $("figure > .icon");
		ph.setStyle("background", "url(img/bars.gif) no-repeat scroll center center");
		return false;
	} 
	
	function removeCameraSpinner() {
		var ph = $("figure > .icon");
		ph.setStyle("background", "url(img/icon-photo.png) no-repeat scroll center center");
		return false;
	}
	
	function onRemovePhoto() {
		Params.attachment_data = false;
		hideFeedback();
		var image = document.getElementById("photo-holder");
		image.innerHTML = "";
		removeCameraSpinner();
	}

	function libraryError() {
		removeCameraSpinner();
	}

	function onCameraFeedbackClose() {
		hideFeedback();
		removeCameraSpinner();
	}

	function msuccess(file) {
		Params.attachment_data = file.fullPath;
		//addPhotoToDom();
		removeCameraSpinner();
	}

	function mfail(error) {
		navigator.notification.alert(
		    "Unable to retrieve file properties: " + error.code,  // message
		    null,         // callback
		    "Alert",            // title
		    "Close"                  // buttonName
		);		
	}		


	function pictureSuccess(fileURI) {
		Params.attachment_data = fileURI;
  
		addPhotoToDom();
		removeCameraSpinner();
	}

	function cameraError(e) {
		removeCameraSpinner();				
	}
	
	function getCameraOptions(t) {
		return {
			// correctOrientation: true,
			quality: 40,
			destinationType: navigator.camera.DestinationType.FILE_URI,
			sourceType: t,
			allowEdit: false,
			encodingType: navigator.camera.EncodingType.JPEG,
			targetWidth: 800,
			targetHeight: 800
		};
	}
	
	
	function onChooseCamera() {		
		navigator.camera.getPicture(pictureSuccess, cameraError, getCameraOptions(navigator.camera.PictureSourceType.CAMERA));
		hideFeedback();
	}

	function onChooseLibrary() {	
		navigator.camera.getPicture(pictureSuccess, libraryError, getCameraOptions(navigator.camera.PictureSourceType.PHOTOLIBRARY));
		hideFeedback();			
	}	
	
	function onAddPhoto() {
		var a, b, c, d;			
		addCameraSpinner();
		if (!Params.attachment_data) {
			updateFeedbackButtons(Templates.camera1);
		} else {
			updateFeedbackButtons(Templates.camera2);
		}

		showFeedback();
		a = new coc.ui.FastButton(document.getElementById("choose-camera"), onChooseCamera);
		b = new coc.ui.FastButton(document.getElementById("choose-library"), onChooseLibrary);
		c = new coc.ui.FastButton(document.getElementById("feedback-close"), onCameraFeedbackClose);
		if (Params.attachment_data) {
			d = new coc.ui.FastButton(document.getElementById("remove-photo"), onRemovePhoto);
		}
	}

	function setMap() {
		var pos, m;
		m = document.getElementById('map');
		// make sure map is there! if not don't make it
		if (m) {
			pos = new google.maps.LatLng(Params.location_latitude, Params.location_longitude);
			activeMap = new google.maps.Map(m, {
				zoom: 18,
				center: pos,
				streetViewControl: false,
				disableDefaultUI: true,
				zoomControl: true,
				zoomControlOptions: {
					style: google.maps.ZoomControlStyle.DEFAULT,
					position: google.maps.ControlPosition.TOP_LEFT
				},
				mapTypeControl: true,
				mapTypeControlOptions: {
					style: google.maps.MapTypeControlStyle.DEFAULT,
					position: google.maps.ControlPosition.TOP_RIGHT
				},			
				keyboardShortcuts: false,
				scrollwheel: false,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			});
			google.maps.event.addListener(activeMap, 'dragend', onMapDragEnd);
			if (!geocoder) {
				geocoder = new google.maps.Geocoder();
			}				
			geocoder.geocode({'latLng': pos}, onGeoCode);
			addMapEvents();			
		}
		return false;
	}

	function findPosition() {
		if (Params.location_latitude && !Params.starting_new_ticket) {
			setMap();
			restorePhoto();
		} else {
			// dont do it if its already being dome.
			if (!locating) {
				getNavigator();
 
                // Unflag the user as starting a new ticket
                Params.starting_new_ticket = false;
			}
		}
		return false;
	}			
	
	function initMap() {
		geoAttempts = 1;
		try {
			positionMap();
			findPosition();
		} catch (e) {
			//map not there
		}
		return false;
	}

	map = function () {
		hideFeedback();
		setCurrentPage("map", function() {
			content.html(reg(Templates.map, "%text%", activeTitle + " (step 1 of 2)"));	
			initMap();

            if (!tip_shown) {
				navigator.notification.alert(
				    'You can drag the map and zoom in or out with your fingers to adjust the location of your report as shown by the blue dot.',  // message
				    null,         // callback
				    'Tip',            // title
				    "Close"                  // buttonName
				);
			}
			tip_shown = true;

            });
		return true;
	};
	
	
// -----------------------------------------------------------------------------
//
// !REPORT
//
// -----------------------------------------------------------------------------
	
	function paramaterize() { 
		var a = [], o; 
		for (o in Params) { 
			if (Params.hasOwnProperty(o)) {
				a.push(o + '=' + encodeURIComponent(Params[o]) || '');
			}
		}

		return a.join('&'); 
	}
	
	function reportFailure(t) {
		hideFeedback();
		
		// if it's 3 try again
		navigator.notification.alert(
		    t,  		// message
		    null,		// callback
		    "Alert",	// title
		    "Close"		// buttonName
		);		

		return false;
	}
	
    function reportSuccessWasDismissedWithIndex(index) {
    	// Index is 1-based: 1 = NO, 2 = YES
    	if (index == 1) {
    		// Go home
	    	$("#report").fire("click");
    	} else {
    		// Go to status page
            $("#status").fire("click");
    	}
    }
	
	function reportSuccess(t, ticketToken) {
		// console.log("message: " + t);
		var message = "";
		Params.attachment_data = false;
		hideFeedback();
		
		if (Number(t) > 0) {
/* 			message = "Your query has been submitted. You can track status in the 'View Status' section."; */
			message = "Your query has been submitted.";
		} else {
			message = t;
		}
 
        // navigator.notification.alert(m,  // message
 
        if (getUserID() != "") {
        	lastUnclaimedTicket = "";
        	
	        // TODO: Remove the following line.
	        message += "\r\rWould you like to track the status of your report?";
        } else {
        	lastUnclaimedTicket = ticketToken;
        	
	        // TODO: Remove the following line.
	        message += "\r\rWould you like to register or log in to track the status of your report?";
        }
        
		navigator.notification.confirm(
			message,
			reportSuccessWasDismissedWithIndex,
			"Success",
			["No","Yes"]
		);		

		return false;
	}
	
	function sendReportNoPhoto() {
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
 
        // Params.device_id = device.uuid || makeUuid();
        Params.device_id = getUserID();
        
        // test device id
    
/* 		server_xhr = content.xhr(server + '/iReport/SubmitTicket.aspx?', {  */
		server_xhr = content.xhr(server + "/" + app_location + "/SubmitTicket.aspx?", { 
			async: true, 
			method: 'post', 
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}, 
			data: paramaterize(),
			error: function (e) {
				clearXHR();
			},
			callback: function () {
				// console.log(this.responseText);
				clearXHR();
				//i_o = false;
				try {
					var r = JSON.parse(this.responseText);
					if (r.success) {
						reportSuccess(r.message, r.ticketToken);
					} else {
						reportFailure(r.message);
					}
				} catch (e) {
					hideFeedback();
					// json not defined
				}
			} 
		});
		return false;
	}
	
	function reportPhotoWin(r) {
 
		clearXHR();

		try {
			var m = JSON.parse(unescape(r.response));
 
            if(m.success)
                reportSuccess(m.message, m.ticketToken);
            else
                reportFailure(m.message);
		} catch (e) {
			reportFailure(r.response);
		}
	}	

	function reportPhotoFail(error) {
		clearXHR();
		hideFeedback();

		navigator.notification.alert(
		    "An error has occurred. Please try again. Error code: " + error.code,  // message
		    null,		// callback
		    "Alert",	// title
		    "Close"		// buttonName
		);		
	}	

	function sendReportAndPhoto() {
 
        // Params.device_id = device.uuid || makeUuid();
        Params.device_id = getUserID();
 
		server_timeout = setTimeout(abortXHR, server_timeout_limit);
		//console.log("string" + Params.attachment_data.substr(0,Params.attachment_data.indexOf("?")));
		var options = new FileUploadOptions(), ft = new FileTransfer(), nfn;
		options.fileKey = "file";
		// does name of pic matter?
		//options.fileName = Params.attachment_data.substr(Params.attachment_data.lastIndexOf('/') + 1);
		options.fileName = "pic.jpg";
		options.mimeType = "image/jpeg";
        options.chunkedMode = true;
		options.params = Params;
		
		if (Params.attachment_data.indexOf("?") > 0) {
			nfn = Params.attachment_data.substr(0,Params.attachment_data.indexOf("?"));
		} else {
			nfn = Params.attachment_data;
		}
  
        //ft.upload(nfn, "http://mclarke-mac.ixite.local:8801/upload", reportPhotoWin, reportPhotoFail, options);
/* 		ft.upload(nfn, server + "/iReport/SubmitTicket.aspx", reportPhotoWin, reportPhotoFail, options); */
		ft.upload(nfn, server + "/" + app_location + "/SubmitTicket.aspx", reportPhotoWin, reportPhotoFail, options);

		return false;
	}
	
	function processMissed() {
		var a = [], check1 = document.getElementById("check1"), check2 = document.getElementById("check2"), check3 = document.getElementById("check3");
		if (check1.checked) {
			a.push(check1.value);
		}
		if (check2.checked) {
			a.push(check2.value);
		}
		if (check3.checked) {
			a.push(check3.value);
		}
		return a.join(",");
	}
	
	function setParams() {

		if (window.localStorage) {
			window.localStorage.setItem("first_name", first_name.val());
			window.localStorage.setItem("last_name", last_name.val());
			window.localStorage.setItem("email_address", email_address.val());
			window.localStorage.setItem("phone_number", phone_number.val());
		}
		
		Params.first_name = first_name.val();
		Params.last_name = last_name.val();
		Params.phone_number = phone_number.val();
		Params.email_address = email_address.val();
		Params.description = description.val();
		// added
		Params.pole_number = pole_number.val();
		Params.restaurant = restaurant.val();
		Params.profanity = (profanity.val() == "Yes") ? "Yes" : "";
		Params.missed = processMissed();
		Params.collection_day = collection_day.val();
        Params.traffic_signal_complaint_type = traffic_signal_complaint_type.val();
        Params.traffic_signal_complaint = traffic_signal_complaint.val();
        Params.bike_request_type = bike_request_type.val();
        Params.bike_rack_type = bike_rack_type.val();
        Params.bike_damage_type = bike_damage_type.val();
        Params.traffic_sign_type = traffic_sign_type.val();
        Params.traffic_sign_type_other = traffic_sign_type_other.val();
        Params.traffic_sign_complaint = traffic_sign_complaint.val();
        Params.tree_maint_action = tree_maint_action.val();
        Params.taxi_complaints_medallion_num = taxi_complaints_medallion_num.val();
        Params.taxi_complaints_plate_num = taxi_complaints_plate_num.val();
        Params.taxi_complaints_pickup_time = taxi_complaints_pickup_time.val();
        Params.taxi_complaints_date = taxi_complaints_date.val();
        Params.taxi_complaints_report_type = taxi_complaints_report_type.val();
 
		return false;
	}

	function onChangeLocation() {
		setParams();
		//document.getElementById("report-form").blur();
		map();
	}
	
	function onCancelForm() {
		map();
		return true;
	}

	function sendReport() {
 
        // Insanity check:
        // Make sure we haven't already sent a report less than a second ago.
 
        var d = new Date();
        var t = d.getTime();
 
        if (t - submitTime < 1000) {
            return;
        } else {
            submitTime = t;
        }
        
        // Giddyup
 
        if (Params.attachment_data) {
			updateFeedbackSpinner("<p>Sending report. Pictures can take longer to transmit. Please wait...</p>");
			showFeedback();
			sendReportAndPhoto();
		} else {
			updateFeedbackSpinner("<p>Sending report. Please wait...</p>");
			showFeedback();
			sendReportNoPhoto();
		}
		//i_o = true;		
		return false;
	}
	
	function processForm() {
		// check for email or phone for rubbish if missedpickup
		if (Params.request_type == ReportTypes.missedpickup) {
			// check for an email or a phone
			if (Params.phone_number.length == 0 && Params.email_address.length == 0) {
				navigator.notification.alert(
				    "A phone number or email is required.",	// message
				    null,									// callback
				    "Alert",								// title
				    "OK"									// buttonName
				);
				//alert("A phone number or email is required.");
			} else {
				if (Params.email_address.length == 0) {
					sendReport();
				} else {
					// check email
					var patt1 = /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/gi;
					if (Params.email_address.match(patt1)) {
						sendReport();
					} else {
						navigator.notification.alert(
						    "The email does not appear valid.",	// message
						    null,								// callback
						    "Alert",							// title
						    "OK"								// buttonName
						);						
						//alert("The email does not appear valid.");
					}
				}
			}
		} else {
			sendReport();
		}
		return false;
	}
	
	function onSubmitReport(e) {
		window.scrollTo(0, 0);
		//window.blur();
		setParams();
		processForm();
	}
	
	function setFormVals() {
		
		if (window.localStorage) {
			Params.first_name = window.localStorage.getItem("first_name");
			Params.last_name = window.localStorage.getItem("last_name");
			Params.phone_number = window.localStorage.getItem("phone_number");
			Params.email_address = window.localStorage.getItem("email_address");
		}
	
		first_name.val(Params.first_name);
		last_name.val(Params.last_name);
		phone_number.val(Params.phone_number);
		email_address.val(Params.email_address);
		description.val(Params.description);
		// added
		pole_number.val(Params.pole_number);
		restaurant.val(Params.restaurant);
		profanity.val(Params.profanity);
		//missed.val(Params.missed);
		collection_day.val(Params.collection_day);
		return false;
	}		
	
	function initReport() {
		var a, b, c;
		a = new coc.ui.FastButton(document.getElementById("submit-form"), onSubmitReport);
		b = new coc.ui.FastButton(document.getElementById("cancel-form"), onCancelForm);
		c = new coc.ui.FastButton(document.getElementById("change-location"), chooseAddressMethod);
		//c = new coc.ui.FastButton(document.getElementById("change-location"), onChangeLocation);

		submit_but = $('#submit-form');
		cancel_but = $('#cancel-form');
		first_name = $('#first_name');
		last_name = $('#last_name');
		phone_number = $('#phone_number');
		email_address = $('#email_address');
		description = $('#description');
		// added
		pole_number = $('#pole_number');
		restaurant = $('#restaurant');
		profanity = $('#profanity');
		//missed = $('#missed');
		collection_day = $('#collection_day');
        traffic_signal_complaint_type = $('#traffic_signal_complaint_type');
        traffic_signal_complaint = $('#traffic_signal_complaint');
        bike_request_type = $('#bike_request_type');
        bike_rack_type = $('#bike_rack_type');
        bike_damage_type = $('#bike_rack_damage_type');
        traffic_sign_type = $('#traffic_sign_type');
        traffic_sign_type_other = $('#traffic_sign_type_other');
        traffic_sign_complaint = $('#traffic_sign_complaint');
        tree_maint_action = $('#tree_maint_action');
        taxi_complaints_medallion_num = $('#taxi_complaints_medallion_num_li');
        taxi_complaints_plate_num = $('#taxi_complaints_plate_num_li');
        taxi_complaints_pickup_time = $('#taxi_complaints_pickup_time_li');
        taxi_complaints_date = $('#taxi_complaints_date_li');
        taxi_complaints_report_type = $('#taxi_complaints_report_type_li');
 
 		setFormVals();
		return false;
	}
	
	function showAddParams() {
		switch (Params.request_type) {
		case ReportTypes.streetlight:
			$("#pole_number_li").removeClass("hidden");
			break;
		case ReportTypes.rodent:
			$("#restaurant_li").removeClass("hidden");
			break;
		case ReportTypes.graffiti:
			$("#profanity_li").removeClass("hidden");
			break;
		case ReportTypes.missedpickup:
			$("#missed_li").removeClass("hidden");
			$("#collection_day_li").removeClass("hidden");
			break;
        case ReportTypes.trafficsignal:
            $("#traffic_signal_complaint_type_li").removeClass("hidden");
            $("#traffic_signal_complaint_li").removeClass("hidden");
 
            var ddl = document.getElementById('traffic_signal_complaint_type');
            ddl.onchange = updateTrafficSignalList;
 
            updateTrafficSignalList(ddl.options[ddl.selectedIndex].value);
            break;
         case ReportTypes.bikerack:
             $("#bike_rack_request_li").removeClass("hidden");
             $("#bike_rack_type_li").removeClass("hidden");
 
             var ddl = document.getElementById('bike_request_type');
             ddl.onchange = showHideBikeRackDamage;
 
             showHideBikeRackDamage();
             break;
        case ReportTypes.trafficsign:
            $("#traffic_sign_type_li").removeClass("hidden");
            $("#traffic_sign_complaint_li").removeClass("hidden");

            var ddl = document.getElementById('traffic_sign_type');
            ddl.onchange = showHideTrafficSignTypeOther;

            showHideTrafficSignTypeOther();
            break;
        case ReportTypes.treemaintenance:
            $("#tree_maint_action_li").removeClass("hidden");
            break;
        case ReportTypes.taxicomplaint:
            $('#taxi_complaints_medallion_num_li').removeClass("hidden");
            $('#taxi_complaints_plate_num_li').removeClass("hidden");
            $('#taxi_complaints_pickup_time_li').removeClass("hidden");
            $('#taxi_complaints_date_li').removeClass("hidden");
            $('#taxi_complaints_report_type_li').removeClass("hidden");
            break;
        }
		return false;
	}
	
	report = function () {
		hideFeedback();
		setCurrentPage("report", function () {
			var t = ["%text%", "%address%"], v = [activeTitle + " (step 2 of 2)", Params.location_streetNum + " " + Params.location_streetName];
			content.html(reg2(Templates.report, t, v));
			showAddParams();
			initReport();
			//setTimeout(initReport, 500);
		});
		return true;
    };
 
 function showHideTrafficSignTypeOther() {
     var ddl = document.getElementById('traffic_sign_type');
     var type = ddl.options[ddl.selectedIndex].value;
     
     if (type == 'Other')
     $("#traffic_sign_type_other_li").removeClass("hidden");
     else
     $("#traffic_sign_type_other_li").addClass("hidden");
 }
 
 function showHideBikeRackDamage() {
     var ddl = document.getElementById('bike_request_type');
     var request = ddl.options[ddl.selectedIndex].value;
     
     if (request == 'Repair damaged rack')
        $("#bike_rack_damage_type_li").removeClass("hidden");
     else
         $("#bike_rack_damage_type_li").addClass("hidden");
 }

 function updateTrafficSignalList() {
     var ddl = document.getElementById('traffic_signal_complaint_type');
     var type = ddl.options[ddl.selectedIndex].value;
     switch(type) {
         case "General":
             $("#traffic_signal_complaint").html("<option>Pedestrian lamp is not working</option><option>red/yellow/green not working</option><option>Signal is facing the wrong direction</option><option>School zone flasher not working</option>");
             break;
         case "Driving":
             $("#traffic_signal_complaint").html("<option>Green light is too short</option><option>Green light never comes on</option><option>Signals not coordinated</option><option>Traffic blocking intersection</option>");
             break;
         case "Walking":
             $("#traffic_signal_complaint").html("<option>Not enough time to cross street</option><option>Vehicles are running the red light</option><option>Vehicles are turning right on red</option><option>WALK light is on but vehicles are turning</option><option>WALK light never comes on</option><option>Push-button is missing</option><option>WALK light takes too long to come on</option>");
             break;
         case "Cycling":
             $("#traffic_signal_complaint").html("<option value='Signal is not detecting my bike'>Signal is not detecting my bike</option><option value='Vehicles are running red light'>Vehicles are running red light</option>");
             break;
         default:
             break;
     }
 }
 
// -----------------------------------------------------------------------------
//
// !ADDRESS
//
// -----------------------------------------------------------------------------
	
	function setAddress(num, name) {
		Params.location_streetNum = num;
		Params.location_streetName = name;
		Params.location_city = 'Cambridge';
		Params.location_zipcode = '';
		Params.location_latitude = '';
		Params.location_longitude = '';
		report();
	}
	
	function addressSuccess(o) {
 
        document.getElementById("change-street-number").blur();
        document.getElementById("change-street-name").blur();
         
		hideFeedback();
		var matches = o.matches,
			i = 0,
			l = matches.length,
			html = '';
		if (matches.length > 0) {
			if (matches.length == 1) {
				setAddress(matches[0].Address.StreetNumber, matches[0].Address.StreetName);
			} else {
				matchesList.html("");
				while (i < l) {
					html += "<a id='" + i + "' class='matches'>" + matches[i].Address.StreetNumber + " " + matches[i].Address.StreetName + "</a><br/><br/>";
					i = i + 1;
				}
				matchesList.html("An exact match could not be found. Did you mean:<br/><br/>" + html);
				$(".matches").click(function (e) {
					var id = $(e.currentTarget).attr("id"),
						match = matches[Number(id)];
					//log(matches[Number(id)]);
					setAddress(match.Address.StreetNumber, match.Address.StreetName);
				});
			}
		} else {
			navigator.notification.alert(
			    "The address you specified is invalid. Please try another.", // message
			    null, // callback
			    "Alert", // title
			    "Close" // buttonName
			);
		}
		
		return false;
	}
	
	function addressFailure(t) {
		hideFeedback();
		navigator.notification.alert(
		    t, // message
		    null, // callback
		    "Alert", // title
		    "Close" // buttonName
		);

		return false;
	}

	
	function onSubmitAddress(e) {
		window.scrollTo(0, 0);
		if (change_street_number.val().length > 0 && change_street_name.val().length > 0) {
			///ireport/validateaddress.aspx?location_streetNum=795&location_streetName=mass%20ave
			updateFeedbackSpinner("<p>Validating address, please wait…</p>");
			showFeedback();
			server_timeout = setTimeout(abortXHR, server_timeout_limit);
			server_xhr = content.xhr(server + "/" + app_location + "/validateaddress.aspx?", { 
				async: true, 
				method: 'post', 
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}, 
				data: 'location_streetNum=' + encodeURIComponent(change_street_number.val()) + '&location_streetName=' + encodeURIComponent(change_street_name.val()),
				error: function () {
					clearXHR();
				},
				callback: function () {

					clearXHR();
					//i_o = false;
					try {
						var r = JSON.parse(this.responseText);
						if (r.success) {
							addressSuccess(r);
						} else {
							addressFailure(r.message);
						}
					} catch (e) {
						hideFeedback();
						// json not defined
					}
				} 
			});
			/*server_timeout_limit = "5000"
			server_timeout = null,
			server_xhr = null;*/
			//alert(xhr.xmlHttpRequest);
			//report();			
		} else {
			navigator.notification.alert(
			    "You must provide a street number and a street name.",	// message
			    null,													// callback
			    "Alert",												// title
			    "Close"													// buttonName
			);
		}
		return true;
	}
	
	function onCancelAddress(e) {
		report();
		return true;
	}

	function initAddress() {
		var a, b;
		a = new coc.ui.FastButton(document.getElementById("submit-address"), onSubmitAddress);
		b = new coc.ui.FastButton(document.getElementById("cancel-address"), onCancelAddress);
		
		change_street_number = $("#change-street-number");
		change_street_name = $("#change-street-name");
		matchesList = $("#matches-list");
		
	}
	
	address = function () {
		hideFeedback();
		setCurrentPage("address", function () {
			var t = ["%text%"], v = [activeTitle + " (step 2 of 2)"];
			content.html(reg2(Templates.address, t, v));
			initAddress();
		});
		return true;
	};


// -----------------------------------------------------------------------------
//
// !INDEX
//
// -----------------------------------------------------------------------------
	
	function setHelpBlurb() {
		var selectedIndex, helpBlurbsList;
 
		$("#report-list ul").find("li").removeClass("selected-help");
 
		selectedIndex = $("#report-list")[0].options.selectedIndex;
		helpBlurbsList = document.getElementById("help-blurbs").children[0];
 
		if (selectedIndex > 0) {
            $(helpBlurbsList.children[selectedIndex - 1]).addClass("selected-help");
		}
	}
	
	function initIndex() {
		// console.log("initIndex");
		var a;

		a = new coc.ui.FastButton(document.getElementById("report-list-confirm"), prepareMap);
        
        document.getElementById("report-list").onchange = function() {
            setHelpBlurb();
        };
 
        // Call setHelpBlurb() to display the help text for the initial item
        setHelpBlurb();
 
		return false;
	}
		
	index = function() {
		setCurrentPage("index", function() {
			content.html(Templates.index);
			initIndex();
		});
		
		return true;
	};
	
	
// -----------------------------------------------------------------------------
//
// !TABS
//
// -----------------------------------------------------------------------------

	function switchTabs(item) {
		activeTab.removeClass("selected");
		activeTab = item;
		activeTab.addClass("selected");
		
		return false;				
	}

	function setActivity(e) {
		var item = $(e.currentTarget), id = String(item.attr("id"));
		switchTabs(item);				
		switch (id) {
			case "report":
				index();
				break;
			case "status":
				status();
				break;
			case "help":
				help();
				break;
		}	
	}

	function makeTabs() {
		var a, b, c;
				
		a = new coc.ui.FastButton(document.getElementById("report"), setActivity);
		b = new coc.ui.FastButton(document.getElementById("status"), setActivity);
		c = new coc.ui.FastButton(document.getElementById("help"), setActivity);
		
		return false;				
	}
		
	tabs = function () {
		header.html(Templates.tabs);
		makeTabs();

		return false;			
	};
	
	
	function addTouch(element, handler) {	
		
		element.on("touchstart", function(e) {
			$(e.currentTarget).addClass("active");
		});
		
		element.on("touchend", function(e) {
			var t = $(e.currentTarget);
			t.removeClass("active");
			event.stopPropagation();
			event.preventDefault();
			t.un("touchend");
			handler(e);
		});
		
		return element;
		
	}


// -----------------------------------------------------------------------------
//
// !FASTBUTTON
//
// -----------------------------------------------------------------------------

	coc.ui = {};
	coc.clickbuster = {};
			
	coc.ui.FastButton = function(element, handler) {
		this.element = element;
		this.handler = handler;
 
		element.addEventListener("touchstart", this, false);
		element.addEventListener("click", this, false);
	};

	coc.ui.FastButton.prototype.handleEvent = function(event) {
		$(this.element).addClass("active");
		switch (event.type) {
			case "touchstart": this.onTouchStart(event); break;
			case "touchmove": this.onTouchMove(event); break;
			case "touchend": this.onClick(event); break;
			case "click": this.onClick(event); break;
		}
	};
	
	coc.ui.FastButton.prototype.onTouchStart = function(event) {
	  event.stopPropagation();
	  
	  this.element.addEventListener("touchend", this, false);
	  document.body.addEventListener("touchmove", this, false);

	  this.startX = event.touches[0].clientX;
	  this.startY = event.touches[0].clientY;
	};
	
	coc.ui.FastButton.prototype.onTouchMove = function(event) {
	  if (Math.abs(event.touches[0].clientX - this.startX) > 10 ||
	      Math.abs(event.touches[0].clientY - this.startY) > 10) {
	    this.reset();
	  }
	};
	
	coc.ui.FastButton.prototype.onClick = function(event) {
		event.stopPropagation();
		
		this.reset();
		this.handler(event);

		if (event.type == "touchend") {
			coc.clickbuster.preventGhostClick(this.startX, this.startY);
		}
	};

	coc.ui.FastButton.prototype.reset = function() {
		$(this.element).removeClass("active");
		this.element.removeEventListener("touchend", this, false);
		document.body.removeEventListener("touchmove", this, false);
	};			


// -----------------------------------------------------------------------------
//
// !CLICKBUSTER
//
// -----------------------------------------------------------------------------

	coc.clickbuster.preventGhostClick = function(x, y) {
		coc.clickbuster.coordinates.push(x, y);
		window.setTimeout(coc.clickbuster.pop, 2500);
	};

	coc.clickbuster.pop = function() {
		coc.clickbuster.coordinates.splice(0, 2);
	};

	coc.clickbuster.onClick = function(event) {
		for (var i = 0; i < coc.clickbuster.coordinates.length; i += 2) {
			var x = coc.clickbuster.coordinates[i];
			var y = coc.clickbuster.coordinates[i + 1];
			
			if (Math.abs(event.clientX - x) < 25 && Math.abs(event.clientY - y) < 25) {
				event.stopPropagation();
				event.preventDefault();
			}
		}
	};


// -----------------------------------------------------------------------------
//
// !MAKE PRODUCTION TEMPLATES
// This section can be commented out for deployment
//
// -----------------------------------------------------------------------------
	

	function getTemplate(o) {
		get("html/" + o + ".html", function (e) {
			Templates[o] = e;
			cnt = cnt + 1;
			
			if (cnt === max) {
				begin();
			}
		});
		
		return false;
	}
	
	function makeTemplates() {
		var o;
		for (o in Templates) {
			if (Templates.hasOwnProperty(o)) {
				getTemplate(o);				
			}
		}
	}
	
	function mapsLoadedProduction() {
		content = $("#content");
		header = $("#header");
		feedback = $("#feedback");
		hider = $("#hider");
		
		signup = $("#signup");
		login = $("#login");
		initAuthentication();
		
		makeTemplates();
	}


// -----------------------------------------------------------------------------
//
// APP EVENTS
//
// -----------------------------------------------------------------------------

	function onBackKey() {
		switch (currentPage) {
			case 'index':
				device.exitApp();
				break;
			case 'map':
				index();
				break;
			case 'report':
				map();
				break;
			case 'address':
				report();
				break;
			case 'help':
				index();
				switchTabs($("#report"));			
				break;
			case 'status':
				index();
				switchTabs($("#report"));			
				break;
		}
	}
				

// -----------------------------------------------------------------------------
//
// !INITIALIZATION
//
// -----------------------------------------------------------------------------

	function begin() {
		tabs();
		activeTab = $(".selected");
		
		index();
		
		windowHeight = window.innerHeight;
		
		return false;
	}

	function mapsLoaded() {
		content = $("#content");
		header = $("#header");
		feedback = $("#feedback");
		hider = $("#hider");

		signup = $("#signup");
		login = $("#login");
		initAuthentication();
		
		begin();
	}

	function loadGoogleMaps() {
		// set to maps loaded for deploy
		googleCallback = mapsLoaded;
		//googleCallback = mapsLoadedProduction;
		try {
			var scriptTag = document.createElement("script");
			scriptTag.type = "text/javascript";
			scriptTag.src = "http://maps.google.com/maps/api/js?sensor=true&callback=googleCallback";			
			document.getElementsByTagName("head")[0].appendChild(scriptTag);	
		} catch (e) {
			navigator.notification.alert(
			    "Maps failed to load please restart the App.",  // message
			    null,         // callback
			    "Alert",            // title
			    "OK"                  // buttonName
			);			
		}
	}
	
	function run() {
        // iOS 7 status bar hack
        /*if (parseFloat(window.device.version) === 7.0) {
            document.body.style.marginTop = "20px";
        }*/
 
		try {
			Params.device_id = getUserID();
			Params.platform = device.platform || "iPhone";
		} catch (e) {
            // console.log("ERROR" + e.message);
		}
		
		// init button objects
		document.addEventListener("click", coc.clickbuster.onClick, true);
		coc.clickbuster.coordinates = [];
		
		// lifecycle events
		try {
			document.addEventListener("backbutton", onBackKey, true);
		} catch (e) {
			// not android
		}

		loadGoogleMaps();
		
		return false;
	}
		
	init = function () {
        document.addEventListener('deviceready', run, false); 

		return false;
	};		
	
}());
