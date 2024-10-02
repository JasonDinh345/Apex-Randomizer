package com.jason.apex_randomizer.legend;

import java.util.List;


import org.springframework.stereotype.Service;


@Service
public class LegendService {

	public List<Legend> getLegends(){
		return List.of(
			new Legend("Bloodhound", "Recon"),
			new Legend("Caustic", "Controller")
		);
	}
}
