import "../../css/ToggleSwitch.css"
import PropTypes from 'prop-types'
export default function ToggleSwitch({isToggled, onToggle}){
   
    return(
        <>
        <label className="switch">
            <input type="checkbox" checked={isToggled} onChange={(onToggle)}></input>
            <span className="slider"></span>
        </label>
        </>
    )

}
ToggleSwitch.propTypes = {
    isToggled: PropTypes.bool,
    onToggle:  PropTypes.func
}