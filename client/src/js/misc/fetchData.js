import axios from "axios"
export default async function fetchData(url){
    const res = await axios.get(url).catch(err=>{
        console.log("There was an error fetch from " + url + err)
    })
    
    return res.data;
}