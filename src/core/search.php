<?php
	require_once 'config.php';
	function search($q,$mode,$autoComplete,$equals,$offset) {
		if ($autoComplete || $equals) {
			switch ($mode) {
				case 0:
					$querystring = 'select name q from task WHERE name like :q union select name q from tag where name like :q union select name q from category WHERE name like :q union select username q from user WHERE username like :q union select email q from user where email like :q union select name q from user where name like :q union select birthdate q from user where birthdate like :q';
					break;
				case 1:
					$querystring = 'select username q from user WHERE username like :q union select email q from user where email like :q union select name q from user where name like :q union select birthdate q from user where birthdate like :q';
					break;
				case 2:
					$querystring = 'select name q from category WHERE name like :q';
					break;
				case 3:
					$querystring = 'select name q from task WHERE name like :q union select name q from tag where name like :q';
					break;
				case 4:
					$querystring = 'select content q from comment WHERE content like :q';
					break;
				case 5:
					$querystring = 'select name q from user where name like :q';
					break;
				case 6:
					$querystring = 'select name q from tag where name like :q';
					break;
				case 7:
					$querystring = 'select username q from user where username = :q';
					break;
				case 8:
					$querystring = 'select email q from user where email = :q';
					break;
			}
			$hasil = ($equals)?query($querystring,array('q' => $q)):queryAll($querystring.' order by q limit 3',array('q' => $q."%"));
			echo json_encode($hasil);
		} else {
		
			switch ($mode) {
				case 0:
					$querystring = 'select distinct user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user WHERE username like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where email like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where name like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where birthdate like :q union
					select user_id, null as username, name, null as task_id, null as deadline, null as done, category_id, \'category\' as type from category WHERE name like :q union
					select user_id, null as username, name, task_id, deadline, done, null category_id, \'task\' as type from task WHERE name like :q union
					select task.user_id, null as username, task.name as name, task.task_id, deadline, done, null category_id, \'task\' as type from ((tag natural join tags) inner join task on(tags.task_id = task.task_id)) where tag.name like :q';
					break;
				case 1:
					$querystring = 'select distinct user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user WHERE username like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where email like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where name like :q union
					select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, \'user\' as type from user where birthdate like :q';
					break;
				case 2:
					$querystring = 'select distinct user_id, null as username, name, null as task_id, null as deadline, null as done, category_id, \'category\' as type from category WHERE name like :q';
					break;
				case 3:
					$querystring = 'select distinct user_id, null as username, name, task_id, deadline, done, null category_id, \'task\' as type from task WHERE name like :q union
					select task.user_id, null as username, task.name as name, task.task_id, deadline, done, null category_id, \'task\' as type from ((tag natural join tags) inner join task on(tags.task_id = task.task_id)) where tag.name like :q';
					break;
				case 4:
					$querystring = 'select distinct user_id, null as username, name, task_id, deadline, done, null category_id, \'task\' as type from (comment natural join task) WHERE content like :q';
					break;
			}
			$hasil = queryAll($querystring.' order by type desc, name asc limit '.$offset.', 10',array('q' => "%".$q."%"));
			foreach($hasil as &$row) {
				if ($row['type'] == 'task')
					$row['tags'] = queryAll('select name from tags natural join tag where task_id = :task_id',array('task_id' => $row['task_id']));
			}
			echo json_encode($hasil);
		}
	}
?>