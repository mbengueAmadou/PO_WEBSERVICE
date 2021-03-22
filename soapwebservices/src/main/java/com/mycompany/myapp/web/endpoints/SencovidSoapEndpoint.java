package com.mycompany.myapp.web.endpoints;

import com.mycompany.myapp.domain.Sencovid;
import com.mycompany.myapp.repository.SencovidRepository;
import com.mycompany.myapp.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Endpoint
public class SencovidSoapEndpoint {

    @Autowired
    SencovidRepository sencovidRepository;
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8081/api/sencovids";
    String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmZHVEeGVCREZjX0ZpX21pc0h4dkJXVTRPcHR5cDVDai1iMTBxb0dpOTNvIn0.eyJleHAiOjE2MTYyNzExNTAsImlhdCI6MTYxNjIzNTE1MCwianRpIjoiNmU0NmE4ZjUtMTVlMy00NmJhLTg2NzEtNTQ3MWUyYjk1MDhkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL2F1dGgvcmVhbG1zL2poaXBzdGVyIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImRkOGRjOTZkLTRhNzUtNDQ2YS1hYWJiLTYyN2ZjYWFlNDM2OSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImNvdmlkIiwic2Vzc2lvbl9zdGF0ZSI6IjE2NGQxODk3LThhNmQtNGE0NS05NzUyLWUxZDcwN2IyZDQwMiIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiUk9MRV9VU0VSIiwib2ZmbGluZV9hY2Nlc3MiLCJST0xFX0FETUlOIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiRmF0b3UgQmluZXRvdSBOaWFuZyIsInByZWZlcnJlZF91c2VybmFtZSI6ImJpYmljaGUiLCJnaXZlbl9uYW1lIjoiRmF0b3UgQmluZXRvdSIsImZhbWlseV9uYW1lIjoiTmlhbmciLCJlbWFpbCI6ImZhYmluaW5pYW5nQGdtYWlsLmNvbSJ9.JoZ3PmF3ivMmABD3YnRzVy_Ut5mZz08gaw6HKkpZxFOlsEOJnGWFdNhD9ykr16ydWiOZFoPwGEZu6Da8KU4pxrmXAs4KYXF8KFz1Lnwl8MQtQRQltOMGVJNmnJJ2JcooIeXwEZQopTkSKJ5RfqKdCHVPqmQai_5hMPn0WTO_nfj9Gf56xbnydd_eijllaGG80gUZ3Ez0BU_zfiUjFYTuNNXrL4KamxtZPDRkk6D4-_xAi7gvqoymYFyuoFBwL8ici-G8m_hMgeZK5Gj89cpjNoB50OSdgP4_IJuIO_1XXZPRJKZGnhEI1SxMRJK96p9kbAzghfiL-2qsQqNH6-Frmw";
    DatatypeFactory datatypeFactory;

    {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    GregorianCalendar calendarExpected = new GregorianCalendar(2018, 6, 28);
    XMLGregorianCalendar expectedXMLGregorianCalendar = datatypeFactory
        .newXMLGregorianCalendar(calendarExpected);

    LocalDate localDate = LocalDate.of(2019, 4, 25);

        XMLGregorianCalendar xmlGregorianCalendar =
            datatypeFactory.newXMLGregorianCalendar(localDate.toString());


    @PayloadRoot(namespace = "http://ws.groupeisi.com",localPart = "getInfoRequest")
    public @ResponsePayload
    GetInfoResponse getInfoRequest (@RequestPayload GetInfoRequest request) {
        GetInfoResponse response= new GetInfoResponse();
        response.setOutput("Bonjour M2GL "+ request.getInput());
        return response;
    }

    @PayloadRoot(namespace = "http://ws.groupeisi.com",localPart = "getCovid19InfoRequest")
    public @ResponsePayload GetCovid19InfoResponses getCovid19InfoRequest (@RequestPayload GetCovid19InfoRequest request) throws DatatypeConfigurationException, JAXBException {
        GetCovid19InfoResponse covidresponse2= new GetCovid19InfoResponse();

        List<GetCovid19InfoResponse> covidresponse= new ArrayList<GetCovid19InfoResponse>();
        GetCovid19InfoResponses getCovid19InfoResponses = new GetCovid19InfoResponses();
        getCovid19InfoResponses.setGetCovid19InfoResponseList(new ArrayList<GetCovid19InfoResponse>());

        long bb = Long.parseLong(request.getInput());
        System.out.println("Input");
        System.out.println(bb);
        //Sencovid sencovid = sencovidRepository.getCovidInfoById((long)11);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+accessToken);
        HttpEntity<Sencovid[]> entity = new HttpEntity<Sencovid[]>(headers);

        ResponseEntity<Sencovid[]> response = restTemplate.exchange(url, //
            HttpMethod.GET, entity, Sencovid[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);

        // Status Code: 200
        if (statusCode == HttpStatus.OK) {
            // Response Body Data
            Sencovid[] list = response.getBody();

            if (list != null) {
                for (Sencovid e : list) {
                    System.out.println("Covid: " + e.getNbrtest() + " - " + e.getImportedCase());
                    //getCovid19InfoResponses.getCovid19InfoResponseList().add(e);
                    covidresponse2.setNbrtest(e.getNbrtest());
                    covidresponse2.setImportedCase(e.getImportedCase());
                    covidresponse2.setPostifcase(e.getPostifcase());
                    covidresponse2.setDeath(e.getDeath());
                    covidresponse2.setRecovered(e.getRecovered());
                    getCovid19InfoResponses.getCovid19InfoResponseList().add(covidresponse2);
                    covidresponse2 = new GetCovid19InfoResponse();
                    /*for(GetCovid19InfoResponse covid: covidresponse){
                        getCovid19InfoResponses.getCovid19InfoResponseList().add(covidresponse2);
                    }*/
                    /*XMLGregorianCalendar dateFormat =
                        datatypeFactory.newXMLGregorianCalendar(e.getDate().toString());
                    covidresponse2.setNbrtest(e.getNbrtest());
                    covidresponse2.setImportedCase(e.getImportedCase());
                    covidresponse2.setPostifcase(e.getPostifcase());
                    covidresponse2.setDeath(e.getDeath());
                    covidresponse2.setRecovered(e.getRecovered());
                    covidresponse2.setDate(dateFormat);*/
                }


            }


        }

        /*JAXBContext jaxbContext = JAXBContext.newInstance(GetCovid19InfoResponses.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(getCovid19InfoResponses, System.out);*/
        //jaxbMarshaller.

        return getCovid19InfoResponses;
    }

    /*public Date StringToDate(String s){

        Date result = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result  = dateFormat.parse(s);
        }

        catch(ParseException e){
            e.printStackTrace();

        }
        return result ;
    }*/
}
