$("#inputField").on("keyup", function(){
	store = this.value;
	localStorage.setItem("store", store);
	console.log(event);
	console.log(localStorage.getItem("store"));
});

window.addEventListener('storage', function(event){
	$("#labelWaarde").text(event.newValue);
});

$("#waarde").ready(() =>{
	$("#waardeText").text(localStorage.getItem("store"));
	console.log(localStorage.getItem("store"));
});

