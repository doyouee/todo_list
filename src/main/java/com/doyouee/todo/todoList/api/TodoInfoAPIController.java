package com.doyouee.todo.todoList.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doyouee.todo.todoList.entity.TodoInfoEntity;
import com.doyouee.todo.todoList.service.TodoInfoService;

import lombok.Delegate;

@RestController
@RequestMapping("/api/todo")
public class TodoInfoAPIController {
    @Autowired TodoInfoService tService;
    @PutMapping("/add") //todo list 추가
    public ResponseEntity<Object> insertTodo(@RequestBody TodoInfoEntity data, HttpSession session) {
        Map<String, Object> map = tService.addTodoInfo(data, session);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }


    @GetMapping("/list") // 리스트 보기
    public ResponseEntity<Object> getTodoList(HttpSession session) {
        Map<String, Object> map = tService.getTodoList(session);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }


    @PatchMapping("/update/{type}") // todo list의 상태 혹은 내용 변경
    public ResponseEntity<Object> getTodoList(HttpSession session, @PathVariable String type, @RequestParam String value, @RequestParam Long seq) {
        if(type.equals("status")) {
            Map<String, Object> map = tService.updateTodoStatus(Integer.parseInt(value), seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
        else if(type.equals("content")) {
            Map<String, Object> map = tService.updateTodoContent(value, seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
        else {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("status", false);
            map.put("message", "type은 status, content 둘 중 한가지만 가능합니다.");
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    }
    

    @DeleteMapping("/delete") // 삭제하기
    public ResponseEntity<Object> deleteTodo(HttpSession session, @RequestParam Long seq) {
        Map<String, Object> map = tService.deleteTodo(seq, session);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    
    @GetMapping("/list/term") // 기간별로 조회하기
    public ResponseEntity<Object> tODO(HttpSession session, @RequestParam String start, @RequestParam String end){
        Map<String, Object> map = tService.selectTodoListByTerm(session, start, end);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}