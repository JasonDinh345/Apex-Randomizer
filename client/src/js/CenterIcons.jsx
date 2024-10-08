import PropTypes from "prop-types";
export default function CenterIcons({loadout}){
    
    return(
        <div className="randomizerContainer-icons">
            {loadout.legend.imageURL  &&
                <>
                <img src={loadout.legend.legendClass.imageURL}/>
                <img src={loadout.weapon1.ammo.imageURL}/>
                <img src={loadout.weapon2.ammo.imageURL}/>
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