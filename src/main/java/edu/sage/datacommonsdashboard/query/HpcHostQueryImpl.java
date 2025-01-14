package edu.sage.datacommonsdashboard.query;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HpcHostQueryImpl implements HpcHostQuery {

    private final HpcHostRepository repository;
    private final HpcHostTransformer transformer;

    public HpcHostQueryImpl(HpcHostRepository repository, HpcHostTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public List<HpcHostModel> getHpcHosts() {
        return repository.getAll().stream()
                .map(transformer::transform)
                .collect(Collectors.toList());
    }
}
