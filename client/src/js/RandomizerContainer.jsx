import LegendInfo from "./LegendInfo";
import WeaponInfo from "./WeaponInfo";
import { useState, useEffect, useReducer, createContext, useMemo, lazy,  Suspense } from "react";
import "../css/RandomizerContainer.css"

import CenterIcons from "./CenterIcons";
const SettingsPanel = lazy(()=>import("./SettingsPanel"))

import fetchData from "./misc/fetchData";
import { defaultSettings } from "./misc/defaultSettings";

export const SettingsDispatchContext = createContext();

export default function RandomizerContainer(){
    const [legends, setLegends] = useState([]);
    const [weapons, setWeapons] = useState([])
    const [ammo, setAmmo] = useState([]);
    const [legendClasses, setLegendClasses] = useState([])
    const [isLoading, setLoading] = useState(false)
    const [prevLoadout, setPrevLoadout] = useState({legend: {}, weapon1: {}, weapon2: {}})
    const [isSettingsVisible, setSettingsVisible] = useState(false)
    const settingsReducer = (settings, action) => {
        switch(settings, action.type){
            case("handleChange-legend"):
            return {...settings, legendSettings: settings.legendSettings.map(setting =>{
                if(setting.label === action.payload.label){
                    return {...setting, checked: !setting.checked}
                }
                return setting
            })}
            case("handleChange-weapon"):
                return {...settings, weaponSettings: settings.weaponSettings.map(setting =>{
                    if(setting.label === action.payload.label){
                        return {...setting, checked: !setting.checked}
                    }
                    return setting
                })}
            default:
                return settings
        }
    }
    
    const [settings, settingsDispatch] = useReducer(settingsReducer, defaultSettings)
    const dupeSetting = settings.weaponSettings.filter(setting => setting.label.includes("Duplicate"))

    
 
    const filteredLegends = useMemo(()=>{

        return legends.filter(legend => {
            return settings.legendSettings.every(setting => !setting.checked || setting.condition(legend, prevLoadout.legend));
        })
    },[settings, legends,prevLoadout])

    const filteredWeapons = useMemo(()=>{
        console.log(prevLoadout)
        return weapons.filter(weapon => {
            return settings.weaponSettings.every(setting => {
              
                if (setting.label.includes("Duplicate") || !setting.checked) {
                  return true;
                }
              
                return setting.condition(weapon, prevLoadout);
              });
        })
    },[settings, weapons ,prevLoadout])
  
    const getRandomLegend = ()=>{
        return filteredLegends[Math.floor(Math.random() * filteredLegends.length)]
    }
    const getRandomWeapon = ()=>{
        return filteredWeapons[Math.floor(Math.random() * filteredWeapons.length )]
    }
  

   
    useEffect(()=>{
        let isMounted = true;
        if(isMounted){
            async function getData() {
                setLegends(await fetchData(import.meta.env.VITE_SERVER_URL_LEGENDS))
                setWeapons(await fetchData(import.meta.env.VITE_SERVER_URL_WEAPONS))
                setAmmo(await fetchData(import.meta.env.VITE_SERVER_URL_AMMO))
                setLegendClasses(await fetchData(import.meta.env.VITE_SERVER_URL_LEGEND_CLASSES))
            }
            getData();
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
    const loadoutReducer = (loadout, action) => {
        setPrevLoadout(loadout)
        let weapon1 = getRandomWeapon();
        let weapon2 = getRandomWeapon();
        let legend = getRandomLegend();
        switch(loadout, action.type){
            case("generate-all"):
                while(dupeSetting.some(setting => {
                    if(setting.checked){
                        return setting.condition(weapon1, weapon2)
                    }
                    return false
                })){
                    weapon1  = getRandomWeapon()
                }
                return {legend: legend, weapon1: weapon1, weapon2: weapon2}
            case("generate-legend"):
                return {...loadout, legend: legend}
            case("generate-weapon"):
            if(action.payload.position === 1){
              
                while(dupeSetting.some(setting => {
                    if(setting.checked){
                        return setting.condition(weapon1, loadout.weapon2)
                    }
                    return false
                })){
                    weapon1  = getRandomWeapon()
                }
                return {...loadout, weapon1: weapon1}
            }else{
                while(dupeSetting.some(setting => {
                    if(setting.checked){
                        return setting.condition(weapon2, loadout.weapon1)
                    }
                    return false
                })){
                    weapon2  = getRandomWeapon()
                }
                return {...loadout, weapon2: weapon2}
            }
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
                   
                    <div className="randomizerContainer-buttons">
                        <button disabled={isLoading} className="loadoutButton"onClick={()=>loadoutDispatch({type: "generate-all"})}>Generate Loadout</button>
                        <button className="settingsButton" onClick={()=>setSettingsVisible(!isSettingsVisible)}><img src="gear.png"/></button>
                    </div>
                     <div className="randomizerContainer-main">
                        <div className="randomizerContainer-legend">
                            <LegendInfo legend={loadout.legend} loadoutDispatch={loadoutDispatch}/>
                        </div>
                        <CenterIcons loadout={loadout}/>
                         <div className="randomizerContainer-weapons">
                            <WeaponInfo weapon={loadout.weapon1} position={1} loadoutDispatch={loadoutDispatch}/>
                            <WeaponInfo weapon={loadout.weapon2} position={2} loadoutDispatch={loadoutDispatch}/>
                        </div>
                    </div>
                    
                    <Suspense>
                        {isSettingsVisible && (
                            <SettingsDispatchContext.Provider value={settingsDispatch}>
                                <SettingsPanel settings={settings} isVisible={isSettingsVisible} handleChange={()=>setSettingsVisible(!isSettingsVisible)}/>
                            </SettingsDispatchContext.Provider>
                        )}
                    </Suspense>
                   
                   </>
                )}
            </div>
            </>
        </>
    )
    
}
