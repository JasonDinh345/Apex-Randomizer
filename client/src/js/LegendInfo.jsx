
import LegendImage from "./LegendImage";
import PropTypes from "prop-types";
import {useRef, useContext} from 'react'
import "../css/LegendInfo.css"
import { DispatchContext } from "./RandomizerContainer";
export default function LegendInfo({legend}){
    const loadoutDispatch = useContext(DispatchContext);
    const buttonRef = useRef(null)
    const handleClick = () =>{
        setTimeout(()=>{
            buttonRef.current.blur()
        }, 500)
        loadoutDispatch({type: "change-legend"})
    }
    return(
        <>
        <div className="legendContainer" id="legend">
            <h1>{legend.name}</h1>
            <LegendImage imageURL={legend.imageURL}/>
        </div>
        <button ref={buttonRef}className="legend-reroll"onClick={handleClick}  ><img src="reroll.png"/></button>
        </>
    )
}
LegendInfo.propTypes = {
    legend: PropTypes.shape({
        name: PropTypes.string, 
        legendClass: PropTypes.shape({
            name:PropTypes.string,
            imageURL: PropTypes.string
        }),
        imageURL: PropTypes.string,            
    }).isRequired,  
  };