package program;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.model.Article;

import java.io.FileWriter;

public class WriteArticleJSON {

	private JSONObject data;


	public WriteArticleJSON(){
		data = new JSONObject();
		data.put("Articles", new JSONArray());
	}

	/**
	 * ajout d'un article dans le json
	 * @param article
	 */
	public void addArticle(Article article){
		JSONObject jsonArticle = new JSONObject();

		jsonArticle.put("catégorie", article.getCatégorie().toSTring());
		jsonArticle.put("prix", article.getPrix());
		jsonArticle.put("nom", article.getNom());

		JSONArray array= (JSONArray) data.get("Articles");


		array.add(jsonArticle);
	}

	/**
	 * ecriture des articles dans le fichier /articles.json
	 */
	public void writeFile(){
		try(FileWriter file = new FileWriter("src/resources/json/article.json")){
			file.write(data.toString());
			file.flush();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public void clear(){
		data = new JSONObject();
		data.put("Articles", new JSONArray());
		writeFile();
	}
}
