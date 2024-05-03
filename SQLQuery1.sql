


CREATE TABLE Readers (
    id INT PRIMARY KEY,
    name VARCHAR(500)
);

CREATE TABLE Blogs (
    id INT PRIMARY KEY,
    title VARCHAR(50),
	description varchar(4000)
);


CREATE TABLE blogs_readers (
    R_ID INT,
	B_ID INT,
    FOREIGN KEY (R_ID) REFERENCES Readers(id),
	FOREIGN KEY (B_ID) REFERENCES Blogs(id)
);






