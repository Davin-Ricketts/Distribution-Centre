package com.cpan228.distributioncentre.repository;

import com.cpan228.distributioncentre.model.DistributionCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionCentreRepository extends JpaRepository<DistributionCentre, Long> {

}
