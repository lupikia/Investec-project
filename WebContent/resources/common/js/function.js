	var numbers = [];
	var commonfactors={};
	var match={};
function addNumber(){
	   
	 
	 var value= document.getElementById("form-input:newNumber").value;
	 
	 d=value;
	 
	 var patt = new RegExp("^[0-9]+$");
	 var validated = patt.test(value);
	 
	 //-->check if number is not empty
	 if(value != "" && typeof validated){
		 
		 //-->check if new number already exist
		 if(numbers.indexOf(value) ===-1)
		 {
			 numbers.push(value);
			 generateList(numbers);
		 }else{
			 errorMessage(value+", already exist in array");
		 }
	 }else{
		 
		 errorMessage("The new number entered is invalid");
	 }
}
function errorMessage(message){
	   
	   if(message != "")
	   {
		 document.getElementById("errorMessage").innerHTML = message;
	   }else{
		   
		   document.getElementById("errorMessage").innerHTML ="";
	   }
}

function generateList(){
	
	if(numbers.length>=1)
	{
		var list = "<ul id='array-integers'>";
		 for(var t = 0; t< numbers.length;t++){
			 
			 list += "<li>" + numbers[t] + "</li>";
		 }
		 list += "</ul>";
		 
		 document.getElementById("all-numbers").innerHTML=list;
		 
		 //-->check add event listener
			 addListnerToList();
			 errorMessage("");
	 }else{
		 document.getElementById("all-numbers").innerHTML="";
		 errorMessage("");
	 }
}

function addListnerToList(){
	
	document.getElementById("array-integers").addEventListener("click", function (e) {
		var selectedValue=e.target.innerText;
		var index= numbers.indexOf(selectedValue);
		if(index>-1)
		{
			for( var i = 0; i < numbers.length; i++){ 
				   if ( numbers[i] === selectedValue) {
					   numbers.splice(i, 1); 
				   }
				}
			generateList();
		}
	    	
	})
	
}

function renderResults(){
	
		
	//-->get integer with lowers factor
	var lowest = commonfactors[numbers[0]].length;
	var lowestFactors = numbers[0];

	Object.keys(commonfactors).forEach(function(index){
		
		if(numbers[0]!==index){
			match[index]=[];
			for(var u=0; u<commonfactors[index].length;u++){
				
				//-->check that an array does not go out off bounce
				if(u<=lowest){
					
					
					
					if(lowestFactors.indexOf(commonfactors[index][u])>-1){
						
						match[index].push(commonfactors[index][u]);
						d=match;
					}
				}
			}
		}
	});
	
	 highest= commonfactors[numbers[0]][commonfactors[numbers[0]].length-1];
	//-->get the highest factor
	if(Object.keys(match).length>1)
	{
		var tempHighest = Object.keys(match)[0];
	
		Object.keys(match).forEach(function(index){
			
			if(index!==tempHighest){
				
				if(match[index].length< match[tempHighest].length){
					tempHighest = index;
				}
			}
		});
		highest=match[tempHighest][match[tempHighest].length -1];
	}
	
	


	
	var display="";
	//-->display all the results
	Object.keys(commonfactors).forEach(function(index){
		
		display+="<p>"+ index + ": "+ commonfactors[index].toString() + "</p>";
	});
	
	document.getElementById("render-fators").innerHTML=display;
	document.getElementById("Highest-fator").innerHTML="<h2>The highest common factor is: " + highest  + "</h2>";
	
}

function getCommonFactor(){
	
	commonfactors={};
	 for(var z= 0; z<numbers.length;z++){
		
		 commonfactors[numbers[z]]=[];
		 var y=0;
		 while(y<=numbers[z]){
			 
			 if((numbers[z]%y)==0){
				 commonfactors[numbers[z]].push(y);
			 }
			 y++;
		 }
	 }
	 renderResults();
}
