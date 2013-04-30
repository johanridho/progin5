<?php
	$title = 'Profile';
	$login_permission = 1;
	include 'inc/header.php';
?>

		<script>
			window.onload=function(){localStorage.user_id = <?php echo $_GET['user_id']; ?>; refreshTask1(localStorage.user_id,0);};
		</script>
		
		<div class="content">
			<div class="profile">
				<header>
					<h1><?php echo getUserName($_GET['user_id']); ?></h1>
					<?php if ($_GET['user_id'] == getUserId()): ?>
						<ul>
							<li><a href="#" id="editProfileLink">Edit Profile</a></li>
						</ul>
					<?php endif; ?>
				</header>
				
				<div id="current-profile">
				
				<section class="profile-details">
					<figure class="profile-image">
						<img src="avatar/<?php echo $_GET['user_id']; ?>.jpg" alt="Profile Photo">
					</figure>
					<p class="description">
						<span class="detail-label">About Me:</span>
						<span class="detail-value">Lorem Ipsum Dolor Sit Amet</span>
					</p>
					<p class="username">
						<span class="detail-label">Username:</span>
						<span class="detail-value"><?php echo getUserUsername($_GET['user_id']); ?></span>
					</p>
					
					<p class="name">
						<span class="detail-label">Full Name:</span>
						<span class="detail-value"><?php echo getUserFullname($_GET['user_id']); ?></span>
					</p>
					
					<p class="email">
						<span class="detail-label">Email:</span>
						<span class="detail-value"><?php echo getUserEmail($_GET['user_id']); ?></span>
					</p>

					<p class="date-of-birth">
						<span class="detail-label">Date of Birth:</span>
						<span class="detail-value"><?php echo getUserBirthdate($_GET['user_id']); ?></span>
					</p>
				</section>
				</div>
				
				<div id="edit-profile" class="editingprofile">
					<form id="new_profile" action="#" method="post">
						<div class="field">
							<label>Full Name</label>
							<input size="30" maxlength="50" name="name" id="name" type="text">
							<div class="buttons">
								<button id="namebutton" onclick="updateProfile('name',<?php echo $_GET['user_id']; ?>);">Save Name</button>
							</div><br>&nbsp;
						</div>
						<div class="field">
							<label>New Avatar</label>
							<input name="avatar" id="avatar" type="file" accept="image/jpeg">
							<div class="buttons">
								<button id="avatarbutton" onclick="updateProfile('avatar',<?php echo $_GET['user_id']; ?>);">Save Avatar</button>
							</div><br>&nbsp;
						</div>
						<div class="field">
							<label>Tanggal Lahir Baru</label>
							<input size = "30" name="tanggal_lahir" id="tanggal_lahir" type="date">
							<div class="buttons">
								<button onclick="updateProfile('tanggal_lahir',<?php echo $_GET['user_id']; ?>);">Save Tanggal lahir</button>
							</div><br>&nbsp;
							</div>
						<div class="field">
								<label>New Password</label>
								<input size="30" maxlength="50" name="password" id="password" type="password">
						</div>
						<div class="field">
							<label>Confirm New Password</label>
							<input size="30" maxlength="50" name="password_k" id="password_k" type="password">
						</div>
						<div class="buttons">
							<button type="submit" name="ganti_pass" id="submitPass" onclick="updateProfile('password',<?php echo $_GET['user_id']; ?>);">Save Password</button>
						</div>
					<?php if ($_GET['user_id'] == getUserId()) :?>
							<br>
						</div>
					</form>
					<?php endif;?>
				</div>

				<div class="primary2">
					<section class="tasks current" id="activeTask1">
					
					</section>

					<section class="tasks completed" id="doneTask1">

					</section>
				</div>
			
				
			
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
