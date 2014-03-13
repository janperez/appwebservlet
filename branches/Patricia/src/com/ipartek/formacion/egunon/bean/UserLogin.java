package com.ipartek.formacion.egunon.bean;

import java.util.Date;

//import com.ipartek.formacion.egunon.listener.SessionListener;

/**
 * Usuario de logeo para la AppWeb
 * 
 * @author Patricia Navascu�s
 * @version 1.0
 * 
 *          Atributos
 *          <ol>
 *          <li>nombre: nombre del usuario</li>
 *          <li>password: pass del usuario</li>
 *          <li>anteriorConexion: Tiempo de la ultima conexi�n del usuario a la
 *          AppWeb en la BBDD</li>
 *          <li>conexion: Tiempo de conexi�n actual al logearse a la AppWeb</li>
 *          </ol>
 * 
 * 
 */
public class UserLogin {

	private String id; // identificador session creado por Tomcat
	private int maxInactiveInterval; // Tiempo de expiraci�n en segundos

	private String nombre;
	private String password; // dni

	private long anteriorConexion;// LastAccessTime
	private long conexion; // CreationTime

	public UserLogin(String nombre, String password) {
		super();
		this.id = id;
		this.maxInactiveInterval = maxInactiveInterval;

		this.nombre = nombre;
		this.password = password;

		this.anteriorConexion = anteriorConexion;
		this.conexion =conexion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAnteriorConexion() {
		return anteriorConexion;
	}

	public void setAnteriorConexion(long anteriorConexion) {
		this.anteriorConexion = anteriorConexion;
	}

	public long getConexion() {
		return conexion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

	public void setConexion(long conexion) {
		this.conexion = conexion;
	}

	/**
	 * M�todo que calcula el tiempo que queda hasta que expire la sesion
	 * 
	 * @return tiempo en segundo que quedan para que expire la sesi�n
	 */
	public long getExpireTime() {
		long tiempoExpiracion = 0;

		long tiempoMaximo = this.getConexion() + this.getMaxInactiveInterval();
		long tiempoConectado = this.getConectedTime();
		tiempoExpiracion = tiempoMaximo - tiempoConectado;

		return tiempoExpiracion;
	}

	/**
	 * M�todo que calcula el n�emro de segundo que lleva conectado el usuario
	 * 
	 * @return segundos que lleva conectado el usuario
	 */
	public long getConectedTime() {
		long tiempoLogeado = 0;

		long actual = new Date().getTime();
		tiempoLogeado = actual - this.getConexion();

		return tiempoLogeado;
	}

	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password + ", anteriorConexion=" + anteriorConexion + ", conexion=" + conexion + "]";
	}

}
