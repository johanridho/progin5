<?php
	require_once 'config.php';
	function postComment($user_id,$task_id,$content) {
		$time = time();
		$hasil = queryn('INSERT into comment (task_id,user_id,content,time) values (:task_id,:user_id,:content,:time)',array(
			'task_id' => $task_id,
			'user_id' => $user_id,
			'content' => $content,
			'time' => $time
			));
	}
?>