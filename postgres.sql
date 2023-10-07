CREATE DATABASE bookshelf_service;

\c bookshelf_service;

CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookshelf(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL
);

CREATE TABLE users_bookshelves(
    user_id BIGINT REFERENCES users(id),
    bookshelf_id BIGINT REFERENCES users(id)
    isOwner BOOL NOT NULL,
)

CREATE TABLE IF NOT EXISTS shelves(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    bookshelf_id BIGINT REFERENCES bookshelf(id)
)

CREATE TABLE IF NOT EXISTS books(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE mediafiles(
	uid VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	extension VARCHAR(20) NOT NULL,
	book_id BIGINT REFERENCES books(id),
	CONSTRAINT books_mediafiles UNIQUE(uid, book_id)
)
