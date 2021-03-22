package sn.isi.m2gl.web.rest;

import sn.isi.m2gl.GesCovid19App;
import sn.isi.m2gl.config.TestSecurityConfiguration;
import sn.isi.m2gl.domain.Sencovid;
import sn.isi.m2gl.repository.SencovidRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SencovidResource} REST controller.
 */
@SpringBootTest(classes = { GesCovid19App.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SencovidResourceIT {

    private static final String DEFAULT_NBRTEST = "AAAAAAAAAA";
    private static final String UPDATED_NBRTEST = "BBBBBBBBBB";

    private static final String DEFAULT_POSTIFCASE = "AAAAAAAAAA";
    private static final String UPDATED_POSTIFCASE = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORTED_CASE = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTED_CASE = "BBBBBBBBBB";

    private static final String DEFAULT_DEATH = "AAAAAAAAAA";
    private static final String UPDATED_DEATH = "BBBBBBBBBB";

    private static final String DEFAULT_RECOVERED = "AAAAAAAAAA";
    private static final String UPDATED_RECOVERED = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SencovidRepository sencovidRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSencovidMockMvc;

    private Sencovid sencovid;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sencovid createEntity(EntityManager em) {
        Sencovid sencovid = new Sencovid()
            .nbrtest(DEFAULT_NBRTEST)
            .postifcase(DEFAULT_POSTIFCASE)
            .importedCase(DEFAULT_IMPORTED_CASE)
            .death(DEFAULT_DEATH)
            .recovered(DEFAULT_RECOVERED)
            .date(DEFAULT_DATE);
        return sencovid;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sencovid createUpdatedEntity(EntityManager em) {
        Sencovid sencovid = new Sencovid()
            .nbrtest(UPDATED_NBRTEST)
            .postifcase(UPDATED_POSTIFCASE)
            .importedCase(UPDATED_IMPORTED_CASE)
            .death(UPDATED_DEATH)
            .recovered(UPDATED_RECOVERED)
            .date(UPDATED_DATE);
        return sencovid;
    }

    @BeforeEach
    public void initTest() {
        sencovid = createEntity(em);
    }

    @Test
    @Transactional
    public void createSencovid() throws Exception {
        int databaseSizeBeforeCreate = sencovidRepository.findAll().size();
        // Create the Sencovid
        restSencovidMockMvc.perform(post("/api/sencovids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sencovid)))
            .andExpect(status().isCreated());

        // Validate the Sencovid in the database
        List<Sencovid> sencovidList = sencovidRepository.findAll();
        assertThat(sencovidList).hasSize(databaseSizeBeforeCreate + 1);
        Sencovid testSencovid = sencovidList.get(sencovidList.size() - 1);
        assertThat(testSencovid.getNbrtest()).isEqualTo(DEFAULT_NBRTEST);
        assertThat(testSencovid.getPostifcase()).isEqualTo(DEFAULT_POSTIFCASE);
        assertThat(testSencovid.getImportedCase()).isEqualTo(DEFAULT_IMPORTED_CASE);
        assertThat(testSencovid.getDeath()).isEqualTo(DEFAULT_DEATH);
        assertThat(testSencovid.getRecovered()).isEqualTo(DEFAULT_RECOVERED);
        assertThat(testSencovid.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createSencovidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sencovidRepository.findAll().size();

        // Create the Sencovid with an existing ID
        sencovid.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSencovidMockMvc.perform(post("/api/sencovids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sencovid)))
            .andExpect(status().isBadRequest());

        // Validate the Sencovid in the database
        List<Sencovid> sencovidList = sencovidRepository.findAll();
        assertThat(sencovidList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSencovids() throws Exception {
        // Initialize the database
        sencovidRepository.saveAndFlush(sencovid);

        // Get all the sencovidList
        restSencovidMockMvc.perform(get("/api/sencovids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sencovid.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbrtest").value(hasItem(DEFAULT_NBRTEST)))
            .andExpect(jsonPath("$.[*].postifcase").value(hasItem(DEFAULT_POSTIFCASE)))
            .andExpect(jsonPath("$.[*].importedCase").value(hasItem(DEFAULT_IMPORTED_CASE)))
            .andExpect(jsonPath("$.[*].death").value(hasItem(DEFAULT_DEATH)))
            .andExpect(jsonPath("$.[*].recovered").value(hasItem(DEFAULT_RECOVERED)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getSencovid() throws Exception {
        // Initialize the database
        sencovidRepository.saveAndFlush(sencovid);

        // Get the sencovid
        restSencovidMockMvc.perform(get("/api/sencovids/{id}", sencovid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sencovid.getId().intValue()))
            .andExpect(jsonPath("$.nbrtest").value(DEFAULT_NBRTEST))
            .andExpect(jsonPath("$.postifcase").value(DEFAULT_POSTIFCASE))
            .andExpect(jsonPath("$.importedCase").value(DEFAULT_IMPORTED_CASE))
            .andExpect(jsonPath("$.death").value(DEFAULT_DEATH))
            .andExpect(jsonPath("$.recovered").value(DEFAULT_RECOVERED))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSencovid() throws Exception {
        // Get the sencovid
        restSencovidMockMvc.perform(get("/api/sencovids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSencovid() throws Exception {
        // Initialize the database
        sencovidRepository.saveAndFlush(sencovid);

        int databaseSizeBeforeUpdate = sencovidRepository.findAll().size();

        // Update the sencovid
        Sencovid updatedSencovid = sencovidRepository.findById(sencovid.getId()).get();
        // Disconnect from session so that the updates on updatedSencovid are not directly saved in db
        em.detach(updatedSencovid);
        updatedSencovid
            .nbrtest(UPDATED_NBRTEST)
            .postifcase(UPDATED_POSTIFCASE)
            .importedCase(UPDATED_IMPORTED_CASE)
            .death(UPDATED_DEATH)
            .recovered(UPDATED_RECOVERED)
            .date(UPDATED_DATE);

        restSencovidMockMvc.perform(put("/api/sencovids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSencovid)))
            .andExpect(status().isOk());

        // Validate the Sencovid in the database
        List<Sencovid> sencovidList = sencovidRepository.findAll();
        assertThat(sencovidList).hasSize(databaseSizeBeforeUpdate);
        Sencovid testSencovid = sencovidList.get(sencovidList.size() - 1);
        assertThat(testSencovid.getNbrtest()).isEqualTo(UPDATED_NBRTEST);
        assertThat(testSencovid.getPostifcase()).isEqualTo(UPDATED_POSTIFCASE);
        assertThat(testSencovid.getImportedCase()).isEqualTo(UPDATED_IMPORTED_CASE);
        assertThat(testSencovid.getDeath()).isEqualTo(UPDATED_DEATH);
        assertThat(testSencovid.getRecovered()).isEqualTo(UPDATED_RECOVERED);
        assertThat(testSencovid.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSencovid() throws Exception {
        int databaseSizeBeforeUpdate = sencovidRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSencovidMockMvc.perform(put("/api/sencovids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sencovid)))
            .andExpect(status().isBadRequest());

        // Validate the Sencovid in the database
        List<Sencovid> sencovidList = sencovidRepository.findAll();
        assertThat(sencovidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSencovid() throws Exception {
        // Initialize the database
        sencovidRepository.saveAndFlush(sencovid);

        int databaseSizeBeforeDelete = sencovidRepository.findAll().size();

        // Delete the sencovid
        restSencovidMockMvc.perform(delete("/api/sencovids/{id}", sencovid.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sencovid> sencovidList = sencovidRepository.findAll();
        assertThat(sencovidList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
