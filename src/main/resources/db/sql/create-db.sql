create schema myTestDB;

CREATE TABLE myTestDB.tbl_contacts (
  contact_id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  contact_name NVARCHAR(255) NOT NULL,
);

CREATE INDEX INDEX_tbl_contacts ON myTestDB.tbl_contacts(contact_name);