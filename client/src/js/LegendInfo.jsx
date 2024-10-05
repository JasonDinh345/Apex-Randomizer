
import LegendImage from "./LegendImage";
import PropTypes from "prop-types";

import "../css/LegendInfo.css"
export default function LegendInfo({legend}){
    //const loadoutDispatch = useContext(DispatchContext);
    return(
        <>
        <div className="legendContainer">
            <h1>{legend.name}</h1>
            <LegendImage imageURL={legend.imageURL}/>
        </div>
        
        </>
    )
}
LegendInfo.propTypes = {
    legend: PropTypes.shape({
      name: PropTypes.string, 
      className: PropTypes.string,
      imageURL: PropTypes.string,           
    }).isRequired,  
  };