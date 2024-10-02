package com.jason.apex_randomizer.legend;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/legends")
public class LegendController {
    private LegendService myService;

    @Autowired
    public LegendController(LegendService theService){
        myService = theService;
    }
	
    @GetMapping
    public List<Legend> getLegends(){
        return myService.getLegends();
    }

    @PostMapping
    public void addNewLegend(@RequestBody Legend theLegend){
        myService.addNewLegend(theLegend);
    }
}
