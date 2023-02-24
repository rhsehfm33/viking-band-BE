package com.sparta.vikingband.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name = "Study")
@NoArgsConstructor
public class Study extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false, length = 30)
    private String subject;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private int minPersonnel;

    @Column(nullable = false)
    private int maxPersonnel;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "study")
    List<StudyBoardComment> studyBoardCommentList;

    @OneToMany(mappedBy = "study")
    List<StudyBoard> studyBoardList;

    @OneToMany(mappedBy = "study")
    List<StudyRegister> studyRegisterList;

    @OneToMany(mappedBy = "study")
    List<StudyWish> studyWishList;
}