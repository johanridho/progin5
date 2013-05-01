<?php
	require_once 'core/config.php';
	$post = $_POST;
	$task_id = 0;
	$name = $post['name'];
	$deadline = $post['deadline'];
	$done = 0;
	$category_id = $post['category_id'];
	$assignee = $post['assignee'];
	$user_id = getUserId();
	$tag = $post['tag'];
	$client = new SoapClient(null, array(
	  'location' => Db::$soap_loc,
      'uri'      => "urn://www.herong.home/req",
	  'trace'    => 1 ));
	$task_id = $client->__soapCall("newTask",array($user_id,$name,$deadline,$category_id,$assignee,$tag));
	foreach ($_FILES['attachment']['name'] as $_key => $_value) {
		$extension = explode("/",$_FILES['attachment']['type'][$_key]);
		$type = $extension[0];
		$ext = pathinfo($_FILES['attachment']['name'][$_key], PATHINFO_EXTENSION);
		if ($type != "image" && $type != "video")
			$type = "file";
		$filename = $client->__soapCall("addAttachment",array($task_id,$type,$ext));
		move_uploaded_file($_FILES["attachment"]["tmp_name"][$_key],"attachment/" . $filename);
	}
	header('Location: view_tugas.php?task_id='.$task_id);
?>
