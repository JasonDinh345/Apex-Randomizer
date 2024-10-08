import LegendInfo from "./LegendInfo";
import WeaponInfo from "./WeaponInfo";
import { useState, useEffect, useReducer, createContext } from "react";
import "../css/RandomizerContainer.css"
import axios from 'axios'
import CenterIcons from "./CenterIcons";

export const DispatchContext = createContext();
export default function RandomizerContainer(){
    const [legends, setLegends] = useState([]);
    const [weapons, setWeapons] = useState([])
    const [ammo, setAmmo] = useState([]);
    const [legendClasses, setLegendClasses] = useState([])
    const [isLoading, setLoading] = useState(false)
    const getRandomLegend = ()=>{
        return legends[Math.floor(Math.random() * legends.length)]
    }
    const getRandomWeapon = ()=>{
        return weapons[Math.floor(Math.random() * weapons.length)]
    }
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
            axios.get(import.meta.env.VITE_SERVER_URL_AMMO).then(res =>
                setAmmo(res.data)
                ).catch(err =>{
                    console.log("There was an error fetching the ammo: " + err)
            })
            axios.get(import.meta.env.VITE_SERVER_URL_LEGEND_CLASSES).then(res =>
                setLegendClasses(res.data)
            ).catch(err => 
                console.log("There was an error fetching the legends: " + err)
            )
        }
        return () => {
            isMounted = false;
        }
    },[])
    useEffect(() => {
        let isMounted = true;
        setLoading(true)
        if(isMounted){
            
              
            const preloadImages = (urls) => {
                const promises = urls.map((url) => {
                  return new Promise((resolve) => {
                    const img = new Image();
                    img.src = url;
                    img.onload = resolve; // resolve the promise when the image loads
                    img.onerror = resolve; // resolve even if there's an error to avoid hanging
                  });
                });
            
                return Promise.all(promises);
              };
           
            const legendImageUrls = legends.map(legend => legend.imageURL);
            const weaponImageUrls = weapons.map(weapon => weapon.imageURL);
            const ammoImageUrls = ammo.flatMap(ammo => [ammo.imageURL, ammo.mythicImageURL])
            const legendClassesImageURL = legendClasses.map(legendClass => legendClass.imageURL)
            const allImageUrls = [...legendImageUrls, ...weaponImageUrls,...ammoImageUrls, ...legendClassesImageURL];
            preloadImages(allImageUrls).then(()=> setLoading(false));
            console.log(ammo)
        }
        return () => {
            isMounted = false;
        }
      }, [legends, weapons,ammo]);
    const loadoutReducer = (loadout, action) =>{
        switch(action.type){
            case "generate-all":
                return {legend: getRandomLegend(), 
                        weapon1: getRandomWeapon(),
                        weapon2: getRandomWeapon()}
            case "change-legend":
                return{...loadout, legend: getRandomLegend()}
            case "change-weapon1":
                return{...loadout, weapon1: getRandomWeapon()}
            case "change-weapon2":
                return{...loadout, weapon2: getRandomWeapon()}
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
            <>
              <div id="randomizerContainer">
                {isLoading  ? (
                    <p>loading</p>
                ):(
                   <DispatchContext.Provider value={loadoutDispatch}>
                     <div className="randomizerContainer-main">
                        <div className="randomizerContainer-legend">
                            <LegendInfo legend={loadout.legend}/>
                        </div>
                        <CenterIcons loadout={loadout}/>
                         <div className="randomizerContainer-weapons">
                            <WeaponInfo weapon={loadout.weapon1} position={1}/>
                            <WeaponInfo weapon={loadout.weapon2} position={2}/>
                        </div>
                    </div>
                
                    <button disabled={isLoading} className="randomizerContainer-button"onClick={handleClick}>Generate Loadout</button>
                   </DispatchContext.Provider>
                )}
            </div>
            </>
        </>
    )
}