<?php
	require_once 'config.php';
	function getProfile($user_id) {
		$hasil = query('select * from user WHERE user_id = :user_id',array('user_id' => $user_id));
		echo json_encode($hasil);
	}
?>