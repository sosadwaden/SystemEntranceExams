package com.sosadwaden.service;

import com.sosadwaden.dao.FacultyDao;
import com.sosadwaden.dto.CreateFacultyDto;
import com.sosadwaden.dto.FacultyDto;
import com.sosadwaden.dto.UpdateFacultyDto;
import com.sosadwaden.entity.Faculty;
import com.sosadwaden.exception.ValidationException;
import com.sosadwaden.mapper.CreateFacultyMapper;
import com.sosadwaden.mapper.ReadFacultyMapper;
import com.sosadwaden.mapper.UpdateFacultyMapper;
import com.sosadwaden.validator.CreateFacultyValidator;
import com.sosadwaden.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacultyService {

    private final FacultyDao facultyDao = FacultyDao.getInstance();
    private final CreateFacultyValidator createFacultyValidator = CreateFacultyValidator.getInstance();
    private final ReadFacultyMapper readFacultyMapper = ReadFacultyMapper.getInstance();
    private final CreateFacultyMapper createFacultyMapper = CreateFacultyMapper.getInstance();
    private final UpdateFacultyMapper updateFacultyMapper = UpdateFacultyMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private static final FacultyService INSTANCE = new FacultyService();

    public static FacultyService getInstance() {
        return INSTANCE;
    }

    public List<FacultyDto> findAll() {
        return facultyDao.findAll().stream()
                .map(readFacultyMapper::mapFrom)
                .collect(toList());
    }


    public FacultyDto findById(Long id) {
        return readFacultyMapper.mapFrom(facultyDao.findById(id).get());
        // TODO разобраться здесь с Optional и как его обрабатывать
    }

    public Long create(CreateFacultyDto createFacultyDto) {
        // validation
        ValidationResult validationResult = createFacultyValidator.isValid(createFacultyDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        // map
        Faculty faculty = createFacultyMapper.mapFrom(createFacultyDto);
        // facultyDao
        try {
            imageService.upload(faculty.getImage(), createFacultyDto.getImage().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facultyDao.save(faculty);
        // return
        return faculty.getId();
    }

    public void update(UpdateFacultyDto updateFacultyDto) {
        //validation
        // map
        Faculty faculty = updateFacultyMapper.mapFrom(updateFacultyDto);
        // dao
        facultyDao.update(faculty);
    }

    public boolean delete(Long facultyId) {
        return facultyDao.delete(facultyId);
    }

//    public void createAndSave() {
//
//        Faculty faculty1 = new Faculty();
//        faculty1.setName("История");
//        faculty1.setFacultyCapacity(2);
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setName("География");
//        faculty2.setFacultyCapacity(2);
//
//        Faculty faculty3 = new Faculty();
//        faculty3.setName("Журналистика");
//        faculty3.setFacultyCapacity(2);
//
//        Applicant applicant1 = new Applicant();
//        applicant1.setName("Дима");
//        applicant1.setSurname("Денисов");
//        applicant1.setAge(18);
//        applicant1.setStatus(Status.TAKE_EXAMS);
//        applicant1.setFaculty(faculty1);
//
//        Applicant applicant2 = new Applicant();
//        applicant2.setName("Максим");
//        applicant2.setSurname("Максимов");
//        applicant2.setAge(19);
//        applicant2.setStatus(Status.TAKE_EXAMS);
//        applicant2.setFaculty(faculty1);
//
//        Applicant applicant3 = new Applicant();
//        applicant3.setName("Иван");
//        applicant3.setSurname("Иванов");
//        applicant3.setAge(20);
//        applicant3.setStatus(Status.TAKE_EXAMS);
//        applicant3.setFaculty(faculty1);
//
//        Applicant applicant4 = new Applicant();
//        applicant4.setName("Света");
//        applicant4.setSurname("Светлановнна");
//        applicant4.setAge(21);
//        applicant4.setStatus(Status.TAKE_EXAMS);
//        applicant4.setFaculty(faculty1);
//
//        Applicant applicant5 = new Applicant();
//        applicant5.setName("Степан");
//        applicant5.setSurname("Степанов");
//        applicant5.setAge(22);
//        applicant5.setStatus(Status.TAKE_EXAMS);
//        applicant5.setFaculty(faculty2);
//
//        Applicant applicant6 = new Applicant();
//        applicant6.setName("Влад");
//        applicant6.setSurname("Владиславович");
//        applicant6.setAge(23);
//        applicant6.setStatus(Status.TAKE_EXAMS);
//        applicant6.setFaculty(faculty2);
//
//        Applicant applicant7 = new Applicant();
//        applicant7.setName("Алексей");
//        applicant7.setSurname("Алексий");
//        applicant7.setAge(24);
//        applicant7.setStatus(Status.TAKE_EXAMS);
//        applicant7.setFaculty(faculty2);
//
//        Applicant applicant8 = new Applicant();
//        applicant8.setName("Илья");
//        applicant8.setSurname("Ильич");
//        applicant8.setAge(25);
//        applicant8.setStatus(Status.TAKE_EXAMS);
//        applicant8.setFaculty(faculty2);
//
//        Applicant applicant9 = new Applicant();
//        applicant9.setName("Данил");
//        applicant9.setSurname("Данилов");
//        applicant9.setAge(26);
//        applicant9.setStatus(Status.TAKE_EXAMS);
//        applicant9.setFaculty(faculty3);
//
//        Applicant applicant10 = new Applicant();
//        applicant10.setName("Вася");
//        applicant10.setSurname("Васяныч");
//        applicant10.setAge(27);
//        applicant10.setStatus(Status.TAKE_EXAMS);
//        applicant10.setFaculty(faculty3);
//
//        Applicant applicant11 = new Applicant();
//        applicant11.setName("Петр");
//        applicant11.setSurname("Петров");
//        applicant11.setAge(28);
//        applicant11.setStatus(Status.TAKE_EXAMS);
//        applicant11.setFaculty(faculty3);
//
//        Applicant applicant12 = new Applicant();
//        applicant12.setName("Рома");
//        applicant12.setSurname("Романов");
//        applicant12.setAge(29);
//        applicant12.setStatus(Status.TAKE_EXAMS);
//        applicant12.setFaculty(faculty3);
//
//        facultyDao.save(faculty1);
//        facultyDao.save(faculty2);
//        facultyDao.save(faculty3);
//
//        applicantDao.save(applicant1);
//        applicantDao.save(applicant2);
//        applicantDao.save(applicant3);
//        applicantDao.save(applicant4);
//        applicantDao.save(applicant5);
//        applicantDao.save(applicant6);
//        applicantDao.save(applicant7);
//        applicantDao.save(applicant8);
//        applicantDao.save(applicant9);
//        applicantDao.save(applicant10);
//        applicantDao.save(applicant11);
//        applicantDao.save(applicant12);
//    }
//
//    public void takeExamsAndSave() {
//        Random random = new Random();
//        List<Applicant> applicants = applicantDao.findAll();
//        for (Applicant applicant: applicants) {
//            applicant.setScore(random.nextInt(101));
//            System.out.println(applicant);
//            applicantDao.update(applicant);
//        }
//    }
//
//    public void returnWhoIsEnrolled() {
//        List<Applicant> applicantsHistory = applicantDao.findByFacultyAndSortByScore(1);
//        List<Applicant> applicantsGeography = applicantDao.findByFacultyAndSortByScore(2);
//        List<Applicant> applicantsJournalism = applicantDao.findByFacultyAndSortByScore(3);
//
//        int facultyCapacityHistory = applicantsHistory.get(0).getFaculty().getFacultyCapacity();
//        int facultyCapacityGeography = applicantsGeography.get(0).getFaculty().getFacultyCapacity();
//        int facultyCapacityJournalism = applicantsJournalism.get(0).getFaculty().getFacultyCapacity();
//
//        for (int i = 0; i < applicantsHistory.size(); i++) {
//            Applicant applicant = applicantsHistory.get(i);
//            if (i >= facultyCapacityHistory) {
//                applicant.setStatus(Status.NOT_ENROLLED);
//            } else {
//                applicant.setStatus(Status.ENROLLED);
//            }
//            applicantDao.update(applicant);
//            System.out.println(applicant);
//        }
//
//        for (int i = 0; i < applicantsGeography.size(); i++) {
//            Applicant applicant = applicantsGeography.get(i);
//            if (i >= facultyCapacityGeography) {
//                applicant.setStatus(Status.NOT_ENROLLED);
//            } else {
//                applicant.setStatus(Status.ENROLLED);
//            }
//            applicantDao.update(applicant);
//            System.out.println(applicant);
//        }
//
//        for (int i = 0; i < applicantsJournalism.size(); i++) {
//            Applicant applicant = applicantsJournalism.get(i);
//            if (i >= facultyCapacityJournalism) {
//                applicant.setStatus(Status.NOT_ENROLLED);
//            } else {
//                applicant.setStatus(Status.ENROLLED);
//            }
//            applicantDao.update(applicant);
//            System.out.println(applicant);
//        }
//    }

}
