<?php
	require_once 'config.php';
	function negateTask($task_id,$time) {
		$hasil = queryn('UPDATE task set done = not done,last_update = :last_update WHERE task_id = :task_id and last_update < :last_update',array('task_id' => $task_id,'last_update' => $time));
		$ret['success'] = true;
		echo json_encode($ret);
	}
?>