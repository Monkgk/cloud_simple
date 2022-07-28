function ticket(film_id,session_id,cinema_id) {
	parent.location.href = "/cinema/"+cinema_id+"/"+film_id+"/session/"+session_id;
}