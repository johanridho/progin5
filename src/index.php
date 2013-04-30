<?php
	$title = 'Homepage';
	$login_permission = 0;
	include 'inc/header.php';
?>
		<script>
			_username = false;
			_password = false;
			_password2 = false;
			_name = false;
			_email = false;
			localStorage.removeItem('user_id');
		</script>
		<div class="content">
			<div class="index">	
				<header>
					<h1>Welcome to DO</h1>
					<ul>
						<li class="login"><a href="#" id="loginLink">Login</a></li>
					</ul>
				</header>
				
				<section class="login-box" id="loginBox">
					<header>
						<h3>Login</h3>
					</header>
					<form id="loginForm" action="#" method="post" class="vertical">
						<div class="field">
							<label>Username</label>
							<input size="30" maxlength="50" name="username" id="login_username" type="text">
						</div>
						<div class="field">
							<label>Password</label>
							<input size="30" maxlength="50" name="password" id="login_password" type="password">
						</div>
						<div class="field">
							<button type="submit" id="loginButton">Login</button>
						</div>
					</form>
				</section>
				
				<div class="primary">
					<section class="welcome">
						<header>
							<h3>A to-do list for getting things done</h3>
						</header>

						<div class="intro">
							<img src="assets/intro.jpg" alt="">
							<h2>Easy Task Management</h2>
							<p>DO lets you focus on the things you're supposed to do.</p>
						</div>

						<div class="features">
							<h3>Features</h3>
							<ul>
								<li>Shared task management</li>
								<li>Assignees</li>
								<li>Tags and categories</li>
							</ul>
					</section>
				</div>
			
				<div class="secondary">
					<section class="register">
						<header>
							<h3>Sign Up Now!</h3>
						</header>

						<form id="register" action="register.php" method="post" class="vertical" enctype="multipart/form-data">
							<div class="field">
								<label>Username</label>
								<input size="30" maxlength="50" name="username" id="username" type="text">
							</div>
							<div class="field">
								<label>Password</label>
								<input size="30" maxlength="50" name="password" id="password" type="password">
							</div>
							<div class="field">
								<label>Confirm Password</label>
								<input size="30" maxlength="50" name="password_k" id="password_k" type="password">
							</div>
							<div class="field">
								<label>Nama Lengkap</label>
								<input size="30" maxlength="50" name="nama_lengkap" id="nama_lengkap" type="text">
							</div>
							<div class="field">
								<label>Tanggal Lahir</label>
								<input name="tanggal_lahir" id="tanggal_lahir" type="date">
							</div>
							<div class="field">
								<label>Email</label>
								<input size="30" maxlength="50" name="email" id="email" type="email">
							</div>
							<div class="field">
								<label>Avatar</label>
								<input name="avatar" id="avatar" type="file" accept="image/jpeg">
							</div>
							<div class="field buttons">
								<button type="submit" name="register_dong" id="submitButton" disabled>Register</button>
							</div>
						</form>
					</section>
				</div>

			</div>

		</div>
<?php
	include 'inc/footer.php';
?>
