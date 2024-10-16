import PropTypes from 'prop-types'
import "../../css/SettingsPanel.css"
import Setting from './Setting';
import { SettingsDispatchContext } from '../RandomizerContainer'
import { useContext, useState, useEffect } from 'react';
export default function SettingsPanel({settings, isVisible, handleChange}){
    const settingsDispatch = useContext(SettingsDispatchContext);
    const [screenHeight, setScreenHeight] = useState(document.documentElement.scrollHeight);

    useEffect(() => {

      const handleResize = () => {
        setScreenHeight(document.documentElement.scrollHeight);
      };
  
    
      window.addEventListener('resize', handleResize);
      window.addEventListener('scroll', handleResize);
  
    
      return () => {
        window.removeEventListener('resize', handleResize);
      window.removeEventListener('scroll', handleResize);
      };
    }, []);
    return(
        <>
        <div className='darkBG' style={isVisible ?({display: "flex", height:`${screenHeight}px`}):({display: "none"})}>
            <div className='settingsPanel'>
                <h1 className='X-out' onClick={handleChange}>X</h1>
                <div className='settingsContainer'>
                    <h1>Legend Settings</h1>
                    {settings.legendSettings.map((setting, i) =>
                        <Setting key={i+1} setting={setting} handleChange={()=>settingsDispatch({type:"handleChange-legend", payload:{label: setting.label}})}/>
                    )}
                </div>
                <div className='settingsContainer'>
                    <h1>Weapon Settings</h1>
                    {settings.weaponSettings.map((setting, i) =>
                        <Setting key={i+1} setting={setting} handleChange={()=>settingsDispatch({type:"handleChange-weapon", payload:{label: setting.label}})}/>
                    )}
                </div>
            </div>
        </div>
        </>
    )
}
SettingsPanel.propTypes = {
    settings: PropTypes.shape({
        legendSettings: PropTypes.arrayOf(
            PropTypes.shape({
                label: PropTypes.string,
                condition: PropTypes.func,
                checked: PropTypes.bool
            })
        ),
        weaponSettings: PropTypes.arrayOf(
            PropTypes.shape({
                label: PropTypes.string,
                condition: PropTypes.func,
                checked: PropTypes.bool
            })
        ),
    }),
    isVisible: PropTypes.bool,
    handleChange: PropTypes.func
    
};