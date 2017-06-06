var inputValue = "";
var pressedNumbers = [];
var signClicked = false;

function Number_click(clickedId){
  if(signClicked = false){
    pressedNumbers.push(clickedId);
  }
  else{
    pressedNumbers.push(pressedNumbers.length+clickedId);
    signClicked = false;
  }
  inputValue = inputValue + clickedId;
  document.getElementById("input_field").value = inputValue;
}

function Sign_click(clickedId){
  inputValue = inputValue + clickedId;
  document.getElementById("input_field").value = inputValue;
  signClicked = true;
}

function Calculate(){
  inputValue = eval(inputValue);
  document.getElementById("input_field").value = inputValue;
}

function Clear_click(){
  inputValue ="";
  document.getElementById("input_field").value = inputValue;
}
