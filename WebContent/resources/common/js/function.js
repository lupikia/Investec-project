	var numbers = [];
	var commonDominators={};
   
   function addNumber(){
	   
	 
		 var value= document.getElementById("form-input:newNumber").value;
		 
		 d=value;
		 //-->check if number is not empty
		 if(value != "" && typeof value == "number"){
			 
			 //-->check if new number already exist
			 
			 if(!value.indexOf())
			 {
				 numbers.push(value);
				 
				 var list = "<ul>";
				 for(var t = 0; t< numbers.length;t++){
					 
					 list += "<li>" + numbers[t] + "</li>";
				 }
				 list += "</ul>";
				 
				 document.getElementById("allNumbers").innerHTML=list;
				 
			 }else{
				 
				 errorMessage(value+", already exist in array");
			 }
		 }else{
			 
			 errorMessage("The new number entered is invalid");
		 }
		 
		 return false;
   }
function errorMessage(message){
	   
	   if(message != "")
	   {
		 document.getElementById("errorMessage").innerHTML = message;
	   }else{
		   
		   document.getElementById("errorMessage").innerHTML ="";
	   }
   }
function getCommonFactor(){
	
	 
}