INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'70c4e922-5260-4ace-a62f-edaa213e5214',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	3,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'fcfc15e0-00e0-4076-83c2-1e03aed888c8',
	ARRAY['https://unsplash.com/photos/fIq0tET6llw',
	'https://unsplash.com/photos/7Z03R1wOdmI']
) ON
conflict do nothing;

INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'5a02dd20-f476-4029-b4b6-1359e3b9b5ad',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	5,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	ARRAY['https://unsplash.com/photos/ukzHlkoz1IE']
) ON
conflict do nothing;

INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'd4508557-5806-4f7f-a04b-9b9140ed5315',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	1,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'9dc54dd9-85f1-4739-8eb9-3105ef1e249a',
	ARRAY['']
) ON
conflict do nothing;

INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'ff93b109-271b-4909-8df3-2446a4100132',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	3,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'cb47ccd1-844f-48f1-a5c0-6d6f7b72810a',
	ARRAY['https://unsplash.com/photos/sf_1ZDA1YFw']
) ON
conflict do nothing;

INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'0dbc167e-f9c4-4ce2-9706-a5006c696e4a',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	4,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'acbdc618-b0ba-47da-bbb6-08aa3972f69d',
	ARRAY['https://unsplash.com/photos/5E5N49RWtbA',
	'https://unsplash.com/photos/nmmU9e9w5s0',
	'https://unsplash.com/photos/mk7D-4UCfmg']
) ON
conflict do nothing;

INSERT
	INTO
	reviews(
		id,
		comment,
		user_rating,
		shop_id,
		user_id,
		review_pictures
	)
VALUES (
	'35e19fc4-f57d-4909-a457-a639c7c55d56',
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet',
	2,
	'36165f81-a402-40e0-a9e4-f08f1f095739',
	'3d62e670-7aff-4334-955c-9437d1a062bc',
	ARRAY['']
) ON
conflict do nothing;
