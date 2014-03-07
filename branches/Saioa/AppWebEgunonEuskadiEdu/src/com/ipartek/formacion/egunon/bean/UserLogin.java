package com.ipartek.formacion.egunon.bean;
/**
 * Usuario de lofeo para la Appweb
 * 
 * @author Curso
 *Atributos
 *<ol>
 *<li>nombre: nombre del usuario</li>
 *<li>password: las pass del usuario</li>
 *<li>ultimaConexion: sera la ultima conexion del usuario a  la AppWeb en BBDD</li>
 *<li>conexion: sera conexion actual </li>
 *<li></li>
 *
 *</ol>
 */
public class UserLogin {
	private String nombre;
	private String password;
	private long ultimaConexion;
	private long conexion;
	
	public UserLogin(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
		
		//TODO crear tabla o usar cookie
		this.ultimaConexion=System.currentTimeMillis();
		this.conexion=System.currentTimeMillis();
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
	
	public long getUltimaConexion() {
		return ultimaConexion;
	}
	
	public void setUltimaConexion(long ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}
	
	public long getConexion() {
		return conexion;
	}
	public void setConexion(long conexion) {
		this.conexion = conexion;
	}
	
	@Override
	public String toString() {
		return "UserLogin [nombre=" + nombre + ", password=" + password
				+ ", ultimaConexion=" + ultimaConexion + ", conexion=" + conexion
				+ "]";
	}
}
