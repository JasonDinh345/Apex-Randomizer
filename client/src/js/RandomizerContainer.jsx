import LegendInfo from "./LegendInfo";
import WeaponInfo from "./WeaponInfo";
import { useState, useEffect, useReducer, createContext, useMemo } from "react";
import "../css/RandomizerContainer.css"
import axios from 'axios'
import CenterIcons from "./CenterIcons";
import SettingsPanel from "./SettingsPanel";

export const DispatchContext = createContext();
export default function RandomizerContainer(){
    const [legends, setLegends] = useState([]);
    const [weapons, setWeapons] = useState([])
    const [ammo, setAmmo] = useState([]);
    const [legendClasses, setLegendClasses] = useState([])
    const [isLoading, setLoading] = useState(false)
    const [prevLoadout, setPrevLoadout] = useState({legend: {}, weapon1: {}, weapon2: {}})
    const settingsReducer = (settings, action) => {
        switch(action.type){
            default:
                return settings
        }
    }
    const [settings, settingsDispatch] = useReducer(settingsReducer, {
        legendSettings : [
            {label: "Only Controllers", 
            condition: function (legend){
                return legend.legendClass.name === "Controller"
            },
            checked: false
        }],
        weaponSettings : {}
    })
    const getRandomLegend = ()=>{
        return filteredLegends[Math.floor(Math.random() * filteredLegends.length)]
    }
    const getRandomWeapon = ()=>{
        return weapons[Math.floor(Math.random() * weapons.length)]
    }
    const filteredLegends = useMemo(()=>{

        return legends.filter(legend => {
            return settings.legendSettings.every(setting => !setting.checked || setting.condition(legend));
        })
    },[settings, legends])
    console.log(filteredLegends)
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
                    img.onload = resolve; 
                    img.onerror = resolve; 
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
        }
        return () => {
            isMounted = false;
        }
      }, [legends, weapons,ammo, legendClasses]);

    const loadoutReducer = (loadout, action) =>{
        switch(action.type){
            case "generate-all":
                setPrevLoadout(loadout);
                return {legend: getRandomLegend(), 
                        weapon1: getRandomWeapon(),
                        weapon2: getRandomWeapon()}
            case "change-legend":
                setPrevLoadout(loadout);
                return{...loadout, legend: getRandomLegend()}
            case "change-weapon1":
                setPrevLoadout(loadout);
                return{...loadout, weapon1: getRandomWeapon()}
            case "change-weapon2":
                setPrevLoadout(loadout);
                return{...loadout, weapon2: getRandomWeapon()}
            default: 
                return loadout
        }
    }
    const [loadout, loadoutDispatch] = useReducer(loadoutReducer, {legend: {}, weapon1: {}, weapon2: {}})
   
   
    return(
        <>
            <>
              <div id="randomizerContainer">
                {isLoading  ? (
                    <p>loading</p>
                ):(
                    <>
                   <DispatchContext.Provider value={loadoutDispatch}>
                    <button disabled={isLoading} className="randomizerContainer-button"onClick={()=> loadoutDispatch({type: "generate-all"})}>Generate Loadout</button>
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
                   </DispatchContext.Provider>
                   <SettingsPanel/>
                   </>
                )}
            </div>
            </>
        </>
    )
}