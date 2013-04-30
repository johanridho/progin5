Rp(function() {
	Rp('#email').on('keyup', function() {
		//javascript email regex from regularexpressionsrightnow.com
		var match = this.value.match(/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|”(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*”)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/);
		xmlhttp=new XMLHttpRequest();
		xmlhttp.open("GET","core/api.php/search/"+this.value+"/8/0/1/0",false);
		xmlhttp.send();
		var parsedJSON = eval('('+xmlhttp.responseText+')');
		if (match&&(this.value!=Rp('#password').val())&&typeof parsedJSON.q === "undefined") {
			// bener
			Rp(this).removeClass('invalid');
			_email = true;
		}
		else {
			// ga valid
			console.log('Invalid email');
			Rp(this).addClass('invalid');
			_email = false;
		}
		validateRegistration();
	});
	Rp('#username').on('keyup', function() {
		var match = this.value.match(/.{5,}/);
		xmlhttp=new XMLHttpRequest();
		xmlhttp.open("GET","core/api.php/search/"+this.value+"/7/0/1/0",false);
		xmlhttp.send();
		var parsedJSON = eval('('+xmlhttp.responseText+')');
		if ((this.value != Rp('#email').val())&&match&&typeof parsedJSON.q === "undefined"){
			//valid
			Rp(this).removeClass('invalid');
			_username = true;
		}else{
			//error
			console.log('Invalid username');
			Rp(this).addClass('invalid');
			_username = false;
		}
		validateRegistration();
	});
	Rp('#password').on('keyup',function(){
		var match = this.value.match(/.{8,}/);
		if( (this.value!=Rp('#username').val())&&(this.value!=Rp('#email').val())&&match ){
			//valid
			Rp(this).removeClass('invalid');
			_password = true;
		}else{
			//error
			console.log('Invalid password');
			Rp(this).addClass('invalid');
			_password = false
		}
		validateRegistration();
	});
	Rp('#password_k').on('keyup',function(){
		if(this.value==Rp('#password').val() && _password){
			//valid
			Rp(this).removeClass('invalid');
			_password2 = true;
		}else{
			//error
			console.log('Invalid password2');
			Rp(this).addClass('invalid');
			_password2 = false;
		}
		validateRegistration();
	});
	Rp('#nama_lengkap').on('keyup', function() {
		var match = this.value.match(/^[A-z]([-']?[A-z]+)*( [A-z]([-']?[A-z]+)*)+$/);
		if (!match){
			//error
			console.log('Invalid nama');
			Rp(this).addClass('invalid');
			_name = false;
		}else{
			//valid
			Rp(this).removeClass('invalid');
			_name = true;
		}
		validateRegistration();
	});
	function validateRegistration(){
		filename = document.getElementById('avatar').value;
		if (_email && _username && _password && _password2 && _name && Rp('#tanggal_lahir').val() != "" && filename.substring(filename.lastIndexOf('.')+1) == "jpg"){
			//valid
			Rp('#submitButton').removeAttribute('disabled');
		}else{
			Rp('#submitButton').attr('disabled', 'disabled');
		}
	}
});

// settings
// var FILE_ENCODING = 'utf-8',
    // EOL = '\n';
 

// function
// var error = false;
// var usr = document.getElementById("username").value;
// var pwd = document.getElementById("password").value;
// var email = document.getElementById("email").value;
// var kpwd = document.getElementById("password_k").value;
// function init(){
	// usr = document.getElementById("username").value;
	// pwd = document.getElementById("password").value;
	// email = document.getElementById("email").value;
	// kpwd = document.getElementById("password_k").value;
// }
// function change1(){
	// if (pwd != null && usr == pwd){
		// alert("Username tidak boleh sama dengan password");
		// document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password\n";
		// error = true;
	// }
// }
// function check2(){
	// if (usr != null && usr == pwd){
		// document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password";
		// error = true;
	// }
	// if (email != null && email == pwd){
		// document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		// error = true;
	// }
	// if (kpwd != null && kpwd == pwd){
		// document.getElementById("errMsg3").innerHTML="Password harus sama";
		// error = true;
	// }
// }
// function check3(){
	// var patt=/.+@.+\.{2,}/
	// if(patt.match!=null){
		// document.getElementById("errMsg2").innerHTML="Email error";
		// error = true;
	// }
	// if (email != null && email == pwd){
		// document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		// error = true;
	// }
// }
