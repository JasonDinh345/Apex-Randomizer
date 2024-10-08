import WeaponImage from "./WeaponImage";
import PropTypes from "prop-types";
import "../css/WeaponInfo.css"
import {useRef, useContext} from 'react'
import { DispatchContext } from "./RandomizerContainer";
export default function WeaponInfo({weapon, position}){
    const buttonRef = useRef(null)
    const loadoutDispatch = useContext(DispatchContext)
    const handleClick = ()=>{
        setTimeout(()=>{
            buttonRef.current.blur()
        }, 500)
        loadoutDispatch({type: "change-weapon" + position})
    }
    return(
        <>
       { weapon && 
       <>
        <div className="weaponContainer">
            <h1>{weapon.name}</h1>
            <WeaponImage imageURL={weapon.imageURL}/>
            <button ref={buttonRef}className="weapon-reroll"onClick={handleClick}  ><img src="reroll.png"/></button>
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
    }),
    position :PropTypes.number

}