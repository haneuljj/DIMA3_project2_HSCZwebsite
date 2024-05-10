package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.CountryChartDTO;
import net.kdigital.web_project.dto.YearChartDTO;
import net.kdigital.web_project.entity.CountryChartEntity;
import net.kdigital.web_project.entity.YearChartEntity;
import net.kdigital.web_project.repository.CountryChartRepository;
import net.kdigital.web_project.repository.YearChartRepository;

@Service

@RequiredArgsConstructor
public class ChartService {

    private final CountryChartRepository countryChartRepository;
    private final YearChartRepository yearChartRepository;

    /**
     * 입력받은 네자리 HS CODE에 따른 수출 상위 5개국 조회
     * @param hs4digit
     * @return
     */
    public List<CountryChartDTO> selectExportRanking(String hs4digit) {

        List<CountryChartDTO> dtoList = new ArrayList<>();
        List<CountryChartEntity> entityList = countryChartRepository.findTop5ExportByHs4digit(hs4digit);

        entityList.forEach((item) -> dtoList.add(CountryChartDTO.toDTO(item)));

        return dtoList;
    }

    /**
     * 입력받은 네자리 HS CODE에 따른 수입 상위 5개국 조회
     * @param hs4digit
     * @return
     */
    public List<CountryChartDTO> selectImportRanking(String hs4digit) {

        List<CountryChartDTO> dtoList = new ArrayList<>();
        List<CountryChartEntity> entityList = countryChartRepository.findTop5ImportByHs4digit(hs4digit);

        entityList.forEach((item) -> dtoList.add(CountryChartDTO.toDTO(item)));

        return dtoList;
    }

    /**
     * 입력받은 네자리 HS CODE에 따른 5년간 수출입 금액 조회
     * @param hs4digit
     * @return
     */
    public List<YearChartDTO> selectYearImex(String hs4digit) {
        List<YearChartDTO> dtoList = new ArrayList<>();
        List<YearChartEntity> entityList = yearChartRepository.findAllByHs4digit(hs4digit);

        entityList.forEach((item) -> dtoList.add(YearChartDTO.toDTO(item)));

        return dtoList;
    }

}
