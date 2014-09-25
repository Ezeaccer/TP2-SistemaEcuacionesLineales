import java.io.*; 
import java.text.*;







public class MatrizMath {

	protected int filas;
	protected int col;
	protected double mat[][];
	
	public MatrizMath (){
		mat = null;
		filas = 0;
		col = 0;
	}
	
	public MatrizMath (int filas, int col){
		this.filas = filas;
		this.col = col;
		this.mat = new double[filas][col];	
	}
	
	public MatrizMath sumarM(MatrizMath mat) throws Exception{
		MatrizMath m = null;
		if(this.filas == mat.filas && this.col == mat.col){		
		m = new MatrizMath(filas, col);
		for (int i=0; i<col; i++)
			for (int j=0; j<filas; j++)
				m.mat[i][j] = this.mat[i][j] + mat.mat[i][j];
		}
		else
			throw new Exception ("No se pueden sumar matrices de distintas dimesiones");
		return m;
	}
	
	public MatrizMath restarM(MatrizMath mat) throws Exception{
		MatrizMath m = null;
		if(this.filas == mat.filas && this.col == mat.col){		
		m = new MatrizMath(filas, col);
		for (int i=0; i<col; i++)
			for (int j=0; j<filas; j++)
				m.mat[i][j] = this.mat[i][j] - mat.mat[i][j];
		}
		else
			throw new Exception ("No se pueden restar matrices de distintas dimesiones");
		return m;
	}
	
	public MatrizMath (String nombre){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try{
			archivo = new File(nombre);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			linea = br.readLine();
			String[] datos;
			datos = linea.split(" ");
			filas = Integer.parseInt(datos[0]);
			col = Integer.parseInt(datos[1]);
			
			mat = new double [filas][col];
			int fila, columna;
			double dato;
			linea = br.readLine();
			while(linea != null){
						datos = linea.split(" ");
						fila = Integer.parseInt(datos[0]);
						columna = Integer.parseInt(datos[1]);
						dato = Double.parseDouble(datos[2]);
						mat[fila][columna] = dato;
						linea = br.readLine();
					}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
	}
	
	public void grabarMatriz (String nombre){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			archivo = new File(nombre);
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat ("##.##", simbolo);
			
			pw.println(filas + " " + col);
			
			for (int j=0; j<col; j++){
				for (int i=0; i<filas; i++)
					pw.println(i + " " + j + " " + formateador.format(mat[i][j]));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public static void generarMatriz (String nombre, int fila, int columna){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
//			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
//			simbolo.setDecimalSeparator('.');
//			DecimalFormat formateador = new DecimalFormat ("##.##", simbolo);
			archivo = new File(nombre);
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			pw.println(fila + " " + columna);
			
			for (int i=0; i<fila; i++){
				for (int j=0; j<columna; j++)
					pw.println(i + " " + j + " " + Math.round(Math.random()*100));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pw.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
public MatrizMath matrizPorFloat (float num){
	MatrizMath m = new MatrizMath(this.filas, this.col);
	for (int i=0; i<filas; i++)
		for (int j=0; j<col; j++)
			m.mat[i][j] = this.mat[i][j] * num;
	return m;
}

public MatrizMath matrizPorMatriz (MatrizMath m) throws Exception{
	MatrizMath res = null;
	if(this.col == m.filas){
		res = new MatrizMath(this.filas, m.col);
		for (int i=0; i<this.filas; i++)
			for (int j=0; j<m.col; j++){
					res.mat[i][j] = 0.0;
					for(int k = 0; k<this.col; k++)
						res.mat[i][j] += this.mat[i][k] * m.mat[k][j];
			}	
	}
	else
		throw new Exception("No se pueden multiplicar las matrices");
	return res;
} 


public MatrizMath clone (){
	MatrizMath m = new MatrizMath(filas, col);
	for(int i = 0; i<filas; i++)
		for(int j = 0; j<col; j++)
			m.mat[i][j] = this.mat[i][j];
	return m;
}

public void genIdentidad () throws RuntimeException{
	if(filas != col)
		throw new RuntimeException ("No es cuadrada");
	for(int i=0; i<filas; i++)
		for(int j=0; j<filas; j++)
			if(i==j)
				mat[i][j]=1.0;
			else
				mat[i][j]=0.0;
}

public void restarUnaFilaAOtra(Integer filaOrigen, Integer filaDestino,
		Double escalar) {
	for (int j = 0; j < col; j++)
		mat[filaDestino][j] -= mat[filaOrigen][j] * escalar;
}

public void intercambirFilas(Integer filaOrigen, Integer filaDestino) {
	for (int j = 0; j < col; j++) {
		Double aux = mat[filaDestino][j];
		mat[filaDestino][j] = mat[filaOrigen][j];
		mat[filaOrigen][j] = aux;
	}
}


public MatrizMath ampliar(MatrizMath segundaParte) {
	MatrizMath aumentada = new MatrizMath(filas, col
			+ segundaParte.col);
	for (int i = 0; i < aumentada.filas; i++)
		for (int j = 0; j < col; j++) {
			aumentada.mat[i][j] = this.mat[i][j];
			aumentada.mat[i][j + col] = segundaParte.mat[i][j];
		}
	return aumentada;
}

public void multiplicarFila(Integer fila, Double escalar) {
	for (int j = 0; j < col; j++)
		mat[fila][j] *= escalar;
}

public MatrizMath inversaGaussJordan() throws DistDimException {
	
	if(this.determinante() == 0)
		throw new DistDimException("No tiene solucion, la matriz no tiene inversa");
	MatrizMath aumentada = this.ampliar(MatrizMath.identidad(filas));
	MatrizMath inversa = new MatrizMath(filas, col);
//	System.out.println("inicio:\n "+aumentada); 
	for (int k = 0; k < col; k++) {
		if (aumentada.mat[k][k] != 0) {
			aumentada.multiplicarFila(k, 1 / aumentada.mat[k][k]);
			for (int i = k + 1; i < filas; i++) {
				if (aumentada.mat[i][k] != 0) {
					Double escalar = aumentada.mat[i][k];
					aumentada.restarUnaFilaAOtra(k, i, escalar);
				}
			}
		} else {
			// intercambiar filas
			Integer unoPrincipalActual = k;
			while (aumentada.mat[k][unoPrincipalActual] == 0
					&& k + 1 < col) {
				if (aumentada.mat[k + 1][unoPrincipalActual] != 0)
					aumentada.intercambirFilas(k + 1, unoPrincipalActual);
				k++;
			}
//			if (k == col)
//				throw new DistDimException("No tiene solucion, la matriz no tiene inversa");
			k = unoPrincipalActual - 1;
		}
	}
//	System.out.println("gauss: " + aumentada);
	for (int k = col - 1; k >= 0; k--) {
		for (int i = k - 1; i >= 0; i--) {
			if (aumentada.mat[i][k] != 0) {
				Double escalar = aumentada.mat[i][k];
				aumentada.restarUnaFilaAOtra(k, i, escalar);
			}
		}
	}
//	System.out.println("jordan" + aumentada);
	for (int i = 0; i < filas; i++)
		for (int j = 0; j < col; j++)
			inversa.mat[i][j] = aumentada.mat[i][col + j];
	return inversa;
}


public Double determinante() throws DistDimException {
	Double det = 0.0;
	if (filas != col)
		throw new DistDimException("La matriz no es cuadrada" + filas + " - " + col);
	if (mat == null)
		throw new DistDimException("Matriz NULL!");
	if (filas == 2)
		return (mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1]);
	for (int i = 0; i < col; i++)
		det += mat[0][i] * Math.pow(-1, i) * this.subMatriz(0, i).determinante();
	return det;
}

private MatrizMath subMatriz(Integer i, Integer j) {
	MatrizMath subMat = new MatrizMath(filas - 1, col - 1);
	int m = 0;
	for (int x = 0; x < filas; x++) {
		int n = 0;
		if (i != x) {
			for (int y = 0; y < col; y++) {
				if (j != y) {
					subMat.mat[m][n] = mat[x][y];
					n++;
				}
			}
			m++;
		}
	}
	return subMat;
}


/*
public double calcularDeterminante (){
	if(filas == 1 && col == 1){
		return abs(mat[0][0]);
	}
	if(filas == 2 && col == 2){
		return (mat[0][0]*mat[1][1] - mat[1][0]*mat[0][1]);
	}
	double det = 0;
	MatrizMath m[] = new MatrizMath[filas];
	for(int j = 0; j < col; j++){
		m[j] = new MatrizMath(filas - 1, col - 1);
		for (int i = 0; i < filas - 1; i++)
			for(int k = 0; k < col - 1; i++)
				m[j].mat[i][k] = this.mat[i+1][]  
		det += pow(-1, 1 + (j+1)) * m[j].calcularDeterminante();
	}
	
	
	
	
}
	*/
	
public static MatrizMath identidad(int dim) {
	MatrizMath ident = new MatrizMath(dim, dim);
	for (int i = 0; i < dim; i++)
		ident.mat[i][i] = 1.0;
	return ident;
}

public String toString (){
	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	simbolo.setDecimalSeparator('.');
//	DecimalFormat formateador = new DecimalFormat ("##.##", simbolo);
	StringBuilder matriz = new StringBuilder ();
	for (int i=0; i<filas; i++){
		for (int j=0; j<col; j++){
//			matriz.append(formateador.format(mat[i][j]));
			matriz.append(mat[i][j]);
			matriz.append("  ");
		}
		matriz.append("\n");
	}
	return matriz.toString();
}
	
public double normaDos (){
	double norma = 0;
	for (int i = 0 ; i<filas; i++)
		for (int j = 0; j<col; j++)
			norma += Math.pow(mat[i][j], 2);
	return Math.pow(norma, 0.5);
}

	public static void main(String[] args) {
		MatrizMath m = new MatrizMath ("entrada.in");
		MatrizMath m2 = new MatrizMath ();
		m2 = m.inversaGaussJordan();
		System.out.println(m2);
	

		
	}

	public double[][] getMatriz() {
		return this.mat;
	}
	
	public Integer getFilas(){
		return this.filas;
	}
}

