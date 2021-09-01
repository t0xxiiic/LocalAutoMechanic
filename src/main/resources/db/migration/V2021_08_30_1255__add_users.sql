INSERT
	INTO
	users(
		id,
		email,
		first_name,
		last_name,
		username
	)
VALUES (
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'kris@gmail.com',
	'kris',
	'stoitsev',
	'kstoitsev'
) ON CONFLICT DO NOTHING;

INSERT
	INTO
	users(
		id,
		email,
		first_name,
		last_name,
		username
	)
VALUES (
	'fcfc15e0-00e0-4076-83c2-1e03aed888c8',
	'niki@gmail.com',
	'niki',
	'belchev',
	'nbel'
) ON CONFLICT DO NOTHING;