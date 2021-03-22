package com.mycompany.myapp.wsdl;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "getCovid19InfoResponses")
@XmlAccessorType (XmlAccessType.FIELD)
public class GetCovid19InfoResponses {

    @XmlElement(name = "getCovid19InfoResponse")
    private List<GetCovid19InfoResponse> getCovid19InfoResponseList = null;

    public List<GetCovid19InfoResponse> getCovid19InfoResponseList() {
        return getCovid19InfoResponseList;
    }

    public void setGetCovid19InfoResponseList(List<GetCovid19InfoResponse> getCovid19InfoResponseList) {
        this.getCovid19InfoResponseList = getCovid19InfoResponseList;
    }
}
