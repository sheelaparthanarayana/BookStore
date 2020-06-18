
/*
 * verify()
 * verify if user confirm password is the same as the one input before
 */
function verify() {
	if(document.getElementById('firstpass').value === document.getElementById('confirmpass').value) {
		var str = "Password Match";
		var strcolor = str.fontcolor("green");
		document.getElementById('subbtn').disabled = false;
		document.getElementById('displaymatch').innerHTML = strcolor;
	}else{
		var str = "Password Don't Match";
		var strcolor = str.fontcolor("red");
	    document.getElementById('subbtn').disabled = true;  
		document.getElementById('displaymatch').innerHTML = strcolor;
    }
}

/*
 * verify zip code is composed correctly in Uppdercase 
 */
function zipVerifyFirst() {
	var zipVal = document.getElementById("zip1").value;
	var zipPattern = /^([A-Z][0-9][A-Z])\s*([0-9][A-Z][0-9])$/;
	var isCorrect = false;
	if (zipPattern.test(zipVal)) {
		isCorrect = true;
	} 
	if(!isCorrect) {
     alert("Kindly provide a valid Zip.");
     document.getElementById('zip1').value=null;
  }
}

/*
 * verify zip code is composed correctly in Uppdercase
 */
function zipVerifySecond() {
	var zipVal = document.getElementById("zip2").value;
	var zipPattern = /^([A-Z][0-9][A-Z])\s*([0-9][A-Z][0-9])$/;
	var isCorrect = false;
	if (zipPattern.test(zipVal)) {
		isCorrect = true;
	} 
	if(!isCorrect) {
		alert("Kindly provide a valid Zip.");
		document.getElementById('zip2').value=null;
	}
}	

/*
 * verify is phone number is 10 digits
 */
function phoneVerify() {
	var phoneVal = document.getElementById("phoneno").value;
	var phoneRegex = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	if (phoneRegex.test(phoneVal)) {
		var formattedPhoneNumber =
			subjectString.replace(phoneRegex, "($1) $2-$3");
	} else {
		alert("its an invalid phone number");
		document.getElementById('phoneno').value=null;
	}
}

