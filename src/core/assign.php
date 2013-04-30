<?php
	require_once 'config.php';
	function assign($task_id,$type,$category_id,$name) {
		switch($type) {
		case 'delete':
			delAssignee($name,$task_id,$category_id);
			break;
		case 'add':
			addAssignee($name,$task_id,$category_id);
			break;
		}
	}
?>