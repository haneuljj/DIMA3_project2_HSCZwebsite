-- 5년치 통계 테이블
DROP TABLE year_imex5;
DROP SEQUENCE year_seq;

CREATE TABLE year_imex5
(
    year_seq NUMBER PRIMARY KEY
    , hs_4digit VARCHAR2(10)
    , imex_year VARCHAR2(10)
    , export_amount NUMBER
    , import_amount NUMBER
);

CREATE SEQUENCE year_seq;
