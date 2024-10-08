import PropTypes from 'prop-types'
export default function IconImage({imageURL}){
    return(
        <>
        <div className='iconImage-contianer'>
            <img src={imageURL}/>
        </div>
        </>
    )
}
IconImage.propTypes = {
    imageURL: PropTypes.string
}