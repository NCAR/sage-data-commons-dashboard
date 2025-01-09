package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HpcHostQuery {

    private final HpcHostRepository repository;
    private final HpcHostTransformer transformer;

    public HpcHostQuery(HpcHostRepository repository, HpcHostTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    public List<HpcHostModel> query() {
        return repository.getAll().stream()
                .map(transformer::transform)
                .collect(Collectors.toList());
    }
}
