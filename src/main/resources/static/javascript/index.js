document.getElementById("submit").addEventListener("click",(event)=> {
	event.preventDefault();
	console.log(document.getElementById("firstname").value);
	let firstname = document.getElementById("firstname").value;
	let lastname = document.getElementById("lastname").value;
	let email = document.getElementById("email").value;
	let phone = document.getElementById("phone").value;
	let password = document.getElementById("password").value;
	
	let user = {
		firstName:firstname,
		lastName:lastname,
		email:email,
		phone:phone,
		password:password
	};
	
	const url = "http://localhost:8080/user";
	fetch(url,{
		method:"POST",
		headers:{
			"content-type":"application/json"
		},
		body:JSON.stringify(user)
	}).then(response => {
		console.log(response.status);
		if(response.status===200){
			document.getElementById("container").innerHTML="Registration Successful !"
		}
	}).then(data =>{
		console.log(data);
	});
})