package sn.isi.m2gl.web.rest;

import sn.isi.m2gl.domain.Sencovid;
import sn.isi.m2gl.repository.SencovidRepository;
import sn.isi.m2gl.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.isi.m2gl.domain.Sencovid}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SencovidResource {

    private final Logger log = LoggerFactory.getLogger(SencovidResource.class);

    private static final String ENTITY_NAME = "gesCovid19Sencovid";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SencovidRepository sencovidRepository;

    public SencovidResource(SencovidRepository sencovidRepository) {
        this.sencovidRepository = sencovidRepository;
    }

    /**
     * {@code POST  /sencovids} : Create a new sencovid.
     *
     * @param sencovid the sencovid to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sencovid, or with status {@code 400 (Bad Request)} if the sencovid has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sencovids")
    public ResponseEntity<Sencovid> createSencovid(@RequestBody Sencovid sencovid) throws URISyntaxException {
        log.debug("REST request to save Sencovid : {}", sencovid);
        if (sencovid.getId() != null) {
            throw new BadRequestAlertException("A new sencovid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sencovid result = sencovidRepository.save(sencovid);
        return ResponseEntity.created(new URI("/api/sencovids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sencovids} : Updates an existing sencovid.
     *
     * @param sencovid the sencovid to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sencovid,
     * or with status {@code 400 (Bad Request)} if the sencovid is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sencovid couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sencovids")
    public ResponseEntity<Sencovid> updateSencovid(@RequestBody Sencovid sencovid) throws URISyntaxException {
        log.debug("REST request to update Sencovid : {}", sencovid);
        if (sencovid.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sencovid result = sencovidRepository.save(sencovid);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sencovid.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sencovids} : get all the sencovids.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sencovids in body.
     */
    @GetMapping("/sencovids")
    public List<Sencovid> getAllSencovids() {
        log.debug("REST request to get all Sencovids");
        return sencovidRepository.findAll();
    }

    /**
     * {@code GET  /sencovids/:id} : get the "id" sencovid.
     *
     * @param id the id of the sencovid to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sencovid, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sencovids/{id}")
    public ResponseEntity<Sencovid> getSencovid(@PathVariable Long id) {
        log.debug("REST request to get Sencovid : {}", id);
        Optional<Sencovid> sencovid = sencovidRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sencovid);
    }

    /**
     * {@code DELETE  /sencovids/:id} : delete the "id" sencovid.
     *
     * @param id the id of the sencovid to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sencovids/{id}")
    public ResponseEntity<Void> deleteSencovid(@PathVariable Long id) {
        log.debug("REST request to delete Sencovid : {}", id);
        sencovidRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
