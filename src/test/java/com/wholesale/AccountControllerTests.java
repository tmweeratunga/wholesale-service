package com.wholesale;

import com.wholesale.api.dto.PlatformResponse;
import com.wholesale.api.dto.TransactionDetails;
import com.wholesale.api.dto.TransactionDetailsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest (classes = WholesaleCodeTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTests {

    private final static String BASE_URL = "http://localhost:";

    @LocalServerPort
    private int localPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @ParameterizedTest
    @CsvSource({
            "Bearer 123456,2",
            "Bearer 1234567,0"
    })
    void testGetUserAccountList_Success(String token, int numOfRecords){
        String url = BASE_URL+localPort + "/user/accounts";
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization",token);
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        PlatformResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertNotNull(response.getData());
        List<TransactionDetailsResponse> transactionDetailsResponseList = (List<TransactionDetailsResponse>) response.getData();
        assertNotNull(transactionDetailsResponseList );
        assertEquals(numOfRecords,transactionDetailsResponseList.size());
    }

    @Test
    void testGetUserAccountListWithoutAuthentication_Failed(){
        String url = BASE_URL + localPort + "/user/accounts";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @ParameterizedTest
    @CsvSource({
            "Bearer 123456,2",
            "Bearer 1234567,0"
    })
    void testGetUserTransactionList_Success(String token, int numOfRecords){
        String url = BASE_URL+localPort + "/user/accounts/0001/transactions?pageNumber=0&pageSize=2";
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization",token);
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        PlatformResponse response = responseEntity.getBody();
        assertNotNull(response);

        TransactionDetailsResponse transactionDetailsResponses = modelMapper.map(response.getData(),TransactionDetailsResponse.class);
        assertNotNull(transactionDetailsResponses);

        List<TransactionDetails> transactionDetailsList = transactionDetailsResponses.getTransactionDetails();
        assertNotNull(transactionDetailsList);
        assertEquals(numOfRecords,transactionDetailsList.size());

    }

    @Test
    void testGetUserTransactionListWithoutAuthentication_Failed(){
        String url = BASE_URL+localPort + "/user/accounts/0001/transactions?pageNumber=0&pageSize=2";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    void testGetUserTransactionListBadPageNumber_Failed(){
        String url = BASE_URL+localPort + "/user/accounts/0001/transactions?pageNumber=-1&pageSize=2";
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization","Bearer 123456");
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGetUserTransactionListBadPageSize_Failed(){
        String url = BASE_URL+localPort + "/user/accounts/0001/transactions?pageNumber=1&pageSize=-2";
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization","Bearer 123456");
        ResponseEntity<PlatformResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), PlatformResponse.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
