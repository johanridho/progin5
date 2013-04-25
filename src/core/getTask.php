<?php
	require_once 'config.php';
	function getTask($user_id,$category_id) {
		if ($category_id) {
			$hasil = queryAll('select * from task WHERE (user_id = :user_id or task_id in (select task_id from assign where user_id = :user_id))and category_id = :category_id',array('user_id' => $user_id,'category_id' => $category_id));
		} else
			$hasil = queryAll('select * from task WHERE (user_id = :user_id or task_id in (select task_id from assign where user_id = :user_id))',array('user_id' => $user_id));
		foreach($hasil as &$row) {
			$row['tags'] = queryAll('select name from tags natural join tag where task_id = :task_id',array('task_id' => $row['task_id']));
		}
		echo json_encode($hasil);
	}
?>