
import LegendImage from "./LegendImage";
import PropTypes from "prop-types";
import {useRef} from 'react'
import "../css/LegendInfo.css"

export default function LegendInfo({legend, loadoutDispatch}){

    const buttonRef = useRef(null)
    const handleClick = () =>{
        setTimeout(()=>{
            buttonRef.current.blur()
        }, 500)
        loadoutDispatch({type:"generate-legend"});
       
    }
    return(
        <>
        <div className="legendContainer" id="legend">
            {legend && legend.name ? (
                <h1>{legend.name}</h1>
            ) : (
                <h1>???</h1>
            )}
            {legend ? (<LegendImage imageURL={legend.imageURL}/>) : ((<LegendImage imageURL={"mysterylegend.png"}/>))}
            
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
    }), 
    loadoutDispatch: PropTypes.func 
  };