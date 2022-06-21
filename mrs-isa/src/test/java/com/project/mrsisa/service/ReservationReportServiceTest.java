package com.project.mrsisa.service;

import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.repository.ReservationReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.project.mrsisa.constants.CottageConstants.COTTAGE_ID;
import static com.project.mrsisa.constants.ReservationReportConstants.RESERVATION_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationReportServiceTest {

    @Mock
    private ReservationReport reservationReportMock;

    @Mock
    private ReservationReportRepository reservationReportRepositoryMock;

    @InjectMocks
    private ReservationReportService reservationReportService;

    @Test
    public void haveReservationReportTest(){
        ReservationReport rr = new ReservationReport();
        rr.setReservationId(1L);
        when(reservationReportRepositoryMock.haveReservationReport(RESERVATION_ID)).thenReturn(rr);
        ReservationReport reservationReport = reservationReportService.haveReservationReport(RESERVATION_ID);

        assertEquals(RESERVATION_ID, reservationReport.getReservationId());

        //verify(reservationReportRepositoryMock, times(1)).haveReservationReport(RESERVATION_ID);
       // verifyNoMoreInteractions(reservationReportRepositoryMock);

    }
}
