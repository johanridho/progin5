<?php
require_once 'config.php';
require_once 'auth.php';
require_once 'countComment.php';
require_once 'deleteComment.php';
require_once 'deleteTask.php';
require_once 'getCategory.php';
require_once 'getComment.php';
require_once 'getTask.php';
require_once 'negateTask.php';
require_once 'search.php';
//require_once 'updateProfile.php';
//require_once 'updateTask.php';
//require_once 'assign.php';
$method = $_SERVER['REQUEST_METHOD'];
$request = explode("/", substr($_SERVER['PATH_INFO'], 1));
switch ($method) {
  case 'PUT':
	switch ($request[0]) {
		case 'updateUsername':
			updateUsername($request[1],$request[2]);
			break;
		case 'negateTask':
			negateTask($request[1]);
			break;
		default:
			echo json_encode(array("error" => "Unknown method!"));
	}
    break;
  case 'GET':
	switch ($request[0]) {
		case 'auth':
			auth($request[1],$request[2]);
			break;
		case 'countComment':
			countComment($request[1]);
			break;
		case 'getCategory':
			getCategory($request[1]);
			break;
		case 'getComment':
			getComment($request[1],$request[2]);
			break;
		case 'getTask':
			getTask($request[1],$request[2]);
			break;
		case 'search':
			search($request[1],$request[2],$request[3],$request[4],$request[5]);
			break;
		default:
			echo json_encode(array("error" => "Unknown method!"));
	}
    break;
  case 'DELETE':
	switch ($request[0]) {
		case 'deleteComment':
			deleteComment($request[1]);
			break;
		case 'deleteTask':
			deleteTask($request[1]);
			break;
		default:
			echo json_encode(array("error" => "Unknown method!"));
	}
    break;
}
?>