package com.yunjae.springr2dbcdemo2;

import com.yunjae.springr2dbcdemo2.entity.Reservation;
import com.yunjae.springr2dbcdemo2.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void all() {
        Flux<Void> deletAll = this.reservationRepository
                .findAll()
                .flatMap(r -> this.reservationRepository.deleteById(r.getId()));
        StepVerifier
                .create(deletAll)
                .expectNextCount(0)
                .verifyComplete();


        Flux<Reservation> reservationFlux = Flux
                .just("first", "second", "third")
                .map(name -> new Reservation(null, name))
                .flatMap(r -> this.reservationRepository.save(r));
        StepVerifier
                .create(reservationFlux)
                .expectNextCount(3)
                .verifyComplete();

        Flux<Reservation> all = this.reservationRepository.findAll();
        StepVerifier
                .create(all)
                .expectNextCount(3)
                .verifyComplete();

        Flux<Reservation> first = this.reservationRepository.findByName("first");
        StepVerifier
                .create(first)
                .expectNextCount(1)
                .verifyComplete();
    }
}
