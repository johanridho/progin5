<?php
	require_once 'config.php';
	function updateTask($task_id,$mode,$value) {
		switch ($mode) {
		case 'deadline':
			queryn('UPDATE task set deadline = :deadline WHERE task_id = :task_id',array('task_id' => $task_id, 'deadline' => $value));
			break;
		case 'addassignee':
			addAssignee($value,$task_id,null);
			break;
		case 'addtag':
			addTag($task_id,$value);
			break;
		case 'delassignee':
			queryn('delete from assign where id = :id',array('id' => $value));
			break;
		case 'deltag':
			queryn('delete from tags where id = :id',array('id' => $value));
			break;
		}
	}
?>