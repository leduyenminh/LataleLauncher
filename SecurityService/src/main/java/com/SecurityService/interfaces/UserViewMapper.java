package com.SecurityService.interfaces;

// import com.SecurityService.interfaces.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.SecurityService.entity.User;

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
