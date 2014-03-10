package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.enumeraciones.TIPO_MENSAJE;
import com.ipartek.pruebas.bbdd.DAOCalificacion;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.enumeraciones.Tipo;
import com.ipartek.pruebas.exception.AlumnoException;
import com.ipartek.pruebas.exception.LibroException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	private final static Logger log = Logger.getLogger(AlumnoServlet.class);
	private Alumno nuevoAlumno;
	private Alumno modificadoAlumno;
	private ModeloAlumno modelo;

	public static final String OP_NUEVO_ALUMNO = "nuevo";
	public static final String OP_MODIFICAR_ALUMNO = "modificar";
	public static final String OP_ELIMINAR_ALUMNO = "eliminar";
	public static final String OP_DETALLE_ALUMNO = "detalle";
	public static final String OP_LISTAR_ALUMNO = "listar";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modelo = new ModeloAlumno();
	}

	@Override
	public void destroy() {
		super.destroy();
		modelo = null;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		String id = request.getParameter("id");
		// Lsitado de los alumnos.
		if (id == null || OP_ELIMINAR_ALUMNO.equalsIgnoreCase(request.getParameter("op"))) {
			log.trace("Listado Alumnos");
			// Obtener dispatcher
			// Se encarga de redireccionar
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");

			ArrayList<Alumno> lAlumnos = modelo.getAll();
			log.debug(lAlumnos.size() + " alumno consultados");
			// Env�ar datos en la request a la JSP
			request.setAttribute("listaAlumnos", lAlumnos);

			// Redireccionar a la jsp
			dispatcher.forward(request, response);
		} else {
			int idAlumno = Integer.parseInt(id);
			Alumno alumno = null;
			try {
				alumno = new Alumno();
			} catch (AlumnoException e) {

				e.printStackTrace();
			} catch (LibroException e) {

				e.printStackTrace();
			}
			if (idAlumno > 0) {
				log.trace("Detalle alumno " + id);
				// Detalle del alumno
				alumno = modelo.getAlumnoById(idAlumno);
				// Se cargan los datos del detalle del alumno
				request.setAttribute("detalleAlumno", alumno);

				request.setAttribute("titulo", "Detalle alumno | Egunon Euskadi");
				request.setAttribute("h1", "Listado del alumno " + alumno.getNombre());
				request.setAttribute("method", "post");
			} else {
				// crear el atributo titulo
				request.setAttribute("titulo", "Crear Alumno | Egunon Euskadi");
				request.setAttribute("h1", "Crear alumno");
				// idultimo+1
				alumno.setId(0);
				request.setAttribute("detalleAlumno", alumno);
			}
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		}

		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("doPost");
		// recoger operaci�n a realizar
		String op = (String) request.getParameter("op");
		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(op)) {
			crearAlumno(request, response);
		} else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			modificarAlumno(request, response);
		} else if (OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
			eliminarAlumno(request, response);
		} else {
			throw new ServletException("Operaci�n no soportada " + op);
		}

		log.trace("doPost - Fin");
	}

	/**
	 * M�todo para eliminar un alumno
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Funci�n eliminar alumno
		log.trace("Eliminar Alumno");
		// S�lo se necesita tener el id del alumno que se desea eliminar
		try {
			String id = (String) request.getParameter("id");

			// Crear alumno nuevoAlumno
			boolean borrado = modelo.delete(Integer.parseInt(id));
			if (borrado) {
				// Modificaci�n OK
				// Crear mensaje de todo correcto
				request.setAttribute("msg", new Mensaje("Alumno eliminado correctamente", "300", TIPO_MENSAJE.INFO));
				log.trace("Alumno [" + id + "] eliminado correctamente");
				// Obtener el id del alumno insertado
			} else {
				// Modificaci�n NO OK
				// Crear mensaje se ha producido un error al insertar en la
				// bbdd
				request.setAttribute("msg", new Mensaje("Se ha producido unu error al elimminar el alumno, por favor intentalo m�s tarde", "500",
						TIPO_MENSAJE.ERROR));
				log.trace("Alumno[ " + id + "] no se ha modificado correctamente");
			}
		} catch (Exception e) {
			log.warn("Excepci�n general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepci�n general", "0", TIPO_MENSAJE.ERROR));
		}

		// request.setAttribute("h1", "Listado del alumno " +
		// nuevoAlumno.getNombre());
		// request.setAttribute("titulo", "Listado alumno | Egunon Euskadi");
		
		// Establecer el id a null para que vaya al listado de alumnos
		
		doGet(request, response);

		log.trace("Eliminar Alumno - Fin");

	}

	/**
	 * M�todo para modificar un alumno
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Funci�n modificar alumno
		// Recoger los datos del alumno

		log.trace("Modificar Alumno");
		RequestDispatcher dispatcher = null;
		String nombre = (String) request.getParameter("nombre");
		String apellido = (String) request.getParameter("apellido");
		String email = (String) request.getParameter("email");
		int edad = (int) Integer.parseInt(request.getParameter("edad"));
		String dni = (String) request.getParameter("dni");
		int id = (int)Integer.parseInt(request.getParameter("id"));

		try {

			modificadoAlumno = new Alumno();

			modificadoAlumno.setNombre(nombre);
			modificadoAlumno.setApellido(apellido);
			modificadoAlumno.setEmail(email);
			modificadoAlumno.setEdad(edad);
			modificadoAlumno.setDni(dni);
			// Crear alumno nuevoAlumno
			boolean actualizado = modelo.update(modificadoAlumno, id);
			if (actualizado) {
				// Modificaci�n OK
				// Crear mensaje de todo correcto
				request.setAttribute("msg", new Mensaje("Alumno modificado correctamente", "300", TIPO_MENSAJE.INFO));
				log.trace("Alumno " + modificadoAlumno.getNombre() + " modificado correctamente");
				// Obtener el id del alumno insertado
			} else {
				// Modificaci�n NO OK
				// Crear mensaje se ha producido un error al insertar en la
				// bbdd
				request.setAttribute("msg", new Mensaje("Se ha producido unu error al modificar el alumno, por favor intentalo m�s tarde", "500",
						TIPO_MENSAJE.ERROR));
				log.trace("Alumno[ " + id + "] " + modificadoAlumno.getNombre() + " no se ha modificado correctamente");
			}
		} catch (AlumnoException e) {
			// Datos no son correctos
			log.warn("Los datos introducidos para el  nuevo alumno no son correctos [" + e.getMensajeError() + "," + e.getCodigoError() + "]");
			request.setAttribute("id", nombre);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido", apellido);
			request.setAttribute("email", email);
			request.setAttribute("edad", edad);
			request.setAttribute("dni", dni);
			// Crear mensaje de datos incorrectos
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), "203", TIPO_MENSAJE.ERROR));
			request.setAttribute("titulo", "Modificar Alumno | Egunon Euskadi");
			request.setAttribute("h1", "Modificar alumno");
		} catch (Exception e) {
			log.warn("Excepci�n general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepci�n general", "0", TIPO_MENSAJE.WARNING));
		}

		request.setAttribute("detalleAlumno", modificadoAlumno);

		request.setAttribute("h1", "Detalle del alumno " + modificadoAlumno.getNombre());
		request.setAttribute("titulo", "Detalle alumno | Egunon Euskadi");
		modificadoAlumno.setId(id);
		request.setAttribute("id", id);
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		log.trace("Modificar Alumno - Fin");
		dispatcher.forward(request, response);

	}

	/**
	 * Metodo para insertar un nuevo alumno
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Funci�n crear nuevo alumno
		// Recoger los datos del alumno nuevo

		log.trace("Crear Alumno");
		RequestDispatcher dispatcher = null;
		int id = 0;
		String nombre = (String) request.getParameter("nombre");
		String apellido = (String) request.getParameter("apellido");
		String email = (String) request.getParameter("email");
		int edad = (int) Integer.parseInt(request.getParameter("edad"));
		String dni = (String) request.getParameter("dni");

		try {

			nuevoAlumno = new Alumno();
			nuevoAlumno.setNombre(nombre);
			nuevoAlumno.setApellido(apellido);
			nuevoAlumno.setEmail(email);
			nuevoAlumno.setEdad(edad);
			nuevoAlumno.setDni(dni);
			// Crear alumno nuevoAlumno
			int insertado = modelo.insert(nuevoAlumno);
			if (insertado > 0) {
				// Insertado OK
				// Crear mensaje de todo correcto
				request.setAttribute("msg", new Mensaje("Alumno insertado correctamente", "300", TIPO_MENSAJE.INFO));
				log.trace("Alumno " + nuevoAlumno.getNombre() + " insertado correctamente");
				// Obtener el id del alumno insertado
				nuevoAlumno.setId(insertado);
			} else {
				// Insertado NO OK
				// Crear mensaje se ha producido un error al insertar en la
				// bbdd
				request.setAttribute("msg", new Mensaje("Se ha producido unu error al crear el alumno, por favor intentalo m�s tarde", "500",
						TIPO_MENSAJE.ERROR));
				log.trace("Alumno " + nuevoAlumno.getNombre() + " no se ha insertado correctamente");
			}
		} catch (AlumnoException e) {
			// Datos no son correctos
			log.warn("Los datos introducidos para el  nuevo alumno no son correctos [" + e.getMensajeError() + "," + e.getCodigoError() + "]");
			request.setAttribute("id", nombre);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido", apellido);
			request.setAttribute("email", email);
			request.setAttribute("edad", edad);
			request.setAttribute("dni", dni);
			// Crear mensaje de datos incorrectos
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), "203", TIPO_MENSAJE.ERROR));
			request.setAttribute("titulo", "Crear Alumno | Egunon Euskadi");
			request.setAttribute("h1", "Crear alumno");
		} catch (Exception e) {
			log.warn("Excepci�n general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepci�n general", "0", TIPO_MENSAJE.WARNING));
		}

		request.setAttribute("detalleAlumno", nuevoAlumno);

		request.setAttribute("h1", "Detalle del alumno " + nuevoAlumno.getNombre());
		request.setAttribute("titulo", "Detalle alumno | Egunon Euskadi");

		request.setAttribute("id", id);
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");

		log.trace("Crear Alumno - Fin");
		dispatcher.forward(request, response);
	}

}
