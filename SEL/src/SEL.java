import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SEL {

	private MatrizMath matrizCoeficientes;
	private VectorMath vectorResultados;
	private MatrizMath vectorColumnaSolucion;

	public SEL(String nombre) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(nombre);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = "";
			linea = br.readLine();
			System.out.println(linea);
			int dim = Integer.parseInt(linea);	
			matrizCoeficientes = new MatrizMath(dim, dim);
			vectorResultados = new VectorMath(dim);
			String[] datos;
			int fila, columna;
			double dato;
			linea = br.readLine();
			for(int i = 0; i < dim*dim; i++) {
				datos = linea.split(" ");
				fila = Integer.parseInt(datos[0]);
				columna = Integer.parseInt(datos[1]);
				dato = Double.parseDouble(datos[2]);
				matrizCoeficientes.getMatriz()[fila][columna] = dato;
				linea = br.readLine();

			}
			for (int i = 0; i < dim; i++) {
				vectorResultados.vec[i] = Double.parseDouble(linea);
				linea = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void resolverSistema(){
		vectorColumnaSolucion = new MatrizMath (vectorResultados.tam, 1);
		try{
			vectorColumnaSolucion = matrizCoeficientes.inversaGaussJordan().matrizPorMatriz(vectorResultados.toMatrizMathColumna());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	public void guardarSolucion(String nombre){
		CreateFile archivo = null;
		
		try{
			archivo = new CreateFile(nombre);
			archivo.writeLine(vectorColumnaSolucion.getFilas().toString());
			for(int i = 0; i< vectorColumnaSolucion.getFilas(); i++){
				Double dato = vectorColumnaSolucion.getMatriz()[i][0];  //No me dejaba mandarle directamente esto a writeLine
				archivo.writeLine(dato.toString());
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				archivo.close();
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		SEL s1 = new SEL("sel.in");
		System.out.println(s1.matrizCoeficientes);
		System.out.println(s1.vectorResultados);
		s1.resolverSistema();
		System.out.println(s1.vectorColumnaSolucion);
		s1.guardarSolucion("solucion.out");
	}

}
