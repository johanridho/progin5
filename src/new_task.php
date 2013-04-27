<?php
	require_once 'core/config.php';
	$post = $_POST;
	$task_id = 0;
	$name = $post['name'];
	$deadline = $post['deadline'];
	$done = 0;
	$category_id = $post['category_id'];
	$assignee = $post['assignee'];
	$user_id = getUserId();
	$task_id = querynid('insert into task (user_id,category_id,name,deadline,done) values (:user_id,:category_id,:name,:deadline,:done)',array(
		'name' => $name,
		'deadline' => $deadline,
		'category_id' => $category_id,
		'user_id' => $user_id,
		'done' => $done
	));
	$tags = explode(",",$post['tag']);
	foreach ($tags as $tag) {
		addTag($task_id,$tag);
	}
	foreach($assignee as $assign) {
		addAssignee($assign,$task_id);
	}
	foreach ($_FILES['attachment']['name'] as $_key => $_value) {
		$extension = explode("/",$_FILES['attachment']['type'][$_key]);
		$type = $extension[0];
		$ext = pathinfo($_FILES['attachment']['name'][$_key], PATHINFO_EXTENSION);
		if ($type != "image" && $type != "video")
			$type = "file";
		$attachment_id = querynid('insert into attachment (task_id,type) values (:task_id,:type)',array(
			'task_id' => $task_id,
			'type' => $type
		));
		$filename = $attachment_id . '.'. $ext;
		queryn('update attachment set filename = :filename where attachment_id = :attachment_id',array(
			'attachment_id' => $attachment_id,
			'filename' => $filename
		));
		move_uploaded_file($_FILES["attachment"]["tmp_name"][$_key],"attachment/" . $filename);
	}
	header('Location: view_tugas.php?task_id='.$task_id);
?>
