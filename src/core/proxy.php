<?php
	// File Name: proxy.php
	if (!isset($_GET['url'])) die();
	$url = urldecode($_GET['url']);
	$url = 'http://' . str_replace(' ','%20',str_replace('http://', '', $url)); // Avoid accessing the file system
	$method = $_SERVER['REQUEST_METHOD'];
	$context = stream_context_create(array(
		'http' => array(
			'method' => $method
		)
	));
	echo file_get_contents($url,false,$context);
?>