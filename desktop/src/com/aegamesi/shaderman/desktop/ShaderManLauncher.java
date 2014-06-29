package com.aegamesi.shaderman.desktop;

import javax.swing.JFrame;

import com.aegamesi.shaderman.ShaderMan;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;

public class ShaderManLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		ShaderMan shaderMan = new ShaderMan();
		LwjglCanvas canvas = new LwjglCanvas(shaderMan, config);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Runtime.getRuntime().halt(0); // address "deadlock causing Swing shutdown hooks"
			}
		});

		JFrame frame = new JFrame("ShaderMan Studio");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ShaderManUI(frame, canvas, shaderMan));
		frame.pack();
	}
}
