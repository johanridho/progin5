<?php
	require_once 'config.php';
	function countComment($task_id) {
		$hasil = query('select count(*) from comment WHERE task_id = :task_id',array('task_id' => $task_id));
		echo $hasil['count(*)'];
	}
?>