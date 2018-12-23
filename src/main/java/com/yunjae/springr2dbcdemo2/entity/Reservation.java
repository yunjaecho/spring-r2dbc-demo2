package com.yunjae.springr2dbcdemo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private Integer id;
    private String name;
}
