package com.example.learnspring.infrastructure.repositories.ecom.report;


import com.example.learnspring.infrastructure.repositories.ecom.report.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, String> {
    List<Package> findAllByOrderInAndCurStationIdAndPackageStatusId(List<Long> order, Integer curStationId, Integer packageStatusId);
    List<Package> findAllByPackageStatusIdGreaterThanAndCurBagOrderNotNullAndCurStationIdIn(Integer packageStatusId, Collection<Integer> curStationId);

}
