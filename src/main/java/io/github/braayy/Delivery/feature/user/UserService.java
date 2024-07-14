package io.github.braayy.Delivery.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }

    public User register(RegisterUserDTO dto) {
        User user = User.builder()
            .name(dto.name())
            .email(dto.email())
            .password(this.passwordEncoder.encode(dto.password()))
            .role(dto.role())
            .build();

        return this.userRepository.save(user);
    }

    public User getById(Long userId) {
        return this.userRepository.getReferenceById(userId);
    }

    public Page<User> listAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User update(Long userId, UpdateUserDTO dto) {
        User user = this.userRepository.getReferenceById(userId);

        if (dto.name() != null) {
            user.setName(dto.name());
        }

        if (dto.email() != null) {
            user.setEmail(dto.email());
        }

        if (dto.password() != null) {
            user.setPassword(this.passwordEncoder.encode(dto.password()));
        }

        return user;
    }

    public void deleteById(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
