<?php
	require_once 'config.php';
	function getTask($user_id,$category_id) {
		if ($category_id) {
			$hasil = queryAll('select * from task WHERE (user_id = :user_id or task_id in (select task_id from assign where user_id = :user_id))and category_id = :category_id',array('user_id' => $user_id,'category_id' => $category_id));
		} else
			$hasil = queryAll('select * from task WHERE (user_id = :user_id or task_id in (select task_id from assign where user_id = :user_id))',array('user_id' => $user_id));
		foreach($hasil as &$row) {
			$row['tags'] = queryAll('select name from tags natural join tag where task_id = :task_id',array('task_id' => $row['task_id']));
			$cat = query('select name from category where category_id = :category_id',array('category_id' => $row['category_id']));
			$row['category'] = $cat['name'];
			$row['assignee'] = queryAll('select name from user where user_id in (select user_id from assign where task_id = :task_id)',array('task_id' => $row['task_id']));
			$row['attachment'] = queryAll('select * from attachment where task_id = :task_id',array('task_id' => $row['task_id']));
		}
		echo json_encode($hasil);
	}
	function getSingleTask($task_id) {
		$hasil = query('select * from task WHERE task_id = :task_id',array('task_id' => $task_id));
		echo json_encode($hasil);
	}
	function getPermission($user_id,$task_id) {
		$hasil = query("select user_id from task where task_id = :task_id and user_id = :user_id union select user_id from assign where task_id = :task_id and user_id = :user_id",array('user_id' => $user_id, 'task_id' => $task_id));
		echo json_encode($hasil);
	}
	function getAssignees($task_id) {
		$hasil = queryAll('select * from assign where task_id = :task_id',array('task_id' => $task_id));
		echo json_encode($hasil);
	}
	function getAttachments($task_id) {
		$hasil = queryAll('select * from attachment where task_id = :task_id',array('task_id' => $task_id));
		echo json_encode($hasil);
	}
?>