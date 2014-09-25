
import java.io.*;


/** La clase es un vector matemático  
 * @author No hay */

public class VectorMath extends MatrizMath {

	protected int tam;
	protected double [] vec;
	
	public VectorMath (){
		tam = 0;
		vec = null;
	}
	
	public VectorMath (int tam){
		vec = new double [tam];
		this.tam = tam;
	}
	
	public VectorMath(String nombre) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(nombre);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = "";

			linea = br.readLine();
			tam = Integer.parseInt(linea);
			vec = new double[tam];

			for (int i = 0; i < tam; i++) {
				linea = br.readLine();
				vec[i] = Double.parseDouble(linea);
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
	
	public VectorMath sumar(VectorMath vec) throws DistDimException{
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		
		VectorMath aux = new VectorMath(tam);
		for (int i=0; i<tam; i++)
			aux.vec[i]=this.vec[i]+vec.vec[i];
		return aux;
	}
	
	public VectorMath restar (VectorMath vec) throws DistDimException{
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		
		VectorMath aux = new VectorMath(tam);
		for (int i=0; i<tam; i++)
			aux.vec[i]=this.vec[i] - vec.vec[i];
		return aux;
	}
	
	public double multipicarPorOtroVector (VectorMath vec) throws DistDimException{
		double res = 0;
		if (tam != vec.tam)
			throw new DistDimException(" Distinta Dimension ");
		for (int i = 0; i < tam; i++)
			res += this.vec[i] * vec.vec [i];
		return res;
		}
	
	public VectorMath vectorPorMatriz (MatrizMath m) throws Exception{
		if(this.tam == m.filas){
			VectorMath res = new VectorMath(m.col);
			for (int j=0; j<m.col; j++){
						res.vec[j] = 0;
						for(int k = 0; k < m.filas; k++)
							res.vec[j] += this.vec[k] * m.mat[k][j];
			}
				
			return res;
		}
		else
			throw new Exception ("No se puede multiplicar");
	}
	
	
	public MatrizMath toMatrizMathColumna() {
		MatrizMath resultado = new MatrizMath(tam, 1);
		for (int i = 0; i < tam; i++)
			resultado.getMatriz()[i][0] = vec[i];
		return resultado;
	}
	
	
	public String toString () {
		StringBuilder vector = new StringBuilder("");
		if (tam != 0) {
			if(tam == 1){
				vector.append(vec[0]);
			}
			else{
				vector.append ( "(" );
				vector.append ( vec[0] );
				
				for (int i = 1; i < tam; i++) {
					vector.append (", ");
					vector.append ( vec[i] );
				}
				vector.append ( ")" );
			}
		} else
			vector.append("El vector está vacio");
		return vector.toString();
	}
			
	public static void main(String[] args) {
		//double res;
	VectorMath vec = new VectorMath("entradaV.in");
//		VectorMath vec2 = new VectorMath("entradaV2.in");
	VectorMath vec3 = new VectorMath(3);
	 MatrizMath m2 = new MatrizMath ("entradaM2.in");
	 System.out.println(vec);
	 System.out.println(m2);
	 try{
		 vec3 = vec.vectorPorMatriz(m2);	
		 System.out.println(vec3);
	 }catch (Exception e){
		 e.printStackTrace();
	 }

//		vec3 = (vec.sumar(vec2));
//		System.out.println(vec);
//		System.out.println(vec2);
//		System.out.println(vec3);
		
	
		
		
		
		
	}
}
