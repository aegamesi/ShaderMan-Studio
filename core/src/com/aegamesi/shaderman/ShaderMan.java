package com.aegamesi.shaderman;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class ShaderMan extends Game {
	public Mesh mesh;
	public ShaderProgram shader;

	public Vector2 size = new Vector2();
	public List<Texture> textures = new ArrayList<Texture>();

	@Override
	public void create() {
		mesh = new Mesh(true, 4, 6, VertexAttribute.Position());
		mesh.setVertices(new float[] { -1, -1, 0, -1, 1, 0, 1, 1, 0, 1, -1, 0 });
		mesh.setIndices(new short[] { 0, 1, 2, 0, 2, 3 });

		System.out.println("Compiling shaders...");
		shader = new ShaderProgram(Gdx.files.internal("shaders/default.vsh"), Gdx.files.internal("shaders/default.fsh"));
		System.out.print("Done. ");
		System.out.println(shader.getLog());
		if (!shader.isCompiled())
			System.out.println("ERROR: Shader did not compile!");

		addTexture("tex/tex_0.png");
		addTexture("tex/tex_1.png");
	}

	public void addTexture(String name) {
		textures.add(new Texture(Gdx.files.internal(name)));
	}

	@Override
	public void resize(int width, int height) {
		size.set(width, height);
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		for (int i = 0; i < textures.size(); i++)
			textures.get(i).bind(i);
		shader.begin();
		for (int i = 0; i < textures.size(); i++)
			if (shader.hasUniform("u_texture_" + i))
				shader.setUniformi("u_texture_" + i, i);
		shader.setUniformf("u_size", size);
		mesh.render(shader, GL20.GL_TRIANGLES);
		shader.end();
	}
}
