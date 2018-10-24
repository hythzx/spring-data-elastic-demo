package com.aiyun.elastic.web.rest;

import com.aiyun.elastic.domain.Scenic;
import com.aiyun.elastic.repository.ScenicRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zhaoyuan
 */
@RestController
@RequestMapping("/api")
public class ElasticResource {


    private final ScenicRepository scenicRepository;

    public ElasticResource(ScenicRepository scenicRepository) {
        this.scenicRepository = scenicRepository;
    }

    @GetMapping("/scenic")
    public ResponseEntity<List<Scenic>> getScenicList(Pageable pageable) {
        Page<Scenic> page = scenicRepository.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping("/scenic")
    public ResponseEntity<Scenic> addScenic(@RequestBody Scenic scenic) {
        scenicRepository.save(scenic);
        return ResponseEntity.ok(scenic);
    }

    @GetMapping("/scenic/search")
    public ResponseEntity<List<Scenic>> search(String keyword, Pageable pageable) {
        Page<Scenic> scenicPage = scenicRepository.search(QueryBuilders.queryStringQuery(keyword), pageable);
        return ResponseEntity.ok(scenicPage.getContent());
    }
}
