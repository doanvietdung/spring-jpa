package com.example.learnspring.application.services;

import com.example.learnspring.infrastructure.repositories.ecom.report.PackageRepository;
import com.example.learnspring.infrastructure.repositories.ecom.report.entity.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void exampleJpa() {

        // TODO Defining Query Methods
        // By deriving the query from the method name directly.
        // Spring boot nó sẽ tự phân tích tên hàm để tự binding ra các câu where
        // + Query lấy nhiều bản ghi theo pagination và sort
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("order").descending());
//        Page<Package> packagesPagination = this.packageRepository.findAll(pageable);
//        packagesPagination.toList().forEach(aPackage -> System.out.println(aPackage.getOrder()));

        //TODO  use filter
//        List<Package> packages = this.packageRepository.findAllByOrderInAndCurStationIdAndPackageStatusId(Arrays.asList(2008286858L, 2005539574L, 2004185825L, 2005541748L, 1717220608L, 1547315476L, 1596608958L, 1664454718L, 2009339358L), 358, 3);
//        packages.forEach(aPackage -> System.out.println(aPackage.getOrder()));


        // TODO query theo example
//        Package example = new Package();
//        example.setId("026b6d0b-406a-43e0-9b6c-aaee3969a350");
//        Example<Package> packageExample = Example.of(example);
//
//        Optional<Package> actual = this.packageRepository.findOne(packageExample);
//        if (actual.isPresent()) {
//            System.out.println(actual.get().getOrder());
//        } else {
//            System.out.println("fall");
//        }
    }
}
