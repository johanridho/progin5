<?php
	require_once 'core/config.php';
	$_SESSION['user_id'] = $_GET['user_id'];
	header('Location: dashboard.php');
?>