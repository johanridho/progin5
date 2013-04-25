<?php
	require_once 'config.php';
	function deleteTask($task_id) {
		$hasil = queryn('DELETE from task where task_id = :task_id',array(
			'task_id' => $task_id
			));
	}
?>