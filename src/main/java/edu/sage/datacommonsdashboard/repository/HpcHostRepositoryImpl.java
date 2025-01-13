package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.model.HpcHost;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HpcHostRepositoryImpl implements HpcHostRepository {

    private final List<HpcHost> hpcHosts;

    public HpcHostRepositoryImpl(List<HpcHost> hpcHosts) {
        this.hpcHosts = hpcHosts;
    }

    @Override
    public Optional<HpcHost> get(UUID id) {
        return hpcHosts.stream()
                .filter(hpcHost -> hpcHost.getId().equals(id))
                .findAny();
    }

    @Override
    public List<HpcHost> getAll() {
        return Collections.unmodifiableList(hpcHosts);
    }

    @Override
    public void add(HpcHost entity) {

    }

    @Override
    public void remove(HpcHost entity) {

    }
}
