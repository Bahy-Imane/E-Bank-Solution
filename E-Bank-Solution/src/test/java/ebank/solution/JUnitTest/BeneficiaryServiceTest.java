//package ebank.solution.JUnitTest;
//
//import ebank.solution.model.Account;
//import ebank.solution.model.Beneficiary;
//import ebank.solution.repository.AccountRepository;
//import ebank.solution.repository.BeneficiaryRepository;
//import ebank.solution.service.BeneficiaryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class BeneficiaryServiceTest {
//
//    @Mock
//    private BeneficiaryRepository beneficiaryRepository;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private BeneficiaryService beneficiaryService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllBeneficiaries() {
//        List<Beneficiary> beneficiaries = new ArrayList<>();
//        beneficiaries.add(new Beneficiary());
//        beneficiaries.add(new Beneficiary());
//        when(beneficiaryRepository.findBeneficiariesByAccount_AccountId(1L)).thenReturn(beneficiaries);
//
//        List<Beneficiary> result = beneficiaryService.getAllBeneficiaries(1L);
//
//        assertEquals(2, result.size());
//        verify(beneficiaryRepository, times(1)).findBeneficiariesByAccount_AccountId(1L);
//    }
//
//    @Test
//    public void testAddBeneficiary() {
//        Account account = new Account();
//        account.setAccountId(1L);
//        Beneficiary beneficiary = new Beneficiary();
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
//        when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
//
//        Beneficiary result = beneficiaryService.addBeneficiary(1L, beneficiary);
//
//        assertNotNull(result);
//        assertEquals(account, result.getAccount());
//        verify(accountRepository, times(1)).findById(1L);
//        verify(beneficiaryRepository, times(1)).save(beneficiary);
//    }
//
//    @Test
//    public void testUpdateBeneficiary() {
//        Beneficiary beneficiary = new Beneficiary();
//        beneficiary.setBenefNAme("Old Name");
//        beneficiary.setAccountType("Old Type");
//        beneficiary.setBenefAccNumber(12345.0);
//
//        Beneficiary updatedBeneficiary = new Beneficiary();
//        updatedBeneficiary.setBenefNAme("New Name");
//        updatedBeneficiary.setAccountType("New Type");
//        updatedBeneficiary.setBenefAccNumber(67890.0);
//
//        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));
//        when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
//
//        Beneficiary result = beneficiaryService.updateBenifiary(1L, updatedBeneficiary);
//
//        assertNotNull(result);
//        assertEquals("New Name", result.getBenefNAme());
//        assertEquals("New Type", result.getAccountType());
//        assertEquals(67890.0, result.getBenefAccNumber());
//        verify(beneficiaryRepository, times(1)).findById(1L);
//        verify(beneficiaryRepository, times(1)).save(beneficiary);
//    }
//
//    @Test
//    public void testDeleteBeneficiary() {
//        doNothing().when(beneficiaryRepository).deleteById(1L);
//
//        beneficiaryService.deleteBeneficiary(1L);
//
//        verify(beneficiaryRepository, times(1)).deleteById(1L);
//    }
//}
