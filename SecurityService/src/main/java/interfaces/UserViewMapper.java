package interfaces;

import com.springLataleLauncher.demo.entity.User;
import com.springLataleLauncher.demo.interfaces.UserView;
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
