<?php
	require_once 'core/config.php';
	$post = $_POST;
	var_dump($post);
	$task_id = $post['task_id'];
	$user_id = $_SESSION['user_id'];
	$content = $post['content'];
	$client = new SoapClient(null, array(
	  'location' => Db::$soap_loc,
      'uri'      => "urn://www.herong.home/req",
	  'trace'    => 1 ));

	$return = $client->__soapCall("postComment",array($user_id,$task_id,$content));
?>