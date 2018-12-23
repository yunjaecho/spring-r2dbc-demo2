package com.yunjae.springr2dbcdemo2.repository;

import com.yunjae.springr2dbcdemo2.entity.Reservation;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 아래의 주석을 풀면 구 방식으로 처리
//@Repository
public class ReservationRepository_old {
    private final ConnectionFactory connectionFactory;

    public ReservationRepository_old(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Mono<Void> deleteById(Integer id) {
        return this.connection()
                .flatMapMany(c -> c.createStatement("delete from reservation where id = $1")
                    .bind("$1", id)
                    .execute()

                ).then();
    }

    public Flux<Reservation> findAll() {
        return this.connection()
                .flatMapMany(connection ->
                        Flux
                                .from(connection.createStatement("select * from reservation").execute())
                                .flatMap(r -> r.map((row, rowMetadata) -> new Reservation(row.get("id", Integer.class),
                                    row.get("name", String.class))))
                );
    }

    public Flux<Reservation> save(Reservation r)
    {
       Flux<? extends Result> flatMapMany =  this.connection()
                .flatMapMany(conn ->
                        conn.createStatement("insert into reservation (name) values ($1)")
                        .bind("$1", r.getName())
                        .add()
                        .execute()
                );
        return flatMapMany.switchMap(x -> Flux.just(new Reservation(r.getId(), r.getName())));
    }

    private Mono<Connection> connection() {
        return Mono.from(this.connectionFactory.create());
    }

}
