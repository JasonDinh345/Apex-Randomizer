import PropTypes from 'prop-types'


export default function Setting({setting, handleChange}){
    
    return(
        <>
        <label>
            {setting.label}
            <input type= "checkbox" checked={setting.checked} onChange={handleChange}/>
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