<?php
	$title = 'View Tugas';
	$login_permission = 1;
	include 'inc/header.php';
	if (!isset($_GET['task_id']))
		header('Location: dashboard.php');
	$tugas = api("getSingleTask/".$_GET['task_id']);
	if (!$tugas)
		header('Location: dashboard.php');
?>
		<script>
			window.onload=function(){_task_id = <?php echo $tugas['task_id']; ?>; localStorage.user_id = <?php echo getUserID(); ?>; refreshComment(_task_id,1);};
		</script>
		<div class="content">
			<div class="task-details not-editing">
				<header>
					<form method="POST">
						<h1>
							<label>
								<span class="task-checkbox"><input id="taskcheck" type="checkbox" class="task-checkbox" onclick="negateTask(<?php echo $tugas['task_id']; ?>)" <?php if ($tugas['done']) echo 'checked ';?>></span>
								<span class="task-title"><a href="view_tugas.php?task_id=<?php echo $tugas['task_id']; ?>"><?php echo $tugas['name']; ?></a></span>
							</label>
						</h1>
					</form>
					<?php 
					$isPermit = api("getPermission/".getUserId()."/".$_GET['task_id']);
					if($isPermit['user_id']) :?>
					<ul>
						<li><a href="#" id="editTaskLink">Edit Task</a></li>
					</ul>
					<?php endif; ?>
				</header>
				<div id="current-task">
					<section class="details">
						<header>
							<h3>Details</h3>
						</header>
						<p class="status">
							<span class="detail-label">Status:</span>
							<span class="detail-content"><?php echo ($tugas['done'])?'Selesai':'Belum';?></span>
						</p>
						<p class="assignee">
							<span class="detail-label">Assignee:</span>
							<?php $assignees = api("getAssignees/".$_GET['task_id']);
							if ($assignees) : ?>
								<?php foreach($assignees as $assignee):?>
									<span class="detail-content names"><a href="profile.php?user_id=<?php echo $assignee['user_id'] ?>"><?php echo getUserName($assignee['user_id']) ?></a></span>
								<?php endforeach; ?>
							<?php endif; ?>
						</p>
						<p class="deadline">
							<span class="detail-label">Deadline:</span>
							<span class="detail-content"><?php echo $tugas['deadline']; ?></span>
						</p>
						<p class="tags">
							<span class="detail-label">Tag:</span>
							<?php $tags = api("getTags/".$_GET['task_id']);
							if ($tags) : ?>
								<?php foreach($tags as $tag):?>
									<span class="tag"><?php echo getTagName($tag['tag_id']) ?></span>
								<?php endforeach; ?>
							<?php else: ?>
								No tag specified!
							<?php endif; ?>
						</p>
					</section>
					<section class="attachment">
						<header>
							<h3>Attachment</h3>
						</header>
						<?php $attachments = api("getAttachments/".$_GET['task_id']);
							if ($attachments) : ?>
								<?php foreach($attachments as $attachment):?>
								<?php if ($attachment['type'] == 'file'): ?>
									Download <a href="attachment/<?php echo $attachment['filename']; ?>"><?php echo $attachment['filename']; ?></a>
								<?php else: ?>
									<figure>
									<?php if ($attachment['type'] == 'image'): ?>
										<img src="attachment/<?php echo $attachment['filename']; ?>" alt="<?php echo $attachment['filename']; ?>">
									<?php else : ?>
										<video width="320" height="240" controls>
											<source src="attachment/<?php echo $attachment['filename']; ?>">
											Your browser does not support the video tag.
										</video>
									<?php endif; ?>
									</figure>
								<?php endif; ?>
								<?php endforeach; ?>
							<?php else: ?>
								No attachment available !
							<?php endif; ?>
					</section>
				</div>
				<div id="edit-task">
					<?php if ($tugas['user_id'] == getUserId()) :?>
					<form id="new_tugas" action="#" method="post">
						<datalist id="suggestion">
						</datalist>
						<div class="field">
							<label>Deadline</label>
							<input size="30" name="deadline" id="deadline" type="date">
							<div class="buttons">
								<button onclick="updateTask('deadline',<?php echo $_GET['task_id']; ?>);">Save Deadline</button>
							</div><br>&nbsp;
						</div>
						<div class="field">
							<label>Assignee</label>
							<input size="30" name="assignee" id="addassignee" type="text" onkeyup="assignee_autocomplete(this)" list="suggestion">
							<div class="buttons">
								<button onclick="updateTask('addassignee',<?php echo $_GET['task_id']; ?>);">Add Assignee</button>
							</div>
							<?php $assignees = api("getAssignees/".$_GET['task_id']);
							if ($assignees) : ?><br><label>click to delete</label>
								<?php foreach($assignees as $assignee):?>
									<span class="detail-content names" onclick="removeElement('delassignee',this,<?php echo $_GET['task_id']; ?>,<?php echo $assignee['id'];?>)"><?php echo getUserName($assignee['user_id']) ?></span>
								<?php endforeach; ?>
								<br>&nbsp;
							<?php endif; ?>
						</div>
						<div class="field">
							<label>Tag</label>
							<input size="30" name="tag" id="addtag" type="text" onkeyup="tag_autocomplete(this)" list="suggestion">
							<div class="buttons">
								<button onclick="updateTask('addtag',<?php echo $_GET['task_id']; ?>);">Add Tag</button>
							</div>
							<?php $tags = api("getTags/".$_GET['task_id']);
							if ($tags) : ?><br><label>click to delete</label>
								<?php foreach($tags as $tag):?>
									<span class="tag" onclick="removeElement('deltag',this,<?php echo $_GET['task_id']; ?>,<?php echo $tag['id'];?>)"><?php echo getTagName($tag['tag_id']) ?></span>
								<?php endforeach; ?>
								<br>&nbsp;
							<?php endif; ?>
						</div>
						<div class="buttons">
							<?php endif; ?>
							<button onclick="deleteTask(<?php echo $_GET['task_id']; ?>);">Delete Task</button>
					<?php if ($tugas['user_id'] == getUserId()) :?>
							<br>
						</div>
					</form>
					<?php endif;?>
				</div>
				<section class="comments">
					<header>
						<h3><span id="commentCount"></span> Comments</h3>
					</header>

					<div id="commentsList">
					</div>
					
					<div id="commentPage">
					</div>
					
					<div class="comment-form">
						<h3>Add Comment</h3>
						<form id="commentForm" action="#" method="post">
							<textarea name="komentar" id="commentBody"></textarea>
							<button type="submit">Send</button>
						</form>
					</div>
				</section>
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
