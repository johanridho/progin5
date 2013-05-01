<?php
	require_once 'core/config.php';
	$post = $_POST;
	$name = $post['name'];
	$user_id = $_SESSION['user_id'];
	$assignee = $post['assignee'];
	$client = new SoapClient(null, array(
	  'location' => Db::$soap_loc,
      'uri'      => "urn://www.herong.home/req",
	  'trace'    => 1 ));
	$category_id = $client->__soapCall("newCategory",array($user_id,$name,$assignee));
	var_dump($category_id);
	header('Location: dashboard.php');
?>