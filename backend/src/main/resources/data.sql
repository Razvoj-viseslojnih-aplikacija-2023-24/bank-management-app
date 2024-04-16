--Bank--

INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'ADIKKO BANK', '(381) 011/222-6000', 100228215);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'ALTA BANKA', '(381) 011/2205-500', 100001829);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'ERSTE BANKA', '(381) 021/4809-700', 101626723);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'OTP BANKA', '(381) 021/4800-001', 100584604);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'INTESA SANPAOLO BANKA', '(381) 011/2011-200', 100001159);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'MOBI BANKA', '(381) 011/4409-670', 100000049);
INSERT INTO "bank"("id", "name", "contact", "tin") VALUES(nextval('BANK_SEQ'), 'UNICREDIT BANK', '(381) 011/3204-500', 100000170);

--User--

INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Jovan', 'Curcin Miletaski', '2201002800015');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Igor', 'Gvozdenac', '1102003676305');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Veljko', 'Marinkovic', '1906002803018');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Kristina', 'Djuric', '1609001721645');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Stefan', 'Vidic-Ronaldo', '2308002890013');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Dusan', 'Malesevic', '0712999421152');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Stefan', 'Hereta', '0103000526544');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Ivan', 'Podlipec', '2803005597015');
INSERT INTO "user"("id", "name", "surname", "id_number") VALUES(nextval('USER_SEQ'), 'Djordje', 'Cvarkov', '0805958678901');

--Branch--
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Milutina Milankovića 7v, Beograd', 3, true, 1);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Milentija Popovića 7b, Beograd', 8, false, 5);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Bulevar Zorana Đinđića 121, Beograd', 4, true, 2);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Bulevar oslobođenja 5, Novi Sad', 2, true, 3);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Omladinskih brigada 88, Beograd', 3, false, 6);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Trg Slobode 5, Novi Sad', 4, false, 4);
INSERT INTO "branch"("id", "address", "counters", "has_boss", "bank") VALUES(nextval('BRANCH_SEQ'), 'Rajićeva 27-29, Beograd', 10, true, 7);

--Bank Service--

INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Izrada platne kartice', 'Izrada i personalizacija  platne kartice', '2023-12-15', 4.0, 1, 7);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Azuriranje dokumenata', 'Korisniku je istekla licna karta i potrebno je ubaciti novu u sistem.', '2024-01-14', 6.0, 7, 2);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Isplata gotovine', 'Isplata gotovine korisniku na šalteru banke uz priložen lični dokument.', '2023-12-15', 3.4, 4, 6);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Stambeni kredit', 'Pružanje kredita za kupovinu stambenog prostora', '2024-01-10', 6.2, 3, 2);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Tekući račun', 'Otvaranje i vođenje tekućeg računa', '2023-12-15', 3.5, 6, 3);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Investiciono savetovanje', 'Pružanje saveta o investicionim prilikama', '2023-09-15', 8.0, 3, 5);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Devizna štednja', 'Štednja u stranoj valuti', '2023-12-15', 7.5, 2, 4);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Mobilno bankarstvo', 'Pristup bankovnim uslugama putem mobilne aplikacije', '2024-01-05', 0.0, 1, 4);
INSERT INTO "bank_service"("id", "name", "service_description", "contract_date", "commission", "user", "branch") VALUES(nextval('BANK_SERVICE_SEQ'), 'Isplata gotovine', 'Isplata gotovine korisniku na šalteru banke uz priložen njegov lični dokument.', '2023-10-11', 7.0, 8, 3);
