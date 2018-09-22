function doAJAX() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("reply").innerHTML = this.responseText

		}
	};
	xhttp.open("get", "/AngJSWebApp/asdfsdaf.do", true);
	xhttp.send();
}

function getAll() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 & this.status == 200) {
			let empJSON = JSON.parse(this.response);
			empJSON.forEach((employee) => {
				$("#test").append("<p>" + employee.userId + " " + employee.username + " " + employee.password + " " + employee.email + "<p>");
			});
		}
	};
	xhttp.open("get", "/AngJSWebApp/getall.do", true);
	xhttp.send();
}

function submitForm() {

	let subEmail = $("#subEmail").val();
	let subPass = $("#subPass").val();
	let subCheck = $("#subCheck").is(':checked');

	console.log(subEmail + " " + subPass + " " + subCheck);
	console.log(subCheck + "haha");

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 & this.status == 200) {
			let loginJSON = JSON.parse(this.response);
			console.log(loginJSON);
			if(loginJSON){
				$("#test").append("<p>" + loginJSON.userId + " " + loginJSON.username + " " + loginJSON.password + " " + loginJSON.email + "<p>");
			} else {
				$("#test").append("<p>incorrect login</p>");
			}
		}
	};
	xhttp.open("post", "/AngJSWebApp/tryLogin.do", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("email=" + subEmail + "&password=" + subPass + "&isManager=" + subCheck);

}