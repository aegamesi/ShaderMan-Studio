package com.aegamesi.shaderman.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.aegamesi.shaderman.ShaderMan;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;

public class ShaderManUI extends JPanel {
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	public LwjglCanvas canvas;
	public ShaderMan shaderMan;

	public ShaderManUI(JFrame frame, LwjglCanvas canvas, ShaderMan shaderMan) {
		this.frame = frame;
		this.canvas = canvas;
		this.shaderMan = shaderMan;

		setBackground(Color.white);
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);

		canvas.getCanvas().setBounds(10, 10, 500, 500);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				addCanvas();
			}
		});
	}
	
	public void addCanvas() {
		add(canvas.getCanvas());
		frame.setVisible(true);
		canvas.getCanvas().requestFocus();
	}

	public void paintComponent(Graphics _g) {
		super.paintComponent(_g);
		Graphics2D g = (Graphics2D) _g;
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(canvas.getCanvas().getX() - 2, canvas.getCanvas().getY() - 2, canvas.getCanvas().getWidth() + 4, canvas.getCanvas().getHeight() + 4);
	}
}
