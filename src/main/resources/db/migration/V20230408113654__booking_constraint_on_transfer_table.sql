ALTER TABLE transfer ADD CONSTRAINT transfer_fk FOREIGN KEY (id) REFERENCES booking(id);
