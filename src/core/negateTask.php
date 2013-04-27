<?php
	require_once 'config.php';
	function negateTask($hasil) {
		$hasil = queryn('UPDATE task set done = not done WHERE task_id = :task_id',array('task_id' => $task_id));
	}
?>