package com.reagroup.pact.provider;

import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import sample.MyController;
import sample.MyService;

@RunWith(PactRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextPactTest.xml"})
@PactFile("file:src/test/resources/consumer-provider.json")
public class PactExampleTest {

    @Autowired
    private MyController myControllerWithService;

    private MyService myResponseService;

    @Before
    public void setUp() throws Exception {
        myResponseService = mock(MyService.class);
        myControllerWithService.withMyResponseService(myResponseService);
    }

    @ProviderState("response hello world for 'get /json'")
    public MyController shouldResponseCorrectHelloWorldForGet() {
        when(myResponseService.<String>getResponse()).thenReturn(new ResponseEntity<String>("{ \"hello\": \"world\" }", HttpStatus.OK));
        return myControllerWithService;
    }

    @ProviderState(value = "response hello world for 'get /deferred_json'", deferredResponseInMillis = 200)
    public MyController shouldResponseCorrectHelloWorldForDeferredGet() {
        when(myResponseService.<String>getResponse()).thenReturn(new ResponseEntity<String>("{ \"hello\": \"world\" }", HttpStatus.OK));
        return myControllerWithService;
    }

}
