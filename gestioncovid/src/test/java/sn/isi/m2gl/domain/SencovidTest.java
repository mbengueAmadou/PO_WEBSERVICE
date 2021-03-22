package sn.isi.m2gl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import sn.isi.m2gl.web.rest.TestUtil;

public class SencovidTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sencovid.class);
        Sencovid sencovid1 = new Sencovid();
        sencovid1.setId(1L);
        Sencovid sencovid2 = new Sencovid();
        sencovid2.setId(sencovid1.getId());
        assertThat(sencovid1).isEqualTo(sencovid2);
        sencovid2.setId(2L);
        assertThat(sencovid1).isNotEqualTo(sencovid2);
        sencovid1.setId(null);
        assertThat(sencovid1).isNotEqualTo(sencovid2);
    }
}
