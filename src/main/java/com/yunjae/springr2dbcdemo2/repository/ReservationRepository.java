package com.yunjae.springr2dbcdemo2.repository;

import com.yunjae.springr2dbcdemo2.entity.Reservation;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {
    @Query("select * from reservation where name = $1")
    public Flux<Reservation> findByName(String name);
}
