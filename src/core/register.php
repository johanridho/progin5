<?php
	require_once 'config.php';
	function register($username,$password,$name,$birthdate,$email) {
		$user_id = querynid('INSERT into user (username,password,name,birthdate,email) values (:username,:password,:name,:birthdate,:email)',array(
			'username' => $username,
			'password' => $password,
			'name' => $name,
			'birthdate' => $birthdate,
			'email' => $email
			));
		return $user_id;
	}
?>