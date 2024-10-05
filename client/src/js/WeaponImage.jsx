import PropTypes from "prop-types";
export default function WeaponImage({imageURL}){
    return(
        <>
        {imageURL &&  (<img rel="preload" loading="lazy"alt="" src={imageURL}/>)}
        </>
    )
}
WeaponImage.propTypes = {
    imageURL: PropTypes.string       
   
  };