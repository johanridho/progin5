<?php
	require_once 'config.php';
	function getTag($tag_id) {
		$hasil = query('select * from tag WHERE tag_id = :tag_id',array('tag_id' => $tag_id));
		echo json_encode($hasil);
	}
	function getTags($task_id) {
		$hasil = queryAll('select * from tags where task_id = :task_id',array('task_id' => $task_id));
		echo json_encode($hasil);
	}
?>