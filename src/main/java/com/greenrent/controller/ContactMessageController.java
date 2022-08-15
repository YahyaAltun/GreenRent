package com.greenrent.controller;

import com.greenrent.domain.ContactMessage;
import com.greenrent.service.ContactMessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contactmessage")
@AllArgsConstructor
public class ContactMessageController {

    private ContactMessageService contactMessageService;


    //http://localhost:8083/contactmessage/visitor
    @PostMapping("/visitors")
    public ResponseEntity<Map<String,String>> createMessage(@Valid @RequestBody ContactMessage contactMessage){
        contactMessageService.createContactMessage(contactMessage);

        Map<String,String> map=new HashMap<>();
        map.put("message","Contact message successfully created");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //http://localhost:8083/contactmessage
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ContactMessage>> getAllContactMessage(){
        List<ContactMessage> list=contactMessageService.getAll();
        return ResponseEntity.ok(list);
    }

    //http://localhost:8083/contactmessage/1
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactMessage> getMessage(@PathVariable Long id){
        ContactMessage contactMessage=contactMessageService.getContactMessage(id);

        return ResponseEntity.ok(contactMessage);
    }

    //http://localhost:8083/contactmessage/request?id=1
    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactMessage> getMessageWithRequestParam(@RequestParam("id") Long id){
        ContactMessage contactMessage=contactMessageService.getContactMessage(id);

        return ResponseEntity.ok(contactMessage);
    }

    //http://localhost:8083/contactmessage/1
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,String>> updateContactMessage(@PathVariable Long id, @Valid @RequestBody ContactMessage contactMessage){
        contactMessageService.updateContactMessage(id, contactMessage);

        Map<String,String> map=new HashMap<>();
        map.put("message","Contact message successfully updated");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //http://localhost:8083/contactmessage/2
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,String>> deleteContactMessage(@PathVariable Long id){
        contactMessageService.deleteContactMessage(id);

        Map<String,String> map=new HashMap<>();
        map.put("message","Contact message successfully deleted");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //http://localhost:8083/contactmessage/pages/?page=0&size=2&sort=id&direction=ASC
    @GetMapping("/pages")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ContactMessage>> getAllWithPage(@RequestParam("page")int page, @RequestParam("size") int size,
                                                               @RequestParam("sort")String prop, @RequestParam("direction")Sort.Direction direction){

        Pageable pageable= PageRequest.of(page, size, Sort.by(direction,prop));
        Page<ContactMessage> contactMessagePage = contactMessageService.getAllWithPage(pageable);

        return ResponseEntity.ok(contactMessagePage);
    }
}
