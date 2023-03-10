package com.sparta.vikingband.controller;

import com.sparta.vikingband.dto.ApiResponse;
import com.sparta.vikingband.dto.StudyWishRequestDto;
import com.sparta.vikingband.dto.StudyWishResponseDto;
import com.sparta.vikingband.security.UserDetailsImpl;
import com.sparta.vikingband.service.StudyWishService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish")
public class StudyWishController {

    private final StudyWishService studyWishService;

//    @GetMapping("/{memberId}")
//    public ApiResponse<List<StudyWishResponseDto>> getWishes(@PathVariable Long memberId) {
//        return ApiResponse.successOf(HttpStatus.OK, studyWishService.getWishes(memberId));
//    }

    @PostMapping("/toggle/{studyId}")
    public ApiResponse<StudyWishResponseDto> toggleWish(
        @PathVariable Long studyId,
        @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ApiResponse.successOf(HttpStatus.ACCEPTED, studyWishService.toggleWish(studyId, userDetails));
    }

//    @DeleteMapping("/{studyId}")
//    public ApiResponse<StudyWishResponseDto> deleteWish(
//        @PathVariable Long studyId,
//        @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) throws AccessDeniedException {
//        studyWishService.deleteWish(studyId, userDetails);
//        return ApiResponse.successOf(HttpStatus.ACCEPTED, null);
//    }
}
