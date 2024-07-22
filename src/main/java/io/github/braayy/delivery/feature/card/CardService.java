package io.github.braayy.delivery.feature.card;

import io.github.braayy.delivery.feature.card.dto.RegisterCardDTO;
import io.github.braayy.delivery.feature.card.dto.UpdateCardDTO;
import io.github.braayy.delivery.feature.user.User;
import io.github.braayy.delivery.feature.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public Card register(RegisterCardDTO dto, Long userId) {
        User user = this.userService.getById(userId);

        Card card = Card.builder()
            .user(user)
            .nickname(dto.nickname())
            .brand(dto.brand())
            .number(dto.number())
            .holderName(dto.holderName())
            .holderCpf(dto.holderCpf())
            .expirationDate(dto.expirationDate().withDayOfMonth(1))
            .cvv(this.passwordEncoder.encode(dto.cvv()))
            .build();

        return this.cardRepository.save(card);
    }

    public Card getById(Long cardId) {
        return this.cardRepository.getReferenceById(cardId);
    }

    public Page<Card> listAll(Pageable pageable, Long userId) {
        User user = this.userService.getById(userId);

        return this.cardRepository.findAllByUser(user, pageable);
    }

    public Card update(Long cardId, UpdateCardDTO dto) {
        Card card = this.cardRepository.getReferenceById(cardId);

        if (dto.nickname() != null) {
            card.setNickname(dto.nickname());
        }

        if (dto.holderName() != null) {
            card.setHolderName(dto.holderName());
        }

        return card;
    }

    public void deleteById(Long cardId) {
        this.cardRepository.deleteById(cardId);
    }

}
