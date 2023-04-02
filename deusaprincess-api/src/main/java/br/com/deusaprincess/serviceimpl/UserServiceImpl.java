package br.com.deusaprincess.serviceimpl;

import br.com.deusaprincess.domain.User;
import br.com.deusaprincess.exception.NotFoundException;
import br.com.deusaprincess.model.UserDTO;
import br.com.deusaprincess.repository.AddressRepository;
import br.com.deusaprincess.repository.UserRepository;
import br.com.deusaprincess.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) throws NotFoundException {
        if (userRepository.existsById(userDTO.getCpf())) {
            throw new NotFoundException("CPF já cadastrado na base de dados.");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new NotFoundException("E-mail já cadastrado na base de dados.");
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        addressRepository.saveAll(user.getAddresses());
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) throws NotFoundException {
        User user = userRepository.findByCpf(userDTO.getCpf())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado na base de dados."));
        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new NotFoundException("E-mail já cadastrado na base de dados.");
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBirthdate(userDTO.getBirthdate());
        if (!userDTO.getPassword().equals(user.getPassword()) && userDTO.getPassword() != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        }
        addressRepository.saveAll(user.getAddresses());
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findByCpf(Long cpf) throws NotFoundException {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado na base de dados."));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado na base de dados."));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long cpf) throws NotFoundException {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado na base de dados."));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}