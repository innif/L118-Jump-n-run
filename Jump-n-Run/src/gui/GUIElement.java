package gui;

public class GUIElement {
	
	private float x;
	private float y;
	private float width;
	private float height;

	private boolean isMouseOver;
	
	public GUIElement(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		GUICallback callback = new GUICallback()
		{
			@Override
			public void onPressed()
			{
				
			}
		};
	}
	
	public void udpate()
	{
		
		onUpdate();
	}
	
	protected void onUpdate() {}
	
}
