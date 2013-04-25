<?php
	require_once 'config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	$user_id = $_SESSION['user_id'];
	$content = $post['content'];
	$time = time();
	$hasil = queryn('INSERT into comment (task_id,user_id,content,time) values (:task_id,:user_id,:content,:time)',array(
		'task_id' => $task_id,
		'user_id' => $user_id,
		'content' => $content,
		'time' => $time
		));
?>