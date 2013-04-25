<?php
	require_once 'config.php';
	function getCategory($user_id) {
		$hasil = queryAll('select * from category WHERE user_id = :user_id or category_id in (select category_id from assign where user_id = :user_id union (select category_id from task where user_id = :user_id or task_id in (select task_id from assign where user_id = :user_id)))',array('user_id' => $user_id));
		echo json_encode($hasil);
	}
?>