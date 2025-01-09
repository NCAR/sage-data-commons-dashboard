package edu.sage.datacommonsdashboard.repository;

import edu.sage.datacommonsdashboard.model.HpcHost;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HpcHostRepository {

    Optional<HpcHost> get(UUID id);

    List<HpcHost> getAll();

    void add(HpcHost entity);

    void remove(HpcHost entity);
}
