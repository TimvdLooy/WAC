loadCountries();
var isoCode;
function loadCountries(){
	$.getJSON("restservices/Countries", function(data){
		var index = 0;
		$.each(data, function(key, value){
			var naam = "\'"+ value["name"] + "\'";
				$("#landTafel").append(
						"<tr class=\"lul\">" +
						"<td onclick=\"getUpdateDelete('"
						+ value["name"] 
						+"','"
						+ value["Hoofdstad"]
						+ "','"
						+ value["Regio"] 
						+"','"
						+ value["Oppervlakte"] 
						+"','"
						+ value["Inwoners"] 
						+"','"
						+value["getCode"]
						+"','"
						+value["isoCode2"]
						+"','"
						+value["lat"]
						+"','"
						+value["long"]
						+"','"
						+value["continent"]
						+"','"
						+value["goverment"]
						+"')\">"
						+ value["name"]
						+"</td>"+
						"<td>"+ value["Hoofdstad"] +"</td>" + "<td>"+ value["Regio"] +"</td>" + 
						"<td>"+ value["Oppervlakte"] +"</td>" + "<td>"+ value["Inwoners"] +"<td></tr>");
				index+=1;
		});
	});
}

function zoekFunctie(){
	$(".lul").remove();
	$.getJSON("restservices/Countries/zoek/" + $("#zoekWaarde").val() + "", function(data){
		var index = 0;
		$.each(data, function(key, value){
			console.log(value);
			var naam = "\'"+ value["name"] + "\'";
				$("#landTafel").append(
						"<tr class=\"lul\"><td onclick=\"getUpdateDelete("+ naam +")\">"+ value["name"] +"</td>"+
						"<td>"+ value["Hoofdstad"] +"</td>" + "<td>"+ value["Regio"] +"</td>" + 
						"<td>"+ value["Oppervlakte"] +"</td>" + "<td>"+ value["Inwoners"] +"<td></tr>");
				index+=1;
		});
	});
}


function getUpdateDelete(name, hoofdstad, regio, oppervlakte, inwoners, iso3, iso2, lat, long, continent, regering){
	isoCode = iso3;
	$("#updateForm").html("<label>naam: </label><input type='text' id='nameWaarde' name='nameWaarde' value='"+ name +"'><br>"
			+"<label>Hoofdstad: </label><input type='text' name='hoofdstadWaarde' id='hoofdstadWaarde' value='"+ hoofdstad +"'><br>"
			+"<label>Regio: </label><input type='text' name='regioWaarde' id='regioWaarde' value='"+ regio +"'><br>"
			+"<label>Oppervlakte: </label><input type='number' name= 'oppervlakteWaarde' id='oppervlakteWaarde' value="+ oppervlakte +"><br>"
			+"<label>Inwoners: </label><input type='number' id='inwonersWaarde' name='inwonersWaarde' value="+ inwoners +"><br>"
			+"<label>Iso code 3</label><input type='text' id='isoCode2', name='isoCode2', value="+iso2+"><br>"
			+"<label>latitude: </label><input type='number' id='lat' name='lat' value="+lat+"><br>"
			+"<label>longitude: </label><input type='number id='longitude' name='longitude' value="+long+"><br>"
			+"<label>continent: </label><input type='text' id='continent' name='continent' value='"+continent+"'><br>"
			+"<label>regering: </label><input type='text' id='regering' name='regering' value='"+regering+"'><br>"
			+"<input type='button' id=\"put\" value='Update' name='action'>"
			+"<input type='button' id='DELETE' value='Delete' name='action'>");
}

$("#insert").click(function(){
			var uri = "restservices/Countries";
	    $.ajax(uri, { 
	        type: "post", 
	        data: $("#insertForum").serialize(),
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer' + token);
	        },
	        success: function(response) {
	            $("#response").text("Something Inserted!");
	        },
	        error: function(response) {
	            $("#response").text("RIP!");
	        }
	    }); 
});

$('#login').click(function(event){
	var data = $('#loginForm').serialize();
	
	$.post("restservices/authentication", data, function(response){
		window.sessionStorage.setItem("sessionToken", response);
	}).fail(function(jqXHR, textStatus, errorThrown){
		console.log(textStatus);
		console.log(errorThrown);
	});
});

$(document).ready ( function () {
	$(document).on('click', "#put", function(){
		var uri = "restservices/Countries/" + isoCode;
	    $.ajax(uri, { 
	        type: "put", 
	        data: $("#updateForm").serialize(), 
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer' + token);
	        },
	        success: function(response) {
	            $("#response").text("Something saved!");
	        },
	        error: function(response) {
	            $("#response").text("RIP!");
	        }
	    }); 
	});
	
	$(document).on('click', "#DELETE", function(){
		var uri = "restservices/Countries/delete/" + isoCode;
	    $.ajax(uri, { 
	        type: "DELETE", 
	        data: $("#updateForm").serialize(), 
	        beforeSend: function(xhr){
	        	var token = window.sessionStorage.getItem("sessionToken");
	        	xhr.setRequestHeader('Authorization', 'Bearer' + token);
	        },
	        success: function(response) {
	            $("#response").text("Something deleted!");
	        },
	        error: function(response) {
	            $("#response").text("RIP de delete!");
	        }
	    }); 
	});
});
