package com.project.mrsisa.service;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.repository.CottageRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.mrsisa.constants.CottageConstants.COTTAGE_ID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageServiceTest {

    @Mock
    private CottageRepository cottageRepositoryMock;

    @Mock
    private Cottage cottageMock;

    @InjectMocks
    private CottageService cottageService;

    @Test
    public void findOneCottageTest(){
        when(cottageRepositoryMock.findById(COTTAGE_ID)).thenReturn(Optional.of(cottageMock));
        Cottage cottage = cottageService.findOne(COTTAGE_ID);
        assertEquals(cottageMock, cottage);
    verify(cottageRepositoryMock, times(1)).findById(COTTAGE_ID);
    verifyNoMoreInteractions(cottageRepositoryMock);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void addCottageTest(){
        List<Cottage> cottageList = new ArrayList<>();
        Cottage cottage = new Cottage();
        cottage.setId(5L);
        cottage.setName("Medica");
        cottage.setDescription("Prelepo mesto sa pogledom na reku.");
        CottageOwner co = new CottageOwner();
        co.setId(1L);
        cottageList.add(cottage);
        co.setCottages(cottageList);
        cottage.setOwner(co);
        when(cottageRepositoryMock.findByOwner()).thenReturn(Arrays.asList(new Student(DB_ID, DB_INDEX, DB_FIRST_NAME, DB_LAST_NAME)));
        when(studentRepositoryMock.save(student)).thenReturn(student);

        // 2. Akcija
        int dbSizeBeforeAdd = studentService.findAll().size();

        Student dbStudent = studentService.save(student);

        when(studentRepositoryMock.findAll()).thenReturn(Arrays.asList(new Student(DB_ID, DB_INDEX, DB_FIRST_NAME, DB_LAST_NAME), student));

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertThat(dbStudent).isNotNull();*/
    }



}
