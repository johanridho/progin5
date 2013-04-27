<?php
	require_once 'config.php';
	function addAttachment($task_id,$type,$ext) {
		$attachment_id = querynid('insert into attachment (task_id,type) values (:task_id,:type)',array(
			'task_id' => $task_id,
			'type' => $type
		));
		$filename = $attachment_id . '.'. $ext;
		queryn('update attachment set filename = :filename where attachment_id = :attachment_id',array(
			'attachment_id' => $attachment_id,
			'filename' => $filename
		));
		return $filename;
	}
?>