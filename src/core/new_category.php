<?php
	require_once 'config.php';
	function newCategory($user_id,$name,$assignee) {
		$category_id = querynid('INSERT into category (name,user_id) values (:name,:user_id)',array(
			'name' => $name,
			'user_id' => $user_id
			));
		foreach($assignee as $assign) {
			addAssignee($assign,null,$category_id);
		}
	}
?>