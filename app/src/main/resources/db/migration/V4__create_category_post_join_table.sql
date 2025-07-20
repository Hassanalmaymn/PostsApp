create table post_category(
	post_id BIGINT ,
	category_id BIGINT,
	FOREIGN KEY (post_id) REFERENCES post(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
	
);
	
