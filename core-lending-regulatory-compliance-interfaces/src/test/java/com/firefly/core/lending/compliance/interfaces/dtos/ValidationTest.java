/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.compliance.interfaces.dtos;

import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlActionTypeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlCaseStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.RiskLevelEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.SarStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.CurrencyCodeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RecordTypeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.ReportTypeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RunStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.SubmissionStatusEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive validation tests for all DTOs to ensure Bean Validation annotations work correctly.
 */
public class ValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAmlCaseDTOValidation() {
        // Test valid AmlCaseDTO
        AmlCaseDTO validDto = AmlCaseDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .amlCaseStatus(AmlCaseStatusEnum.OPEN)
                .caseOpenedAt(LocalDate.now().minusDays(1))
                .riskLevel(RiskLevelEnum.MEDIUM)
                .notes("Valid notes")
                .build();

        Set<ConstraintViolation<AmlCaseDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid AmlCaseDTO - missing required fields
        AmlCaseDTO invalidDto = AmlCaseDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        // Check specific violations
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reporting run ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Customer ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Transaction ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("AML case status is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Case opened date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Risk level is required")));

        // Test future date validation
        AmlCaseDTO futureDateDto = AmlCaseDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .amlCaseStatus(AmlCaseStatusEnum.OPEN)
                .caseOpenedAt(LocalDate.now().plusDays(1))
                .riskLevel(RiskLevelEnum.MEDIUM)
                .build();
        
        violations = validator.validate(futureDateDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot be in the future")));

        // Test notes size validation
        AmlCaseDTO longNotesDto = AmlCaseDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .amlCaseStatus(AmlCaseStatusEnum.OPEN)
                .caseOpenedAt(LocalDate.now().minusDays(1))
                .riskLevel(RiskLevelEnum.MEDIUM)
                .notes("x".repeat(2001))
                .build();
        
        violations = validator.validate(longNotesDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 2000 characters")));
    }

    @Test
    void testAmlActionDTOValidation() {
        // Test valid AmlActionDTO
        AmlActionDTO validDto = AmlActionDTO.builder()
                .amlCaseId(UUID.randomUUID())
                .actionDate(LocalDateTime.now().minusHours(1))
                .actionType(AmlActionTypeEnum.REVIEW)
                .outcome("Valid outcome")
                .build();

        Set<ConstraintViolation<AmlActionDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid AmlActionDTO - missing required fields
        AmlActionDTO invalidDto = AmlActionDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("AML case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Action date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Action type is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Outcome is required")));

        // Test outcome size validation
        AmlActionDTO longOutcomeDto = AmlActionDTO.builder()
                .amlCaseId(UUID.randomUUID())
                .actionDate(LocalDateTime.now().minusHours(1))
                .actionType(AmlActionTypeEnum.REVIEW)
                .outcome("x".repeat(1001))
                .build();
        
        violations = validator.validate(longOutcomeDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 1000 characters")));
    }

    @Test
    void testAmlSarDTOValidation() {
        // Test valid AmlSarDTO
        AmlSarDTO validDto = AmlSarDTO.builder()
                .amlCaseId(UUID.randomUUID())
                .sarReference("SAR-2024-001")
                .sarDate(LocalDate.now().minusDays(1))
                .sarStatus(SarStatusEnum.SUBMITTED)
                .authorityFeedback("Valid feedback")
                .build();

        Set<ConstraintViolation<AmlSarDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid AmlSarDTO - missing required fields
        AmlSarDTO invalidDto = AmlSarDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("AML case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("SAR reference is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("SAR date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("SAR status is required")));

        // Test SAR reference size validation
        AmlSarDTO longRefDto = AmlSarDTO.builder()
                .amlCaseId(UUID.randomUUID())
                .sarReference("x".repeat(101))
                .sarDate(LocalDate.now().minusDays(1))
                .sarStatus(SarStatusEnum.SUBMITTED)
                .build();
        
        violations = validator.validate(longRefDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 100 characters")));
    }

    @Test
    void testReportingRunDTOValidation() {
        // Test valid ReportingRunDTO
        ReportingRunDTO validDto = ReportingRunDTO.builder()
                .reportType(ReportTypeEnum.AML)
                .reportingPeriodStart(LocalDate.now().minusDays(30))
                .reportingPeriodEnd(LocalDate.now().minusDays(1))
                .runStatus(RunStatusEnum.COMPLETED)
                .remarks("Valid remarks")
                .build();

        Set<ConstraintViolation<ReportingRunDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid ReportingRunDTO - missing required fields
        ReportingRunDTO invalidDto = ReportingRunDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Report type is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reporting period start date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reporting period end date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Run status is required")));

        // Test remarks size validation
        ReportingRunDTO longRemarksDto = ReportingRunDTO.builder()
                .reportType(ReportTypeEnum.AML)
                .reportingPeriodStart(LocalDate.now().minusDays(30))
                .reportingPeriodEnd(LocalDate.now().minusDays(1))
                .runStatus(RunStatusEnum.COMPLETED)
                .remarks("x".repeat(1001))
                .build();
        
        violations = validator.validate(longRemarksDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 1000 characters")));
    }

    @Test
    void testReportingRecordDTOValidation() {
        // Test valid ReportingRecordDTO
        ReportingRecordDTO validDto = ReportingRecordDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .loanServicingCaseId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .recordType(RecordTypeEnum.EXPOSURE)
                .reportedAmount(new BigDecimal("1000.50"))
                .currencyCode(CurrencyCodeEnum.EUR)
                .isIncluded(true)
                .build();

        Set<ConstraintViolation<ReportingRecordDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid ReportingRecordDTO - missing required fields
        ReportingRecordDTO invalidDto = ReportingRecordDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reporting run ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Loan servicing case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Customer ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Transaction ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Record type is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reported amount is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Currency code is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Is included flag is required")));

        // Test reported amount validation - zero value
        ReportingRecordDTO zeroAmountDto = ReportingRecordDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .loanServicingCaseId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .recordType(RecordTypeEnum.EXPOSURE)
                .reportedAmount(BigDecimal.ZERO)
                .currencyCode(CurrencyCodeEnum.EUR)
                .isIncluded(true)
                .build();
        
        violations = validator.validate(zeroAmountDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be greater than zero")));
    }

    @Test
    void testRegulatorySubmissionDTOValidation() {
        // Test valid RegulatorySubmissionDTO
        RegulatorySubmissionDTO validDto = RegulatorySubmissionDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .submissionRef("SUB-2024-001")
                .submissionDate(LocalDate.now().minusDays(1))
                .submissionStatus(SubmissionStatusEnum.SUBMITTED)
                .responseDetails("Valid response")
                .build();

        Set<ConstraintViolation<RegulatorySubmissionDTO>> violations = validator.validate(validDto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no violations");

        // Test invalid RegulatorySubmissionDTO - missing required fields
        RegulatorySubmissionDTO invalidDto = RegulatorySubmissionDTO.builder().build();
        violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have violations");
        
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reporting run ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Submission reference is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Submission date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Submission status is required")));

        // Test submission reference size validation
        RegulatorySubmissionDTO longRefDto = RegulatorySubmissionDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .submissionRef("x".repeat(101))
                .submissionDate(LocalDate.now().minusDays(1))
                .submissionStatus(SubmissionStatusEnum.SUBMITTED)
                .build();
        
        violations = validator.validate(longRefDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 100 characters")));

        // Test response details size validation
        RegulatorySubmissionDTO longResponseDto = RegulatorySubmissionDTO.builder()
                .reportingRunId(UUID.randomUUID())
                .submissionRef("SUB-2024-001")
                .submissionDate(LocalDate.now().minusDays(1))
                .submissionStatus(SubmissionStatusEnum.SUBMITTED)
                .responseDetails("x".repeat(2001))
                .build();
        
        violations = validator.validate(longResponseDto);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("cannot exceed 2000 characters")));
    }
}
