import PropTypes from "prop-types";
import "../../css/CenterIcons.css"
import IconImage from "./IconImage";
export default function CenterIcons({loadout}){
    
    return(
        <div className="randomizerContainer-icons">
            {loadout.weapon1 && loadout.weapon1.imageURL  && (
                <>
                {loadout.weapon1.isCarePackage ? (<IconImage imageURL={loadout.weapon1.ammo.mythicImageURL}/>):(<IconImage imageURL={loadout.weapon1.ammo.imageURL}/>)}
                
                </>
            )}
            {loadout.legend && loadout.legend.imageURL  && (
                <>
               
                <IconImage imageURL={loadout.legend.legendClass.imageURL}/>
               
                </>
            )}
            {loadout.weapon2 && loadout.weapon2.imageURL  && (
                <>
                
                {loadout.weapon2.isCarePackage ? (<IconImage imageURL={loadout.weapon2.ammo.mythicImageURL}/>):(<IconImage imageURL={loadout.weapon2.ammo.imageURL}/>)}
                </>
            )}
        </div>
    )
}
CenterIcons.propTypes = {
    loadout: PropTypes.shape({
        legend: PropTypes.shape({
            name: PropTypes.string, 
            legendClass: PropTypes.shape({
                name:PropTypes.string,
                imageURL: PropTypes.string
            }),
            imageURL: PropTypes.string,   
        }),
        weapon1 : PropTypes.shape({
            name : PropTypes.string,
            imageURL: PropTypes.string,
            ammo : PropTypes.shape({
                name: PropTypes.string,
                imageURL: PropTypes.string,
                mythicImageURL: PropTypes.string
            }),
            isCarePackage: PropTypes.bool
        }),
        weapon2: PropTypes.shape({
            name : PropTypes.string,
            imageURL: PropTypes.string,
            ammo : PropTypes.shape({
                name: PropTypes.string,
                imageURL: PropTypes.string,
                mythicImageURL: PropTypes.string
            }),
            isCarePackage: PropTypes.bool
        })
    })
        
    
    
}