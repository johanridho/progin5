<?php
	require_once 'config.php';
	function newTask($user_id,$name,$deadline,$category_id,$assignee,$tag) {
		$done = 0;
		$task_id = querynid('insert into task (user_id,category_id,name,deadline,done) values (:user_id,:category_id,:name,:deadline,:done)',array(
			'name' => $name,
			'deadline' => $deadline,
			'category_id' => $category_id,
			'user_id' => $user_id,
			'done' => $done
		));
		$tags = explode(",",$tag);
		foreach ($tags as $tag_split) {
			addTag($task_id,$tag_split);
			echo $tag_split;
		}
		foreach($assignee as $assign) {
			addAssignee($assign,$task_id);
		}
		return $task_id;
	}
?>
