import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
 
/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/helloWorld")
public class HelloWorld extends HttpServlet {

    public ArrayList<String> res = new ArrayList<String>();  // Puesto en public para la evaluación de los JUnit tests
	
	private static final long serialVersionUID = 1L;
 
        public HelloWorld() {
            super();
         }
 
	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* protected void doGet(HttpServletRequest request, 
                                       HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name").trim();
		response.setContentType("text/html"); 
    	        PrintWriter out = response.getWriter(); 
    	        out.print("<h2>Hello "+name+ "</h2>"); 
    	        out.close();
	} */
   @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
        {
        doPost(req,res);
        }
 
	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doPost(HttpServletRequest request, 
                                       HttpServletResponse response) throws ServletException, IOException {
		
                try {
                    String raw = request.getParameter("words").trim(); //Toma las palabras del parametro words
                    String [] words = raw.split("\\r?\\n");           //Las separa dependiendo si hay un enter
                    boolean moreThanOneSlash = false;                       //Revisa si hay mas de un "/" en cualquiera de las palabras
                    int temp = 0;                      
                    for (int index = 0; index < words.length; index++) {    //Recorre cada set de palabras
                        for (int jindex = 0; jindex < words[index].length(); jindex++) {    //Recorre cada palabra individualmente
                            if(words[index].charAt(jindex) == '/'){
                                    temp++;
                            }
                        }
                        if(temp>1){
                            moreThanOneSlash =true;                         //Si la palabra contiene mas de un slash pone el valor en true

                        }                                                   //A pesar de que el programa sabe que una de los valores ya es incorrecto 
                                                                            //va a intentar correr el resto de las palabras antes de arrojar una excepción
                        temp = 0;
                        
                    }                                                       

                    for (String name : words) {

                        if(!name.contains("/")){                              //Si no contiene "/" lanza una excepcion
                            throw new Exception("La palabra no contiene /");
                        }else if(moreThanOneSlash){                             //Si contiene mas de un "/" lanza una excepcion
                            throw new Exception("La palabra contiene mas de un /");
                        }else{
                            String [] numWord = name.split("/");          //divide la frase en el / y pone sus datos en variables
                            int number = Integer.parseInt(numWord[0]);
                            String phrase = numWord[1];

                            String [] letters = phrase.split("\\s+");     //Separa las letras usando regex en espacios en blanco
                            response.getWriter().println(phrase + "/" + ((letters.length == number) ? true : false)); //concatena la frase y si el tamaño de la palabra corresponde al numero devuelve true en caso contrario, false
                            
                            res.add(phrase + "/" + ((letters.length == number) ? true : false));      //Evaluacion de los tests JUnit
                        }
                    }
                    
                        
                } catch (Exception e) {
                    response.getWriter().println(e.toString());
                }
                
	}
 
}