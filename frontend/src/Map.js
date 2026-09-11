import { MapContainer } from 'react-leaflet/MapContainer'
import { TileLayer } from 'react-leaflet/TileLayer'
import {Marker} from 'react-leaflet/Marker'
import {Popup} from 'react-leaflet/Popup'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'leaflet/dist/leaflet.css';

import blueMarker from './markers/blueMarker'
function Map({markers}){
  //

return (
    <div style={{height:"600px",width:"600px"}}>
     <MapContainer center={[51.505, -0.09]}
        zoom={13}
        style={{ height: "100%", width: "100%" }}>
      <TileLayer
      attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
    />
    {markers.map((posting)=><Marker draggable={false} position={posting.location.coordinates} icon={blueMarker}> <Popup> Title: {posting.title} <br/>Notes: {posting.notes} <br/> Portions: {posting.portions} </Popup> </Marker>)}
    </MapContainer>
    </div>
);

}
export default Map;