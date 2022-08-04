package com.greenrent.controller;

import com.greenrent.dto.UserDTO;
import com.greenrent.dto.request.UpdatePasswordRequest;
import com.greenrent.dto.request.UserUpdateRequest;
import com.greenrent.dto.response.GRResponse;
import com.greenrent.dto.response.ResponseMessage;
import com.greenrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    //http://localhost:8083/user/auth/all
    @GetMapping("/auth/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Sistemdeki kayitli herhangi bir kullanici kendi bilgilerini getiriyor
    //http://localhost:8083/user
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('COSTOMER')")
    public ResponseEntity<UserDTO> getUserById(HttpServletRequest request){
        Long id =(Long) request.getAttribute("id");
        UserDTO userDTO=userService.findById(id);

        return ResponseEntity.ok(userDTO);
    }


    //http://localhost:8083/user/auth/pages?size=1&page=0&sort=id&direction=DESC
    @GetMapping("/auth/pages")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllUsersPage(@RequestParam("page")int page,
                                                         @RequestParam("size")int size,
                                                         @RequestParam("sort")String prop,
                                                         @RequestParam("direction")Direction direction){

        Pageable pageable=PageRequest.of(page,size,Sort.by(direction,prop));

        Page<UserDTO> userDTOPage=userService.getUserPage(pageable);

        return ResponseEntity.ok(userDTOPage);

    }

    //to get any user in the system, Admin is able to use this method
    //http://localhost:8083/user/1/auth
    @GetMapping("/{id}/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserByIdAdmin(@PathVariable Long id){
        UserDTO user=userService.findById(id);
        return ResponseEntity.ok(user);
    }

    //http://localhost:8083/user/auth
    @PatchMapping("/auth")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COSTOMER')")
    public ResponseEntity<GRResponse> updatePassword(HttpServletRequest httpServletRequest, @RequestBody UpdatePasswordRequest passwordRequest){
        Long id= (Long) httpServletRequest.getAttribute("id");
        userService.updatePassword(id,passwordRequest);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.PASSWORD_CHANGED_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    //http://localhost:8083/user
    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COSTOMER')")
    public ResponseEntity<GRResponse> updateUser(HttpServletRequest httpServletRequest,@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        Long id= (Long) httpServletRequest.getAttribute("id");
        userService.updateUser(id,userUpdateRequest);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    //http://localhost:8083/user/2/auth
    @DeleteMapping("/{id}/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> deleteUser(@PathVariable Long id){
        userService.removeById(id);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.DELETE_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

}
