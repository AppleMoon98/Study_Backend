package com.mallppang.bakery;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bakery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 등록 번호
    private Long id;

    // 가게이름
    private String name;
    
    // 주소
    private String loadAddress;
    private String townAddress;
    
    // 오픈, 클로즈 타임
    private Date openDate;
    private Date closeDate;
    
    // 주차여부
    private boolean parking;
    
    // 빵 목록
    @OneToMany(mappedBy = "bakery", cascade = CascadeType.REMOVE)
    private List<BakeryProduct> productList;
}

