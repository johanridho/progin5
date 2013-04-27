<?php
	require_once 'config.php';
	require_once 'postComment.php';
	require_once 'new_task.php';
	require_once 'register.php';
	require_once 'new_category.php';
	require_once 'attachment.php';
	$handler = new SoapServer(null,array('uri' => "url://localhost/IF3038-2013/src/core/soap.php"));
	$handler->addFunction("postComment");
	$handler->addFunction("newTask");
	$handler->addFunction("register");
	$handler->addFunction("newCategory");
	$handler->addFunction("addAttachment");
	$handler->handle();
?>