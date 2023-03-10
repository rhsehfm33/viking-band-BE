//package com.sparta.vikingband.service;
//
//import com.sparta.posting.dto.CommentOuterResponseDto;
//import com.sparta.posting.dto.CommentRequestDto;
//import com.sparta.posting.entity.Board;
//import com.sparta.posting.entity.Comment;
//import com.sparta.posting.entity.CommentLike;
//import com.sparta.posting.entity.User;
//import com.sparta.posting.enums.ErrorMessage;
//import com.sparta.posting.enums.UserRoleEnum;
//import com.sparta.posting.repository.*;
//import com.sparta.posting.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.nio.file.AccessDeniedException;
//
//@Service
//@RequiredArgsConstructor
//public class CommentService {
//    private final BoardRepository boardRepository;
//    private final UserRepository userRepository;
//    private final CommentRepository commentRepository;
//    private final CommentLikeRepository commentLikeRepository;
//    private final ReplyRepository replyRepository;
//
//    @Transactional
//    public CommentOuterResponseDto createComment(
//            CommentRequestDto commentRequestDto,
//            UserDetailsImpl userDetailsImpl
//    ) {
//        // 인증된 사용자 이름으로 사용자 정보를 DB에 조회
//        User user = userRepository.findByUsername(userDetailsImpl.getUsername()).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
//        );
//
//        Board board = boardRepository.findById(commentRequestDto.getBoardId()).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.BOARD_NOT_FOUND.getMessage())
//        );
//
//        Comment newComment = new Comment(commentRequestDto, user, board);
//        commentRepository.save(newComment);
//
//        return CommentOuterResponseDto.of(newComment);
//    }
//
//    @Transactional
//    public CommentOuterResponseDto updateComment(
//            Long commentId,
//            CommentRequestDto commentRequestDto,
//            UserDetailsImpl userDetailsImpl
//    ) throws AccessDeniedException {
//        // 인증된 사용자 이름으로 사용자 정보를 DB에 조회
//        User user = userRepository.findByUsername(userDetailsImpl.getUsername()).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
//        );
//
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
//        );
//
//        if (user.getRole() != UserRoleEnum.ADMIN && comment.getUser() != user) {
//            throw new AccessDeniedException(ErrorMessage.ACCESS_DENIED.getMessage());
//        }
//
//        comment.update(commentRequestDto, user);
//
//        return CommentOuterResponseDto.of(comment);
//    }
//
//    @Transactional
//    public void deleteComment(Long commentId, UserDetailsImpl userDetailsImpl) throws AccessDeniedException {
//        // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
//        User user = userRepository.findByUsername(userDetailsImpl.getUsername()).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
//        );
//
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
//        );
//
//        if (user.getRole() != UserRoleEnum.ADMIN && comment.getUser() != user) {
//            throw new AccessDeniedException(ErrorMessage.ACCESS_DENIED.getMessage());
//        }
//
//        replyRepository.deleteAllByComment_Id(comment.getId());
//
//        commentRepository.delete(comment);
//    }
//
//    @Transactional
//    public void toggleCommentLike(Long commentId, UserDetailsImpl userDetails) throws AccessDeniedException {
//        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
//        );
//
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
//        );
//
//        if (user.getRole() != UserRoleEnum.ADMIN && comment.getUser() != user) {
//            throw new AccessDeniedException(ErrorMessage.ACCESS_DENIED.getMessage());
//        }
//
//        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment).orElse(null);
//        if (commentLike == null) {
//            commentLikeRepository.save(new CommentLike(user, comment));
//        }
//        else {
//            commentLikeRepository.delete(commentLike);
//        }
//    }
//}
