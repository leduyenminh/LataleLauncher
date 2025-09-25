package com.springLataleLauncher.demo.interfaces;

import com.springLataleLauncher.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {

    private ModelMapper modelMapper;

    public UserView toUserview(User user) {
        return modelMapper.map(user, UserView.class);
    }
//    public UserView toUserview(User user) {
//        UserView userview = new UserView();
//        userview.setUserId(user.getUserId());
//        userview.setUsername(user.getUsername());
//        // Map other relevant fields
//        return userview;
//    }
}
