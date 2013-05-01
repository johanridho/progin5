<?php
	require_once 'core/config.php';
	$post = $_POST;
	var_dump($post);
	var_dump($_FILES["avatar"]);
	$username = $post['username'];
	$password = $post['password'];
	$name = $post['nama_lengkap'];
	$birthdate = $post['tanggal_lahir'];
	$email = $post['email'];
	$client = new SoapClient(null, array(
	  'location' => Db::$soap_loc,
      'uri'      => "urn://www.herong.home/req",
	  'trace'    => 1 ));
	$user_id = $client->__soapCall("register",array($username,$password,$name,$birthdate,$email));
	echo '<br />\n';
	var_dump($user_id);
	move_uploaded_file($_FILES["avatar"]["tmp_name"],"avatar/" . $user_id . '.jpg');
	$_SESSION['user_id'] = (int)$user_id;
	header('Location: dashboard.php');
?>