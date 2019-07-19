CREATE TABLE Flash_Card_User (
    user_id NUMBER PRIMARY KEY,
    user_username VARCHAR2(20) UNIQUE NOT NULL,
    user_password VARCHAR2(30) NOT NULL
);
/
CREATE TABLE Flash_Card_Deck (
    deck_id NUMBER PRIMARY KEY,
    deck_name VARCHAR2(50),
    deck_description VARCHAR2(200),
    deck_created_date DATE,
    deck_owner NUMBER CONSTRAINT deck_user_fk REFERENCES Flash_Card_User(user_id)
);
/
CREATE TABLE Flash_Card (
    flash_card_id NUMBER PRIMARY KEY,
    deck_id NUMBER CONSTRAINT flash_card_deck_deck_fk REFERENCES Flash_Card_Deck(deck_id),
    flash_card_term VARCHAR2(1000),
    flash_card_description VARCHAR2(1000),
    flash_card_mastered NUMBER(1) DEFAULT 0 CHECK(flash_card_mastered IN (1,0))
);
/
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE flash_card_deck_id_seq START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE flash_card_id_seq START WITH 1 INCREMENT BY 1 CACHE 10;
/
CREATE OR REPLACE TRIGGER user_id_trigger 
BEFORE INSERT ON Flash_Card_User
FOR EACH ROW
BEGIN
    SELECT user_id_seq.nextVal INTO :new.user_id FROM Dual;
END;
/
CREATE OR REPLACE TRIGGER flash_card_deck_id_trigger 
BEFORE INSERT ON Flash_Card_Deck
FOR EACH ROW
BEGIN
    SELECT flash_card_deck_id_seq.nextVal INTO :new.deck_id FROM Dual;
END;
/
CREATE OR REPLACE TRIGGER flash_card_id_trigger 
BEFORE INSERT ON Flash_Card
FOR EACH ROW
BEGIN
    SELECT flash_card_id_seq.nextVal INTO :new.flash_card_id FROM Dual;
END;
/