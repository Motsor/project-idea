package fi.project.projectidea.web;

import fi.project.projectidea.domain.User;
import fi.project.projectidea.domain.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Finds the current user and loads its data.
    @Override
    public UserDetails loadUserByUsername(String username) {
        User currentUser = userRepository.findByUsername(username);
        //Returns instance of UserDetails filled with the data of the current user.
        return new org.springframework.security.core.userdetails
                .User(username, currentUser.getPassword(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
    }

}
