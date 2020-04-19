CREATE TABLE EMPLOYEE_AUTH (
  USERNAME varchar(50) NOT NULL,
  PASSWORD varchar(65) NOT NULL,
  ROLE varchar(15) NOT NULL);
  

INSERT INTO EMPLOYEE_AUTH VALUES ('user','$2a$10$5e3dB36HeRcozRgp8xQfw.tfD3Qsut8xu/NT9g/DSpVKg9Kzuitrq','USER');
-- Encrypted Password: password
INSERT INTO EMPLOYEE_AUTH VALUES ('admin','$2y$12$Sm/JNB3fMQnN17LvuS29buhCIEGnand8MIIktCEnMhStZ1T1XLTii','ADMIN');
-- Encrypted Password: admin123