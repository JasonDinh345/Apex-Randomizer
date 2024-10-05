import WeaponImage from "./WeaponImage";
import PropTypes from "prop-types";
import "../css/WeaponInfo.css"
export default function WeaponInfo({weapon}){
    return(
        <>
       { weapon && 
       <>
        <div className="weaponContianer">
            <h1>{weapon.name}</h1>
            <WeaponImage imageURL={weapon.imageURL}/>
        </div>
       </>
       }
        </>
    )
}
WeaponInfo.propTypes = {
    weapon : PropTypes.shape({
        name : PropTypes.string,
        imageURL: PropTypes.string,
        ammo : PropTypes.shape({
            name: PropTypes.string,
            imageURL: PropTypes.string,
            mythicImageURL: PropTypes.string
        })
    })
}