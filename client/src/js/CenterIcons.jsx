import PropTypes from "prop-types";
import "../css/CenterIcons.css"
import IconImage from "./IconImage";
export default function CenterIcons({loadout}){
    
    return(
        <div className="randomizerContainer-icons">
            {loadout.legend.imageURL  &&
                <>
                <IconImage imageURL={loadout.weapon1.ammo.imageURL}/>
                <IconImage imageURL={loadout.legend.legendClass.imageURL}/>
                <IconImage imageURL={loadout.weapon2.ammo.imageURL}/>
                </>
            }
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
            })
        }),
        weapon2: PropTypes.shape({
            name : PropTypes.string,
            imageURL: PropTypes.string,
            ammo : PropTypes.shape({
                name: PropTypes.string,
                imageURL: PropTypes.string,
                mythicImageURL: PropTypes.string
            })
        })
    })
        
    
    
}