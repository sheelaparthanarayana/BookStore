/*
 * verify the credit card number composed by 16 digits
 */
function ccverify() {
	var ccNum = document.getElementById("cardNum").value;
	var visaRegEx = /^\d{16}$/;
	var isValid = false;

	if (visaRegEx.test(ccNum)) {
		isValid = true;
	}
	
	if (!isValid) {
		
		 var d = new Date();
		 var m = d.getMonth()+1;
		 var y = d.getFullYear();
		alert("Incorrect Credit Card Number");
		document.getElementById('cardNum').value = null;
	}
}

/*
 * verify if Month and Year is valid (Past Months & Years shouldn't be accepted)
 */
function verifyExpiration(){
	var d = new Date();
	var m = d.getMonth()+1;
	var y = d.getFullYear();
	var selectedMonth = document.getElementById("ExpMon").value;
	var selectedYear = document.getElementById("ExpYear").value;

	var isCorrect=true;

	if( y==selectedYear && m>selectedMonth)
	{
	isCorrect=false;
	}
	if( y>selectedYear)
	{
	isCorrect=false;
	}
	if(!isCorrect)
	{
		 alert("Incorrect Credit Card Month and Year Expiry");
	     document.getElementById('cvv').value=null;
	     return false;
	}
	return true;
}



/*
 * verify if cvv is 3 digits
 */
function cvvverify() {
	var cvvno =/^\d{3}$/;
	var cvvNum = document.getElementById("cvv").value;
	isCorrect = false; 
	if(cvvno.test(cvvNum)){
		  isCorrect = true;
		  }
	  if(!isCorrect) {
		     alert("Incorrect CVV Format");
		     document.getElementById('cvv').value=null;
		  }
}

/*
 * assign hash to critical page onload
 */
function changeHashOnLoad() {
    window.location.href += "#";
    setTimeout("changeHashAgain()", "50");
}

/*
 * change the hash to others
 */
function changeHashAgain(){
    window.location.href += "1";
}
//save hash
var storedHash = window.location.hash;
//call this function every 50ms
window.setInterval(function () {
    if (window.location.hash != storedHash) {
        window.location.hash = storedHash;
    }
}, 50);