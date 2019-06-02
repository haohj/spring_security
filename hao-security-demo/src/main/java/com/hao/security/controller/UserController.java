package com.hao.security.controller;

import com.hao.security.entity.User;
import com.hao.security.entity.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/query")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam(required = false,name = "username",defaultValue = "tom") String name){
        System.out.println(name);
        List<User> userList = new ArrayList<>();
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }


    @GetMapping("/query2")
    public List<User> query2(UserQueryCondition userQueryCondition, @PageableDefault(page = 1,size = 15,sort = "name",direction = Sort.Direction.ASC) Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> userList = new ArrayList<>();
        User user1 = new User("user1","123456");
        User user2 = new User("user2","123456");
        User user3 = new User("user3","123456");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailsView.class)
    public User getUserInfo(@PathVariable String id){
       return new User("tom","123456");
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error ->{
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getBirthdate());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error->{
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField()+" "+fieldError.getDefaultMessage();
                System.out.println(message);
                //System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(ReflectionToStringBuilder.toString(User.class,ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }
}
