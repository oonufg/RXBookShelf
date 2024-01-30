CREATE DATABASE bookshelf_service;

\c bookshelf_service;


CREATE TABLE mediafiles(
    id BIGSERIAL PRIMARY KEY,
	uid VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	extension VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL
);

CREATE TABLE refresh_tokens(
	user_id BIGINT REFERENCES users(id),
	token VARCHAR NOT NULL,
	expiration TIMESTAMP NOT NULL
)

CREATE TABLE IF NOT EXISTS bookshelves(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    owner_id BIGINT REFERENCES users(id),
    UNIQUE(title, owner_id)
);

CREATE TABLE bookshelves_subscribes(
    user_id BIGINT REFERENCES users(id),
    bookshelf_id BIGINT REFERENCES bookshelves(id),
    UNIQUE(user_id, bookshelf_id)
);

CREATE TABLE IF NOT EXISTS shelves(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    bookshelf_id BIGINT REFERENCES bookshelves(id) ON DELETE CASCADE,
    UNIQUE(id, title)
);

CREATE TABLE IF NOT EXISTS books(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
	shelf_id BIGINT REFERENCES shelves(id) ON DELETE CASCADE,
    payload_id BIGINT REFERENCES mediafiles(id)
);