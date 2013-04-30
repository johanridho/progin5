Rp(function() {
	genComment = function(user_id, author, body, time, comment_id) {
		var oImg=document.createElement("img");
		oImg.setAttribute('src', 'avatar/'+user_id+'.jpg');
		oImg.setAttribute('class', 'imgComment');
		oImg.setAttribute('height', '32px');
		oImg.setAttribute('width', '32px');
		article = document.createElement('article');
		article.className = 'comment';
		header = document.createElement('header');
		h4 = document.createElement('h4');
		h4.appendChild(document.createTextNode(author));
		header.appendChild(oImg);
		header.appendChild(h4);

		var today = new Date(time*1000);
		var dd = today.getDate();
		var mm = today.getMonth()+1;
		var hours = today.getHours();
		var minutes = today.getMinutes();
		if(hours<10){hours='0'+hours}
		if(minutes<10){minutes='0'+minutes}
		if(dd<10){dd='0'+dd}
		if(mm<10){mm='0'+mm}
		d=hours+':'+minutes+' - '+dd+'/'+mm;

		times = document.createElement('span');
		times.className = 'time';
		times.appendChild(document.createTextNode(d));
		header.appendChild(times);
		
		if (user_id == localStorage.user_id) {
			del = document.createElement('a');
			del.setAttribute('href', '#');
			del.setAttribute('onclick', 'deleteComment('+comment_id+')');
			del.appendChild(document.createTextNode('Delete'));
			header.appendChild(del);
		}
		
		body = body.replace('\n', '<br>');
		p = document.createElement('p');
		p.appendChild(document.createTextNode(body));

		article.appendChild(header);
		article.appendChild(p);

		return article;
	};
	
	genTask = function(task) {
		article = document.createElement('article');
		article.className = 'task';
		header = document.createElement('header');
		h1 = document.createElement('h1');
		label = document.createElement('label');
		span = document.createElement('span');
		span.className = 'task-checkbox';
		chkbox = document.createElement('input');
		chkbox.className = 'task-checkbox';
		chkbox.setAttribute('type', 'checkbox');
		if (typeof _category_id !== "undefined")
			chkbox.setAttribute('onclick', 'negateTask('+task.task_id+'); refreshTask('+localStorage.user_id+','+_category_id+')');
		else
			chkbox.setAttribute('onclick', 'negateTask('+task.task_id+');');
		if (task.done == 1)
			chkbox.setAttribute('checked');
		span.appendChild(chkbox);
		title = document.createElement('a');
		title.appendChild(document.createTextNode(task.name));
		title.setAttribute('href','view_tugas.php?task_id='+task.task_id);
		label.appendChild(span);
		label.appendChild(title);
		h1.appendChild(label);
		header.appendChild(h1);
		
		details = document.createElement('div');
		details.className = 'details';
		p = document.createElement('p');
		p.className = 'deadline';
		detail_label = document.createElement('span');
		detail_label.className = 'detail-label';
		detail_label.appendChild(document.createTextNode('Deadline:'));
		detail_content = document.createElement('span');
		detail_content.className = 'detail-content';
		detail_content.appendChild(document.createTextNode(task.deadline));
		p.appendChild(detail_label);
		p.appendChild(detail_content);
		
		tags = document.createElement('p');
		tags.className = 'tags';
		detail_label = document.createElement('span');
		detail_label.className = 'detail-label';
		detail_label.appendChild(document.createTextNode('Tag:'));
		tags.appendChild(detail_label);
		for(i = 0; i < task.tags.length; i++) {
			tag = document.createElement('span');
			tag.className = 'tag';
			tag.appendChild(document.createTextNode(task.tags[i].name));
			tags.appendChild(tag);
		}
		
		details.appendChild(p);
		details.appendChild(tags);
		
		if (task.user_id == localStorage.user_id) {
			del = document.createElement('a');
			del.setAttribute('href', '#');
			del.setAttribute('onclick', 'deleteTask('+task.task_id+')');
			del.appendChild(document.createTextNode('Delete'));
			details.appendChild(del);
		}

		article.appendChild(header);
		article.appendChild(details);

		return article;
	};
	
	genTask1 = function(task) {
		article = document.createElement('article');
		article.className = 'task';
		header = document.createElement('header');
		h1 = document.createElement('h1');
		label = document.createElement('label');
		span = document.createElement('span');
		title = document.createElement('a');
		title.appendChild(document.createTextNode(task.name));
		title.setAttribute('href','view_tugas.php?task_id='+task.task_id);
		label.appendChild(span);
		label.appendChild(title);
		h1.appendChild(label);
		header.appendChild(h1);
		
		details = document.createElement('div');
		details.className = 'details';
		p = document.createElement('p');
		p.className = 'deadline';
		detail_label = document.createElement('span');
		detail_label.className = 'detail-label';
		detail_label.appendChild(document.createTextNode('Deadline:'));
		detail_content = document.createElement('span');
		detail_content.className = 'detail-content';
		detail_content.appendChild(document.createTextNode(task.deadline));
		p.appendChild(detail_label);
		p.appendChild(detail_content);
		
		tags = document.createElement('p');
		tags.className = 'tags';
		detail_label = document.createElement('span');
		detail_label.className = 'detail-label';
		detail_label.appendChild(document.createTextNode('Tag:'));
		tags.appendChild(detail_label);
		for(i = 0; i < task.tags.length; i++) {
			tag = document.createElement('span');
			tag.className = 'tag';
			tag.appendChild(document.createTextNode(task.tags[i].name));
			tags.appendChild(tag);
		}
		
		details.appendChild(p);
		details.appendChild(tags);
		
		article.appendChild(header);
		article.appendChild(details);

		return article;
	};

	genCategory = function(category) {
		article = document.createElement('article');
		article.className = 'task';
		header = document.createElement('header');
		h1 = document.createElement('h1');
		label = document.createElement('label');
		title = document.createElement('a');
		title.appendChild(document.createTextNode(category.name));
		title.setAttribute('href','dashboard.php');
		label.appendChild(title);
		h1.appendChild(label);
		header.appendChild(h1);

		article.appendChild(header);

		return article;
	};
	genUser = function(user) {
		article = document.createElement('article');
		article.className = 'task';
		header = document.createElement('header');
		h1 = document.createElement('h1');
		label = document.createElement('label');
		span = document.createElement('span');
		span.className = 'imgAvatar';
		imgAvatar = document.createElement('img');
		imgAvatar.className = 'imgProfileLink';
		imgAvatar.setAttribute('src', 'avatar/'+user.user_id+'.jpg');
		span.appendChild(imgAvatar);
		title = document.createElement('a');
		title.appendChild(document.createTextNode(user.username));
		title.setAttribute('href','profile.php?user_id='+user.user_id);
		label.appendChild(span);
		label.appendChild(title);
		h1.appendChild(label);
		header.appendChild(h1);
		
		details = document.createElement('div');
		details.className = 'details';
		p = document.createElement('p');
		p.className = 'name';
		p.appendChild(document.createTextNode(user.name));
				
		details.appendChild(p);
		
		article.appendChild(header);
		article.appendChild(details);

		return article;
	};


	Rp('#searchBar').on('keyup', function() {
		q = this.value;
		e = document.getElementById('searchMode');
		mode = e.options[e.selectedIndex].value;
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				var parsedJSON = eval('('+xmlhttp.responseText+')');
				document.getElementById("suggestion").innerHTML = "";
				for (index=0; index < parsedJSON.length; index++) {
					document.getElementById("suggestion").innerHTML += '<option value="'+parsedJSON[index].q+'">\n'
				}
			}
		}
		xmlhttp.open("GET","core/api.php/search/"+q+"/"+mode+"/1/0/0",true);
		xmlhttp.send();
	});
	
	assignee_autocomplete = function(field) {
		q = field.value;
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				var parsedJSON = eval('('+xmlhttp.responseText+')');
				document.getElementById("suggestion").innerHTML = "";
				for (index=0; index < parsedJSON.length; index++) {
					document.getElementById("suggestion").innerHTML += '<option value="'+parsedJSON[index].q+'">\n'
				}
			}
		}
		xmlhttp.open("GET","core/api.php/search/"+q+"/5/1/0/0",true);
		xmlhttp.send();
	};
	
	tag_autocomplete = function(field) {
		q = field.value;
		xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				var parsedJSON = eval('('+xmlhttp.responseText+')');
				document.getElementById("suggestion").innerHTML = "";
				for (index=0; index < parsedJSON.length; index++) {
					document.getElementById("suggestion").innerHTML += '<option value="'+parsedJSON[index].q+'">\n'
				}
			}
		}
		xmlhttp.open("GET","core/api.php/search/"+q+"/6/1/0/0",true);
		xmlhttp.send();
	};

	Rp('#more_assignee').on('click', function(e) {
		e.preventDefault();

		document.getElementById('assignee_field').innerHTML += '<input size="30" maxlength="50" name="assignee[]" id="assignee_add" type="text" onkeyup="assignee_autocomplete(this)" list="suggestion">';
	})

	Rp('#more_attachment').on('click', function(e) {
		e.preventDefault();

		document.getElementById('attachment_field').innerHTML += '<input size="30" maxlength="50" name="attachment[]" id="attachment" type="file" accept="image/*,video/*">';
	})

	Rp('#commentForm').on('submit', function(e) {
		e.preventDefault();
		body = Rp('#commentBody').val();
		xmlhttp=new XMLHttpRequest();
		xmlhttp.open("POST","postComment.php",false);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("task_id="+_task_id+"&content="+body);
		refreshComment(_task_id,_page);
		Rp('#commentBody').nodes[0].value = "";
	});

	Rp('#loginLink').on('click', function(e) {
		e.preventDefault();

		Rp('#loginBox').toggleClass('visible');

		Rp('#login_username').nodes[0].focus();
	})

	Rp('#categoryLink').on('click', function(e) {
		e.preventDefault();

		Rp('#loginBox').toggleClass('visible');

		Rp('#category_name').nodes[0].focus();
	})

	Rp('#loginForm').on('submit', function(e) {

		e.preventDefault();
		u = Rp('#login_username').val();
		p = Rp('#login_password').val();
		xmlhttp=new XMLHttpRequest();
		xmlhttp.open("GET","core/api.php/auth/"+u+"/"+p,false);
		xmlhttp.send();
		var parsedJSON = eval('('+xmlhttp.responseText+')');

		if (parsedJSON.success) {
			localStorage.user_id = parsedJSON.user_id;
			window.location.href = 'dashboard.php';
		}
		else
			alert('Invalid username/password combination.');
	});

	Rp('#newCategoryForm').on('submit', function(e) {

		this.submit();
	});
	
	Rp('#editTaskLink').on('click', function(e) {
		e.preventDefault();

		if (Rp('#editTaskLink').hasClass('editing')) {
			Rp('#editTaskLink').nodes[0].innerHTML = 'Edit Task';
			Rp('#editTaskLink').removeClass('editing');
			Rp('#edit-task').hide();
			Rp('#current-task').show();
		}
		else {
			Rp('#editTaskLink').nodes[0].innerHTML = 'View Task';
			Rp('#editTaskLink').addClass('editing');
			Rp('#current-task').hide();
			Rp('#edit-task').nodes[0].style.display = 'block';
		}
	});
	
	Rp('#editProfileLink').on('click', function(e) {
		e.preventDefault();

		if (Rp('#editProfileLink').hasClass('editingprofile')) {
			Rp('#editProfileLink').nodes[0].innerHTML = 'Edit Profile';
			Rp('#editProfileLink').removeClass('editingprofile');
			Rp('#edit-profile').hide();
			Rp('#current-profile').show();
			alert("Nothing Change!");
		}
		else {
			Rp('#editProfileLink').nodes[0].innerHTML = 'Done';
			Rp('#editProfileLink').addClass('editingprofile');
			Rp('#current-profile').hide();
			Rp('#edit-profile').nodes[0].style.display = 'block';


		}
	});
	
});

function updateTask(mode,task_id){
	if (mode == "deadline") {
		value = Rp('#deadline').val();
	}
	if (mode == "addassignee") {
		value = Rp('#addassignee').val();
	}
	if (mode == "addtag") {
		value = Rp('#addtag').val();
	}
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("PUT","core/api.php/updateTask/"+task_id+"/"+mode+"/"+value,false);
	xmlhttp.send();
}

function updateProfile(mode,user_id){
	if (mode == "name") {
		value = Rp('#name').val();
	}
	if (mode == "tanggal_lahir") {
		value = Rp('#tanggal_lahir').val();
	}
	if (mode == "password") {
		if (Rp('#password').val() != Rp('#password_k').val()) {
			alert('password tidak sama!');
			return;
		}
		value = Rp('#password').val();
		
	}
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("PUT","core/api.php/updateProfile/"+user_id+"/"+mode+"/"+value,false);
	xmlhttp.send();
}

function removeElement(mode,field,task_id,id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("PUT","core/api.php/updateTask/"+task_id+"/"+mode+"/"+id,false);
	xmlhttp.send();
	field.outerHTML = "";
}

function negateTask(task_id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("PUT","core/api.php/negateTask/"+task_id,false);
	xmlhttp.send();
}

function deleteComment(comment_id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("DELETE","core/api.php/deleteComment/"+comment_id,false);
	xmlhttp.send();
	if ((_page-1)*10 == parseInt(_commentCount-1)) {
		_page = _page - 1;
	}
	refreshComment(_task_id,_page);
}

function deleteTask(task_id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("DELETE","core/api.php/deleteTask/"+task_id,false);
	xmlhttp.send();
	refreshTask(localStorage.user_id,_category_id);
}

function deleteCategory(category_id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("DELETE","core/api.php/deleteCategory/"+category_id,false);
	xmlhttp.send();
	refreshTask(localStorage.user_id,0);
	refreshCategory(localStorage.user_id);
	document.getElementById('taskLink').setAttribute('style','display:none;');
}

function refreshComment(task_id,page){
	_page = page;
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/getComment/"+task_id+"/"+((page-1)*10),false);
	xmlhttp.send();
	var parsedJSON = eval('('+xmlhttp.responseText+')');
	document.getElementById('commentsList').innerHTML = "";
	for (index=0; index < parsedJSON.length; index++) {
		comment = genComment(parsedJSON[index].user_id,parsedJSON[index].user_name,parsedJSON[index].content,parsedJSON[index].time,parsedJSON[index].comment_id);
		document.getElementById('commentsList').innerHTML += comment.outerHTML;
	}
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/countComment/"+task_id,false);
	xmlhttp.send();
	_commentCount = xmlhttp.responseText;
	document.getElementById('commentCount').innerHTML = _commentCount;
	if (xmlhttp.responseText > 10) {
		document.getElementById('commentPage').innerHTML = "Page :";
		for (index=1; (index-1)*10 < _commentCount; index++) {
			number = document.createElement('a');
			number.className = 'numbers';
			if (index == page)
				number.className += ' active';
			number.setAttribute('href', '#');
			number.setAttribute('onclick', 'refreshComment('+_task_id+','+index+')');
			number.appendChild(document.createTextNode(index));
			document.getElementById('commentPage').innerHTML += number.outerHTML;
		}
	} else {
		document.getElementById('commentPage').innerHTML = "";
	}
}

function refreshTask(user_id,category_id){
	_category_id = category_id;
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/getTask/"+user_id+"/"+category_id,false);
	xmlhttp.send();
	var parsedJSON = eval('('+xmlhttp.responseText+')');
	header = document.createElement('header');
	h3 = document.createElement('h3');
	h3.appendChild(document.createTextNode('Current Task'));
	header.appendChild(h3);
	document.getElementById('activeTask').innerHTML = header.outerHTML;
	header = document.createElement('header');
	h3 = document.createElement('h3');
	h3.appendChild(document.createTextNode('Completed Task'));
	header.appendChild(h3);
	document.getElementById('doneTask').innerHTML = header.outerHTML;
	var done =false;
	var active = false;
	for (index=0; index < parsedJSON.length; index++) {
		task = genTask(parsedJSON[index]);
		if (parsedJSON[index].done == 0) {
			document.getElementById('activeTask').innerHTML += task.outerHTML;
			active = true;
		} else {
			document.getElementById('doneTask').innerHTML += task.outerHTML;
			done = true;
		}
	}
	if (active == 0)
		document.getElementById('activeTask').innerHTML += 'No task available!';
	if (done == 0)
		document.getElementById('doneTask').innerHTML += 'No task available!';
}

function refreshTask1(user_id,category_id){
	_category_id = category_id;
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/getTask/"+user_id+"/"+category_id,false);
	xmlhttp.send();
	var parsedJSON = eval('('+xmlhttp.responseText+')');
	header = document.createElement('header');
	h3 = document.createElement('h3');
	h3.appendChild(document.createTextNode('Current Task'));
	header.appendChild(h3);
	document.getElementById('activeTask1').innerHTML = header.outerHTML;
	header = document.createElement('header');
	h3 = document.createElement('h3');
	h3.appendChild(document.createTextNode('Completed Task'));
	header.appendChild(h3);
	document.getElementById('doneTask1').innerHTML = header.outerHTML;
	var done =false;
	var active = false;
	for (index=0; index < parsedJSON.length; index++) {
		task = genTask1(parsedJSON[index]);
		if (parsedJSON[index].done == 0) {
			document.getElementById('activeTask1').innerHTML += task.outerHTML;
			active = true;
		} else {
			document.getElementById('doneTask1').innerHTML += task.outerHTML;
			done = true;
		}
	}
	if (active == 0)
		document.getElementById('activeTask1').innerHTML += 'No task available!';
	if (done == 0)
		document.getElementById('doneTask1').innerHTML += 'No task available!';
}

function refreshCategory(user_id){
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/getCategory/"+user_id,false);
	xmlhttp.send();
	var parsedJSON = eval('('+xmlhttp.responseText+')');
	document.getElementById('categoryList').innerHTML = "";
	for (index=0; index < parsedJSON.length; index++) {
		list = document.createElement('li');
		link = document.createElement('a');
		link.appendChild(document.createTextNode(parsedJSON[index].name));
		link.setAttribute('href', '#');
		link.setAttribute('onclick', 'selectCategory('+localStorage.user_id+','+parsedJSON[index].category_id+');');
		list.appendChild(link);
		if (parsedJSON[index].user_id == localStorage.user_id) {
			del = document.createElement('span');
			del.setAttribute('onclick', 'deleteCategory('+parsedJSON[index].category_id+')');
			del.appendChild(document.createTextNode('(x delete '+parsedJSON[index].name+')'));
			list.appendChild(del);
		}
		document.getElementById('categoryList').innerHTML += list.outerHTML;
	}
	if (parsedJSON.length == 0)
		document.getElementById('categoryList').innerHTML += 'No Category Available!';
}

function selectCategory(user_id,category_id) {
	document.getElementById('taskLink').setAttribute('style','display:block;');
	document.getElementById('taskLink').setAttribute('href','new_tugas.php?category_id='+category_id);
	refreshTask(user_id,category_id);
}

function getSearchResult(q,mode,page){
	_page = page+1;
	xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","core/api.php/search/"+q+"/"+mode+"/0/0/"+page*10,false);
	xmlhttp.send();
	var parsedJSON = eval('('+xmlhttp.responseText+')');
	for (index=0; index < parsedJSON.length; index++) {
		if (parsedJSON[index].type == 'task') {
			if (!tasks) {
				header = document.createElement('header');
				h3 = document.createElement('h3');
				h3.appendChild(document.createTextNode('Tasks'));
				header.appendChild(h3);
				document.getElementById('searchResult').innerHTML += header.outerHTML;
				tasks = true;
			}
			elsearch = genTask(parsedJSON[index]);
		} else {
			if (parsedJSON[index].type == 'user') {
				if (!users) {
					header = document.createElement('header');
					h3 = document.createElement('h3');
					h3.appendChild(document.createTextNode('Users'));
					header.appendChild(h3);
					document.getElementById('searchResult').innerHTML += header.outerHTML;
					users = true;
				}
				elsearch = genUser(parsedJSON[index]);
			} else {
				if (!categories) {
					header = document.createElement('header');
					h3 = document.createElement('h3');
					h3.appendChild(document.createTextNode('Categories'));
					header.appendChild(h3);
					document.getElementById('searchResult').innerHTML += header.outerHTML;
					categories = true;
				}
				elsearch = genCategory(parsedJSON[index]);
			}
		}
		document.getElementById('searchResult').innerHTML += elsearch.outerHTML;
	}
	if (parsedJSON.length == 0) {
		if (page == 0)
			document.getElementById('searchResult').innerHTML += 'No search result!';
		_done = true;
	}
}

function loadMoreSearch(){
    var myWidth = 0,
        myHeight = 0;
	if (typeof(window.innerWidth) == 'number') {
        //Non-IE
        myWidth = window.innerWidth;
        myHeight = window.innerHeight;
    } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
        //IE 6+ in 'standards compliant mode'
        myWidth = document.documentElement.clientWidth;
        myHeight = document.documentElement.clientHeight;
    }
    var scrolledtonum = window.pageYOffset + myHeight + 2;
    var heightofbody = document.body.offsetHeight;
    if (scrolledtonum >= heightofbody) {
		if (!_done) {
			getSearchResult(_q,_mode,_page);
			console.log('more loaded');
		} else {
			console.log('cant load again');
		}
    }

}