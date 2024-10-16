import PropTypes from 'prop-types'
import ToggleSwitch from '../component/ToggleSwtich'


export default function Setting({setting, handleChange}){
    
    return(
        <>
        <label>
            
            <ToggleSwitch onToggle={handleChange} isToggled={setting.checked}/>
            {setting.label}
        </label>
        </>
    )
}
Setting.propTypes = {
    setting :  PropTypes.shape({
        label: PropTypes.string,
        condition: PropTypes.func,
        checked: PropTypes.bool
    }),
    handleChange: PropTypes.func
}