<?php
	require_once 'config.php';
	function deleteComment($comment_id) {
		$hasil = queryn('DELETE from comment where comment_id = :comment_id',array(
			'comment_id' => $comment_id
			));
	}
?>