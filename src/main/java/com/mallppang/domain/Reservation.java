package com.mallppang.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mallppang.member.Member;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 예약자는 한 명 → ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member user;

    private Date date;

    // ✅ 예약된 상품은 여러 개 → ManyToMany
    @ManyToMany
    @JoinTable(
        name = "reservation_products",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<BakeryProduct> productList = new ArrayList<>();
}
