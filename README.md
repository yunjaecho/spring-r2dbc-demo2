# spring-r2dbc-demo2

### postgres auto increment (Identity) Column 지정

-- 테이블 생성
CREATE TABLE reservation (
    id  SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

SERIAL 자동으로 시퀀스 생성 하므로 권한이 필요함
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to orders;
