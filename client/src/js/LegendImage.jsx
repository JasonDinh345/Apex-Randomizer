
import PropTypes from "prop-types";
export default function LegendImage({imageURL}){
    
    return(
        <>
        {imageURL ?  (<img rel="preload" loading="lazy"alt="" src={imageURL}/>):(
            <img src="mysterylegend.png"/>
        )}
        </>
    )
}
LegendImage.propTypes = {
    imageURL: PropTypes.string       
   
  };