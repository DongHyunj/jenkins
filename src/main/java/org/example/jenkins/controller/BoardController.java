package org.example.jenkins.controller;

import org.example.jenkins.entity.Board;
import org.example.jenkins.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> getList() {
        List<Board> list = boardRepository.findAllByOrderByIdxDesc();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getDetail(@PathVariable Long idx) {
        Optional<Board> board = boardRepository.findById(idx);
        if (board.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "게시글을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(board.get());
    }

    @PostMapping("/reg")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Board board) {
        Board saved = boardRepository.save(board);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "게시글이 성공적으로 등록되었습니다.");
        response.put("idx", saved.getIdx());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
