loadCountries();
laadInfo();
function laadInfo(){
	$.getJSON("http://ip-api.com/json", function(data) {
		$("#landcode").append(data["countryCode"]);
		$("#land").append(data["country"]);
		$("#regio").append(data["regionName"]);
		$("#stad").append(data["city"]);
		$("#postcode").append(data["zip"]);
		$("#latitude").append(data["lat"]);
		$("#longitude").append(data["lon"]);
		$("#ip").append(data["query"]);
		getWeer(data["lat"], data["lon"], data["country"]);
	});
}

function laadInfo2(){
	$.getJSON("http://ip-api.com/json", function(data) {
		getWeer(data["lat"], data["lon"], "The " + data["country"]);
	});
}


function getWeer(lat, lon, naam){
	if(localStorage.getItem(naam) == null){
	$.getJSON("http://api.openweathermap.org/data/2.5/weather?lat="+ lat +"&lon="+ lon +"&appid=018136a312933e82e7019dbcb6ab6371", function(weer){
		$("#temp").html("<li id=\"temp\">Temparatuur: "+ Math.round((weer["main"]["temp"] - 273)*10)/10 + "C</li>");
		$("#luchtvocht").html("<li id=\"luchtvocht\">Luchtvochtigheid: "+ weer["main"]["humidity"] + "%</li>");
		$("#windsnelheid").html("<li id=\"windsnelheid\">Windsnelheid: "+ weer["wind"]["speed"] + " km/u</li>");
		$("#windrichting").html("<li id=\"windrichting\">Windrichting: "+ weer["wind"]["deg"] + " graden</li>");
		var date = new Date(weer["sys"]["sunrise"]);
		var time = "<li id=\"zonsopgang\">Zonsopgang: "+ date.getHours() + ':' + date.getMinutes() + ":" + date.getSeconds() + "</li>";
		$("#zonsopgang").html(time);
		var date2 = new Date(weer["sys"]["sunset"]);
		var time2 = "<li id=\"zonsondergang\">Zonsondergang: "+ date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds() + "</li>";
		$("#zonsondergang").html(time2);
		$("#landWeer").html("<h1 id=\"landWeer\">the weather in "+ naam +"<h1>");
		var waardes = [];
		waardes.push("<li id=\"temp\">Temparatuur: "+ Math.round((weer["main"]["temp"] - 273)*10)/10 + "C</li>");
		waardes.push("<li id=\"luchtvocht\">Luchtvochtigheid: "+ weer["main"]["humidity"] + "%</li>");
		waardes.push("<li id=\"windsnelheid\">Windsnelheid: "+ weer["wind"]["speed"] + " km/u</li>");
		waardes.push("<li id=\"windrichting\">Windrichting: "+ weer["wind"]["deg"] + " graden</li>");
		waardes.push(time);
		waardes.push(time2);
		waardes.push("<h1 id=\"landWeer\">the weather in "+ naam +"<h1>");
		var d = new Date();
		var t = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		console.log(t);
		waardes.push(t)
		console.log("request made");
		localStorage.setItem(naam, JSON.stringify(waardes));
	});
	}
	else{
		var storedWaardes = JSON.parse(localStorage.getItem(naam));
		console.log("array: " + storedWaardes[7]);
		var currentDate = new Date();
		currentDate.setMinutes(currentDate.getMinutes()+10);
		if(currentDate > Date.parse("01/01/2011 " + storedWaardes[7])){
			$("#temp").html(storedWaardes[0]);
			$("#luchtvocht").html(storedWaardes[1]);
			$("#windsnelheid").html(storedWaardes[2]);
			$("#windrichting").html(storedWaardes[3]);
			$("#zonsopgang").html(storedWaardes[4]);
			$("#zonsondergang").html(storedWaardes[5])
			$("#landWeer").html(storedWaardes[6]);
		}
	}
}

function loadCountries(){
	$.getJSON("http://localhost:4711/firstapp/restservices/Countries", function(data){
		var index = 0;
		$.each(data, function(key, value){
			var naam = "\'"+ value["name"] + "\'";
				$("#"+index.toString()).append(
						"<td onclick=\"getWeer("+ value["lat"] +", "+ value["long"] +", "+ naam +")\">"+ value["name"] +"</td>"+
						"<td>"+ value["Hoofdstad"] +"</td>" + "<td>"+ value["Regio"] +"</td>" + 
						"<td>"+ value["Oppervlakte"] +"</td>" + "<td>"+ value["Inwoners"] +"<td>");
				index+=1;
		});
	});
}