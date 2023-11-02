package springrest.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    FriendRepository friR;
    @GetMapping
    public ResponseEntity<List<Friend>> list() {
        log.info("list 요청");
        List<Friend> friendList = friR.findAll();
        ResponseEntity<List<Friend>> entity = new ResponseEntity<>(friendList, HttpStatus.OK);

        return entity;
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(friR.findAll());//이런식으로 해도 됨.
    }
    @GetMapping("/{friendNo}")
    public ResponseEntity<Friend> read(@PathVariable("friendNo") int friendNo) {
        log.info("read 요청");
        Optional<Friend> friend = friR.findById(friendNo);
        if (!friend.isEmpty()){
            ResponseEntity<Friend> entity = new ResponseEntity<>(friend.get(), HttpStatus.OK);
            return entity;
        }
        else{
            HttpHeaders header = new HttpHeaders();
            header.add("BAD_ID",String.valueOf(friendNo));
            return new ResponseEntity(header, HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody Friend friend) {
        log.info("register 요청");
        try{
            friR.save(friend);
            ResponseEntity<String> entity = new ResponseEntity<>("성공적으로 삽입했어용", HttpStatus.CREATED);
            return entity;
        }catch (Exception e){
            return new ResponseEntity("입력을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PutMapping("/{friendNo}")
    public ResponseEntity<String> modify(@PathVariable("friendNo") int friendNo, @RequestBody Friend friend) {
        log.info("modify 요청");
        Optional<Friend> friend0 = friR.findById(friendNo);
        if(friend0.isPresent()){
            Friend friend1 = friend0.get();
            friend1.setFage(friend.getFage());
            friend1.setFname(friend.getFname());
            try{
                friR.save(friend1);
                ResponseEntity<String> entity = new ResponseEntity<>("성공적으로 수정했어용", HttpStatus.RESET_CONTENT);
                log.info("요청 성공");
                return entity;
            }
            catch(Exception e){
                ResponseEntity<String> entity = new ResponseEntity<>("수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
                log.info("요청 실패");
                return entity;
            }
        }else{
            ResponseEntity<String> entity = new ResponseEntity<>("해당 아이디가 없습니다.", HttpStatus.NOT_FOUND);
            log.info("요청 실패");
            return entity;
        }

    }
    @DeleteMapping("/{friendNo}")
    public ResponseEntity<String> remove(@PathVariable("friendNo") int friendNo) {
        log.info("remove 요청");
        try{
            Friend friend1 = friR.findById(friendNo).get();
            friR.deleteById(friendNo);
            ResponseEntity<String> entity = new ResponseEntity<>("성공적으로 삭제했어용", HttpStatus.RESET_CONTENT);
            log.info("요청 성공");
            return entity;
        }
        catch(Exception e){
            ResponseEntity<String> entity = new ResponseEntity<>("삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            log.info("요청 실패");
            return entity;
        }
    }
}
