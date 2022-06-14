package com.project.mrsisa.domain;

import javax.persistence.*;
@Entity
public class ReservationReport {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String reportText;
        @Column(nullable = false)
        private Long clientId;
        @Column(nullable = false)
        private Long reservationId;
        @Column(nullable = false)
        private boolean penallty;
        @Column(nullable = false)
        private boolean  unarrivedClient;
        @Enumerated
        private ProcessingStatus status;

        public ReservationReport() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getReportText() {
                return reportText;
        }

        public void setReportText(String reportText) {
                this.reportText = reportText;
        }

        public Long getClientId() {
                return clientId;
        }

        public void setClientId(Long clientId) {
                this.clientId = clientId;
        }

        public Long getReservationId() {
                return reservationId;
        }

        public void setReservationId(Long reservationId) {
                this.reservationId = reservationId;
        }

        public boolean isPenallty() {
                return penallty;
        }

        public void setPenallty(boolean penallty) {
                this.penallty = penallty;
        }

        public boolean isUnarrivedClient() {
                return unarrivedClient;
        }

        public void setUnarrivedClient(boolean unarrivedClient) {
                this.unarrivedClient = unarrivedClient;
        }

		public ProcessingStatus getStatus() {
			return status;
		}

		public void setStatus(ProcessingStatus status) {
			this.status = status;
		}
        
}
