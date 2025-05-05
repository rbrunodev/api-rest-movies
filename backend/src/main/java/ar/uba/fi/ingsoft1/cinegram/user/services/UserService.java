package ar.uba.fi.ingsoft1.cinegram.user.services;

import ar.uba.fi.ingsoft1.cinegram.user.dto.RefreshDTO;
import ar.uba.fi.ingsoft1.cinegram.user.dto.TokenDTO;
import ar.uba.fi.ingsoft1.cinegram.user.dto.UserCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.user.interfaces.UserCredentials;
import ar.uba.fi.ingsoft1.cinegram.user.interfaces.UserRepository;
import ar.uba.fi.ingsoft1.cinegram.user.models.User;
import ar.uba.fi.ingsoft1.cinegram.config.security.JwtService;
import ar.uba.fi.ingsoft1.cinegram.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.cinegram.user.refresh_token.RefreshToken;
import ar.uba.fi.ingsoft1.cinegram.user.refresh_token.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    UserService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RefreshTokenService refreshTokenService
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    var msg = String.format("Username '%s' not found", username);
                    return new UsernameNotFoundException(msg);
                });
    }

    public Optional<TokenDTO> singUp(UserCreateDTO data) {
        if (userRepository.findByUsername(data.username()).isPresent()) {
            return Optional.empty();
        }

        var user = data.asUser(passwordEncoder::encode);
        userRepository.save(user);
        return Optional.of(generateTokens(user));
    }

    public Optional<TokenDTO> loginUser(UserCredentials data) {
        Optional<User> maybeUser = userRepository.findByUsername(data.username());
        return maybeUser
                .filter(user -> passwordEncoder.matches(data.password(), user.getPassword()))
                .map(this::generateTokens);
    }

    public Optional<TokenDTO> refresh(RefreshDTO data) {
        return refreshTokenService.findByValue(data.refreshToken())
                .map(RefreshToken::user)
                .map(this::generateTokens);
    }

    private TokenDTO generateTokens(User user) {
        String accessToken = jwtService.createToken(new JwtUserDetails(
                user.getUsername(),
                user.getRole()
        ));
        RefreshToken refreshToken = refreshTokenService.createFor(user);
        return new TokenDTO(accessToken, refreshToken.value());
    }
}
