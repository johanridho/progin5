<?php
	require_once 'config.php';
	function getComment($task_id, $offset) {
		$hasil = queryAll('select comment.*,name from (select * from comment WHERE task_id = :task_id order by time asc limit '.$offset.', 10) as comment natural join user',array('task_id' => $task_id));
		echo json_encode($hasil);
	}
?>