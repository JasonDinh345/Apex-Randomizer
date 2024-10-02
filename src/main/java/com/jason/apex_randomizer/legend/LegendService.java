package com.jason.apex_randomizer.legend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LegendService {

	private LegendRepository myRepository;

	@Autowired
	public LegendService(LegendRepository theRepository){
		myRepository = theRepository;
	}
	public List<Legend> getLegends(){
		return myRepository.findAll();
	}
	public void addNewLegend(Legend theLegend) {
		Optional<Legend> legendOptional = myRepository.findLegendByName(theLegend.getName());
		if(legendOptional.isPresent()){
			throw new IllegalStateException("Legend Already Exists");
		}
		myRepository.save(theLegend);
	}
}
