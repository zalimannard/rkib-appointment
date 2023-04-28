package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberResponseDto;

@Service
@RequiredArgsConstructor
public class StaffMemberServiceImpl implements StaffMemberService {

    private final StaffMemberMapper mapper;
    private final StaffMemberRepository repository;

    private final PersonService personService;

    @Override
    public StaffMemberResponseDto create(StaffMemberRequestDto staffMemberDto) {
        Person person = personService.readEntity(staffMemberDto.getPersonId());
        StaffMember staffMemberToCreate = mapper.toEntity(staffMemberDto, person);
        StaffMember createdStaffMember = createEntity(staffMemberToCreate);
        return mapper.toDto(createdStaffMember);
    }

    @Override
    public StaffMember createEntity(StaffMember staffMember) {
        try {
            return repository.save(staffMember);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-01", "Конфликт при добавлении StaffMember в базу данных", e.getMessage());
        }
    }

    @Override
    public StaffMemberResponseDto read(String id) {
        StaffMember staffMember = readEntity(id);
        return mapper.toDto(staffMember);
    }

    @Override
    public StaffMember readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("sms-02", "Не найден StaffMember с id=" + id, null));
    }

    @Override
    public StaffMemberResponseDto update(String id, StaffMemberRequestDto staffMemberDto) {
        Person person = personService.readEntity(staffMemberDto.getPersonId());
        StaffMember staffMemberToUpdate = mapper.toEntity(staffMemberDto, person);
        staffMemberToUpdate.setId(id);
        StaffMember updatedStaffMember = updateEntity(staffMemberToUpdate);
        return mapper.toDto(updatedStaffMember);
    }

    @Override
    public StaffMember updateEntity(StaffMember staffMember) {
        try {
            return repository.save(staffMember);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-03", "Конфликт при обновлении StaffMember в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            StaffMember staffMember = readEntity(id);
            repository.delete(staffMember);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-04", "Конфликт при удалении StaffMember из базы данных", e.getMessage());
        }
    }

}
