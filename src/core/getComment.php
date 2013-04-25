<?php
	require_once 'config.php';
	function getComment($task_id, $offset) {
		$hasil = queryAll('select * from comment WHERE task_id = :task_id order by time asc limit '.$offset.', 10',array('task_id' => $task_id));
		foreach ($hasil as &$row) {
			$row['user_name'] = getUserName($row['user_id']);
		}
		echo json_encode($hasil);
	}
?>