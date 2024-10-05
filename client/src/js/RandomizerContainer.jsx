import LegendInfo from "./LegendInfo";
import WeaponInfo from "./WeaponInfo";
import { useState, useEffect, useReducer } from "react";
import "../css/RandomizerContainer.css"
import axios from 'axios'


export default function RandomizerContainer(){
    const [legends, setLegends] = useState([]);
    const [weapons, setWeapons] = useState([])
    const [isLoading, setLoading] = useState(false)
  
    useEffect(()=>{
        let isMounted = true;
        if(isMounted){
            axios.get(import.meta.env.VITE_SERVER_URL_LEGENDS).then(res =>
                setLegends(res.data)
            ).catch(err => 
                console.log("There was an error fetching the legends: " + err)
            )
    
            axios.get(import.meta.env.VITE_SERVER_URL_WEAPONS).then(res =>
                setWeapons(res.data)
            ).catch(err => 
                
                console.log("There was an error fetching the weapons: " + err)
            )
        }
        return () => {
            isMounted = false;
        }
    },[])
    useEffect(() => {
        setLoading(true)
        const preloadImages = (urls) => {
            console.log(urls)
          urls.forEach((url) => {
            const img = new Image();

            img.src = url;
          });
        };
        const legendImageUrls = legends.map(legend => legend.imageURL);
        const weaponImageUrls = weapons.map(weapon => weapon.imageURL);


        const allImageUrls = [...legendImageUrls, ...weaponImageUrls];
        preloadImages(allImageUrls);
        setLoading(false)
      }, []);
    const loadoutReducer = (loadout, action) =>{
        switch(action.type){
            case "generate-all":
                return {legend: legends[Math.floor(Math.random() * legends.length)], 
                        weapon1: weapons[Math.floor(Math.random() * weapons.length)],
                        weapon2: weapons[Math.floor(Math.random() * weapons.length)]}
            case "change-legend":
                return{...loadout, legend: action.payload.legend}
            case "change-weapon1":
                return{...loadout, weapon1: action.payload.weapon}
            case "change-weapon2":
                return{...loadout, weapon2: action.payload.weapon}
            default: 
                return loadout
        }
    }
    const [loadout, loadoutDispatch] = useReducer(loadoutReducer, {legend: {}, weapon1: {}, weapon2: {}})
    const handleClick = () =>{
        setLoading(true);
        loadoutDispatch({type: "generate-all"})
        setLoading(false)
    }
    return(
        <>
       
          {isLoading ? (<p>loading</p>):(
            <>
              <div id="randomizerContainer">
                <LegendInfo legend={loadout.legend}/>
                <WeaponInfo weapon={loadout.weapon1}/>
                <WeaponInfo weapon={loadout.weapon2}/>
                <button onClick={handleClick}>Generate Loadout</button>
            </div>
            </>
          )}
       
        
        </>
    )
}